package com.dao;

import com.entity.XueshengkaoqinEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.XueshengkaoqinView;

/**
 * 学生考勤 Dao 接口
 *
 * @author 
 */
public interface XueshengkaoqinDao extends BaseMapper<XueshengkaoqinEntity> {

   List<XueshengkaoqinView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
