package org.main;

import java.io.IOException;

public class BackgroundBackup implements Runnable{

    private TaskManager manager;

    public BackgroundBackup(TaskManager manager){
        this.manager = manager;
    }

    @Override
    public void run() {
            System.out.println("Фоновое сохранение задач");
        try {
            manager.saveToFile(manager.getTasks());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Фоновое сохранение задач выполнено!");
    }
}
