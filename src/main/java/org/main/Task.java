package org.main;

public class Task {
    private String title;
    private int priority;
    private boolean isCompleted;

    public Task(String title, int priority){
        this.title = title;
        setPriority(priority);
        this.isCompleted = false;
    }

    public String getDescription(){
        return ("Задача: " + title + ", приоритет: " + priority + ", статус задачи: " + isCompleted);
    }

    public String getTitle(){
        return title;
    }

    public int getPriority(){
        return priority;
    }

    public boolean isCompleted(){
        return isCompleted;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setPriority(int priority){
        if(priority < 1 || priority > 10){
            throw new IllegalArgumentException("Приоритет должен быть от 1 до 10!");
        }
        this.priority = priority;
    }

    public void setCompleted(boolean completed){
        isCompleted = completed;
    }


}
