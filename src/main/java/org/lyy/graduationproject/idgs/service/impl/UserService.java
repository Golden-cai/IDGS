package org.lyy.graduationproject.idgs.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.mysql.cj.util.StringUtils;
import org.lyy.graduationproject.idgs.infrastructure.BaseVO.ApplicationException;
import org.lyy.graduationproject.idgs.infrastructure.BaseVO.WebResultEnum;
import org.lyy.graduationproject.idgs.infrastructure.PO.DoctorShiftsPO;
import org.lyy.graduationproject.idgs.infrastructure.PO.UserInfoPO;
import org.lyy.graduationproject.idgs.mapper.UserDao;
import org.lyy.graduationproject.idgs.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Iterator;

/**
 * description: UserService
 * date: 2023/5/6 22:56
 * author: caugi
 * version: 1.0
 */
@Service
public class UserService implements IUserService {
    Logger logger = LoggerFactory.getLogger(UserService.class);

    private final Snowflake snowFlake = IdUtil.getSnowflake();

    @Autowired
    private UserDao userDao;


    @Override
    public UserInfoPO queryUserInfoByTel(String tel, String password) throws ApplicationException {
        if (StringUtils.isNullOrEmpty(tel)) {
            logger.error("queryUserInfoByTel: params is null");
            throw new ApplicationException(WebResultEnum.PARAM_NULL);
        }
        try {
            return userDao.queryUserInfoByTel(tel, password);
        } catch (SQLException e) {
            logger.error("queryUserInfoByTel: sql exception", e);
            throw new ApplicationException(WebResultEnum.SQL_EXCEPTION);
        }
    }
}
