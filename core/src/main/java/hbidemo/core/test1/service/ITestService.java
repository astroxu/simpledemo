package hbidemo.core.test1.service;

import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import hbidemo.core.test1.dto.Test;

public interface ITestService extends IBaseService<Test>, ProxySelf<ITestService>{

}