package hbidemo.core.NewEmployeeOnboarding.mapper;


import com.hand.hap.mybatis.common.Mapper;
import hbidemo.core.NewEmployeeOnboarding.dto.NeoCheck;

import java.util.List;

public interface NeoCheckMapper extends Mapper<NeoCheck> {
    @Override
    List<NeoCheck> select(NeoCheck neoApplication);
}