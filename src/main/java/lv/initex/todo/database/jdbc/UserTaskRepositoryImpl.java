package lv.initex.todo.database.jdbc;


import lv.initex.todo.database.UserTaskRepository;
import lv.initex.todo.domain.Task;
import lv.initex.todo.domain.TaskStatusEnum;
import lv.initex.todo.domain.User;
import lv.initex.todo.domain.UserTask;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class UserTaskRepositoryImpl extends JDBCRepository implements UserTaskRepository {

    private User activeUser = new User();

    @Override
    public void addUser(User user) {
        Connection connection = null;
        try {
            connection = getConnection();
            String sql = "insert into users(id, userName) values(default, ?)";
            PreparedStatement preparedStatement =
                    connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getUserName());

            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                user.setId(rs.getLong(1));
            }
        } catch (Throwable e) {
            System.out.println("Exception while execute .addUser()");
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public void setActiveUser(User user) {
        activeUser = user;
    }

    @Override
    public Optional<User> findUserByName(String userName) {
        Connection connection = null;

        try {
            connection = getConnection();
            String sql = "select * from users where userName = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userName);
            ResultSet resultSet = preparedStatement.executeQuery();
            User user = null;
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getLong("id"));
                user.setUserName(resultSet.getString("userName"));
            }
            return Optional.ofNullable(user);
        } catch (Throwable e) {
            System.out.println("Exception while execute .findUserByName()");
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public User getActiveUser() {
        return activeUser;
    }

    @Override
    public void addTask(UserTask userTask) {
        Connection connection = null;
        try {
            connection = getConnection();
            String sql = "insert into tasks(id, user_ID, taskName, status) values(default, ?,?,?)";
            PreparedStatement preparedStatement =
                    connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, userTask.getUser().getId());
            preparedStatement.setString(2, userTask.getTask().getTaskName());
            preparedStatement.setString(3, userTask.getTask().getStatus().name());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                userTask.setId(rs.getLong(1));
            }
        } catch (Throwable e) {
            System.out.println("Exception while execute .addUser()");
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public void modifyTaskName(UserTask task, String newName) {

        Connection connection = null;
        try {
            connection = getConnection();
            String sql = "update  tasks set taskName=? where id=? ";
            PreparedStatement preparedStatement =
                    connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, newName);
            preparedStatement.setLong(2, task.getId());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                task.getTask().setTaskName(rs.getString(3));
            }
        } catch (Throwable e) {
            System.out.println("Exception while execute .modifyTaskName()");
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public void modifyTaskStatus(UserTask task, TaskStatusEnum status) {
        Connection connection = null;
        try {
            connection = getConnection();
            String sql = "update  tasks set status=? where id=? ";
            PreparedStatement preparedStatement =
                    connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, status.name());
            preparedStatement.setLong(2, task.getId());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                task.getTask().setStatus(TaskStatusEnum.valueOf(rs.getString(4)));
            }
        } catch (Throwable e) {
            System.out.println("Exception while execute .modifyTaskStatus()");
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public boolean deleteTask(UserTask userTask) {
        Connection connection = null;
        try {
            connection = getConnection();
            String sql = "delete from TASKS where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, userTask.getId());
            int numOfRowsDeleted = preparedStatement.executeUpdate();
            return numOfRowsDeleted == 1;
        } catch (Throwable e) {
            System.out.println("Exception while execute .deleteTask()");
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public Optional<UserTask> findTaskByTitle(String taskName) {
        Connection connection = null;

        try {
            connection = getConnection();
            String sql = "select * from tasks where taskName = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, taskName);
            ResultSet resultSet = preparedStatement.executeQuery();
            UserTask userTask = null;
            if (resultSet.next()) {
                Task task = new Task();
                task.setTaskName(resultSet.getString("taskName"));
                task.setStatus(TaskStatusEnum.valueOf(resultSet.getString("status")));
                userTask = new UserTask(activeUser, task);
                userTask.setId(resultSet.getLong("ID"));
            }
            return Optional.ofNullable(userTask);
        } catch (Throwable e) {
            System.out.println("Exception while execute .findTaskByTitle()");
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        Connection connection = null;
        try {
            connection = getConnection();
            String sql = "select * from users";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setUserName(resultSet.getString("userName"));
                users.add(user);
            }
        } catch (Throwable e) {
            System.out.println("Exception while getting user list .getAllUserTasks()");
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
        }
        return users;
    }


    @Override
    public List<UserTask> getAllUserTasks(User user) {
        List<UserTask> userTasks = new ArrayList<>();
        Connection connection = null;
        try {
            connection = getConnection();
            String sql = "select * from tasks where user_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, user.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Task task = new Task();
                task.setTaskName(resultSet.getString("taskName"));
                task.setStatus(TaskStatusEnum.valueOf(resultSet.getString("status")));
                UserTask currentTask = new UserTask(user, task);
                userTasks.add(currentTask);
            }
        } catch (Throwable e) {
            System.out.println("Exception while getting user list .getAllUserTasks()");
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
        }
        return userTasks;
    }
}

