import java.util.ArrayList;

public class Bank
{
	String nameOfBank;
	ArrayList<Account> accounts;
	
	public Bank()
	{
		nameOfBank = "default name";
		accounts = new ArrayList<Account>();
	}
	
	public Bank(String name)
	{
		nameOfBank = name;
		accounts = new ArrayList<Account>();
	}
	
	public String getName()
	{
		return nameOfBank;
	}
	
	public boolean addAccount(Account toAdd)
	{
		accounts.add(toAdd);
		return true;
	}
	
	public boolean removeAccount(Account toRemove)
	{
		for(Account a : accounts)
		{
			if(a.equals(toRemove))
			{
				accounts.remove(a);
				return true;
			}
		}
		return false;
	}
	
	public ArrayList<Account> getAccounts()
	{
		return accounts;
	}
}
