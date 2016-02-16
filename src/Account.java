
public class Account
{
	Person accountOwner;
	String ID;
	
	public Account()
	{
		accountOwner = null;
	}
	
	public Account(Person owner, String ID)
	{
		accountOwner = owner;
		this.ID = ID;
	}
	
	public Person getOwner()
	{
		return accountOwner;
	}
	
	public String getID()
	{
		return ID;
	}
}
