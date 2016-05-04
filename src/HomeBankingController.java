import java.util.ArrayList;

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
	
	public boolean addAccount(String bankName, String ownerID, String accID)
	{
		ArrayList<Bank> bankList = getBankList();
		for(Bank b : bankList)
		{
			if(b.getName().equals(bankName))
			{
				ArrayList<Person> peopleList = getPeopleList();
				for(Person p : peopleList)
				{
					if(p.getIdentifier().equals(ownerID))
					{
						b.addAccount(new Account(p, accID));
						return Database.updateBank(b);
					}
				}
			}
		}
		return false;
	}
	
	public String checkOwners()
	{
		String toReturn = "Owners:\n";
		ArrayList<Bank> bankList = getBankList();
		for(Bank b : bankList)
		{
			ArrayList<Account> accountList = b.getAccounts();
			for(Account a : accountList)
			{
				ArrayList<Person> peopleList = getPeopleList();
				for(Person p : peopleList)
				{
					if(p.getIdentifier().equals(a.getOwner().getIdentifier()))
					{
						toReturn += p.getFullName() + "\n";
						break;
					}
				}
			}
		}
		return toReturn;
	}
	
	public boolean removeAccount(String bankName, String accID)
	{
		ArrayList<Bank> bankList = getBankList();
		for(Bank b : bankList)
		{
			if(b.getName().equals(bankName))
			{
				ArrayList<Account> accountList = b.getAccounts();
				for(Account a : accountList)
				{
					if(a.getID().equals(accID))
					{
						b.removeAccount(a);
						return Database.updateBank(b);
					}
				}
			}
		}
		return false;
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
	public boolean addDependent(String accID, String cID)
	{
		ArrayList<Bank> bankList = getBankList();
		for(Bank b : bankList)
		{
			for(Account a : b.getAccounts())
			{
				if(a.getID().equals(accID))
				{
					for(Child c : getChildrenList())
					{
						if(c.getIdentifier().equals(cID))
						{
							a.addDependent(c);
							return Database.updateBank(b);
						}
					}
				}
			}
		}
		return false;
	}
	
	public boolean removeDependent(String accID, String cID)
	{
		ArrayList<Bank> bankList = getBankList();
		for(Bank b : bankList)
		{
			ArrayList<Account> accs = b.getAccounts();
			for(Account a : accs)
			{
				if(a.getID().equals(accID))
				{
					ArrayList<Child> children = getChildrenList();
					for(Child c : children)
					{
						if(c.getIdentifier().equals(cID))
						{
							a.removeDependent(c);
							return Database.updateBank(b);
						}

					}
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
				if(a.getID().equals(accID))
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
				if(a.getID().equals(accID))
				{
					return a.getFundsValue();
				}
			}
		}
		return 0;	
	}
	
	public boolean addBilling(String accID, String name, Double value)
	{
		ArrayList<Bank> bankList = getBankList();
		for(Bank b : bankList)
		{
			ArrayList<Account> accs = b.getAccounts();
			for(Account a : accs)
			{
				if(a.getID().equals(accID))
				{
					a.addBill(new Billing(name, value));
					return Database.updateBank(b);
				}
			}
		}
		return false;		
	}
	
	public boolean removeBilling(String accID, String name)
	{
		ArrayList<Bank> bankList = getBankList();
		for(Bank b : bankList)
		{
			ArrayList<Account> accs = b.getAccounts();
			for(Account a : accs)
			{
				if(a.getID().equals(accID))
				{
					a.removeBill(new Billing(name, 0));
					return Database.updateBank(b);
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
				if(a.getID().equals(accID))
				{
					return a.getLoansValue();
				}
			}
		}
		return 0;	
	}
	
	public boolean addLoan(String accID, String name, double value)
	{
		ArrayList<Bank> bankList = getBankList();
		for(Bank b : bankList)
		{
			ArrayList<Account> accs = b.getAccounts();
			for(Account a : accs)
			{
				if(a.getID().equals(accID))
				{
					a.addLoan(new Loan(name, value));
					return Database.updateBank(b);
				}
			}
		}
		return false;	
	}
	
	public boolean removeLoan(String accID, String name)
	{
		ArrayList<Bank> bankList = getBankList();
		for(Bank b : bankList)
		{
			ArrayList<Account> accs = b.getAccounts();
			for(Account a : accs)
			{
				if(a.getID().equals(accID))
				{
					a.removeLoan(new Loan(name, 0));
					return Database.updateBank(b);
				}
			}
		}
		return false;		
	}
	
	public boolean addAutoBilling(String accID, String name, double amountToBill, double frequency)
	{
		ArrayList<Bank> bankList = getBankList();
		for(Bank b : bankList)
		{
			ArrayList<Account> accs = b.getAccounts();
			for(Account a : accs)
			{
				if(a.getID().equals(accID))
				{
					a.addAutoBill(new AutoBilling(name, amountToBill, frequency));
					return Database.updateBank(b);
				}
			}
		}
		return false;	
	}
	
	public boolean removeAutoBilling(String accID, String name)
	{
		ArrayList<Bank> bankList = getBankList();
		for(Bank b : bankList)
		{
			ArrayList<Account> accs = b.getAccounts();
			for(Account a : accs)
			{
				if(a.getID().equals(accID))
				{
					a.removeAutoBill(new AutoBilling(name, 0, 0));
					return Database.updateBank(b);
				}
			}
		}
		return false;	
	}
	
	public boolean addManualBilling(String accID, String name, double amountToBill)
	{
		ArrayList<Bank> bankList = getBankList();
		for(Bank b : bankList)
		{
			ArrayList<Account> accs = b.getAccounts();
			for(Account a : accs)
			{
				if(a.getID().equals(accID))
				{
					a.addManBill(new ManualBilling(name, amountToBill));
					return Database.updateBank(b);
				}
			}
		}
		return false;	
	}
	
	public boolean removeManualBilling(String accID, String name)
	{
		ArrayList<Bank> bankList = getBankList();
		for(Bank b : bankList)
		{
			ArrayList<Account> accs = b.getAccounts();
			for(Account a : accs)
			{
				if(a.getID().equals(accID))
				{
					a.removeManBill(new ManualBilling(name, 0));
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
				if(a.getID().equals(accID))
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
				if(a.getID().equals(accID))
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
				if(a.getID().equals(accID))
				{
					return a.getActivity();
				}
			}
		}
		return "No account found.\n";	
	}
	
	public boolean addAsset(String accID, String name, double val)
	{
		ArrayList<Bank> bankList = getBankList();
		for(Bank b : bankList)
		{
			ArrayList<Account> accs = b.getAccounts();
			for(Account a : accs)
			{
				if(a.getID().equals(accID))
				{
					a.addAsset(new Asset(name, val));
					return Database.updateBank(b);
				}
			}
		}
		return false;	
	}
	
	public boolean removeAsset(String accID, String name)
	{
		ArrayList<Bank> bankList = getBankList();
		for(Bank b : bankList)
		{
			ArrayList<Account> accs = b.getAccounts();
			for(Account a : accs)
			{
				if(a.getID().equals(accID))
				{
					a.removeAsset(new Asset(name, 0));
					return Database.updateBank(b);
				}
			}
		}
		return false;	
	}
}

