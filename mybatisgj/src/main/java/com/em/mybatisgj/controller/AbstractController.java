package com.em.mybatisgj.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * AbstractController class
 *
 * @author Administrator
 * @date
 */
public abstract class AbstractController {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private HttpServletRequest request;

    protected String getContextPath() {
        return request.getContextPath();
    }
}
