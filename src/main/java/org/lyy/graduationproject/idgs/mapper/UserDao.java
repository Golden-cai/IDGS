package org.lyy.graduationproject.idgs.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.lyy.graduationproject.idgs.infrastructure.PO.UserInfoPO;

import java.sql.SQLException;

/**
 * description: UserDao
 * date: 2023/5/6 23:09
 * author: caugi
 * version: 1.0
 */
@Mapper
public interface UserDao {

    UserInfoPO queryUserInfoByTel(@Param("tel") String tel, @Param("password") String password) throws SQLException;
}
