import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class HomeBankingController
{
	HomeBankingController()
	{
	}
	
	public ArrayList<Person> getPeopleList()
	{
		return Database.getPeople();
	}
	
	public ArrayList<Child> getChildrenList()
	{
		return Database.getChildren();
	}
	
	public ArrayList<Bank> getBankList()
	{
		return Database.getBanks();
	}
	
	public boolean addPerson(String firstName, String lastName, String ID)
	{
		return Database.addPerson(new Person(firstName, lastName, ID));
	}
	
	public boolean removePerson(String id)
	{
		return Database.removePerson(id);
	}
	
	public boolean addChild(String firstName, String lastName, String ID, String ParentID)
	{
		return Database.addChild(new Child(firstName, lastName, ID, ParentID));
	}
	
	public boolean removeChild(String id)
	{
		return Database.removeChild(id);
	}
	
	public boolean addBank(String bankName)
	{
		return Database.addBank(new Bank(bankName));
	}
	
	public boolean removeBank(String id)
	{
		return Database.removeBank(id);
	}
	
	//Iteration 2
	public boolean addDependent(String accID, Child c)
	{
		ArrayList<Bank> bankList = getBankList();
		for(Bank b : bankList)
		{
			ArrayList<Account> accs = b.getAccounts();
			for(Account a : accs)
			{
				if(a.getID() == accID)
				{
					a.addDependent(c);
					return Database.updateBank(b);
				}
			}
		}
		return false;
	}
	
	public boolean removeDependent(String accID, Child c)
	{
		ArrayList<Bank> bankList = getBankList();
		for(Bank b : bankList)
		{
			ArrayList<Account> accs = b.getAccounts();
			for(Account a : accs)
			{
				if(a.getID() == accID)
				{
					a.removeDependent(c);
					return Database.updateBank(b);
				}
			}
		}
		return false;
	}
	
	public double getAccountBalance(String accID)
	{
		ArrayList<Bank> bankList = getBankList();
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
		ArrayList<Bank> bankList = getBankList();
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
		ArrayList<Bank> bankList = getBankList();
		for(Bank b : bankList)
		{
			ArrayList<Account> accs = b.getAccounts();
			for(Account a : accs)
			{
				if(a.getID() == accID)
				{
					a.addBill(bill);
					Database.updateBank(b);
				}
			}
		}
		return false;		
	}
	
	public boolean removeBilling(String accID, Billing bill)
	{
		ArrayList<Bank> bankList = getBankList();
		for(Bank b : bankList)
		{
			ArrayList<Account> accs = b.getAccounts();
			for(Account a : accs)
			{
				if(a.getID() == accID)
				{
					a.removeBill(bill);
					Database.updateBank(b);
				}
			}
		}
		return false;	
	}
	
	public double getLoanAmount(String accID)
	{
		ArrayList<Bank> bankList = getBankList();
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
		ArrayList<Bank> bankList = getBankList();
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
		ArrayList<Bank> bankList = getBankList();
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
	
	public boolean addAutoBilling(String accID, AutoBilling aB)
	{
		ArrayList<Bank> bankList = getBankList();
		for(Bank b : bankList)
		{
			ArrayList<Account> accs = b.getAccounts();
			for(Account a : accs)
			{
				if(a.getID() == accID)
				{
					a.addAutoBill(aB);
					return Database.updateBank(b);
				}
			}
		}
		return false;	
	}
	
	public boolean removeAutoBilling(String accID, AutoBilling aB)
	{
		ArrayList<Bank> bankList = getBankList();
		for(Bank b : bankList)
		{
			ArrayList<Account> accs = b.getAccounts();
			for(Account a : accs)
			{
				if(a.getID() == accID)
				{
					a.removeAutoBill(aB);
					return Database.updateBank(b);
				}
			}
		}
		return false;	
	}
	
	public boolean addManualBilling(String accID, ManualBilling aB)
	{
		ArrayList<Bank> bankList = getBankList();
		for(Bank b : bankList)
		{
			ArrayList<Account> accs = b.getAccounts();
			for(Account a : accs)
			{
				if(a.getID() == accID)
				{
					a.addManBill(aB);
					return Database.updateBank(b);
				}
			}
		}
		return false;	
	}
	
	public boolean removeManualBilling(String accID, ManualBilling aB)
	{
		ArrayList<Bank> bankList = getBankList();
		for(Bank b : bankList)
		{
			ArrayList<Account> accs = b.getAccounts();
			for(Account a : accs)
			{
				if(a.getID() == accID)
				{
					a.removeManBill(aB);
					return Database.updateBank(b);
				}
			}
		}
		return false;	
	}
	
	public double getTransferAmount(String accID)
	{
		ArrayList<Bank> bankList = getBankList();
		for(Bank b : bankList)
		{
			ArrayList<Account> accs = b.getAccounts();
			for(Account a : accs)
			{
				if(a.getID() == accID)
				{
					return a.getTransferAmount();
				}
			}
		}
		return -1;	
	}
	
	public boolean updateTransferAmount(String accID, double amount)
	{
		ArrayList<Bank> bankList = getBankList();
		for(Bank b : bankList)
		{
			ArrayList<Account> accs = b.getAccounts();
			for(Account a : accs)
			{
				if(a.getID() == accID)
				{
					a.updateTransferAmount(amount);
					return Database.updateBank(b);
				}
			}
		}
		return false;	
	}
	
	public String getActivity(String accID)
	{
		ArrayList<Bank> bankList = getBankList();
		for(Bank b : bankList)
		{
			ArrayList<Account> accs = b.getAccounts();
			for(Account a : accs)
			{
				if(a.getID() == accID)
				{
					String toReturn = "";
					toReturn += "Beginning account activity report..." + "\n";
					//TODO
					//toReturn += "" +  + "\n";
					return toReturn;
				}
			}
		}
		return "No account found.\n";	
	}
	
	public boolean addAsset(String accID, Asset asset)
	{
		ArrayList<Bank> bankList = getBankList();
		for(Bank b : bankList)
		{
			ArrayList<Account> accs = b.getAccounts();
			for(Account a : accs)
			{
				if(a.getID() == accID)
				{
					a.addAsset(asset);
					return Database.updateBank(b);
				}
			}
		}
		return false;	
	}
	
	public boolean removeAsset(String accID, Asset asset)
	{
		ArrayList<Bank> bankList = getBankList();
		for(Bank b : bankList)
		{
			ArrayList<Account> accs = b.getAccounts();
			for(Account a : accs)
			{
				if(a.getID() == accID)
				{
					a.removeAsset(asset);
					return Database.updateBank(b);
				}
			}
		}
		return false;	
	}
}
