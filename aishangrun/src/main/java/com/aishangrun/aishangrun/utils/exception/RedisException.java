/**
 * CopyRight (c) 2016 北京好数科技有限公司 保留所有权利
 */

package com.aishangrun.aishangrun.utils.exception;

/**
 * redis缓存异常类
 * 
 * @author luandy
 * @version 1.0.0.2016年3月23日
 */
public class RedisException extends SystemException {

    /**  */
    private static final long serialVersionUID = 2012222051213155448L;

    /**
     * @param errorCode
     */
    public RedisException(int errorCode) {
        super(errorCode, "系统繁忙，请稍后再试");
    }

    public RedisException(int errorCode, String msg) {
        super(errorCode, msg);
    }

}
