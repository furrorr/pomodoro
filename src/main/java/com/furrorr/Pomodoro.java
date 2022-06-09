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
                        if (cmd[++i].equals("-b")) {
                            --i;
                        } else workMinutes = Integer.parseInt(cmd[i]);
                    }
                    case "-b" -> {
                        if (cmd[++i].equals("-c")) {
                            --i;
                        } else breakMinutes = Integer.parseInt(cmd[i]);
                    }
                    case "-c" -> {
                        if (i != cmd.length - 1) {
                            count = Integer.parseInt(cmd[++i]);
                        }
                        isTest = false;
                    }
                    default -> {
                        System.out.println("Введите команду из доступных. Для справки введите '-help'.");
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
        System.out.println();
        System.out.println("\tПривет! Пиши команду в виде -w [время] -b [время] -c [время]");
        System.out.println("\tПример: [ -w 20 -b 5 -count 1 ] - эта команда поставит таймер на 20 минуты работы, 5 минут отдыха и все это на один круг!");
        System.out.println("\t-w [time]: время работы, сколько хочешь работать.");
        System.out.println("\t-b [time]: время отдыха, сколько хочешь отдыхать.");
        System.out.println("\t-c [count]: - количество итераций.");
        System.out.println();
        isCallHelp = true;
    }
}
