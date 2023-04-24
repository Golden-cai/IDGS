package org.lyy.graduationproject.idgs.infrastructure.BaseVO;

/**
 * description: ResponseUtil
 * date: 2023/4/16 20:13
 * author: lyy
 * version: 1.0
 */
public class ResponseUtil {

    public static final <T> Response success(T data) {

        Response response =  new Response();
        response.setResult(new Result(
                WebResultEnum.SUCCESS.getErrorResult().getCode(),
                WebResultEnum.SUCCESS.getErrorResult().getMsg()));
        response.setData(data);
        return response;
    }

    public static final <T> Response success() {

        Response response =  new Response();
        response.setResult(new Result(
                WebResultEnum.SUCCESS.getErrorResult().getCode(),
                WebResultEnum.SUCCESS.getErrorResult().getMsg()));
        return response;
    }

    public static final <T> Response fail(Result result) {
        Response response =  new Response();
        response.setResult(result);
        return response;
    }
}
