package hbidemo.core.NewEmployeeOnboarding.mapper;


import com.hand.hap.mybatis.common.Mapper;
import hbidemo.core.NewEmployeeOnboarding.dto.NeoCheckAd;

import java.util.List;

public interface NeoCheckAdMapper extends Mapper<NeoCheckAd> {
    @Override
    List<NeoCheckAd> select(NeoCheckAd neoApplication);
}