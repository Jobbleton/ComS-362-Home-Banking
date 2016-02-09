
public class Person
{
	String firstName;
	String lastName;
	String identifier;
	
	public Person()
	{
		firstName = "default first name";
		lastName = "default last name";
		identifier = "default identifier";
	}
	
	public Person(String firstName, String lastName, String identifier)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.identifier = identifier;
	}
	
	public String getFullName()
	{
		return firstName + " " + lastName;
	}
}
