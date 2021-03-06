package com.ercifenpeixitong.ercifenpeixitong.domain;


import com.ercifenpeixitong.ercifenpeixitong.constant.ResultCode;

/**
 * 服务返回结果包装类
 */
public class ResultInfo<T> {
    private Integer resultCode;
    private String resultMessage;
    private T resultObj;

    public ResultInfo() {
        //默认是失败的
        this.resultCode = ResultCode.RESULT_CODE_FAIL;
    }

    public Integer getResultCode() {
        return resultCode;
    }

    public void setResultCode(Integer resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public T getResultObj() {
        return resultObj;
    }

    public void setResultObj(T resultObj) {
        this.resultObj = resultObj;
    }

    @Override
    public String toString() {
        return "ResultInfo{" +
                "resultCode=" + resultCode +
                ", resultMessage='" + resultMessage + '\'' +
                ", resultObj=" + resultObj +
                '}';
    }
}
