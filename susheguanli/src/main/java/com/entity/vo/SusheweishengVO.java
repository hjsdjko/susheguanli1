package com.entity.vo;

import com.entity.SusheweishengEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 宿舍卫生
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("susheweisheng")
public class SusheweishengVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 宿舍
     */

    @TableField(value = "sushe_id")
    private Integer susheId;


    /**
     * 宿舍卫生编号
     */

    @TableField(value = "susheweisheng_uuid_numnber")
    private String susheweishengUuidNumnber;


    /**
     * 卫生状态
     */

    @TableField(value = "susheweisheng_types")
    private Integer susheweishengTypes;


    /**
     * 打分
     */

    @TableField(value = "dafen")
    private Double dafen;


    /**
     * 所属日期
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "riqi_time")
    private Date riqiTime;


    /**
     * 卫生详情
     */

    @TableField(value = "susheweisheng_content")
    private String susheweishengContent;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


    /**
     * 创建时间 show3 listShow
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "create_time")
    private Date createTime;


    /**
	 * 设置：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 获取：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 设置：宿舍
	 */
    public Integer getSusheId() {
        return susheId;
    }


    /**
	 * 获取：宿舍
	 */

    public void setSusheId(Integer susheId) {
        this.susheId = susheId;
    }
    /**
	 * 设置：宿舍卫生编号
	 */
    public String getSusheweishengUuidNumnber() {
        return susheweishengUuidNumnber;
    }


    /**
	 * 获取：宿舍卫生编号
	 */

    public void setSusheweishengUuidNumnber(String susheweishengUuidNumnber) {
        this.susheweishengUuidNumnber = susheweishengUuidNumnber;
    }
    /**
	 * 设置：卫生状态
	 */
    public Integer getSusheweishengTypes() {
        return susheweishengTypes;
    }


    /**
	 * 获取：卫生状态
	 */

    public void setSusheweishengTypes(Integer susheweishengTypes) {
        this.susheweishengTypes = susheweishengTypes;
    }
    /**
	 * 设置：打分
	 */
    public Double getDafen() {
        return dafen;
    }


    /**
	 * 获取：打分
	 */

    public void setDafen(Double dafen) {
        this.dafen = dafen;
    }
    /**
	 * 设置：所属日期
	 */
    public Date getRiqiTime() {
        return riqiTime;
    }


    /**
	 * 获取：所属日期
	 */

    public void setRiqiTime(Date riqiTime) {
        this.riqiTime = riqiTime;
    }
    /**
	 * 设置：卫生详情
	 */
    public String getSusheweishengContent() {
        return susheweishengContent;
    }


    /**
	 * 获取：卫生详情
	 */

    public void setSusheweishengContent(String susheweishengContent) {
        this.susheweishengContent = susheweishengContent;
    }
    /**
	 * 设置：录入时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：录入时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：创建时间 show3 listShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间 show3 listShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
