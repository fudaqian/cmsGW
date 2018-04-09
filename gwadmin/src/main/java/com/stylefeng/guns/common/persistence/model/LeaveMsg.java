package com.stylefeng.guns.common.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

public class LeaveMsg extends Model<LeaveMsg>{

	private static final long serialVersionUID = 1L;
	
    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    
    private String name;
    
    private String phone;
    
    private String email;
    
    private String content;
    
    private String createDate;
	
    
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getCreateDate() {
		return createDate;
	}


	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	@Override
	protected Serializable pkVal() {
		// TODO 自动生成的方法存根
		return null;
	}


	@Override
	public String toString() {
		return "LeaveMsg {id=" + id + ",createDate=" + createDate + ", name=" + name + ", phone=" + phone + ", email=" + email + ", content="
				+ content + "}";
	}

	
}
