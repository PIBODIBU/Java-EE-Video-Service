package utils;

import java.util.LinkedList;
import java.util.Random;

public class MDColors {

    private static LinkedList<String> colors = new LinkedList<String>();

    private static void init() {
        colors.add("#F44336");
        colors.add("#E91E63");
        colors.add("#9C27B0");
        colors.add("#673AB7");
        colors.add("#673AB7");
        colors.add("#2196F3");
        colors.add("#009688");
        colors.add("#FF5722");
        colors.add("#795548");
        colors.add("#607D8B");
    }

    public static String getRandomColor() {
        init();
        Random random = new Random();

        return colors.get(random.nextInt(colors.size()));
    }
}
