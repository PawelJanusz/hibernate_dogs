package dao;

import model.Dog;
import model.Race;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

class DogDaoTest {

    private DogDao dao = new DogDao();

    @BeforeEach
    void cleanUp(){
        dao.getAll().clear();
    }

    @Test
    @Disabled
    void dogShouldBeDeleteFromTheList(){
        //given
        DogDao dao = new DogDao();
        Dog dog = new Dog(10L, "Burek", 12, "Andrzej", 23, true, Race.BERNARDINE);
        //when
        dao.getAll().add(dog);
        dao.delete(dog);
        //then
        assertThat(dao.getAll().size(), is(96));
    }

    @Test
    void addingDogsShouldBeOnTheList(){
        //given
        DogDao dao = new DogDao();
        Dog dog = new Dog(10L, "Burek", 12, "Andrzej", 23, true, Race.BERNARDINE);
        //when
        dao.getAll().add(dog);
        //then
        assertThat(dao.getAll().size(), is(97));
    }

    @Test
    void referencesToTheSameObjectShouldBeEqual(){
        //given
        Dog dog = new Dog(10L, "Burek", 12, "Andrzej", 23, true, Race.BERNARDINE);
        Dog dog1 = dog;
        //then
        assertSame(dog, dog1);
    }

    @Test
    void referencesToDifferentObjectShouldNotBeEqual(){
        //given
        Dog dog = new Dog(10L, "Burek", 12, "Andrzej", 23, true, Race.BERNARDINE);
        Dog dog1 = new Dog(10L, "Burek", 12, "Andrzej", 19, true, Race.BERNARDINE);
        //then
        assertNotSame(dog, dog1);
    }

    @Test
    void twoDogsShouldBeEqualWithTheSameParameters(){
        //given
        Dog dog = new Dog(10L, "Burek", 9, "Andrzej", 19, true, Race.CHIHUAHUA);
        Dog dog1 = new Dog(10L, "Burek", 9, "Andrzej", 19, true, Race.CHIHUAHUA);
        //then
        assertEquals(dog, dog1);
    }

    @Test
    void dogsShouldBeNotSameByEqualOnlyRace(){
        //given
        DogDao dao = new DogDao();
        //when
        List<Dog> list = dao.findByRaceName(Race.DACHSHUND);
        //then
        assertNotSame(Race.DACHSHUND, list);
    }

    @Test
    void dogsShouldBeNotSameByRaceEqualFromList(){
        //given
        DogDao dao = new DogDao();
        //when
        List<Dog> list1 = dao.findByRaceName(Race.MOPS);
        List<Dog> list2 = dao.findByRaceName(Race.MOPS);
        //then
        assertNotSame(list1, list2);
    }

}