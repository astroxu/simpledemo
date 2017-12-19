package hbidemo.core.NewEmployeeOnboarding.controllers;

import hbidemo.core.NewEmployeeOnboarding.dto.Demo;
import hbidemo.core.NewEmployeeOnboarding.dto.NeoCheck;
import hbidemo.core.NewEmployeeOnboarding.service.INeoCheckService;
import net.sf.json.JSONArray;
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

import java.util.ArrayList;
import java.util.List;

@Controller
public class NeoCheckController extends BaseController {

    @Autowired
    private INeoCheckService service;


    @RequestMapping(value = "/lvmh/neo/check/query")
    @ResponseBody
    public ResponseData query(NeoCheck dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                              @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.select(requestContext, dto, page, pageSize));
    }

    @RequestMapping(value = "/lvmh/neo/check/submit")
    @ResponseBody
    public ResponseData update(@RequestBody List<NeoCheck> dto, BindingResult result, HttpServletRequest request) {
        getValidator().validate(dto, result);
        if (result.hasErrors()) {
            ResponseData responseData = new ResponseData(false);
            responseData.setMessage(getErrorMessage(result, request));
            return responseData;
        }
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/lvmh/neo/check/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request, @RequestBody List<NeoCheck> dto) {
        service.batchDelete(dto);
        return new ResponseData();
    }


    @RequestMapping(value = "/lvmh/neo/check/submit/test")
    @ResponseBody
    public ResponseData updatetest(@RequestParam("num") String num, @RequestParam("dtos") String dto, HttpServletRequest request) {
        /*新建类 Demo 接收 dtos*/
        JSONArray jsonline = JSONArray.fromObject(dto);
        List<Demo> demoList = (List<Demo>) jsonline.toCollection(jsonline, Demo.class);
        List<NeoCheck> CheckList = new ArrayList<NeoCheck>();

        for (Demo demo : demoList) {
            NeoCheck check = new NeoCheck();
            check.setcName(demo.getName());
            check.setcValue(demo.getValue());
            check.setcNum(num);
            CheckList.add(check);
        }

        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, CheckList));
    }
}