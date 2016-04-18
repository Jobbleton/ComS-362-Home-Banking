import java.util.ArrayList;

public class Account
{
	Person accountOwner;
	String ID;
	ArrayList<Child> dependents;
	double value;
	ArrayList<Fund> funds;
	ArrayList<Billing> bills;
	ArrayList<Loan> loans;
	
	public Account()
	{
		accountOwner = null;
		dependents = new ArrayList<Child>();
		value = 0;
		funds = new ArrayList<Fund>();
	}
	
	public Account(Person owner, String ID)
	{
		accountOwner = owner;
		this.ID = ID;
		dependents = new ArrayList<Child>();
		value = 0;
		funds = new ArrayList<Fund>();
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
}
