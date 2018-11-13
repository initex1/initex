package lv.initex.todo.views;

import lv.initex.todo.services.AddTaskService;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class AddTaskView {
    private AddTaskService addTaskService;

    public AddTaskView(AddTaskService addTaskService) {
        this.addTaskService = addTaskService;
    }

    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Add new task" + "\n" + "Enter task name");
        String taskName = scanner.next();
        addTaskService.addTask(taskName);
    }
}
