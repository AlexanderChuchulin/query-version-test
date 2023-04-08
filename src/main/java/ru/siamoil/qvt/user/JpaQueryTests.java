package ru.siamoil.qvt.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class JpaQueryTests {
    private final UserRepository userRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void doTests() {
        List<UUID> usersUuids = userRepository.findAll().stream().map(User::getUuid).toList();
        List<String> usersNames = userRepository.findAll().stream().map(User::getName).toList();

        log.info("getUsersByUuids null");
        try {
            log.info("{}", userRepository.getUsersByUuids(null));
        } catch (Exception exc) {
            log.info("getUsersByUuids null caused exception {}", exc.getMessage());
        }

        log.info("getUsersByNames null");
        try {
            log.info("{}", userRepository.getUsersByNames(null));
        } catch (Exception exc) {
            log.info("getUsersByNames null caused exception {}", exc.getMessage());
        }

        log.info("getUsersByUuids one UUID");
        try {
            log.info("{}", userRepository.getUsersByUuids(List.of(usersUuids.get(0))));
        } catch (Exception exc) {
            log.info("getUsersByUuids one UUID caused exception {}", exc.getMessage());
        }

        log.info("getUsersByNames one name");
        try {
            log.info("{}", userRepository.getUsersByNames(List.of(usersNames.get(0))));
        } catch (Exception exc) {
            log.info("getUsersByNames one name caused exception {}", exc.getMessage());
        }

        log.info("getUsersByUuids some UUIDS");
        try {
            log.info("{}", userRepository.getUsersByUuids(usersUuids));
        } catch (Exception exc) {
            log.info("getUsersByUuids some UUIDS caused exception {}", exc.getMessage());
        }

        log.info("getUsersByNames some names");
        try {
            log.info("{}", userRepository.getUsersByNames(usersNames));
        } catch (Exception exc) {
            log.info("getUsersByNames some names caused exception {}", exc.getMessage());
        }
    }
}
