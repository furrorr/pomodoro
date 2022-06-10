package com.furrorr;

import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Pomodoro {

    static boolean isTest = true;
    static boolean isCallHelp = false;
    public static int test = 0;

    public static void main(String[] args) throws InterruptedException {

        System.out.println("-------------Hello pomodoro!-------------");

        int workMinutes = 25;
        int breakMinutes = 5;
        int count = 1;
        int sizePrint = 60;

        while(isTest) {
            System.out.print("Команда: ");
            String[] cmd = new Scanner(System.in).nextLine().split(" ");
            for (int i = 0; i < cmd.length; i++) {
                switch (cmd[i]) {
                    case "-help" -> printHelpMessage();
                    case "-w" -> {
                        if (i == cmd.length - 1) {  }
                        else if (cmd[++i].equals("-b") || cmd[i].equals("-c")) {
                            --i;
                        } else workMinutes = Integer.parseInt(cmd[i]);
                        isTest = false;
                    }
                    case "-b" -> {
                        if (i == cmd.length - 1) {  }
                        else if (cmd[++i].equals("-c")) --i;
                        else breakMinutes = Integer.parseInt(cmd[i]);
                        isTest = false;
                    }
                    case "-c" -> {
                        if (i != cmd.length - 1) count = Integer.parseInt(cmd[++i]);
                        isTest = false;
                    }
                    case "" -> isTest = false;
                    default -> {
                        System.out.println("Введите команду из доступных. Для справки введите '-help'.");
                        isTest = true;
                    }
                }
            }
        }
        if (!isCallHelp) {
            if (workMinutes > 0 && breakMinutes > 0 && count > 0){
                System.out.printf("Работаем %d min, отдыхаем %d min," +
                        " повторям круг %d раз\n", workMinutes, breakMinutes, count);
                long startTime = System.currentTimeMillis();
                for (int i = 1; i <= count; i++) {
                    timer(workMinutes, breakMinutes, sizePrint);
                }
                long endTime = System.currentTimeMillis();
                System.out.println("Pomodoro таймер истек: " + (endTime - startTime) / (1000 * 60));
            } else {
                numberLessZero(workMinutes, breakMinutes);
            }
        }
        System.out.println("-------------Bye pomodoro!-------------");
    }




    private static void numberLessZero(int workMinutes, int breakMinutes) {
        if (workMinutes < 0)
            System.out.println("Значение '-w' отрицательное");
        else if (breakMinutes < 0)
            System.out.println("Значение '-b' отрицательное");
        else System.out.println("Значение '-c' отрицательное");
    }

    private static void timer(int workTime, int breakTime, int sizeProgressBar) throws InterruptedException {
        printProgress("Время вкалывать:: ", workTime, sizeProgressBar);
        printProgress("Время отдыхать:: ", breakTime, sizeProgressBar);
    }

    private static void printProgress(String process, int time, int size) throws InterruptedException {
        int length;
        int rep;
        length = 60 * time / size;
        rep = 60 * time /length;
        double stretchb = size /(3.0 * time);
        for(int i = 1; i <= rep; i++){
            double x = i;
            x = 1.0 / 3.0 * x;
            x *= 10;
            x = Math.round(x);
            x /= 10;
            double w = time * stretchb;
            double percent = (x / w) * 1000;
            x /= stretchb;
            x *= 10;
            x = Math.round(x);
            x /= 10;
            percent = Math.round(percent);
            percent /= 10;
            System.out.print(process + percent+"% " + (" ").repeat(5 - (String.valueOf(percent).length())) +"[" + ("#").repeat(i) + ("-").repeat(rep - i)+"]    ( " + x +"min / " + time +"min )"+  "\r");
            if(test == 0){
                TimeUnit.SECONDS.sleep(length);
            }
        }
        System.out.println();
    }

    private static void printHelpMessage() {
        System.out.println("/*------------------------------------------------------------------------------------------------------------------------------*/");

        String text = "\tПривет! Программа ставит таймер на [-w] минут для работы, [-b] минут для отдыха и на [-c] повторов.\n"+
                "\t\tДефолтные значения: [-w] _ 25 минут\n"+
                "\t\t\t\t\t\t\t[-b] _ 5 минут\n"+
                "\t\t\t\t\t\t\t[-c] _ 1 повтор\n"+
                "\tПримеры:\n"+
                "\t\t~ Если вы хотите изменить все дефолтные значения, то введите:\n"+
                "\t\t\t-w [количество_минут] -b [количество_минут] -с [количество_повторов] ___ -w 10 -b 5 -c 3 ___ -w 15 -b 10 -c 5 ___\n"+
                "\t\t~ Если вы хотите изменить количество минут отдыха, но оставить без изменения количество минут работы и количество повторов, то введите:\n"+
                "\t\t\t-b [количество_минут] ___ -b 10 ___ -b 7 ___ -b 20 ___\n"+
                "\t\t~ Если вы хотите изменить количество минут работы и колчество повторов, но оставить без изменения количество минут отдыха, то введите:\n"+
                "\t\t\t-w [количество_минут] -с [количество_повторов] ___ -w 10 -c 2 ___ -w 15 -c 3 ___ -w 5 -c 5 ___\n"+
                "\t\t~ Для того, чтобы запустить программу с дефолтными значениями, просто нажмите Enter.\n";
        System.out.println(text);

        System.out.println("/*------------------------------------------------------------------------------------------------------------------------------*/");        isCallHelp = true;
    }
}

/*

        Привет! Программа ставит таймер на [-w] минут для работы, [-b] минут для отдыха и на [-c] повторов.
            Дефолтные значения: [-w] _ 25 минут
                                [-b] _ 5 минут
                                [-c] _ 1 повтор
        Примеры:
            ~ Если вы хотите изменить все дефолтные значения, то введите:
                -w [количество_минут] -b [количество_минут] -с [количество_повторов] ___ -w 10 -b 5 -c 3 ___ -w 15 -b 10 -c 5 ___
            ~ Если вы хотите изменить количество минут отдыха, но оставить без изменения количество минут работы и количество повторов, то введите:
                -b [количество_минут] ___ -b 10 ___ -b 7 ___ -b 20 ___
            ~ Если вы хотите изменить количество минут работы и колчество повторов, но оставить без изменения количество минут отдыха, то введите:
                -w [количество_минут] -с [количество_повторов] ___ -w 10 -c 2 ___ -w 15 -c 3 ___ -w 5 -c 5 ___
            ~ Для того, чтобы запустить программу с дефолтными значениями, просто нажмите Enter
 */
