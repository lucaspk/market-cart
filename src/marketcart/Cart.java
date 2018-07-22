package marketcart;

import java.util.HashMap;
import java.util.Map;

public class Cart {

	private Map<Product, Integer> products;
	private Map<String, Float> providersAmount;
	
	private final Integer DEFAULT_QUANTITY = 1;
	
	public Cart() {
		this.products = new HashMap<>();
		this.providersAmount = new HashMap<>();
	}
		
	public void addProduct(final Product newProduct, Integer quantity) {
		if (products.containsKey(newProduct)) {
			int oldQuantity = products.get(newProduct);
			products.put(newProduct, quantity + oldQuantity);
		} else {
			products.put(newProduct, quantity);
		}
		
		String provider = newProduct.getProvider();
		if (providersAmount.containsKey(provider)) {
			float oldAmount = providersAmount.get(provider);
			providersAmount.put(provider, oldAmount + newProduct.getPrice()*quantity);
		} else {
			providersAmount.put(provider, newProduct.getPrice()*quantity);
		}
	}
	
	public void addProduct(final Product newProduct) {
		addProduct(newProduct, DEFAULT_QUANTITY);
	}

	public int getTotalProductsInCart() {
		return products.size();
	}
	
	public int getProductQuantity(Product p1) {
		return products.get(p1);
	}
	
	public void removeProduct(final Product product) {
		String provider = product.getProvider();
		if (products.containsKey(product)) {
			float oldPrice = providersAmount.get(provider);
			providersAmount.put(provider, oldPrice - product.getPrice()*getProductQuantity(product));
			
			products.remove(product);
		}
	}

	public float getTotalPrice() {
		float totalPrice = 0.0f;
		for (Product p : products.keySet()) {
			totalPrice += p.getPrice() * products.get(p);
		}
		return totalPrice;
	}
	
	public float getAmoutToReceiveBy(String provider) {
		return providersAmount.get(provider);
	}



	

}
