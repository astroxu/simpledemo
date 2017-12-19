package hbidemo.core.NewEmployeeOnboarding.service;

import com.hand.hap.activiti.custom.IActivitiBean;
import org.activiti.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Service;


/**
 * Created by parton.chen on 2017/5/9.
 */

@Service
public class NeoJudge implements  IActivitiBean {

    public  void getApproveResult1(String str,DelegateExecution delegateExecution){
        delegateExecution.setVariable("result1",str);
    }

    public  void getApproveResult2(String str,DelegateExecution delegateExecution){
        delegateExecution.setVariable("result2",str);
    }
    public void getTurn(DelegateExecution delegateExecution){
        String result1="";
        String result2="";
        //如果两个变量的值其中之一为空，则设置变量f的值为true，否则设为false,并获取两个result的值，如果两个之中有一个是拒绝，就设置f为true
        if(delegateExecution.getVariable("result1")==null||delegateExecution.getVariable("result2")==null){
            delegateExecution.setVariable("F",Boolean.TRUE);
        }
        else{
             delegateExecution.setVariable("F",Boolean.FALSE);
             result1= delegateExecution.getVariable("result1").toString();
             result2= delegateExecution.getVariable("result2").toString();
            if(("REJECTED").equals(result1)||("REJECTED").equals(result2)){
                delegateExecution.setVariable("F",Boolean.TRUE);
            }
        }


    }
    @Override
    public String getBeanName() {
        return "NeoJudge";
    }

    }
    //  ${NeoJudge.getTurn(execution)}  approveResult,


