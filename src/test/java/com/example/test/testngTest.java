package com.example.test;

import com.example.test.enums.*;
import com.example.test.model.PackageModel;
import com.example.test.model.SpecialModel;
import com.example.test.service.ComputeService;
import com.example.test.service.ComputeSpecial;
import com.example.test.service.InternationalOver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class testngTest {
//    @DataProvider(name = "calBigWeightData")
//    public static Object[][] calBigWeightData() {
//        return new Object[][]{
//                {"SSVIP", "FIRST", "ADULT", 70},
//                {"SVIP", "BUSINESS", "CHILD", 50},
//                {"VIP", "ECONOMY", "CHILD", 40},
//                {"VIP", "ECONOMY", "BABY", 30}
//        };
//    }

    @Parameters({"vip","cabinType","ticketType","ans"})
    @Test
    public void calBigWeightTest(String vip, String cabinType, String ticketType, int ans) {
        //String v = "vip";
        Vip vip1 = Vip.valueOf(vip);
        CabinType cabinType1 = CabinType.valueOf(cabinType);
        TicketType ticketType1 = TicketType.valueOf(ticketType);
        Assert.assertEquals(ComputeService.calBigWeight(vip1, cabinType1, ticketType1), ans);
    }

    @Parameters({"cabinType","len","freeZone","section","ans"})
    @Test
    public void calculOverNumTest(String cabinType, int len, String freeZone, String section, float ans) {
        CabinType cabinType1 = CabinType.valueOf(cabinType);
        FreeZone freeZone1 =FreeZone.valueOf(freeZone);
        FlightInterval section1 = FlightInterval.valueOf(section);
        BigDecimal ans1 = new BigDecimal(ans).setScale(2);
        Assert.assertEquals(InternationalOver.calculOverNum(cabinType1, len, freeZone1, section1), ans1);
    }
}