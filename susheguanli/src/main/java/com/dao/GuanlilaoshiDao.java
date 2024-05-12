package com.dao;

import com.entity.GuanlilaoshiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.GuanlilaoshiView;

/**
 * 管理老师 Dao 接口
 *
 * @author 
 */
public interface GuanlilaoshiDao extends BaseMapper<GuanlilaoshiEntity> {

   List<GuanlilaoshiView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
