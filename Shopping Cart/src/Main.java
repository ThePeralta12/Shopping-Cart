import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

/**
* @author 17-1819
* Date 16th April 2018	
* Fundamental of Object Oriented Programming - Skills Demo 3
* 
*/                                                  

public class Main 
{
	/**
	 * The Complex Classes in the Main Class
	 */
	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static Scanner input = new Scanner(System.in);
	static DecimalFormat currency = new DecimalFormat("0.00");
	
	private static ArrayList<Product> inventory = new ArrayList<Product>();
	private static ArrayList<Product> cart = new ArrayList<Product>();
	private static ArrayList<Product> totalSales = new ArrayList<Product>();
	/**
	 *Variable/Properties of the Main class 
	 */
	private static int centralId=1001; // Use to for the ID of the items
	private static double transactionCost = 0; // Global variable to hold the cost of overall price.
	private static double stockCost = 0; 
	private static double salesCost =0;

	private static String[] foodName = {"HamBurger","Chips","Hotdog", "Sandwich","Pizza","Fried Chicken","Chicken Nuggets",
	"Chicken Wings","Mix Kebab","Donor Kebab","Chicken Kebab","Taco","Cheeseburger","Fish And Chips","Steak"};
	
	private static String[] foodBrand = {"McDonalds","KFC","Burger King","Subway","Captian America","Eddie Rockets","Five Guys",
	"Abrakebabra","SuperMac","Eddie Rockets"};
	
	private static String[] drinkName = {"Coca-Cola","Still Water","Sparkling Water ", "Club Orange", "7Up", "Club Lemon", "Pepsi", "Corona", "Guinness",
	"Carlsberg", "Bulmers","Coors Light", "HopHouse 13"};
	
	private static String[] drinktype = {"Bottle","500ML","Multi-Pack","Can","2L"};
	
	private static String[] candyName = {"Dairy Milk", "Twirl", "Dairy Milk Caramel","Wispa","Flake", "Snickers","Maltesers","Skittles",
	"Mars","Kit-Kat","Kinder Bueno","Yorkie"};
	
	private static String[] type = {"Bar","Family-Size Bar", "Multi-Pack"};

