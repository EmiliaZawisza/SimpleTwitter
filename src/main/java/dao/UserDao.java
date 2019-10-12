package dao;

import model.User;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class UserDao extends AbstractDao {
    public void saveUser(User user) {
        hibernateUtil.save(user);
    }

    public void deleteUser(long userId) {
        hibernateUtil.delete(User.class, userId);
    }

    public User getUserByEmail(String email) {
        TypedQuery<User> query = entityManager.createQuery("select u from User u where email = :email", User.class);
        return query.setParameter("email", email).getSingleResult();
    }

    public User getUserByLogin(String login) {
        TypedQuery<User> query = entityManager.createQuery("select u from User u where login = :login", User.class);
        return query.setParameter("login", login).getSingleResult();
    }

    public List<User> getUserByName(String name) {
        Query query = entityManager.createQuery("select u from User u where name =: name", User.class);
        return query.setParameter("name", name).getResultList();
    }

    public boolean isUserValid(String login, String password) {
        Query query = entityManager.createQuery("select count(*) as cnt from User u where u.login = :login and u.password = :password");
        query.setParameter("login", login);
        query.setParameter("password", password);
        Object singleResult = query.getSingleResult();
        return ((Long) singleResult > 0) ? true : false;
    }

    public List<User> getFollowedUser(String login) {
        User user = getUserByLogin(login);
        Long userId = user.getId();
        Query query = entityManager.createQuery("select follows from User u where u.id = :userId");
        return query.setParameter("userId", userId).getResultList();
    }

    public List<User> getNotFollowedUsers(String login) {
        Query query = entityManager.createQuery("select u from User u where u.login != :login");
        query.setParameter("login", login);
        List<User> users = query.getResultList();
        List<User> followeUsers = getFollowedUser(login);
        users.removeAll(followeUsers);
        return users;
    }

    public void follow(String currenUserLogin, String userLoginToFollow) {
        if (currenUserLogin != userLoginToFollow) {
            User currentUser = getUserByLogin(currenUserLogin);
            User userToFollow = getUserByLogin(userLoginToFollow);
            currentUser.getFollows().add(userToFollow);
            saveUser(currentUser);
        }
    }

    public void stopFollowing(String currentUserLogin, String userLoginToStopFollow) {
        if (currentUserLogin != userLoginToStopFollow) {
            User currentUser = getUserByLogin(currentUserLogin);
            User userToStopFollowing = getUserByLogin(userLoginToStopFollow);
            currentUser.getFollows().remove(userToStopFollowing);
            saveUser(currentUser);
        }
    }

    public List<User> getAllUsers() {
        TypedQuery typedQuery = entityManager.createQuery("select u from User u", User.class);
        return typedQuery.getResultList();
    }
}

