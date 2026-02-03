package org.example.api.user.repository;

import org.example.api.user.model.LoginDtoReq;
import org.example.api.user.model.LoginDtoRes;
import org.example.api.user.model.SignupDtoReq;
import org.example.api.user.model.SignupDtoRes;
import org.example.api.user.model.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class UserHibernateRepository implements UserRepository {
    private final SessionFactory sessionFactory;

    public UserHibernateRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public SignupDtoRes signup(SignupDtoReq req) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();

            User userEntity = new User(req.getEmail(), req.getName(), req.getPassword());
            session.persist(userEntity);

            tx.commit();

            return new SignupDtoRes(userEntity.getIdx(), userEntity.getEmail(), userEntity.getName());
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null) tx.rollback();
        }
        return null;

    }


    @Override
    public LoginDtoRes login(LoginDtoReq req) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();

            User userEntity = session.createQuery(
                            "from User u where u.email = :email and u.password = :password",
                            User.class
                    )
                    .setParameter("email", req.getEmail())
                    .setParameter("password", req.getPassword())
                    .uniqueResult();

            tx.commit();

            return new LoginDtoRes(userEntity.getIdx(), userEntity.getEmail(), userEntity.getName());
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null) tx.rollback();
        }
        return null;
    }
}
