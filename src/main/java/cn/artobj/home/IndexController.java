package cn.artobj.home;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;

import cn.artdev.springboot.DB;
import cn.artdev.springboot.Page;
import cn.artdev.springboot.Page.Interval;
import cn.artobj.entity.User;

@Controller
@RequestMapping(value="/")
public class IndexController {
	@Autowired
	private DB db;
	
	@Autowired
    private RedisTemplate<String, String> cacheDB;
	
	@RequestMapping(value="/home",method = RequestMethod.GET)
	public Object index(HttpServletRequest request,ModelMap model){
		List<HashMap<String,Object>> list=new ArrayList<HashMap<String,Object>>();
		HashMap<String,Object> item1=new HashMap<String,Object>();
		item1.put("id", "1");item1.put("text", "one");
		HashMap<String,Object> item2=new HashMap<String,Object>();
		item2.put("id", "2");item2.put("text", "two");
		list.add(item1);
		list.add(item2);
		model.put("nobj",list);
		
		return "home";
	}
	
	@RequestMapping(value="/data",method = RequestMethod.GET)
	@ResponseBody
	public User data(HttpServletRequest request,ModelMap model){
		DB.Table table=db.table("user").where("user_id=?", "00");
//		Page page = new Page(request).setPageSize(5).setTotal(99);
//		Interval showPage = page.getInterval();
		
//		return showPage.getStart()+","+showPage.getEnd();
		User user = table.find(User.class);
		System.out.println(JSON.toJSONString(user));
		return user;
		//return db.table(table.toSql("a")).where("a.org_id=?", "53").select();
	}
}
