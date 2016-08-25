package com.blog.blog;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

import java.util.List;

/**
 * Blog model.
 */
@SuppressWarnings("serial")
public class Blog extends Model<Blog> {
	public static final Blog dao = new Blog();
	
	/**
	 * 所有 sql 写在 Model 或 Service 中，不要写在 Controller 中，养成好习惯，有利于大型项目的开发与维护
	 */
	public Page<Blog> paginate(int pageNumber, int pageSize) {
		return paginate(pageNumber, pageSize, "select *", "from blog order by create_time desc");
	}

	public Page<Blog> paginateByType(int pageNumber, int pageSize, int type) {
		return paginate(pageNumber, pageSize, "select *", "from blog where type="+type+" order by create_time desc");
	}

	public Page<Blog> paginateByTitle(int pageNumber, int pageSize, String title) {
		return paginate(pageNumber, pageSize, "select *", "from blog where title like '%"+title+"%' order by create_time desc");
	}

	public List<Blog> mostLookBlog(){
		return find("select * from blog order by click_times desc limit 0,8");
	}

	public List<Blog> mostCommentBlog(){
		return find("select * from blog order by comment_times desc limit 0,8");
	}

}
