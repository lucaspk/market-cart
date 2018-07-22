package marketcart;

public class Product {

	private String name;
	private String provider;
	private float price;
	
	public Product(String name, String provider, float price) {
		this.name = name;
		this.provider = provider;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	
	

}
