package hbidemo.core.NewEmployeeOnboarding.service.impl;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import hbidemo.core.NewEmployeeOnboarding.dto.NeoCheckAd;
import hbidemo.core.NewEmployeeOnboarding.mapper.NeoCheckAdMapper;
import hbidemo.core.NewEmployeeOnboarding.service.INeoCheckAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class NeoCheckAdServiceImpl extends BaseServiceImpl<NeoCheckAd> implements INeoCheckAdService {
    @Autowired
    NeoCheckAdMapper mapper;
    @Override
    public List<NeoCheckAd> batchUpdate(IRequest request, List<NeoCheckAd> list) {
        NeoCheckAd checkad = new NeoCheckAd();
        checkad.setNum(list.get(0).getNum());
        List<NeoCheckAd> checks = new ArrayList<NeoCheckAd>();
        checks = mapper.select(checkad);

        if(checks.size()>0){
            mapper.delete(checkad);
        }else if(checks.size()==0){
            System.out.println("insert");
        }else{
            System.out.println("error");
        }
        for(NeoCheckAd dto:list){
            mapper.insertSelective(dto);
        }
        return list;
    }
    @Override
    public List<NeoCheckAd> select(IRequest request, NeoCheckAd dto, int pageNum, int pageSize) {
        List<NeoCheckAd> dtolist = new ArrayList<NeoCheckAd>();
        dtolist = mapper.select(dto);
        return dtolist;
    }

}