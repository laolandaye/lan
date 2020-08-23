package com.lan.javase._2数组._4二维数组;

public class ArrayReverse {

    public static void main(String[] args) {
        int arry[][] = new int[][] {{1,2,3},{4,5,6},{7,8,9}};
        reverse(arry);
        printArray(arry);
    }

    /**
     * 二维数组转置
     * @param arry
     */
    public static void reverse(int arry[][]) {
        int count = 0;//用于统计总共循环次数
        for(int i=0; i< arry.length-1; i++) {
            //列循环从：i+1开始，提高循环效率
            for(int j=i+1; j< arry[i].length; j++) {
                int temp = arry[i][j];
                arry[i][j] = arry[j][i];
                arry[j][i] = temp;
                count++;
            }
        }
        System.out.println(count);
    }

    public static void printArray(int array[][]){
        for(int i=0;i<array.length; i++) {
            for(int j = 0; j< array[i].length; j++) {
                System.out.print(array[i][j]+"、");
            }
            System.out.println();
        }
    }
}