	/**
	 *  This is the start of the program.
	 * @param args
	 */
	public static void main(String[] args) 
	{
		File f = new File("product.ser");
		if(f.exists())
		{
			try {
				importDatabase(); 
			} catch (Exception e) {
			
				System.out.println("Error reading file");
			}
		}
		centralId=1001+ inventory.size();
		menu();

	}
	/**
	 * The Main Menu is to direct the User to their desired menu.
	 */
	private	 static void menu() 
	{
		System.out.println("\t \t WELCOME TO FOOD STORE - ONLINE ");
		System.out.println("***************************************************");
		System.out.println("\tPress 1 to Start Shopping!");
		System.out.println("\tPress 2 to Product Menu");
		System.out.println("");
		System.out.println("\tPress 5 to Save and Quit");
		System.out.println("***************************************************");
		System.out.println();
		System.out.println();
		System.out.println("(Press 99 to Enter Admin)");
		System.out.println();
		String choice = input.next();
		
		switch(choice)
		{
		case "1":
		{
			salesMenu();
			break;
		}
		case"2":
		{
			productMenu();
			break;
		}
		case"5":
		{
			try {
				exportDatabase();
			} catch (Exception e) {
				
				System.out.println("Error, Please Try Again");
			}
			
			System.out.println("Data Saved, System shutting down");
			System.exit(0);
		}
		case"99":
		{
			password();
			break;
		}
		default:
		{
			System.out.println("Error, Please select 1,2 or 5");
			break;
		}
		
		}
		menu();
		}
	/**
	 * Password is necessary if you wish to enter the Admin menu
	 */
	private static void password() 
	{
		System.out.println();
        System.out.println("Enter the Password for Admin \t \t (123)");  
        System.out.println();
          
        int pass = input.nextInt();  
        System.out.println();
          
        if(pass==(123)) //the password  
        {  
            adminMenu();  // this direct to the adminMenu() menu.
        }  
        else  
        {  
            System.out.println("Incorrect, Please try again");  
              
        } 
		
	}
	/**
	 * This method stores the existing variables when its executed. 
	 * @throws Exception - exportDatabase
	 */
	private static void exportDatabase() throws Exception 
	{
		FileOutputStream exportFile = new FileOutputStream("product.ser");
		ObjectOutputStream write = new ObjectOutputStream(exportFile);
		write.writeObject(inventory);
		write.writeObject(salesCost);
		write.writeObject(totalSales);
		write.close();
		
		
	}
	/**
	 * This method reuploads the stored variables to the new running program.
	 * @throws Exception - importDatabase
	 */
	private static void importDatabase() throws Exception
	{
		FileInputStream importFile = new FileInputStream("product.ser");
		ObjectInputStream read = new ObjectInputStream(importFile);
		inventory = (ArrayList<Product>)read.readObject();
		totalSales = (ArrayList<Product>)read.readObject();
		salesCost = (double)read.readDouble();
		read.close();	
	}
	/**
	 * The Admin menu lets you choose to find Total Sales, Stock Value, Average Sales,
	 * and it also allows the user to clear the variables in the inventory ArrayList. 
	 */
	private static void adminMenu() 
	{
		System.out.println("============================================");
		System.out.println("Welcome to Admin Menu");
		System.out.println("");
		System.out.println("\t Press 1 for Total Sales");
		System.out.println("\t Press 2 for Stock Value");
		System.out.println("\t Press 3 to Clear Database");
		System.out.println();
		System.out.println("\t Press 4 to Return to Main Menu");
		System.out.println("===========================================");
		System.out.println();
		
		String choice = input.next();
		
		switch(choice)
		{
		case"1":
		{
			totalSales();
			break;
		}
		case"2":
		{
			stockValue();
			break;
		}
		
		case"3":
		{
			inventory.clear();
			try {
				exportDatabase();
			} catch (Exception e) 
			{
			
				System.out.println("Error, Please try again");
			}
			System.out.println("Database Cleared!");
			System.out.println();
			break;
		}
		case"4":
		{
			menu();
			break;
		}
		default:
		{
			System.out.println("ERROR, Please select 1-5");
			break;
		}
		}
		
		adminMenu();
		
	}
	/**
	 * This method will print the variables stored in the the Inventory ArrayList
	 */
	private static void stockValue()
	{
		for(Product c: inventory)
		{
			System.out.println(c.getProductId() + "\t" + c.getProductName() + "\t€" + currency.format(c.getPrice()));
			stockCost += c.getPrice();
		}
		System.out.println();
		System.out.println("Stock Value: \t€" + currency.format(stockCost));
		System.out.println();
		

		
	}
	/**
	 * This method will print the variables stored in the the totalSales ArrayList
	 */
	private static void totalSales() 
	{
		for(Product c: totalSales)
		{
			System.out.println(c.getProductId() + "\t" + c.getProductName() + "\t€" + currency.format(c.getPrice()));
			
			
		}
		System.out.println();
		System.out.println("Sales Total: \t€" + currency.format(salesCost));
		System.out.println();
		
	}
	/**
	 * The Product menu lets you choose to Create Products, Print Product, Search for Product, Edit a Product,
	 * and Delete a Product.
	 */
	private static void productMenu() 
	{
		System.out.println("Welcome to the Product Menu!");
		System.out.println();
		System.out.println("Press 1 to Create Product");
		System.out.println("Press 2 to Create 5 random Products");
		System.out.println("Press 3 to Print Products");
		System.out.println("Press 4 to Search for Product");
		System.out.println("Press 5 to Edit a Product");
		System.out.println("Press 6 to Delete a Product");
		System.out.println();
		System.out.println("Press X to Return to Main Menu");
		String choice = input.next();
		
		switch(choice) 
		{
		case"1":
		{
			try {
				createProduct();
			} catch (Exception e) {
			
				System.out.println("Error, Cannot Find");
			}
			break;
		}
		case"2":
		{
			createProducts();
			break;
		}
		case"3":
		{
			printProduct();
			break;
		}
		case"4":
		{
			searchProduct();
			break;
		}
		
		case"5":
		{
			try {
				editProduct();
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
			break;
		}
		case"6":
		{
			deleteProduct();
			break;
		}
		case"X": case "x":
		{
			menu();
			break;
		}
		default:
		{
			System.out.println("Error, Please choose between 1-5");
			break;
		}
		}
		productMenu();
	}
	/**
	 * This method lets you delete an item from the inventory ArrayList
	 */
	private static void deleteProduct() 
	{
		for(Product f: inventory)
		{
			System.out.println(f.getProductId() + "\t" + f.getProductName() + "\t \t€" + currency.format(f.getPrice()));
			System.out.println();
			
		}
		System.out.println("Please choose a Product by ID to Delete");
		int chosenProduct = input.nextInt();
		for(Product p: inventory)
		{
			if(p.getProductId()==chosenProduct)
			{
				inventory.remove(p);
				System.out.println("Product Removed!");
				System.out.println();
				productMenu();
			}
		}
		
	}
	/**
	 * This method lets you edit an item from the iventory ArrayList
	 * @throws Exception
	 */
	private static void editProduct() throws Exception 
	{
		for(Product f: inventory)
		{
			System.out.println(f.getProductId() + "\t" + f.getProductName() + "\t \t€" + currency.format(f.getPrice()));
			System.out.println();
			
		}
		System.out.println("Please choose a Product by ID to Edit");
		int chosenProduct = input.nextInt();
		for(Product p: inventory)
		{
			if(chosenProduct==p.getProductId())
			{
				System.out.println("1\t Edit Name");
				System.out.println("2\t Edit Quantity");
				System.out.println("3\t Edit Price");
				System.out.println("4\t Edit Calories");
				
				String choice = input.next();
				
				switch(choice)
				{
				case"1":
				{
					System.out.println("Enter new name to replace " + p.getProductName());
					p.setProductName(reader.readLine());
					System.out.println("Successful!");
					System.out.println();
					break;
				}
				case"2":
				{
					System.out.println("Enter new Quantity to replace " + p.getQuantity());
					p.setQuantity(input.nextInt());
					System.out.println("Sucessfull!");
					System.out.println();
					break;
				}
				case"3":
				{
					System.out.println("Enter new Price to replace €" + currency.format(p.getPrice()));
					p.setPrice(input.nextDouble());
					System.out.println("Successful!");
					System.out.println();
					break;
				}
				case"4":
				{
					System.out.println("Enter new Calories to replace " + p.getCalories());
					p.setCalories(input.nextInt());
					System.out.println("Successful!");
					System.out.println();
					break;
				}
				
				}
				productMenu();
				
			}
		}
		
	}
	/**
	 * This method gives you the ability to search the items in the inventory ArrayList 
	 */
	private static void searchProduct() 
	{
	
		for(Product f: inventory)
		{
			System.out.println(f.getProductId() + "\t" + f.getProductName() + "\t \t€" + currency.format(f.getPrice()));
			System.out.println();
			
		}
		System.out.println("Please choose a Product by ID to view more Details");
		int chosenProduct = input.nextInt();
		for(Product p: inventory)
		{
			if(chosenProduct==p.getProductId()) 
			{	
				if(p.getType().equals("candy"))
				{
					Candy c = (Candy)p;
					c.printProduct();
					System.out.println("Candy Type: " + c.getCandyType());
					System.out.println();
				}
				else if(p.getType().equals("food"))
				{
					Food f = (Food)p;
					f.printProduct();
					System.out.println("Brand: " + f.getBrand());
					System.out.println();
				}
				else if(p.getType().equals("drink"))
				{
					Drinks d =(Drinks)p;
					d.printProduct();
					System.out.println("Drink Type: " + d.getDrinktype());
					System.out.println();
				}
				
			}
		}

	}
	/**
	 *  This method prints out all the item in the iventory Arraylist
	 */
	private static void printProduct()
	{
		for(Product f: inventory)
		{
			System.out.println(f.getProductId() + "\t" + f.getProductName() + "\t \t€" + currency.format(f.getPrice()));
			System.out.println();

		}
		
	}
	/**
	 * This method is the navigation for the creation of a Product
	 * @throws Exception
	 */
	private static void createProduct() throws Exception 
	{
		
		System.out.println(""); 
		System.out.println("Press 1 for Food");
		System.out.println("Press 2 for Drink");
		System.out.println("Press 3 for Candy");
		System.out.println("");
		System.out.println("Press 5 to Return to Product Menu");
		
		String choice = input.next();
		switch(choice)
		{
		case"1":
		{
			createFood();
			break;
			
		}
		case"2":
		{
			createDrink();
			break;
		}
		case"3":
		{
			createCandy();
			break;
			
		}
		case"5":
		{
			productMenu();
			break;
		}
		default:
		{
			System.out.println("Please choose a Product");
			break;
		}
		}
		createProduct();
	}
	/**
	 * This method creates a Candy item(Manually)
	 * @throws Exception
	 */
	private static void createCandy() throws Exception 
	{
		Candy c = new Candy();
		
		c.setProductId(centralId);
		centralId++;
		
		System.out.println("Enter Name of Candy");
		c.setProductName(reader.readLine());
		System.out.println("Enter a desired Price");
		c.setPrice(input.nextDouble());
		System.out.println("Enter a set quantity");
		c.setQuantity(input.nextInt());
		
		System.out.println();
		System.out.println("Enter the Candy Type");
		c.setCandyType(reader.readLine());
		
		System.out.println("Enter Calories");
		c.setCalories(input.nextInt());
	
		
		inventory.add(c);
		System.out.println("Successfully Added!");
		System.out.println();
		productMenu();
	}
	/**
	 * This method creates a Food item(Manually)
	 * @throws Exception
	 */
	private static void createFood() throws Exception
	{
		Food f = new Food();
		f.setProductId(centralId);
		centralId++;
		
		System.out.println("Enter Name of Food");
		f.setProductName(reader.readLine());
		System.out.println("Enter a desired Price");
		f.setPrice(input.nextDouble());
		System.out.println("Enter a set quantity");
		f.setQuantity(input.nextInt());
		
		System.out.println("Enter Calories");
		f.setCalories(input.nextInt());
		
		System.out.println("Enter Name of Brand");
		f.setBrand(reader.readLine());
		f.setType("food");
		
		
		inventory.add(f);
		System.out.println("Successfully Added!");
		System.out.println();
		productMenu();
		
	}
	/**
	 * This method creates a Drink(Manually)
	 * @throws Exception
	 */
	private static void createDrink() throws Exception
	{
		Drinks d = new Drinks();
		
		d.setProductId(centralId);
		centralId++;
		
		System.out.println("Enter Name of Drink");
		d.setProductName(reader.readLine());
		System.out.println("Enter a desired Price");
		d.setPrice(input.nextDouble());
		System.out.println("Enter a set quantity");
		d.setQuantity(input.nextInt());
		
		System.out.println();
		System.out.println("What type of Drink does it hold? (1L, MultiPack, 500ML, etc)");
		d.setDrinktype(reader.readLine());
		
		
		System.out.println("Enter Calories");
		d.setCalories(input.nextInt());
		
		inventory.add(d);
		System.out.println("Successfully Added!");
		System.out.println();
		productMenu();
	}
	/**
	 * This method is the navigation to generate a Product
	 */
	private static void createProducts() 
	{
		System.out.println(""); 
		System.out.println("******************************************");
		System.out.println("\t Press 1 for Food");
		System.out.println("\t Press 2 for Drink");
		System.out.println("\t Press 3 for Candy");
		System.out.println("");
		System.out.println("\t Press 5 to Return to Product Menu");
		System.out.println("******************************************");
		
		String choice = input.next();
		switch(choice)
		{
		case"1":
		{
			createFoods(5);
			break;
			
		}
		case"2":
		{
			createDrinks(5);
			break;
		}
		case"3":
		{
			createCandys(5);
			break;
			
		}
		case"5":
		{
			productMenu();
			break;
		}
		default:
		{
			System.out.println("Please choose a Product");
			break;
		}
		}
		productMenu();
	}
	/**
	 * This method will auto generate 5 Candy
	 * @param numCandy
	 */
	private static void createCandys(int numCandy)
	{
		for(int i=0; i< numCandy; i++)
		{
			 Candy c = new Candy();
			 c.setProductId(centralId);
			 centralId++;
			 
			 String name = candyName[(int) (Math.random()*candyName.length)];
			 
			 c.setProductName(name);
			 
			c.setPrice((double) (Math.random()*3)+1);
			c.setQuantity((int) (Math.random()*30)+1);
			c.setCalories((int) (Math.random()*1000)+1);
			
			String pack = type[(int) (Math.random()*type.length)];
			
			c.setCandyType(pack);
			c.setType("candy");
			
			inventory.add(c);
		}

	}
	/**
	 * This method will auto generate 5 Drink
	 * @param numDrink
	 */
	private static void createDrinks(int numDrink) 
	{
		for(int i=0; i <numDrink; i++)
		{
			Drinks d = new Drinks();
			d.setProductId(centralId);
			centralId++;
			
			String name = drinkName[(int) (Math.random()*drinkName.length)];
			
			d.setProductName(name);
			
			d.setPrice((double) (Math.random()*10)+1);
			d.setQuantity((int) (Math.random()*30)+1);
			d.setCalories((int) (Math.random()*1000)+1);
			
			String drinkflavour = drinktype[(int)(Math.random()*drinktype.length)];
			
			d.setDrinktype(drinkflavour);
			d.setType("drink");
			
			inventory.add(d);
			
			
		}
		System.out.println("Successfully added 5 Products");
		System.out.println();
	}
	/**
	 * This method will auto generate 5 Food
	 * @param numFood
	 */
	private static void createFoods(int numFood) 
	{
		for(int i=0; i<numFood; i++)
		{
			Food f = new Food();
			f.setProductId(centralId);
			centralId++;
			
			String name	 = foodName[(int)(Math.random()*foodName.length)];
			
			f.setProductName(name);
			
			f.setPrice((double)(Math.random()*10)+1);
			f.setQuantity((int)(Math.random()*30)+1);
			f.setCalories((int)(Math.random()*100)+1);
			
			String brand = foodBrand[(int) (Math.random()*foodBrand.length)]; 
			
			f.setBrand(brand);
			f.setType("food");
			
			inventory.add(f);
			
		}
		
		System.out.println("Successfully added 5 Products");
	}
	/**
	 * The Sales Menu lets you choose to View Product, View Cart, and
	 * Add Item to Cart.
	 */
	private static void salesMenu() 
	{

		System.out.println();
		System.out.println("\t \t Welcome!");
		System.out.println("**************************************************");
		System.out.println("\t Press 1 to View Products");
		System.out.println("\t Press 2 to View Cart");
		System.out.println("\t Press 3 to Add Item to Cart");
		System.out.println("\t Press 4 to Return to Main Menu");
		System.out.println();
		System.out.println("\t Press 5 to Checkout");
		System.out.println("***************************************************");
		
		String choice = input.next();
		
		switch(choice)
		{
		case"1":
		{
			searchProduct();
			break;
		}
		case"2":
		{
			viewCart();
			break;
		}
		case"3":
		{
			additem();
			break;
		}
		case"4":
		{
			menu();
			break;
		}
		case"5":
		{
			checkout();
			break; 
		}
		default:
		{
			System.out.println("Please choose between 1-5.");
			break;
		}
		}
		
		salesMenu();
	}
	/**
	 * This method will give you the ability to pay the items in the cart Arraylist.
	 */
	private static void checkout() 
	{
		
		
		System.out.println("Transaction Total is €" + currency.format(transactionCost));
		System.out.println("Make Payment");
		
		System.out.println();
		
			double moneyEntered = input.nextDouble();
			if(moneyEntered < transactionCost)
			{
				System.out.println("Balance remaining €"  + currency.format(transactionCost - moneyEntered));
				moneyEntered += input.nextDouble();
			}
			System.out.println("Transaction Sucessful");
			System.out.println("Change is €" + currency.format(moneyEntered-transactionCost));
			System.out.println("");
			System.out.println(""); 
			
			System.out.println("Would you like a receipt? (Yes or No)");
		
			String choice = input.next();
			
			switch(choice)
			{
			case"Yes":
			case"yes":
			case"YES":
			{
			System.out.println("==========================Reciept============================");
				for(Product c: totalSales)
				{
					System.out.println(c.getProductId() + "\t" + c.getProductName() + "\t€" + currency.format(c.getPrice()));
					
					
				}
				System.out.println();
				System.out.println("Total: \t\t€" + currency.format(transactionCost));
				System.out.println("Paid by Cash: \t€" + currency.format(moneyEntered));
				System.out.println("Change: \t€" + currency.format(moneyEntered-transactionCost));
				System.out.println();
				System.out.println("========================================================");
			
				break;
			}
			case"No":
			case"no":
			case"NO":
			{
				break;
				
			}
			default:
			{
				System.out.println("Error, Please select Yes or No");
			}
			}
		cart.clear();
		transactionCost = 0;
		menu();
	}
	
	private static void additem() 
	{
		for(Product f: inventory)
		{
			System.out.println(f.getProductId() + "\t" + f.getProductName() + "\t€" + currency.format(f.getPrice()));
			System.out.println();
			
		}
		System.out.println("Please choose a Product by ID");
		int chosenProduct = input.nextInt();
		for(Product p: inventory)
		{
			if(chosenProduct==p.getProductId()) 
			{
				transactionCost += p.getPrice();
				salesCost += p.getPrice();
				cart.add(p);
				totalSales.add(p);
			}
		
		}
		
		System.out.println("Would you like buy more? (Yes OR No)");
		System.out.println();
		 String add = input.next();
         
         
         switch(add)   
         {   
         case "Yes":  //added both Yes and yes just in-case the user types capital Y
         case"yes":
         case"YES":
         {  
             additem();   
             break;   
         }   
         case "No":  //added both No and no just in-case the user types capital N
         case "no":
         case"NO":
         {   
             salesMenu();   
             break;   
         }   
         default:   
         {   
             System.out.println("Please choose Yes or No.");
             System.out.println();
             break;
         }
         }   
		salesMenu();
		
	}
	private static void viewCart() 
	{
		
		for(Product c: cart)
		{
			System.out.println(c.getProductId() + "\t" + c.getProductName() + "\t€" + currency.format(c.getPrice()));
			
		}
		System.out.println();
		System.out.println("Transaction Total: \t€" + currency.format(transactionCost));
		System.out.println();
		System.out.println("Press 1 to Continue Shopping");
		System.out.println("Press 2 to Checkout");
		System.out.println("Press 3 to Cancel Shopping");
		
		String choice = input.next();
		
		switch(choice)
		{
		case"1":
		{
			additem();
			break;
		}
		case"2":
		{
			checkout();
			break;
		}
		case"3":
		{
			cart.clear();
			menu();
		}
		default:
		{
			System.out.println("Error, Please choose 1-3.");
			break;
		}
		
		}
		viewCart();
	}
	
}
