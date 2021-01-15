package ru.napoleonit.service;

import ru.napoleonit.model.Poll;

import java.util.List;

public interface PollService {

    List<Poll> findAll();

    Poll savePoll(Poll poll);

    Poll findPollByPollName(String pollName);

    List<Poll> findPollByActive(boolean active);

    Poll findPollById(Long pollId);

    Poll updatePoll(Long pollId, Poll newPoll);

    String deletePoll(Long pollId);

}
