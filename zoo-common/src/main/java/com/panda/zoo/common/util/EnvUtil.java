package com.panda.zoo.common.util;

/**
 * @author huixiangdou
 * @date 2018/7/25
 */
public class EnvUtil {
    public static final String DEV = "dev";
    public static final String PRE = "pre";
    public static final String PUBLIC = "prod";
    public static final String DAILY = "daily";
    public static String ENV = null;

    static {
        String env = System.getenv("HOSTNAME");
        if (env == null || env.equals("")) {
            ENV = DAILY;
        } else {
            String[] ss = env.split("\\.");
            if (ss.length < 2) {
                ENV = DAILY;
            } else {
                ENV = ss[1];
            }
        }
    }

    public static boolean isPublic() {
        return ENV.equalsIgnoreCase(PUBLIC);
    }

    public static boolean isPre() {
        return ENV.equalsIgnoreCase(PRE);
    }

    public static boolean isDaily() {
        return ENV.equalsIgnoreCase(DAILY);
    }

    public static boolean isDev() {
        return ENV.equals(DEV);
    }

    public static boolean isOnLine() {
        return isPublic() || isPre();
    }

    public static boolean isOffLine() {
        return !isOnLine();
    }

    public static void main(String[] args) {
        System.out.println(isOffLine());
    }
}
