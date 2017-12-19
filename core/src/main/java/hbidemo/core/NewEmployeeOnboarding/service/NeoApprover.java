package hbidemo.core.NewEmployeeOnboarding.service;


import com.hand.hap.activiti.custom.IActivitiBean;
import hbidemo.core.NewEmployeeOnboarding.dto.NeoApplication;
import hbidemo.core.NewEmployeeOnboarding.mapper.NeoApplicationMapper;
import org.activiti.engine.delegate.DelegateExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by parton.chen on 2017/5/9.
 */

@Service
public class NeoApprover implements IActivitiBean {

    @Autowired
    NeoApplicationMapper neoApplicationMapper;


    public String getApplicant(DelegateExecution delegateExecution){
        NeoApplication neoApplication =new NeoApplication();
        neoApplication.setNum(delegateExecution.getProcessInstanceBusinessKey());
        if(neoApplicationMapper.select(neoApplication).size()>0){
            neoApplication= neoApplicationMapper.select(neoApplication).get(0);
        }
        String applicant = neoApplication.getApplicant();
        return applicant;
    }


    public String getRecipitor(DelegateExecution delegateExecution){
        NeoApplication neoApplication =new NeoApplication();
        neoApplication.setNum(delegateExecution.getProcessInstanceBusinessKey());
        if(neoApplicationMapper.select(neoApplication).size()>0){
            neoApplication= neoApplicationMapper.select(neoApplication).get(0);
        }
        String delegate = neoApplication.getReceiptor();
        return delegate;
    }

    @Override
    public String getBeanName() {
        return "getNeoApprover";
    }

}


