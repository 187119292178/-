package com.aishangrun.aishangrun.utils;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.OSSObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;

@Scope("singleton")
@Component(value = "ossUtil")
public class OSSUtil {

	/** 测试 **/
	 public static String endpoint = "oss-cn-beijing.aliyuncs.com";
	 public static String accessKeyId = "LTAIGvoG2siVQ1ee";
	 public static String accessKeySecret = "0wtroJL0DUj9k40FJsGYPPO09CVXyU";
	 public static String bucketName = "sldcadmin";
	/** 生产 **/
//	public static String endpoint = Constance.OSS_ENDPOINT_PROD;
//	public static String accessKeyId = Constance.OSS_ACCESSKEYID_PROD;
//	public static String accessKeySecret = Constance.OSS_ACCESSKEYSECRET_PROD;
//	public static String bucketName = Constance.OSS_BUCKETNAME_PROD;

	private static OSSClient ossClient;

	private OSSUtil() {
		// 创建OSSClient实例。
		ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
	}

//	public static void main(String[] args) throws Exception {
//		OSSUtil ossUtil = new OSSUtil();
//		ossUtil.downFileUrl("DeviceInfo.xls", "device/template");
//
//	}

	/**
	 * 文件上传
	 * 
	 * @param filePath
	 *            本地文件路径
	 * @param fileName
	 *            上传至服务器的文件名称
	 * @param objectType
	 *            上传至服务器文件夹路径
	 * @return true-成功， false-失败
	 */
	public boolean uploadFile(String filePath, String fileName, String objectType) {
		if (StringUtils.isBlank(fileName) || StringUtils.isBlank(objectType) || StringUtils.isBlank(filePath)) {
			return false;
		}
		boolean flag = false;
		try {
			// 设置服务器文件位置及命名
			String objectName = objectType + "/" + fileName;
			// 上传文件流。
			File f = new File(filePath);
			FileInputStream fin = new FileInputStream(f);

			ossClient.putObject(bucketName, objectName, fin);
			flag = true;
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e.getMessage());
		} finally {
			// ossClient.shutdown();
		}
		return flag;
	}

	/**
	 * 文件上传（InputStream 流上传）
	 * 
	 * @param fin
	 *            文件流
	 * @param fileName
	 *            上传至服务器的文件名称
	 * @param objectType
	 *            上传至服务器文件夹路径
	 * @return true-成功， false-失败
	 */
	public boolean uploadFile(InputStream fin, String fileName, String objectType) {
		if (StringUtils.isBlank(fileName) || StringUtils.isBlank(objectType) || fin == null) {
			return false;
		}
		boolean flag = false;
		try {
			// 设置服务器文件位置及命名
			String objectName = objectType + "/" + fileName;
			// 上传文件流。
			ossClient.putObject(bucketName, objectName, fin);
			flag = true;
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e.getMessage());
		} finally {
			// ossClient.shutdown();
		}
		return flag;
	}

	/**
	 * 文件下载 （下载返回byte数组）
	 * 
	 * @param fileName
	 *            文件名称
	 * @param objectType
	 *            服务器文件路径
	 * @return 文件byte数组
	 * @throws Exception
	 */
	public byte[] downFile(String fileName, String objectType) throws Exception {
		if (StringUtils.isBlank(fileName) || StringUtils.isBlank(objectType)) {
			return null;
		}
		// 设置服务器文件位置及命名
		String objectName = objectType + "/" + fileName;
		// ossObject包含文件所在的存储空间名称、文件名称、文件元信息以及一个输入流。
		OSSObject ossObject = ossClient.getObject(bucketName, objectName);
		InputStream in = ossObject.getObjectContent();
		// 读取文件内容。
		ByteArrayOutputStream baOut = new ByteArrayOutputStream();
		byte[] buff = new byte[100];
		int rc = 0;
		while ((rc = in.read(buff, 0, 100)) > 0) {
			baOut.write(buff, 0, rc);
		}
		byte[] in2b = baOut.toByteArray();

		// ossClient.shutdown();
		return in2b;
		// 数据读取完成后，获取的流必须关闭，否则会造成连接泄漏，导致请求无连接可用，程序无法正常工作。
	}

	/**
	 * 文件下载 （下载返回InputStream 流）
	 * 
	 * @param fileName
	 *            文件名称
	 * @param objectType
	 *            服务器文件路径
	 * @return 文件流InputStream
	 * @throws Exception
	 */
	public InputStream downFileIn(String fileName, String objectType) throws Exception {
		if (StringUtils.isBlank(fileName) || StringUtils.isBlank(objectType)) {
			return null;
		}
		// 设置服务器文件位置及命名
		String objectName = objectType + "/" + fileName;
		// ossObject包含文件所在的存储空间名称、文件名称、文件元信息以及一个输入流。
		OSSObject ossObject = ossClient.getObject(bucketName, objectName);
		InputStream in = ossObject.getObjectContent();

		// ossClient.shutdown();
		return in;
		// 数据读取完成后，获取的流必须关闭，否则会造成连接泄漏，导致请求无连接可用，程序无法正常工作。
	}

	/**
	 * 文件下载 （下载返回URL链接）
	 * 
	 * @param fileName
	 *            文件名称
	 * @param objectType
	 *            服务器文件路径
	 * @return 文件网络连接
	 * @throws Exception
	 */
	public String downFileUrl(String fileName, String objectType) {
		if (StringUtils.isBlank(fileName) || StringUtils.isBlank(objectType)) {
			return null;
		}
		// 设置服务器文件位置及命名
		String objectName = objectType + "/" + fileName;
		Date expiration = new Date(System.currentTimeMillis() + 3600L * 1000 * 24 * 365 * 10);
		// 生成URL
		URL url = ossClient.generatePresignedUrl(bucketName, objectName, expiration);
		if (url != null) {
			return url.toString();
		}
		return null;
	}

	/**
	 * 文件下载 （下载返回URL链接）
	 * 
	 * @param fileName
	 *            文件名称
	 * @return 文件网络连接
	 * @throws Exception
	 */
	public String downFileUrl(String fileName) {
		if (StringUtils.isBlank(fileName)) {
			return null;
		}
		// 设置服务器文件位置及命名
		String objectName = fileName;
		Date expiration = new Date(System.currentTimeMillis() + 3600L * 1000 * 24 * 365 * 10);
		// 生成URL
		URL url = ossClient.generatePresignedUrl(bucketName, objectName, expiration);
		if (url != null) {
			return url.toString();
		}
		return null;
	}

	/**
	 * 文件删除 （下载返回URL链接）
	 *
	 * @param objectName
	 *            文件名称
	 * @return 文件网络连接
	 * @throws Exception
	 */
	public void deleteFileUrl(String objectName) {
		// 生成URL
		ossClient.deleteObject(bucketName, objectName);
		// 关闭OSSClient。
		ossClient.shutdown();
	}
}