package cn.artobj.home;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonView;

import cn.artdev.springboot.DB;
import cn.artobj.entity.User;

@RestController
@RequestMapping(value="/api")
public class APIController {
	@Autowired
	private DB db;
	
	@RequestMapping(value="/data",method = RequestMethod.GET)
	@JsonView(User.ListView.class)
	public Object data(HttpServletRequest request,ModelMap model){
		DB.Table table=db.table(User.class).field(User::getName).where(User::getUser_id,DB.Opt.eq, "00");
//		Page page = new Page(request).setPageSize(5).setTotal(99);
//		Interval showPage = page.getInterval();
		
//		return showPage.getStart()+","+showPage.getEnd();
		User user = table.find(User.class);
		System.out.println(JSON.toJSONString(user));
		return user;
		//return db.table(table.toSql("a")).where("a.org_id=?", "53").select();
	}
}
