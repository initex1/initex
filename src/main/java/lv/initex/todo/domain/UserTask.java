package lv.initex.todo.domain;

public class UserTask {
    private Long id;
    private User user;
    private Task task;

    public UserTask(User user, Task task){
        this.user=user;
        this.task=task;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
