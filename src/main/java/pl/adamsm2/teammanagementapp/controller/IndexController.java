package pl.adamsm2.teammanagementapp.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
@RequestMapping("/")
public class IndexController {

    @GetMapping
    public String getIndex() {
        return "index";
    }

}
