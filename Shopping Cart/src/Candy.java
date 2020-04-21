
public class Candy extends Product 
{
	
	private String candyType;
	
	public Candy()
	{
		
	}
	
	public void printPoduct()
	{
		System.out.println("Product ID:\t" + getProductId());
		System.out.println("Product Name:\t" + getProductName());
		System.out.println("Food Type:\t" + candyType);
		System.out.println("Quantity:\t" + getQuantity());
		System.out.println("Price:\t" + getPrice());
		System.out.println("");
		System.out.println("Calories:\t "+ getCalories());
	}

	public String getCandyType() {
		return candyType;
	}

	public void setCandyType(String candyType) {
		this.candyType = candyType;
	}
	
	

}
