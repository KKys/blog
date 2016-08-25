package com.blog.Album;

import com.blog.blog.Blog;
import com.blog.comment.Comment;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;

import java.util.Date;

/**
 * 说明：1.pageSearchType类型： 0：首页查看全部  1：分类分页查看  2：搜索查看  3：单个详情查看  4:相册
 */
public class AlbumController extends Controller {
    public void albumHtml(){
        Page<Album> page = Album.dao.paginate(getParaToInt(0, 1), 16);
        int size = (page.getList().size());
        setAttr("albumPage", page);
        int finalSize = (size/4+1)*4;
        setAttr("finalSize", finalSize);
        render("album.html");
    }

    public void add(){
        Album album = getModel(Album.class,"album");
        album.set("create_time",new Date());
        album.save();
        Page<Album> page = Album.dao.paginate(getParaToInt(0, 1), 16);
        int size = (page.getList().size());
        setAttr("albumPage", page);
        int finalSize = (size/4+1)*4;
        setAttr("finalSize", finalSize);
        render("album.html");
    }
}
