package org.lyy.graduationproject.idgs.controller;


import org.lyy.graduationproject.idgs.infrastructure.BaseVO.Response;
import org.lyy.graduationproject.idgs.infrastructure.BaseVO.ResponseUtil;
import org.lyy.graduationproject.idgs.mapper.HospitalDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/v1/test")
public class TestController {

    @Autowired
    DataSource dataSource;

    @Autowired
    HospitalDao hospitalDao;


    @PostMapping("/getHospital")
    public Response test() throws SQLException {

       int po = hospitalDao.queryAllHospital().size();

        return ResponseUtil.success(po);
    }
}
