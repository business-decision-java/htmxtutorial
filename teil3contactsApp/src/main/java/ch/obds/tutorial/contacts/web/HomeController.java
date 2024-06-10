package ch.obds.tutorial.contacts.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({"/"})
public class HomeController {

    @GetMapping("/")
    ModelAndView home(ModelMap modelMap) {
        return new ModelAndView("redirect:/contacts", modelMap);
    }


}
