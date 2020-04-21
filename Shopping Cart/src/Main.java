import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;


public class Main 
{
	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static Scanner input = new Scanner(System.in);
	private static int centralId=1001;
	private static int centralCartId=11;
	private static ArrayList<Product> inventory = new ArrayList<Product>();
	private static ArrayList<Product> cart = new ArrayList<Product>();
	
	

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
	private static void menu() 
	{
		System.out.println("\t \t WELCOME TO FOOD STORE - ONLINE ");
		System.out.println();
		System.out.println("Press 1 to Start Stopping");
		System.out.println("Press 2 to Product Menu");
		System.out.println("");
		System.out.println("Press 5 to Save and Quit");
		
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
				
				e.printStackTrace();
			}
			
			System.out.println("Data Saved, System shutting down");
			System.exit(0);
		}
		case"99":
		{
			adminMenu();
			break;
		}
		default:
		{
			System.out.println("Error, Please select 1 or 2");
			break;
		}
		
		}
		menu();
		}
	private static void exportDatabase() throws Exception 
	{
		FileOutputStream exportFile = new FileOutputStream("player.ser");
		ObjectOutputStream write = new ObjectOutputStream(exportFile);
		write.writeObject(inventory);
		write.close();
		
		
	}
	private static void importDatabase() throws Exception
	{
		FileInputStream importFile = new FileInputStream("product.ser");
		ObjectInputStream read = new ObjectInputStream(importFile);
		inventory = (ArrayList<Product>)read.readObject();
		read.close();
		
		
	}
	private static void adminMenu() 
	{
		
		
	}
	private static void productMenu() 
	{
		System.out.println("Welcome to the Product Menu!");
		System.out.println();
		System.out.println("Press 1 to Create New Player");
		System.out.println("Press 2 to Create 10 random Products");
		System.out.println("Press 3 to Print Products");
		System.out.println("Press 4 t Search for a Product");// Print the inventory arraylist and use a for loop
		
	}
	private static void createProducts() {
		// TODO Auto-generated method stub
		
	}
	private static void salesMenu() 
	{
		
	}
		
		
	}


