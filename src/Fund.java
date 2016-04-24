
public class Fund
{
	String name;
	double value;
	
	public Fund()
	{
		value = 0;
	}
	
	public Fund(String name, double value)
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
