package com.aishangrun.aishangrun.controller.agent;

import com.aishangrun.aishangrun.config.jwt.JwtIgnore;
import com.aishangrun.aishangrun.utils.ResultUtil;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Auther: cxy
 * @Date: 2022/1/4
 * @Description: 短信验证码测试
 */

@RestController
@RequestMapping("sys/agent/")
public class PhoneCode {

    private static final Logger logger = LoggerFactory.getLogger(PhoneCode.class);






//    // 产品名称:云通信短信API产品,开发者无需替换
    static final String product = "cxy";
    // 产品域名,开发者无需替换
    static final String domain = "dysmsapi.aliyuncs.com";

    // TODO 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
    static final String accessKeyId = "LTAI4G6gV5b6zqyQGGqNT6J1";           // TODO 改这里
    static final String accessKeySecret = "Tbva1bph7qH4PejJthr6nSVgG3VHqD"; // TODO 改这里

    public static SendSmsResponse sendSms(String telephone, String code) throws ClientException {
        // 可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "100000");
        System.setProperty("sun.net.client.defaultReadTimeout", "100000");

        // 初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        // 组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        // 必填:待发送手机号
        request.setPhoneNumbers(telephone);
        // 必填:短信签名-可在短信控制台中找到
        request.setSignName("伞亮科技"); // TODO 改这里
        // 必填:短信模板-可在短信控制台中找到
        request.setTemplateCode("SMS_232162832");  // TODO 改这里
        // 可选:模板中的变量替换JSON串,如模板内容为"亲爱的用户,您的验证码为${code}"时,此处的值为
        request.setTemplateParam("{\"code\":\"" + code + "\"}");

        // 选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        // request.setSmsUpExtendCode("90997");

        // 可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId("yourOutId");

        // hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);

        if(sendSmsResponse.getCode()!= null && sendSmsResponse.getCode().equals("OK")){
            System.out.println("短信发送成功！");

        }else {
            System.out.println("短信发送失败！");
        }
        return sendSmsResponse;
    }


    /*请求短信验证码*/
    @PostMapping("/duanxin")
    @JwtIgnore // 加此注解, 请求不做token验证
    @ResponseBody
    public ResultUtil duanxin(String phone, HttpServletRequest request){
        String code =Integer.toString((int)(Math.random()*9999)+1000);

        //

        SendSmsResponse sendSms = null;//填写你需要测试的手机号码
        try {
           // phone="18739987649";
            sendSms = sendSms(phone,code);
        } catch (ClientException e) {
            e.printStackTrace();
        }
        System.out.println("验证码为："+code);
        System.out.println("短信接口返回的数据----------------");
        System.out.println("Code=" + sendSms.getCode());
        System.out.println("Message=" + sendSms.getMessage());
        System.out.println("RequestId=" + sendSms.getRequestId());
        System.out.println("BizId=" + sendSms.getBizId());

        request.getServletContext().setAttribute("code",code);
        request.getServletContext().setAttribute("phone",phone);

        if(sendSms.getMessage().equals("OK")){
            return ResultUtil.ok("发送成功");
        }else{
            return ResultUtil.error("发送失败"+sendSms.getMessage());
        }
    }









}