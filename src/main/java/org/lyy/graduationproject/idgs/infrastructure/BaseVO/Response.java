package org.lyy.graduationproject.idgs.infrastructure.BaseVO;

/**
 * description: Response
 * date: 2023/4/16 20:11
 * author: lyy
 * version: 1.0
 */
public class Response<T> {
    private Result result;
    private T data;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
