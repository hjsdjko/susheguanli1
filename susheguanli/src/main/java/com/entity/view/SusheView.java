package com.entity.view;

import org.apache.tools.ant.util.DateUtils;
import com.annotation.ColumnInfo;
import com.entity.SusheEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import com.utils.DateUtil;

/**
* 宿舍
* 后端返回视图实体辅助类
* （通常后端关联的表或者自定义的字段需要返回使用）
*/
@TableName("sushe")
public class SusheView extends SusheEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//当前表
	/**
	* 楼栋的值
	*/
	@ColumnInfo(comment="楼栋的字典表值",type="varchar(200)")
	private String loudongValue;
	/**
	* 单元的值
	*/
	@ColumnInfo(comment="单元的字典表值",type="varchar(200)")
	private String danyuanValue;




	public SusheView() {

	}

	public SusheView(SusheEntity susheEntity) {
		try {
			BeanUtils.copyProperties(this, susheEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	//当前表的
	/**
	* 获取： 楼栋的值
	*/
	public String getLoudongValue() {
		return loudongValue;
	}
	/**
	* 设置： 楼栋的值
	*/
	public void setLoudongValue(String loudongValue) {
		this.loudongValue = loudongValue;
	}
	//当前表的
	/**
	* 获取： 单元的值
	*/
	public String getDanyuanValue() {
		return danyuanValue;
	}
	/**
	* 设置： 单元的值
	*/
	public void setDanyuanValue(String danyuanValue) {
		this.danyuanValue = danyuanValue;
	}




	@Override
	public String toString() {
		return "SusheView{" +
			", loudongValue=" + loudongValue +
			", danyuanValue=" + danyuanValue +
			"} " + super.toString();
	}
}
