package org.lyy.graduationproject.idgs.infrastructure.BaseVO;

/**
 * description: Request
 * date: 2023/4/16 20:29
 * author: lyy
 * version: 1.0
 */
public class Request<T> {
    private T params;

    public T getParams() {
        return params;
    }

    public void setParams(T params) {
        this.params = params;
    }
}
