package com.stylefeng.guns.modular.system.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.util.HtmlUtils;

import com.stylefeng.guns.common.annotion.Permission;
import com.stylefeng.guns.common.exception.BizExceptionEnum;
import com.stylefeng.guns.common.exception.BussinessException;
import com.stylefeng.guns.common.persistence.dao.ComplainMapper;
import com.stylefeng.guns.common.persistence.dao.DeptMapper;
import com.stylefeng.guns.common.persistence.dao.PresentMapper;
import com.stylefeng.guns.common.persistence.model.Complain;
import com.stylefeng.guns.common.persistence.model.Present;
import com.stylefeng.guns.config.properties.GunsProperties;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.util.DateUtil;
import com.stylefeng.guns.core.xss.XssFilter;
import com.stylefeng.guns.modular.system.dao.DeptDao;
import com.stylefeng.guns.modular.system.service.IColumnService;
import com.stylefeng.guns.modular.system.service.IComplainService;
import com.stylefeng.guns.modular.system.service.IDeptService;
import com.stylefeng.guns.modular.system.service.IPresentService;
import com.stylefeng.guns.modular.system.warpper.DeptWarpper;

import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/complain")
public class ComplainController extends BaseController {
	
	
	private String PREFIX = "/system/complain/";
	
    @Resource
    private GunsProperties gunsProperties;
	@Resource
	ComplainMapper complainMapper;
	@Resource
	IComplainService complainImpl;
	
    /**
     * 跳转到投诉管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "complain.html";
    }

    /**
     * 跳转到添加投诉页面
     */
    @RequestMapping("/complain_add")
    public String complainAdd() {
        return PREFIX + "complain_add.html";
    }
    
    /**
     * 跳转到修改投诉页面
     */
    @RequestMapping("/complain_edit/{complainId}")
    public String complainEdit(@PathVariable Integer complainId, Model model) {
    	Complain complain = complainMapper.selectById(complainId);
    	String html = complain.getFeedbackIdeaHtml();
    	complain.setFeedbackIdeaHtml(HtmlUtils.htmlUnescape(html));
    	model.addAttribute("complain", complain);
    	return PREFIX + "complain_edit.html";
    }
    
    /**
     * 跳转查看投诉页面
     */
    @RequestMapping("/complain_detail/{complainId}")
    public String complainDetail(@PathVariable Integer complainId, Model model) {
    	Complain complain = complainMapper.selectById(complainId);
    	String html = complain.getFeedbackIdeaHtml();
    	complain.setFeedbackIdeaHtml(HtmlUtils.htmlUnescape(html));
    	model.addAttribute("complain", complain);
    	return PREFIX + "complain_detail.html";
    }
    
    /**
     * 获取投诉列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String acceptName,String acceptType,String ischeck, Integer id) {
        return complainImpl.list(acceptName,acceptType,ischeck,id);
    }
    /**
     * 添加投诉
     */
    @ApiOperation("添加投诉")
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public Object add(@Valid Complain complain) {    	
    	complain.setCreatDate(DateUtil.getTime());
    	complain.setSn(DateUtil.getAllTime()+(int)(Math.random()*100000));
    	System.out.println(complain.getSn());
    	Integer num = complainMapper.insert(complain);
    	if(num == 1){
    		return complain.getSn();
    	}
    	return super.ERROR;
    }
    
    /**
     * 修改举报
     */
    @RequestMapping(value = "/edit")
    @ResponseBody
    public Object edit(@Valid Complain complain,String feedbackIdeaHtml) {
    	Integer num = complainMapper.updateAllColumnById(complain);
    	if(num == 1){
    		return super.SUCCESS_TIP;
    	}
    	return super.ERROR;
    }
    
    /**
     * 删除举报
     */
    @RequestMapping(value = "/remove")
    @ResponseBody
    public Object remove(Integer complainId) {
    	Integer num = complainMapper.deleteById(complainId);
    	if(num == 1){
    		return super.SUCCESS_TIP;
    	}
    	return super.ERROR;
    }
    
    /**
     * 上传文件
     */
    @RequestMapping(method = RequestMethod.POST, value = "/upload")
    @ResponseBody
    public String upload(@RequestPart("file") MultipartFile file) {
    	String filename = file.getOriginalFilename();
    	String[] name = filename.split("\\.");
    	String suffix = name[name.length-1];
        String fileName = UUID.randomUUID().toString() +"."+ suffix;
        try {
            String fileSavePath = gunsProperties.getFileUploadPath();
            file.transferTo(new File(fileSavePath + fileName));
        } catch (Exception e) {
            throw new BussinessException(BizExceptionEnum.UPLOAD_ERROR);
        }
        return fileName;
    }
    
    /**
     * 下载文件
     * 
     */
    @RequestMapping(method = RequestMethod.GET, path = "/down/{complainId}")
    @ResponseBody
    public Object fileDown(@PathVariable Integer complainId, Model model, HttpServletResponse response) {
    	Complain complain = new Complain();
    	complain = complainMapper.selectById(complainId);
        String filename = complain.getFileName();
        response.reset();
        //设置文件MIME类型  
        response.setContentType(complain.getFileType()+";charset=utf-8");
        //设置Content-Disposition 
        response.setCharacterEncoding("iso-8859-1");
        try {
			response.setHeader("Content-Disposition", "attachment;filename="+new String(filename.getBytes(), "iso-8859-1"));
		} catch (UnsupportedEncodingException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
        //读取目标文件，通过response将目标文件写到客户端  
        //获取目标文件的绝对路径  
        String fullFileName = gunsProperties.getFileUploadPath()+complain.getFile();
        //读取文件     
        BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
        try {
        InputStream in = new FileInputStream(fullFileName);  
        ServletOutputStream out = response.getOutputStream();
		
			bis = new BufferedInputStream(in);
			bos = new BufferedOutputStream(out);
			byte[] buff = new byte[2048];
			int bytesRead;
			// Simple read/write loop.
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
			if (bis != null)
				bis.close();
			if (bos != null)
				bos.close();
		} catch (IOException e) {
			e.getStackTrace();
		}
        return super.SUCCESS_TIP;
    }
    
}
