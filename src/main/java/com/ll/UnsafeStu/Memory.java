package com.ll.UnsafeStu;

import sun.misc.Unsafe;

/**
 * @author Lei
 * @date 2023/3/29 11:31
 */
public class Memory {
    class DirectMemoryBuffer {
        private final static int BYTE = 1;
        private long size;
        private long address;
        private Unsafe unsafe;

        public DirectMemoryBuffer(long size, Unsafe unsafe) {
            this.size = size;
            this.unsafe = unsafe;
            address = unsafe.allocateMemory(size * BYTE);
        }

        public void set(long i, byte value) {
            unsafe.putByte(address + i * BYTE, value);
        }

        public int get(long idx) {
            return unsafe.getByte(address + idx * BYTE);
        }

        public long size() {
            return size;
        }

        public void freeMemory() {
            unsafe.freeMemory(address);
        }
    }

    public static void main(String[] args) {
        Unsafe unsafe = BaseGet.getUnsafe();

    }
}
