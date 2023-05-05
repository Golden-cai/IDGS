package org.lyy.graduationproject.idgs.infrastructure.BaseVO;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * description: BasePO
 * date: 2023/4/16 21:28
 * author: lyy
 * version: 1.0
 */
public class BasePO implements Serializable {
    private static final long serialVersionUID =  1567558288662304842L;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    private Long createBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date lastUpdateTime;

    private Long lastUpdateBy;

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreateBy() {
        return this.createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Date getLastUpdateTime() {
        return this.lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Long getLastUpdateBy() {
        return this.lastUpdateBy;
    }

    public void setLastUpdateBy(Long lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }
}
