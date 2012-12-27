
package linh.cbr.laptop;


import java.util.ArrayList;
import java.util.Collection;
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
import jcolibri.method.retrieve.RetrievalResult;
import jcolibri.method.retrieve.NNretrieval.NNConfig;
import jcolibri.method.retrieve.NNretrieval.NNScoringMethod;
import jcolibri.method.retrieve.NNretrieval.similarity.global.Average;
import jcolibri.method.retrieve.NNretrieval.similarity.local.Equal;
import jcolibri.method.retrieve.NNretrieval.similarity.local.Interval;
import jcolibri.method.retrieve.selection.SelectCases;
import jcolibri.test.test2.TravelDescription;



public class CBRMainApp2 implements StandardCBRApplication {

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
		
		
		// First configure the KNN
				NNConfig simConfig = new NNConfig();
				// Set the average() global similarity function for the description of the case
				simConfig.setDescriptionSimFunction(new Average());				
				
				simConfig.addMapping(new Attribute("speed", LaptopDescription.class), new Equal());
				// For the duration attribute we are going to set its local similarity function and the weight
				
				
				simConfig.addMapping(new Attribute("ram", LaptopDescription.class), new Equal());				
				
				// Execute NN
				Collection<RetrievalResult> eval = NNScoringMethod.evaluateSimilarity(_caseBase.getCases(), query, simConfig);
				
				// Select k cases
				eval = SelectCases.selectTopKRR(eval, 1);
				
				// Print the retrieval
				System.out.println("Retrieved cases:");
				for(RetrievalResult nse: eval)
					System.out.println(nse);
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
		CBRMainApp2 test = new CBRMainApp2();
		try {
			test.configure();
			test.preCycle();
			//test.cycle(null);
						
			LaptopDescription queryDesc = new LaptopDescription();
			// The accommodation is a value of the Enum
			//queryDesc.setAccommodation(TravelDescription.AccommodationTypes.ThreeStars);
			queryDesc.setRam(4);
			queryDesc.setSpeed(new Float(3));
			
			
			CBRQuery query = new CBRQuery();
			query.setDescription(queryDesc);
			
			// Execute query
			test.cycle(query);					
			test.postCycle();
		} catch (ExecutionException e) {
			org.apache.commons.logging.LogFactory.getLog(CBRMainApp2.class).error(e);
		}

	}

}
