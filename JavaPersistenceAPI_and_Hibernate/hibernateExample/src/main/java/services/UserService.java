package services;

import dao.UserDao;
import models.Operation;
import models.User;

import java.util.List;

public class UserService {
    private UserDao usersDao = new UserDao();

    public UserService() {
    }

    public User findUser(int id) {
        return usersDao.findById(id);
    }

    public void saveUser(User user) {
        usersDao.save(user);
    }

    public void deleteUser(User user) {
        usersDao.delete(user);
    }

    public void updateUser(User user) {
        usersDao.update(user);
    }

    public Operation findOperationById(int id) {
        return usersDao.findOperationById(id);
    }

}
