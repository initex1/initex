package lv.initex.todo.views;

import lv.initex.todo.domain.UserTask;
import lv.initex.todo.services.PrintTaskListService;

public class PrintTaskListView {

    private PrintTaskListService printTaskListService;

    public PrintTaskListView(PrintTaskListService printTaskListService) {
        this.printTaskListService = printTaskListService;
    }

    public void execute() {

        System.out.println("Current user have following tasks:");
        for (UserTask task : printTaskListService.getAllTasks()) {
            System.out.println(task.getTask().getTaskName() + " Status: " + task.getTask().getStatus());
        }
    }
}
