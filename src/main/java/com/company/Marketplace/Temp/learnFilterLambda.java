package com.company.Marketplace.Temp;

import java.util.ArrayList;
import java.util.List;

public class learnFilterLambda {

    public static void main(String[] args) {

        List<Integer> stringList = new ArrayList<>();

        stringList.add(1);
        stringList.add(2);
        stringList.add(3);

        if(stringList.stream().filter(s -> s == 5)== null)
            throw new IllegalArgumentException ("D is not there in the list");
        System.out.println("D is there in the list");

    }
}
