package net.kaoriya.xxhashbench;

import net.openhft.hashing.LongHashFunction;

public class ZeroAllocationHashing {

    private static LongHashFunction xx_r39 = LongHashFunction.xx_r39();

    public static long xxhash(byte[]b) {
        return xx_r39.hashBytes(b);
    }

    private static LongHashFunction murmur_3 = LongHashFunction.murmur_3();

    public static long murmur3(byte[]b) {
        return murmur_3.hashBytes(b);
    }

    private static LongHashFunction farmNa = LongHashFunction.farmNa();

    public static long farmNa(byte[]b) {
        return farmNa.hashBytes(b);
    }

    private static LongHashFunction farmUo = LongHashFunction.farmUo();

    public static long farmUo(byte[]b) {
        return farmUo.hashBytes(b);
    }

    private static LongHashFunction city11 = LongHashFunction.city_1_1();

    public static long city11(byte[]b) {
        return city11.hashBytes(b);
    }
}
