import dao.UserDao;
import hibernate.util.HibernateUtil;
import model.User;

import java.util.Date;


public class TestDatabaseConnection {

    public static void main(String[] args) {
        HibernateUtil hibernateUtil = HibernateUtil.getInstance();
        UserDao userDao = new UserDao();

        for(int i = 0; i<20; i++){
            User user = new User();
            user.setDate_of_registration(new Date());
            user.setLogin("Login" + i);
            user.setName("Name");
            user.setLastName("LastName");
            user.setPassword("pass");
            user.setEmail("email@0"+ i);
            userDao.saveUser(user);

        }


    }
}
