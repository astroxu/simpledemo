package hbidemo.core.NewEmployeeOnboarding.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.core.annotation.StdWho;
import com.hand.hap.system.service.IBaseService;
import hbidemo.core.NewEmployeeOnboarding.dto.NeoApplication;

import java.util.List;

public interface INeoApplicationService extends IBaseService<NeoApplication>, ProxySelf<INeoApplicationService>{

    List<NeoApplication> startProcess(IRequest iRequest, @StdWho List<NeoApplication> list);

    List<NeoApplication> selectApplicant(IRequest iRequest, NeoApplication neoApplication, int i, int i1);
}