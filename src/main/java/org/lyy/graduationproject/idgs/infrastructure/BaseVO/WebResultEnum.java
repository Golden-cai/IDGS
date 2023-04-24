package org.lyy.graduationproject.idgs.infrastructure.BaseVO;

import org.apache.ibatis.jdbc.SQL;

/**
 * description: WebResultEnum
 * date: 2023/4/16 20:20
 * author: lyy
 * version: 1.0
 */
public enum WebResultEnum {
    SUCCESS("0000", "success"),
    FAIL("9999", "fail"),
    SQL_EXCEPTION("1000", "execute sql exception"),

    PARAM_NULL("1001", "params null"),

    PARAM_ERROE("1002", "params error");


    private Result errorResult;

    WebResultEnum(String message, String code) {
        this.errorResult = new Result(message, code);
    }

    public Result getErrorResult() {
        return errorResult;
    }

    public void setErrorResult(Result errorResult) {
        this.errorResult = errorResult;
    }
}
