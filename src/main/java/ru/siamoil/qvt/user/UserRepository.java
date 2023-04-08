package ru.siamoil.qvt.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    @Query("select user from User user " +
            "where (:userUuids) is null or user.uuid in (:userUuids)")
    List<User> getUsersByUuids(List<UUID> userUuids);

    @Query("select user from User user " +
            "where (:userNames) is null or user.name in (:userNames)")
    List<User> getUsersByNames(List<String> userNames);
}
