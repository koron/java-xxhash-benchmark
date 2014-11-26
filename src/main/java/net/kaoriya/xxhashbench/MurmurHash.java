package net.kaoriya.xxhashbench;

import util.hash.MurmurHash3;

public class MurmurHash {

    public static int hash3(byte[] b) {
        return MurmurHash3.murmurhash3_x86_32(b, 0, b.length, 123456789);
    }

}
