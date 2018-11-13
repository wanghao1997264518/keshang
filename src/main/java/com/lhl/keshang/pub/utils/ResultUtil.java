package com.lhl.keshang.pub.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lhl.keshang.pub.pojo.Result;

import java.util.ArrayList;
import java.util.List;

/**
 * 应用模块名称<p>
 * 代码描述<p>
 * Copyright: Copyright (C) 2018 XXX, Inc. All rights reserved. <p>
 * Company: XXX科技有限公司<p>
 *
 * @author 刘浩磊
 * @since 2018/11/9 15:29
 */
public class ResultUtil {

    public static Result success(Object data){

        Result r = new Result();
        r.setCode(200);
        r.setMsg("成功");

        if(data instanceof List){
            List list = (List)data;
            r.setData(list);
        }else{
            List list = new ArrayList();
            list.add(data);
            r.setData(list);
        }

        return r;

    }

    public static Result error(Integer code,String msg){

        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;

    }

}
