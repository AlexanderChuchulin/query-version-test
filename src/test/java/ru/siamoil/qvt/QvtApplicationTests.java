package ru.siamoil.qvt;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.siamoil.qvt.user.User;
import ru.siamoil.qvt.user.UserRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class QvtApplicationTests {
    @Autowired
    public UserRepository userRepository;

    @Test
    @Order(0)
    void contextLoads() {
    }


    @Test
    @Order(1)
    void getUsersByNullUuidQuery() {
        List<User> usersCheckList = new ArrayList<>(userRepository.findAll());

        assertTrue(usersCheckList.size() > 1, "usersCheckList size less than 2");

        assertEquals(usersCheckList, userRepository.getUsersByUuids(null),
                "Passing a null argument, the user lists are not equal");
    }

    @Test
    @Order(2)
    void getUsersByNullNameQueryMod() {
        List<User> usersCheckList = new ArrayList<>(userRepository.findAll());

        assertTrue(usersCheckList.size() > 1, "usersCheckList size less than 2");

        assertEquals(usersCheckList, userRepository.getUsersByNames(null),
                "Passing a null argument, the user lists are not equal");
    }

    @Test
    @Order(3)
    void getUsersByOneUuidQuery() {
        List<User> usersCheckList = List.of(userRepository.findAll().get(0));

        assertEquals(usersCheckList, userRepository.getUsersByUuids(usersCheckList.stream().map(User::getUuid).toList()),
                "When passing a list with one UUID, the user lists are not equal");
    }

    @Test
    @Order(4)
    void getUsersByOneNameQuery() {
        List<User> usersCheckList = List.of(userRepository.findAll().get(0));

        assertEquals(usersCheckList, userRepository.getUsersByNames(usersCheckList.stream().map(User::getName).toList()),
                "When passing a list with one name, the user lists are not equal");
    }

    @Test
    @Order(5)
    void getUsersBySomeUuidQuery() {
        List<User> usersCheckList = userRepository.findAll();

        assertTrue(usersCheckList.size() > 1, "usersCheckList size less than 2");

        assertEquals(usersCheckList, userRepository.getUsersByUuids(usersCheckList.stream().map(User::getUuid).toList()),
                "When passing a list with some UUID, the user lists are not equal");
    }

    @Test
    @Order(6)
    void getUsersBySomeNameQuery() {
        List<User> usersCheckList = userRepository.findAll();

        assertTrue(usersCheckList.size() > 1, "usersCheckList size less than 2");

        assertEquals(usersCheckList, userRepository.getUsersByNames(usersCheckList.stream().map(User::getName).toList()),
                "When passing a list with some names, the user lists are not equal");
    }
}
