package lv.initex.todo.views;

import lv.initex.todo.domain.User;
import lv.initex.todo.services.SelectUserService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class SelectUserView {
    private SelectUserService selectUserService;

    public SelectUserView(SelectUserService selectUserService) {
        this.selectUserService = selectUserService;
    }

    public void execute() {
        Scanner scanner = new Scanner(System.in);
        List<User> userList=selectUserService.printAllUsers();
        for(User user:userList){
            System.out.println(user.getUserName());
        }
        System.out.println("Select user");
        String userName = scanner.next();
        User user = selectUserService.selectUser(userName);
        if (user != null) {
            System.out.println("Active user set to " + user.getUserName());
        } else {
            System.out.println("Oops! No such user!");
        }
    }
}
