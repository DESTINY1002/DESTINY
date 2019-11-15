package com.jiangconglin.algrithm.sorting;

public class SortingUtil {

    public static int[] insertSorting(int[] input){
        for (int i=1;i<input.length;i++){
            int current = input[i];

            int j = i-1;
            while (j >=0 && input[j] > current){
                input[j+1] = input[j];
                j--;
            }
            input[j+1] = current;
        }
        return input;
    }
}
