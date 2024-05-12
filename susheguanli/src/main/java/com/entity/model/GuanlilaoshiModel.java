package com.entity.model;

import com.entity.GuanlilaoshiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 管理老师
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class GuanlilaoshiModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 账户
     */
    private String username;


    /**
     * 密码
     */
    private String password;


    /**
     * 管理老师姓名
     */
    private String guanlilaoshiName;


    /**
     * 管理老师手机号
     */
    private String guanlilaoshiPhone;


    /**
     * 管理老师身份证号
     */
    private String guanlilaoshiIdNumber;


    /**
     * 管理老师头像
     */
    private String guanlilaoshiPhoto;


    /**
     * 性别
     */
    private Integer sexTypes;


    /**
     * 管理老师邮箱
     */
    private String guanlilaoshiEmail;


    /**
     * 账户状态
     */
    private Integer jinyongTypes;


    /**
     * 创建时间
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
	 * 获取：账户
	 */
    public String getUsername() {
        return username;
    }


    /**
	 * 设置：账户
	 */
    public void setUsername(String username) {
        this.username = username;
    }
    /**
	 * 获取：密码
	 */
    public String getPassword() {
        return password;
    }


    /**
	 * 设置：密码
	 */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
	 * 获取：管理老师姓名
	 */
    public String getGuanlilaoshiName() {
        return guanlilaoshiName;
    }


    /**
	 * 设置：管理老师姓名
	 */
    public void setGuanlilaoshiName(String guanlilaoshiName) {
        this.guanlilaoshiName = guanlilaoshiName;
    }
    /**
	 * 获取：管理老师手机号
	 */
    public String getGuanlilaoshiPhone() {
        return guanlilaoshiPhone;
    }


    /**
	 * 设置：管理老师手机号
	 */
    public void setGuanlilaoshiPhone(String guanlilaoshiPhone) {
        this.guanlilaoshiPhone = guanlilaoshiPhone;
    }
    /**
	 * 获取：管理老师身份证号
	 */
    public String getGuanlilaoshiIdNumber() {
        return guanlilaoshiIdNumber;
    }


    /**
	 * 设置：管理老师身份证号
	 */
    public void setGuanlilaoshiIdNumber(String guanlilaoshiIdNumber) {
        this.guanlilaoshiIdNumber = guanlilaoshiIdNumber;
    }
    /**
	 * 获取：管理老师头像
	 */
    public String getGuanlilaoshiPhoto() {
        return guanlilaoshiPhoto;
    }


    /**
	 * 设置：管理老师头像
	 */
    public void setGuanlilaoshiPhoto(String guanlilaoshiPhoto) {
        this.guanlilaoshiPhoto = guanlilaoshiPhoto;
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
	 * 获取：管理老师邮箱
	 */
    public String getGuanlilaoshiEmail() {
        return guanlilaoshiEmail;
    }


    /**
	 * 设置：管理老师邮箱
	 */
    public void setGuanlilaoshiEmail(String guanlilaoshiEmail) {
        this.guanlilaoshiEmail = guanlilaoshiEmail;
    }
    /**
	 * 获取：账户状态
	 */
    public Integer getJinyongTypes() {
        return jinyongTypes;
    }


    /**
	 * 设置：账户状态
	 */
    public void setJinyongTypes(Integer jinyongTypes) {
        this.jinyongTypes = jinyongTypes;
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

    }
