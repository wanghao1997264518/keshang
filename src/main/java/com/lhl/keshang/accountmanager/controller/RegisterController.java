package com.lhl.keshang.accountmanager.controller;

import com.lhl.keshang.accountmanager.pojo.Account;
import com.lhl.keshang.accountmanager.pojo.vo.AccountRegisterVo;
import com.lhl.keshang.accountmanager.service.AccountService;
import com.lhl.keshang.pub.pojo.Result;
import com.lhl.keshang.pub.utils.JsonUtil;
import com.lhl.keshang.pub.utils.ResultUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 应用模块名称<p>
 * 代码描述<p>
 * Copyright: Copyright (C) 2018 XXX, Inc. All rights reserved. <p>
 * Company: XXX科技有限公司<p>
 *
 * @author 刘浩磊
 * @since 2018/11/9 17:29
 */
@RestController
public class RegisterController {

    @Autowired
    private AccountService accountService;

//    @PostMapping("/register")
//    public String register(@RequestBody @Valid AccountRegisterVo accountRegisterVo, BindingResult bindingResult){
//
//        if(bindingResult.hasFieldErrors()){
//            return JsonUtil.objectToJsonString(ResultUtil.error(500,bindingResult.getFieldError().getField()+":"+bindingResult.getFieldError().getDefaultMessage()));
//        }
//        Result result = accountService.addNewAccount(accountRegisterVo);
//        return JsonUtil.objectToJsonString(result);
//
//    }

    @PostMapping("/validateUser")
    public String validateUser(@RequestBody AccountRegisterVo accountRegisterVo){

        if(accountRegisterVo.getPassword()==null||accountRegisterVo.getPassword1()==null||accountRegisterVo.getPassword()==""||accountRegisterVo.getPassword1()==""){
            return  JsonUtil.objectToJsonString(ResultUtil.error(401,"密码不能为空"));
        }else if(!accountRegisterVo.getPassword().equals(accountRegisterVo.getPassword1())){
            return  JsonUtil.objectToJsonString(ResultUtil.error(401,"两次密码不一致"));
        }else if(accountRegisterVo.getUserName()!=null&&(!accountRegisterVo.getUserName().equals(""))){
            Account account = new Account();
            BeanUtils.copyProperties(accountRegisterVo,account);
            Result result = accountService.validateUserName(account);
            if(result.getCode()==200){
                result = accountService.addNewAccount(account);
            }
            return JsonUtil.objectToJsonString(result);
        }else{
            return  JsonUtil.objectToJsonString(ResultUtil.error(401,"用户名不能为空"));
        }


    }

}
