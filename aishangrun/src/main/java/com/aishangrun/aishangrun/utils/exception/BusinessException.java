/**
 * CopyRight (c) 2016 北京好数科技有限公司 保留所有权利
 */

package com.aishangrun.aishangrun.utils.exception;

/**
 * 业务异常类
 * 
 * @author luandy
 * @version 1.0.0.2016年3月23日
 */
public class BusinessException extends BasicException {

    /**  */
    private static final long serialVersionUID = 6454976252327808607L;

    /**
     * @param errorCode
     */
    public BusinessException(int errorCode) {
        super(errorCode, "BUSINESS ERROR");
    }

    public BusinessException(int errorCode, String msg) {
        super(errorCode, msg);
    }


}
