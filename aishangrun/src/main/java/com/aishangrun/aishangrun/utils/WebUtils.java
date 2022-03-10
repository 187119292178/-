package com.aishangrun.aishangrun.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.*;

public class WebUtils {
	private static Logger LOGGER = LoggerFactory.getLogger(WebUtils.class);
	 
	 	/**
	 	 * 获取系统上下文路径
	 	 * @param request
	 	 * @return
	 	 */
	 	public static String getContextPath(HttpServletRequest request) {
	 		return request.getSession().getServletContext().getContextPath();
	 	}
	 
	 	/**
	 	 * 获取用户的真正IP地址
	 	 * @param request
	 	 * @return
	 	 */
	 	public static String getRemoteAddr(HttpServletRequest request) {
	 		String ip = request.getHeader("X-Forwarded-For");
	 		if (StringUtils.isBlank(ip) || StringUtils.equalsIgnoreCase(ip, "unknown")) {
	 			ip = request.getHeader("Proxy-Client-IP");
	 		}
	 		if (StringUtils.isBlank(ip) || StringUtils.equalsIgnoreCase(ip, "unknown")) {
	 			ip = request.getHeader("WL-Proxy-Client-IP");
	 		}
	 		if (StringUtils.isBlank(ip) || StringUtils.equalsIgnoreCase(ip, "unknown")) {
	 			ip = request.getHeader("HTTP_CLIENT_IP");
	 		}
	 
	 		// 民安赖波经理提供的从head的什么值获取IP地址
	 		if (StringUtils.isBlank(ip) || StringUtils.equalsIgnoreCase(ip, "unknown")) {
	 			ip = request.getHeader("X-Real-IP");
	 		}
	 
	 		if (StringUtils.isBlank(ip) || StringUtils.equalsIgnoreCase(ip, "unknown")) {
	 			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
	 		}
	 		if (StringUtils.isBlank(ip) || StringUtils.equalsIgnoreCase(ip, "unknown")) {
	 			ip = request.getRemoteAddr();
	 		}
	 		if (StringUtils.isNotBlank(ip) && StringUtils.indexOf(ip, ",") > 0) {
	 			String[] ipArray = StringUtils.split(ip, ",");
	 			ip = ipArray[0];
	 		}
	 		return ip;
	 	}
	 
	 	/**
	 	 * 获取本地IP地址
	 	 */
	 	public static String getLocalAddr() throws UnknownHostException {
	 		InetAddress addr = InetAddress.getLocalHost();
	 		return addr.getHostAddress();
	 	}
	 
	 	/**
	 	 * 处理乱码
	 	 */
	 	public static String encodingHelp(String s) throws Exception {
	 		return new String(s.getBytes("ISO-8859-1"), "UTF-8");
	 	}
	 
	 	/**
	 	 * 对ajax提交过来的参数进行解码
	 	 * @param s
	 	 * @return
	 	 * @throws Exception
	 	 */
	 	public static String ajaxDecode(String s) throws Exception {
	 		return URLDecoder.decode(s, "UTF8");
	 	}
	 
	 	/**
	 	 * 页面弹出提示信息
	 	 * @param response
	 	 * @param msg
	 	 * @throws Exception
	 	 */
	 	public static void alertMsg(HttpServletResponse response, String msg) throws Exception {
	 		response.setCharacterEncoding("UTF-8");
	 		response.setContentType("text/html");
	 
	 		StringBuilder sb = new StringBuilder();
	 		sb.append("<script type='text/javascript'>");
	 		sb.append("alert(\""+msg+"\");");
	 		sb.append("</script>");
	 		PrintWriter out = response.getWriter();
	 		out.print(sb.toString());
	 		out.close();
	 	}
	 
	 	public static HttpServletRequest getRequest() {
	 		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	 	}
	 	
	 	/**
	 	 * 判断是否为ajax请求
	 	 * @param request
	 	 * @return
	 	 */
	 	public static boolean isAjaxReqest(HttpServletRequest request) {
	 		if ((request.getHeader("accept") != null && (request.getHeader("accept").indexOf("application/json") > -1) || (request.getHeader("X-Requested-With") != null && request.getHeader(
	 				"X-Requested-With").indexOf("XMLHttpRequest") > -1))) {
	 			return true;
	 		}
	 		return false;
	 	}
	 
	 	/**
	 	 * 发送ajax
	 	 * @param response
	 	 * @param code
	 	 * @param msg
	 	 * @param data
	 	 * @throws Exception
	 	 */
	 	/*public static void ajaxJson(HttpServletResponse response, Integer code, String msg) throws Exception {
	 		String json = JsonUtils.objectToJson(new ResultUtil(code, msg));
	 		response.setContentType("text/json; charset=utf-8");
	 		response.setHeader("Cache-Control", "no-cache"); // 取消浏览器缓存
	 		PrintWriter out = response.getWriter();
	 		out.print(json);
	 		out.flush();
	 		out.close();
	 	}*/


	/**
	 2      *
	 3      * 方法用途: 对所有传入参数按照字段名的 ASCII 码从小到大排序（字典序），并且生成url参数串<br>
	 4      * 实现步骤: <br>
	 5      *
	 6      * @param paraMap   要排序的Map对象
	 7      * @param urlEncode   是否需要URLENCODE
	 8      * @param keyToLower    是否需要将Key转换为全小写
	 9      *            true:key转化成小写，false:不转化
	 10      * @return
	 11      */
	public static String formatUrlMap(Map<String, String> paraMap, boolean urlEncode, boolean keyToLower) {
		String buff = "";
		Map<String, String> tmpMap = paraMap;
		try
		{
			List<Map.Entry<String, String>> infoIds = new ArrayList<Map.Entry<String, String>>(tmpMap.entrySet());
			// 对所有传入参数按照字段名的 ASCII 码从小到大排序（字典序）
			Collections.sort(infoIds, new Comparator<Map.Entry<String, String>>()
			{
				@Override
				public int compare (Map.Entry < String, String > o1, Map.Entry < String, String > o2)
				{
					return (o1.getKey()).toString().compareTo(o2.getKey());

				}

			});
			// 构造URL 键值对的格式
			StringBuilder buf = new StringBuilder();
			for (Map.Entry<String, String> item : infoIds)
			{
				if (org.apache.commons.lang.StringUtils.isNotBlank(item.getKey()))
				{
					String key = item.getKey();
					String val = item.getValue();
					if (urlEncode)
					{
						val = URLEncoder.encode(val, "utf-8");

					}
					if (keyToLower)
					{
						buf.append(key.toLowerCase() + "=" + val);

					} else
					{
						buf.append(key + "=" + val);

					}
					buf.append("&");

				}

			}
			buff = buf.toString();
			if (buff.isEmpty() == false)
			{
				buff = buff.substring(0, buff.length() - 1);

			}

		} catch(Exception e)
		{
			return null;

		}
		return buff;
	}
	static BASE64Decoder decoder = new BASE64Decoder();
	/**
	 * 将二进制转换为图片
	 *
	 * @param base64String
	 */
	public static void base64StringToImage(String base64String) {
		try {
			byte[] bytes = base64String.getBytes();

			ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
			BufferedImage bi1 = ImageIO.read(bais);
			File w2 = new File("C:/linshi/1.jpg");// 可以是jpg,png,gif格式
			ImageIO.write(bi1, "jpg", w2);// 不管输出什么格式图片，此处不需改动
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
