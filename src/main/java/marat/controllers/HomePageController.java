package marat.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomePageController {
    @RequestMapping(value = { "/", "/index"})
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/clients" )
    public String clients() {
        return "clients";
    }

    @RequestMapping(value = "/offices")
    public String offices() {
        return "offices";
    }

    @RequestMapping(value = "/accounts")
    public String accounts() {
        return "accounts";
    }
}
