package hbidemo.core.test2.service.impl;

import com.github.pagehelper.PageHelper;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import hbidemo.core.test2.mapper.LinesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import hbidemo.core.test2.dto.Lines;
import hbidemo.core.test2.service.ILinesService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class LinesServiceImpl extends BaseServiceImpl<Lines> implements ILinesService{
    @Autowired
    private LinesMapper linesMapper;


    @Override
    public List<Lines> selectSalesLineByHeaderId(IRequest requestContext, Lines lines, int page, int pagesize) {
        PageHelper.startPage(page, pagesize);
        return linesMapper.selectSalesLineByHeaderId(lines);
    }
}