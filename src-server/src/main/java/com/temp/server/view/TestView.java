package com.temp.server.view;

import com.temp.server.data.http.req.BaseReq;
import com.temp.server.data.http.resp.BaseResp;
import com.temp.server.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class TestView {
    private static final Logger LOG = LoggerFactory.getLogger(TestView.class);

    @Autowired
    private TestService mTestService;

    @RequestMapping(path = {"", "/index"}, method = RequestMethod.GET)
    public String handleIndex(HttpServletRequest request, HttpServletResponse response) {
        return "indexPage";
    }

    @RequestMapping(path = "/test", method = RequestMethod.GET)
    @ResponseBody
    public BaseResp hanldeTestGet(@RequestHeader("Accept-Encoding") String encoding,
                                  @RequestParam(required = false) String search) {
        BaseResp<String> resp = new BaseResp<>();
        resp.setCode(0);
        resp.setMsg("success");
        resp.setData(search == null ? System.currentTimeMillis() + "" : search);
        return resp;
    }

    @RequestMapping(path = "/test", method = RequestMethod.POST)
    @ResponseBody
    public BaseResp hanldeTestPost(@RequestBody BaseReq<String> body) {
        BaseResp<String> resp = new BaseResp<>();
        resp.setCode(0);
        resp.setMsg("success");
        resp.setData(body == null ? null : body.getData());
        return resp;
    }
}
