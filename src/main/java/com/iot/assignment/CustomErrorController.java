package com.iot.assignment;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
public class CustomErrorController implements ErrorController {

	private static final String ERROR_404 = "error-404";
	private static final String ERROR_500 = "error-500";

	@GetMapping("/error")
	public String errorPage(HttpServletRequest request) {
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		String requestedURI = (String) request.getAttribute(RequestDispatcher.FORWARD_REQUEST_URI);
		log.info("Status is " + status + " for url " + requestedURI);
		String root = "/error/";
		if (status == null) {
			return root + ERROR_404;
		}
		int statusCode = Integer.parseInt(status.toString());
		if (statusCode == HttpStatus.NOT_FOUND.value()) {
			return root + ERROR_404;
		} else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
			return root + ERROR_500;
		}
		return root + "error";
	}

	@Override
	public String getErrorPath() {
		return "/error";
	}
}
