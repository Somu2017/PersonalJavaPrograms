
public class curFormatTester 
{
	static String curFormat(double amt)	// Formats for currency
	{
		String output = "";
		int decimal = (int)Math.round(amt*100);
		decimal = (int)decimal%100;
			
		int amtClone = (int)amt;
		
		if(amtClone/1000 > 0)
		{
			output = ","+(amtClone%1000);
			if(amtClone%1000 == 0)
				output+="00";
			amtClone/=1000;
		}
		
		while(amtClone/100 > 0)
		{
			if(amtClone%100 == 0)
				output = ",00"+output;
			else if((amtClone%100)/10 == 0)
				output = ",0"+(amtClone%100)+output;
			else
				output = ","+(amtClone%100)+output;
			amtClone/=100;
		}
		
		output = amtClone+output;
		if(decimal/10 == 0)
			output = output+".0"+decimal;
		else
			output = output+"."+decimal;
		return "Rs."+output;
	}
	
	public static void main(String args[])
	{
		System.out.println(curFormat(123.0));
	}
}
