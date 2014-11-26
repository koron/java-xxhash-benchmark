package net.kaoriya.xxhashbench;

import java.util.Random;

import com.cfelde.bohmap.Binary;

public class Utils
{
    public static Binary randomBinary(Random r, int len) {
        byte[] b = new byte[len];
        r.nextBytes(b);
        return new Binary(b);
    }
}
