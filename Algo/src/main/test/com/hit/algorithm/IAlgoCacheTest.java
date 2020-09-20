package com.hit.algorithm;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class IAlgoCacheTest
{
    @Test
    public void RandomTest() {
        final Integer capacity = 3;
        final RandomAlgoCacheImpl<Integer, String> test = new RandomAlgoCacheImpl<Integer, String>(capacity);
        System.out.println("\n ############################ \n         Random Test \n ############################ \n");
        String returnValue = test.putElement(5, "value: 5");
        System.out.println("elemntToReturn: " + returnValue + "\n ---------------- \n");
        returnValue = test.putElement(8, "value: 8");
        System.out.println("elemntToReturn: " + returnValue + "\n ---------------- \n");
        returnValue = test.putElement(10, "value: 10");
        System.out.println("elemntToReturn: " + returnValue + "\n ---------------- \n");
        returnValue = test.putElement(5, "value: 5");
        System.out.println("elemntToReturn: " + returnValue + "\n ---------------- \n");
        returnValue = test.putElement(0, "value: 0");
        System.out.println("elemntToReturn: " + returnValue + "\n ---------------- \n");
        returnValue = test.putElement(3, "value: 3");
        System.out.println("elemntToReturn: " + returnValue + "\n ---------------- \n");
        returnValue = test.putElement(0, "value: 0");
        System.out.println("elemntToReturn: " + returnValue + "\n ---------------- \n");
        returnValue = test.putElement(4, "value: 4");
        System.out.println("elemntToReturn: " + returnValue + "\n ---------------- \n");
        returnValue = test.putElement(2, "value: 2");
        System.out.println("elemntToReturn: " + returnValue + "\n ---------------- \n");
        returnValue = test.putElement(3, "value: 3");
        System.out.println("elemntToReturn: " + returnValue + "\n ---------------- \n");
        returnValue = test.putElement(5, "value: 5");
        System.out.println("elemntToReturn: " + returnValue + "\n ---------------- \n");
        returnValue = test.putElement(4, "value: 4");
        System.out.println("elemntToReturn: " + returnValue + "\n ---------------- \n");
        returnValue = test.putElement(3, "value: 3");
        System.out.println("elemntToReturn: " + returnValue + "\n ---------------- \n");
    }
    
    public void LRUAlgoCacheTest() {
        final Integer capacity = 3;
        final LRUAlgoCacheImpl<Integer, String> test = new LRUAlgoCacheImpl<Integer, String>(capacity);
        System.out.println("\n ############################ \n      LRU Algo Cache Test \n ############################ \n");
        String returnValue = test.putElement(2, "value: 2");
        System.out.println("elemntToReturn: " + returnValue + "\n ---------------- \n");
        Assertions.assertEquals((Object)null, (Object)returnValue);
        returnValue = test.putElement(3, "value: 3");
        System.out.println("elemntToReturn: " + returnValue + "\n ---------------- \n");
        Assertions.assertEquals((Object)null, (Object)returnValue);
        returnValue = test.putElement(4, "value: 4");
        System.out.println("elemntToReturn: " + returnValue + "\n ---------------- \n");
        Assertions.assertEquals((Object)null, (Object)returnValue);
        returnValue = test.putElement(2, "value: 2");
        System.out.println("elemntToReturn: " + returnValue + "\n ---------------- \n");
        Assertions.assertEquals((Object)"value: 2", (Object)returnValue);
        returnValue = test.putElement(1, "value: 1");
        System.out.println("elemntToReturn: " + returnValue + "\n ---------------- \n");
        Assertions.assertEquals((Object)"value: 3", (Object)returnValue);
        returnValue = test.putElement(3, "value: 3");
        System.out.println("elemntToReturn: " + returnValue + "\n ---------------- \n");
        Assertions.assertEquals((Object)"value: 4", (Object)returnValue);
        returnValue = test.putElement(7, "value: 7");
        System.out.println("elemntToReturn: " + returnValue + "\n ---------------- \n");
        Assertions.assertEquals((Object)"value: 2", (Object)returnValue);
        returnValue = test.putElement(5, "value: 5");
        System.out.println("elemntToReturn: " + returnValue + "\n ---------------- \n");
        Assertions.assertEquals((Object)"value: 1", (Object)returnValue);
        returnValue = test.putElement(4, "value: 4");
        System.out.println("elemntToReturn: " + returnValue + "\n ---------------- \n");
        Assertions.assertEquals((Object)"value: 3", (Object)returnValue);
        returnValue = test.putElement(3, "value: 3");
        System.out.println("elemntToReturn: " + returnValue + "\n ---------------- \n");
        Assertions.assertEquals((Object)"value: 7", (Object)returnValue);
    }
}
