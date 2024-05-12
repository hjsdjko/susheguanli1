
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
 * 宿舍成员
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/susheXuesheng")
public class SusheXueshengController {
    private static final Logger logger = LoggerFactory.getLogger(SusheXueshengController.class);

    private static final String TABLE_NAME = "susheXuesheng";

    @Autowired
    private SusheXueshengService susheXueshengService;


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
    private SusheweishengService susheweishengService;//宿舍卫生
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
        PageUtils page = susheXueshengService.queryPage(params);

        //字典表数据转换
        List<SusheXueshengView> list =(List<SusheXueshengView>)page.getList();
        for(SusheXueshengView c:list){
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
        SusheXueshengEntity susheXuesheng = susheXueshengService.selectById(id);
        if(susheXuesheng !=null){
            //entity转view
            SusheXueshengView view = new SusheXueshengView();
            BeanUtils.copyProperties( susheXuesheng , view );//把实体数据重构到view中
            //级联表 学生
            //级联表
            XueshengEntity xuesheng = xueshengService.selectById(susheXuesheng.getXueshengId());
            if(xuesheng != null){
            BeanUtils.copyProperties( xuesheng , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "xueshengId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setXueshengId(xuesheng.getId());
            }
            //级联表 宿舍
            //级联表
            SusheEntity sushe = susheService.selectById(susheXuesheng.getSusheId());
            if(sushe != null){
            BeanUtils.copyProperties( sushe , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "xueshengId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
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
    public R save(@RequestBody SusheXueshengEntity susheXuesheng, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,susheXuesheng:{}",this.getClass().getName(),susheXuesheng.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("学生".equals(role))
            susheXuesheng.setXueshengId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        Wrapper<SusheXueshengEntity> queryWrapper = new EntityWrapper<SusheXueshengEntity>()
            .eq("xuesheng_id", susheXuesheng.getXueshengId())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        SusheXueshengEntity susheXueshengEntity = susheXueshengService.selectOne(queryWrapper);
        if(susheXueshengEntity==null){
            susheXuesheng.setInsertTime(new Date());
            susheXuesheng.setCreateTime(new Date());
            susheXueshengService.insert(susheXuesheng);
            return R.ok();
        }else {
            return R.error(511,"该学生已经绑定了宿舍,不能重复绑定");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody SusheXueshengEntity susheXuesheng, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,susheXuesheng:{}",this.getClass().getName(),susheXuesheng.toString());
        SusheXueshengEntity oldSusheXueshengEntity = susheXueshengService.selectById(susheXuesheng.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("学生".equals(role))
//            susheXuesheng.setXueshengId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

            susheXueshengService.updateById(susheXuesheng);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<SusheXueshengEntity> oldSusheXueshengList =susheXueshengService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        susheXueshengService.deleteBatchIds(Arrays.asList(ids));

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
            List<SusheXueshengEntity> susheXueshengList = new ArrayList<>();//上传的东西
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
                            SusheXueshengEntity susheXueshengEntity = new SusheXueshengEntity();
//                            susheXueshengEntity.setXueshengId(Integer.valueOf(data.get(0)));   //学生 要改的
//                            susheXueshengEntity.setSusheId(Integer.valueOf(data.get(0)));   //宿舍 要改的
//                            susheXueshengEntity.setInsertTime(date);//时间
//                            susheXueshengEntity.setCreateTime(date);//时间
                            susheXueshengList.add(susheXueshengEntity);


                            //把要查询是否重复的字段放入map中
                        }

                        //查询是否重复
                        susheXueshengService.insertBatch(susheXueshengList);
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
        PageUtils page = susheXueshengService.queryPage(params);

        //字典表数据转换
        List<SusheXueshengView> list =(List<SusheXueshengView>)page.getList();
        for(SusheXueshengView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        SusheXueshengEntity susheXuesheng = susheXueshengService.selectById(id);
            if(susheXuesheng !=null){


                //entity转view
                SusheXueshengView view = new SusheXueshengView();
                BeanUtils.copyProperties( susheXuesheng , view );//把实体数据重构到view中

                //级联表
                    XueshengEntity xuesheng = xueshengService.selectById(susheXuesheng.getXueshengId());
                if(xuesheng != null){
                    BeanUtils.copyProperties( xuesheng , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setXueshengId(xuesheng.getId());
                }
                //级联表
                    SusheEntity sushe = susheService.selectById(susheXuesheng.getSusheId());
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
    public R add(@RequestBody SusheXueshengEntity susheXuesheng, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,susheXuesheng:{}",this.getClass().getName(),susheXuesheng.toString());
        Wrapper<SusheXueshengEntity> queryWrapper = new EntityWrapper<SusheXueshengEntity>()
            .eq("xuesheng_id", susheXuesheng.getXueshengId())
            .eq("sushe_id", susheXuesheng.getSusheId())
//            .notIn("sushe_xuesheng_types", new Integer[]{102})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        SusheXueshengEntity susheXueshengEntity = susheXueshengService.selectOne(queryWrapper);
        if(susheXueshengEntity==null){
            susheXuesheng.setInsertTime(new Date());
            susheXuesheng.setCreateTime(new Date());
        susheXueshengService.insert(susheXuesheng);

            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

}

