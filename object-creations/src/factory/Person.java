package factory;

public class Person {

	private String name;
	private String address;
	
	public Person(){}
	
	public Person(String nameIn, String addressIn){
		name = nameIn;
		address = addressIn;
	}
	
	public String getName(){
		return name;
	}
	
	public String getAddress(){
		return address;
	}
	
	public void setName(String nameIn){
		name = nameIn;
	}
	
	public void setAddress(String addressIn){
		address = addressIn;
	}
	
}
