package org.lyy.graduationproject.idgs.infrastructure.BaseVO;

import org.apache.ibatis.jdbc.SQL;

/**
 * description: WebResultEnum
 * date: 2023/4/16 20:20
 * author: lyy
 * version: 1.0
 */
public enum WebResultEnum {
    SUCCESS("0000", "Success"),
    FAIL("9999", "Fail"),
    SQL_EXCEPTION("1000", "Execute sql exception"),

    PARAM_NULL("1001", "Params null"),

    PARAM_ERROE("1002", "Params error"),

    QUERY_SHIFTS_ERROR("2001", "More than one effective shifts");


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
