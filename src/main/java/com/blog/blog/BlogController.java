package com.blog.blog;

import com.blog.Album.Album;
import com.blog.comment.Comment;
import com.blog.info.Info;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.tx.Tx;

import java.util.Date;
import java.util.List;

/**
	 说明：1.pageSearchType类型： 0：首页查看全部  1：分类分页查看  2：搜索查看  3：单个详情查看  4：相册
 */
@Before({Tx.class,BlogInterceptor.class})
public class BlogController extends Controller {
	public void index() {
		//访客人数
		Info info = Info.dao.findById(1);
		info.set("today_click_times",info.getInt("today_click_times")+1);
		info.set("history_click_times",info.getInt("history_click_times")+1);
		info.update();
		setAttr("info", info);
		//最新文章
		setAttr("blogPage", Blog.dao.paginate(getParaToInt(0, 1), 8));
		//阅读量最多的文章
		setAttr("mostLookBlogList",Blog.dao.mostLookBlog());
		//评论量最多的文章
		setAttr("mostCommentBlogList",Blog.dao.mostCommentBlog());
		//最近评论
		setAttr("commentList",Comment.dao.getRecentCommentList());
		//最近照片
		setAttr("picList", Album.dao.listPic());
		setAttr("pageSearchType", 0);
		render("blog_index.html");
		//renderJson(Blog.dao.paginate(getParaToInt(0, 1), 10));
	}

	public void detail(){
		int id = getParaToInt();
		Blog blog = Blog.dao.findById(id);
		setAttr("x", blog);
		setAttr("commentList", Comment.dao.getCommentList(id));
		blog.set("click_times",blog.getInt("click_times")+1);
		blog.update();
		setAttr("pageSearchType",3);
		render("detail.html");
	}
	
	public void add() {
		int type = getParaToInt("type");
		String content = getPara("content");
		String title = getPara("title");
		String picUrl = getPara("picUrl");
		Blog blog = new Blog();
		blog.set("type",type);
		blog.set("title",title);
		blog.set("content",content);
		blog.set("create_time",new Date());
		blog.set("pic_url",picUrl);
		blog.save();
		redirect("/");
	}

	public void uploadPic(){
		setAttr("picUrl","/upload/"+getFile().getFileName());
		//renderJson(getFile().getUploadPath());
		render("blog_add.html");
	}

	public void searchByType(){
		int type = getParaToInt(0);
		setAttr("blogPage", Blog.dao.paginateByType(getParaToInt(1, 1), 8,type));
		setAttr("pageSearchType", 1);
		setAttr("type",type);
		render("blog_search.html");
	}

	public void searchByTitle(){
		String titleSearch = getPara("titleSearch");
		setAttr("blogPage", Blog.dao.paginateByTitle(getParaToInt(0, 1), 10000,titleSearch));
		setAttr("pageSearchType", 2);
		render("blog_search.html");
	}

	@Before(BlogValidator.class)
	public void save() {
		getModel(Blog.class).save();
		redirect("/blog");
	}
	
	public void edit() {
		setAttr("blog", Blog.dao.findById(getParaToInt()));
	}
	
	@Before(BlogValidator.class)
	public void update() {
		getModel(Blog.class).update();
		redirect("/blog");
	}
	
	public void delete() {
		Blog.dao.deleteById(getParaToInt());
		redirect("/blog");
	}

	public void adminHtml(){
		render("blog_admin.html");
	}

	public void admin(){
		String username = getPara("username");
		String password = getPara("password");
		if("admin".equals(username)&&"123456".equals(password)){
			render("blog_add.html");
		}else
			renderJson("用户名密码错误");
	}
}