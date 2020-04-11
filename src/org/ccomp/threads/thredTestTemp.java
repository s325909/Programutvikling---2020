package org.ccomp.threads;

public class thredTestTemp implements Runnable {

    //Todo
    //



    @Override
    public void run() {
        System.out.println("Hello world from thread with id" + Thread.currentThread().getId());
    }


}
