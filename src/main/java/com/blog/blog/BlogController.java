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


	/**
	 * 前台接口
	 */
	public void index() {
		//访客人数
		Info info = Info.dao.findById(1);
		setAttr("info", info);
		//判断是否是当日登录
		if(getSessionAttr("now")==null){
			setSessionAttr("now",true);
			info.set("today_click_times",info.getInt("today_click_times")+1);
			info.set("history_click_times",info.getInt("history_click_times")+1);
			info.update();
		}
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

	//详情页面
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

	//根据类型查博客
	public void searchByType(){
		int type = getParaToInt(0);
		setAttr("blogPage", Blog.dao.paginateByType(getParaToInt(1, 1), 8,type));
		setAttr("pageSearchType", 1);
		setAttr("type",type);
		render("blog_search.html");
	}

	//根据标题查博客
	public void searchByTitle(){
		String titleSearch = getPara("titleSearch");
		setAttr("blogPage", Blog.dao.paginateByTitle(getParaToInt(0, 1), 10000,titleSearch));
		setAttr("pageSearchType", 2);
		render("blog_search.html");
	}

	//返回博客信息列表
	public void getBlogList(){
		renderJson(Blog.dao.paginate(getParaToInt(0, 1), 8));
	}

	//返回博客详情json
	public void getBlogDetailJson(){
		int id = getParaToInt();
		Blog blog = Blog.dao.findById(id);
		blog.set("click_times",blog.getInt("click_times")+1);
		blog.update();
		renderJson(blog);
	}


	/**
	 * 后台接口
	 */
	//获取单个博客内容
	public void get() {
		renderJson(Blog.dao.findById(getParaToInt()));
	}

	//保存
	@Before(Tx.class)
	public void save() {
		Blog blog = getModel(Blog.class,"blog");
		if(getParaToInt("userType")==1){
			blog.set("title","[网友测试]"+blog.get("title"));
		}
		blog.set("create_time",new Date());
		blog.save();
		setAttr("blogPage", Blog.dao.paginate(getParaToInt(0, 1), 15));
		render("blog_add.html");
	}

	//修改
	@Before(Tx.class)
	public void update() {
		getModel(Blog.class).update();
		setAttr("blogPage", Blog.dao.paginate(getParaToInt(0, 1), 15));
		render("blog_add.html");
	}

	//删除
	@Before(Tx.class)
	public void delete() {
		Blog.dao.deleteById(getParaToInt());
		setAttr("blogPage", Blog.dao.paginate(getParaToInt(0, 1), 15));
		render("blog_add.html");
	}

	//跳转到管理登录页面
	public void adminHtml(){
		render("blog_admin.html");
	}

	//上传图片
	public void imageUpload() {
		renderJson("/upload/"+getFile().getFileName());
	}

	/**
	 * 登录接口，type=0：管理员登录，type=1：游客登录
	 */
	public void login(){
		int type = getParaToInt("type");
		if(type == 1){//游客登录
			setSessionAttr("adminType",1);
			setAttr("blogPage", Blog.dao.paginate(getParaToInt(0, 1), 15));
			render("blog_add.html");
		}else if(type == 0){//管理员登录
			setSessionAttr("adminType",0);
			String username = getPara("username");
			String password = getPara("password");
			if("admin".equals(username)&&"123654".equals(password)){
				setAttr("blogPage", Blog.dao.paginate(getParaToInt(0, 1), 15));
				render("blog_add.html");
			}else
				renderJson("用户名密码错误");
		}
	}

	/**
	 * 跳转到管理页面接口
	 */
	public void admin(){
		setAttr("blogPage", Blog.dao.paginate(getParaToInt(0, 1), 15));
		render("blog_add.html");
	}
}