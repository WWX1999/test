package com.example.test;

import com.example.test.controller.IndexController;
import com.example.test.enums.*;
import com.example.test.model.PackageModel;
import com.example.test.model.SpecialModel;
import com.example.test.service.ComputeService;
import com.example.test.service.ComputeSpecial;
import com.example.test.service.InternationalOver;
import net.sf.json.JSONArray;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class testngTest {
    @DataProvider(name = "calBigWeightData")
    public static Object[][] calBigWeightData() {
        return new Object[][]{
                {"SSVIP", "FIRST", "ADULT", 70},
                {"SVIP", "BUSINESS", "CHILD", 50},
                {"VIP", "ECONOMY", "CHILD", 40},
                {"VIP", "ECONOMY", "BABY", 30}
        };
    }

    @Test(dataProvider = "calBigWeightData")
    public void calBigWeightTest(String vip, String cabinType, String ticketType, int ans) {
        String v = "vip";
        Vip vip1 = Vip.valueOf(vip);
        CabinType cabinType1 = CabinType.valueOf(cabinType);
        TicketType ticketType1 = TicketType.valueOf(ticketType);
        Assert.assertEquals(ComputeService.calBigWeight(vip1, cabinType1, ticketType1), ans);
    }

    @DataProvider(name = "calculOverNumData")
    public static Object[][] calculOverNumData() {
        List list = new ArrayList<PackageModel>();
        list.add(new PackageModel(0, 30, 30, 30, 10));
        list.add(new PackageModel(0, 30, 30, 30, 10));

        List list2 = new ArrayList<PackageModel>();
        list2.add(new PackageModel(0, 30, 30, 30, 30));
        list2.add(new PackageModel(0, 30, 30, 30, 30));
        return new Object[][]{
                {"ECONOMY", 0, "ONE", "ONE", 0},
                {"ECONOMY", 3, "TWO", "TWO", 1100},
                {"SUPERECONOMY", 4, "TWO", "TWO", 2200},
                {"BUSINESS", 5, "TWO", "TWO", 3790},
                {"FIRST", 0, "TWO", "TWO", 0}

        };
    }

    @Test(dataProvider = "calculOverNumData")
    public void calculOverNumTest(String cabinType, int len, String freeZone, String section, float ans) {
        CabinType cabinType1 = CabinType.valueOf(cabinType);
        FreeZone freeZone1 =FreeZone.valueOf(freeZone);
        FlightInterval section1 = FlightInterval.valueOf(section);
        BigDecimal ans1 = new BigDecimal(ans).setScale(2);
        Assert.assertEquals(InternationalOver.calculOverNum(cabinType1, len, freeZone1, section1), ans1);
    }

    @DataProvider(name = "controllerData")
    public static Object[][] controllerData() {
        return new Object[][]{
                {"VIP","ADULT","ECONOMY","1000","ONE","TWO",
                "[{\"id\":\"1\",\"x\":\"30\",\"y\":\"30\",\"z\":\"30\",\"weight\":\"30\"},{\"id\":\"2\",\"x\":\"30\",\"y\":\"30\",\"z\":\"30\",\"weight\":\"30\"},{\"id\":\"3\",\"x\":\"30\",\"y\":\"30\",\"z\":\"30\",\"weight\":\"30\"},{\"id\":\"4\",\"x\":\"30\",\"y\":\"30\",\"z\":\"30\",\"weight\":\"30\"}]\n",
                "[{\"id\":\"1\",\"packetType\":0,\"weight\":\"20\",\"pakageType\":\"type1\"}]",
                        "最终价格为：8140.00"
                },
                {
                        "VIP","ADULT","ECONOMY","1240","ZERO","TWO",
                    "[{\"id\":\"1\",\"x\":\"30\",\"y\":\"30\",\"z\":\"30\",\"weight\":\"30\"},{\"id\":\"2\",\"x\":\"29\",\"y\":\"24\",\"z\":\"23\",\"weight\":\"24\"},{\"id\":\"3\",\"x\":\"23\",\"y\":\"24\",\"z\":\"24\",\"weight\":\"20\"},{\"id\":\"4\",\"x\":\"32\",\"y\":\"34\",\"z\":\"33\",\"weight\":\"30\"}]",
                       "[{\"id\":\"1\",\"packetType\":0,\"weight\":\"30\",\"pakageType\":\"THREE\"},{\"id\":\"2\",\"packetType\":0,\"weight\":\"30\",\"pakageType\":\"FOUR\"}]",
                        "最终价格为：16720.00"
                }
        };
    }

    @Test(dataProvider = "controllerData")
    public void controllerTest(String vip, String type, String classes, String price, String freezone, String section, String jsonText,String specialText,String ans) {
        IndexController controller = new IndexController();
        String cost = controller.compute(vip,type,classes,price,freezone,section,jsonText,specialText);
        //BigDecimal bigCost = BigDecimal.valueOf(Double.parseDouble(cost));
        //BigDecimal ans1 = new BigDecimal(ans).setScale(2);
        Assert.assertEquals(cost, ans);
    }










}
