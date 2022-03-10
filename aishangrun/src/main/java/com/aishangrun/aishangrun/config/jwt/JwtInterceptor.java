package com.aishangrun.aishangrun.config.jwt;


import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class JwtInterceptor implements HandlerInterceptor {
    private Logger logger = LoggerFactory.getLogger(JwtInterceptor.class);

    @Autowired
    private JwtEntity jwtEntity;





    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        //权限验证
        String url= request.getServletPath();
        String myUrl=url.substring(1, url.length());

        System.out.println(myUrl);

        if(myUrl.contains("static/")){
            return true;
        }

        //二维码图片
        if(myUrl.contains("sys/device/qRCodeImg/")){
            return true;
        }



        // 忽略带JwtIgnore注解的请求, 不做后续token认证校验
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            JwtIgnore jwtIgnore = handlerMethod.getMethodAnnotation(JwtIgnore.class);
            if (jwtIgnore != null) {
                return true;
            }
        }





        if (HttpMethod.OPTIONS.equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }

        final String authHeader = request.getHeader(JwtConstant.AUTH_HEADER_KEY);
        System.out.println("这是啥"+authHeader);
        System.out.println("这是啥"+authHeader);
        System.out.println("这是啥"+authHeader);
        System.out.println("这是啥"+authHeader);

        if (StringUtils.isEmpty(authHeader)) {
            // TODO 这里自行抛出异常
            logger.info("===== 用户未登录, 请先登录 =====");
            return false;
        }

        // 校验头格式校验
        if (!JwtUtils.validate(authHeader)) {
            // TODO 这里自行抛出异常
            logger.info("===== token格式异常 =====");
            return false;
        }
        Claims claims = null;
        try {
            // token解析
            final String authToken = JwtUtils.getRawToken(authHeader);
            System.out.println("token解析========="+authToken);
            claims = JwtUtils.parseToken(authToken, jwtEntity.getBase64Secret());

        } catch (Exception e) {
            request.setAttribute("code",false);
            return false;
        }
        if (claims == null) {
            logger.info("===== token解析异常 =====");
            return false;
        }

        // 传递所需信息  userId 保存在claims 里面
        request.setAttribute("adminUserId", claims);

        request.setAttribute("userId",claims.get("userId").toString());

//       Integer integer= Integer.valueOf(claims.get("userId").toString());
//        //权限验证
//       TAdminUser adminUser= adminUserService.findAdminUserById(integer);
//        request.setAttribute("adminUser", adminUser);

//        if( "sys/admin/login".equals(myUrl)
//           ||"sys/admin/findAdminUserInfo".equals(myUrl)
//           ||"sys/menu/findAllIcon".equals(myUrl)
//           ||myUrl.contains("static/")
//        ){
//            return true;
//        }

        request.setAttribute("code",true);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) throws Exception {

    }
}
