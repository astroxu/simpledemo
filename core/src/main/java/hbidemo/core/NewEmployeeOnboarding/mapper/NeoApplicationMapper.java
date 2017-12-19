package hbidemo.core.NewEmployeeOnboarding.mapper;


import com.hand.hap.mybatis.common.Mapper;
import hbidemo.core.NewEmployeeOnboarding.dto.NeoApplication;

import java.util.List;

public interface NeoApplicationMapper extends Mapper<NeoApplication> {
    @Override
    List<NeoApplication> select(NeoApplication neoApplication);

    List<NeoApplication> selectApplicant(NeoApplication neoApplication);

}