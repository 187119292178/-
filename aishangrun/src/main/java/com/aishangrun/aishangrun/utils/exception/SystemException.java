/**
 * CopyRight (c) 2016 北京好数科技有限公司 保留所有权利
 */

package com.aishangrun.aishangrun.utils.exception;

/**
 * 系统异常类
 * 
 * @author zhangzhan
 * @version 1.0.0.2017年2月14日
 */
public class SystemException extends BasicException {

    private static final long serialVersionUID = -5383757341406403295L;

    public SystemException(int errorCode) {
        super(errorCode, "系统繁忙，请稍后再试");
    }

    public SystemException(int errorCode, String msg) {
        super(errorCode, msg);
    }

}
