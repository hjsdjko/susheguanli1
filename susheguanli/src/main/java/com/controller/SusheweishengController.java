
package com.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.*;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.*;
import com.entity.view.*;
import com.service.*;
import com.utils.PageUtils;
import com.utils.R;
import com.alibaba.fastjson.*;

/**
 * 宿舍卫生
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/susheweisheng")
public class SusheweishengController {
    private static final Logger logger = LoggerFactory.getLogger(SusheweishengController.class);

    private static final String TABLE_NAME = "susheweisheng";

    @Autowired
    private SusheweishengService susheweishengService;


    @Autowired
    private TokenService tokenService;

    @Autowired
    private BaoxiuService baoxiuService;//报修
    @Autowired
    private DictionaryService dictionaryService;//字典
    @Autowired
    private FangkeService fangkeService;//访客
    @Autowired
    private GonggaoService gonggaoService;//公告
    @Autowired
    private GuanlilaoshiService guanlilaoshiService;//管理老师
    @Autowired
    private NewsService newsService;//新闻信息
    @Autowired
    private SusheService susheService;//宿舍
    @Autowired
    private SusheXueshengService susheXueshengService;//宿舍成员
    @Autowired
    private XueshengService xueshengService;//学生
    @Autowired
    private XueshengkaoqinService xueshengkaoqinService;//学生考勤
    @Autowired
    private UsersService usersService;//管理员


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永不会进入");
        else if("学生".equals(role))
            params.put("xueshengId",request.getSession().getAttribute("userId"));
        else if("管理老师".equals(role))
            params.put("guanlilaoshiId",request.getSession().getAttribute("userId"));
        CommonUtil.checkMap(params);
        PageUtils page = susheweishengService.queryPage(params);

        //字典表数据转换
        List<SusheweishengView> list =(List<SusheweishengView>)page.getList();
        for(SusheweishengView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        SusheweishengEntity susheweisheng = susheweishengService.selectById(id);
        if(susheweisheng !=null){
            //entity转view
            SusheweishengView view = new SusheweishengView();
            BeanUtils.copyProperties( susheweisheng , view );//把实体数据重构到view中
            //级联表 宿舍
            //级联表
            SusheEntity sushe = susheService.selectById(susheweisheng.getSusheId());
            if(sushe != null){
            BeanUtils.copyProperties( sushe , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setSusheId(sushe.getId());
            }
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody SusheweishengEntity susheweisheng, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,susheweisheng:{}",this.getClass().getName(),susheweisheng.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");

        Wrapper<SusheweishengEntity> queryWrapper = new EntityWrapper<SusheweishengEntity>()
            .eq("sushe_id", susheweisheng.getSusheId())
            .eq("susheweisheng_uuid_numnber", susheweisheng.getSusheweishengUuidNumnber())
            .eq("susheweisheng_types", susheweisheng.getSusheweishengTypes())
            .eq("riqi_time", new SimpleDateFormat("yyyy-MM-dd").format(susheweisheng.getRiqiTime()))
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        SusheweishengEntity susheweishengEntity = susheweishengService.selectOne(queryWrapper);
        if(susheweishengEntity==null){
            susheweisheng.setInsertTime(new Date());
            susheweisheng.setCreateTime(new Date());
            susheweishengService.insert(susheweisheng);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody SusheweishengEntity susheweisheng, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,susheweisheng:{}",this.getClass().getName(),susheweisheng.toString());
        SusheweishengEntity oldSusheweishengEntity = susheweishengService.selectById(susheweisheng.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");

            susheweishengService.updateById(susheweisheng);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<SusheweishengEntity> oldSusheweishengList =susheweishengService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        susheweishengService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName, HttpServletRequest request){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        Integer xueshengId = Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId")));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //.eq("time", new SimpleDateFormat("yyyy-MM-dd").format(new Date()))
        try {
            List<SusheweishengEntity> susheweishengList = new ArrayList<>();//上传的东西
            Map<String, List<String>> seachFields= new HashMap<>();//要查询的字段
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if(lastIndexOf == -1){
                return R.error(511,"该文件没有后缀");
            }else{
                String suffix = fileName.substring(lastIndexOf);
                if(!".xls".equals(suffix)){
                    return R.error(511,"只支持后缀为xls的excel文件");
                }else{
                    URL resource = this.getClass().getClassLoader().getResource("static/upload/" + fileName);//获取文件路径
                    File file = new File(resource.getFile());
                    if(!file.exists()){
                        return R.error(511,"找不到上传文件，请联系管理员");
                    }else{
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());//读取xls文件
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for(List<String> data:dataList){
                            //循环
                            SusheweishengEntity susheweishengEntity = new SusheweishengEntity();
//                            susheweishengEntity.setSusheId(Integer.valueOf(data.get(0)));   //宿舍 要改的
//                            susheweishengEntity.setSusheweishengUuidNumnber(data.get(0));                    //宿舍卫生编号 要改的
//                            susheweishengEntity.setSusheweishengTypes(Integer.valueOf(data.get(0)));   //卫生状态 要改的
//                            susheweishengEntity.setDafen(data.get(0));                    //打分 要改的
//                            susheweishengEntity.setRiqiTime(sdf.parse(data.get(0)));          //所属日期 要改的
//                            susheweishengEntity.setSusheweishengContent("");//详情和图片
//                            susheweishengEntity.setInsertTime(date);//时间
//                            susheweishengEntity.setCreateTime(date);//时间
                            susheweishengList.add(susheweishengEntity);


                            //把要查询是否重复的字段放入map中
                        }

                        //查询是否重复
                        susheweishengService.insertBatch(susheweishengList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }




    /**
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        CommonUtil.checkMap(params);
        PageUtils page = susheweishengService.queryPage(params);

        //字典表数据转换
        List<SusheweishengView> list =(List<SusheweishengView>)page.getList();
        for(SusheweishengView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        SusheweishengEntity susheweisheng = susheweishengService.selectById(id);
            if(susheweisheng !=null){


                //entity转view
                SusheweishengView view = new SusheweishengView();
                BeanUtils.copyProperties( susheweisheng , view );//把实体数据重构到view中

                //级联表
                    SusheEntity sushe = susheService.selectById(susheweisheng.getSusheId());
                if(sushe != null){
                    BeanUtils.copyProperties( sushe , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setSusheId(sushe.getId());
                }
                //修改对应字典表字段
                dictionaryService.dictionaryConvert(view, request);
                return R.ok().put("data", view);
            }else {
                return R.error(511,"查不到数据");
            }
    }


    /**
    * 前端保存
    */
    @RequestMapping("/add")
    public R add(@RequestBody SusheweishengEntity susheweisheng, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,susheweisheng:{}",this.getClass().getName(),susheweisheng.toString());
        Wrapper<SusheweishengEntity> queryWrapper = new EntityWrapper<SusheweishengEntity>()
            .eq("sushe_id", susheweisheng.getSusheId())
            .eq("susheweisheng_uuid_numnber", susheweisheng.getSusheweishengUuidNumnber())
            .eq("susheweisheng_types", susheweisheng.getSusheweishengTypes())
//            .notIn("susheweisheng_types", new Integer[]{102})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        SusheweishengEntity susheweishengEntity = susheweishengService.selectOne(queryWrapper);
        if(susheweishengEntity==null){
            susheweisheng.setInsertTime(new Date());
            susheweisheng.setCreateTime(new Date());
        susheweishengService.insert(susheweisheng);

            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

}

