import java.io.*;
public class electricBill 
{
	public static void main(String args[]) throws IOException
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Please enter the number of Units : ");
		int units = Integer.parseInt(in.readLine());
		
		float amount = 0;
		amount = bill(units);
		
		System.out.println("Your bill is : "+amount);
	}
	
	static float bill(int units)		// Uses Cumulative Sum to calculate Bill
	{
		float amount=0;
		
		if(units>300)
		{
			amount += (units-300)*8.92;
			units=300;
		}
		
		if(units>150)
		{
			amount += (units-150)*7.33;
			units=150;
		}
		
		if(units>100)
		{
			amount += (units-100)*7.16;
			units=100;
		}
		
		if(units>60)
		{
			amount += (units-60)*6.41;
			units=60;
		}
		
		if(units>25)
		{
			amount += (units-25)*5.40;
			units=25;
		}
		
		amount += units*4.89;
		
		return amount;
	}
}
