package domain;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.Query;
import org.hibernate.Session;

@Named(value="ph")
@RequestScoped
public class ProductHolder {
    private Product currentProduct;
    private int amount;
    private int[] amountList = new int[10];
    CreateInvoice invoice;
    @Inject
    private Catalog catalog;
    @Inject
    private ShoppingCart shoppingCart;
    
    private OrderItem order;
    private boolean filledCart;;
    
    public OrderItem getOrder() {
		return order;
	}
	public void setOrder(OrderItem order) {
		this.order = order;
	}
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
  
    public boolean isFilledCart() {
		return filledCart;
	}
	public void setFilledCart(boolean filledCart) {
		this.filledCart = filledCart;
	}
	public void setProductId(Long pid) {    
      currentProduct = catalog.getProduct(pid);
    }
    
    public int[] addStockSelection(){
    	int i =1;
    	for(i=1; i <= amountList.length; i++){
    		amountList[i-1] = i;
    	}
    	return amountList;
    }
    
    public List<Product> productNames(){
    	List<Product> list = new ArrayList<Product>();
        Iterator<Entry<Product, Integer>> iterator = shoppingCart.getOrders().entrySet().iterator() ;
        
        while(iterator.hasNext()){
        	Map.Entry<Product, Integer> storedProduct = iterator.next();
        	list.add(storedProduct.getKey());
        }
        return list;
    }
    
    public List<Integer> productQuantity(){
    	List<Integer> list = new ArrayList<Integer>();
        Iterator<Entry<Product, Integer>> iterator = shoppingCart.getOrders().entrySet().iterator() ;
        
        while(iterator.hasNext()){
        	Map.Entry<Product, Integer> storedProduct = iterator.next();
        	list.add(storedProduct.getValue());
        }
        return list;
    }
    
    public List<Double> productTotal(){
    	List<Double> list = new ArrayList<Double>();
        Iterator<Entry<Product, Integer>> iterator = shoppingCart.getOrders().entrySet().iterator() ;
        while(iterator.hasNext()){
        	Map.Entry<Product, Integer> storedProduct = iterator.next();
        	list.add(storedProduct.getValue() * storedProduct.getKey().getPrice());
        }
        return list;
    }
    
    public String subTotal(){
    	double total = 0;
    	DecimalFormat df = new DecimalFormat("#.00");
        Iterator<Entry<Product, Integer>> iterator = shoppingCart.getOrders().entrySet().iterator() ;
        while(iterator.hasNext()){
        	Map.Entry<Product, Integer> storedProduct = iterator.next();
        	total +=storedProduct.getValue() * storedProduct.getKey().getPrice();
        }
        return df.format(total);
    }
    
    public boolean cartStatus() throws IllegalStateTransitionException{
    	if(shoppingCart.getNumberOfItems() == 0){
    		setFilledCart(false);
    		shoppingCart.setCompleted(OrderStatus.EMPTY);
    		return true;
    	}
    	return false;
    }
    
    public String addToShoppingCart() throws IllegalStateTransitionException, IOException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.getTransaction().begin();
		Query query = session.createQuery("SELECT inStock FROM Product WHERE ID ='" +currentProduct.getId()+"' " );
		@SuppressWarnings("unchecked")
		List<Integer> results = query.list();
		int temp = 0; // getting stock number from product
		int tempCart = 0; //getting number of products from cart for the same item;
		for(int inStock : results) {
	        temp = inStock;
	    }
		
		if(shoppingCart.getOrders().containsKey(currentProduct)){
			tempCart = shoppingCart.getOrders().get(currentProduct);
		}
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		if(temp == 0){
			facesContext.addMessage("productDetails", new FacesMessage("Unable to proceed this order. Currently this item is out of stock"));
			return null;
		}
		else
		if((amount+tempCart) > temp){
			if(shoppingCart.getOrders().containsKey(currentProduct)){
				facesContext.addMessage("productDetails", new FacesMessage("Unable to proceed this order. Currently only " + temp + " item(s) are in stock. You already have "+ tempCart  +" items of this product in cart"));
				return null;
			}
			facesContext.addMessage("productDetails", new FacesMessage("Unable to proceed this order. Currently only " + temp + " item(s) are in stock."));
			return null;
		}
    	order = new OrderItem(getProductId(), currentProduct.getName(), currentProduct.getPrice(), amount);
		facesContext.addMessage("productDetails", new FacesMessage("Product Added " +order.getId()));
		shoppingCart.addToCart(currentProduct, amount);
		setFilledCart(true);
		shoppingCart.setCompleted(OrderStatus.FILLED);
		return "added";
    }
    
}
