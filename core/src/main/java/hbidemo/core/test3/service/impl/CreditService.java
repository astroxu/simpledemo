package hbidemo.core.test3.service.impl;

import com.hand.hap.activiti.custom.IActivitiBean;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service
public class CreditService implements JavaDelegate,IActivitiBean{
    @Override
    public void execute(DelegateExecution execution) {
        Integer amount = execution.getVariable("amount",Integer.class);
        Integer credit = execution.getVariable("credit",Integer.class);
        if(amount > credit*1000){
            execution.setVariable("accept",Boolean.FALSE);
        }else {
            execution.setVariable("accept",Boolean.TRUE);
        }
    }

    @Override
    public String getBeanName() {
        return "checkCredit";
    }
}
