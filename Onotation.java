package ru.itis.semestr_asd1_205.gaifullin;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Onotation {
    private static int count = 0;
        public static void main(String[] args) {
            String string, pattern;
            int result;
            final int startIndex = 0;
            Random random = new Random();
            try (FileWriter writer = new FileWriter("KMP-4.csv")) {

                for (int i = 100; i < 1000000; i += 1000) {

                    string = givenUsingPlainJava(i);
                    pattern = givenUsingPlainJava(6);



                    long t = System.nanoTime();
                    count = 0;
                    result = KMP(string, pattern, startIndex);

                    writer.write(i + ";" + count + ";" + (System.nanoTime() - t) + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    public static int[] prefixFunction(String string) {
        int n = string.length();
        int k;
        //создадим массив, он будет заполнен нулями.
        int[] p = new int[n];
        p[0] = 0;
        for (int i = 1; i < n; i++) {
            ++count;
            k = p[i-1];
            while (k > 0 && string.charAt(i)!=string.charAt(k)){
                ++count;
                k = p[k-1];
            }
            if (string.charAt(i) == string.charAt(k)){
                ++count;
                k++;
            }
            p[i] = k;
        }
        ++count;
        return p;
    }
        public static int KMP(String string, String pattern, int startIndex){
            // Вычисляем перфикс функцию для паттерна
            int[] pi = prefixFunction(pattern);
            int index = -1;
            int j = 0; // j - индекс паттерна
            for(int i = startIndex; i < string.length(); ++i){
                ++count;
                // Если элементы не равны и j > 0, то откатываем в начало или до тех пор, пока символы не будут совпадать.
                for(;j>0 && string.charAt(i) != pattern.charAt(j);){
                    ++count;
                    j = pi[j-1];
                }
                //Если символы совпадают, то двигаем оба курсора
                if (string.charAt(i) == pattern.charAt(j)){
                    ++count;
                    ++j;
                }
                //Если j равен последнему элементу подстроки (поиск успешен)
                if(j >= pattern.length()){
                    ++count;
                    index = i - j + 1;
                    return index;
                }
            }
            ++count;
            return index;
        }
        public static String givenUsingPlainJava(int len) {

            int leftLimit = 97; // letter 'a'
            int rightLimit = 100; // letter 'd'
            int targetStringLength = len;
            Random random = new Random();
            StringBuilder buffer = new StringBuilder(targetStringLength);
            for (int i = 0; i < targetStringLength; i++) {
                ++count;
                int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
                buffer.append((char) randomLimitedInt);
            }
            String generatedString = buffer.toString();

            return generatedString;
        }
    }