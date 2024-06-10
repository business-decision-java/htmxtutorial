package ch.obds.tutorial.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Collection;

@Controller
public class TemperaturesController {

    record CityTemperature (String city, float temperature) {};

    Collection<CityTemperature> temperatures = new ArrayList<CityTemperature>();
    {
        temperatures.add(new CityTemperature("Bern", 21.4f));
        temperatures.add(new CityTemperature("Zürich", 22.1f));
        temperatures.add(new CityTemperature("Genf", 19.7f));
        temperatures.add(new CityTemperature("Oslo", 13.2f));
        temperatures.add(new CityTemperature("Murmansk", -3.8f));
    };

    @GetMapping("/temperatures")
    public String temperatures(Model model) {
        model.addAttribute("temperatures", temperatures);
        return "temperatures";
    }

    @GetMapping("/temperatures/new")
    public ModelAndView newTemperature(ModelMap model) {
        model.addAttribute("cityTemperature", new CityTemperature("", 0f));
        return new ModelAndView("form", model);

    }

    @PostMapping("/temperatures/saveTemperature")
    public ModelAndView saveTemperature (ModelMap model, @ModelAttribute("cityTemperature") CityTemperature cityTemperature, RedirectAttributes redirectAttributes) {
        temperatures.add(cityTemperature);
        return new ModelAndView("form", model);
    }


}

