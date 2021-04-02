package model;

import dao.DogDao;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DogTest {

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
    void raceShouldBeNotNullAndThrowsException(){
        //given
        Dog dog = new Dog("Bambo", 2, "Atreju", 4, true, null);
        DogDao dao = new DogDao();
        //when
        //then
        assertThrows(IllegalStateException.class, () -> dao.saveOrUpdate(dog));
    }

    @Test
    void referencesToTheSameObjectShouldBeEqual(){
        //given
        Dog dog = new Dog("Burek", 12, "Andrzej", 23, true, Race.BERNARDINE);
        Dog dog1 = dog;
        //then
        assertSame(dog, dog1);
    }


}