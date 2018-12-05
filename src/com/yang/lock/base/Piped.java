package com.yang.lock.base;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

public class Piped {

    public static void main(String[] args) throws IOException {
        PipedWriter pw = new PipedWriter();
        PipedReader pr = new PipedReader();
        // 将输出流和输入流进行连接，否则在使用时会抛出IOException
        pw.connect(pr);

        Thread printThread = new Thread(new Print(pr), "PrintThread");
        printThread.start();

        int receive = 0;

        try {
            while ((receive = System.in.read()) != -1) {
                pw.write(receive);
            }
        } finally {
            pw.close();
        }
    }

    static class Print implements Runnable {

        private PipedReader in;

        public Print(PipedReader in) {
            this.in = in;
        }

        @Override
        public void run() {
            int receive = 0;

            while (true) {
                try {
                    while ((receive = in.read()) != -1) {
                        System.out.print((char) receive);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        }
    }




}
