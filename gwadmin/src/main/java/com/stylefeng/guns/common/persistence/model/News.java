package com.stylefeng.guns.common.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

public class News extends Model<News> {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键id
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;
	/**
	 * 栏目id
	 */
	private Integer cloumnId;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 副标题
	 */
	private String subtitle;
	/**
	 * html版内容
	 */
	private String htmlContent;
	/**
	 * 图片
	 */
	private String image;
	/**
	 * 创建时间
	 */
	private String creatDate;
	/**
	 * 修改时间
	 */
	private String modifyDate;
	/**
	 * 操作员
	 */
	private String userName;
	/**
	 * 置顶
	 */
	private String istop;
	/**
	 * 版本
	 */
	private Integer version;

	@Override
	protected Serializable pkVal() {
		// TODO 自动生成的方法存根
		return null;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getCloumnId() {
		return cloumnId;
	}

	public void setCloumnId(Integer cloumnId) {
		this.cloumnId = cloumnId;
	}

	public String getCreatDate() {
		return creatDate;
	}

	public void setCreatDate(String creatDate) {
		this.creatDate = creatDate;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIstop() {
		return istop;
	}

	public void setIstop(String istop) {
		this.istop = istop;
	}

	public String getHtmlContent() {
		return htmlContent;
	}

	public void setHtmlContent(String htmlContent) {
		this.htmlContent = htmlContent;
	}

	public String getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	@Override
	public String toString() {
		return "News {id=" + id + ", cloumnId=" + cloumnId + ", title=" + title + ", htmlContent=" + htmlContent
				+ ", image=" + image + ", creatDate=" + creatDate + ", modifyDate=" + modifyDate + ", userName="
				+ userName + ", istop=" + istop + ", "+ "version=" + version 
				+ ", "+ "subtitle=" + subtitle 
				+ "}";
	}




}
