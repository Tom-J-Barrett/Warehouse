import java.util.Scanner;

import Database.Database;
import components.LogOnComponent;
import control.AddNewProduct;
import control.GenerateReport;
import control.LogIn;
import control.ReceiveInventory;
import control.Ship;
import control.Scrap;

public class IntelligentWarehouse {

	public static void main(String[] args) {
		LogOnComponent aLogOnComponent=new LogOnComponent();
		//boolean run=true;
		//while(run){
			//Database db = new Database();
			/*System.out.println("What would you like to do?:");
			System.out.println("0.GUI Under Construction");
			System.out.println("1.Generate Report");
			System.out.println("2.Ship");
			System.out.println("3.Login");  // <--- should be the first option no ?
			System.out.println("4.Add New Product");
			System.out.println("5.Add Item to Inventory");
			System.out.println("6.Scrap");
			System.out.println("7.Exit");
			
			Scanner in=new Scanner(System.in);
			int choice=Integer.parseInt(in.nextLine());
			
			switch(choice){
			case 0:LogOnComponent aLogOnComponent=new LogOnComponent();break;
			case 1:GenerateReport report=new GenerateReport();break;
			case 2:Ship ship=new Ship();break;
			case 3:LogIn login= new LogIn("cli");break;
			case 4:AddNewProduct product=new AddNewProduct(db);break;
			case 5:ReceiveInventory recInv=new ReceiveInventory(db);break;
			case 6:Scrap scrap=new Scrap(db);break;
			case 7:run=false;break;
			}
			
			in.close();*/
		//2}
	}
}
