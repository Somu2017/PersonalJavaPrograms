import java.io.*;
public class carPlan 
{
	/*	Constants */
	final static double incomePerCar = 20000.00;//	Income per month.
	final static double inflation = 0.02;		//	Rate of Inflation Per year.
	
	final static int carRetirePeriod = 5;		// 	Time (in years) after which a car is no longer usable.
	
	/*	Variables to be inputed */
	static int startMonth;						//	Starting Month.
	static int startYear;						//	Starting Year.
	static int totalYears;						//	Total Years for calculation.
	static int investmentYears;					//	No of years a recurring investment is made.
	
	/*	Variables to calculate time periods */
	static String monthName[] = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
	
	/*	Supporting Variables */
	static double price = 800000.00;			//	Base price in first year of purchase. Recurring Yearly.
	static int month = 0;						// 	Records no of months elapsed.
	static int totalMonths = 0;					//	Total no of months in time period.
	static double bankBalance = 0;				// 	Account Balance.
	static double originalBalance = 0;			// 	Bank Balance before transaction.
	static double income = 0;					// 	Total Income per month.
	static int carsBought = 0;					//	Number of Cars bought.
	static int carsTotal = 0;					// 	Total Cars bought.
	static boolean newInvestment = false;		//	New (recurring) investment for the year.
	static double serviceCost = 15000;			//	Servicing Cost for each Usable Car.
	static double totalServiceCost = 0;			//	Total Servicing for all cars.
	static int carsRequiringServicing = 0;		//	Number of Cars that need servicing.
	
	static int carAge[] = new int[1000];		//	Maximum carsTotal MUST be less than 1000.
	static int carsJunk = 0;					//	Cars that are too old to drive.
	
	static void display()
	{
		System.out.println("Month : "+month+" (Year: "+(((month-1)/12)+1)+") ["+monthName[(month-1)%12]+" "+(((month-1)/12)+startYear)+"] ");
		if(newInvestment)
			System.out.print("Recurring Yearly Investment : "+curFormat(price)+" ");
		System.out.print("Bank Balance : "+curFormat(originalBalance)+" ");
		System.out.print("New Balance : "+curFormat(bankBalance)+" ");
		System.out.println("Income : "+curFormat(income));
		
		System.out.print("Cars Bought : "+carsBought+" ");
		System.out.print("Total Cars : "+carsTotal+" ");
		System.out.print("Junk Cars : "+carsJunk+" ");
		System.out.println("Usable Cars : "+(carsTotal-carsJunk));
		if(totalServiceCost>0)
		{
			System.out.print("Cars Requiring Servicing : "+carsRequiringServicing+" ");
			System.out.println("Total Service Cost : "+curFormat(totalServiceCost));
		}
		System.out.println();
	}
	
	
	
	public static void main(String args[]) throws IOException
	{	
		/*	Input Section */
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Enter the Starting Year : ");
		startYear = Integer.parseInt(in.readLine());
		System.out.print("Enter the Starting Month : ");
		startMonth = Integer.parseInt(in.readLine());
		System.out.print("Enter number of years for calculation : ");
		totalYears = Integer.parseInt(in.readLine());
		System.out.print("Enter number of years for recurring investment : ");
		investmentYears = Integer.parseInt(in.readLine());
		
		/*	Initializations */
		totalMonths = totalYears * 12;
		for(int i=0; i<carAge.length; i++)
			carAge[i] = 0;
		
		/*	Calculation Starts */
		System.out.println("\n*** Period of Calculation : From "+monthName[startMonth-1]+", "+startYear+" Till "+monthName[startMonth-1]+", "+(startYear+totalYears)+" ***\n");
		
		while(month < totalMonths)
		{
			totalServiceCost = carsRequiringServicing = 0;
			int oldCarsJunk = carsJunk;
			carsJunk = 0;			
			for(int i=0;i<carsTotal;i++)
			{
				carAge[i]++;
				if(carAge[i]>=carRetirePeriod*12)
					carsJunk++;
			}
			bankBalance += ((carsJunk - oldCarsJunk)*100000);		//	Adding resell values of old (Junk) cars. Assuming unit resell value of 1,00,000.
			month++;
			
			if(((month-1)%12 == 0) && (((month-1)/12)) != 0)		// 	Modelling Inflation. Zero addition on First Year.
			{
				price += inflation * price;
				carsRequiringServicing = (carsTotal-carsJunk);
				totalServiceCost = serviceCost * carsRequiringServicing;
				bankBalance -= totalServiceCost;					// 	Servicing cost of all cars.
			}
			
			if(((month-1)%12 == 0) && (((month-1)/12))<investmentYears)
			{
				newInvestment = true;
				bankBalance += price;
			}
			else
				newInvestment = false;
			
			bankBalance += income;
			originalBalance = bankBalance;
			
			if(bankBalance >= price || totalServiceCost > 0)
			{
				carsBought = 0;
				carsBought = (int)(bankBalance/price);
				carsTotal += carsBought;
				
				bankBalance = bankBalance%price;
				
				income = incomePerCar * (carsTotal-carsJunk);
				
				display();
			}
		}
	}
	
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
}
