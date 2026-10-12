package org.main;

import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskManager manager = new TaskManager();
        try{
            manager.loadFromFile();
        } catch (IOException e){
            System.out.println("Ошибка загрузки файла данных!");
        }

        while (true) {
            System.out.println("=== ТРЕКЕР ЗАДАЧ ===");
            System.out.println("1. Добавить обычную задачу");
            System.out.println("2. Добавить срочную задачу");
            System.out.println("3. Показать все задачи");
            System.out.println("4. Отметить задачу выполненной");
            System.out.println("5. Удалить задачу");
            System.out.println("6. Выполненные задачи");
            System.out.println("7. Найти задачу по совпадению");
            System.out.println("0. Выйти из программы");
            System.out.print("Ваше действие: ");

            try {
                int number = 0;
                number = Integer.parseInt(scanner.nextLine());
                switch (number) {
                    case 1: {
                            System.out.print("Введите название задачи: ");
                            String name = scanner.nextLine();
                            System.out.print("Введите приоритет задачи(от 1 до 10): ");
                            int priority = Integer.parseInt(scanner.nextLine());
                            Task newTask = new Task(name, priority);
                            manager.addTask(newTask);
                    } break;
                    case 2: {
                        System.out.print("Введите название срочной задачи: ");
                        String name = scanner.nextLine();
                        System.out.print("Введите приоритет задачи(от 1 до 10): ");
                        int priority = Integer.parseInt(scanner.nextLine());
                        System.out.print("Введите дедлайн задачи: ");
                        String deadline = scanner.nextLine();
                        UrgentTask newUrgentTask = new UrgentTask(name, priority, deadline);
                        manager.addTask(newUrgentTask);
                    } break;
                    case 3: {
                        manager.printAllTasks();
                    } break;
                    case 4: {
                        manager.printAllTasks();
                        System.out.println("Выберите задачу, которую завершили.");
                        int index = Integer.parseInt(scanner.nextLine()) - 1;
                        manager.markTaskAsCompleted(index);
                        manager.printTaskOnIndex(index);
                    } break;
                    case 5: {
                        manager.printAllTasks();
                        System.out.println("Выберите задачу для удаления.");
                        int index = Integer.parseInt(scanner.nextLine()) - 1;
                        manager.removeTask(index);
                    } break;
                    case 6: {
                        List<Task> completedTasks;
                        completedTasks = manager.getCompletedTasks();
                        if(!completedTasks.isEmpty()){
                            for (Task completedTask : completedTasks) {
                                System.out.println(completedTask.getDescription());
                            }
                        } else {
                            System.out.println("Ничего не найдено!");
                        }
                    } break;
                    case 7: {
                        System.out.println("Введите слово из задачи");
                        String matchWord = scanner.nextLine();
                        manager.searchTasksByKeyword(matchWord);
                    } break;
                    case 0: {
                        manager.saveToFile(manager.getTasks());
                        return;
                    }
                    default:
                        System.out.println("Такого пункта не существует!");
                }

            } catch (NumberFormatException e) {
                System.out.println("Неверный формат ввода: " + e.getMessage());
            } catch (NoSuchElementException e){
                System.out.println("Строка не найдена: " + e.getMessage());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}