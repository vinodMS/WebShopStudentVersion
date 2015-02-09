package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Inventory {
	private Map<Product, Integer> allProducts;
	private static Inventory singleInventory;
	
	private Inventory() {
		allProducts = new HashMap<Product, Integer>();
	}
	
	public static Inventory getInventory() {
		if (singleInventory == null) {
			singleInventory = new Inventory();
		}
		return singleInventory;
	}

	public int size() {
		return allProducts.size();
	}

	public boolean contains(Product key) {
		return allProducts.containsKey(key);
	}

	public int get(Product key) {
		return allProducts.get(key);
	}
	
	public void remove(Product key, int amount) throws IllegalParameterException {
		int currentAmount;
		if (contains(key)) {
			currentAmount = allProducts.get(key);
			if (currentAmount >= amount) {
				int newAmount = currentAmount - amount;
				allProducts.put(key,  newAmount);
			}
			else {
				throw new IllegalParameterException("Cannot remove more than existing amount");
			}
		}
		else {
			throw new IllegalParameterException("Cannot remove product which does not exist");
		}
	}
	
	public void remove(Product key) throws IllegalParameterException {
		Integer amount = allProducts.get(key); 
		if (amount == null) {
			throw new IllegalParameterException("Cannot remove product which does not exist");
		}
		allProducts.put(key, 0);
	}
	
	public void add(Product product, int amount) {
		if (allProducts.containsKey(product)) {
			int currentAmount = allProducts.get(product);
			allProducts.put(product,  currentAmount + amount);
		}
		else {
			allProducts.put(product,  amount);
		}
	}

	public void clear() {
		allProducts.clear();
		
	}
	
	public List<Product> getAllProducts() {
		return new ArrayList<Product>(allProducts.keySet());
	}
	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("Inventory contains: \n");
		List<Product> currentProducts = getAllProducts();
		for (Product aProduct: currentProducts) {
			result.append(aProduct);
			result.append(", ");
			result.append(allProducts.get(aProduct));
			result.append(" items in stock.\n");
		}
		return new String(result);
	}
}
