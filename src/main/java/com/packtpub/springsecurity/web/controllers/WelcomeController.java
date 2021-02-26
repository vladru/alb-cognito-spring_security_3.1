package com.packtpub.springsecurity.web.controllers;

import com.packtpub.springsecurity.domain.CalendarUser;
import com.packtpub.springsecurity.service.CalendarService;
import com.packtpub.springsecurity.service.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * This displays the welcome screen that shows what will be happening in this chapter.
 *
 * @author Rob Winch
 *
 */
@Controller
public class WelcomeController {
    private final UserContext userContext;

    @Autowired
    public WelcomeController(UserContext userContext) {
        this.userContext = userContext;
    }
    @RequestMapping("/")
    public ModelAndView welcome() {
        CalendarUser currentUser = userContext.getCurrentUser();
        ModelAndView result = new ModelAndView("index");
        String note = currentUser == null ? "No Logged user" : currentUser.getNote();
        result.addObject("note", note);
        return result;
    }

    @RequestMapping("/tstcognito")
    public ModelAndView tstcognito() {
        CalendarUser currentUser = userContext.getCurrentUser();
        ModelAndView result = new ModelAndView("index");
        String note = currentUser == null ? "No Logged user" : currentUser.getNote();
        result.addObject("note", note);
        return result;
    }


}
