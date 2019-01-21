package com.osdevs.testspringboot.result.error;

/**
 * 应用系统级别已知的错误码
 */
public enum GlobalErrorInfoEnum implements ErrorInfoInterface{
    SUCCESS(0, "success"),
    PARAM_ERR(-3000, "参数不能为空"),
    USER_EXIST(-3001, "用户以存在"),
    USER_NOTEXIST(-3001, "用户不存在"),
    USER_PASSWORD(-3002, "密码不能为空"),
    USER_CONFIRMPASSWORD(-3003, "两次密码输入不一致"),
    USER_USERNAME(-3004, "用户名不能为空"),
    USER_USERTYPE(-3005, "请选择输入类型"),
    USER_PASSWORD_ZH(-3006, "账号或密码不正确"),
    USER_UNTOKEN(-3007, "token无效，请重新登录"),
//    USER_TOKEN(-3008, "token无效"),
    PARAM_ERROR(-5000, "参数异常"),
    SQL_ERROR(-5001, "数据库异常"),
    NOT_FOUND(-9999, "服务器异常");




    private int code;

    private String message;

     GlobalErrorInfoEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode(){
        return this.code;
    }

    public String getMessage(){
        return this.message;
    }
}
