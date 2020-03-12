package prototype;

public abstract class Person implements Cloneable {
	private String name; 
	private String state;
	protected String region;
	
	public Person() {
		// Default constructor
	}
		
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	
	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}
	
	/**
	 * @return the region
	 */
	public String getRegion() {
		return region;
	}
	
	// A method to clone a person instance
	public Object clone() {
		Object clone = null; 
		
		try {
			clone = super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		
		return clone;
	}
}
