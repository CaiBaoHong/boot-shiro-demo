package com.abc.vo;

import jdk.nashorn.internal.runtime.regexp.joni.constants.OPCode;

import java.util.HashMap;

/**
 * Created by CaiBaoHong on 2017/2/15.
 *
 */
public class BaseResponse extends HashMap<String, Object> {

    /////////////////////// 默认的键 ///////////////////////

    public static final String KEY_OPER = "oper";
    public static final String KEY_SUCC = "succ";
    public static final String KEY_CODE = "code";
    public static final String KEY_MSG = "msg";
    public static final String KEY_DATA = "data";

    /////////////////////// 默认的值 ///////////////////////

    public static final String DEFAULT_OPER_VAL = "default";
    public static final int DEFAULT_SUCC_CODE = 1;
    public static final int DEFAULT_FAIL_CODE = -1;
    public static final String DEFAULT_SUCC_MSG = "ok";
    public static final String DEFAULT_FAIL_MSG = "fail";


    /////////////////////// 最通用的两个构造函数 ///////////////////////

    /**
     * 无参构造：操作成功返回的响应信息
     */
    public BaseResponse() {
        this.put(KEY_OPER,DEFAULT_OPER_VAL);
        this.put(KEY_SUCC,true);
        this.put(KEY_CODE,DEFAULT_SUCC_CODE);
        this.put(KEY_MSG,DEFAULT_SUCC_MSG);
    }

    /**
     * 全参构造
     * @param operate
     * @param success
     * @param code
     * @param msg
     * @param data
     */
    public BaseResponse(String operate, boolean success, int code, String msg, Object data) {
        this.put(KEY_OPER,operate);
        this.put(KEY_SUCC,success);
        this.put(KEY_CODE,code);
        this.put(KEY_MSG,msg);
        if (data!=null){
            this.put(KEY_DATA,data);
        }
    }

    /////////////////////// 各种简便的静态工厂方法 ///////////////////////

    ////// 操作成功的：

    public static BaseResponse succ() {
        return new BaseResponse();
    }

    public static BaseResponse succ(String operate) {
        return new BaseResponse(operate,true, DEFAULT_SUCC_CODE, DEFAULT_SUCC_MSG,null);
    }

    public static BaseResponse succ(String operate,String message) {
        return new BaseResponse(operate,true, DEFAULT_SUCC_CODE, message,null);
    }

    public static BaseResponse succ(String operate,Object data) {
        return new BaseResponse(operate,true, DEFAULT_SUCC_CODE, DEFAULT_SUCC_MSG,data);
    }

    public static BaseResponse succ(String operate,String dataKey, Object dataVal) {
        return new BaseResponse(operate,true, DEFAULT_SUCC_CODE, DEFAULT_SUCC_MSG,null)
                .data(dataKey,dataVal);
    }

    ////// 操作失败的：

    public static BaseResponse fail() {
        return new BaseResponse(DEFAULT_OPER_VAL,false,DEFAULT_FAIL_CODE,DEFAULT_FAIL_MSG,null);
    }

    public static BaseResponse fail(String operate) {
        return new BaseResponse(operate,false, DEFAULT_FAIL_CODE, DEFAULT_FAIL_MSG,null);
    }

    public static BaseResponse fail(String operate,String message) {
        return new BaseResponse(operate,false, DEFAULT_FAIL_CODE, message,null);
    }

    public static BaseResponse fail(String operate,Object data) {
        return new BaseResponse(operate,false, DEFAULT_FAIL_CODE, DEFAULT_FAIL_MSG,data);
    }

    public static BaseResponse fail(String operate,String dataKey, Object dataVal) {
        return new BaseResponse(operate,false, DEFAULT_FAIL_CODE, DEFAULT_FAIL_MSG,null)
                .data(dataKey,dataVal);
    }


    /////////////////////// 各种链式调用方法 ///////////////////////

    /** 设置操作名称 */
    public BaseResponse oper(String operate){
        this.put(KEY_OPER,operate);
        return this;
    }

    /** 设置操作结果是否成功的标记 */
    public BaseResponse succ(boolean success){
        this.put(KEY_SUCC,success);
        return this;
    }

    /** 设置操作结果的代码 */
    public BaseResponse code(int code){
        this.put(KEY_CODE,code);
        return this;
    }

    /** 设置操作结果的信息 */
    public BaseResponse msg(String message){
        this.put(KEY_MSG,message);
        return this;
    }

    /** 设置操作返回的数据 */
    public BaseResponse data(Object dataVal){
        this.put(KEY_OPER,dataVal);
        return this;
    }

    /** 设置操作返回的数据，数据使用自定义的key存储 */
    public BaseResponse data(String dataKey, Object dataVal){
        this.put(dataKey,dataVal);
        return this;
    }






}
