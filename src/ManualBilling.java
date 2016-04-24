
public class ManualBilling extends Billing
{
	double amountToBill;
	int timesBilled;
	
	public ManualBilling()
	{
		name = "";
		value = 0;
		amountToBill = 0;
		timesBilled = 0;
	}
	
	public ManualBilling(String name, double amountToBill)
	{
		this.name = name;
		value = 0;
		this.amountToBill = amountToBill;
		timesBilled = 0;
	}
	
	public void setTimesBilled(int amt)
	{
		timesBilled = amt;
	}
	
	public boolean bill()
	{
		value += amountToBill;
		timesBilled++;
		return true;
	}
	
	public int getTimesBilled()
	{
		return timesBilled;
	}
}
