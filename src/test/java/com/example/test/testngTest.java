package com.example.test;

import com.example.test.enums.*;
import com.example.test.model.PackageModel;
import com.example.test.model.SpecialModel;
import com.example.test.service.ComputeService;
import com.example.test.service.ComputeSpecial;
import com.example.test.service.InternationalOver;
import org.apache.commons.lang.ObjectUtils;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import sun.swing.SwingUtilities2;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class testngTest {
    @DataProvider(name = "calBigWeightData")
    public static Object[][] calBigWeightData() {
        return new Object[][]{
                {Vip.SSVIP, CabinType.FIRST, TicketType.ADULT, 70},
                {Vip.SVIP, CabinType.BUSINESS, TicketType.CHILD, 50},
                {Vip.VIP, CabinType.ECONOMY, TicketType.CHILD, 40},
                {Vip.VIP, CabinType.ECONOMY, TicketType.BABY, 30}
//                {Vip.VIP, CabinType.ECONOMY, null,20},
        };
    }

    @DataProvider(name = "calSpecialData")
    public static Object[][] calSpecialData() {
        int[] stageList = {2, 23, 32, 45};
        int[] priceList = {3600, 3900, 5200};
        SpecialModel model0 = new SpecialModel(-1, "one", 25);
        SpecialModel model1 = new SpecialModel(-1, "one", 0);
        SpecialModel model2 = new SpecialModel(-1, "one", 50);
        return new Object[][]{
                {stageList, priceList, model0, new BigDecimal(3900)},
                {stageList, priceList, model1, new BigDecimal(0)},
                {stageList, priceList, model2, new BigDecimal(0)},
        };
    }

    @DataProvider(name = "calculateCostData")
    public static Object[][] calculateCostData() {
        List list = new ArrayList<PackageModel>();
        list.add(new PackageModel(0, 30, 30, 30, 10));
        list.add(new PackageModel(0, 30, 30, 30, 10));

        List list2 = new ArrayList<PackageModel>();
        list2.add(new PackageModel(0, 30, 30, 30, 30));
        list2.add(new PackageModel(0, 30, 30, 30, 30));
        return new Object[][]{
                {Vip.VIP, CabinType.ECONOMY, new BigDecimal(1000), TicketType.ADULT, list, new BigDecimal(0.00).setScale(2)},
                {Vip.VIP, CabinType.ECONOMY, new BigDecimal(1000), TicketType.ADULT, list2, new BigDecimal(300.00).setScale(2)}
        };
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
                {CabinType.ECONOMY, 0, FreeZone.ONE, FlightInterval.ONE, new BigDecimal("0").setScale(2)},
                {CabinType.ECONOMY, 3, FreeZone.TWO, FlightInterval.TWO, new BigDecimal("1100").setScale(2)},
                {CabinType.SUPERECONOMY, 4, FreeZone.TWO, FlightInterval.TWO, new BigDecimal("2200").setScale(2)},
                {CabinType.BUSINESS, 5, FreeZone.TWO, FlightInterval.TWO, new BigDecimal("3790").setScale(2)},
                {CabinType.FIRST, 0, FreeZone.TWO, FlightInterval.TWO, new BigDecimal("0").setScale(2)}

        };
    }

    @DataProvider(name = "pacageClassfyData")
    public static Object[][] pacageClassfyDataData() {
        List<PackageModel> list = new ArrayList<PackageModel>();
        list.add(new PackageModel(0, 20, 10, 20, 1));
        List<PackageModel> list1 = new ArrayList<PackageModel>();
        list1.add(new PackageModel(0, 30, 30, 30, 20));
        List<PackageModel> list2 = new ArrayList<PackageModel>();
        list2.add(new PackageModel(0, 30, 30, 30, 25));
        List<PackageModel> list3 = new ArrayList<PackageModel>();
        list3.add(new PackageModel(0, 30, 30, 30, 29));
        List<PackageModel> list4 = new ArrayList<PackageModel>();
        list4.add(new PackageModel(0, 80, 50, 30, 15));
        List<PackageModel> list5 = new ArrayList<PackageModel>();
        list5.add(new PackageModel(0, 80, 50, 30, 29));

        List<Integer> ans = new ArrayList<>();
        ans.add(0);
        ans.add(0);
        ans.add(0);
        ans.add(0);
        ans.add(0);
        List<Integer> ans1 = new ArrayList<>();
        ans1.add(1);
        ans1.add(0);
        ans1.add(0);
        ans1.add(0);
        ans1.add(0);
        List<Integer> ans2 = new ArrayList<>();
        ans2.add(0);
        ans2.add(1);
        ans2.add(0);
        ans2.add(0);
        ans2.add(0);
        List<Integer> ans3 = new ArrayList<>();
        ans3.add(0);
        ans3.add(0);
        ans3.add(1);
        ans3.add(0);
        ans3.add(0);
        List<Integer> ans4 = new ArrayList<>();
        ans4.add(0);
        ans4.add(0);
        ans4.add(0);
        ans4.add(1);
        ans4.add(0);
        List<Integer> ans5 = new ArrayList<>();
        ans5.add(0);
        ans5.add(0);
        ans5.add(0);
        ans5.add(0);
        ans5.add(1);
        return new Object[][]{
                {list, ans},
                {list1, ans1},
                {list2, ans2},
                {list3, ans3},
                {list4, ans4},
                {list5, ans5},
        };
    }

    @DataProvider(name = "calculData")
    public static Object[][] calculData() {
        int[] price = {30, 40, 50, 60};
        List<Integer> ans = new ArrayList<>();
        ans.add(0);
        ans.add(0);
        ans.add(0);
        ans.add(0);
        ans.add(0);
        List<Integer> ans1 = new ArrayList<>();
        ans1.add(1);
        ans1.add(0);
        ans1.add(0);
        ans1.add(0);
        ans1.add(0);
        List<Integer> ans2 = new ArrayList<>();
        ans2.add(0);
        ans2.add(1);
        ans2.add(0);
        ans2.add(0);
        ans2.add(0);
        List<Integer> ans3 = new ArrayList<>();
        ans3.add(0);
        ans3.add(0);
        ans3.add(1);
        ans3.add(0);
        ans3.add(0);
        List<Integer> ans4 = new ArrayList<>();
        ans4.add(0);
        ans4.add(0);
        ans4.add(0);
        ans4.add(1);
        ans4.add(0);
        List<Integer> ans5 = new ArrayList<>();
        ans5.add(0);
        ans5.add(0);
        ans5.add(0);
        ans5.add(0);
        ans5.add(1);
        return new Object[][]{
                {CabinType.ECONOMY, ans, price, new BigDecimal("0").setScale(2)},
                {CabinType.ECONOMY, ans1, price, new BigDecimal("0").setScale(2)},
                {CabinType.SUPERECONOMY, ans2, price, new BigDecimal("30").setScale(2)},
                {CabinType.SUPERECONOMY, ans3, price, new BigDecimal("40").setScale(2)},
                {CabinType.FIRST, ans4, price, new BigDecimal("50").setScale(2)},
                {CabinType.FIRST, ans5, price, new BigDecimal("0").setScale(2)},
        };
    }

    @DataProvider(name = "calculOverWeightData")
    public static Object[][] calculOverWeightData() {
        List list = new ArrayList<PackageModel>();
        list.add(new PackageModel(0, 30, 30, 30, 30));
        list.add(new PackageModel(0, 30, 30, 30, 30));
        return new Object[][]{
                {CabinType.FIRST, list, FlightInterval.ONE, new BigDecimal("0").setScale(2)},
                {CabinType.FIRST, list, FlightInterval.TWO, new BigDecimal("0").setScale(2)},
                {CabinType.FIRST, list, FlightInterval.THREE, new BigDecimal("0").setScale(2)},
                {CabinType.FIRST, list, FlightInterval.FOUR, new BigDecimal("0").setScale(2)},
                {CabinType.FIRST, list, FlightInterval.FIVE, new BigDecimal("0").setScale(2)},
        };
    }

    @DataProvider(name = "computePriceData")
    public static Object[][] computePriceData() {
        List list = new ArrayList<PackageModel>();
        list.add(new PackageModel(0, 30, 30, 30, 30));
        list.add(new PackageModel(0, 30, 30, 30, 30));
        List specialList = new ArrayList<SpecialModel>();
        return new Object[][]{
                {Vip.GENERAL, TicketType.ADULT, CabinType.FIRST, new BigDecimal(1000).setScale(2), FreeZone.ZERO, FlightInterval.ZERO, list, specialList, new BigDecimal("300").setScale(2)},
                {Vip.GENERAL, TicketType.ADULT, CabinType.FIRST, new BigDecimal(1000).setScale(2), FreeZone.ZERO, FlightInterval.ONE, list, specialList, new BigDecimal("0").setScale(2)},
                {Vip.GENERAL, TicketType.ADULT, CabinType.FIRST, new BigDecimal(1000).setScale(2), FreeZone.ZERO, FlightInterval.TWO, list, specialList, new BigDecimal("0").setScale(2)},
                {Vip.GENERAL, TicketType.ADULT, CabinType.FIRST, new BigDecimal(1000).setScale(2), FreeZone.ZERO, FlightInterval.THREE, list, specialList, new BigDecimal("0").setScale(2)},
                {Vip.GENERAL, TicketType.ADULT, CabinType.FIRST, new BigDecimal(1000).setScale(2), FreeZone.ZERO, FlightInterval.FOUR, list, specialList, new BigDecimal("0").setScale(2)},
                {Vip.GENERAL, TicketType.ADULT, CabinType.FIRST, new BigDecimal(1000).setScale(2), FreeZone.ZERO, FlightInterval.FIVE, list, specialList, new BigDecimal("0").setScale(2)}
        };
    }

    @DataProvider(name = "computeData")
    public static  Object[][] computeData(){
        List<SpecialModel> specialModelList1 = new ArrayList<>();
        specialModelList1.add(new SpecialModel(-1,"ONE",30));
        List<SpecialModel> specialModelList2 = new ArrayList<>();
        specialModelList2.add(new SpecialModel(-1,"TWO",30));
        List<SpecialModel> specialModelList3 = new ArrayList<>();
        specialModelList3.add(new SpecialModel(-1,"THREE",30));
        List<SpecialModel> specialModelList4 = new ArrayList<>();
        specialModelList4.add(new SpecialModel(-1,"FOUR",30));
        List<SpecialModel> specialModelList5 = new ArrayList<>();
        specialModelList5.add(new SpecialModel(-1,"FIVE",30));
        List<SpecialModel> specialModelList6 = new ArrayList<>();
        specialModelList6.add(new SpecialModel(-1,"SIX",30));
        List<SpecialModel> specialModelList7 = new ArrayList<>();
        specialModelList7.add(new SpecialModel(-1,"SEVEN",30));
        List<SpecialModel> specialModelList8 = new ArrayList<>();
        specialModelList8.add(new SpecialModel(-1,"EIGHT",30));
        List<SpecialModel> specialModelList9 = new ArrayList<>();
        specialModelList9.add(new SpecialModel(-1,"NINE",30));

        List<PackageModel> list = new ArrayList<>();
        return new Object[][]{
                {specialModelList1,list,new BigDecimal("0").setScale(2)},
                {specialModelList2,list,new BigDecimal("0").setScale(2)},
                {specialModelList3,list,new BigDecimal("3900").setScale(2)},
                {specialModelList4,list,new BigDecimal("2600").setScale(2)},
                {specialModelList5,list,new BigDecimal("0").setScale(2)},
                {specialModelList6,list,new BigDecimal("3900").setScale(2)},
                {specialModelList7,list,new BigDecimal("2600").setScale(2)},
                {specialModelList8,list,new BigDecimal("0").setScale(2)},
                {specialModelList9,list,new BigDecimal("7800").setScale(2)}
        };
    }
    @Test(dataProvider = "calBigWeightData")
    public void calBigWeightTest(Vip vip, CabinType cabinType, TicketType ticketType, int ans) {
        Assert.assertEquals(ComputeService.calBigWeight(vip, cabinType, ticketType), ans);
    }

    @Test(dataProvider = "calSpecialData")
    public void calSpecialTest(int[] stageList, int[] priceList, SpecialModel modele, BigDecimal ans) {
        Assert.assertEquals(ComputeSpecial.calSpecial(stageList, priceList, modele), ans);
    }

    @Test(dataProvider = "calculateCostData")
    public void calculateCostTest(Vip vip, CabinType cabinType, BigDecimal cost, TicketType ticketType, List<PackageModel> list, BigDecimal ans) {
        Assert.assertEquals(ComputeService.calculateCost(vip, cabinType, cost, ticketType, list), ans);
    }

    @Test(dataProvider = "calculOverNumData")
    public void calculOverNumTest(CabinType cabinType, int len, FreeZone freeZone, FlightInterval section, BigDecimal ans) {
        Assert.assertEquals(InternationalOver.calculOverNum(cabinType, len, freeZone, section), ans);
    }

    @Test(dataProvider = "pacageClassfyData")
    public void pacageClassfyTest(List<PackageModel> list, List<Integer> ans) {
        Assert.assertEquals(InternationalOver.pacageClassfy(list), ans);
    }

    @Test(dataProvider = "calculData")
    public void calculDataTest(CabinType cabinType, List<Integer> list, int[] price, BigDecimal ans) {
        Assert.assertEquals(InternationalOver.calcul(cabinType, list, price), ans);
    }

    @Test(dataProvider = "calculOverWeightData")
    public void calculOverWeightTest(CabinType cabinType, List<PackageModel> list, FlightInterval section, BigDecimal ans) {
        Assert.assertEquals(InternationalOver.calculOverWeight(cabinType, list, section), ans);
    }

    @Test(dataProvider = "computePriceData")
    public void computePriceTest(Vip vip, TicketType ticketType, CabinType cabinType, BigDecimal cost, FreeZone freeZone, FlightInterval section, List<PackageModel> list, List<SpecialModel> specialList, BigDecimal ans) {
        Assert.assertEquals(ComputeService.computePrice(vip, ticketType, cabinType, cost, freeZone, section, list, specialList), ans);
    }

    @Test(dataProvider = "computeData")
    public void computeTest(List<SpecialModel> specialList,List<PackageModel> list,BigDecimal ans){
        Assert.assertEquals(ComputeSpecial.compute(specialList,list), ans);
    }
}
