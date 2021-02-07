package dao;

import launch.HibernateUtil;
import model.Dog;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class DogDao {

    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    Transaction transaction = null;

    public void saveOrUpdate(Dog dog){

        try (Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            session.saveOrUpdate(dog);
            transaction.commit();
        }catch (HibernateException ex){
            if (transaction != null){
                transaction.rollback();
            }
        }
    }

    public void delete(Dog dog){

        try (Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            session.delete(dog);
            transaction.commit();
        }catch (HibernateException ex){
            if (transaction != null){
                transaction.rollback();
            }
        }
    }

    public Optional<Dog> findById(Long id) {
        try (Session session = sessionFactory.openSession()){
            return Optional.ofNullable(session.get(Dog.class, id));
        }catch (HibernateException ex){
            ex.printStackTrace();
        }
        return Optional.empty();
    }

    public List<Dog> getAll(){
        List<Dog> list = new ArrayList<>();

        try (Session session = sessionFactory.openSession()){

            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Dog> criteriaQuery = cb.createQuery(Dog.class);
            Root<Dog> root = criteriaQuery.from(Dog.class);

            criteriaQuery.select(root);

            list.addAll(session.createQuery(criteriaQuery).list());
        }catch (HibernateException ex){
            ex.printStackTrace();
        }
        return list;
    }
}
