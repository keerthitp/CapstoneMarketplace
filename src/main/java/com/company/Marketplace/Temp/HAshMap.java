package com.company.Marketplace.Temp;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class HAshMap {

    public static void main(String[] args) {
        Map<String, Integer> hashMapMap = new HashMap<>();

        hashMapMap.put("Joe", 72);
        hashMapMap.put("Jane", 63);
        hashMapMap.put("Sally", 65);

    hashMapMap.remove("Joe");
    hashMapMap.put("Jane", 13);

        Iterator<Map.Entry<String,Integer>> iterator = hashMapMap.entrySet().iterator();

        while (iterator.hasNext())
        {
            Map.Entry<String,Integer> map = iterator.next();
            System.out.println(map.getKey()+ "->"+map.getValue());
        }
    }



}
