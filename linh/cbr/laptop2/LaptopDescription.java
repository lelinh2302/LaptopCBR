/**
 * IrisDescription.java
 * jCOLIBRI2 framework. 
 * @author Juan A. Recio-García.
 * GAIA - Group for Artificial Intelligence Applications
 * http://gaia.fdi.ucm.es
 * 03/05/2007
 */
package linh.cbr.laptop2;

import jcolibri.cbrcore.Attribute;
import jcolibri.cbrcore.CaseComponent;


public class LaptopDescription implements CaseComponent {

	private Double sepalLength;
	private String manufacture;
	private String id;
	

	public String toString()
	{
		return id+", "+sepalLength+", "+manufacture;
	}

	public Attribute getIdAttribute() {
		return new Attribute("id", this.getClass());
	}

	/**
	 * @return Returns the id.
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id The id to set.
	 */
	public void setId(String id) {
		this.id = id;
	}


	/**
	 * @return Returns the sepalLength.
	 */
	public Double getSepalLength() {
		return sepalLength;
	}

	/**
	 * @param sepalLength The sepalLength to set.
	 */
	public void setSepalLength(Double sepalLength) {
		this.sepalLength = sepalLength;
	}
	/**
	 * @return the manufacture
	 */
	public String getManufacture() {
		return manufacture;
	}
	/**
	 * @param manufacture the manufacture to set
	 */
	public void setManufacture(String manufacture) {
		this.manufacture = manufacture;
	}

	
	
}
