package domain;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class ConfirmService {
    @Inject
    private Cart cart;
    @Inject
    private ProductDAO catalog;
    @Inject
    private UserHolder uh;

    public double getTotal() {
        double total = 0;
//        for (String pid : cart.getProductIds()) {
//            total += catalog.getProduct(pid).getPrice();
//        }
        return total;
   }
//   public String getCreditCardNo() {
//       return uh.getCurrentUser().getCreditCardNo();
// }
    
    public String getUsername() {
        return null;
  }

}
