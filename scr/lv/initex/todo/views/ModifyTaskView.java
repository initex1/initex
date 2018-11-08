package lv.initex.todo.views;

import lv.initex.todo.domain.UserTask;
import lv.initex.todo.services.ModifyTaskService;

import java.util.Scanner;

public class ModifyTaskView {
    private ModifyTaskService modifyTaskService;

    public ModifyTaskView(ModifyTaskService modifyTaskService) {
        this.modifyTaskService = modifyTaskService;
    }

    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Current user have following tasks:");

        printTasks();
        System.out.println("");
        System.out.println("Which task to modify");
        String findTask = scanner.next();
        UserTask taskToModify = modifyTaskService.getTaskForModify(findTask);
        System.out.println("What do u want to modify: " + "\n" + "1-Task name" + "\n" + "2-Task status");
        int taskParameter = scanner.nextInt();
        switch (taskParameter) {
            case 1:
                System.out.println("Enter new task name");
                String newTaskName = scanner.next();
                modifyTaskService.modifyTaskName(taskToModify, newTaskName);
                break;
            case 2:
                System.out.println("Enter new status " + "\n" + "1-ACTIVE" + "\n" + "2-INPROGRESS" + "\n" + "3-CANCELLED" + "\n" + "4-FINISHED");
                int newStatus = scanner.nextInt();
                modifyTaskService.modifyTaskStatus(taskToModify, newStatus);
                break;
        }
        printTasks();
    }

    private void printTasks() {
        for (UserTask task : modifyTaskService.getAllTasks()) {
            System.out.println(task.getTask().getTaskName() + " Status: " + task.getTask().getStatus());
        }
    }
}
