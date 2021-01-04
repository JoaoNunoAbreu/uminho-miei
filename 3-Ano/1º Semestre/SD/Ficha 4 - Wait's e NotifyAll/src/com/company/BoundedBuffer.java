package com.company;

public class BoundedBuffer{

    private final int [] buffer;
    private int filled = 0;

    public BoundedBuffer(int tam){
        this.buffer = new int[tam];
    }

    // Deve bloquear enquanto tiver cheio
    public void put(int v) throws InterruptedException {
        synchronized(this.buffer){
            while(this.filled == buffer.length)
                this.buffer.wait();

            this.buffer[this.filled] = v;
            this.filled++;
            this.buffer.notifyAll();
        }
    }

    // Deve bloquear enquanto tiver vazio
    public int get() throws InterruptedException {
        int res;
        synchronized(this.buffer){
            while(this.filled == 0)
                this.buffer.wait();

            res = this.buffer[this.filled-1];
            this.buffer[this.filled-1] = 0;
            this.filled--;
            this.buffer.notifyAll();
        }
        return res;
    }

    public String toString(){
        StringBuilder res = new StringBuilder();
        for(int value : buffer) {
            res.append(value).append(" ");
        }
        return res.toString();
    }
}
