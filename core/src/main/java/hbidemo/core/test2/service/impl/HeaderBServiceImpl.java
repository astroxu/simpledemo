package hbidemo.core.test2.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import hbidemo.core.test2.dto.Lines;
import hbidemo.core.test2.mapper.HeaderBMapper;
import hbidemo.core.test2.mapper.LinesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import hbidemo.core.test2.dto.HeaderB;
import hbidemo.core.test2.service.IHeaderBService;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class HeaderBServiceImpl extends BaseServiceImpl<HeaderB> implements IHeaderBService{

    @Autowired
    private HeaderBMapper headerBMapper;
    @Autowired
    private LinesMapper linesMapper;

    @Override
    public boolean salesHeaderDelete(List<HeaderB> Headers) {
        //迭代头
        Iterator iterator = Headers.iterator();
        HeaderB Header;
        Lines lines = new Lines();
        while (iterator.hasNext()) {
            Header = (HeaderB) iterator.next();
            headerBMapper.deleteByPrimaryKey(Header);
            lines.setSalesHeaderId(Header.getSalesId());
            linesMapper.deleteSalesLineByHeaderId(lines);
        }
        return true;
    }
}