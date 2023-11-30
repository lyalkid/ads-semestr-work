package ru.itis.semestr_asd1_205.gaifullin;

import java.util.Arrays;
import java.util.Scanner;

public class MyAlgorithm {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String string = scanner.nextLine();
        String pattern = scanner.nextLine();
        System.out.print((KMP(string,pattern,0)));
    }


    public static int KMP(String string, String pattern, int startIndex){
        // Вычисляем перфикс функцию для паттерна
        int[] pi = prefixFunction(pattern);
        int index = -1;
        int j = 0; // j - индекс паттерна

        // базовый случай 1: шаблон нулевой или пустой
        if (pattern == null || pattern.length() == 0)
        {
            System.out.println("The pattern occurs with shift 0");
            return index;
        }
        // базовый случай 2: текст равен NULL или длина текста меньше длины шаблона
        if (string == null || pattern.length() > string.length())
        {
            System.out.println("Pattern not found");
            return index;
        }
        for(int i = startIndex; i < string.length(); ++i){
            // Если элементы не равны и j > 0, то откатываем в начало или до тех пор, пока символы не будут совпадать.
            while (j>0 && string.charAt(i) != pattern.charAt(j)){
                j = pi[j-1];
            }
            //Если символы совпадают, то двигаем оба курсора
            if (string.charAt(i) == pattern.charAt(j)){
                ++j;
            }
            //Если j равен последнему элементу подстроки (поиск успешен)
            if(j >= pattern.length()){
                index = i - j + 1;
                return index;
            }
        }
        return index;
    }

    public static int[] prefixFunction(String string) {
        int n = string.length();
        int k;
        //создадим массив, он будет заполнен нулями.
        int[] p = new int[n];
        p[0] = 0;
        for (int i = 1; i < string.length(); i++) {
            k = p[i-1];
            while (k > 0 && string.charAt(i)!=string.charAt(k)){
                k = p[k-1];
            }
            if (string.charAt(i) == string.charAt(k)){
                k++;
            }
            p[i] = k;
        }
        return p;
    }
}
