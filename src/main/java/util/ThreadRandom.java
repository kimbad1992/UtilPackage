package util;

import java.security.SecureRandom;

public class ThreadRandom {

    private static final ThreadLocal<SecureRandom> threadLocalRandom =
            ThreadLocal.withInitial(SecureRandom::new);

    public static int generateRandomInt(int bound) {
        return threadLocalRandom.get().nextInt(bound);
    }

    public static void clear() {
        threadLocalRandom.remove();
    }
}
