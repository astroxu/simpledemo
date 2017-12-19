package hbidemo.core.test2.mapper;

import com.hand.hap.mybatis.common.Mapper;
import hbidemo.core.test2.dto.Lines;

import java.util.List;

public interface LinesMapper extends Mapper<Lines>{
    //头行结构中根据头id查行
    List<Lines> selectSalesLineByHeaderId(Lines lines);
    //通过头id删除行
    boolean deleteSalesLineByHeaderId(Lines lines);
}