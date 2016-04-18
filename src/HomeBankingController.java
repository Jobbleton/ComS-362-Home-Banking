import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class HomeBankingController
{
	ArrayList<Person> peopleList;
	ArrayList<Child> childrenList;
	ArrayList<Bank> bankList;
	
	HomeBankingController()
	{
		peopleList = new ArrayList<Person>();
		childrenList = new ArrayList<Child>();
		bankList = new ArrayList<Bank>();
	}
	
	public ArrayList<Person> getPeopleList()
	{
		return peopleList;
	}
	
	public ArrayList<Child> getChildrenList()
	{
		return childrenList;
	}
	
	public ArrayList<Bank> getBankList()
	{
		return bankList;
	}
	
	public boolean addPerson(String firstName, String lastName, String ID)
	{
		Person p = new Person(firstName, lastName, ID);
		peopleList.add(p);
		return true;
	}
	
	public boolean removePerson(String id)
	{
		for(int i = 0; i < peopleList.size(); i++)
		{
			if(peopleList.get(i).getIdentifier().equals(id))
			{
				peopleList.remove(i);
				return true;
			}
		}
		return false;
	}
	
	public boolean addChild(String firstName, String lastName, String ID, String ParentID)
	{
		Child c = new Child(firstName, lastName, ID, ParentID);
		childrenList.add(c);
		return true;
	}
	
	public boolean removeChild(String id)
	{
		for(int i = 0; i < childrenList.size(); i++)
		{
			if(childrenList.get(i).getIdentifier().equals(id))
			{
				childrenList.remove(i);
				return true;
			}
		}
		return false;
	}
	
	public boolean addBank(String bankName)
	{
		Bank b = new Bank(bankName);
		bankList.add(b);
		return true;
	}
	
	public boolean removeBank(String id)
	{
		for(int i = 0; i < bankList.size(); i++)
		{
			if(bankList.get(i).getName().equals(id))
			{
				bankList.remove(i);
				return true;
			}
		}
		return false;
	}
	
	//Iteration 2
	public boolean addDependent(String accID, Child c)
	{
		for(Bank b : bankList)
		{
			ArrayList<Account> accs = b.getAccounts();
			for(Account a : accs)
			{
				if(a.getID() == accID)
				{
					return a.addDependent(c);
				}
			}
		}
		return false;
	}
	
	public boolean removeDependent(String accID, Child c)
	{
		for(Bank b : bankList)
		{
			ArrayList<Account> accs = b.getAccounts();
			for(Account a : accs)
			{
				if(a.getID() == accID)
				{
					return a.removeDependent(c);
				}
			}
		}
		return false;
	}
	
	public double getAccountBalance(String accID)
	{
		for(Bank b : bankList)
		{
			ArrayList<Account> accs = b.getAccounts();
			for(Account a : accs)
			{
				if(a.getID() == accID)
				{
					return a.getValue();
				}
			}
		}
		return 0;

	}
	
	public double getTotalFunds(String accID)
	{
		for(Bank b : bankList)
		{
			ArrayList<Account> accs = b.getAccounts();
			for(Account a : accs)
			{
				if(a.getID() == accID)
				{
					return a.getFundsValue();
				}
			}
		}
		return 0;	
	}
	
	public boolean addBilling(String accID, Billing bill)
	{
		for(Bank b : bankList)
		{
			ArrayList<Account> accs = b.getAccounts();
			for(Account a : accs)
			{
				if(a.getID() == accID)
				{
					return a.addBill(bill);
				}
			}
		}
		return false;		
	}
	
	public boolean removeBilling(String accID, Billing bill)
	{
		for(Bank b : bankList)
		{
			ArrayList<Account> accs = b.getAccounts();
			for(Account a : accs)
			{
				if(a.getID() == accID)
				{
					return a.removeBill(bill);
				}
			}
		}
		return false;	
	}
	
	public double getLoanAmount(String accID)
	{
		for(Bank b : bankList)
		{
			ArrayList<Account> accs = b.getAccounts();
			for(Account a : accs)
			{
				if(a.getID() == accID)
				{
					return a.getLoansValue();
				}
			}
		}
		return 0;	
	}
	
	public boolean addLoan(String accID, Loan l)
	{
		for(Bank b : bankList)
		{
			ArrayList<Account> accs = b.getAccounts();
			for(Account a : accs)
			{
				if(a.getID() == accID)
				{
					return a.addLoan(l);
				}
			}
		}
		return false;	
	}
	
	public boolean removeLoan(String accID, Loan l)
	{
		for(Bank b : bankList)
		{
			ArrayList<Account> accs = b.getAccounts();
			for(Account a : accs)
			{
				if(a.getID() == accID)
				{
					return a.removeLoan(l);
				}
			}
		}
		return false;		
	}
	
	//Iteration 3
	public boolean addAutoBilling()
	{
		return false;
	}
	
	public boolean removeAutoBilling()
	{
		return false;
	}
	
	public boolean addManualBilling()
	{
		return false;
	}
	
	public boolean removeManualBilling()
	{
		return false;
	}
	
	public boolean getTransferAmount()
	{
		return false;
	}
	
	public boolean updateTransferAmount()
	{
		return false;
	}
	
	public boolean getActivity()
	{
		return false;
	}
	
	public boolean addAsset()
	{
		return false;
	}
	
	public boolean removeAsset()
	{
		return false;
	}
}
