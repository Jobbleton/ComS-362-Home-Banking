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
		return dependents.remove(c);
	}
	
	public void setValue(double amt)
	{
		value = amt;
	}
	
	public double getValue()
	{
		return getFundsValue();
		//+ loans + bills
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
		return bills.remove(b);
	}
	
	public boolean addAutoBill(AutoBilling b)
	{
		return bills.add(b);
	}
	
	public boolean removeAutoBill(AutoBilling b)
	{
		return bills.remove(b);
	}
	
	public boolean addManBill(ManualBilling b)
	{
		return bills.add(b);
	}
	
	public boolean removeManBill(ManualBilling b)
	{
		return bills.remove(b);
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
		return loans.remove(l);
	}

	public boolean addFund(Fund f)
	{
		return funds.add(f);
	}

	public boolean removeFund(Fund f)
	{
		return assets.remove(f);
	}
	
	public boolean addAsset(Asset a)
	{
		return assets.add(a);
	}
	
	public boolean removeAsset(Asset a)
	{
		return assets.remove(a);
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
}
