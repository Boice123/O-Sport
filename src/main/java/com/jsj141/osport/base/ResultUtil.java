package com.jsj141.osport.base;

/**
 * 结果返回统一数据工具类
 */
public class ResultUtil {
    public static final int CODE_SUCCESS = 0;
    public static final int CODE_NOLOGIN = 1;
    public static final int CODE_ERROR = 2;

    public static final String MSG_ERROR = "参数错误";
    public static final String MSG_SUCCESS = "获取成功";
    public static final String MSG_NOLOGIN = "未登录或登录超时";

    private ResultUtil() {
    }

    public static Result initResult() {
        return new Result(CODE_ERROR, MSG_ERROR);
    }

    public static void setSuccess(Result result, Object data) {
        setSuccess(result, MSG_SUCCESS, null);
    }

    public static void setSuccess(Result result, String msg, Object data) {
        result.setCode(CODE_SUCCESS);
        result.setMsg(msg == null ? MSG_SUCCESS : msg);
        result.setData(data);
    }


}
