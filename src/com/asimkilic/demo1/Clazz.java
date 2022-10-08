package com.asimkilic.demo1;

public class Clazz extends Thread  {
    Processor p = new Processor();

    private boolean isFlag = true;
    @Override
    public void run() {
        try {
            if(Processor.UPPER_LIMIT>10){
                p.producer();
            }
            else{
                p.consumer();
            }

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
