package com.blog.comment;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

/**
 * 添加评论校验
 */
public class CommentValidator extends Validator{

    protected void validate(Controller controller) {
//        validateRequiredString("comment.email", "emailMsg", "请输入邮箱以便联系!");
//        validateRequiredString("comment.username", "userMsg", "请输入用户名!");
    }

    protected void handleError(Controller controller) {
//		controller.keepModel(Comment.class);
//
//		String actionKey = getActionKey();
//		if (actionKey.equals("/comment/add")){
//            int blogId = controller.getParaToInt("comment.blog_id");
//            controller.redirect("/detail/"+blogId+"#comment_id");
//        }
    }
}
