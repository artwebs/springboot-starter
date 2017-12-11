package cn.artobj.home;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.artdev.springboot.DB;
import cn.artdev.springboot.Page;
import cn.artdev.springboot.Page.Interval;

@Controller
@RequestMapping(value="/")
public class IndexController {
	@Autowired
	private DB db;
	
	@Autowired
    private RedisTemplate<String, String> cacheDB;
	
	@RequestMapping(value="/home",method = RequestMethod.GET)
	@ResponseBody
	public Object index(HttpServletRequest request){
//		DB.Table table=db.table("user").where("user_id=?", "00");
		Page page = new Page(request).setPageSize(5).setTotal(99);
		Interval showPage = page.getInterval();
		return showPage.getStart()+","+showPage.getEnd();
		//return db.table(table.toSql("a")).where("a.org_id=?", "53").select();
	}
}
