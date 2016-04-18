
public class Billing
{
	double value;
	String name;
	
	public Billing()
	{
		name = "";
		value = 0;
	}
	
	public Billing(String name, double value)
	{
		this.name = name;
		this.value = value;
	}
	
	public double getValue()
	{
		return value;
	}
}
