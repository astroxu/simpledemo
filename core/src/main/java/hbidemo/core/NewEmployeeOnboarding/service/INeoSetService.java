package hbidemo.core.NewEmployeeOnboarding.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import hbidemo.core.NewEmployeeOnboarding.dto.NeoSet;

import java.util.List;

public interface INeoSetService extends IBaseService<NeoSet>, ProxySelf<INeoSetService>{

    List<NeoSet> selectApp(IRequest iRequest, NeoSet neoSet);

    List<NeoSet> selectIt(IRequest iRequest, NeoSet neoSet);

    List<NeoSet> selectAd(IRequest iRequest, NeoSet neoSet);

    @Override
    List<NeoSet> select(IRequest iRequest, NeoSet neoSet, int i, int i1);
}