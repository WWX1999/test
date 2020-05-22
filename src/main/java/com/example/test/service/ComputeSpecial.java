package com.example.test.service;

import com.example.test.model.PackageModel;
import com.example.test.model.SpecialModel;

import java.math.BigDecimal;
import java.util.List;

public class ComputeSpecial {
    public static BigDecimal compute(List<SpecialModel> specialList,List<PackageModel> list){
        BigDecimal ans = new BigDecimal(0);
        for(SpecialModel item:specialList){
            if(item.getPakageType().equals("One")){
                ans = ans.add(new BigDecimal(0));
            }else if(item.getPakageType().equals("TWO")){
                list.add(new PackageModel(-1,30,30,30,item.getWeight()));
            }else if(item.getPakageType().equals("THREE")){
                int[] stageList = {2,23,32,45};
                int[] priceList = {3600,3900,5200};
                ans = ans.add(calSpecial(stageList,priceList,item));
            }else if(item.getPakageType().equals("FOUR")){
                int[] stageList = {2,23,32,45};
                int[] priceList = {1300,2600,3900};
                ans = ans.add(calSpecial(stageList,priceList,item));
            }else if(item.getPakageType().equals("FIVE")){
                list.add(new PackageModel(-1,30,30,30,item.getWeight()));
            }else if(item.getPakageType().equals("SIX")){
                int[] stageList = {2,23,32};
                int[] priceList = {490,3900};
                ans = ans.add(calSpecial(stageList,priceList,item));
            }else if(item.getPakageType().equals("SEVEN")){
                int[] stageList = {2,23,32};
                int[] priceList = {1300,2600};
                ans = ans.add(calSpecial(stageList,priceList,item));
            }else if(item.getPakageType().equals("EIGHT")){
                int[] stageList = {2,5};
                int[] priceList = {1300};
                ans = ans.add(calSpecial(stageList,priceList,item));
            }else if(item.getPakageType().equals("NINE")){
                int[] stageList = {2,8,23,32};
                int[] priceList = {3900,5200,7800};
                ans = ans.add(calSpecial(stageList,priceList,item));
            }else{
                continue;
            }
        }
        return ans.setScale(2);
    }


    public static BigDecimal calSpecial(int[] stageList, int[] priceList,SpecialModel model){
        BigDecimal ans = new BigDecimal(0);
        int len = priceList.length;
        float weight = model.getWeight();
        System.out.println(weight);
        for(int i =0; i < len;i++){
            if(weight > stageList[i] && weight< stageList[i+1]){
                ans = ans.add(new BigDecimal(priceList[i]));
                break;
            }
        }
        return ans;
    }
}
