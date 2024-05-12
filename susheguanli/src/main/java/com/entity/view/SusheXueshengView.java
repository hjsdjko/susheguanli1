package com.entity.view;

import org.apache.tools.ant.util.DateUtils;
import com.annotation.ColumnInfo;
import com.entity.SusheXueshengEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import com.utils.DateUtil;

/**
* 宿舍成员
* 后端返回视图实体辅助类
* （通常后端关联的表或者自定义的字段需要返回使用）
*/
@TableName("sushe_xuesheng")
public class SusheXueshengView extends SusheXueshengEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//当前表

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
	//级联表 学生
		/**
		* 学生姓名
		*/

		@ColumnInfo(comment="学生姓名",type="varchar(200)")
		private String xueshengName;
		/**
		* 学生手机号
		*/

		@ColumnInfo(comment="学生手机号",type="varchar(200)")
		private String xueshengPhone;
		/**
		* 学生身份证号
		*/

		@ColumnInfo(comment="学生身份证号",type="varchar(200)")
		private String xueshengIdNumber;
		/**
		* 学生头像
		*/

		@ColumnInfo(comment="学生头像",type="varchar(200)")
		private String xueshengPhoto;
		/**
		* 学生邮箱
		*/

		@ColumnInfo(comment="学生邮箱",type="varchar(200)")
		private String xueshengEmail;
		/**
		* 账户状态
		*/
		@ColumnInfo(comment="账户状态",type="int(11)")
		private Integer jinyongTypes;
			/**
			* 账户状态的值
			*/
			@ColumnInfo(comment="账户状态的字典表值",type="varchar(200)")
			private String jinyongValue;



	public SusheXueshengView() {

	}

	public SusheXueshengView(SusheXueshengEntity susheXueshengEntity) {
		try {
			BeanUtils.copyProperties(this, susheXueshengEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	//级联表的get和set 学生

		/**
		* 获取： 学生姓名
		*/
		public String getXueshengName() {
			return xueshengName;
		}
		/**
		* 设置： 学生姓名
		*/
		public void setXueshengName(String xueshengName) {
			this.xueshengName = xueshengName;
		}

		/**
		* 获取： 学生手机号
		*/
		public String getXueshengPhone() {
			return xueshengPhone;
		}
		/**
		* 设置： 学生手机号
		*/
		public void setXueshengPhone(String xueshengPhone) {
			this.xueshengPhone = xueshengPhone;
		}

		/**
		* 获取： 学生身份证号
		*/
		public String getXueshengIdNumber() {
			return xueshengIdNumber;
		}
		/**
		* 设置： 学生身份证号
		*/
		public void setXueshengIdNumber(String xueshengIdNumber) {
			this.xueshengIdNumber = xueshengIdNumber;
		}

		/**
		* 获取： 学生头像
		*/
		public String getXueshengPhoto() {
			return xueshengPhoto;
		}
		/**
		* 设置： 学生头像
		*/
		public void setXueshengPhoto(String xueshengPhoto) {
			this.xueshengPhoto = xueshengPhoto;
		}

		/**
		* 获取： 学生邮箱
		*/
		public String getXueshengEmail() {
			return xueshengEmail;
		}
		/**
		* 设置： 学生邮箱
		*/
		public void setXueshengEmail(String xueshengEmail) {
			this.xueshengEmail = xueshengEmail;
		}
		/**
		* 获取： 账户状态
		*/
		public Integer getJinyongTypes() {
			return jinyongTypes;
		}
		/**
		* 设置： 账户状态
		*/
		public void setJinyongTypes(Integer jinyongTypes) {
			this.jinyongTypes = jinyongTypes;
		}


			/**
			* 获取： 账户状态的值
			*/
			public String getJinyongValue() {
				return jinyongValue;
			}
			/**
			* 设置： 账户状态的值
			*/
			public void setJinyongValue(String jinyongValue) {
				this.jinyongValue = jinyongValue;
			}


	@Override
	public String toString() {
		return "SusheXueshengView{" +
			", xueshengName=" + xueshengName +
			", xueshengPhone=" + xueshengPhone +
			", xueshengIdNumber=" + xueshengIdNumber +
			", xueshengPhoto=" + xueshengPhoto +
			", xueshengEmail=" + xueshengEmail +
			", susheUuidNumber=" + susheUuidNumber +
			", susheName=" + susheName +
			", susheAddress=" + susheAddress +
			", susheContent=" + susheContent +
			"} " + super.toString();
	}
}
