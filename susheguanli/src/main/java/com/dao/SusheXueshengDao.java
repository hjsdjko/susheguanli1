package com.dao;

import com.entity.SusheXueshengEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.SusheXueshengView;

/**
 * 宿舍成员 Dao 接口
 *
 * @author 
 */
public interface SusheXueshengDao extends BaseMapper<SusheXueshengEntity> {

   List<SusheXueshengView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
