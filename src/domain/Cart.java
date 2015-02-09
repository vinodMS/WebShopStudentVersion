package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named(value="cart")
@SessionScoped
public class Cart implements Serializable {
 
	private static final long serialVersionUID = 1L;
	private List<Long> productIds;

    public Cart() {
         productIds = new ArrayList<Long>();
    }
    public void add(long pid) {
         productIds.add(pid);
    }
    
    public List<Long> getProductIds() {
        return productIds;
    }
}