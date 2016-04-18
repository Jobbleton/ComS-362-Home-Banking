
public class Fund
{
	double value;
	public Fund()
	{
		value = 0;
	}
	
	public Fund(int value)
	{
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
}
