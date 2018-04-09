package com.stylefeng.guns.common.persistence.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * <p>
 * 栏目表
 * </p>
 *
 * @author stylefeng
 * @since 2017-07-11
 */
public class Columns extends Model<Columns> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 菜单父编号
     */
    private Integer pid;
    /**
     * 当前菜单的所有父菜单编号
     */
    private String pids;
    /**
     * 菜单名称
     */
    @NotBlank
    private String name;
    /**
     * url地址
     */
    private String url;
    /**
     * 菜单排序号
     */
    private Integer num;
    /**
     * 是否置顶
     */
    private Integer istop;
    /**
     * 部门id
     */
    private Integer deptid;
    /**
     * 菜单层级
     */
    private Integer levels;
    /**
     * 备注
     */
    private String tips;
    /**
     * 菜单状态 :  1:启用   0:不启用
     */
    private Integer status;
    /**
     * 是否打开:    1:打开   0:不打开
     */
    private Integer isopen;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDeptid() {
		return deptid;
	}

	public void setDeptid(Integer deptid) {
		this.deptid = deptid;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getIstop() {
		return istop;
	}

	public void setIstop(Integer istop) {
		this.istop = istop;
	}

	public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getLevels() {
        return levels;
    }

    
    public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getPids() {
		return pids;
	}

	public void setPids(String pids) {
		this.pids = pids;
	}

	public void setLevels(Integer levels) {
        this.levels = levels;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsopen() {
        return isopen;
    }

    public void setIsopen(Integer isopen) {
        this.isopen = isopen;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", pcode=" + pid +
                ", pcodes=" + pids +
                ", name=" + name +
                ", url=" + url +
                ", num=" + num +
                ", levels=" + levels +
                ", tips=" + tips +
                ", status=" + status +
                ", isopen=" + isopen +
                ", istop=" + istop +
                ", deptid=" + deptid +
                "}";
    }
}
