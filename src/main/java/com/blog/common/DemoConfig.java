package com.blog.common;


import cn.dreampie.quartz.QuartzPlugin;
import com.blog.Album.Album;
import com.blog.Album.AlbumController;
import com.blog.blog.Blog;
import com.blog.blog.BlogController;
import com.blog.comment.Comment;
import com.blog.comment.CommentController;
import com.blog.info.Info;
import com.jfinal.config.*;
import com.jfinal.core.JFinal;
import com.jfinal.ext.handler.ContextPathHandler;
import com.jfinal.ext.interceptor.SessionInViewInterceptor;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.plugin.druid.DruidPlugin;

/**
 * API引导式配置
 */
public class DemoConfig extends JFinalConfig {
	
	/**
	 * 配置常量
	 */
	public void configConstant(Constants me) {
		// 加载少量必要配置，随后可用getProperty(...)获取值
		loadPropertyFile("a_little_config.txt");
		me.setDevMode(true);//开发者模式，打印了调试信息
		me.setBaseUploadPath("/usr/local/apache-tomcat-7.0.64/webapps/upload");
	}
	
	/**
	 * 配置路由
	 */
	public void configRoute(Routes me) {
		me.add("/", BlogController.class,"/blog");
		me.add("/comment", CommentController.class,"/blog");
		me.add("/album", AlbumController.class,"/blog");
	}
	
	/**
	 * 配置插件
	 */
	public void configPlugin(Plugins me) {
		// 配置C3p0数据库连接池插件
//		C3p0Plugin c3p0Plugin = new C3p0Plugin(getProperty("jdbcUrl"), getProperty("user"), getProperty("password").trim());
		DruidPlugin dp = new DruidPlugin(getProperty("jdbcUrl"), getProperty("user"), getProperty("password").trim());
		me.add(dp);
		// 配置ActiveRecord插件
		ActiveRecordPlugin arp = new ActiveRecordPlugin(dp);
		arp.setShowSql(true);
		me.add(arp);
		arp.addMapping("blog", Blog.class);	// 映射blog 表到 Blog模型
		arp.addMapping("comment", Comment.class);	// 评论model
		arp.addMapping("album", Album.class);	// 相册model
		arp.addMapping("info", Info.class);	// 网站信息model
		//定时任务
		QuartzPlugin quartz = new QuartzPlugin();
		quartz.setJobs("jobs.properties");
		me.add(quartz);
	}
	
	/**
	 * 配置全局拦截器
	 */
	public void configInterceptor(Interceptors me) {
		me.add(new SessionInViewInterceptor());
	}
	
	/**
	 * 配置处理器
	 */
	public void configHandler(Handlers me) {
//		me.add(new FakeStaticHandler(".love"));
		me.add(new ContextPathHandler("base"));
	}
	
	/**
	 * 建议使用 JFinal 手册推荐的方式启动项目
	 * 运行此 main 方法可以启动项目，此main方法可以放置在任意的Class类定义中，不一定要放于此
	 */
	public static void main(String[] args) {
		JFinal.start("src/main/webapp", 8080, "/", 5);
	}
}
