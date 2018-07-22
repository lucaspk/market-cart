package marketcart;

import org.junit.*;

public class TestMarketCart {

	private Cart c;
	
	private Product p1;
	private Product p2;
	private Product p3;
	
	@Before
	public void setUp() {
		c = new Cart();
		p1 = new Product("Desodorante Kaiak", "Natura", 55.96f);
		p2 = new Product("Creme para barbear", "Boticario", 24.00f);
		p3 = new Product("creme para cabelo", "Avon", 35.04f);
		
	}
	
	@Test
	public void addOneItemWithQuantityToCart() {
		c.addProduct(p1, 2);
		
		int expectedTotalProducts = 1;
		Assert.assertEquals(expectedTotalProducts, c.getTotalProductsInCart());
	}
	
	@Test
	public void addItemsWithQuantityToCart() {
		c.addProduct(p1, 2);
		c.addProduct(p2, 3);
		c.addProduct(p3, 3);
		
		int expectedTotalProducts = 3;
		Assert.assertEquals(expectedTotalProducts, c.getTotalProductsInCart());
	}
	
	@Test
	public void addItemsRepeatedToCartIncrementQuantity() {
		c.addProduct(p1, 2);
		c.addProduct(p1, 3);
		
		int expectedTotalProducts = 1;
		Assert.assertEquals(expectedTotalProducts, c.getTotalProductsInCart());
		
		int expectedQuantity = 5;
		Assert.assertEquals(expectedQuantity, c.getProductQuantity(p1));
		
	}
	
	@Test
	public void addItemsToCartWithoutQuantity() {
		c.addProduct(p1);
		c.addProduct(p2);
		c.addProduct(p3);
		
		int expectedTotalProducts = 3;
		Assert.assertEquals(expectedTotalProducts, c.getTotalProductsInCart());
	}
	
	@Test
	public void addItemsToCartWithoutQuantityAndThemChangeQuantity() {
		c.addProduct(p1);
		c.addProduct(p2);
		c.addProduct(p3);
				
		c.addProduct(p1, 3);
		
		int expectedTotalProducts = 3;
		Assert.assertEquals(expectedTotalProducts, c.getTotalProductsInCart());
		
		int expectedQuantity = 4;
		Assert.assertEquals(expectedQuantity, c.getProductQuantity(p1));
	}
	
	@Test
	public void addItemsToCartWithQuantityAndThemTryAddWithoutQuantity() {
		c.addProduct(p1, 3);
		c.addProduct(p2, 2);
		c.addProduct(p3, 2);
				
		c.addProduct(p1);
		
		int expectedTotalProducts = 3;
		Assert.assertEquals(expectedTotalProducts, c.getTotalProductsInCart());
		
		int expectedQuantity = 4;
		Assert.assertEquals(expectedQuantity, c.getProductQuantity(p1));
	}
	
	@Test
	public void RemoveItemsFromCart() {
		
		int expectedTotalProducts;
		
		c.addProduct(p1);
		c.addProduct(p2);
		c.addProduct(p3);
		
		expectedTotalProducts = 3;
		Assert.assertEquals(expectedTotalProducts, c.getTotalProductsInCart());
		
		c.removeProduct(p1);
		
		expectedTotalProducts = 2;
		Assert.assertEquals(expectedTotalProducts, c.getTotalProductsInCart());
		
		c.removeProduct(p2);
		
		expectedTotalProducts = 1;
		Assert.assertEquals(expectedTotalProducts, c.getTotalProductsInCart());
		
	}
	
	@Test
	public void getTotalPriceOfItemsAddedInCart() {
		c.addProduct(p1);
		c.addProduct(p2);
		c.addProduct(p3);
		
		float expectedPrice = p1.getPrice() + p2.getPrice() + p3.getPrice();
		
		Assert.assertEquals(expectedPrice, c.getTotalPrice(), 0.0001);
	}
	
	@Test
	public void getTotalPriceOfItemsWithQuantityAddedInCart() {
		c.addProduct(p1, 2);
		c.addProduct(p2, 3);
		c.addProduct(p3, 3);
		
		float expectedPrice = p1.getPrice()*2 + p2.getPrice()*3 + p3.getPrice()*3;
		
		Assert.assertEquals(expectedPrice, c.getTotalPrice(), 0.0001);
	}
	
	@Test
	public void updateTotalPriceAfterRemoveItem() {
		c.addProduct(p1);
		c.addProduct(p2);
		c.addProduct(p3);
		
		float expectedPrice = p1.getPrice() + p2.getPrice() + p3.getPrice();
		Assert.assertEquals(expectedPrice, c.getTotalPrice() , 0.0001);
		
		c.removeProduct(p3);
		
		float updatedPrice = p1.getPrice() + p2.getPrice();
		Assert.assertEquals(updatedPrice, c.getTotalPrice() , 0.0001);
		
	}
	
	@Test
	public void getTotalPaymentPerProvider() {
		Product p4 = new Product("Sabonete Líquido", "Avon", 19.90f);
		
		c.addProduct(p3, 3);
		c.addProduct(p4, 2);
		
		float avonAmount = p3.getPrice()*3 + p4.getPrice()*2;
		
		Assert.assertEquals(avonAmount, c.getAmoutToReceiveBy(p3.getProvider()), 0.00001);
	}
	
	@Test
	public void getTotalPaymentPerProviderAfterRemoveFromCart() {
		Product p4 = new Product("Sabonete Líquido", "Avon", 19.90f);
		
		c.addProduct(p3, 3);
		c.addProduct(p4, 2);
		
		float avonAmount = p3.getPrice()*3 + p4.getPrice()*2;
		Assert.assertEquals(avonAmount, c.getAmoutToReceiveBy(p3.getProvider()), 0.00001);
		
		c.removeProduct(p4);
		
		float newAvonAmount = p3.getPrice()*3;
		Assert.assertEquals(newAvonAmount, c.getAmoutToReceiveBy(p3.getProvider()), 0.00001);
	}
	
}
