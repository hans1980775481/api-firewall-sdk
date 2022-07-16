package com.github.skycloudtech.apifirewallsdk;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootTest
class ApiFirewallSdkApplicationTests {

    @Test
    void contextLoads() {
    }

    public static void main(String[] args) {
//        double one = 5.26419;
//        DecimalFormat format = new DecimalFormat("#.00");
//        String str = format.format(one);
//        System.out.println(str);
//        double four = Double.parseDouble(str);
//        System.out.println(four);

        String discountPrices = discountPrices("$1 2 $3 and $4 $5 $6 7 8$ $9 $10$ $19a $19 nidhsi sds", 50);
        System.out.println(discountPrices);

//        String discountPrices = discountPrices("there are $1 $2 $3 and 5$ candies in the shop $20 $19", 50);
//        System.out.println(discountPrices);
//        discountPrices = discountPrices("$7383692 5q $5870426", 64);
//        System.out.println(discountPrices);
    }

//    public static String discountPrices2(String sentence, int discount) {
//        Pattern pattern = Pattern.compile("\\s\\$([0-9]+?)\\s");
////        Pattern pattern = Pattern.compile("\\$([0-9]+?)");
//        System.out.println(pattern.pattern());
//        Matcher matcher = pattern.matcher(sentence);
//        DecimalFormat format = new DecimalFormat("#.00");
//        int index = 0;
//        int len = sentence.length();
//        StringBuilder result = new StringBuilder();
//        while (matcher.find(index)) {
//            System.out.println("|" + matcher.group(1) + "|");
//            System.out.println("|" + matcher.start() + "|");
//            System.out.println("|" + matcher.end() + "|");
//
//            Integer price = Integer.valueOf(matcher.group(1));
//            double newPrice = price*discount*1.0/100;
//            String newPriceStr = format.format(newPrice);
//            double newPriceShort = Double.parseDouble(newPriceStr);
//            result.append(sentence.substring(index,matcher.start()+2));
//            result.append(newPriceShort);
//            index = matcher.end()-1;
//
//        }
//        String lastStr = sentence.substring(index, len);
//        Pattern pattern2 = Pattern.compile("\\s\\$([0-9]+?)");
//        Matcher matcher2 = pattern.matcher(sentence);
//        result.append(lastStr);
//
//        System.out.println(result);
//        return "";
//    }

//    public static String discountPrices3(String sentence, int discount) {
//        Pattern pattern = Pattern.compile("\\s\\$([0-9]+)\\s");
//        Matcher matcher = pattern.matcher(sentence);
//
//        int index = 0;
//        int len = sentence.length();
//        StringBuilder result = new StringBuilder();
//        while (matcher.find(index)) {
//            System.out.println(matcher.group());
//            result.append(sentence.substring(index,matcher.start()+2));
//            result.append(getNewPrice(matcher,discount));
//            index = matcher.end()-1;
//
//        }
//        String lastStr = sentence.substring(index, len);
//        Pattern pattern2 = Pattern.compile("\\s\\$([0-9]+)$");
//        Matcher matcher2 = pattern2.matcher(sentence);
//        System.out.println("lastStr = |" + lastStr + "|");
//        if (!matcher2.find()) {
//            result.append(lastStr);
//        } else {
//            System.out.println("lastStr = " + lastStr + " index = " + index);
//            System.out.println("matcher2.start() = " + matcher2.start());
//            result.append(sentence.substring(index, matcher2.start() + 2));
//            System.out.println(matcher2.group());
//            result.append(getNewPrice(matcher2, discount));
//        }
//
//
//        return result.toString();
//    }

//    public static String discountPrices(String sentence, int discount) {
//        Pattern pattern = Pattern.compile("(\\s|^)\\$([0-9]+)(\\s|$)");
//        Matcher matcher = pattern.matcher(sentence);
//
//        int index = 0;
//        int len = sentence.length();
//        StringBuilder result = new StringBuilder();
//        while (matcher.find(index)) {
//            System.out.println(matcher.group());
//            result.append(sentence.substring(index,matcher.start()+2));
//            result.append(getNewPrice(matcher,discount));
//            index = matcher.end()-1;
//
//        }
//        String lastStr = sentence.substring(index, len);
//        Pattern pattern2 = Pattern.compile("\\s\\$([0-9]+)$");
//        Matcher matcher2 = pattern2.matcher(sentence);
//        if (!matcher2.find()) {
//            result.append(lastStr);
//        } else {
//            result.append(sentence.substring(index, matcher2.start() + 2));
//            result.append(getNewPrice(matcher2, discount));
//        }
//        return result.toString();
//    }

    private static DecimalFormat format = new DecimalFormat("#.00");

    public static String discountPrices(String sentence, int discount) {

        if (sentence.length() < 2) {
            return sentence;
        }

        Pattern pattern = Pattern.compile("(\\s|^)\\$([0-9]+)(\\s|$)");
        Matcher matcher = pattern.matcher(sentence);

        int index = 0;
        StringBuilder result = new StringBuilder();
        while (matcher.find(index)) {
            System.out.println(matcher.group());
            int start = matcher.start();
            if (start == 0) {
                result.append(sentence.substring(index, start + 1));
            } else {
                result.append(sentence.substring(index, start + 2));
            }

            result.append(getNewPrice(matcher, discount));
            index = matcher.end() - 1;

        }
        int len = sentence.length();
        if (index != len - 1) {
            String lastStr = sentence.substring(index, len);
            result.append(lastStr);
        }

        return result.toString();
    }


    public static String getNewPrice(Matcher matcher, int discount) {
        String matchPrice = matcher.group(2);
        if (matchPrice.length() > 10) {
            return matchPrice;
        }
        Long price = Long.valueOf(matchPrice);
        double newPrice = price * (100 - discount) * 1.0 / 100;
        String newPriceStr = format.format(newPrice);
        System.out.println(newPriceStr);
        if (newPriceStr.startsWith(".")) {
            newPriceStr = "0" + newPriceStr;
        }
        return newPriceStr;
    }
}
