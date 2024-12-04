package util;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ColorLogger {

    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";

    private static void colorize(String msg, String color, Object... args) {
        for (int i=0; i<args.length; i++) {
            msg = msg.replaceFirst("\\{}", "{" + i + "}");
        }
        String formattedMsg = String.format(msg, args);
        log.debug("{}{}{}", color, formattedMsg, RESET);
    }

    public static void red(String msg, Object... args) {
        colorize(msg, RED, args);
    }

    public static void green(String msg, Object... args) {
        colorize(msg, GREEN, args);
    }

    public static void yellow(String msg, Object... args) {
        colorize(msg, YELLOW, args);
    }

    public static void blue(String msg, Object... args) {
        colorize(msg, BLUE, args);
    }

    public static void purple(String msg, Object... args) {
        colorize(msg, PURPLE, args);
    }

    public static void cyan(String msg, Object... args) {
        colorize(msg, CYAN, args);
    }

}
