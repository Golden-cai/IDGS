package org.lyy.graduationproject.idgs.controller;

import org.lyy.graduationproject.idgs.infrastructure.BaseVO.ApplicationException;
import org.lyy.graduationproject.idgs.infrastructure.BaseVO.Response;
import org.lyy.graduationproject.idgs.infrastructure.BaseVO.ResponseUtil;
import org.lyy.graduationproject.idgs.service.impl.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * description: UserController
 * date: 2023/5/6 22:42
 * author: caugi
 * version: 1.0
 */

@RestController
@RequestMapping("/controller/user")
public class UserController {
    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;


    /**
     * @Description: 根据用户手机号查询用户信息
     *
     * @param TelNumber
     * @return
     * @throws
     */
    @GetMapping("/query-user-info/{telNumber}/{password}")
    Response queryUserInfoByTel(@PathVariable("telNumber") String TelNumber, @PathVariable("password") String password) {
        Response response;
        try {
            response = ResponseUtil.success(userService.queryUserInfoByTel(TelNumber, password));
        } catch (ApplicationException e) {
            logger.error(e.getResult().getMsg(), e.getResult().getCode(), e);
            response = ResponseUtil.fail(e.getResult());
        }
        return response;
    }

}
