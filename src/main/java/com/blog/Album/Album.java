package com.blog.Album;

import com.blog.blog.Blog;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

import java.util.List;

/**
 * Created by Administrator on 2016/8/19.
 */
public class Album extends Model<Album> {
    public static final Album dao = new Album();

    /**
     * 分页查相册
     */
    public Page<Album> paginate(int pageNumber, int pageSize) {
        return paginate(pageNumber, pageSize, "select *", "from album order by create_time desc");
    }

    public List<Album> listPic() {
        return find("select * from album order by create_time desc limit 0,5");
    }
}
