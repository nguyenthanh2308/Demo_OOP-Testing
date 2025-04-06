package controller;

import jakarta.enterprise.context.RequestScoped;
import jakarta.servlet.http.HttpSession;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import dao.UserDAO;  // Import UserDAO để dùng JDBC
import model.User;

@Named
@RequestScoped
public class AuthController implements Serializable {
    private String username;
    private String password;
    private User searchUser;
    private String searchUsername;


    private UserDAO userDAO = new UserDAO();

    // Getter & Setter
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSearchUsername() {
        return searchUsername;
    }

    public void setSearchUsername(String searchUsername) {
        this.searchUsername = searchUsername;
    }

    public User getSearchUser() {
        return searchUser;
    }

    // Xử lý đăng nhập
    public String login() {
        User user = userDAO.findUserByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                    .getExternalContext().getSession(true);
            session.setAttribute("username", username);
            return "welcome.xhtml?faces-redirect=true";
        }
        return "login.xhtml?faces-redirect=true";
    }

    // Xử lý đăng ký
    public String register() {
        boolean success = userDAO.registerUser(username, password);
        if (success) {
            return "login.xhtml?faces-redirect=true";
        }
        return "register.xhtml?faces-redirect=true";
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "login?faces-redirect=true"; // Chuyển về trang đăng nhập
    }

    // Phương thức tìm kiếm người dùng
    public void searchUser() {
        searchUser = userDAO.getUserByUsername(searchUsername);
        if (searchUser == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Không tìm thấy người dùng!", ""));
        }


    }
}
