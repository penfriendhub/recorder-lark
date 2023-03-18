package com.penfriendhub.recorderlark.controller;

import com.lark.oapi.sdk.servlet.ext.ServletAdapter;
import com.penfriendhub.recorderlark.service.RecorderLarkMessageService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    private RecorderLarkMessageService larkMessageService;
    @Resource
    private ServletAdapter servletAdapter;

    @PostMapping("event")
    public void event(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        servletAdapter.handleEvent(request, response, larkMessageService.getEventDispatcher());
    }

    // TODO
    @PostMapping("page")
    public Object page () {
        return "Waiting ...";
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
