package com.aishangrun.aishangrun.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.commons.lang.StringUtils;
import org.apache.http.client.fluent.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class CommUtil {

    private final static Logger logger = LoggerFactory.getLogger(CommUtil.class);

    /**
     * 获取客户端真实IP地址
     *
     * @return
     */
    public static String getIp() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String ip = request.getHeader("X-Forwarded-For");
        if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = ip.indexOf(",");
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        }
        ip = request.getHeader("X-Real-IP");
        if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
            return ip;
        }
        return request.getRemoteAddr();
    }

    /**
     * 将一个完整的List数据进行分页
     *
     * @param pageSize  每页数量
     * @param pageNum  当前页 第0页开始
     * @param <T>
     * @return 返回指定页数，指定每页长度的数据
     * @author hufei
     */
    public static <T> List<T> getPageData(List<T> dataList, int pageSize, int pageNum) {

        int from = pageNum * pageSize;
        int to = from + pageSize;

        to = to > dataList.size() ? dataList.size() : to;

        if (from >= to) {
            return Collections.emptyList();
        }

        return dataList.subList(from, to);
    }

    public static JsonObject callJsonApi(String requestUrl) {
        return new Gson().fromJson(callRequestUrl(requestUrl), JsonObject.class);
    }

    public static String callRequestUrl(String requestUrl) {
        try {
            logger.info("send message requestUrl={}",requestUrl);
            //发送远程接口请求
            return Request.Get(requestUrl).execute().returnContent().asString(Charset.forName("UTF-8"));
        } catch (IOException e) {
            logger.error("access error: " + requestUrl, e);
            return "";
        }
    }

    public static String getURLWithParams() {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        //url
        String url = request.getScheme() + "://"        //请求方式
                + request.getServerName()    //服务器地址
//                    + ":"  + request.getServerPort()           //端口号
                + request.getContextPath()          //项目名称
                + request.getServletPath();         //请求页面或其他地址

        //params
        Map<String, String[]> params = request.getParameterMap();
        String queryString = "";
        for (String key : params.keySet()) {
            //微信回调的code&state参数不获取
            if (key.equals("code") || key.equals("state")) continue;

            String[] values = params.get(key);
            for (String value : values) {
                queryString += key + "=" + value + "&";
            }
        }

        if (StringUtils.isNotBlank(queryString)) {
            // 去掉最后一个&
            queryString = queryString.substring(0, queryString.length() - 1);
            url = url + "?" + queryString;
        }

        return url;
    }

    public static String getWholeUrl() {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        StringBuffer url = request.getRequestURL();
        String queryString = request.getQueryString();

        if (StringUtils.isNotBlank(queryString)) {
            url.append("?").append(queryString);
        }

        return url.toString();
    }
}
