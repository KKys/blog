package com.blog.info;

import com.jfinal.plugin.activerecord.Model;

/**
 * Created by Administrator on 2016/8/21.
 */
public class Info extends Model<Info>{
    public static final Info dao = new Info();

    public void clearTodayClick(){
        Info info = findById(1);
        info.set("today_click_times",0);
        info.update();
    }
}
