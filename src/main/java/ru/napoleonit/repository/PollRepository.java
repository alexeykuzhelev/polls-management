package ru.napoleonit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.napoleonit.model.Poll;

import java.util.List;
import java.util.Optional;

public interface PollRepository extends JpaRepository<Poll, Long> {

    Optional<Poll> findPollByPollName(String pollName);

    Optional<List<Poll>> findPollByActive(boolean active);
    
}
