package hbidemo.core.test3.service.impl;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import hbidemo.core.test3.Exception.PictureException;
import hbidemo.core.test3.mapper.PictureMapper;
import hbidemo.core.test3.util.UploadUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import hbidemo.core.test3.dto.Picture;
import hbidemo.core.test3.service.IPictureService;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class PictureServiceImpl extends BaseServiceImpl<Picture> implements IPictureService{
    private static Logger logger = LoggerFactory.getLogger(PictureServiceImpl.class);
    //不做介绍
    @Autowired
    private PictureMapper pictureMapper;

    @Override
    public int insert(Picture picture) {
//数据存入log表


        pictureMapper.inserti(picture);
            logger.debug("picture插入成功");
        return 0;
    }

    @Override
    public Picture selectByPrimaryKey1(IRequest var1, Long picture1) throws Exception {
        Picture picture = pictureMapper.queryBypictureId(var1,picture1);
        logger.debug("picture查询成功");
        return picture;
    }



    @Override
    public int deleteByPrimaryKey1(IRequest requestContext, Picture picture1) throws Exception {
        Long pictureId1 = picture1.getPictureId();
        logger.debug("进入deleteByPrimaryKey1");
        logger.debug("pictureID1:" + pictureId1);

        Picture picture = pictureMapper.queryBypictureId(requestContext,pictureId1);
        logger.debug("getPicPath ： " + picture.getPicPath());
        if (picture != null) {
            this.pictureMapper.deleteBypictureId(requestContext,pictureId1);
        } else {
            picture.setPictureId(pictureId1);
        }
        logger.debug("picture得到的路径" + picture.getPicPath());
        UploadUtil.deleteFile(picture.getPicPath());
        logger.debug("picture删除成功");
        return 0;

    }

    @Override
    public int delete(IRequest requestContext, Long pictureId1) {
        Picture picture = pictureMapper.queryBypictureId(requestContext,pictureId1);
        if (picture != null) {
            this.pictureMapper.deleteBypictureId(requestContext,pictureId1);
        } else {
            picture.setPictureId(pictureId1);
        }

        UploadUtil.deleteFile(picture.getPicPath());
        return 0;
    }

    @Override
    public List<Picture> queryBySupplierId(IRequest var1, Long var2) throws Exception {
        List<Picture> picture = pictureMapper.queryBySupplierId(var1,var2);

       /* //数据存入log表
        for(Picture tokenlist : tokenList){

            TokenLog tokenLog = new TokenLog();

            tokenLog.setTokenid(tokenlist.getTokenid());
            tokenLog.setNodemd5(tokenlist.getNodemd5());
            tokenLog.setNodeid(tokenlist.getNodeid());
            tokenLog.setWfid(tokenlist.getWfid());
            tokenLog.setEmployeeid(tokenlist.getEmployeeid());
            tokenLog.setApprovalflag(Long.valueOf(tokenlist.getApprovalflag()));

            tokenLogMapper.insertTokenLog(tokenLog);
            logger.debug("tokenlog插入成功");
        }*/



        logger.debug("picture查询成功");
        return picture;
    }

}