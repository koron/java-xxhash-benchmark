package net.kaoriya.xxhashbench;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.function.Function;

import com.cfelde.bohmap.Binary;
import net.kaoriya.xxhash.XXHash;
import net.openhft.hashing.LongHashFunction;
import org.jruby.util.SipHashInline;

public class HashBench {

    public static int KEY_LEN = 128;
    public static int ITERATION = 1000000;

    public void run(
            String label,
            Function<byte[], Integer> hashFunc,
            int iteration,
            int keyLen,
            Random r)
    {
        ArrayList<Binary> list = new ArrayList<>();
        for (int i = 0; i < ITERATION; ++i) {
            list.add(Utils.randomBinary(r, keyLen));
        }
        System.gc();
        System.gc();
        System.gc();
        int countMinus = 0;
        long startAt = System.nanoTime();
        for (int i = 0; i < iteration; ++i) {
            int v = hashFunc.apply(list.get(i).getValue());
            if (v < 0) {
                ++countMinus;
            }
        }
        long elapsed = System.nanoTime() - startAt;
        System.out.println(String.format("%-15s : %d/%d (%.2f%%) %,3d", label,
                    countMinus, iteration,
                    (double)countMinus * 100 / iteration,
                    elapsed));
    }

    public void run() {
        System.out.println(
                "Format: {LABEL} : {MINUS HASH COUNT}/{ITERATION COUNT} {ELAPSED NANOTIME}");
        run("xxHash", HashBench::xxhash32, ITERATION, KEY_LEN, new Random());
        run("MurmurHash3", MurmurHash::hash3, ITERATION, KEY_LEN, new Random());
        run("SipHash", HashBench::siphash, ITERATION, KEY_LEN, new Random());
        run("Arrays#hashCode", Arrays::hashCode, ITERATION, KEY_LEN, new Random());
    }

    public static int xxhash32(byte[] b) {
        return XXHash.hash32(b, 0, b.length, 0);
    }

    public static int siphash(byte[] b) {
        long h = SipHashInline.hash24(0, 0, b);
        return (int)h;
    }

    public void run64(
            String label,
            Function<byte[], Long> hashFunc,
            int iteration,
            int keyLen,
            Random r)
    {
        ArrayList<Binary> list = new ArrayList<>();
        for (int i = 0; i < ITERATION; ++i) {
            list.add(Utils.randomBinary(r, keyLen));
        }
        System.gc();
        System.gc();
        System.gc();
        int countMinus = 0;
        long startAt = System.nanoTime();
        for (int i = 0; i < iteration; ++i) {
            long v = hashFunc.apply(list.get(i).getValue());
            if (v < 0) {
                ++countMinus;
            }
        }
        long elapsed = System.nanoTime() - startAt;
        System.out.println(String.format("%-15s : %d/%d (%.2f%%) %,3d", label,
                    countMinus, iteration,
                    (double)countMinus * 100 / iteration,
                    elapsed));
    }

    public void run64() {
        System.out.println(
                "Format: {LABEL} : {MINUS HASH COUNT}/{ITERATION COUNT} {ELAPSED NANOTIME}");
        run64("xxHash", HashBench::xxhash64, ITERATION, KEY_LEN, new Random());
        run64("ZAH-city11", ZeroAllocationHashing::city11,
                ITERATION, KEY_LEN, new Random());
        run64("ZAH-farmNa", ZeroAllocationHashing::farmNa,
                ITERATION, KEY_LEN, new Random());
        run64("ZAH-murmur3", ZeroAllocationHashing::murmur3,
                ITERATION, KEY_LEN, new Random());
        run64("ZAH-xxHash", ZeroAllocationHashing::xxhash,
                ITERATION, KEY_LEN, new Random());
        run64("Guava-murmur3", GuavaHash::murmur3,
                ITERATION, KEY_LEN, new Random());
        run64("Guava-siphash", GuavaHash::siphash,
                ITERATION, KEY_LEN, new Random());
    }

    public static long xxhash64(byte[] b) {
        return XXHash.hash64(b, 0, b.length, 0);
    }
}
