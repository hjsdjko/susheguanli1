package com.entity.vo;

import com.entity.GuanlilaoshiEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 管理老师
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("guanlilaoshi")
public class GuanlilaoshiVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 账户
     */

    @TableField(value = "username")
    private String username;


    /**
     * 密码
     */

    @TableField(value = "password")
    private String password;


    /**
     * 管理老师姓名
     */

    @TableField(value = "guanlilaoshi_name")
    private String guanlilaoshiName;


    /**
     * 管理老师手机号
     */

    @TableField(value = "guanlilaoshi_phone")
    private String guanlilaoshiPhone;


    /**
     * 管理老师身份证号
     */

    @TableField(value = "guanlilaoshi_id_number")
    private String guanlilaoshiIdNumber;


    /**
     * 管理老师头像
     */

    @TableField(value = "guanlilaoshi_photo")
    private String guanlilaoshiPhoto;


    /**
     * 性别
     */

    @TableField(value = "sex_types")
    private Integer sexTypes;


    /**
     * 管理老师邮箱
     */

    @TableField(value = "guanlilaoshi_email")
    private String guanlilaoshiEmail;


    /**
     * 账户状态
     */

    @TableField(value = "jinyong_types")
    private Integer jinyongTypes;


    /**
     * 创建时间
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
	 * 设置：账户
	 */
    public String getUsername() {
        return username;
    }


    /**
	 * 获取：账户
	 */

    public void setUsername(String username) {
        this.username = username;
    }
    /**
	 * 设置：密码
	 */
    public String getPassword() {
        return password;
    }


    /**
	 * 获取：密码
	 */

    public void setPassword(String password) {
        this.password = password;
    }
    /**
	 * 设置：管理老师姓名
	 */
    public String getGuanlilaoshiName() {
        return guanlilaoshiName;
    }


    /**
	 * 获取：管理老师姓名
	 */

    public void setGuanlilaoshiName(String guanlilaoshiName) {
        this.guanlilaoshiName = guanlilaoshiName;
    }
    /**
	 * 设置：管理老师手机号
	 */
    public String getGuanlilaoshiPhone() {
        return guanlilaoshiPhone;
    }


    /**
	 * 获取：管理老师手机号
	 */

    public void setGuanlilaoshiPhone(String guanlilaoshiPhone) {
        this.guanlilaoshiPhone = guanlilaoshiPhone;
    }
    /**
	 * 设置：管理老师身份证号
	 */
    public String getGuanlilaoshiIdNumber() {
        return guanlilaoshiIdNumber;
    }


    /**
	 * 获取：管理老师身份证号
	 */

    public void setGuanlilaoshiIdNumber(String guanlilaoshiIdNumber) {
        this.guanlilaoshiIdNumber = guanlilaoshiIdNumber;
    }
    /**
	 * 设置：管理老师头像
	 */
    public String getGuanlilaoshiPhoto() {
        return guanlilaoshiPhoto;
    }


    /**
	 * 获取：管理老师头像
	 */

    public void setGuanlilaoshiPhoto(String guanlilaoshiPhoto) {
        this.guanlilaoshiPhoto = guanlilaoshiPhoto;
    }
    /**
	 * 设置：性别
	 */
    public Integer getSexTypes() {
        return sexTypes;
    }


    /**
	 * 获取：性别
	 */

    public void setSexTypes(Integer sexTypes) {
        this.sexTypes = sexTypes;
    }
    /**
	 * 设置：管理老师邮箱
	 */
    public String getGuanlilaoshiEmail() {
        return guanlilaoshiEmail;
    }


    /**
	 * 获取：管理老师邮箱
	 */

    public void setGuanlilaoshiEmail(String guanlilaoshiEmail) {
        this.guanlilaoshiEmail = guanlilaoshiEmail;
    }
    /**
	 * 设置：账户状态
	 */
    public Integer getJinyongTypes() {
        return jinyongTypes;
    }


    /**
	 * 获取：账户状态
	 */

    public void setJinyongTypes(Integer jinyongTypes) {
        this.jinyongTypes = jinyongTypes;
    }
    /**
	 * 设置：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
