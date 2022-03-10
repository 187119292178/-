package com.aishangrun.aishangrun.utils;

/***
 * 服务端返回给浏览器的数据封装类
 * 主要用于ajax请求的相应
 * @author xuanwent
 *
 */
public class ResultAjax {

    /**
     * status 保存请求执行状态：1-成功
     */
    private Integer status;

    /**
     * msg保存服务器要发送给浏览器的消息
     */
    private String msg;

    /**
     * data保存服务器要发送给浏览器的数据
     */
    private Object data;



    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }



    @Override
    public String toString() {
        return "ResultPage [status=" + status + ", msg=" + msg + ", data=" + data +  "]";
    }

    /**
     * 返回成功
     *
     * @return
     */
    public static ResultAjax ok(String  msg,Object data) {
        return new ResultAjax(1, msg, data );
    }

    /**
     * 返回失败
     *
     * @return
     */
    public static ResultAjax fail(String msg) {
        return new ResultAjax(0, msg, "");
    }


    public ResultAjax(Integer status, String msg, Object data ) {
        this.status = status;
        this.msg = msg;
        this.data = data;

    }

    public ResultAjax() {
    }
}
