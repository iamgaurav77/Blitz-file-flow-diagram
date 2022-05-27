package com.tc.blitzfileFlowDiagram.serviceTask;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component("UserInfoSaveServiceTask")
public class UserInfoSaveServiceTask implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
        String name = (String) execution.getVariable("name");
        String password = (String) execution.getVariable("password");
        String dropDown = (String) execution.getVariable("typeOfTransactions");
        
	}

}
