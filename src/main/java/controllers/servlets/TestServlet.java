package controllers.servlets;

import dao.UserDao;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet(name = "test", value = "/test")
public class TestServlet extends HttpServlet {
    UserDao userDao = new UserDao();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       User user = new User.UserBuilder().buildPassword("lala").buildEmail("w@w.pl").buildLastName("kowalik").buildName("franka")
               .buildLogin("Franka").buildUser();

       List<User> allUsers = userDao.getAllUsers();
       req.setAttribute("users", allUsers);
       req.setAttribute("user", user);
       req.setAttribute("me", "Mark");
       req.getSession().setAttribute("me", "MarkSession");
       req.setAttribute("now", new Date());
       req.getRequestDispatcher("test.jsp").forward(req, resp);
    }
}
