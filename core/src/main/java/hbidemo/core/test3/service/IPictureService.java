package hbidemo.core.test3.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import hbidemo.core.test3.dto.Picture;

import java.util.List;

public interface IPictureService extends IBaseService<Picture>, ProxySelf<IPictureService>{
    int insert(Picture picture);
    Picture selectByPrimaryKey1(IRequest var1, Long var2) throws Exception;

    int delete(IRequest requestContext, Long pictureId1);
    int deleteByPrimaryKey1(IRequest var1, Picture var2) throws Exception;
    List<Picture> queryBySupplierId(IRequest var1, Long var2) throws Exception;
}