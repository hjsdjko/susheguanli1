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
 * 宿舍
 *
 * @author 
 * @email
 */
@TableName("sushe")
public class SusheEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public SusheEntity() {

	}

	public SusheEntity(T t) {
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
     * 宿舍编号
     */
    @ColumnInfo(comment="宿舍编号",type="varchar(200)")
    @TableField(value = "sushe_uuid_number")

    private String susheUuidNumber;


    /**
     * 楼栋
     */
    @ColumnInfo(comment="楼栋",type="int(11)")
    @TableField(value = "loudong_types")

    private Integer loudongTypes;


    /**
     * 单元
     */
    @ColumnInfo(comment="单元",type="int(11)")
    @TableField(value = "danyuan_types")

    private Integer danyuanTypes;


    /**
     * 宿舍名称
     */
    @ColumnInfo(comment="宿舍名称",type="varchar(200)")
    @TableField(value = "sushe_name")

    private String susheName;


    /**
     * 宿舍位置
     */
    @ColumnInfo(comment="宿舍位置",type="varchar(200)")
    @TableField(value = "sushe_address")

    private String susheAddress;


    /**
     * 宿舍备注
     */
    @ColumnInfo(comment="宿舍备注",type="text")
    @TableField(value = "sushe_content")

    private String susheContent;


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
	 * 获取：宿舍编号
	 */
    public String getSusheUuidNumber() {
        return susheUuidNumber;
    }
    /**
	 * 设置：宿舍编号
	 */

    public void setSusheUuidNumber(String susheUuidNumber) {
        this.susheUuidNumber = susheUuidNumber;
    }
    /**
	 * 获取：楼栋
	 */
    public Integer getLoudongTypes() {
        return loudongTypes;
    }
    /**
	 * 设置：楼栋
	 */

    public void setLoudongTypes(Integer loudongTypes) {
        this.loudongTypes = loudongTypes;
    }
    /**
	 * 获取：单元
	 */
    public Integer getDanyuanTypes() {
        return danyuanTypes;
    }
    /**
	 * 设置：单元
	 */

    public void setDanyuanTypes(Integer danyuanTypes) {
        this.danyuanTypes = danyuanTypes;
    }
    /**
	 * 获取：宿舍名称
	 */
    public String getSusheName() {
        return susheName;
    }
    /**
	 * 设置：宿舍名称
	 */

    public void setSusheName(String susheName) {
        this.susheName = susheName;
    }
    /**
	 * 获取：宿舍位置
	 */
    public String getSusheAddress() {
        return susheAddress;
    }
    /**
	 * 设置：宿舍位置
	 */

    public void setSusheAddress(String susheAddress) {
        this.susheAddress = susheAddress;
    }
    /**
	 * 获取：宿舍备注
	 */
    public String getSusheContent() {
        return susheContent;
    }
    /**
	 * 设置：宿舍备注
	 */

    public void setSusheContent(String susheContent) {
        this.susheContent = susheContent;
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
        return "Sushe{" +
            ", id=" + id +
            ", susheUuidNumber=" + susheUuidNumber +
            ", loudongTypes=" + loudongTypes +
            ", danyuanTypes=" + danyuanTypes +
            ", susheName=" + susheName +
            ", susheAddress=" + susheAddress +
            ", susheContent=" + susheContent +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
