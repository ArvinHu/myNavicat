package com.milla.navicat.spring.study.concurrency.join;

/**
 * @Package: com.milla.navicat.spring.study.concurrency.join
 * @Description: <>
 * @Author: MILLA
 * @CreateDate: 2020/3/26 17:28
 * @UpdateUser: MILLA
 * @UpdateDate: 2020/3/26 17:28
 * @UpdateRemark: <>
 * @Version: 1.0
 */
public class JoinTest {
    /**
     * 问：现在有T1、T2、T3三个线程，你怎样保证T2在T1执行完后执行，T3在T2执行完后执行？
     * 答：要保证T1、T2、T3三个线程顺序执行，可以利用Thread类的join方法。
     * <p>
     * <p>
     * <p>
     * 问：join方法的作用？
     * 答：　Thread类中的join方法的主要作用就是同步，它可以使得线程之间的并行执行变为串行执行。
     * 当我们调用某个线程的这个方法时，这个方法会挂起调用线程，直到被调用线程结束执行，调用线程才会继续执行。
     * <p>
     * <p>
     * <p>
     * 问：join方法传参和不传参的区别？
     * 答：join方法中如果传入参数，则表示这样的意思：如果A线程中掉用B线程的join(10)，
     * 则表示A线程会等待B线程执行10毫秒，10毫秒过后，A、B线程并行执行。需要注意的是，
     * jdk规定，join(0)的意思不是A线程等待B线程0秒，而是A线程等待B线程无限时间，直到B线程执行完毕，即join(0)等价于join()。
     */
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Runner("one");
        Thread thread2 = new Runner("two");
        Thread thread3 = new Runner("three");

        thread1.start();
        thread1.join();

        thread2.start();
        thread2.join();

        thread3.start();
        thread3.join();
    }

    static class Runner extends Thread {

        public Runner(String runnerName) {
            super(runnerName);
        }

        @Override
        public void run() {
            for (int i = 1; i < 4; i++) {
                System.out.println(this.getName() + " - " + i);
            }
        }
    }
}
