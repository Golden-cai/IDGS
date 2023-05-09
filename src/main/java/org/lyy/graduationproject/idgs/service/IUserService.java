package org.lyy.graduationproject.idgs.service;

import org.lyy.graduationproject.idgs.infrastructure.BaseVO.ApplicationException;
import org.lyy.graduationproject.idgs.infrastructure.PO.UserInfoPO;

/**
 * description: IUserService
 * date: 2023/5/6 22:52
 * author: caugi
 * version: 1.0
 */
public interface IUserService {

    UserInfoPO queryUserInfoByTel(String tel,String password) throws ApplicationException;
}
