package com.huning.acm;

import java.util.Scanner;

/**
 * ACM模板(多数之和)
 * @author huning
 */

public class MainTemplate {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

                    //读取输入元素个数
                    int n = scanner.nextInt();

                    int[] arr = new int[n];
                    for (int i = 0; i < n; i++) {
                        arr[i] = scanner.nextInt();
                    }

                    int sum=0;
                    for (int num : arr) {
                        sum+=num;
                    }
                    System.out.println("求和结果是: "+sum);
        scanner.close();
    }
}