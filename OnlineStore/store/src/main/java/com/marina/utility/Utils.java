package com.marina.utility;

import java.io.InputStream;
import java.util.Scanner;

public class Utils {

    public static int generateRandomValue(int min, int max){
        return (int)(min + Math.random() * max);
    }

    public static int getIntFromConsole(InputStream inputStream){
        Scanner in = new Scanner(inputStream);
        System.out.println("Input product id: ");
        return in.nextInt();
    }
}
