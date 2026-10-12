package org.main;


public class UrgentTask extends Task{
    private String deadline;

    public UrgentTask(String title, int priority, String deadline){
        super(title, priority);
        this.deadline = deadline;
    }

    @Override
    public String getDescription(){
        return ("СРОЧНО: " + getTitle() + ", приоритет: " + getPriority() + ", ДЕДЛАЙН: " + deadline + ", статус задачи: " + isCompleted());
    }

    public String getDeadline(){return deadline;}

    public void setDeadline(String deadline){this.deadline = deadline;}
}
