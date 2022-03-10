package com.aishangrun.aishangrun.controller.agent;

import com.aishangrun.aishangrun.config.jwt.JwtEntity;
import com.aishangrun.aishangrun.config.jwt.JwtIgnore;
import com.aishangrun.aishangrun.config.jwt.JwtUtils;
import com.aishangrun.aishangrun.entity.UserEntity;
import com.aishangrun.aishangrun.entity.UserinfoEntity;
import com.aishangrun.aishangrun.service.agent.UserService;
import com.aishangrun.aishangrun.service.agent.UserinfoService;
import com.aishangrun.aishangrun.utils.HttpRequest;
import com.aishangrun.aishangrun.utils.MD5;
import com.aishangrun.aishangrun.utils.MyUtil;
import com.aishangrun.aishangrun.utils.ResultUtil;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("sys/user/")
public class AgentUserController {


        private static final Logger logger = LoggerFactory.getLogger(AgentUserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private UserinfoService userinfoService;



    @Autowired
    private JwtEntity jwtEntity;



    //String name,Integer age, String sex,String height,String weight,String total_distance,String total_calorie,String total_day
    @PostMapping("/updateUserInfo")
    @ResponseBody
    public ResultUtil updateUserInfo(UserinfoEntity userinfo, HttpServletRequest  request){
        String userId=request.getAttribute("userId").toString();
        userinfo.setUserId(userId);
        int res=userinfoService.updateUserinfo(userinfo);
        if(res==0){
            return ResultUtil.error("更新失败！");
        }else {
            return ResultUtil.ok("更新成功！");
        }
    }

    @PostMapping("/a")
    @ResponseBody
    public ResultUtil updateUserInfo(){

            return ResultUtil.error("更新失败！");

    }


    /*app爱尚跑注册并登入*/
    @RequestMapping("/RegisterLogin")
    @JwtIgnore // 加此注解, 请求不做token验证
    @ResponseBody
    public ResultUtil register(String phoneNum,String code,HttpServletRequest request) {
        String phoneCode = (String) request.getServletContext().getAttribute("code");
        String phone = (String) request.getServletContext().getAttribute("phone");
        System.out.println("发送的验证码为，session中取出" + phoneCode+"电话号码"+phone+"=========="+phoneNum);
        System.out.println("前台传过来的验证码为：" + code);
        /*默认手机验证码为0000*/


        if ((code.equals(phoneCode) && phone.equals(phoneNum))||code.equals("0000")) {
            if (phoneNum == null || phoneNum == "") {
                return ResultUtil.error("手机号和密码不能为空！");
            } else {
                //phoneNum="19984712254";
                //password="666666";
                Map map = new HashMap();
                map.put("phoneNum", phoneNum);
                System.out.printf(MD5.MD5Pwd(phoneNum, phoneNum));
                map.put("password", MD5.MD5Pwd(phoneNum, phoneNum));
//                Map mapinfo = new HashMap();
//                mapinfo.put("phoneNum",phoneNum);
//                mapinfo.put("name",phoneNum);
                /*判断手机号码是否唯一*/
                UserEntity queryAgentUser = userService.selectUserPhone(map);
                UserEntity userEntity =null;
                List<UserinfoEntity> userinfoEntityList = new ArrayList<UserinfoEntity>();
                if (queryAgentUser == null) {
                    String uuid=uuid=MyUtil.getStrUUID();
                    System.out.printf("uuid=========================" +
                            uuid);
                    map.put("id",uuid);
                    int addAppUser = userService.addUserPhone(map);
                    map.put("userId",uuid);
                    int adduserinfo = userinfoService.addUserinfoPhone(map);
                    //添加成功
                    System.out.printf("addAppUser用户id"+addAppUser);
                    //是否添加新用户成功
                    if (addAppUser == 1 && adduserinfo == 1) {
                       userEntity = userService.selectUserPhone(map);
                        // 判断是否有该用户
                        if (userEntity != null & userEntity.getId()!=null) {
                            String access_token = JwtUtils.createToken(userEntity.getId() + "", jwtEntity);
                            if (access_token == null) {
                                System.out.println("===== 用户签名失败 =====");
                                return ResultUtil.error("用户签名失败!");
                            }
                            System.out.println("===== 用户" + phoneNum + "生成签名" + access_token + "=====");
                            return ResultUtil.ok(JwtUtils.getAuthorizationHeader(access_token),userinfoEntityList);
                        }
                        return ResultUtil.ok("注册成功！");
                    } else {
                        return ResultUtil.error("注册失败");
                    }
                }else {
                     UserinfoEntity userinfoEntity =  userinfoService.selectUserinfoPhone(map);
                    System.out.println("--------userinfoEntity------"+userinfoEntity.toString());
                     // 判断是否有该用户
                    if (userinfoEntity != null && userinfoEntity.getUserId()!=null) {
                        String access_token = JwtUtils.createToken(userinfoEntity.getUserId() + "", jwtEntity);
                        if (access_token == null) {
                            System.out.println("===== 用户签名失败 =====");
                            return ResultUtil.error("用户签名失败!");
                        }
                        System.out.println("===== 用户" + phoneNum + "生成签名" + access_token + "=====");
                        return ResultUtil.ok(JwtUtils.getAuthorizationHeader(access_token),userinfoEntity);
                    }
                    return ResultUtil.ok("登入成功！");
                }
            }
        } else {
            return ResultUtil.error(" 电话号码或者验证码错误！");
        }


    }

}

