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
public class NeoPrejudge implements  IActivitiBean {
    @Autowired
    NeoApplicationMapper neoApplicationMapper;

    public void DLQ(DelegateExecution delegateExecution) {
        NeoApplication neoApplication =new NeoApplication();
        neoApplication.setNum(delegateExecution.getProcessInstanceBusinessKey());
        if(neoApplicationMapper.select(neoApplication).size()>0){
            neoApplication= neoApplicationMapper.select(neoApplication).get(0);
        }
       String lq = neoApplication.getReceiptor();
       String sq = neoApplication.getApplicant();
        if(sq.equals(lq)||lq==""||lq==null) {
            delegateExecution.setVariable("Flage",Boolean.FALSE);
        }else{
            delegateExecution.setVariable("Flage",Boolean.TRUE);
        }

    }


    @Override
    public String getBeanName() {
        return "NeoPrejudge";
    }

    }


