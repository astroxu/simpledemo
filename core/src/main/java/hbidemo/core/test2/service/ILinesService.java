package hbidemo.core.test2.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import hbidemo.core.test2.dto.Lines;

import java.util.List;

public interface ILinesService extends IBaseService<Lines>, ProxySelf<ILinesService>{
    List<Lines> selectSalesLineByHeaderId(IRequest requestContext, Lines lines, int page, int pagesize);
}