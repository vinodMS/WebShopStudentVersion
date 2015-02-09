package domain;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named(value="ph")
@RequestScoped
public class ProductHolder {
    private Product currentProduct;
    private int amount;
    private int[] amountList = new int[10];
    @Inject
    private Cart cart;
    @Inject
    private Catalog catalog;
    @Inject
    private ShoppingCart shoppingCart;

    public Product getCurrentProduct() {
        return currentProduct;
    }
    public void setCurrentProduct(Product p) {
        this.currentProduct = p;
    }
    
    public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public Long getProductId() {
        return currentProduct != null ? currentProduct.getId() : null;
    }
  
    public void setProductId(Long pid) {    
      currentProduct = catalog.getProduct(pid);
    }
    
    public int[] getAmountList() {
    	for(int i=1; i<11; i++){
    		amountList[i] = i;
    	}
		return amountList;
    }
    
    public int[] addStockSelection(){
    	for(int i=0; i<10; i++){
    		amountList[i] = i;
    	}
    	return amountList;
    }

    public String addToCart()	{
    	OrderItem order = new OrderItem(getProductId(), currentProduct.getName(), currentProduct.getPrice(), amount);
    	System.out.println("Adding "+order.getId());
//    	System.out.println(currentProduct.hashCode());
        cart.add(order.getId());
        return "added";
    	
    }
    public String addToShoppingCart() {
    	OrderItem order = new OrderItem(getProductId(), currentProduct.getName(), currentProduct.getPrice(), amount);
    	shoppingCart.addToCart(currentProduct);
    	return "added";
    }
}
