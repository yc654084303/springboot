package com.osdevs.testspringboot.controller;

import com.osdevs.testspringboot.entity.User;
import com.osdevs.testspringboot.entity.UserToken;
import com.osdevs.testspringboot.model.RegisterUserModel;
import com.osdevs.testspringboot.model.UserModel;
import com.osdevs.testspringboot.result.ResultBody;
import com.osdevs.testspringboot.result.exception.GlobalErrorInfoException;
import com.osdevs.testspringboot.service.user.UserService;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping(value ="/Api/User")
@Api(value ="用户管理")
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation("用户登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName",value = "用户名",dataType = "String",required=true, paramType = "query"),
            @ApiImplicitParam(name = "passWord",value = "密码",dataType = "String",required=true, paramType = "query")
    })
    @PostMapping(value = "/Login")
    public ResultBody LoginUser( String userName,String passWord)throws GlobalErrorInfoException{
        User user=userService.LoginUser(userName,passWord);
        //为生成Token准备
        Date date = new Date();
        //生成Token
        UserToken   userToken= creatToken(user, date);
        user.setToken( userToken);
        return new ResultBody<>( user);
    }
    @ApiOperation("用户注册")
    @PostMapping(value = "/Register")
    public ResultBody registerUser(@RequestBody RegisterUserModel user) throws GlobalErrorInfoException {
        return new ResultBody<>(userService.registerUser(user));
    }


    //生成Token信息方法（根据有效的用户信息）
    private UserToken creatToken(User user, Date date) {
        UserToken userToken=new UserToken();
        Date tokenBuildTime=   new Date(date.getTime() + UserToken.TOKEN_REFRESH_TIME);
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        JwtBuilder builder = Jwts.builder()
//                .setHeaderParam("token", "JWT") // 设置header
//                .setHeaderParam("alg", "HS256")
                .setIssuedAt(date) // 设置签发时间
                .setExpiration(tokenBuildTime)
                .claim("userId",String.valueOf(user.getUserId()) ) // 设置内容
//                .setIssuer("churck")// 设置签发人
                .signWith(signatureAlgorithm, "SignKey"); // 签名，需要算法和key
        String jwt = builder.compact();
        userToken.setUserId(user.getUserId());
        userToken.setToken(jwt);
        userToken.setBuildTime(date);
        return userToken;
    }

}
