package org.lyy.graduationproject.idgs.infrastructure.BaseVO;

/**
 * description: ApplicationException
 * date: 2023/4/16 20:39
 * author: caugi
 * version: 1.0
 */
public class ApplicationException extends Exception {
    private Result result;

    public ApplicationException(WebResultEnum result) {
        this.result = result.getErrorResult();
    }
    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }
}
