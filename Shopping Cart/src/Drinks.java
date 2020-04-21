
public class Drinks extends Product 
{
	
	
	private String drinkType;
	private String flavour;

	public Drinks()
	{
		
	}

	public void printProduct()
	{
		System.out.println("Product ID:\t" + getProductId());
		System.out.println("Product Name:\t" + getProductName());
		System.out.println("Food Type:\t" + drinkType);
		System.out.println("Flavour: \t" + flavour);
		System.out.println("Quantity:\t" + getQuantity());
		System.out.println("Price:\t" + getPrice());
		System.out.println("");
		System.out.println("Calories:\t "+ getCalories());
	}
	

	public String getDrinkType() {
		return drinkType;
	}

	public void setDrinkType(String drinkType) {
		this.drinkType = drinkType;
	}

	public String getFlavour() {
		return flavour;
	}

	public void setFlavour(String flavour) {
		this.flavour = flavour;
	}
	
	

}
