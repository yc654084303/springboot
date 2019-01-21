package com.osdevs.testspringboot.interceptor;

import com.osdevs.testspringboot.entity.User;
import com.osdevs.testspringboot.entity.UserToken;
import com.osdevs.testspringboot.result.error.GlobalErrorInfoEnum;
import com.osdevs.testspringboot.result.exception.GlobalErrorInfoException;
import com.osdevs.testspringboot.service.user.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Date;

public class TokenInterceptor implements HandlerInterceptor {
    @Autowired
    private UserService userService;
    @Override
    public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
        //普通路径放行
        if ("/Api/User/Login".equals(arg0.getRequestURI()) ||
                "/Api/User/Register".equals(arg0.getRequestURI())) {
            return true;
        }
        //权限路径拦截
        arg1.setCharacterEncoding("UTF-8");
        if (ckToken(arg0)) {
            throw new GlobalErrorInfoException(GlobalErrorInfoEnum.USER_UNTOKEN);
        }
        //最后才放行
        return true;
    }

    private boolean ckToken(HttpServletRequest arg0)  {
        String headerToken=arg0.getHeader("token");
        //判断请求信息
        if(StringUtils.isBlank(headerToken)){
            return true;
        }
        //解析Token信息
        Claims claims = Jwts.parser().setSigningKey("SignKey").parseClaimsJws(headerToken).getBody();
        String tokenUserId=(String)claims.get("userId");
        //根据客户Token查找数据库Token
        User myToken=userService.findByUserName(tokenUserId );
            if (myToken==null){
                return true;
            }
        //判断Token过期
        Date tokenDate=(Date)claims.getExpiration();
        int chaoshi=(int)(new Date().getTime()-tokenDate.getTime())/1000;
        if(chaoshi>UserToken.TOKEN_REFRESH_TIME){
            return true;
        }
        return false;
    }

}
