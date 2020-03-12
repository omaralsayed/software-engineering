package factory;

public class InvalidZip implements ZipCode{
	
	Person info = new Person();
	
	InvalidZip(String addressIn, String nameIn){
		info.setName(nameIn);
		info.setAddress(addressIn);
	}
	
	public Person getPerson(){
		return info;
	}
	
	public boolean isValid(){
		return false;
	}
	
	public void display(){
		System.out.println("invalid zip code");
	}
	
}
