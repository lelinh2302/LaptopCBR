
package linh.cbr.laptop;


import java.util.ArrayList;
import java.util.Date;

import jcolibri.casebase.LinealCaseBase;
import jcolibri.cbraplications.StandardCBRApplication;
import jcolibri.cbrcore.Attribute;
import jcolibri.cbrcore.CBRCase;
import jcolibri.cbrcore.CBRCaseBase;
import jcolibri.cbrcore.CBRQuery;
import jcolibri.cbrcore.Connector;
import jcolibri.connector.PlainTextConnector;
import jcolibri.exception.AttributeAccessException;
import jcolibri.exception.ExecutionException;



public class CBRMainApp implements StandardCBRApplication {

	Connector _connector;
	CBRCaseBase _caseBase;
	

	@Override
	public void configure() throws ExecutionException {
		try{
			_connector = new PlainTextConnector();
			_connector.initFromXMLfile(jcolibri.util.FileIO.findFile("linh/cbr/laptop/plaintextconfig.xml"));
			_caseBase  = new LinealCaseBase();
			} catch (Exception e){
				throw new ExecutionException(e);
		}

	}
	

	@Override
	public CBRCaseBase preCycle() throws ExecutionException {
		System.out.println("PreCycle!");
		_caseBase.init(_connector);
		java.util.Collection<CBRCase> cases = _caseBase.getCases();
		for(CBRCase c: cases)
			System.out.println(c);
		return _caseBase;
	}


	@Override
	public void cycle(CBRQuery query) throws ExecutionException {
		System.out.println("Cycle!");
		//Obtain only the first case
		CBRCase newcase = _caseBase.getCases().iterator().next();
		//Modify its id attribute and store it back
		Attribute id = newcase.getDescription().getIdAttribute();
		System.out.println("Attribute!");
		try {
			Date d = new Date();
			id.setValue(newcase.getDescription(), ("case "+d.toString()).replaceAll(" ", "_"));
			System.out.println("getDescription!");
		} catch (AttributeAccessException e) {
			org.apache.commons.logging.LogFactory.getLog(this.getClass()).error(e);
		}
		
		ArrayList<CBRCase> casestoLearnt = new ArrayList<CBRCase>();
		casestoLearnt.add(newcase);
		_caseBase.learnCases(casestoLearnt);
		System.out.println("learnCases!");

	}


	@Override
	public void postCycle() throws ExecutionException {
		System.out.println("PostCycle!");
		_connector.close();

	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CBRMainApp test = new CBRMainApp();
		try {
			test.configure();
			test.preCycle();
			test.cycle(null);
			test.postCycle();
		} catch (ExecutionException e) {
			org.apache.commons.logging.LogFactory.getLog(CBRMainApp.class).error(e);
		}

	}

}
