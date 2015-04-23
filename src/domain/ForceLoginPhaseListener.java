package domain;

import javax.faces.application.Application;
import javax.faces.application.ViewHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

public class ForceLoginPhaseListener implements PhaseListener{
	private static final long serialVersionUID = 1L;
	
	@Override
	public void afterPhase(PhaseEvent event) {
	}

	@Override
	public void beforePhase(PhaseEvent event) {
		FacesContext context = FacesContext.getCurrentInstance();
		String viewID = context.getViewRoot().getViewId();
		if (viewID.equals("/editUser")){
			Application app = context.getApplication();
			UserHolder uh = app.evaluateExpressionGet(context, "#{uh}", UserHolder.class);
			if (uh.getCurrentUser() == null){
				ViewHandler viewHandler = app.getViewHandler();
				UIViewRoot viewRoot = viewHandler.createView(context, "/login.html");
				context.setViewRoot(viewRoot);
			}
		}
		
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RENDER_RESPONSE;
	}

}
