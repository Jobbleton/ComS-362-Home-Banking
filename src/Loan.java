
public class Loan
{
	double value;
	String name;
	
	public Loan()
	{
		name = "";
		value = 0;
	}
	
	public Loan(String name, int value)
	{
		this.name = name;
		this.value = value;
	}
	
	public double getValue()
	{
		return value;
	}
}
