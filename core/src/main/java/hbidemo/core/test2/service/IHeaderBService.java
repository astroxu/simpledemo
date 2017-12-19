package hbidemo.core.test2.service;

import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import hbidemo.core.test2.dto.HeaderB;

import java.util.List;

public interface IHeaderBService extends IBaseService<HeaderB>, ProxySelf<IHeaderBService>{
    boolean salesHeaderDelete(List<HeaderB> Headers);
}