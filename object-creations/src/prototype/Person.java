package prototype;


// Base class "Person"
public abstract class Person implements Cloneable {
	private String name; 
	private String state;
	protected String region;
	
	public Person() {
		// Default Constructor
	}
	
	abstract void create(); 
	
	public String getName() {
		return name;
	}
	
	public String getState() {
		return state;
	}
	
	public String getRegion() {
		return region;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
	// When Person.clone() is called, it clones the person instance
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