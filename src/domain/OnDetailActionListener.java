package domain;

import javax.el.ELContext;
import javax.el.ValueExpression;
import javax.faces.application.Application;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;

public class OnDetailActionListener implements ActionListener {
    @Override
    public void processAction(ActionEvent ev) throws AbortProcessingException {
        FacesContext context = FacesContext.getCurrentInstance();
        Application app = context.getApplication();
        Product p = (Product) app.evaluateExpressionGet(context, "#{p}",
        		Product.class);
        ELContext elContext = context.getELContext();
        ValueExpression ve = app.getExpressionFactory().createValueExpression(
        		elContext, "#{ph.currentProduct}", Product.class);
        ve.setValue(elContext, p);
    }
}