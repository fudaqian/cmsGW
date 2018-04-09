package com.stylefeng.guns.modular.system.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.validation.Valid;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.HtmlUtils;

import com.alibaba.fastjson.JSONObject;
import com.stylefeng.guns.common.exception.BizExceptionEnum;
import com.stylefeng.guns.common.exception.BussinessException;
import com.stylefeng.guns.common.persistence.dao.NewsMapper;
import com.stylefeng.guns.common.persistence.model.News;
import com.stylefeng.guns.config.properties.GunsProperties;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.stylefeng.guns.core.util.DateUtil;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.system.dao.ColumnDao;
import com.stylefeng.guns.modular.system.service.INewsService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Controller
@RequestMapping("/news")
public class NewsController extends BaseController{
	
	
	private String PREFIX = "/system/news/";

	@Resource
	private INewsService newsServiceImpl;
	@Resource
	private NewsMapper newsMapper;
	@Resource
	private ColumnDao columnDao;
    @Resource
    private GunsProperties gunsProperties;
	/**
     * 跳转到新闻管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "news.html";
    }

    /**
     * 跳转到添加新闻
     */
    @RequestMapping("/news_add")
    public String presentAdd(Model model) {
    	List<Map<String, Object>> columns = new ArrayList<>();
    	if(ShiroKit.isAdmin()){
    		columns = columnDao.list(null,null,null,null);
    	}else{
    		columns = columnDao.list(null,null,null,ShiroKit.getUser().getDeptId());
    	}
    	model.addAttribute("columns", columns);
        return PREFIX + "news_add.html";
    }
    
    /**
     * 跳转到修改新闻
     */
    @RequestMapping("/news_edit/{newsId}")
    public String presentDetail(@PathVariable Integer newsId, Model model) {
    	List<Map<String, Object>> columns = new ArrayList<>();
    	if(ShiroKit.isAdmin()){
    		columns = columnDao.list(null,null,null,null);
    	}else{
    		columns = columnDao.list(null,null,null,ShiroKit.getUser().getDeptId());
    	}
    	News news = newsMapper.selectById(newsId);
    	news.setHtmlContent(HtmlUtils.htmlUnescape(news.getHtmlContent()));    	
    	news.setSubtitle(HtmlUtils.htmlUnescape(news.getSubtitle()));    	
    	model.addAttribute("columns", columns);
    	model.addAttribute("news",news);
    	model.addAttribute("createDate", news.getCreatDate());
    	return PREFIX + "news_edit.html";
    }
    
