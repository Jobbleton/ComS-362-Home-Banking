import java.util.ArrayList;

public class Bank
{
	String nameOfBank;
	ArrayList<Account> accounts;
	
	public Bank()
	{
		nameOfBank = "default name";
	}
	
	public Bank(String name)
	{
		nameOfBank = name;
	}
	
	public String getName()
	{
		return nameOfBank;
	}
	
	public boolean addAccount(Account toAdd)
	{
		//TODO: ERROR HANDLING
		accounts.add(toAdd);
		return true;
	}
}
