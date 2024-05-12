package com.entity.model;

import com.entity.SusheweishengEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 宿舍卫生
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class SusheweishengModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 宿舍
     */
    private Integer susheId;


    /**
     * 宿舍卫生编号
     */
    private String susheweishengUuidNumnber;


    /**
     * 卫生状态
     */
    private Integer susheweishengTypes;


    /**
     * 打分
     */
    private Double dafen;


    /**
     * 所属日期
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date riqiTime;


    /**
     * 卫生详情
     */
    private String susheweishengContent;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 创建时间 show3 listShow
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date createTime;


    /**
	 * 获取：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 设置：主键
	 */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 获取：宿舍
	 */
    public Integer getSusheId() {
        return susheId;
    }


    /**
	 * 设置：宿舍
	 */
    public void setSusheId(Integer susheId) {
        this.susheId = susheId;
    }
    /**
	 * 获取：宿舍卫生编号
	 */
    public String getSusheweishengUuidNumnber() {
        return susheweishengUuidNumnber;
    }


    /**
	 * 设置：宿舍卫生编号
	 */
    public void setSusheweishengUuidNumnber(String susheweishengUuidNumnber) {
        this.susheweishengUuidNumnber = susheweishengUuidNumnber;
    }
    /**
	 * 获取：卫生状态
	 */
    public Integer getSusheweishengTypes() {
        return susheweishengTypes;
    }


    /**
	 * 设置：卫生状态
	 */
    public void setSusheweishengTypes(Integer susheweishengTypes) {
        this.susheweishengTypes = susheweishengTypes;
    }
    /**
	 * 获取：打分
	 */
    public Double getDafen() {
        return dafen;
    }


    /**
	 * 设置：打分
	 */
    public void setDafen(Double dafen) {
        this.dafen = dafen;
    }
    /**
	 * 获取：所属日期
	 */
    public Date getRiqiTime() {
        return riqiTime;
    }


    /**
	 * 设置：所属日期
	 */
    public void setRiqiTime(Date riqiTime) {
        this.riqiTime = riqiTime;
    }
    /**
	 * 获取：卫生详情
	 */
    public String getSusheweishengContent() {
        return susheweishengContent;
    }


    /**
	 * 设置：卫生详情
	 */
    public void setSusheweishengContent(String susheweishengContent) {
        this.susheweishengContent = susheweishengContent;
    }
    /**
	 * 获取：录入时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 设置：录入时间
	 */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：创建时间 show3 listShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间 show3 listShow
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
