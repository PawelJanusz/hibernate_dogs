package dao;

import launch.HibernateUtil;
import model.Dog;
import model.Race;
import org.hibernate.*;
import org.hibernate.criterion.Order;

import javax.persistence.EntityManagerFactory;
import javax.persistence.OrderBy;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class DogDao {

    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private Transaction transaction = null;

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

    public List<Dog> findByAgeBetween(int ageFrom, int ageTo){
        List<Dog> list = new ArrayList<>();

        try (Session session = sessionFactory.openSession()){

            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Dog> criteriaQuery = cb.createQuery(Dog.class);
            Root<Dog> root = criteriaQuery.from(Dog.class);

            criteriaQuery.select(root)
                    .where(
                            cb.between(root.get("age"), ageFrom, ageTo)
                    )
                    .orderBy(
                            cb.asc(root.get("age"))
                    );
            list.addAll(session.createQuery(criteriaQuery).list());
        }catch (HibernateException ex){
            ex.printStackTrace();
        }
        return list;
    }

    public List<Dog> findByWeightBetween(int weightFrom, int weightTo){
        List<Dog> list = new ArrayList<>();

        try (Session session = sessionFactory.openSession()){

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Dog> criteriaQuery = criteriaBuilder.createQuery(Dog.class);
            Root<Dog> root = criteriaQuery.from(Dog.class);

            criteriaQuery.select(root)
                    .where(
                            criteriaBuilder.between(root.get("weight"), weightFrom, weightTo)
                    )
                    .orderBy(
                            criteriaBuilder.asc(root.get("weight"))
                    );
            list.addAll(session.createQuery(criteriaQuery).list());
        }catch (HibernateException ex){
            ex.printStackTrace();
        }
        return list;
    }

    public List<Dog> findByRaceName(Race race){
        List<Dog> list = new ArrayList<>();

        try (Session session = sessionFactory.openSession()){

            CriteriaBuilder cb  = session.getCriteriaBuilder();
            CriteriaQuery<Dog> criteriaQuery = cb.createQuery(Dog.class);
            Root<Dog> root = criteriaQuery.from(Dog.class);

            criteriaQuery.select(root)
                    .where(
                            cb.equal(root.get("race"), race)
                    );
            list.addAll(session.createQuery(criteriaQuery).list());
        }catch (HibernateException ex){
            ex.printStackTrace();
        }
        return list;
    }

    public List<Dog> findByPureRaceAndName(boolean pureRace, String name){
        List<Dog> list = new ArrayList<>();

        try (Session session = sessionFactory.openSession()){

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Dog> criteriaQuery = criteriaBuilder.createQuery(Dog.class);
            Root<Dog> root = criteriaQuery.from(Dog.class);

            criteriaQuery.select(root)
                    .where(
                            criteriaBuilder.and(
                            criteriaBuilder.equal(root.get("pureRace"), pureRace),
                            criteriaBuilder.equal(root.get("name"), name)
                            )
                    )
                    .orderBy(
                            criteriaBuilder.asc(root.get("name"))
                    );
            list.addAll(session.createQuery(criteriaQuery).list());
        }
        return list;
    }
}
