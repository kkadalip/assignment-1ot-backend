package com.iot.assignment;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
public class CustomErrorController implements ErrorController {

    private static final String PATH_ERROR = "/error";

    @RequestMapping(value = PATH_ERROR)
    public String errorPage(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        log.info("Status is " + status + " for url " + request.getRequestURL());
        String root = "/error/";
        if (status == null) {
            return root + "error-404";
        }
        int statusCode = Integer.parseInt(status.toString());
        if (statusCode == HttpStatus.NOT_FOUND.value()) {
            return root + "error-404";
        } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
            return root + "error-500";
        }
        return root + "error";
    }

    @Override
    public String getErrorPath() {
        return PATH_ERROR;
    }
}
