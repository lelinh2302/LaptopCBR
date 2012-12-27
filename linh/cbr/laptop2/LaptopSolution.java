
package linh.cbr.laptop2;

import jcolibri.cbrcore.Attribute;
import jcolibri.cbrcore.CaseComponent;


public class LaptopSolution implements CaseComponent {

	private String type;
	

	public String toString()
	{
		return type;
	}

	public Attribute getIdAttribute() {
		return new Attribute("type", this.getClass());
	}

	/**
	 * @return Returns the type.
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type The type to set.
	 */
	public void setType(String type) {
		this.type = type;
	}
	
}
