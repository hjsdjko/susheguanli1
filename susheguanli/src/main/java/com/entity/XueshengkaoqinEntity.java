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
 * 学生考勤
 *
 * @author 
 * @email
 */
@TableName("xueshengkaoqin")
public class XueshengkaoqinEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public XueshengkaoqinEntity() {

	}

	public XueshengkaoqinEntity(T t) {
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
     * 学生
     */
    @ColumnInfo(comment="学生",type="int(11)")
    @TableField(value = "xuesheng_id")

    private Integer xueshengId;


    /**
     * 考勤结果
     */
    @ColumnInfo(comment="考勤结果",type="int(11)")
    @TableField(value = "xueshengkaoqin_types")

    private Integer xueshengkaoqinTypes;


    /**
     * 考勤详情
     */
    @ColumnInfo(comment="考勤详情",type="text")
    @TableField(value = "xueshengkaoqin_content")

    private String xueshengkaoqinContent;


    /**
     * 考勤日期
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
	@DateTimeFormat
    @ColumnInfo(comment="考勤日期",type="date")
    @TableField(value = "kaoqin_time")

    private Date kaoqinTime;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="录入时间",type="timestamp")
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 创建时间
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
	 * 获取：学生
	 */
    public Integer getXueshengId() {
        return xueshengId;
    }
    /**
	 * 设置：学生
	 */

    public void setXueshengId(Integer xueshengId) {
        this.xueshengId = xueshengId;
    }
    /**
	 * 获取：考勤结果
	 */
    public Integer getXueshengkaoqinTypes() {
        return xueshengkaoqinTypes;
    }
    /**
	 * 设置：考勤结果
	 */

    public void setXueshengkaoqinTypes(Integer xueshengkaoqinTypes) {
        this.xueshengkaoqinTypes = xueshengkaoqinTypes;
    }
    /**
	 * 获取：考勤详情
	 */
    public String getXueshengkaoqinContent() {
        return xueshengkaoqinContent;
    }
    /**
	 * 设置：考勤详情
	 */

    public void setXueshengkaoqinContent(String xueshengkaoqinContent) {
        this.xueshengkaoqinContent = xueshengkaoqinContent;
    }
    /**
	 * 获取：考勤日期
	 */
    public Date getKaoqinTime() {
        return kaoqinTime;
    }
    /**
	 * 设置：考勤日期
	 */

    public void setKaoqinTime(Date kaoqinTime) {
        this.kaoqinTime = kaoqinTime;
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
	 * 获取：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }
    /**
	 * 设置：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Xueshengkaoqin{" +
            ", id=" + id +
            ", xueshengId=" + xueshengId +
            ", xueshengkaoqinTypes=" + xueshengkaoqinTypes +
            ", xueshengkaoqinContent=" + xueshengkaoqinContent +
            ", kaoqinTime=" + DateUtil.convertString(kaoqinTime,"yyyy-MM-dd") +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
