package productPackage;

public class Product {
	
	
	public int product_id;
	public String product_name;
	public String product_description;
	public double price;
	public int stock;

	public Product(int id, String p_name, String description, double price, int stocks) {
	    this.product_id = id;
	    this.product_name = p_name;
	    this.product_description = description;
	    this.price = price;
	    this.stock = stocks;
	}


}
