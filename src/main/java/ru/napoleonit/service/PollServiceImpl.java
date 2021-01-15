package ru.napoleonit.service;

import ru.napoleonit.exception.DataNotFoundException;
import ru.napoleonit.model.Poll;
import ru.napoleonit.model.Question;
import ru.napoleonit.repository.PollRepository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;

@Service
@Transactional
@AllArgsConstructor @NoArgsConstructor
@Log
public class PollServiceImpl implements PollService {

    private PollRepository pollRepository;

    @Autowired
    public void setPollRepository(PollRepository pollRepository) {
        this.pollRepository = pollRepository;
    }

    @Override
    public Poll savePoll(Poll poll) {
        validatePoll(poll);
        Poll savePoll = pollRepository.save(poll);
        log.info("Poll successfully saved. Poll details: " + savePoll);
        return savePoll;
    }

    private void validatePoll(Poll poll) throws DataNotFoundException {
        if (isNull(poll)) {
            throw new DataNotFoundException("Объект Poll == null");
        }
        if (isNull(poll.getPollName()) || poll.getPollName().isEmpty()) {
            throw new DataNotFoundException("Название опроса не задано");
        }
    }

    @Override
    public List<Poll> findAll() {
        return pollRepository.findAll();
    }

    @Override
    public Poll findPollByPollName(String pollName) {
        Poll poll = pollRepository.findPollByPollName(pollName)
                .orElseThrow(() -> new DataNotFoundException("Опрос с таким названием не найден"));
        log.info("Poll successfully loaded. Poll details: " + poll);
        return poll;
    }

    @Override
    public List<Poll> findPollByActive(boolean active) {
        List<Poll> listPoll = pollRepository.findPollByActive(active)
                .filter(list -> !list.isEmpty())
                .orElseThrow(() -> new DataNotFoundException("Опросы с таким статусом не найдены"));
        log.info("Polls successfully loaded. Polls details: " + listPoll);
        return listPoll;
    }

    @Override
    public Poll findPollById(Long pollId) {
        Poll poll = pollRepository.findById(pollId)
        .orElseThrow(() -> new DataNotFoundException("Опрос с таким id не найден"));
        log.info("Poll successfully loaded. Poll details: " + poll);
        return poll;
    }

    @Override
    public Poll updatePoll(Long pollId, Poll newPoll) {
        Poll updatePoll = pollRepository.findById(pollId).map(poll -> {
            poll.setPollName(newPoll.getPollName());
            poll.setDateBegin(newPoll.getDateBegin());
            poll.setDateEnd(newPoll.getDateEnd());
            poll.setActive(newPoll.isActive());
            List<Question> questionsList = newPoll.getQuestions();
            if (!questionsList.isEmpty()) {
                poll.setQuestions(questionsList);
            }
            return pollRepository.save(poll);
        }).orElseThrow(() -> new DataNotFoundException("Опрос не обновлен"));
        log.info("Poll successfully update. Poll details: " + updatePoll);
        return updatePoll;
    }

    @Override
    public String deletePoll(Long pollId) {
        Optional<Poll> poll = pollRepository.findById(pollId);
        if (poll.isPresent()) {
            pollRepository.deleteById(pollId);
            log.info("Poll successfully remove. Poll details: " + poll.get());
            return "Опрос удален";
        }
        return "Опрос не удален";
    }

}
