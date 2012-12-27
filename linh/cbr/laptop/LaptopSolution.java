
package linh.cbr.laptop;

import jcolibri.cbrcore.Attribute;
import jcolibri.cbrcore.CaseComponent;


public class LaptopSolution implements CaseComponent {

	String type;
	@Override
	public String toString()
	{
		return type;
	}
	@Override
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
