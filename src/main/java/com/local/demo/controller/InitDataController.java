package com.local.demo.controller;

import com.local.demo.entity.ChildT;
import com.local.demo.entity.MotherT;
import com.local.demo.global.ResponseVO;
import com.local.demo.mapper.ChildTMapper;
import com.local.demo.mapper.MotherTMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/initData")
@Slf4j
public class InitDataController {

    @Autowired
    private MotherTMapper motherTMapper;

    @Autowired
    private ChildTMapper childTMapper;

    @GetMapping("/start")
    ResponseVO queryUserInfo(@PathParam("dataNum") Long count) throws ParseException {
        SimpleDateFormat sdf  = new SimpleDateFormat("yyyy-MM-dd");
        Date parse = sdf.parse("2024-06-21");
        List<ChildT> childList = new ArrayList<>();
        List<MotherT> motherList = new ArrayList<>();
        for(int i=50501;i<=count;i++){
            ChildT childT = new ChildT();
            childT.setId(Long.valueOf(i));
            childT.setMotherId(Long.valueOf(i));
            childT.setName("c"+i);
            childT.setBatchDate(parse);
            childList.add(childT);

            MotherT motherT = new MotherT();
            motherT.setId(Long.valueOf(i));
            motherT.setName("m"+i);
            motherT.setBatchDate(parse);

            motherList.add(motherT);
        }
        motherTMapper.batchInsert(motherList);
        childTMapper.batchInsert(childList);
        return ResponseVO.success();
    }

}
