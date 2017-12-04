package cn.artobj.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.artdev.springboot.DB;

@Controller
@RequestMapping(value="/")
public class IndexController {
	@Autowired
	private DB db;
	
	@RequestMapping(value="/home",method = RequestMethod.GET)
	@ResponseBody
	public Object index(){
		return db.table("user").select();
	}
}
