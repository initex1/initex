package lv.initex.todo.views;

import lv.initex.todo.services.DeleteTaskService;

import java.util.Scanner;

public class DeleteTaskView {
    private DeleteTaskService deleteTaskService;

    public DeleteTaskView(DeleteTaskService deleteTaskService){
        this.deleteTaskService=deleteTaskService;
    }

    public void execute(){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Which task to delete?");
        String deleteTaskName=scanner.next();
        boolean isDeleted=deleteTaskService.deleteTask(deleteTaskName);
        if(isDeleted){
            System.out.println("Task deleted successfully");
        }else{
            System.out.println("Oops! Something wrong!");
        }
    }
}
