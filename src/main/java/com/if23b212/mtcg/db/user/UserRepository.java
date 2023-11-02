package com.if23b212.mtcg.db.user;

import com.if23b212.mtcg.exception.user.UserExceptionHelper;
import com.if23b212.mtcg.util.HibernateUtils;
import com.if23b212.mtcg.model.user.User;
import com.if23b212.mtcg.model.user.UserCredentials;
import com.if23b212.mtcg.util.PasswordUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;

public class UserRepository {

    /**
     * Creates a new User and stores it in the Database(if does not already exist)
     * @param userCredentials
     * @throws UserExceptionHelper.AlreadyRegisteredException
     */
    public void saveUser(UserCredentials userCredentials) throws UserExceptionHelper.AlreadyRegisteredException {
        Transaction transaction = null;
        boolean alreadyExists = false;

        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            // Check if user with the given username already exists
            Long count = (Long) session.createQuery("SELECT COUNT(u) FROM User u WHERE u.username = :username").setParameter("username", userCredentials.getUsername()).uniqueResult();
            if(count > 0 ) {
                alreadyExists = true;
            } else {
                transaction = session.beginTransaction();
                session.save(new User(userCredentials.getUsername(), PasswordUtils.hashPassword(userCredentials.getPassword()), 0, new ArrayList<>()));
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        if(alreadyExists) {
            throw new UserExceptionHelper.AlreadyRegisteredException();
        }
    }

    public User loginUser(UserCredentials userCredentials) throws UserExceptionHelper.InvalidCredentialsException {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            // Query to fetch a user with the given username and password
            session.beginTransaction();
            User user = session.createQuery("FROM User u WHERE u.username = :username", User.class)
                    .setParameter("username", userCredentials.getUsername())
                    .uniqueResult();
            session.getTransaction().commit();

            if (user != null && PasswordUtils.checkPassword(userCredentials.getPassword(), user.getPassword())) {
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new UserExceptionHelper.InvalidCredentialsException();
    }
}
