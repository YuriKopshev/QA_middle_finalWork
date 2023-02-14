package ru.iteco.fmhandroid.ui.utils;

import java.util.Random;

public class TestUtilities {

    public static String getRandomComment() {
        final int stringLength = 10;
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < stringLength; i++) {
            int number = random.nextInt(52);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    public static String getRandomClaimName() {
        Random rand = new Random();
        return "my claim" + rand.nextInt(10000);
    }

    public static String getRandomNewsItem() {
        Random random = new Random();
        return "My news #" + random.nextInt(10000);
    }
}
