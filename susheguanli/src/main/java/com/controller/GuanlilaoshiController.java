
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
 * 管理老师
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/guanlilaoshi")
public class GuanlilaoshiController {
    private static final Logger logger = LoggerFactory.getLogger(GuanlilaoshiController.class);

    private static final String TABLE_NAME = "guanlilaoshi";

    @Autowired
    private GuanlilaoshiService guanlilaoshiService;


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
        PageUtils page = guanlilaoshiService.queryPage(params);

        //字典表数据转换
        List<GuanlilaoshiView> list =(List<GuanlilaoshiView>)page.getList();
        for(GuanlilaoshiView c:list){
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
        GuanlilaoshiEntity guanlilaoshi = guanlilaoshiService.selectById(id);
        if(guanlilaoshi !=null){
            //entity转view
            GuanlilaoshiView view = new GuanlilaoshiView();
            BeanUtils.copyProperties( guanlilaoshi , view );//把实体数据重构到view中
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
    public R save(@RequestBody GuanlilaoshiEntity guanlilaoshi, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,guanlilaoshi:{}",this.getClass().getName(),guanlilaoshi.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");

        Wrapper<GuanlilaoshiEntity> queryWrapper = new EntityWrapper<GuanlilaoshiEntity>()
            .eq("username", guanlilaoshi.getUsername())
            .or()
            .eq("guanlilaoshi_phone", guanlilaoshi.getGuanlilaoshiPhone())
            .or()
            .eq("guanlilaoshi_id_number", guanlilaoshi.getGuanlilaoshiIdNumber())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        GuanlilaoshiEntity guanlilaoshiEntity = guanlilaoshiService.selectOne(queryWrapper);
        if(guanlilaoshiEntity==null){
            guanlilaoshi.setCreateTime(new Date());
            guanlilaoshi.setPassword("123456");
            guanlilaoshiService.insert(guanlilaoshi);
            return R.ok();
        }else {
            return R.error(511,"账户或者管理老师手机号或者管理老师身份证号已经被使用");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody GuanlilaoshiEntity guanlilaoshi, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,guanlilaoshi:{}",this.getClass().getName(),guanlilaoshi.toString());
        GuanlilaoshiEntity oldGuanlilaoshiEntity = guanlilaoshiService.selectById(guanlilaoshi.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
        if("".equals(guanlilaoshi.getGuanlilaoshiPhoto()) || "null".equals(guanlilaoshi.getGuanlilaoshiPhoto())){
                guanlilaoshi.setGuanlilaoshiPhoto(null);
        }

            guanlilaoshiService.updateById(guanlilaoshi);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<GuanlilaoshiEntity> oldGuanlilaoshiList =guanlilaoshiService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        guanlilaoshiService.deleteBatchIds(Arrays.asList(ids));

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
            List<GuanlilaoshiEntity> guanlilaoshiList = new ArrayList<>();//上传的东西
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
                            GuanlilaoshiEntity guanlilaoshiEntity = new GuanlilaoshiEntity();
//                            guanlilaoshiEntity.setUsername(data.get(0));                    //账户 要改的
//                            guanlilaoshiEntity.setPassword("123456");//密码
//                            guanlilaoshiEntity.setGuanlilaoshiName(data.get(0));                    //管理老师姓名 要改的
//                            guanlilaoshiEntity.setGuanlilaoshiPhone(data.get(0));                    //管理老师手机号 要改的
//                            guanlilaoshiEntity.setGuanlilaoshiIdNumber(data.get(0));                    //管理老师身份证号 要改的
//                            guanlilaoshiEntity.setGuanlilaoshiPhoto("");//详情和图片
//                            guanlilaoshiEntity.setSexTypes(Integer.valueOf(data.get(0)));   //性别 要改的
//                            guanlilaoshiEntity.setGuanlilaoshiEmail(data.get(0));                    //管理老师邮箱 要改的
//                            guanlilaoshiEntity.setJinyongTypes(Integer.valueOf(data.get(0)));   //账户状态 要改的
//                            guanlilaoshiEntity.setCreateTime(date);//时间
                            guanlilaoshiList.add(guanlilaoshiEntity);


                            //把要查询是否重复的字段放入map中
                                //账户
                                if(seachFields.containsKey("username")){
                                    List<String> username = seachFields.get("username");
                                    username.add(data.get(0));//要改的
                                }else{
                                    List<String> username = new ArrayList<>();
                                    username.add(data.get(0));//要改的
                                    seachFields.put("username",username);
                                }
                                //管理老师手机号
                                if(seachFields.containsKey("guanlilaoshiPhone")){
                                    List<String> guanlilaoshiPhone = seachFields.get("guanlilaoshiPhone");
                                    guanlilaoshiPhone.add(data.get(0));//要改的
                                }else{
                                    List<String> guanlilaoshiPhone = new ArrayList<>();
                                    guanlilaoshiPhone.add(data.get(0));//要改的
                                    seachFields.put("guanlilaoshiPhone",guanlilaoshiPhone);
                                }
                                //管理老师身份证号
                                if(seachFields.containsKey("guanlilaoshiIdNumber")){
                                    List<String> guanlilaoshiIdNumber = seachFields.get("guanlilaoshiIdNumber");
                                    guanlilaoshiIdNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> guanlilaoshiIdNumber = new ArrayList<>();
                                    guanlilaoshiIdNumber.add(data.get(0));//要改的
                                    seachFields.put("guanlilaoshiIdNumber",guanlilaoshiIdNumber);
                                }
                        }

                        //查询是否重复
                         //账户
                        List<GuanlilaoshiEntity> guanlilaoshiEntities_username = guanlilaoshiService.selectList(new EntityWrapper<GuanlilaoshiEntity>().in("username", seachFields.get("username")));
                        if(guanlilaoshiEntities_username.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(GuanlilaoshiEntity s:guanlilaoshiEntities_username){
                                repeatFields.add(s.getUsername());
                            }
                            return R.error(511,"数据库的该表中的 [账户] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                         //管理老师手机号
                        List<GuanlilaoshiEntity> guanlilaoshiEntities_guanlilaoshiPhone = guanlilaoshiService.selectList(new EntityWrapper<GuanlilaoshiEntity>().in("guanlilaoshi_phone", seachFields.get("guanlilaoshiPhone")));
                        if(guanlilaoshiEntities_guanlilaoshiPhone.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(GuanlilaoshiEntity s:guanlilaoshiEntities_guanlilaoshiPhone){
                                repeatFields.add(s.getGuanlilaoshiPhone());
                            }
                            return R.error(511,"数据库的该表中的 [管理老师手机号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                         //管理老师身份证号
                        List<GuanlilaoshiEntity> guanlilaoshiEntities_guanlilaoshiIdNumber = guanlilaoshiService.selectList(new EntityWrapper<GuanlilaoshiEntity>().in("guanlilaoshi_id_number", seachFields.get("guanlilaoshiIdNumber")));
                        if(guanlilaoshiEntities_guanlilaoshiIdNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(GuanlilaoshiEntity s:guanlilaoshiEntities_guanlilaoshiIdNumber){
                                repeatFields.add(s.getGuanlilaoshiIdNumber());
                            }
                            return R.error(511,"数据库的该表中的 [管理老师身份证号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        guanlilaoshiService.insertBatch(guanlilaoshiList);
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
    * 登录
    */
    @IgnoreAuth
    @RequestMapping(value = "/login")
    public R login(String username, String password, String captcha, HttpServletRequest request) {
        GuanlilaoshiEntity guanlilaoshi = guanlilaoshiService.selectOne(new EntityWrapper<GuanlilaoshiEntity>().eq("username", username));
        if(guanlilaoshi==null || !guanlilaoshi.getPassword().equals(password))
            return R.error("账号或密码不正确");
        else if(guanlilaoshi.getJinyongTypes() != 1)
            return R.error("账户已被禁用");
        String token = tokenService.generateToken(guanlilaoshi.getId(),username, "guanlilaoshi", "管理老师");
        R r = R.ok();
        r.put("token", token);
        r.put("role","管理老师");
        r.put("username",guanlilaoshi.getGuanlilaoshiName());
        r.put("tableName","guanlilaoshi");
        r.put("userId",guanlilaoshi.getId());
        return r;
    }

    /**
    * 注册
    */
    @IgnoreAuth
    @PostMapping(value = "/register")
    public R register(@RequestBody GuanlilaoshiEntity guanlilaoshi, HttpServletRequest request) {
//    	ValidatorUtils.validateEntity(user);
        Wrapper<GuanlilaoshiEntity> queryWrapper = new EntityWrapper<GuanlilaoshiEntity>()
            .eq("username", guanlilaoshi.getUsername())
            .or()
            .eq("guanlilaoshi_phone", guanlilaoshi.getGuanlilaoshiPhone())
            .or()
            .eq("guanlilaoshi_id_number", guanlilaoshi.getGuanlilaoshiIdNumber())
            ;
        GuanlilaoshiEntity guanlilaoshiEntity = guanlilaoshiService.selectOne(queryWrapper);
        if(guanlilaoshiEntity != null)
            return R.error("账户或者管理老师手机号或者管理老师身份证号已经被使用");
        guanlilaoshi.setJinyongTypes(1);//启用
        guanlilaoshi.setCreateTime(new Date());
        guanlilaoshiService.insert(guanlilaoshi);

        return R.ok();
    }

    /**
     * 重置密码
     */
    @GetMapping(value = "/resetPassword")
    public R resetPassword(Integer  id, HttpServletRequest request) {
        GuanlilaoshiEntity guanlilaoshi = guanlilaoshiService.selectById(id);
        guanlilaoshi.setPassword("123456");
        guanlilaoshiService.updateById(guanlilaoshi);
        return R.ok();
    }

	/**
	 * 修改密码
	 */
	@GetMapping(value = "/updatePassword")
	public R updatePassword(String  oldPassword, String  newPassword, HttpServletRequest request) {
        GuanlilaoshiEntity guanlilaoshi = guanlilaoshiService.selectById((Integer)request.getSession().getAttribute("userId"));
		if(newPassword == null){
			return R.error("新密码不能为空") ;
		}
		if(!oldPassword.equals(guanlilaoshi.getPassword())){
			return R.error("原密码输入错误");
		}
		if(newPassword.equals(guanlilaoshi.getPassword())){
			return R.error("新密码不能和原密码一致") ;
		}
        guanlilaoshi.setPassword(newPassword);
		guanlilaoshiService.updateById(guanlilaoshi);
		return R.ok();
	}



    /**
     * 忘记密码
     */
    @IgnoreAuth
    @RequestMapping(value = "/resetPass")
    public R resetPass(String username, HttpServletRequest request) {
        GuanlilaoshiEntity guanlilaoshi = guanlilaoshiService.selectOne(new EntityWrapper<GuanlilaoshiEntity>().eq("username", username));
        if(guanlilaoshi!=null){
            guanlilaoshi.setPassword("123456");
            guanlilaoshiService.updateById(guanlilaoshi);
            return R.ok();
        }else{
           return R.error("账号不存在");
        }
    }


    /**
    * 获取用户的session用户信息
    */
    @RequestMapping("/session")
    public R getCurrGuanlilaoshi(HttpServletRequest request){
        Integer id = (Integer)request.getSession().getAttribute("userId");
        GuanlilaoshiEntity guanlilaoshi = guanlilaoshiService.selectById(id);
        if(guanlilaoshi !=null){
            //entity转view
            GuanlilaoshiView view = new GuanlilaoshiView();
            BeanUtils.copyProperties( guanlilaoshi , view );//把实体数据重构到view中

            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }
    }


    /**
    * 退出
    */
    @GetMapping(value = "logout")
    public R logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return R.ok("退出成功");
    }



    /**
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        CommonUtil.checkMap(params);
        PageUtils page = guanlilaoshiService.queryPage(params);

        //字典表数据转换
        List<GuanlilaoshiView> list =(List<GuanlilaoshiView>)page.getList();
        for(GuanlilaoshiView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        GuanlilaoshiEntity guanlilaoshi = guanlilaoshiService.selectById(id);
            if(guanlilaoshi !=null){


                //entity转view
                GuanlilaoshiView view = new GuanlilaoshiView();
                BeanUtils.copyProperties( guanlilaoshi , view );//把实体数据重构到view中

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
    public R add(@RequestBody GuanlilaoshiEntity guanlilaoshi, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,guanlilaoshi:{}",this.getClass().getName(),guanlilaoshi.toString());
        Wrapper<GuanlilaoshiEntity> queryWrapper = new EntityWrapper<GuanlilaoshiEntity>()
            .eq("username", guanlilaoshi.getUsername())
            .or()
            .eq("guanlilaoshi_phone", guanlilaoshi.getGuanlilaoshiPhone())
            .or()
            .eq("guanlilaoshi_id_number", guanlilaoshi.getGuanlilaoshiIdNumber())
//            .notIn("guanlilaoshi_types", new Integer[]{102})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        GuanlilaoshiEntity guanlilaoshiEntity = guanlilaoshiService.selectOne(queryWrapper);
        if(guanlilaoshiEntity==null){
            guanlilaoshi.setCreateTime(new Date());
            guanlilaoshi.setPassword("123456");
        guanlilaoshiService.insert(guanlilaoshi);

            return R.ok();
        }else {
            return R.error(511,"账户或者管理老师手机号或者管理老师身份证号已经被使用");
        }
    }

}

