package com.sunsine.fastdfs.exception;

/**
 * 
 * @ClassName: FastDFSException   
 * @Description: FastDFS Exception
 * @author: liutao
 * @date: 2018年3月2日 下午1:52:15
 */
public class FastDFSException extends Exception {

    /**  
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)  
	 */
	private static final long serialVersionUID = 1L;
	private int errorCode = 0;

    public FastDFSException(int errorCode) {
        this.errorCode = errorCode;
    }

    public FastDFSException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public FastDFSException(String message, Throwable cause, int errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public FastDFSException(Throwable cause, int errorCode) {
        super(cause);
        this.errorCode = errorCode;
    }

    public FastDFSException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace,
                            int errorCode) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.errorCode = errorCode;
    }
}
