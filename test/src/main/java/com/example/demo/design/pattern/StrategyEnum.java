package com.example.demo.design.pattern;

/**
 * 策略枚举
 */
public class StrategyEnum {
    public static void main(String[] args) {
        String day = "Tuesday";
        String toDo = getToDo(day);
        System.out.println(toDo);
        System.out.println(getToDoStrategy(day));
    }

    private static String getToDo(String day) {
        if ("Monday".equals(day)) {
            return "今天上英语课";
        } else if ("Tuesday".equals(day)) {
            return "今天上语文课";
        } else if ("Wednesday".equals(day)) {
            return "今天上数学课";
        } else if ("Thursday".equals(day)) {
            return "今天上音乐课";
        } else if ("sunday".equals(day)) {
            return "今天上编程课";
        } else {
            // 此处省略10086行......
            return "";
        }
    }

    private static String getToDoStrategy(String day) {
        DayEnum dayEnum = DayEnum.valueOf(day);
//        return dayEnum.toDo();
        CheckDay checkDay = new CheckDay();
        return checkDay.day(dayEnum);
    }


}
