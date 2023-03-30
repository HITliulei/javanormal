package com.ll.UnsafeStu;

import sun.misc.Unsafe;

/**
 * @author Lei
 * @date 2023/3/29 12:01
 */
public class Cas {
    class CASCounter {
        private Unsafe unsafe;
        // count 需要声明为 volatile 来保证对所有线程可见
        private volatile long count = 0;
        private long offset;

        public CASCounter(Unsafe unsafe) {
            this.unsafe = unsafe;
            try {
                offset = unsafe.objectFieldOffset(CASCounter.class.getDeclaredField("count"));
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }

        // 关键的 increment 方法，其在 while 循环里不断尝试调用 compareAndSwapLong，在方法内部累加count的同时
        // 检查其值有没有被其他线程改变。如没有，就提交更改，如果不一致，那么继续尝试提交更改
        public void increment() {
            long before = count;
            while (!unsafe.compareAndSwapLong(this, offset, before, before + 1)) {
                before = count;
            }
        }

        public long getCount() {
            return count;
        }
    }

}
