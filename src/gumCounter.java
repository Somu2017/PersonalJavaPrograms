import java.io.*;
public class gumCounter 
{	
	public static void main(String args[]) throws IOException
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		double cost = 6.56D;
		double totalCost = 0;
		int totalGums = 0;
		System.out.print("Enter number of gums per day in the first week : ");
		int gums = Integer.parseInt(in.readLine());
		System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n");
		
		for(int months=1;gums>0;months++)
		{
			double monthlyCost = 0;
			for(int weeks=1;((weeks<=4)&&(gums>0));weeks++,gums--)
			{
				double weeklyCost = gums*cost*7;
				totalGums+=gums*7;
				System.out.println("Month : "+months+"\tWeek : "+weeks);
				System.out.println("Number of Gums per day : "+gums);
				System.out.println("Total Gums : "+totalGums);
				System.out.println("Daily Cost : Rs."+gums*cost);
				System.out.println("Weekly Cost : Rs."+weeklyCost);
				monthlyCost += weeklyCost;
				System.out.println("***********************************");
			}
			System.out.println("Monthly Cost : Rs."+monthlyCost);
			System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n");
			totalCost += monthlyCost;
		}
		System.out.println("Total Cost : Rs."+totalCost);
		System.out.println("Total Gums : "+totalGums);
		int totalPacks = (totalGums/9) + ((totalGums%9)>0?1:0);
		System.out.println("Total Packs : "+totalPacks);
		int emergencyPacks = 2;
		System.out.println("Emergency Packs : "+emergencyPacks);
		totalPacks += emergencyPacks;
		System.out.println("Emergency Packs + Total Packs : "+totalPacks);
		System.out.println("Adjusted Total Cost : Rs."+(totalPacks*59.0));
	}
}
