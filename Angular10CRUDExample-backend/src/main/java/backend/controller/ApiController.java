package backend.controller;

import backend.JsonUtil;
import backend.bean.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;


@RestController()
@RequestMapping("/api")
@CrossOrigin(
        origins = "*",
        allowedHeaders = "*",
        allowCredentials = "true",
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS, RequestMethod.HEAD}
)
public class ApiController {
    @RequestMapping("/products")
    public String getAllProducts() throws IOException, ParseException {
//            JsonUtil.writeJson();
        List<Product> list=JsonUtil.readJson();
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(list);
//        return "aaa";
    }
}
