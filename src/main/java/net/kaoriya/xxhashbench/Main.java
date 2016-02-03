package net.kaoriya.xxhashbench;

public class Main {
    public static void main(String[] args) {
        System.out.println("*** 32bit hash ***");
        new HashBench().run();
        System.out.println("");
        System.out.println("*** 64bit hash ***");
        new HashBench().run64();
    }
}
