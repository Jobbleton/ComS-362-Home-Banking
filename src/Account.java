import java.util.ArrayList;

public class Account
{
	Person accountOwner;
	String ID;
	ArrayList<Child> dependents;
	double value;
	ArrayList<Fund> funds;
	ArrayList<Billing> bills;
	ArrayList<AutoBilling> autoBills;
	ArrayList<ManualBilling> manBills;
	ArrayList<Loan> loans;
	ArrayList<Asset> assets;
	double transferAmount;
	
	public Account()
	{
		accountOwner = null;
		dependents = new ArrayList<Child>();
		value = 0;
		funds = new ArrayList<Fund>();
		bills = new ArrayList<Billing>();
		autoBills = new ArrayList<AutoBilling>();
		manBills = new ArrayList<ManualBilling>();
		loans = new ArrayList<Loan>();
		assets = new ArrayList<Asset>();
		transferAmount = 0;
	}
	
	public Account(Person owner, String ID)
	{
		accountOwner = owner;
		this.ID = ID;
		dependents = new ArrayList<Child>();
		value = 0;
		funds = new ArrayList<Fund>();
		bills = new ArrayList<Billing>();
		autoBills = new ArrayList<AutoBilling>();
		manBills = new ArrayList<ManualBilling>();
		loans = new ArrayList<Loan>();
		assets = new ArrayList<Asset>();
		transferAmount = 0;
	}
	
	public Person getOwner()
	{
		return accountOwner;
	}
	
	public String getID()
	{
		return ID;
	}
	
	public ArrayList<Child> getDependents()
	{
		return dependents;
	}
	
	public boolean addDependent(Child c)
	{	
		return dependents.add(c);
	}
	
	public boolean removeDependent(Child c)
	{
		for(Child ch : dependents)
		{
			if(ch.getIdentifier().equals(c.getIdentifier()))
			{
				dependents.remove(ch);
				return true;
			}
		}
		return false;
	}
	
	public double getValue()
	{
		double toReturn = (getFundsValue() + transferAmount);
		for(Billing b : bills)
		{
			toReturn -= b.getValue();
		}
		for(AutoBilling b : autoBills)
		{
			toReturn -= b.getValue();
		}
		for(ManualBilling b : manBills)
		{
			toReturn -= b.getValue();
		}
		for(Loan l : loans)
		{
			toReturn -= l.getValue();
		}
		for(Asset a : assets)
		{
			toReturn += a.getValue();
		}
		for(Fund f : funds)
		{
			toReturn += f.getValue();
		}
		value = toReturn;
		return toReturn;
	}
	
	public double getFundsValue()
	{
		double toReturn = 0;
		for(Fund f : funds)
		{
			toReturn += f.getValue();
		}
		return toReturn;
	}
	
	public boolean addBill(Billing b)
	{
		return bills.add(b);
	}
	
	public boolean removeBill(Billing b)
	{
		for(Billing bi : bills)
		{
			if(b.getName().equals(bi.getName()))
			{
				bills.remove(b);
				return true;
			}
		}
		return false;
	}
	
	public boolean addAutoBill(AutoBilling b)
	{
		return bills.add(b);
	}
	
	public boolean removeAutoBill(AutoBilling b)
	{
		for(AutoBilling bi : autoBills)
		{
			if(b.getName().equals(bi.getName()))
			{
				autoBills.remove(b);
				return true;
			}
		}
		return false;
	}
	
	public boolean addManBill(ManualBilling b)
	{
		return bills.add(b);
	}
	
	public boolean removeManBill(ManualBilling b)
	{
		for(ManualBilling bi : manBills)
		{
			if(b.getName().equals(bi.getName()))
			{
				manBills.remove(b);
				return true;
			}
		}
		return false;
	}
	
	public double getLoansValue()
	{
		double toReturn = 0;
		for(Loan l : loans)
		{
			toReturn += l.getValue();
		}
		return toReturn;
	}
	
	public boolean addLoan(Loan l)
	{
		return loans.add(l);
	}
	
	public boolean removeLoan(Loan l)
	{
		for(Loan lo : loans)
		{
			if(lo.getName().equals(l.getName()))
			{
				return loans.remove(lo);
			}
		}
		return false;
	}

	public boolean addFund(Fund f)
	{
		return funds.add(f);
	}

	public boolean removeFund(Fund f)
	{
		for(Fund fu : funds)
		{
			if(f.getName().equals(fu.getName()))
			{
				return funds.remove(fu);
			}
		}
		return false;
	}
	
	public boolean addAsset(Asset a)
	{
		return assets.add(a);
	}
	
	public boolean removeAsset(Asset a)
	{
		for(Asset as : assets)
		{
			if(a.getName().equals(as.getName()))
			{
				return assets.remove(as);
			}
		}
		return false;
	}
	
	public ArrayList<Fund> getFunds()
	{
		return funds;
	}
	
	public ArrayList<Billing> getBills()
	{
		return bills;
	}
	
	public ArrayList<AutoBilling> getAutoBills()
	{
		return autoBills;
	}
	
	public ArrayList<ManualBilling> getManBills()
	{
		return manBills;
	}
	
	public ArrayList<Loan> getLoans()
	{
		return loans;
	}
	
	public ArrayList<Asset> getAssets()
	{
		return assets;
	}
	
	public boolean updateTransferAmount(double amt)
	{
		transferAmount += amt;
		return true;
	}
	
	public double getTransferAmount()
	{
		return transferAmount;
	}

	public String getActivity()
	{
		String toReturn = "";
		toReturn += "Beginning account activity report..." + "\n";
		//TODO
		for(Child c : getDependents())
		{
			toReturn += "Dependent: " + c.getFullName() + "\n";
		}
		for(Billing bi : getBills())
		{
			toReturn += "Bill: " + bi.getName() + " " + bi.getValue() + "\n";
		}
		for(AutoBilling bi : getAutoBills())
		{
			toReturn += "Auto Bill: " + bi.getName() + " " + bi.getValue() + "\n";
		}
		for(ManualBilling bi : getManBills())
		{
			toReturn += "Manual Bill: " + bi.getName() + " " + bi.getValue() + "\n";
		}
		for(Loan lo : getLoans())
		{
			toReturn += "Loan: " + lo.getName() + " " + lo.getValue() + "\n";
		}
		for(Asset as : getAssets())
		{
			toReturn += "Asset: " + as.getName() + " " + as.getValue() + "\n";
		}
		for(Fund fu : getFunds())
		{
			toReturn += "Fund: " + fu.getName() + " " + fu.getValue() + "\n";
		}
		return toReturn;
	}
	
	public void setValue(double value2)
	{
		value = value2;
	}
}
