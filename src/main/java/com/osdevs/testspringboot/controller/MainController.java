package com.osdevs.testspringboot.controller;

import com.osdevs.testspringboot.result.ResultBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@ApiIgnore
public class MainController {
    @ApiIgnore
    @RequestMapping(value ="/Api",method = {RequestMethod.POST,RequestMethod.GET})
    public ResultBody hello() {
        return new ResultBody<>( "hello,this is a service");
    }
}
