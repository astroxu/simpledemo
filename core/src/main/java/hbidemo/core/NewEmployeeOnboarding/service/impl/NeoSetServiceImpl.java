package hbidemo.core.NewEmployeeOnboarding.service.impl;

import com.github.pagehelper.PageHelper;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import hbidemo.core.NewEmployeeOnboarding.dto.NeoSet;
import hbidemo.core.NewEmployeeOnboarding.mapper.NeoSetMapper;
import hbidemo.core.NewEmployeeOnboarding.service.INeoSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class NeoSetServiceImpl extends BaseServiceImpl<NeoSet> implements INeoSetService {

    @Autowired
    NeoSetMapper mapper;

    @Override
    public List<NeoSet> select(IRequest request, NeoSet dto, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<NeoSet> dtolist = new ArrayList<NeoSet>();
        dtolist = mapper.select(dto);
        return dtolist;
    }

    @Override
    public List<NeoSet> selectApp(IRequest iRequest, NeoSet dto) {
        List<NeoSet> dtolist = new ArrayList<NeoSet>();
        dtolist = mapper.selectApp(dto);
        return dtolist;
    }

    @Override
    public List<NeoSet> selectIt(IRequest iRequest, NeoSet dto) {
        List<NeoSet> dtolist = new ArrayList<NeoSet>();
        dtolist = mapper.selectIt(dto);
        return dtolist;
    }

    @Override
    public List<NeoSet> selectAd(IRequest iRequest, NeoSet dto) {
        List<NeoSet> dtolist = new ArrayList<NeoSet>();
        dtolist = mapper.selectAd(dto);
        return dtolist;
    }
}