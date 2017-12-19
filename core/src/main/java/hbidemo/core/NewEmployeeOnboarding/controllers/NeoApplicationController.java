package hbidemo.core.NewEmployeeOnboarding.controllers;

import hbidemo.core.NewEmployeeOnboarding.dto.Demo;
import hbidemo.core.NewEmployeeOnboarding.dto.NeoApplication;
import hbidemo.core.NewEmployeeOnboarding.service.INeoApplicationService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

    @Controller
    public class NeoApplicationController extends BaseController{

    @Autowired
    private INeoApplicationService service;


    @RequestMapping(value = "/lvmh/neo/application/query")
    @ResponseBody
    public ResponseData query(NeoApplication dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                              @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.select(requestContext,dto,page,pageSize));
    }

@RequestMapping(value = "/lvmh/neo/applicant/query")
    @ResponseBody
    public ResponseData selectApplicant(NeoApplication dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.selectApplicant(requestContext,dto,page,pageSize));
    }

    @RequestMapping(value = "/lvmh/neo/application/submit")
    @ResponseBody
    public ResponseData update(@RequestBody List<NeoApplication> dto, BindingResult result, HttpServletRequest request){
        getValidator().validate(dto, result);
        if (result.hasErrors()) {
        ResponseData responseData = new ResponseData(false);
        responseData.setMessage(getErrorMessage(result, request));
        return responseData;
        }
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/lvmh/neo/application/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request,@RequestBody List<NeoApplication> dto){
        service.batchDelete(dto);
        return new ResponseData();
    }


        @RequestMapping(value = "/lvmh/neo/application/submit/test")
        @ResponseBody
        public ResponseData test(@RequestParam("dtos")String dto,HttpServletRequest request){

             /*随机生成序号*/
            SerialNumberTool ss = new SerialNumberTool();
            String str = "TZ" + new SimpleDateFormat("yyyyMMddHHmm").format(new Date());
            String noticer = ss.getInstance().generaterNextNumber(4);
            String num = str + noticer;

            /*新建类 Demo 接收 dtos*/
            JSONArray jsonline= JSONArray.fromObject(dto);
            List<Demo> demoList = (List<Demo>)  jsonline.toCollection(jsonline,Demo.class);
            List<NeoApplication> AppList = new ArrayList<NeoApplication>();
            String value = "";
            for(Demo demo:demoList){
                NeoApplication app = new NeoApplication();
                app.setName(demo.getName());
                app.setValue(demo.getValue());
                app.setNum(num);
                if("Employee ID".equals(app.getName())){
                    value = app.getValue();
                }
                app.setApplicant(value);
                AppList.add(app);
            }

            IRequest requestCtx = createRequestContext(request);
            return new ResponseData(service.startProcess(requestCtx, AppList));
        }


        @RequestMapping(value = "/lvmh/neo/receive/submit/test")
        @ResponseBody
        public ResponseData updatetest(@RequestParam("demo") String demo,@RequestParam("num") String num,HttpServletRequest request){

            JSONObject jsonObject= JSONObject.fromObject(demo);
            NeoApplication app=(NeoApplication) jsonObject.toBean(jsonObject,NeoApplication.class);
            List<NeoApplication> AppList = new ArrayList<NeoApplication>();

            app.setNum(num);
            app.getReceiptor();

            AppList.add(app);

            IRequest requestCtx = createRequestContext(request);
            return new ResponseData(service.batchUpdate(requestCtx,AppList));
        }
    }