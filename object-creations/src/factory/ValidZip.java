package factory;

public class ValidZip implements ZipCode{

	Person info = new Person();
	
	ValidZip(String addressIn, String nameIn){
		info.setName(nameIn);
		info.setAddress(addressIn);
	}
	
	public Person getPerson(){
		return info;
	}
	
	public boolean isValid(){
		return true;
	}
	
	public void display(){
		System.out.println("valid zip code");
	}
	
}
