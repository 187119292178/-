/**
 * CopyRight (c) 2016 北京好数科技有限公司 保留所有权利
 */

package com.aishangrun.aishangrun.utils.exception;

/**
 * 异常基类
 * 
 * @author zhangzhan
 */
public class BasicException extends Exception {

    private static final long serialVersionUID = -8954757564796420359L;

    // 错误编码
    private int errorCode;

    public BasicException(int errorCode) {
        super("ERROR");
        this.errorCode = errorCode;
    }

    public BasicException(int errorCode, String msg) {
        super(msg);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

}
