package com.osdevs.testspringboot.service.user.impl;

import com.osdevs.testspringboot.dao.Userdao;
import com.osdevs.testspringboot.entity.User;
import com.osdevs.testspringboot.model.RegisterUserModel;
import com.osdevs.testspringboot.result.error.GlobalErrorInfoEnum;
import com.osdevs.testspringboot.result.exception.GlobalErrorInfoException;
import com.osdevs.testspringboot.service.user.UserService;
import com.osdevs.testspringboot.utils.UUID;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.core.util.UuidUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
   private Userdao userdao;
    @Override
    public User LoginUser(String userName, String passWord) throws GlobalErrorInfoException {
        // 从缓存中获取城市信息
        String key = "User_" + userName;
        ValueOperations<String, User> operations = redisTemplate.opsForValue();
        // 缓存存在
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            User user = operations.get(key);
            LOGGER.info("CityServiceImpl.findCityById() : 从缓存中获取了用户>> " + user.toString());
            return user;
        }
        if (StringUtils.isBlank(userName)){
            throw new GlobalErrorInfoException(GlobalErrorInfoEnum.USER_USERNAME);
        }else if (StringUtils.isBlank(passWord)){
            throw new GlobalErrorInfoException(GlobalErrorInfoEnum.USER_PASSWORD);
        }
        // 插入缓存
        User user= userdao.loginUser(userName,passWord);
        if (user==null){
            throw new GlobalErrorInfoException(GlobalErrorInfoEnum.USER_PASSWORD_ZH);
        }else {
            operations.set(key, user, 10, TimeUnit.SECONDS);
            LOGGER.info("CityServiceImpl.findCityById() : 用户插入缓存 >> " + user.toString());
        }
        return user;
    }
    @Override
    public User registerUser(RegisterUserModel userModel) throws GlobalErrorInfoException {
        User user= isUserMobileOrEmailExist(userModel);
        int i=userdao.registerUser(user);
        if (i>0){
            return user;
        }else {
            throw new GlobalErrorInfoException(GlobalErrorInfoEnum.SQL_ERROR);
        }
    }

    @Override
    public User findByUserName(String userid) {
        userdao.findByUserName(userid);
        return null;
    }

    private User isUserMobileOrEmailExist(RegisterUserModel userModel) throws GlobalErrorInfoException {
        //1手机用户，2，邮件用户，3微信用户，4QQ用户
        if (userModel==null){
            throw new GlobalErrorInfoException(GlobalErrorInfoEnum.PARAM_ERR);
        }
        if (StringUtils.isBlank(userModel.getUserName())){
            throw new GlobalErrorInfoException(GlobalErrorInfoEnum.USER_USERNAME);
        }else if (StringUtils.isBlank(userModel.getConfirmPassWord())||StringUtils.isBlank(userModel.getPassWord())){
            throw new GlobalErrorInfoException(GlobalErrorInfoEnum.USER_PASSWORD);

        }else if (  !StringUtils.equals(userModel.getPassWord(),userModel.getConfirmPassWord())){
            throw new GlobalErrorInfoException(GlobalErrorInfoEnum.USER_CONFIRMPASSWORD);
        }else if (userModel.getUserType()<=0){
            throw new GlobalErrorInfoException(GlobalErrorInfoEnum.USER_USERTYPE);
        }
        //是否注册过
        User user=  userdao.isUserMobileOrEmailExist(userModel.getUserName(),userModel.getUserType());
        if (user!=null){
            throw new GlobalErrorInfoException(GlobalErrorInfoEnum.USER_EXIST);
         }else{
            user=new User();
            if (userModel.getUserType()==1){
                user.setMobile(userModel.getUserName());
            }else if (userModel.getUserType()==2){
                user.setEmail(userModel.getUserName());
            }
            user.setPassWord(userModel.getPassWord());
            user.setUserId(UUID.UU32());
            user.setUserType(userModel.getUserType());

            user.setRegisterTime(new Date());
        }
        return user;
    }
}
