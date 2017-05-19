package com.up.skill.controllers;

import com.up.skill.models.Form;
import com.up.skill.models.FormInterface;
import com.up.skill.support.web.MessageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

/**
 * Created by Multi Cabz on 9/21/2016.
 */
@Controller
public class Main {

    @Autowired
    private FormInterface formInterface;

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String index(){
        return "main/index";
    }

//    @RequestMapping(value = "/thankyou")
//    public String thanks() { return "main/thankyou"; }

    @RequestMapping(value = "/thankyou", method = RequestMethod.POST)
    public String form(Form form, RedirectAttributes ra) {
        formInterface.save(form);
        return "main/thankyou";
    }

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String view(Model model){

        model.addAttribute("users", formInterface.findAll());
        return "main/view";
    }

    @RequestMapping ("/delete/{id}")
    public String doDelete(@PathVariable Long id,
                           RedirectAttributes ra){
        formInterface.delete(id);

//        MessageHelper.addSuccessAttribute(ra, "form.deleted");

        return "redirect:/view";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(){

        return "main/edit";
    }
}
