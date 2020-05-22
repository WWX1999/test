package com.example.test.service;

import com.example.test.enums.*;
import com.example.test.model.PackageModel;
import com.example.test.model.SpecialModel;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.List;

/**
 * 提供计算行李服务
 */
public class ComputeService {
    private ComputeService() {
    }

    public static BigDecimal computePrice(Vip vip, TicketType ticketType, CabinType cabinType, BigDecimal cost, FreeZone freeZone, FlightInterval section, List<PackageModel> list,List<SpecialModel> specialList) {
        BigDecimal ans = null;
        switch (section) {
            case ZERO:
                ans = calculateCost(vip, cabinType, cost, ticketType, list);
                break;
            case ONE:
            case TWO:
            case THREE:
            case FOUR:
            case FIVE:
                ans = calculateInternational(cabinType, list,freeZone,section,specialList);
                break;
            default:
                break;
        }
        return ans;
    }


    /**
     * 计算国内区域的价格
     *
     * @param vip
     * @param cabinType
     * @param cost
     * @param ticketType
     * @param list
     * @return
     */
    public static BigDecimal calculateCost(Vip vip, CabinType cabinType, BigDecimal cost, TicketType ticketType, List<PackageModel> list) {
        int maxWeight = calBigWeight(vip, cabinType, ticketType);
        int sumWeight = 0;
        for (PackageModel item : list) {
            if (item.getX() > 100 || item.getY() > 60 || item.getZ() > 40) {
                continue;
            }
            sumWeight += item.getWeight();
        }
        int lastweight = 0;
        if(sumWeight > maxWeight){
            lastweight = sumWeight-maxWeight;
        }
        BigDecimal over = new BigDecimal(lastweight);
        BigDecimal ans = over.multiply(new BigDecimal("0.015"));
        ans=   ans.multiply(cost);
        ans = ans.setScale(2);
        return ans;
    }

    /**
     * 计算国际地区的价格
     * @param cabinType
     * @param list
     * @return
     */
    private static BigDecimal calculateInternational(CabinType cabinType,List<PackageModel> list, FreeZone freeZone,FlightInterval section,List<SpecialModel> specialList) {
        BigDecimal specialPrice = ComputeSpecial.compute(specialList,list);
        BigDecimal weightPrice = InternationalOver.calculOverWeight(cabinType,list,section);
        BigDecimal numsPrice = InternationalOver.calculOverNum(cabinType,list.size()+specialList.size(),freeZone,section);
        return weightPrice.add(numsPrice).add(specialPrice);
    }

    public static int calBigWeight(Vip vip, CabinType cabinType, @NotNull TicketType ticketType) {
        int maxWeight = 0;
        switch (ticketType) {
            case BABY:
                maxWeight = 10;
                break;
            case CHILD:
            case ADULT:
                if (cabinType.equals(CabinType.FIRST)) {
                    maxWeight = 40;
                } else if (cabinType.equals(CabinType.BUSINESS)) {
                    maxWeight = 30;
                } else {
                    maxWeight = 20;
                }
                break;
        }
        switch (vip) {
            case SSVIP:
                maxWeight += 30;
                break;
            case SVIP:
            case VIP:
                maxWeight += 20;
                break;
            default:
                break;
        }
        return maxWeight;
    }
}
