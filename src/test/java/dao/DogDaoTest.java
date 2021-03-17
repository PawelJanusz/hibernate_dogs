package dao;

import model.Dog;
import model.Race;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

class DogDaoTest {

    @Test
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

}