package org.main;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class TaskManager {

    private List<Task> tasks = new ArrayList<>();

    Gson gson = new GsonBuilder()
            .setPrettyPrinting().create();
    public void saveToFile(List<Task> tasks) throws IOException {
        String jsoneString = gson.toJson(tasks);
        Files.writeString(Path.of("tasks.json"), jsoneString);
    }

    public void loadFromFile() throws IOException {
        Path path = Path.of("tasks.json");
        if(!Files.exists(path)){
            return;
        }
        String gsonTasks = Files.readString(path);

        Type targetType = new TypeToken<ArrayList<Task>>(){}.getType();
        tasks = gson.fromJson(gsonTasks, targetType);

    }

    public List<Task> getTasks(){
        return tasks;
    }

    public List<Task> getCompletedTasks(){
        return tasks.stream().filter(task -> task.isCompleted()).collect(Collectors.toList());
    }

    public void searchTasksByKeyword(String keyword){
        List<Task> matchingTasks = tasks.stream().filter(task -> task.getTitle().contains(keyword)).collect(Collectors.toList());
        if(!matchingTasks.isEmpty()){
            for(Task task : matchingTasks){
                System.out.println(task.getDescription());
            }
        } else{
            System.out.println("Ничего не найдено!");
        }
    }

    public void addTask(Task task){
        tasks.add(task);
        System.out.println("Задача " + task.getTitle() + " добавлена.");
    }

    public void printAllTasks(){
        if(!tasks.isEmpty()){
            for(int i = 0; i < tasks.size(); i++){
                System.out.println(i+1 + ". " + tasks.get(i).getDescription());
            }
        } else System.out.println("Список задач пуст.");
    }

    public void printTaskOnIndex(int index){
        if(index < 0 || index > tasks.size() - 1){
            throw new IllegalArgumentException("Такой задачи не существует!");
        }
        tasks.get(index).getDescription();
    }

    public void removeTask(int index){
        if(index < 0 || index > tasks.size() - 1){
            throw new IllegalArgumentException("Такой задачи не существует!");
        }
        tasks.remove(index);
        System.out.println("Задача удалена.");
    }

    public void markTaskAsCompleted(int index) {
        if (index < 0 || index > tasks.size() - 1) {
            throw new IllegalArgumentException("Такой задачи не существует!");
        }
        tasks.get(index).setCompleted(true);
    }

}
