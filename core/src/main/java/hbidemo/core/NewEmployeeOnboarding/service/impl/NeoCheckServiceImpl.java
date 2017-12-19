package hbidemo.core.NewEmployeeOnboarding.service.impl;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import hbidemo.core.NewEmployeeOnboarding.dto.NeoCheck;
import hbidemo.core.NewEmployeeOnboarding.mapper.NeoCheckMapper;
import hbidemo.core.NewEmployeeOnboarding.service.INeoCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class NeoCheckServiceImpl extends BaseServiceImpl<NeoCheck> implements INeoCheckService {
    @Autowired
    NeoCheckMapper mapper;
    @Override
    public List<NeoCheck> batchUpdate(IRequest request, List<NeoCheck> list) {
        NeoCheck check = new NeoCheck();
        check.setcNum(list.get(0).getcNum());
        List<NeoCheck> checks = new ArrayList<NeoCheck>();
        checks = mapper.select(check);

        if(checks.size()>0){
                mapper.delete(check);
        }else if(checks.size()==0){
                System.out.println("insert");
        }else{
                System.out.println("error");
            }
        for(NeoCheck dto:list){
            mapper.insertSelective(dto);
        }

        return list;
    }

    @Override
    public List<NeoCheck> select(IRequest request, NeoCheck dto, int pageNum, int pageSize) {
        List<NeoCheck> dtolist = new ArrayList<NeoCheck>();
        dtolist = mapper.select(dto);
        return dtolist;
    }
}