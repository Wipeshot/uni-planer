package de.digitra.uniplaner.controller;

import de.digitra.uniplaner.domain.Lecture;
import de.digitra.uniplaner.exceptions.BadRequestException;
import de.digitra.uniplaner.exceptions.ResourceNotFoundException;
import de.digitra.uniplaner.interfaces.ILectureController;
import de.digitra.uniplaner.service.LectureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

//Vorlesung
@RestController
@RequestMapping("/lectures")
public class LectureController implements ILectureController {

    @Autowired
    LectureService lectureService;

    @Override
    public ResponseEntity<Lecture> createLecture(Lecture lecture) throws BadRequestException {
        if(lecture.getId() != null) {
            return  new ResponseEntity<>(HttpStatus.valueOf(400));
        }
        lectureService.save(lecture);
        return new ResponseEntity<>(lecture, HttpStatus.valueOf(200));
    }

    @Override
    public ResponseEntity<Lecture> updateLecture(Lecture lecture) throws BadRequestException {
        if(lecture.getId() == null) {
            return new ResponseEntity<>(HttpStatus.valueOf(400));
        }
        lectureService.delete(lecture.getId());
        lectureService.save(lecture);
        return new ResponseEntity<>(lecture, HttpStatus.valueOf(200));
    }

    @Override
    public ResponseEntity<Lecture> updatelecture(Long id, Lecture lectureDetails) throws ResourceNotFoundException {
        if(!lectureService.findOne(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.valueOf(404));
        }
        lectureService.delete(id);
        lectureDetails.setId(id);
        lectureService.save(lectureDetails);
        return new ResponseEntity<>(lectureDetails, HttpStatus.valueOf(200));
    }

    @Override
    public ResponseEntity<List<Lecture>> getAllLectures() {
        return new ResponseEntity<>(lectureService.findAll(), HttpStatus.valueOf(200));
    }

    @Override
    public ResponseEntity<Lecture> getLecture(Long id) throws ResourceNotFoundException {
        if(!lectureService.findOne(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.valueOf(404));
        }
        return new ResponseEntity<>(lectureService.findOne(id).orElse(new Lecture()), HttpStatus.valueOf(200));
    }

    @Override
    public ResponseEntity<Void> deleteLecture(Long id) {
        lectureService.delete(id);
        return new ResponseEntity<>(HttpStatus.valueOf(204));
    }
}
