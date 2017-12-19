package hbidemo.core.test1.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import hbidemo.core.test1.dto.Test;
import hbidemo.core.test1.service.ITestService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class TestServiceImpl extends BaseServiceImpl<Test> implements ITestService{

}