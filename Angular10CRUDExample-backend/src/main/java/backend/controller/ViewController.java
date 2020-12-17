package backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Chris Chen https://blog.kyocoolcool.com
 * @version 1.0
 * @since 2020/12/17 7:51 PM
 **/
@Controller
public class ViewController {
    @RequestMapping("/")
    public String index() {
        return "index";
    }
}
