package service;

import dao.UserDAO;
import model.User;

public class UserService {
    private UserDAO userDAO = new UserDAO();

    public User searchUser(String username) {
        return userDAO.findUserByUsername(username);
    }
}
