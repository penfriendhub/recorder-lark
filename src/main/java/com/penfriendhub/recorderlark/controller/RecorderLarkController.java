package com.penfriendhub.recorderlark.controller;

import com.lark.oapi.sdk.servlet.ext.ServletAdapter;
import com.penfriendhub.recorderlark.service.RecorderLarkMessageService;
import com.penfriendhub.recorderlark.service.RecorderLarkService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author unickcheng
 * @since 2023-03-18
 */

@RestController
@RequestMapping("lark")
public class RecorderLarkController {
    @Resource
    private RecorderLarkService recorderLarkService;
    @Resource
    private RecorderLarkMessageService larkMessageService;
    @Resource
    private ServletAdapter servletAdapter;

    @PostMapping("event")
    public void event(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        servletAdapter.handleEvent(request, response, larkMessageService.getEventDispatcher());
    }

    // TODO Return all data temporarily
    @GetMapping("page/{token}")
    public Object page (@PathVariable String token) {
        return recorderLarkService.page(token);
    }

    // TODO Exporting excel data
    @PostMapping("export")
    public Object export () {
        return "Waiting ...";
    }

    //TODO Import existing excel data
    @PostMapping("load")
    public void load () {
    }
}
