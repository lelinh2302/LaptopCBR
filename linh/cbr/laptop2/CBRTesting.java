package linh.cbr.laptop2;


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


public class CBRTesting implements StandardCBRApplication {

	Connector _connector;
	CBRCaseBase _caseBase;
	
	
	/* (non-Javadoc)
	 * @see jcolibri.cbraplications.StandardCBRApplication#configure()
	 */

	public void configure() throws ExecutionException {
		try{
			_connector = new PlainTextConnector();
			_connector.initFromXMLfile(jcolibri.util.FileIO.findFile("linh/cbr/laptop/plaintextconfig.xml"));
			_caseBase  = new LinealCaseBase();
			} catch (Exception e){
				throw new ExecutionException(e);
		}

	}
	
	/* (non-Javadoc)
	 * @see jcolibri.cbraplications.StandardCBRApplication#preCycle()
	 */

	public CBRCaseBase preCycle() throws ExecutionException {
		
		_caseBase.init(_connector);
		java.util.Collection<CBRCase> cases = _caseBase.getCases();
		for(CBRCase c: cases)
			System.out.println(c);
		return _caseBase;
	}

	/* (non-Javadoc)
	 * @see jcolibri.cbraplications.StandardCBRApplication#cycle()
	 */

	public void cycle(CBRQuery query) throws ExecutionException {
		
		//Obtain only the first case
		CBRCase newcase = _caseBase.getCases().iterator().next();
		//Modify its id attribute and store it back
		Attribute id = newcase.getDescription().getIdAttribute();
		
		try {
			Date d = new Date();
			id.setValue(newcase.getDescription(), ("case "+d.toString()).replaceAll(" ", "_"));
			
		} catch (AttributeAccessException e) {
			org.apache.commons.logging.LogFactory.getLog(this.getClass()).error(e);
		}
		
		ArrayList<CBRCase> casestoLearnt = new ArrayList<CBRCase>();
		casestoLearnt.add(newcase);
		_caseBase.learnCases(casestoLearnt);
		

	}

	/* (non-Javadoc)
	 * @see jcolibri.cbraplications.StandardCBRApplication#postCycle()
	 */

	public void postCycle() throws ExecutionException {
		
		_connector.close();

	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CBRTesting test = new CBRTesting();
		try {
			test.configure();
			test.preCycle();
			test.cycle(null);
		} catch (ExecutionException e) {
			org.apache.commons.logging.LogFactory.getLog(CBRTesting.class).error(e);
		}

	}

}
