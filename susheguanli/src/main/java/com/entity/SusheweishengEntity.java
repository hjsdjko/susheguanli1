package com.entity;

import com.annotation.ColumnInfo;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;
import java.io.Serializable;
import java.util.*;
import org.apache.tools.ant.util.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.utils.DateUtil;


/**
 * 宿舍卫生
 *
 * @author 
 * @email
 */
@TableName("susheweisheng")
public class SusheweishengEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public SusheweishengEntity() {

	}

	public SusheweishengEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @ColumnInfo(comment="主键",type="int(11)")
    @TableField(value = "id")

    private Integer id;


    /**
     * 宿舍
     */
    @ColumnInfo(comment="宿舍",type="int(11)")
    @TableField(value = "sushe_id")

    private Integer susheId;


    /**
     * 宿舍卫生编号
     */
    @ColumnInfo(comment="宿舍卫生编号",type="varchar(200)")
    @TableField(value = "susheweisheng_uuid_numnber")

    private String susheweishengUuidNumnber;


    /**
     * 卫生状态
     */
    @ColumnInfo(comment="卫生状态",type="int(11)")
    @TableField(value = "susheweisheng_types")

    private Integer susheweishengTypes;


    /**
     * 打分
     */
    @ColumnInfo(comment="打分",type="decimal(10,2)")
    @TableField(value = "dafen")

    private Double dafen;


    /**
     * 所属日期
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
	@DateTimeFormat
    @ColumnInfo(comment="所属日期",type="date")
    @TableField(value = "riqi_time")

    private Date riqiTime;


    /**
     * 卫生详情
     */
    @ColumnInfo(comment="卫生详情",type="text")
    @TableField(value = "susheweisheng_content")

    private String susheweishengContent;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="录入时间",type="timestamp")
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 创建时间  listShow
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="创建时间",type="timestamp")
    @TableField(value = "create_time",fill = FieldFill.INSERT)

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
	 * 获取：创建时间  listShow
	 */
    public Date getCreateTime() {
        return createTime;
    }
    /**
	 * 设置：创建时间  listShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Susheweisheng{" +
            ", id=" + id +
            ", susheId=" + susheId +
            ", susheweishengUuidNumnber=" + susheweishengUuidNumnber +
            ", susheweishengTypes=" + susheweishengTypes +
            ", dafen=" + dafen +
            ", riqiTime=" + DateUtil.convertString(riqiTime,"yyyy-MM-dd") +
            ", susheweishengContent=" + susheweishengContent +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
