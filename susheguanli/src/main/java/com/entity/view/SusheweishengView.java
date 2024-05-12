package com.entity.view;

import org.apache.tools.ant.util.DateUtils;
import com.annotation.ColumnInfo;
import com.entity.SusheweishengEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import com.utils.DateUtil;

/**
* 宿舍卫生
* 后端返回视图实体辅助类
* （通常后端关联的表或者自定义的字段需要返回使用）
*/
@TableName("susheweisheng")
public class SusheweishengView extends SusheweishengEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//当前表
	/**
	* 卫生状态的值
	*/
	@ColumnInfo(comment="卫生状态的字典表值",type="varchar(200)")
	private String susheweishengValue;

	//级联表 宿舍
		/**
		* 宿舍编号
		*/

		@ColumnInfo(comment="宿舍编号",type="varchar(200)")
		private String susheUuidNumber;
		/**
		* 楼栋
		*/
		@ColumnInfo(comment="楼栋",type="int(11)")
		private Integer loudongTypes;
			/**
			* 楼栋的值
			*/
			@ColumnInfo(comment="楼栋的字典表值",type="varchar(200)")
			private String loudongValue;
		/**
		* 单元
		*/
		@ColumnInfo(comment="单元",type="int(11)")
		private Integer danyuanTypes;
			/**
			* 单元的值
			*/
			@ColumnInfo(comment="单元的字典表值",type="varchar(200)")
			private String danyuanValue;
		/**
		* 宿舍名称
		*/

		@ColumnInfo(comment="宿舍名称",type="varchar(200)")
		private String susheName;
		/**
		* 宿舍位置
		*/

		@ColumnInfo(comment="宿舍位置",type="varchar(200)")
		private String susheAddress;
		/**
		* 宿舍备注
		*/

		@ColumnInfo(comment="宿舍备注",type="text")
		private String susheContent;



	public SusheweishengView() {

	}

	public SusheweishengView(SusheweishengEntity susheweishengEntity) {
		try {
			BeanUtils.copyProperties(this, susheweishengEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	//当前表的
	/**
	* 获取： 卫生状态的值
	*/
	public String getSusheweishengValue() {
		return susheweishengValue;
	}
	/**
	* 设置： 卫生状态的值
	*/
	public void setSusheweishengValue(String susheweishengValue) {
		this.susheweishengValue = susheweishengValue;
	}


	//级联表的get和set 宿舍

		/**
		* 获取： 宿舍编号
		*/
		public String getSusheUuidNumber() {
			return susheUuidNumber;
		}
		/**
		* 设置： 宿舍编号
		*/
		public void setSusheUuidNumber(String susheUuidNumber) {
			this.susheUuidNumber = susheUuidNumber;
		}
		/**
		* 获取： 楼栋
		*/
		public Integer getLoudongTypes() {
			return loudongTypes;
		}
		/**
		* 设置： 楼栋
		*/
		public void setLoudongTypes(Integer loudongTypes) {
			this.loudongTypes = loudongTypes;
		}


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
		/**
		* 获取： 单元
		*/
		public Integer getDanyuanTypes() {
			return danyuanTypes;
		}
		/**
		* 设置： 单元
		*/
		public void setDanyuanTypes(Integer danyuanTypes) {
			this.danyuanTypes = danyuanTypes;
		}


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

		/**
		* 获取： 宿舍名称
		*/
		public String getSusheName() {
			return susheName;
		}
		/**
		* 设置： 宿舍名称
		*/
		public void setSusheName(String susheName) {
			this.susheName = susheName;
		}

		/**
		* 获取： 宿舍位置
		*/
		public String getSusheAddress() {
			return susheAddress;
		}
		/**
		* 设置： 宿舍位置
		*/
		public void setSusheAddress(String susheAddress) {
			this.susheAddress = susheAddress;
		}

		/**
		* 获取： 宿舍备注
		*/
		public String getSusheContent() {
			return susheContent;
		}
		/**
		* 设置： 宿舍备注
		*/
		public void setSusheContent(String susheContent) {
			this.susheContent = susheContent;
		}


	@Override
	public String toString() {
		return "SusheweishengView{" +
			", susheweishengValue=" + susheweishengValue +
			", susheUuidNumber=" + susheUuidNumber +
			", susheName=" + susheName +
			", susheAddress=" + susheAddress +
			", susheContent=" + susheContent +
			"} " + super.toString();
	}
}
