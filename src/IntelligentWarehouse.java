import Control.Ship;
import Control.LogIn;

import java.util.Scanner;

import Control.GenerateReport;

public class IntelligentWarehouse {

	public static void main(String[] args) {
		boolean run=true;
		//while(run){
			System.out.println("What would you like to do?:");
			System.out.println("1.Generate Report");
			System.out.println("2.Ship");
			System.out.println("3.Login");
			System.out.println("4.Exit");
			
			Scanner in=new Scanner(System.in);
			int choice=Integer.parseInt(in.nextLine());
			
			switch(choice){
			case 1:GenerateReport report=new GenerateReport();break;
			case 2:Ship ship=new Ship();break;
			case 3:LogIn login= new LogIn();break;
			case 4:run=false;break;
			}
			
			in.close();
		//2}
	}
}
