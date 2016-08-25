package com.blog.common;

import com.jfinal.core.Controller;

/**
 * Created by Administrator on 2016/8/7.
 */
public class testController extends Controller{
    public void index(){
        render("/common/test.html");
    }
}
