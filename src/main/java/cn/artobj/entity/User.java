package cn.artobj.entity;

import java.io.Serializable;

import org.apache.commons.lang3.ClassUtils.Interfaces;

import com.fasterxml.jackson.annotation.JsonView;

import cn.artdev.springboot.DB.DBTable;
import cn.artdev.springboot.DB.DBTableKey;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@DBTable(name="user")
@DBTableKey(value="user_id")
public class User {
	
	 String user_id;
	@JsonView({ListView.class})
	 String account;
	@JsonView({ListView.class})
	 String name;
	
	public interface ListView{
		
	}
	
}
