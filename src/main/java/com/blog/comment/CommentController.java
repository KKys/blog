package com.blog.comment;


import com.blog.blog.Blog;
import com.jfinal.core.Controller;

import java.util.Date;

/**
 * 说明：1.pageSearchType类型： 0：首页查看全部  1：分类分页查看  2：搜索查看  3：单个详情查看  4:相册
 */
public class CommentController extends Controller {

    public void add() {
        Comment comment = getModel(Comment.class,"comment");
        comment.set("create_time",new Date());
        comment.save();
        int blogId = comment.getInt("blog_id");
        Blog blog = Blog.dao.findById(blogId);
        blog.set("comment_times",blog.getInt("comment_times")+1);
        blog.update();
        setAttr("x", blog);
        setAttr("commentList", Comment.dao.getCommentList(blogId));
        setAttr("pageSearchType",3);
        redirect("/detail/"+blogId+"#comment_id");
    }

    //返回博客评论列表
    public void getCommentList() {
        renderJson(Comment.dao.getCommentList(getParaToInt()));
    }
}
