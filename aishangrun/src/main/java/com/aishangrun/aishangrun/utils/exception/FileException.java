/**
 * CopyRight (c) 2016 北京好数科技有限公司 保留所有权利
 */

package com.aishangrun.aishangrun.utils.exception;

/**
 * 文件异常类
 * 
 * @author luandy
 * @version 1.0.0.2016年3月23日
 */
public class FileException extends SystemException {

    /**  */
    private static final long serialVersionUID = -6914981011600398586L;

    /**
     * @param errorCode
     */
    public FileException(int errorCode) {
        super(errorCode, "系统繁忙，请稍后再试");
    }

    public FileException(int errorCode, String msg) {
        super(errorCode, msg);
    }

}
