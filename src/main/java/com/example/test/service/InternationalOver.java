package com.example.test.service;

import com.example.test.enums.CabinType;
import com.example.test.enums.FlightInterval;
import com.example.test.enums.FreeZone;
import com.example.test.model.PackageModel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 国际地区的计费
 */
public class InternationalOver {
    private InternationalOver() {
    }

    private static int[][] arr ={
            {1400,2000,3000},
            {1100,1100,1590},
            {1170,1170,1590},
            {1380,1380,1590},
            {830,1100,1590}
    };
    /**
     * 计算国际地区超重的费用
     * @param cabinType
     * @param list
     * @param section
     * @return
     */
    public static BigDecimal calculOverWeight(CabinType cabinType, List<PackageModel> list, FlightInterval section){
        List<Integer>  typeNums = pacageClassfy(list);
        BigDecimal ans = null;
        switch (section){
            case ONE:
                ans = one(cabinType,typeNums);
                break;
            case TWO:
                ans = two(cabinType,typeNums);
                break;
            case THREE:
                ans = three(cabinType,typeNums);
                break;
            case FOUR:
                ans = four(cabinType,typeNums);
                break;
            case FIVE:
                ans = five(cabinType,typeNums);
                break;
            default:
                break;
        }
        return ans.setScale(2);
    }

    public static BigDecimal calculOverNum(CabinType cabinType, int len, FreeZone freeZone,FlightInterval section){
        int free = 0;
        BigDecimal ans = null;
            if(cabinType.equals(CabinType.ECONOMY) && freeZone.equals(FreeZone.ONE)){
            free =1;
        }else if(cabinType.equals(CabinType.ECONOMY) && freeZone.equals(FreeZone.TWO)){
            free = 2;
        }else if(cabinType.equals(CabinType.SUPERECONOMY) ||
                cabinType.equals(CabinType.BUSINESS)||cabinType.equals(CabinType.FIRST)){
            free = 2;
        }
        int[] price = new int[0];
        switch (section){
            case ONE:
                price = arr[0];
                break;
            case TWO:
                price = arr[1];
                break;
            case THREE:
                price = arr[2];
                break;
            case FOUR:
                price = arr[3];
                break;
            case FIVE:
                price = arr[4];
                break;
            default:
                break;
        }
        if(len - free <= 0){
            ans = new BigDecimal(0);
        }else if(len -free == 1){
            ans = new BigDecimal(price[0]);
        }else if(len -free == 2){
            ans = new BigDecimal(price[0] + price[1]);
        }else if(len -free >=3 ){
            ans = new BigDecimal(price[0] + price[1]+(len-free-2)*price[2]);
        }
        return ans.setScale(2);
    }
    /**
     * 将行李进行分类，分别按照合格的，超重第一阶层的，超重第二阶层，超尺寸的，超重且超尺寸的
     * @param list 行李集合
     * @return
     */
    public static List<Integer>  pacageClassfy(List<PackageModel> list){
        Iterator<PackageModel> iter = list.iterator();
        List<Integer> ans = new ArrayList<>(5);
        for(int i =0 ;i < 5;i++){
            ans.add(0);
        }
        while (iter.hasNext()){
            PackageModel model = iter.next();
            float size = model.getX()+model.getY()+model.getZ();
            float weight = model.getWeight();
            if(size < 60 || weight < 2){
                continue;
            }
            // 合格
            if(size <= 158 && weight <= 23){
                ans.set(0,ans.get(0)+1);
                // 超重第一阶
            } else if(size<=158 && weight > 23 && weight <= 28){
                ans.set(1,ans.get(1)+1);
                // 超重第二阶
            } else if(size<=158 && weight > 28 && weight <= 32){
                ans.set(2,ans.get(2)+1);
                // 超尺寸
            }  else if(size>158 && size <= 203 && weight < 23) {
                ans.set(3, ans.get(3) + 1);
                // 超重量且超尺寸
            }else if(size>158 && size <= 203 && weight > 23 && weight <=32) {
                ans.set(4, ans.get(4) + 1);
            }
        }
        return ans;
    }
    /**
     * 区域一的计费
     *
     * @param cabinType 舱型
     * @param list      各类型背包的数量
     * @return 价格
     */
    private static BigDecimal one(CabinType cabinType, List<Integer> list) {
        int[] price = {380, 980, 980, 1400};
        return calcul(cabinType,list,price);
    }
    private static BigDecimal two(CabinType cabinType, List<Integer> list) {
        int[] price = {280, 690, 690, 1100};
        return calcul(cabinType,list,price);
    }
    private static BigDecimal three(CabinType cabinType, List<Integer> list) {
        int[] price = {520, 520, 520, 520};
        return calcul(cabinType,list,price);
    }
    private static BigDecimal four(CabinType cabinType, List<Integer> list) {
        int[] price = {690, 1040, 1040, 2050};
        return calcul(cabinType,list,price);
    }
    private static BigDecimal five(CabinType cabinType, List<Integer> list) {
        int[] price = {210, 520, 520, 830};
        return calcul(cabinType,list,price);
    }
    public static BigDecimal calcul(CabinType cabinType, List<Integer> list,int[] price){
        BigDecimal ans = new BigDecimal(0);
        if (cabinType.equals(CabinType.ECONOMY) || cabinType.equals(CabinType.SUPERECONOMY)) {
            for (int i = 0; i < price.length; i++) {
                ans = ans.add(new BigDecimal(price[i]).multiply(new BigDecimal(list.get(i + 1))));
            }
        } else {
            ans = ans.add(new BigDecimal(price[2]).multiply(new BigDecimal(list.get(2 + 1))));
        }
        return ans.setScale(2);
    }
}
