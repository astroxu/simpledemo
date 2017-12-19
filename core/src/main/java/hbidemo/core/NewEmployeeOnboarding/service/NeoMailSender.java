/*
package hbidemo.core.NewEmployeeOnboarding.service;


import com.hand.hap.activiti.custom.IActivitiBean;
import com.hand.hap.hr.dto.Employee;
import com.hand.hap.hr.dto.EmployeeAssign;
import com.hand.hap.hr.dto.HrOrgUnit;
import com.hand.hap.hr.dto.Position;
import com.hand.hap.hr.mapper.EmployeeAssignMapper;
import com.hand.hap.hr.mapper.EmployeeMapper;
import com.hand.hap.hr.mapper.OrgUnitMapper;
import com.hand.hap.hr.mapper.PositionMapper;
import hbidemo.core.NewEmployeeOnboarding.dto.NeoApplication;
import hbidemo.core.NewEmployeeOnboarding.dto.NeoCheck;
import hbidemo.core.NewEmployeeOnboarding.mapper.NeoApplicationMapper;
import hbidemo.core.NewEmployeeOnboarding.mapper.NeoCheckMapper;
import org.activiti.engine.delegate.DelegateExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


*//*

*/
/*
*
 * Created by parton.chen on 2017/5/9.

*//*




@Service
public class NeoMailSender implements IActivitiBean {

    @Autowired
    NeoApplicationMapper neoApplicationMapper;
    @Autowired
    EmployeeMapper employeeMapper;
    @Autowired
    NeoCheckMapper neoCheckMapper;



    public void informITSpecialist(DelegateExecution delegateExecution){
        String htmltext="";
        String temptext="";
        String hrCode="";
        String name="";
       // String email= "";

        NeoApplication neoApplication =new NeoApplication();
        neoApplication.setNum(delegateExecution.getProcessInstanceBusinessKey());
        List<NeoApplication> neoApplications = new ArrayList<NeoApplication>();
        neoApplications= neoApplicationMapper.select(neoApplication);
        if(neoApplications.size()>0){
            hrCode = neoApplications.get(0).getUserCode();
        }
        Employee employee =new Employee();
        employee.setEmployeeCode(hrCode);
        if(employeeMapper.select(employee).size()>0){
            name =employeeMapper.select(employee).get(0).getName();
          //  email=employeeMapper.select(employee).get(0).getEmail();
        }


        temptext= " <body>" +
                "    <tr>" +
                "        <font style='font-size: 17px;color: black'><b>Hello! Dear IT Infra Specialist:</b></font></br>" +
                "        &nbsp;&nbsp;&nbsp;&nbsp;<font style='font-size: 10px;color: black'><b>"+name+" has initiated the New Employee Induction Application. Below is the application information.</b></font></br>" +
                "    </tr>" +
                "    <tr>" +
                "        <table  align='left' style='border-collapse: collapse;'>" +
                "            <tr align='center' style='background-color:rgb(230,230,230)'> <td colspan='4'>New Employee Onboarding Application Form</td></tr>" +
                "            <tr align='center' style='background-color:rgb(242,242,242)'><td colspan='4'>Basic Information</td></tr>";
         htmltext+=temptext;


        for(int i=0;i<neoApplications.size();i++){
            if(i%2==0&&i==(neoApplications.size()-1)){
                temptext= " <tr align=\"center\"><td width='25%' style='border: #e7ecf1 solid 1px;padding:8px;font-size:12px;'>"+neoApplications.get(i).getName()+"</td><td width='25%' style='border: #e7ecf1 solid 1px;padding:8px;font-size:12px;'>"+neoApplications.get(i).getValue()+"</td><td width='25%' style='border: #e7ecf1 solid 1px;padding:8px;font-size:12px;'></td><td width='25%' style='border: #e7ecf1 solid 1px;padding:8px;font-size:12px;'></td></tr>";
                htmltext+=temptext;
            }else if(i%2==0){
                temptext ="<tr align=\"center\"><td width='25%' style='border: #e7ecf1 solid 1px;padding:8px;font-size:12px;'>" + neoApplications.get(i).getName() + "</td><td width='25%' style='border: #e7ecf1 solid 1px;padding:8px;font-size:12px;'>" + neoApplications.get(i).getValue() + "</td>";
                htmltext+=temptext;
            }else if(i%2==1){
                temptext="<td width='25%' style='border: #e7ecf1 solid 1px;padding:8px;font-size:12px;'>"+neoApplications.get(i).getName()+"</td><td width='25%' style='border: #e7ecf1 solid 1px;padding:8px;font-size:12px;'>"+neoApplications.get(i).getValue()+"</td></tr>";
                htmltext+=temptext;
            }
        }
        temptext=" </table>\n" +
                "    </tr>\n" +
                "    </body>";
        htmltext+=temptext;



        MailSenderInfo mailInfo = new MailSenderInfo();
        mailInfo.setMailServerHost("mail.hand-china.com");
        mailInfo.setMailServerPort("25");
        mailInfo.setValidate(true);
        mailInfo.setUserName("wencheng.tang@hand-china.com");
        mailInfo.setPassword("A1478963250a");//您的邮箱密码
        mailInfo.setFromAddress("wencheng.tang@hand-china.com");
        //mailInfo.setToAddress(email);
        mailInfo.setToAddress("183349812@qq.com");
        mailInfo.setSubject("New Employee Onboarding Workflow");
        mailInfo.setContent(htmltext);
        //这个类主要来发送邮件
        // SimpleMailSender sms = new SimpleMailSender();
        //  sms.sendTextMail(mailInfo);//发送文体格式
        SimpleMailSender.sendHtmlMail(mailInfo);//发送html格式
    }

    public void informServiceDesk1(DelegateExecution delegateExecution){
        String htmltext="";
        String temptext="";
        String hrCode="";
        String name="";
        // String email= "";

        NeoApplication neoApplication =new NeoApplication();
        neoApplication.setNum(delegateExecution.getProcessInstanceBusinessKey());
        List<NeoApplication> neoApplications = new ArrayList<NeoApplication>();
        neoApplications= neoApplicationMapper.select(neoApplication);
        if(neoApplications.size()>0){
            hrCode = neoApplications.get(0).getUserCode();
        }
        Employee employee =new Employee();
        employee.setEmployeeCode(hrCode);
        if(employeeMapper.select(employee).size()>0){
            name =employeeMapper.select(employee).get(0).getName();
            //  email=employeeMapper.select(employee).get(0).getEmail();
        }


        temptext= " <body>\n" +
                "    <tr>\n" +
                "        <font style='font-size: 17px;color: black'><b>Hello! Dear Service Desk:</b></font></br>\n" +
                "        &nbsp;&nbsp;&nbsp;&nbsp;<font style='font-size: 10px;color: black'><b>"+name+" has initiated the New Employee Induction Application. Below is the application information. Please prepare the appropriate equipment for the employee and click the link below to login HAP system to input the relevant information.</b></font></br>http://localhost:8080/</br>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "        <table  align='left' style='border-collapse: collapse;'>\n" +
                "            <tr align='center' style='background-color:rgb(230,230,230)'> <td colspan='4'>New Employee Onboarding Application Form</td></tr>\n" +
                "            <tr align='center' style='background-color:rgb(242,242,242)'><td colspan='4'>Basic Information</td></tr>";
        htmltext+=temptext;


        for(int i=0;i<neoApplications.size();i++){
            if(i%2==0&&i==(neoApplications.size()-1)){
                temptext= " <tr align=\"center\"><td width='25%' style='border: #e7ecf1 solid 1px;padding:8px;font-size:12px;'>"+neoApplications.get(i).getName()+"</td><td width='25%' style='border: #e7ecf1 solid 1px;padding:8px;font-size:12px;'>"+neoApplications.get(i).getValue()+"</td><td width='25%' style='border: #e7ecf1 solid 1px;padding:8px;font-size:12px;'></td><td width='25%' style='border: #e7ecf1 solid 1px;padding:8px;font-size:12px;'></td></tr>";
                htmltext+=temptext;
            }else if(i%2==0){
                temptext ="<tr align=\"center\"><td width='25%' style='border: #e7ecf1 solid 1px;padding:8px;font-size:12px;'>" + neoApplications.get(i).getName() + "</td><td width='25%' style='border: #e7ecf1 solid 1px;padding:8px;font-size:12px;'>" + neoApplications.get(i).getValue() + "</td>";
                htmltext+=temptext;
            }else if(i%2==1){
                temptext="<td width='25%' style='border: #e7ecf1 solid 1px;padding:8px;font-size:12px;'>"+neoApplications.get(i).getName()+"</td><td width='25%' style='border: #e7ecf1 solid 1px;padding:8px;font-size:12px;'>"+neoApplications.get(i).getValue()+"</td></tr>";
                htmltext+=temptext;
            }
        }
        temptext=" </table>\n" +
                "    </tr>\n" +
                "    </body>";
        htmltext+=temptext;



        MailSenderInfo mailInfo = new MailSenderInfo();
        mailInfo.setMailServerHost("mail.hand-china.com");
        mailInfo.setMailServerPort("25");
        mailInfo.setValidate(true);
        mailInfo.setUserName("wencheng.tang@hand-china.com");
        mailInfo.setPassword("A1478963250a");//您的邮箱密码
        mailInfo.setFromAddress("wencheng.tang@hand-china.com");
        //mailInfo.setToAddress(email);
        mailInfo.setToAddress("183349812@qq.com");
        mailInfo.setSubject("New Employee Onboarding Workflow");
        mailInfo.setContent(htmltext);
        //这个类主要来发送邮件
        // SimpleMailSender sms = new SimpleMailSender();
        //  sms.sendTextMail(mailInfo);//发送文体格式
        SimpleMailSender.sendHtmlMail(mailInfo);//发送html格式
    }

    public void informServiceDesk2(DelegateExecution delegateExecution){
        String htmltext="";
        String temptext="";
        // String email= "";
        String appName="";
        String app="";

        NeoApplication neoApplication =new NeoApplication();
        neoApplication.setNum(delegateExecution.getProcessInstanceBusinessKey());
        List<NeoApplication> neoApplications = new ArrayList<NeoApplication>();
        neoApplications= neoApplicationMapper.select(neoApplication);
        if(neoApplications.size()>0){
            app =neoApplications.get(0).getApplicant();
        }
        Employee employee =new Employee();
        employee.setEmployeeCode(app);
        if(employeeMapper.select(employee).size()>0){
            appName =employeeMapper.select(employee).get(0).getName();

        }


        temptext= " <body>\n" +
                "    <tr>\n" +
                "        <font style='font-size: 17px;color: black'><b>Hello! Dear Service Desk:</b></font></br>\n" +
                "        &nbsp;&nbsp;&nbsp;&nbsp;<font style='font-size: 10px;color: black'><b>The employee's entry preparation for "+appName+" has been completed.We have notified the employee to collect the computer.Please have your preparation ready.</b></font></br>http://localhost:8080/</br>\n" +
                "    </tr>\n";
        htmltext+=temptext;
        temptext=" </body>";
        htmltext+=temptext;



        MailSenderInfo mailInfo = new MailSenderInfo();
        mailInfo.setMailServerHost("mail.hand-china.com");
        mailInfo.setMailServerPort("25");
        mailInfo.setValidate(true);
        mailInfo.setUserName("wencheng.tang@hand-china.com");
        mailInfo.setPassword("A1478963250a");//您的邮箱密码
        mailInfo.setFromAddress("wencheng.tang@hand-china.com");
        //mailInfo.setToAddress(email);
        mailInfo.setToAddress("183349812@qq.com");
        mailInfo.setSubject("New Employee Onboarding Workflow");
        mailInfo.setContent(htmltext);
        //这个类主要来发送邮件
        // SimpleMailSender sms = new SimpleMailSender();
        //  sms.sendTextMail(mailInfo);//发送文体格式
        SimpleMailSender.sendHtmlMail(mailInfo);//发送html格式
    }

    public void informServiceDesk3(DelegateExecution delegateExecution){
        String htmltext="";
        String temptext="";
        String hrCode="";
        String name="";

        NeoApplication neoApplication =new NeoApplication();
        neoApplication.setNum(delegateExecution.getProcessInstanceBusinessKey());
        List<NeoApplication> neoApplications = new ArrayList<NeoApplication>();
        neoApplications= neoApplicationMapper.select(neoApplication);
        if(neoApplications.size()>0){
            hrCode = neoApplications.get(0).getUserCode();
        }
        Employee employee =new Employee();
        employee.setEmployeeCode(hrCode);
        if(employeeMapper.select(employee).size()>0){
            name =employeeMapper.select(employee).get(0).getName();
        }



        temptext= " <body>\n" +
                "    <tr>\n" +
                "        <font style='font-size: 17px;color: black'><b>Hello! Dear Service Desk:</b></font></br>\n" +
                "        &nbsp;&nbsp;&nbsp;&nbsp;<font style='font-size: 10px;color: black'><b>The New Employee Induction Application "+name+" initiated has been completed. Please update the asset information and click the link below to login HAP system for confirmation.</b></font></br>http://localhost:8080/</br>\n" +
                "    </tr>\n";
        htmltext+=temptext;
        temptext=" </body>";
        htmltext+=temptext;



        MailSenderInfo mailInfo = new MailSenderInfo();
        mailInfo.setMailServerHost("mail.hand-china.com");
        mailInfo.setMailServerPort("25");
        mailInfo.setValidate(true);
        mailInfo.setUserName("wencheng.tang@hand-china.com");
        mailInfo.setPassword("A1478963250a");//您的邮箱密码
        mailInfo.setFromAddress("wencheng.tang@hand-china.com");
        //mailInfo.setToAddress(email);
        mailInfo.setToAddress("183349812@qq.com");
        mailInfo.setSubject("New Employee Onboarding Workflow");
        mailInfo.setContent(htmltext);
        //这个类主要来发送邮件
        // SimpleMailSender sms = new SimpleMailSender();
        //  sms.sendTextMail(mailInfo);//发送文体格式
        SimpleMailSender.sendHtmlMail(mailInfo);//发送html格式
    }

    public void informAdministrator(DelegateExecution delegateExecution){
        String htmltext="";
        String temptext="";
        String hrCode="";
        String name="";
        // String email= "";

        NeoApplication neoApplication =new NeoApplication();
        neoApplication.setNum(delegateExecution.getProcessInstanceBusinessKey());
        List<NeoApplication> neoApplications = new ArrayList<NeoApplication>();
        neoApplications= neoApplicationMapper.select(neoApplication);
        if(neoApplications.size()>0){
            hrCode = neoApplications.get(0).getUserCode();
        }
        Employee employee =new Employee();
        employee.setEmployeeCode(hrCode);
        if(employeeMapper.select(employee).size()>0){
            name =employeeMapper.select(employee).get(0).getName();
            //  email=employeeMapper.select(employee).get(0).getEmail();
        }


        temptext= " <body>\n" +
                "    <tr>\n" +
                "        <font style='font-size: 17px;color: black'><b>Hello! Dear Administrator:</b></font></br>\n" +
                "        &nbsp;&nbsp;&nbsp;&nbsp;<font style='font-size: 10px;color: black'><b>"+name+" has initiated the New Employee Induction Application. Below is the application information. Please have your preparation ready and click the link below to login HAP system to input the relevant information.</b></font></br>http://localhost:8080/</br>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "        <table  align='left' style='border-collapse: collapse;'>\n" +
                "            <tr align='center' style='background-color:rgb(230,230,230)'> <td colspan='4'>New Employee Onboarding Application Form</td></tr>\n" +
                "            <tr align='center' style='background-color:rgb(242,242,242)'><td colspan='4'>Basic Information</td></tr>";
        htmltext+=temptext;


        for(int i=0;i<neoApplications.size();i++){
            if(i%2==0&&i==(neoApplications.size()-1)){
                temptext= " <tr align=\"center\"><td width='25%' style='border: #e7ecf1 solid 1px;padding:8px;font-size:12px;'>"+neoApplications.get(i).getName()+"</td><td width='25%' style='border: #e7ecf1 solid 1px;padding:8px;font-size:12px;'>"+neoApplications.get(i).getValue()+"</td><td width='25%' style='border: #e7ecf1 solid 1px;padding:8px;font-size:12px;'></td><td width='25%' style='border: #e7ecf1 solid 1px;padding:8px;font-size:12px;'></td></tr>";
                htmltext+=temptext;
            }else if(i%2==0){
                temptext ="<tr align=\"center\"><td width='25%' style='border: #e7ecf1 solid 1px;padding:8px;font-size:12px;'>" + neoApplications.get(i).getName() + "</td><td width='25%' style='border: #e7ecf1 solid 1px;padding:8px;font-size:12px;'>" + neoApplications.get(i).getValue() + "</td>";
                htmltext+=temptext;
            }else if(i%2==1){
                temptext="<td width='25%' style='border: #e7ecf1 solid 1px;padding:8px;font-size:12px;'>"+neoApplications.get(i).getName()+"</td><td width='25%' style='border: #e7ecf1 solid 1px;padding:8px;font-size:12px;'>"+neoApplications.get(i).getValue()+"</td></tr>";
                htmltext+=temptext;
            }
        }
        temptext=" </table>\n" +
                "    </tr>\n" +
                "    </body>";
        htmltext+=temptext;



        MailSenderInfo mailInfo = new MailSenderInfo();
        mailInfo.setMailServerHost("mail.hand-china.com");
        mailInfo.setMailServerPort("25");
        mailInfo.setValidate(true);
        mailInfo.setUserName("wencheng.tang@hand-china.com");
        mailInfo.setPassword("A1478963250a");//您的邮箱密码
        mailInfo.setFromAddress("wencheng.tang@hand-china.com");
        //mailInfo.setToAddress(email);
        mailInfo.setToAddress("183349812@qq.com");
        mailInfo.setSubject("New Employee Onboarding Workflow");
        mailInfo.setContent(htmltext);
        //这个类主要来发送邮件
        // SimpleMailSender sms = new SimpleMailSender();
        //  sms.sendTextMail(mailInfo);//发送文体格式
        SimpleMailSender.sendHtmlMail(mailInfo);//发送html格式
    }

    public void informHR(DelegateExecution delegateExecution){
        String htmltext="";
        String temptext="";
        String hrCode="";
        String name="";
        // String email= "";
        String app="";
        String appName="";

        NeoApplication neoApplication =new NeoApplication();
        neoApplication.setNum(delegateExecution.getProcessInstanceBusinessKey());
        List<NeoApplication> neoApplications = new ArrayList<NeoApplication>();
        neoApplications= neoApplicationMapper.select(neoApplication);
        if(neoApplications.size()>0){
            hrCode = neoApplications.get(0).getUserCode();
            app =neoApplications.get(0).getApplicant();
        }
        Employee employee =new Employee();
        employee.setEmployeeCode(hrCode);
        if(employeeMapper.select(employee).size()>0){
            name =employeeMapper.select(employee).get(0).getName();
            //  email=employeeMapper.select(employee).get(0).getEmail();
        }
        employee.setEmployeeCode(app);
        if(employeeMapper.select(employee).size()>0){
            appName =employeeMapper.select(employee).get(0).getName();

        }


        temptext= " <body>\n" +
                "    <tr>\n" +
                "        <font style='font-size: 17px;color: black'><b>Hello! Dear "+name+":</b></font></br>\n" +
                "        &nbsp;&nbsp;&nbsp;&nbsp;<font style='font-size: 10px;color: black'><b>The employee's entry preparation for "+appName+" has been completed.  Below is the application information. Please lead the employee to the Service Desk to collect the computer .</b></font></br>http://localhost:8080/</br>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "        <table  align='left' style='border-collapse: collapse;'>\n" +
                "            <tr align='center' style='background-color:rgb(230,230,230)'> <td colspan='4'>New Employee Onboarding Application Form</td></tr>\n" +
                "            <tr align='center' style='background-color:rgb(242,242,242)'><td colspan='4' style=\"padding: 10px 10px\">Basic Information</td></tr>";
        htmltext+=temptext;


        for(int i=0;i<neoApplications.size();i++){
            if(i%2==0&&i==(neoApplications.size()-1)){
                temptext= " <tr align=\"center\"><td width='25%' style='border: #e7ecf1 solid 1px;padding:8px;font-size:12px;'>"+neoApplications.get(i).getName()+"</td><td width='25%' style='border: #e7ecf1 solid 1px;padding:8px;font-size:12px;'>"+neoApplications.get(i).getValue()+"</td><td width='25%' style='border: #e7ecf1 solid 1px;padding:8px;font-size:12px;'></td><td width='25%' style='border: #e7ecf1 solid 1px;padding:8px;font-size:12px;'></td></tr>";
                htmltext+=temptext;
            }else if(i%2==0){
                temptext ="<tr align=\"center\"><td width='25%' style='border: #e7ecf1 solid 1px;padding:8px;font-size:12px;'>" + neoApplications.get(i).getName() + "</td><td width='25%' style='border: #e7ecf1 solid 1px;padding:8px;font-size:12px;'>" + neoApplications.get(i).getValue() + "</td>";
                htmltext+=temptext;
            }else if(i%2==1){
                temptext="<td width='25%' style='border: #e7ecf1 solid 1px;padding:8px;font-size:12px;'>"+neoApplications.get(i).getName()+"</td><td width='25%' style='border: #e7ecf1 solid 1px;padding:8px;font-size:12px;'>"+neoApplications.get(i).getValue()+"</td></tr>";
                htmltext+=temptext;
            }
        }
        temptext="<tr align=\"left\"><td colspan='4'>Check List</td></tr>";
        htmltext+=temptext;
        NeoCheck neoCheck =new NeoCheck();
        neoCheck.setcNum(delegateExecution.getProcessInstanceBusinessKey());
        List<NeoCheck> neoChecks = new ArrayList<NeoCheck>();
        neoChecks= neoCheckMapper.select(neoCheck);
        int flag =0;
        for(NeoCheck n:neoChecks){
            if(("Email Account").equals(n.getcName())){
                if(flag==0){
                    temptext="<tr align=\"center\"><td width='25%' style='border: #e7ecf1 solid 1px;padding:8px;font-size:12px;'>Email Account</td><td width='25%' style='border: #e7ecf1 solid 1px;padding:8px;font-size:12px;'>"+n.getcValue()+"</td>";
                    htmltext+=temptext;
                    flag=1;
                }else{
                    temptext="<td width='25%' style='border: #e7ecf1 solid 1px;padding:8px;font-size:12px;'>Email Account</td><td width='25%' style='border: #e7ecf1 solid 1px;padding:8px;font-size:12px;'>"+n.getcValue()+"</td></tr>";
                    htmltext+=temptext;
                }
            }
            if(("Extension Number").equals(n.getcName())){
                if(flag==0){
                    temptext="<tr align=\"center\"><td width='25%' style='border: #e7ecf1 solid 1px;padding:8px;font-size:12px;'>Extension Number</td><td width='25%' style='border: #e7ecf1 solid 1px;padding:8px;font-size:12px;'>"+n.getcValue()+"</td>";
                    htmltext+=temptext;
                    flag=1;
                }else{
                    temptext="<td width='25%' style='border: #e7ecf1 solid 1px;padding:8px;font-size:12px;'>Extension Number</td><td width='25%' style='border: #e7ecf1 solid 1px;padding:8px;font-size:12px;'>"+n.getcValue()+"</td></tr>";
                    htmltext+=temptext;
                }
            }
        }

        temptext=" </table>\n" +
                "    </tr>\n" +
                "    </body>";
        htmltext+=temptext;



        MailSenderInfo mailInfo = new MailSenderInfo();
        mailInfo.setMailServerHost("mail.hand-china.com");
        mailInfo.setMailServerPort("25");
        mailInfo.setValidate(true);
        mailInfo.setUserName("wencheng.tang@hand-china.com");
        mailInfo.setPassword("A1478963250a");//您的邮箱密码
        mailInfo.setFromAddress("wencheng.tang@hand-china.com");
        //mailInfo.setToAddress(email);
        mailInfo.setToAddress("183349812@qq.com");
        mailInfo.setSubject("New Employee Onboarding Workflow");
        mailInfo.setContent(htmltext);
        //这个类主要来发送邮件
        // SimpleMailSender sms = new SimpleMailSender();
        //  sms.sendTextMail(mailInfo);//发送文体格式
        SimpleMailSender.sendHtmlMail(mailInfo);//发送html格式
    }

    public void informDelegate(DelegateExecution delegateExecution){
        String htmltext="";
        String temptext="";
        String receiptor="";
        String name="";
        // String email= "";
        String appName="";
        String app="";

        NeoApplication neoApplication =new NeoApplication();
        neoApplication.setNum(delegateExecution.getProcessInstanceBusinessKey());
        List<NeoApplication> neoApplications = new ArrayList<NeoApplication>();
        neoApplications= neoApplicationMapper.select(neoApplication);
        if(neoApplications.size()>0){
            receiptor = neoApplications.get(0).getReceiptor();
            app =neoApplications.get(0).getApplicant();
        }
        Employee employee =new Employee();
        employee.setEmployeeCode(receiptor);
        if(employeeMapper.select(employee).size()>0){
            name =employeeMapper.select(employee).get(0).getName();
        }
        employee.setEmployeeCode(app);
        if(employeeMapper.select(employee).size()>0){
            appName =employeeMapper.select(employee).get(0).getName();

        }


        temptext= " <body>\n" +
                "    <tr>\n" +
                "        <font style='font-size: 17px;color: black'><b>Hello! Dear "+name+":</b></font></br>\n" +
                "        &nbsp;&nbsp;&nbsp;&nbsp;<font style='font-size: 10px;color: black'><b>You have collected the computer for "+appName+".  Please click the link below to login HAP system for confirmation .</b></font></br>http://localhost:8080/</br>\n" +
                "    </tr>\n";
        htmltext+=temptext;
        temptext=" </body>";
        htmltext+=temptext;



        MailSenderInfo mailInfo = new MailSenderInfo();
        mailInfo.setMailServerHost("mail.hand-china.com");
        mailInfo.setMailServerPort("25");
        mailInfo.setValidate(true);
        mailInfo.setUserName("wencheng.tang@hand-china.com");
        mailInfo.setPassword("A1478963250a");//您的邮箱密码
        mailInfo.setFromAddress("wencheng.tang@hand-china.com");
        //mailInfo.setToAddress(email);
        mailInfo.setToAddress("183349812@qq.com");
        mailInfo.setSubject("New Employee Onboarding Workflow");
        mailInfo.setContent(htmltext);
        //这个类主要来发送邮件
        // SimpleMailSender sms = new SimpleMailSender();
        //  sms.sendTextMail(mailInfo);//发送文体格式
        SimpleMailSender.sendHtmlMail(mailInfo);//发送html格式
    }

    public void informApplicant(DelegateExecution delegateExecution) {
        String htmltext="";
        String temptext="";
        String receiptor="";
        String name="";
        // String email= "";
        String appName="";
        String app="";

        NeoApplication neoApplication =new NeoApplication();
        neoApplication.setNum(delegateExecution.getProcessInstanceBusinessKey());
        List<NeoApplication> neoApplications = new ArrayList<NeoApplication>();
        neoApplications= neoApplicationMapper.select(neoApplication);
        if(neoApplications.size()>0){
            receiptor = neoApplications.get(0).getReceiptor();
            app =neoApplications.get(0).getApplicant();
        }
        Employee employee =new Employee();
        employee.setEmployeeCode(receiptor);
        if(employeeMapper.select(employee).size()>0){
            name =employeeMapper.select(employee).get(0).getName();
        }
        employee.setEmployeeCode(app);
        if(employeeMapper.select(employee).size()>0){
            appName =employeeMapper.select(employee).get(0).getName();

        }


        temptext= " <body>\n" +
                "    <tr>\n" +
                "        <font style='font-size: 17px;color: black'><b>Hello! Dear "+appName+":</b></font></br>\n" +
                "        &nbsp;&nbsp;&nbsp;&nbsp;<font style='font-size: 10px;color: black'><b>"+name+" have collected the computer for you. Please make sure you get the computer from "+name+" and then click the link below to login HAP system for confirmation .</b></font></br>http://localhost:8080/</br>\n" +
                "    </tr>\n";
        htmltext+=temptext;
        temptext=" </body>";
        htmltext+=temptext;



        MailSenderInfo mailInfo = new MailSenderInfo();
        mailInfo.setMailServerHost("mail.hand-china.com");
        mailInfo.setMailServerPort("25");
        mailInfo.setValidate(true);
        mailInfo.setUserName("wencheng.tang@hand-china.com");
        mailInfo.setPassword("A1478963250a");//您的邮箱密码
        mailInfo.setFromAddress("wencheng.tang@hand-china.com");
        //mailInfo.setToAddress(email);
        mailInfo.setToAddress("183349812@qq.com");
        mailInfo.setSubject("New Employee Onboarding Workflow");
        mailInfo.setContent(htmltext);
        //这个类主要来发送邮件
        // SimpleMailSender sms = new SimpleMailSender();
        //  sms.sendTextMail(mailInfo);//发送文体格式
        SimpleMailSender.sendHtmlMail(mailInfo);//发送html格式
    }


    @Override
    public String getBeanName() {
        return "NeoMailSender";
    }

}


       //   ${NeoMailSender.informApplicant(execution)}
*/
