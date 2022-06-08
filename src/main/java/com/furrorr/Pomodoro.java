package com.furrorr;

import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Pomodoro {

    static boolean isTest = false;

    public static void main(String[] args) throws InterruptedException {

        /*
        -w 1 -b 1 -count 1
        [-w, 1, -b, 1, -count, 1]

                -добавить проверки пользовательнского ввода
         */

        System.out.println("\tHello pomidoro! \nНапиши пожалуйста команду в виде " +
                "\"-w [число] -b [число] -count [число]\", " +
                "\nгде \'-w\' это количество минут работы, \'-b\' это количество  минут отдыха, " +
                "а \'-count\' это количство повторений круга.");

        // считываем пользовательский ввод
        String[] cmd = new Scanner(System.in).nextLine().split(" ");

        // выводим, что получилось
        System.out.println("Ваш запрос: " + Arrays.toString(cmd));

        // время работы
        int workMinutes = 25;
        // время отдыхка
        int breakMinutes = 5;
        // количество подходов
        int count = 1;

        // длина рисунка progress bar
        int sizePrint = 30;

        boolean isCallHelp = false;

        for (int i = 0; i < cmd.length; i++) {
            switch (cmd[i]) {
                case "-help" -> {
                    printHelpMessage();
                    isCallHelp = true;
                }
                case "-w" -> workMinutes = Integer.parseInt(cmd[++i]);
                case "-b" -> breakMinutes = Integer.parseInt(cmd[++i]);
                case "-count" -> count = Integer.parseInt(cmd[++i]);
                case "-t" -> isTest = true;
            }
        }
        if (!isCallHelp) {
            System.out.printf("Работаем %d min, отдыхаем %d min," +
                    " повторям круг %d раз", workMinutes, breakMinutes, count);
            long startTime = System.currentTimeMillis();
            for (int i = 1; i <= count; i++) {
                timer(workMinutes, breakMinutes, sizePrint);
            }
            long endTime = System.currentTimeMillis();
            System.out.println("Pomodoro таймер истек: " + (endTime - startTime) / (1000 * 60));
        }
    }

    private static void timer(int workTime, int breakTime, int sizeProgressBar) throws InterruptedException {
        printProgress("Время вкалывать:: ", workTime, sizeProgressBar);
        printProgress("Время отдыхать:: ", breakTime, sizeProgressBar);
    }

    private static void printProgress(String process, int time, int size) throws InterruptedException {
        int length;
        int rep;
        length = 60* time / size;
        rep = 60* time /length;
        int stretchb = size /(3* time);
        for(int i=1; i <= rep; i++){
            double x = i;
            x = 1.0/3.0 *x;
            x *= 10;
            x = Math.round(x);
            x /= 10;
            double w = time *stretchb;
            double percent = (x/w) *1000;
            x /=stretchb;
            x *= 10;
            x = Math.round(x);
            x /= 10;
            percent = Math.round(percent);
            percent /= 10;
            System.out.print(process + percent+"% " + (" ").repeat(5 - (String.valueOf(percent).length())) +"[" + ("#").repeat(i) + ("-").repeat(rep - i)+"]    ( " + x +"min / " + time +"min )"+  "\r");
            if(!isTest){
                TimeUnit.SECONDS.sleep(length);
            }
        }
        System.out.println();
    }

    private static void printHelpMessage() {
        System.out.println();
        System.out.println("\tPomodoro - сделай своё время более эффективным!");
        System.out.println();
        System.out.println("\t-w [time]: время работы, сколько хочешь работать.");
        System.out.println("\t-b [time]: время отдыха, сколько хочешь отдыхать.");
        System.out.println("\t-count [count]: - количество итераций.");
        System.out.println();
    }
}
