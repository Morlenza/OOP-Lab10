package it.unibo.oop.lab.reactivegui03;


import it.unibo.oop.lab.reactivegui02.ConcurrentGUI;

public class AnotherConcurrentGUI extends ConcurrentGUI {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 
     * Starts another agent on the superclass.
     */
    public AnotherConcurrentGUI() {
        super();
        final Agent2 agent2 = new Agent2();
        new Thread(agent2).start();
    }

    private void stop() {
        super.stopFunctionButton();
    }

    private class Agent2 implements Runnable {
        private volatile boolean stop;
        private volatile int counter;
        private static final long TIME = 10_000;


        @Override
        public void run() {
            while (!this.stop) {
                try {
                    Thread.sleep(TIME);
                    stop();
                } catch (InterruptedException ex) {
                    /*
                     * This is just a stack trace print, in a real program there
                     * should be some logging and decent error reporting
                     */
                    ex.printStackTrace();
                }
            }
        }

        /**
         * External command to stop counting.
         */
        public void stopCounting() {
            this.stop = true;
        }
    }

}
