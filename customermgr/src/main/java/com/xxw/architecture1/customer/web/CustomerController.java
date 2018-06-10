package com.xxw.architecture1.customer.web;

import com.xxw.util.format.DateFormatHelper;
import com.xxw.util.json.JsonHelper;
import com.xxw.architecture1.customer.service.ICustomerService;
import com.xxw.architecture1.customer.vo.CustomerModel;
import com.xxw.architecture1.customer.vo.CustomerQueryModel;
import com.xxw.pageutil.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/customer")
public class CustomerController {

    @Autowired
    private ICustomerService customerService = null;

    @RequestMapping(value = "toAdd", method = RequestMethod.GET)
    public String toAdd(){
        return "customer/add";
    }
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(@ModelAttribute("cm") CustomerModel cm){
        cm.setRegisterTime(DateFormatHelper.longToStr(System.currentTimeMillis()));
        customerService.create(cm);
        return "customer/success";
    }

    @RequestMapping(value = "toUpdate/{uuid}", method = RequestMethod.GET)
    public String toUpdate(Model model, @PathVariable("uuid") int uuid){
        CustomerModel cm = null;
        cm = customerService.getByUuid(uuid);
        model.addAttribute("cm", cm);
        return "customer/update";
    }
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(@ModelAttribute("cm") CustomerModel cm){

        try{
            customerService.update(cm);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "customer/success";
    }

    @RequestMapping(value = "toDelete/{uuid}", method = RequestMethod.GET)
    public String toDelete(Model model, @PathVariable("uuid") int uuid){
        CustomerModel cm = customerService.getByUuid(uuid);
        model.addAttribute("cm", cm);
        return "customer/update";
    }
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public String delete(@RequestParam("uuid") int uuid){
        customerService.delete(uuid);
        return "customer/success";
    }

    @RequestMapping(value = "toList", method = RequestMethod.GET)
    public String toList(Model model, @ModelAttribute("cw") CustomerWebModel cw){
        CustomerQueryModel cqm = null;
        if(cw.getQueryJsonStr() == null || cw.getQueryJsonStr().trim().length() == 0)
            cqm = new CustomerQueryModel();
        else
            cqm = (CustomerQueryModel) JsonHelper.strToObject(cw.getQueryJsonStr(), CustomerQueryModel.class);

        cqm.getPage().setNowPage(cw.getNowPage());
        if(cw.getPageShow() > 0){
            cqm.getPage().setPageShow(cw.getPageShow());
        }
        Page dbPage = customerService.getByConditionPage(cqm);

        model.addAttribute("cw", cw);
        model.addAttribute("page", dbPage);
        return "customer/list";
    }

    @RequestMapping(value = "toQuery", method = RequestMethod.GET)
    public String toQuery(){
        //CustomerModel cm = customerService.getByUuid(uuid);
        //model.addAttribute("cm", cm);
        return "customer/query";
    }

}
