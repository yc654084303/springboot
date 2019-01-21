package com.osdevs.testspringboot.service.user;

import com.osdevs.testspringboot.entity.User;
import com.osdevs.testspringboot.model.RegisterUserModel;
import com.osdevs.testspringboot.result.exception.GlobalErrorInfoException;

public interface UserService {
     User LoginUser(String userName, String passWord) throws GlobalErrorInfoException;
     User registerUser(RegisterUserModel id) throws GlobalErrorInfoException;
     User findByUserName(String userid);
}
