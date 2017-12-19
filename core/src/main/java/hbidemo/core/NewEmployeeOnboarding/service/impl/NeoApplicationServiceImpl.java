package hbidemo.core.NewEmployeeOnboarding.service.impl;

import com.hand.hap.activiti.service.IActivitiService;
import com.hand.hap.core.IRequest;
import com.hand.hap.core.annotation.StdWho;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import hbidemo.core.NewEmployeeOnboarding.dto.NeoApplication;
import hbidemo.core.NewEmployeeOnboarding.mapper.NeoApplicationMapper;
import hbidemo.core.NewEmployeeOnboarding.service.INeoApplicationService;
import org.activiti.rest.service.api.runtime.process.ProcessInstanceCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional(rollbackFor = Exception.class)
public class NeoApplicationServiceImpl extends BaseServiceImpl<NeoApplication> implements INeoApplicationService {
    @Autowired
    NeoApplicationMapper mapper;
    @Autowired
    private IActivitiService activitiService;     /*工作流对象*/

    private final String processDefinitionKey="NEO"; /*工作流名称*/

    @Override
    public List<NeoApplication> startProcess(IRequest request, @StdWho List<NeoApplication> list) {
        /*保存　NeoApplication */
        for(NeoApplication app:list){
            app.setUserCode(request.getEmployeeCode());
            mapper.insertSelective(app);
        }

       ProcessInstanceCreateRequest instanceCreateRequest = new ProcessInstanceCreateRequest();    //*新建进程*//*
        instanceCreateRequest.setBusinessKey(String.valueOf(list.get(0).getNum()));                 //*设置businessKey*//*
        instanceCreateRequest.setProcessDefinitionKey(processDefinitionKey);                        //*获取进程名称*//*

        /* //添加流程变量
        List<RestVariable> variables = new ArrayList<>();
        RestVariable variable = new RestVariable();
        variable.setName("price");
        variable.setType("string");
        variable.setValue(String.valueOf("123"));

        variables.add(variable);
        instanceCreateRequest.setVariables(variables);*/

        activitiService.startProcess(request, instanceCreateRequest);
        return list;
    }


    @Override
    public List<NeoApplication> select(IRequest request, NeoApplication dto, int pageNum, int pageSize) {
        List<NeoApplication> dtolist = new ArrayList<NeoApplication>();
        dtolist = mapper.select(dto);
        return dtolist;
    }

   @Override
    public List<NeoApplication> selectApplicant(IRequest request, NeoApplication dto, int pageNum, int pageSize) {
        List<NeoApplication> dtolist = new ArrayList<NeoApplication>();
        dtolist = mapper.selectApplicant(dto);
        dtolist.get(0).getUserName();
        dtolist.get(0).getEmail();
        return dtolist;
    }


    @Override
    public List<NeoApplication> batchUpdate(IRequest request, List<NeoApplication> list) {

        List<NeoApplication> dtolist = new ArrayList<NeoApplication>();
        dtolist = mapper.selectAll();
        for(NeoApplication app:dtolist){
            if(list.get(0).getNum().equals(app.getNum())){
                app.setReceiptor(list.get(0).getReceiptor());
                mapper.updateByPrimaryKey(app);
            }
        }
        return list;
    }




}