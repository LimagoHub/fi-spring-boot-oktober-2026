package de.fi.webapp.presentation.controller.v1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/demo")
public class DemoController {


    // http://localhost:8080/swagger-ui.html

    @GetMapping("/gruss")
    public String hello() {
        return "Hello Rest";
    }
}
