package backend.controller;

import backend.JsonUtil;
import backend.bean.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalLong;
import java.util.stream.Collectors;


@RestController()
@RequestMapping("/api")
@CrossOrigin(
        origins = "*",
        allowedHeaders = "*",
        allowCredentials = "true",
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS, RequestMethod.HEAD}
)
public class ApiController {
    @RequestMapping(value = {"/products"} , method = RequestMethod.GET)
    public String getAllProducts() throws JsonProcessingException {
        List<Product> list;
        try {
            list = JsonUtil.readJson();
        } catch (Exception e) {
            list = new ArrayList<Product>();
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(list);
    }

    @RequestMapping(value = {"/products"} , method = RequestMethod.POST)
    public String createProduct(@RequestBody Product product) throws IOException {
        List<Product> list= null;
        try {
            list = JsonUtil.readJson();
        } catch (Exception e) {
            list = new ArrayList<>();
            product.setId(1L);
            list.add(product);
            JsonUtil.writeJson(list);
            return "{\"response\":\"success\"}";
        }
        final OptionalLong max = list.stream().mapToLong(Product::getId).max();
        product.setId(max.orElse(-1)+1);
        list.add(product);
        JsonUtil.writeJson(list);
        return "{\"response\":\"success\"}";
    }

    @RequestMapping(value = {"/product"} , method = RequestMethod.GET)
    public String getProductByName( @RequestParam String name) throws IOException, ParseException {
        List<Product> list=JsonUtil.readJson();
        ObjectMapper mapper = new ObjectMapper();
        if (StringUtils.isEmpty(name)) {
            return mapper.writeValueAsString(list);
        }
        List<Product>  searchList=list.stream().filter(x -> x.getName().equals(name)).collect(Collectors.toList());
        return mapper.writeValueAsString(searchList);
    }

    @RequestMapping(value = {"/products/{id}"} , method = RequestMethod.GET)
    public String getProductById( @PathVariable Long id) throws IOException, ParseException {
        List<Product> list=JsonUtil.readJson();
        ObjectMapper mapper = new ObjectMapper();
        List<Product>  searchList=list.stream().filter(x -> x.getId().equals(id)).collect(Collectors.toList());
        return mapper.writeValueAsString(searchList);
    }

    @RequestMapping(value = {"/product/{id}"} , method = RequestMethod.PUT)
    public String updateProductById( @PathVariable Long id,@RequestBody Product product) throws IOException, ParseException {
        List<Product> list=JsonUtil.readJson();
        ObjectMapper mapper = new ObjectMapper();
        List<Product>  allList=list.stream().filter(x -> !x.getId().equals(id)).collect(Collectors.toList());
        allList.add(product);
        JsonUtil.writeJson(allList);
        return "{\"response\":\"success\"}";
    }

    @RequestMapping(value = {"/product/{id}"} , method = RequestMethod.DELETE)
    public String deleteProductById( @PathVariable Long id) throws IOException, ParseException {
        List<Product> list=JsonUtil.readJson();
        List<Product>  allList=list.stream().filter(x -> !x.getId().equals(id)).collect(Collectors.toList());
        JsonUtil.writeJson(allList);
        return "{\"response\":\"success\"}";
    }

    @RequestMapping(value = {"/products"} , method = RequestMethod.DELETE)
    public String deleteAllProducts() throws IOException, ParseException {
        JsonUtil.emptyJson();
        return "{\"response\":\"success\"}";
    }
}
