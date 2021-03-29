package dao;

import model.Dog;
import model.Race;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

class DogDaoTest {

    private DogDao dao = new DogDao();
    private Dog dog = new Dog();

    @BeforeEach
    void cleanUp(){
        dao.delete(dog);
    }

    @Test
    void dogShouldBeDeleteFromTheList(){
        //given
        Dog dog = new Dog("Burek", 12, "Andrzej", 23, true, Race.BERNARDINE);
        int sizeListBefore = dao.getAll().size();
        //when
        dao.saveOrUpdate(dog);
        dao.delete(dog);
        int sizeListAfter = dao.getAll().size();
        //then
        assertThat(sizeListAfter, is(sizeListBefore));
    }

    @Test
    void addDogShouldBeOnTheList(){
        //given
        Dog dog = new Dog("Burek", 12, "Andrzej", 23, true, Race.BERNARDINE);
        int sizeListBefore = dao.getAll().size();
        //when
        dao.saveOrUpdate(dog);
        int sizeListAfter = dao.getAll().size();
        //then
        assertThat(sizeListAfter, is(sizeListBefore + 1));
    }

    @Test
    void addManyDogsToTheList(){
        //given
        Dog dog1 = new Dog("Max", 3, "Monika", 6, false, Race.MONGREL);
        Dog dog2 = new Dog("Toffik", 1, "Monika", 3, true, Race.CHIHUAHUA);
        int sizeListBefore = dao.getAll().size();
        //when
        dao.saveOrUpdate(dog1);
        dao.saveOrUpdate(dog2);
        int sizeListAfter = dao.getAll().size();
        //then
        assertThat(sizeListAfter, is(sizeListBefore + 2));

    }

    @Test
    void referencesToTheSameObjectShouldBeEqual(){
        //given
        Dog dog = new Dog("Burek", 12, "Andrzej", 23, true, Race.BERNARDINE);
        Dog dog1 = dog;
        //then
        assertSame(dog, dog1);
    }

    @Test
    void referencesToDifferentObjectShouldNotBeEqual(){
        //given
        Dog dog = new Dog("Burek", 12, "Andrzej", 23, true, Race.BERNARDINE);
        Dog dog1 = new Dog("Burek", 12, "Andrzej", 19, true, Race.BERNARDINE);
        //then
        assertNotSame(dog, dog1);
    }

    @Test
    void twoDogsShouldBeEqualWithTheSameParameters(){
        //given
        Dog dog = new Dog("Burek", 9, "Andrzej", 19, true, Race.CHIHUAHUA);
        Dog dog1 = new Dog("Burek", 9, "Andrzej", 19, true, Race.CHIHUAHUA);
        //then
        assertEquals(dog, dog1);
    }

    @Test
    void dogsShouldBeNotSameByEqualOnlyRace(){
        //given
        //when
        List<Dog> list = dao.findByRaceName(Race.DACHSHUND);
        //then
        assertNotSame(Race.DACHSHUND, list);
    }

    @Test
    void dogsShouldBeNotSameByRaceEqualFromList(){
        //given
        //when
        List<Dog> list1 = dao.findByRaceName(Race.MOPS);
        List<Dog> list2 = dao.findByRaceName(Race.MOPS);
        //then
        assertNotSame(list1, list2);
    }
}