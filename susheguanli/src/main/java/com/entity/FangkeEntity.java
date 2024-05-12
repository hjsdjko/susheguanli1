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
 * 访客
 *
 * @author 
 * @email
 */
@TableName("fangke")
public class FangkeEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public FangkeEntity() {

	}

	public FangkeEntity(T t) {
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
     * 访客姓名
     */
    @ColumnInfo(comment="访客姓名",type="varchar(200)")
    @TableField(value = "fangke_name")

    private String fangkeName;


    /**
     * 访客手机号
     */
    @ColumnInfo(comment="访客手机号",type="varchar(200)")
    @TableField(value = "fangke_phone")

    private String fangkePhone;


    /**
     * 访客身份证号
     */
    @ColumnInfo(comment="访客身份证号",type="varchar(200)")
    @TableField(value = "fangke_id_number")

    private String fangkeIdNumber;


    /**
     * 性别
     */
    @ColumnInfo(comment="性别",type="int(11)")
    @TableField(value = "sex_types")

    private Integer sexTypes;


    /**
     * 健康码
     */
    @ColumnInfo(comment="健康码",type="varchar(200)")
    @TableField(value = "fangke_photo")

    private String fangkePhoto;


    /**
     * 来访事由
     */
    @ColumnInfo(comment="来访事由",type="text")
    @TableField(value = "fangke_content")

    private String fangkeContent;


    /**
     * 来访时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="来访时间",type="timestamp")
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
	 * 获取：访客姓名
	 */
    public String getFangkeName() {
        return fangkeName;
    }
    /**
	 * 设置：访客姓名
	 */

    public void setFangkeName(String fangkeName) {
        this.fangkeName = fangkeName;
    }
    /**
	 * 获取：访客手机号
	 */
    public String getFangkePhone() {
        return fangkePhone;
    }
    /**
	 * 设置：访客手机号
	 */

    public void setFangkePhone(String fangkePhone) {
        this.fangkePhone = fangkePhone;
    }
    /**
	 * 获取：访客身份证号
	 */
    public String getFangkeIdNumber() {
        return fangkeIdNumber;
    }
    /**
	 * 设置：访客身份证号
	 */

    public void setFangkeIdNumber(String fangkeIdNumber) {
        this.fangkeIdNumber = fangkeIdNumber;
    }
    /**
	 * 获取：性别
	 */
    public Integer getSexTypes() {
        return sexTypes;
    }
    /**
	 * 设置：性别
	 */

    public void setSexTypes(Integer sexTypes) {
        this.sexTypes = sexTypes;
    }
    /**
	 * 获取：健康码
	 */
    public String getFangkePhoto() {
        return fangkePhoto;
    }
    /**
	 * 设置：健康码
	 */

    public void setFangkePhoto(String fangkePhoto) {
        this.fangkePhoto = fangkePhoto;
    }
    /**
	 * 获取：来访事由
	 */
    public String getFangkeContent() {
        return fangkeContent;
    }
    /**
	 * 设置：来访事由
	 */

    public void setFangkeContent(String fangkeContent) {
        this.fangkeContent = fangkeContent;
    }
    /**
	 * 获取：来访时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }
    /**
	 * 设置：来访时间
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
        return "Fangke{" +
            ", id=" + id +
            ", xueshengId=" + xueshengId +
            ", fangkeName=" + fangkeName +
            ", fangkePhone=" + fangkePhone +
            ", fangkeIdNumber=" + fangkeIdNumber +
            ", sexTypes=" + sexTypes +
            ", fangkePhoto=" + fangkePhoto +
            ", fangkeContent=" + fangkeContent +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
