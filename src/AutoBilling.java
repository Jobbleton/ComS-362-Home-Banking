
public class AutoBilling extends Billing
{
	double amountToBill;
	double dailyFrequency;
	int timesBilled;
	
	public AutoBilling()
	{
		name = "";
		value = 0;
		dailyFrequency = 0;
		amountToBill = 0;
	}
	
	public AutoBilling(String name, double amountToBill, double frequency)
	{
		this.name = name;
		value = 0;
		this.dailyFrequency = frequency;
		
	}
	
	public boolean bill()
	{
		value += amountToBill;
		timesBilled++;
		return true;
	}
	
	public double getFrequency()
	{
		return dailyFrequency;
	}
}
