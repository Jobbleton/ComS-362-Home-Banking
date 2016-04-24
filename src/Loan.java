
public class Loan
{
	double value;
	String name;
	
	public Loan()
	{
		name = "";
		value = 0;
	}
	
	public Loan(String name, double value)
	{
		this.name = name;
		this.value = value;
	}
	
	public boolean setValue(double value)
	{
		this.value = value;
		return true;
	}
	
	public double getValue()
	{
		return value;
	}
	
	public String getName()
	{
		return name;
	}
}
