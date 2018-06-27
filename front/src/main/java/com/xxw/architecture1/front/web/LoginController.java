package com.xxw.architecture1.front.web;

import com.xxw.architecture1.customer.service.ICustomerService;
import com.xxw.architecture1.customer.vo.CustomerModel;
import com.xxw.architecture1.goodsmgr.service.IGoodsService;
import com.xxw.architecture1.goodsmgr.vo.GoodsModel;
import com.xxw.architecture1.goodsmgr.vo.GoodsQueryModel;
import com.xxw.pageutil.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RequestMapping("/")
@Controller
public class LoginController {

    @Autowired
    private ICustomerService customerService;

    @RequestMapping(value = "/toLogin", method = RequestMethod.GET)
    public String toLogin(){
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam("customerId") String customerId, @RequestParam("pwd") String pwd,
                        HttpServletResponse response){
        if(customerId == null || customerId.trim().length() == 0) return "login";

        CustomerModel cm = customerService.getByCustomerId(customerId);
        if(cm == null) return "login";

        Cookie cookie = new Cookie("MyLogin", cm.getUuid()+"#"+System.currentTimeMillis());
        response.addCookie(cookie);

        return "redirect:/toIndex";
    }


}
