package lv.initex.todo.views;

import lv.initex.todo.services.AddUserService;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class AddUserView {
    private AddUserService addUserService;

    public AddUserView(AddUserService addUserService) {
        this.addUserService = addUserService;
    }

    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Add new user" + "\n" + "Enter user name");
        String userName = scanner.next();
        addUserService.add(userName);
        System.out.println("Hi, " + userName + "! Database active user set to " + userName);
    }
}
