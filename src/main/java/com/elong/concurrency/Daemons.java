package com.elong.concurrency;

import java.util.concurrent.TimeUnit;

/**
 * 包名: com.elong.concurrency
 * 创建人 : Elong
 * 时间: 16/9/5 下午7:41
 * 描述 :
 */
class Daemon implements Runnable {
    private Thread[] t = new Thread[10];

    public void run() {
        for (int i = 0; i < t.length; i++) {
            t[i] = new Thread(new DaemonSpawn());
            t[i].start();
            System.out.print("DaemonSpawn " + i + " started, ");
        }
        for (int i = 0; i < t.length; i++) {
            System.out.print("t[" + i + "].isDaemon() = " + t[i].isDaemon() + ", ");
        }
        while (true) {
            Thread.yield();
        }
    }
}

class DaemonSpawn implements Runnable {

    public void run() {
        while (true) {
            Thread.yield();
        }
    }
}

public class Daemons {
    public static void main(String[] args) throws Exception {
        Thread d = new Thread(new Daemon());
        d.setDaemon(true);
        d.start();
        System.out.print("d.isDaemon() = " + d.isDaemon() + ", ");
        TimeUnit.MILLISECONDS.sleep(1);
        Thread t1 = new Thread();
        System.out.println("\n\n" + t1.isDaemon());
    }
}