    /**
     * 获取所有新闻列表
     */
    @ApiOperation(value = "获取新闻列表", notes = "关键字，栏目Id，新闻Id")
    @RequestMapping(value = "/list",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Object list(
    		@ApiParam("新闻分页页码，非必填") @RequestParam(value = "pagenum", required = false)Integer pagenum,
    		@ApiParam("新闻数量，非必填") @RequestParam(value = "pagesize", required = false)Integer pagesize,
    		@ApiParam("关键字，非必填") @RequestParam(value = "condition", required = false)String condition,
    		@ApiParam("栏目Id，非必填") @RequestParam(value = "cloumnId", required = false)Integer cloumnId,
    		@ApiParam("部门Id，必填") @RequestParam(value = "deptid", required = true)Integer deptid,
    		@ApiParam("新闻Id，非必填") @RequestParam(value = "id", required = false)Integer id,
    		@ApiParam("是否置顶（1.是 ，0.否），非必填") @RequestParam(value = "istop", required = false)Integer istop) { 
    	List<Integer> cloumns = columnDao.getColumnByDept(deptid);   
        return newsServiceImpl.list(condition, cloumnId,id,istop,pagenum,pagesize,cloumns);
    }
    
    /**
     * 获取所有新闻列表
     */
    @RequestMapping(value = "/adminList",method = RequestMethod.POST)
    @ResponseBody
    public Object adminList(
    		@ApiParam("新闻分页页码，非必填") @RequestParam(value = "offset", required = false)Integer offset,
    		@ApiParam("新闻数量，非必填") @RequestParam(value = "limit", required = false)Integer limit,
    		@ApiParam("关键字，非必填") @RequestParam(value = "condition", required = false)String condition,
    		@ApiParam("栏目Id，非必填") @RequestParam(value = "cloumnId", required = false)Integer cloumnId,
    		@ApiParam("新闻Id，非必填") @RequestParam(value = "id", required = false)Integer id,
    		@ApiParam("是否置顶（1.是 ，0.否），非必填") @RequestParam(value = "istop", required = false)Integer istop) {
    	//获取当前管理员的部门对应的栏目
    	List<Map<String, Object>> newsList;
    	Integer deptid = ShiroKit.getUser().getDeptId();
    	List<Integer> cloumns = columnDao.getColumnByDept(deptid);
    	if(ShiroKit.isAdmin()){
    		newsList = newsServiceImpl.adminList(condition, cloumnId,id,istop,offset,limit,null);
    	}else{	   
	    	if(ToolUtil.isEmpty(cloumns)){
	    		return null;
	    	}
	    	newsList = newsServiceImpl.adminList(condition, cloumnId,id,istop,offset,limit,cloumns);
    	}
    	JSONObject result = new JSONObject();
    	result.put("rows", newsList);
    	result.put("total", newsServiceImpl.countNews(cloumnId, condition,cloumns));
    	return result;
    }
    
    
    /**
     * 添加新闻
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(@Valid News news) {
    	if(ToolUtil.isEmpty(news.getCreatDate())){
    		news.setCreatDate(DateUtil.getTime());
    	}
    	news.setUserName(ShiroKit.getUser().getName());
    	Integer num = newsMapper.insert(news);
    	if(num == 1){
    		return SUCCESS_TIP;
    	}
    	return ERROR;
    }
    
    /**
     * 修改新闻
     */
    @RequestMapping(value = "/edit")
    @ResponseBody
    public Object edit(@Valid News news) {
    	news.setModifyDate(DateUtil.getTime());
    	news.setUserName(ShiroKit.getUser().getName());
    	Integer num = newsMapper.updateById(news);
    	if(num == 1){
    		return SUCCESS_TIP;
    	}
    	return ERROR;
    }
    
    /**
     * 添加新闻
     */
    @RequestMapping(value = "/remove")
    @ResponseBody
    public Object remove(Integer newsId) {

    	Integer num = newsMapper.deleteById(newsId);
    	if(num == 1){
    		return SUCCESS_TIP;
    	}
    	return ERROR;
    }
    /**
     * 获取新闻条数
     */
    @ApiOperation(value = "获取新闻条数", notes = "可直接获取")
    @RequestMapping(value = "/count",method = RequestMethod.POST)
    @ResponseBody
    public Object count(
    		@ApiParam("栏目id，非必填") @RequestParam(value = "cloumnId", required = false)Integer cloumnId,
    		@ApiParam("关键字，非必填") @RequestParam(value = "condition", required = false)String condition
    		) { 	
    	Integer num = newsServiceImpl.countNews(cloumnId,condition,null);
    	if(num != null){
    		return num;
    	}
    	return ERROR;
    }
    
    /**
     * 上传图片(上传到项目的webapp/static/img)
     */
    @RequestMapping(method = RequestMethod.POST, path = "/upload")
    @ResponseBody
    public String upload(@RequestPart("upload") MultipartFile picture,String CKEditorFuncNum) {
    	
    	String filename = picture.getOriginalFilename();
    	String[] name = filename.split("\\.");
    	String suffix = name[name.length-1];
        String pictureName = UUID.randomUUID().toString() +"."+ suffix;
        try {
            String fileSavePath = gunsProperties.getFileUploadPath();
            picture.transferTo(new File(fileSavePath + pictureName));
        } catch (Exception e) {
            throw new BussinessException(BizExceptionEnum.UPLOAD_ERROR);
        }
        String result ="<script type=\"text/javascript\">window.parent.CKEDITOR.tools.callFunction("+ CKEditorFuncNum + ",'"+gunsProperties.getProjectPathHttp()+"/kaptcha/"+ pictureName + "','')</script>";
        return result;
    }

}
