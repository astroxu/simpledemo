package hbidemo.core.test3.service.impl;

import com.hand.hap.activiti.custom.IActivitiBean;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProcessLoan implements JavaDelegate,IActivitiBean{
    private static Logger logger = LoggerFactory.getLogger(ProcessLoan.class);
    @Override
    public void execute(DelegateExecution execution) {
        logger.debug("#################################");
        logger.debug("处理借款请求");
        logger.debug("#################################");
    }

    @Override
    public String getBeanName() {
        return "processLoan";
    }
}
