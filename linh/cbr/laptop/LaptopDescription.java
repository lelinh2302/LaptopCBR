
package linh.cbr.laptop;

import jcolibri.cbrcore.Attribute;
import jcolibri.cbrcore.CaseComponent;


public class LaptopDescription implements CaseComponent {

	private String manufacture;
	private Float speed;
	private Integer ram;
	Double sepalLength;
	Double sepalWidth;
	Double petalLength;
	Double petalWidth;
	String id;
	@Override
	public String toString()
	{
		return id+": Manufacture:"+manufacture+", Speed:"+speed+", RAM:"+ram+", "+sepalLength+", "+sepalWidth+", "+petalLength+", "+petalWidth;
	}
	@Override
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
	 * @return Returns the petalLength.
	 */
	public Double getPetalLength() {
		return petalLength;
	}

	/**
	 * @param petalLength The petalLength to set.
	 */
	public void setPetalLength(Double petalLength) {
		this.petalLength = petalLength;
	}

	/**
	 * @return Returns the petalWidth.
	 */
	public Double getPetalWidth() {
		return petalWidth;
	}

	/**
	 * @param petalWidth The petalWidth to set.
	 */
	public void setPetalWidth(Double petalWidth) {
		this.petalWidth = petalWidth;
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
	 * @return Returns the sepalWidth.
	 */
	public Double getSepalWidth() {
		return sepalWidth;
	}

	/**
	 * @param sepalWidth The sepalWidth to set.
	 */
	public void setSepalWidth(Double sepalWidth) {
		this.sepalWidth = sepalWidth;
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
	/**
	 * @return the speed
	 */
	public Float getSpeed() {
		return speed;
	}
	/**
	 * @param speed the speed to set
	 */
	public void setSpeed(Float speed) {
		this.speed = speed;
	}
	/**
	 * @return the ram
	 */
	public Integer getRam() {
		return ram;
	}
	/**
	 * @param ram the ram to set
	 */
	public void setRam(Integer ram) {
		this.ram = ram;
	}

	
	
}
