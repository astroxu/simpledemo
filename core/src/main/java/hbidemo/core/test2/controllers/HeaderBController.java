package hbidemo.core.test2.controllers;

import com.fasterxml.jackson.databind.JavaType;
import com.hand.hap.excel.dto.ColumnInfo;
import com.hand.hap.excel.dto.ExportConfig;
import org.springframework.stereotype.Controller;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.dto.ResponseData;
import hbidemo.core.test2.dto.HeaderB;
import hbidemo.core.test2.service.IHeaderBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindingResult;

import java.io.IOException;
import java.util.List;
import java.util.function.Function;

/*
* 头行结构
*
* */
@Controller
    public class HeaderBController extends BaseController{

    @Autowired
    private IHeaderBService service;

    //excel生成
   /* @RequestMapping(value = "/sales/header/export")
    public void createXLSForStudent(HttpServletRequest request, @RequestParam String config,HttpServletResponse httpServletResponse) {
        IRequest requestContext = createRequestContext(request);
        try {
            JavaType type = objectMapper.getTypeFactory().constructParametrizedType(ExportConfig.class,
                    ExportConfig.class, HeaderB.class, ColumnInfo.class);
            ExportConfig<Function, ColumnInfo> exportConfig = objectMapper.readValue(config, type);
            service.exportAndDownloadExcel("com.hand.hap.system.mapper.StudentMapper.findStudent",
                    exportConfig, request, httpServletResponse, requestContext);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
*/


    @RequestMapping(value = "/sales/header/query")
    @ResponseBody
    public ResponseData query(HeaderB dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.select(requestContext,dto,page,pageSize));
    }

    @RequestMapping(value = "/sales/header/submit")
    @ResponseBody
    public ResponseData update(@RequestBody List<HeaderB> dto, BindingResult result, HttpServletRequest request){
        getValidator().validate(dto, result);
        if (result.hasErrors()) {
        ResponseData responseData = new ResponseData(false);
        responseData.setMessage(getErrorMessage(result, request));
        return responseData;
        }
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/sales/header/remove")
    public ResponseData removeSaleHeader(@RequestBody List<HeaderB> dto){
        service.salesHeaderDelete(dto);
        return new ResponseData(dto);
    }
}
