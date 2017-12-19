package hbidemo.core.test2.controllers;

import org.springframework.stereotype.Controller;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.dto.ResponseData;
import hbidemo.core.test2.dto.Lines;
import hbidemo.core.test2.service.ILinesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import org.springframework.validation.BindingResult;
import java.util.List;

    @Controller
    public class LinesController extends BaseController{

    @Autowired
    private ILinesService service;


    @RequestMapping(value = "/sales/lines/query")
    @ResponseBody
    public ResponseData query(Lines dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.select(requestContext,dto,page,pageSize));
    }

        @RequestMapping(value = "/sales/lines/queryByHeaderId")
        @ResponseBody
        public ResponseData selectSalesLinesByHeaderId(Lines dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                                                       @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
            IRequest requestContext = createRequestContext(request);
            return new ResponseData(service.selectSalesLineByHeaderId(requestContext, dto, page, pageSize));
        }


        @RequestMapping(value = "/sales/lines/submit")
    @ResponseBody
    public ResponseData update(@RequestBody List<Lines> dto, BindingResult result, HttpServletRequest request){
        //验证数据是否正常
        getValidator().validate(dto, result);
        if (result.hasErrors()) {
        ResponseData responseData = new ResponseData(false);
        responseData.setMessage(getErrorMessage(result, request));
        return responseData;
        }
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/sales/lines/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request,@RequestBody List<Lines> dto){
        service.batchDelete(dto);
        return new ResponseData();
    }
    }