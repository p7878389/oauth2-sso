package com.shareworks.client1.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author martin.peng
 */
@Controller
public class IndexController {

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/securedPage")
    public String securedPage(ModelAndView modelAndView) {
        modelAndView.addObject("authentication", SecurityContextHolder.getContext().getAuthentication());
        return "securedPage";
    }
}
