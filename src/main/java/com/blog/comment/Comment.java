package com.blog.comment;

import com.blog.blog.Blog;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

import java.util.List;

/**
 * Created by Administrator on 2016/8/18.
 */
public class Comment extends Model<Comment> {
    public static final Comment dao = new Comment();

    public List<Comment> getCommentList(int blogId) {
        return find("select * from comment where blog_id = "+blogId+" order by create_time");
    }

    public List<Record> getRecentCommentList() {
        return Db.find("select blog.id,blog.title,comment.content,comment.create_time,comment.username " +
                "from comment left join blog on blog.id = comment.blog_id " +
                "ORDER BY comment.create_time desc LIMIT 0,5");
    }
}
