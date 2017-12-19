package hbidemo.core.NewEmployeeOnboarding.controllers;

import hbidemo.core.NewEmployeeOnboarding.dto.NeoSet;
import hbidemo.core.NewEmployeeOnboarding.service.INeoSetService;
import org.springframework.stereotype.Controller;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import org.springframework.validation.BindingResult;
import java.util.List;

    @Controller
    public class NeoSetController extends BaseController{

    @Autowired
    private INeoSetService service;


    @RequestMapping(value = "/lvmh/neo/set/query")
    @ResponseBody
    public ResponseData query(NeoSet dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                              @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.select(requestContext,dto,page,pageSize));
    }

        @RequestMapping(value = "/lvmh/neo/set/app/query")
        @ResponseBody
        public ResponseData appquery(NeoSet dto, HttpServletRequest request) {
            IRequest requestContext = createRequestContext(request);
            return new ResponseData(service.selectApp(requestContext,dto));
        }
        @RequestMapping(value = "/lvmh/neo/set/it/query")
        @ResponseBody
        public ResponseData itquery(NeoSet dto, HttpServletRequest request) {
            IRequest requestContext = createRequestContext(request);
            return new ResponseData(service.selectIt(requestContext,dto));
        }
        @RequestMapping(value = "/lvmh/neo/set/ad/query")
        @ResponseBody
        public ResponseData adquery(NeoSet dto, HttpServletRequest request) {
            IRequest requestContext = createRequestContext(request);
            return new ResponseData(service.selectAd(requestContext,dto));
        }
    @RequestMapping(value = "/lvmh/neo/set/submit")
    @ResponseBody
    public ResponseData update(@RequestBody List<NeoSet> dto, BindingResult result, HttpServletRequest request){
        getValidator().validate(dto, result);
        if (result.hasErrors()) {
        ResponseData responseData = new ResponseData(false);
        responseData.setMessage(getErrorMessage(result, request));
        return responseData;
        }
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/lvmh/neo/set/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request,@RequestBody List<NeoSet> dto){
        service.batchDelete(dto);
        return new ResponseData();
    }
    }