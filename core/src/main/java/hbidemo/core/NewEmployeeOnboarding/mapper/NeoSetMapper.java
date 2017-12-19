package hbidemo.core.NewEmployeeOnboarding.mapper;


import com.hand.hap.mybatis.common.Mapper;
import hbidemo.core.NewEmployeeOnboarding.dto.NeoSet;

import java.util.List;

public interface NeoSetMapper extends Mapper<NeoSet> {

    List<NeoSet> select(NeoSet neoSet);
    List<NeoSet> selectApp(NeoSet neoSet);
    List<NeoSet> selectIt(NeoSet neoSet);
    List<NeoSet> selectAd(NeoSet neoSet);
}