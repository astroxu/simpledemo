package hbidemo.core.test3.mapper;

import com.hand.hap.core.IRequest;
import com.hand.hap.mybatis.common.Mapper;
import hbidemo.core.test3.dto.Picture;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PictureMapper extends Mapper<Picture>{
    int inserti(Picture var2);
    Picture queryBypictureId(IRequest var1, @Param("var2") Long var2);

    int deleteBypictureId(IRequest var1, @Param("var2") Long var2);

    List<Picture> queryBySupplierId(IRequest var1, @Param("var2") Long var2);

   /* List<SysFile> selectFiles(IRequest var1, SysFile var2, int var3, int var4);

    SysFile insertFileAndAttach(IRequest var1, @StdWho Attachment var2, @StdWho SysFile var3);

    SysFile updateOrInsertFile(IRequest var1, @StdWho Attachment var2, @StdWho SysFile var3) throws UniqueFileMutiException;

    SysFile selectByPrimaryKey(IRequest var1, Long var2);


    List<SysFile> selectFilesByTypeAndKey(IRequest var1, String var2, Long var3);

    void removeFiles(IRequest var1, List<SysFile> var2);

    List<SysFile> selectByIds(IRequest var1, List<String> var2);

    SysFile deleteImage(IRequest var1, SysFile var2);

    void deletefiles(IRequest var1, SysFile var2);*/

}