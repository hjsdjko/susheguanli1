
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
 * 访客
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/fangke")
public class FangkeController {
    private static final Logger logger = LoggerFactory.getLogger(FangkeController.class);

    private static final String TABLE_NAME = "fangke";

    @Autowired
    private FangkeService fangkeService;


    @Autowired
    private TokenService tokenService;

    @Autowired
    private BaoxiuService baoxiuService;//报修
    @Autowired
    private DictionaryService dictionaryService;//字典
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
        PageUtils page = fangkeService.queryPage(params);

        //字典表数据转换
        List<FangkeView> list =(List<FangkeView>)page.getList();
        for(FangkeView c:list){
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
        FangkeEntity fangke = fangkeService.selectById(id);
        if(fangke !=null){
            //entity转view
            FangkeView view = new FangkeView();
            BeanUtils.copyProperties( fangke , view );//把实体数据重构到view中
            //级联表 学生
            //级联表
            XueshengEntity xuesheng = xueshengService.selectById(fangke.getXueshengId());
            if(xuesheng != null){
            BeanUtils.copyProperties( xuesheng , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "xueshengId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setXueshengId(xuesheng.getId());
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
    public R save(@RequestBody FangkeEntity fangke, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,fangke:{}",this.getClass().getName(),fangke.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("学生".equals(role))
            fangke.setXueshengId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        Wrapper<FangkeEntity> queryWrapper = new EntityWrapper<FangkeEntity>()
            .eq("xuesheng_id", fangke.getXueshengId())
            .eq("fangke_name", fangke.getFangkeName())
            .eq("fangke_phone", fangke.getFangkePhone())
            .eq("fangke_id_number", fangke.getFangkeIdNumber())
            .eq("sex_types", fangke.getSexTypes())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        FangkeEntity fangkeEntity = fangkeService.selectOne(queryWrapper);
        if(fangkeEntity==null){
            fangke.setInsertTime(new Date());
            fangke.setCreateTime(new Date());
            fangkeService.insert(fangke);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody FangkeEntity fangke, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,fangke:{}",this.getClass().getName(),fangke.toString());
        FangkeEntity oldFangkeEntity = fangkeService.selectById(fangke.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("学生".equals(role))
//            fangke.setXueshengId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        if("".equals(fangke.getFangkePhoto()) || "null".equals(fangke.getFangkePhoto())){
                fangke.setFangkePhoto(null);
        }

            fangkeService.updateById(fangke);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<FangkeEntity> oldFangkeList =fangkeService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        fangkeService.deleteBatchIds(Arrays.asList(ids));

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
            List<FangkeEntity> fangkeList = new ArrayList<>();//上传的东西
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
                            FangkeEntity fangkeEntity = new FangkeEntity();
//                            fangkeEntity.setXueshengId(Integer.valueOf(data.get(0)));   //学生 要改的
//                            fangkeEntity.setFangkeName(data.get(0));                    //访客姓名 要改的
//                            fangkeEntity.setFangkePhone(data.get(0));                    //访客手机号 要改的
//                            fangkeEntity.setFangkeIdNumber(data.get(0));                    //访客身份证号 要改的
//                            fangkeEntity.setSexTypes(Integer.valueOf(data.get(0)));   //性别 要改的
//                            fangkeEntity.setFangkePhoto("");//详情和图片
//                            fangkeEntity.setFangkeContent("");//详情和图片
//                            fangkeEntity.setInsertTime(date);//时间
//                            fangkeEntity.setCreateTime(date);//时间
                            fangkeList.add(fangkeEntity);


                            //把要查询是否重复的字段放入map中
                                //访客手机号
                                if(seachFields.containsKey("fangkePhone")){
                                    List<String> fangkePhone = seachFields.get("fangkePhone");
                                    fangkePhone.add(data.get(0));//要改的
                                }else{
                                    List<String> fangkePhone = new ArrayList<>();
                                    fangkePhone.add(data.get(0));//要改的
                                    seachFields.put("fangkePhone",fangkePhone);
                                }
                                //访客身份证号
                                if(seachFields.containsKey("fangkeIdNumber")){
                                    List<String> fangkeIdNumber = seachFields.get("fangkeIdNumber");
                                    fangkeIdNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> fangkeIdNumber = new ArrayList<>();
                                    fangkeIdNumber.add(data.get(0));//要改的
                                    seachFields.put("fangkeIdNumber",fangkeIdNumber);
                                }
                        }

                        //查询是否重复
                         //访客手机号
                        List<FangkeEntity> fangkeEntities_fangkePhone = fangkeService.selectList(new EntityWrapper<FangkeEntity>().in("fangke_phone", seachFields.get("fangkePhone")));
                        if(fangkeEntities_fangkePhone.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(FangkeEntity s:fangkeEntities_fangkePhone){
                                repeatFields.add(s.getFangkePhone());
                            }
                            return R.error(511,"数据库的该表中的 [访客手机号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                         //访客身份证号
                        List<FangkeEntity> fangkeEntities_fangkeIdNumber = fangkeService.selectList(new EntityWrapper<FangkeEntity>().in("fangke_id_number", seachFields.get("fangkeIdNumber")));
                        if(fangkeEntities_fangkeIdNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(FangkeEntity s:fangkeEntities_fangkeIdNumber){
                                repeatFields.add(s.getFangkeIdNumber());
                            }
                            return R.error(511,"数据库的该表中的 [访客身份证号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        fangkeService.insertBatch(fangkeList);
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
        PageUtils page = fangkeService.queryPage(params);

        //字典表数据转换
        List<FangkeView> list =(List<FangkeView>)page.getList();
        for(FangkeView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        FangkeEntity fangke = fangkeService.selectById(id);
            if(fangke !=null){


                //entity转view
                FangkeView view = new FangkeView();
                BeanUtils.copyProperties( fangke , view );//把实体数据重构到view中

                //级联表
                    XueshengEntity xuesheng = xueshengService.selectById(fangke.getXueshengId());
                if(xuesheng != null){
                    BeanUtils.copyProperties( xuesheng , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setXueshengId(xuesheng.getId());
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
    public R add(@RequestBody FangkeEntity fangke, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,fangke:{}",this.getClass().getName(),fangke.toString());
        Wrapper<FangkeEntity> queryWrapper = new EntityWrapper<FangkeEntity>()
            .eq("xuesheng_id", fangke.getXueshengId())
            .eq("fangke_name", fangke.getFangkeName())
            .eq("fangke_phone", fangke.getFangkePhone())
            .eq("fangke_id_number", fangke.getFangkeIdNumber())
            .eq("sex_types", fangke.getSexTypes())
//            .notIn("fangke_types", new Integer[]{102})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        FangkeEntity fangkeEntity = fangkeService.selectOne(queryWrapper);
        if(fangkeEntity==null){
            fangke.setInsertTime(new Date());
            fangke.setCreateTime(new Date());
        fangkeService.insert(fangke);

            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

}

