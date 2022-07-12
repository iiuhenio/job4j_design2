package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "Petr Arsentev";
        int age = 33;
        Boolean male = true;
        char female = 'n';
        long life = 99999999;
        double temp = 36.6;
        byte adulthood = 18;
        short birthday = 6;
        float start = 0;
        LOG.debug("User info name : {}, age : {}, male : {}, female : {}, life : {}, "
                 + "temp : {}, adulthood : {}, birthday : {}, start : {}", name, age,
                 male, female, life, temp, adulthood, birthday, start);
    }
}