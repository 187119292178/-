/**
 * CopyRight (c) 2016 北京好数科技有限公司 保留所有权利
 */

package com.aishangrun.aishangrun.utils.exception;

/**
 * 数据库异常类
 * 
 * @author luandy
 * @version 1.0.0.2016年3月23日
 */
public class DBException extends SystemException {

    /**  */
    private static final long serialVersionUID = 1331847837989419315L;

    /**
     * @param errorCode
     */
    public DBException(int errorCode) {
        super(errorCode, "系统繁忙，请稍后再试");
    }

    public DBException(int errorCode, String msg) {
        super(errorCode, msg);
    }
}
