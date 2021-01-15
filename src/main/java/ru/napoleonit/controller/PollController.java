package ru.napoleonit.controller;

import ru.napoleonit.model.Poll;
import ru.napoleonit.service.PollService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/polls")
@AllArgsConstructor @NoArgsConstructor
@Log
public class PollController {

    private PollService pollService;

    @Autowired
    public void setPollService(PollService pollService) {
        this.pollService = pollService;
    }

    @PostMapping(value = "/savePolls",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> savePolls(@RequestBody Poll poll) {
        return new ResponseEntity<>(pollService.savePoll(poll), HttpStatus.CREATED);
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAllPolls() {
        return new ResponseEntity<>(pollService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/findPollByPollName")
    public ResponseEntity<?> findPollByPollName(@RequestParam String pollname) {
        return new ResponseEntity<>(pollService.findPollByPollName(pollname), HttpStatus.OK);
    }

    @GetMapping("/findPollByActive")
    public ResponseEntity<?> findPollByActive(@RequestParam boolean active) {
        return new ResponseEntity<>(pollService.findPollByActive(active), HttpStatus.OK);
    }

    @GetMapping(value = "/findPollById/{pollId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findPollById(@PathVariable Long pollId) {
        return new ResponseEntity<>(pollService.findPollById(pollId), HttpStatus.OK);
    }

    @PutMapping(value = "/updatePoll/{pollId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updatePoll(@PathVariable Long pollId, @Valid @RequestBody Poll newPoll) {
        if (pollId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(pollService.updatePoll(pollId, newPoll), HttpStatus.OK);
    }

    @DeleteMapping(value = "/deletePoll/{pollId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deletePoll(@PathVariable Long pollId) {
        if (pollId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(pollService.deletePoll(pollId),HttpStatus.OK);
    }

}
