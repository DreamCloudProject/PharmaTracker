package ru.dreamcloud.alexion.zk.viewmodels.widgets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zul.Window;

import ru.dreamcloud.alexion.model.authentication.CommonRole;
import ru.dreamcloud.alexion.zk.services.AuthenticationService;
import ru.dreamcloud.alexion.zk.services.SchedulerService;
import ru.dreamcloud.util.jpa.DataSourceLoader;

public class ShortcutMenuVM {
	
	/**************************************
	  Property authService	 
	***************************************/
	private AuthenticationService authService;
	
	/**************************************
	  Property schedulerService	 
	***************************************/
	private SchedulerService schedulerService;
	
	/**************************************
	  Property isVisibleCreateNewEvent	 
	***************************************/
	private Boolean isVisibleCreateNewEvent;	
	
	public Boolean getIsVisibleCreateNewEvent() {
		return isVisibleCreateNewEvent;
	}

	public void setIsVisibleCreateNewEvent(Boolean isVisibleCreateNewEvent) {
		this.isVisibleCreateNewEvent = isVisibleCreateNewEvent;
	}

	/**************************************
	  Methods	 
	***************************************/
	@Init
	public void init(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
		Session session = Sessions.getCurrent();
		authService = new AuthenticationService();
		schedulerService = (SchedulerService)session.getAttribute("schedulerService");
		List<CommonRole> matchRoles = new ArrayList(DataSourceLoader.getInstance().fetchRecords("CommonRole", "where e.title='"+view.getPage().getId()+"'"));
		CommonRole pageRole = matchRoles.isEmpty() ? null : matchRoles.get(0);		
		isVisibleCreateNewEvent = authService.checkAccessRights(pageRole,"createNewEvent");
	}
	
	@Command
	public void createNewPatientHistory(){
		final HashMap<String, Object> params = new HashMap<String, Object>();    	
    	params.put("actionType", "NEW");
    	Window window = (Window)Executions.createComponents("/WEB-INF/zk/windows/patienthistorywindow.zul", null, params);
        window.doModal();
	}
	
	@Command
	public void createNewEvent(){
    	final HashMap<String, Object> params = new HashMap<String, Object>();    	
    	params.put("actionType", "NEW");
    	Window window = (Window)Executions.createComponents("/WEB-INF/zk/windows/eventwindow.zul", null, params);
        window.doModal();
	}
	
	@Command
	public void logout(){
		authService.logout();
		schedulerService.cancelAllSchedulerJobs();
		schedulerService.setTimer(null);
		Executions.sendRedirect(Labels.getLabel("pages.login.URL"));
	}
	
}
