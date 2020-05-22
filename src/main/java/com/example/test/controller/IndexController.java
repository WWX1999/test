package com.example.test.controller;

import com.example.test.enums.*;
import com.example.test.model.PackageModel;
import com.example.test.model.SpecialModel;
import com.example.test.service.ComputeService;
import net.sf.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {
    @RequestMapping(value = "")
    public String index() {
        return "index";
    }

    /**
     * 传入和机票有关的信息，返回
     *
     * @param vip vip的等级
     * @param type 机票类型（例：成人票）
     * @param classes 机舱类型（例：经济舱）
     * @param price 经济舱价格（为了计算超重费用）
     * @param freezone 经济舱用户免费提供行李托运的区间
     * @param section 国航划分的不同区间
     * @return
     */
    @RequestMapping(value = "compute")
    @ResponseBody
    public String compute(String vip, String type, String classes, String price, String freezone, String section, String jsonText,String specialText) {
        // 标准化相关数据到对应enum
        Vip en_vip = Vip.valueOf(vip);
        TicketType enType = TicketType.valueOf(type);
        CabinType enCabin = CabinType.valueOf(classes);
        FreeZone enZone = FreeZone.valueOf(freezone);
        FlightInterval enInterval = FlightInterval.valueOf(section);
        // 标准化背包list
        JSONArray jsonArray = JSONArray.fromObject(jsonText);
        List<PackageModel> list = (ArrayList<PackageModel>)JSONArray.toCollection(jsonArray, PackageModel.class);
        System.out.println(specialText);
        for(PackageModel item:list){
            System.out.println("type是:"+item.getWeight());
        }
        // 标准化特殊背包
        JSONArray specialArray = JSONArray.fromObject(specialText);
        List<SpecialModel> specialModelList = (ArrayList<SpecialModel>)JSONArray.toCollection(specialArray,SpecialModel.class);
        for(SpecialModel item:specialModelList){
            System.out.println("type是:"+item.getPakageType());
        }
        // 标准化金额，保留两位小数
        BigDecimal cost = new BigDecimal(price);
        cost.setScale(2);
        BigDecimal ans = ComputeService.computePrice(en_vip,enType,enCabin,cost,enZone,enInterval,list,specialModelList);
        ans = ans.setScale(2);
        return "最终价格为：" + ans;
    }
}
