                                                   

public class Food extends Product
{
	private String foodType;
	
	
	private int protein;
	private int carbs;
	private int fats;
	
	
	public Food()
	{
		
	}
	public void printPoduct()
	{
		System.out.println("Product ID:\t" + getProductId());
		System.out.println("Product Name:\t" + getProductName());
		System.out.println("Food Type:\t" + foodType);
		System.out.println("Quantity:\t" + getQuantity());
		System.out.println("Price:\t" + getPrice());
		System.out.println("");
		System.out.println("Calories:\t "+ getCalories());
		System.out.println("Carbohydrates: " + carbs);
		System.out.println("Protein:\t " + protein);
		System.out.println("Fats:\t " + fats );
			
		
	}
	
	
	public String getFoodType() {
		return foodType;
	}
	public void setFoodType(String foodType) {
		this.foodType = foodType;
	}
	public int getProtein() {
		return protein;
	}
	public void setProtein(int protein) {
		this.protein = protein;
	}
	public int getCarbs() {
		return carbs;
	}
	public void setCarbs(int carbs) {
		this.carbs = carbs;
	}
	public int getFats() {
		return fats;
	}
	public void setFats(int fats) {
		this.fats = fats;
	}
	
	
	
	
}
