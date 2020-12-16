package com.example.demo.controller;

import com.example.demo.model.City;
import com.example.demo.model.Country;
import com.example.demo.service.city.ICityService;
import com.example.demo.service.country.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/cities")
public class CityController {
    @Autowired
    private ICityService iCityService;
    @Autowired
    private ICountryService iCountryService;

    @ModelAttribute("country")
    private Iterable<Country> country() {
        return iCountryService.findAll();
    }

    @GetMapping("")
    public ModelAndView listCity() {
        Iterable<City> cities = iCityService.findAll();
        ModelAndView modelAndView = new ModelAndView("city/list");
        modelAndView.addObject("city", cities);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showNewFormCity() {
        ModelAndView modelAndView = new ModelAndView("city/create");
        modelAndView.addObject("city", new City());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView createCity(@ModelAttribute City city) {
        iCityService.save(city);
        ModelAndView modelAndView = new ModelAndView("city/create");
        modelAndView.addObject("city", new City());
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable Long id){
        City city =iCityService.findById(id).get();
        ModelAndView modelAndView = new ModelAndView("city/edit");
        modelAndView.addObject("city", city);
        return modelAndView;
    }

    @PostMapping("/edit")
    public ModelAndView editCustomer(@ModelAttribute City city) {
        City city1=city;
        iCityService.save(city);
        ModelAndView modelAndView = new ModelAndView("city/list");
        modelAndView.addObject("city", iCityService.findAll());
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        iCityService.delete(id);
        return "redirect:/cities";
    }

}
