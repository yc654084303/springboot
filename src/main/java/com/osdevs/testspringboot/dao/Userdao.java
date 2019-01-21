package com.osdevs.testspringboot.dao;

import com.osdevs.testspringboot.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface Userdao {
    /**
     * 登录
     * @param userName
     * @param passWord
     * @return
     */
    User loginUser(String userName, String passWord);


    /**
     * 注册
     * @param user
     * @return
     */
    int registerUser(User user);


    /**
     * 是否存在手机号,邮件邮
     * @param userName
     * @return
     */
    User isUserMobileOrEmailExist(String userName,int userType);

    /**
     * 查用户信息
     * @param userid
     * @return
     */
    User findByUserName(String userid);
}
