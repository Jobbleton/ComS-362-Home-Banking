
public class Asset
{
	double value;
	String name;
	
	public Asset()
	{
		name = "";
		value = 0;
	}
	
	public Asset(String name, double value)
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
