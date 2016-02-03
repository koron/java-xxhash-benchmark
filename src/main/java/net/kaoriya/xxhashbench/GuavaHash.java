package net.kaoriya.xxhashbench;

import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

public class GuavaHash {

    private static HashFunction murmur3_128 = Hashing.murmur3_128();

    public static long murmur3(byte[] b) {
        return murmur3_128.hashBytes(b).asLong();
    }

    private static HashFunction sipHash24 = Hashing.sipHash24();

    public static long siphash(byte[] b) {
        return sipHash24.hashBytes(b).asLong();
    }
}
