package de.digitra.uniplaner.controller;

import de.digitra.uniplaner.domain.LectureDate;
import de.digitra.uniplaner.exceptions.BadRequestException;
import de.digitra.uniplaner.exceptions.ResourceNotFoundException;
import de.digitra.uniplaner.interfaces.ILectureDateController;
import de.digitra.uniplaner.service.LectureDateService;
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

//Vorlesungstermine
@RestController
@RequestMapping("/lecturedates")
public class LectureDateController implements ILectureDateController {

    @Autowired
    LectureDateService lectureDateService;

    @Override
    public ResponseEntity<LectureDate> createLectureDate(LectureDate lecturedate) throws BadRequestException {
        if(lecturedate.getId() != null) {
            return new ResponseEntity<>(HttpStatus.valueOf(400));
        }
        lectureDateService.save(lecturedate);
        return new ResponseEntity<>(lecturedate, HttpStatus.valueOf(200));
    }

    @Override
    public ResponseEntity<LectureDate> updateLectureDate(LectureDate lecturedate) throws BadRequestException {
        if(lecturedate.getId() == null) {
            return new ResponseEntity<>(HttpStatus.valueOf(400));
        }
        lectureDateService.delete(lecturedate.getId());
        lectureDateService.save(lecturedate);
        return new ResponseEntity<>(lecturedate, HttpStatus.valueOf(200));
    }

    @Override
    public ResponseEntity<LectureDate> updateLectureDate(Long id, LectureDate lecturedateDetails) throws ResourceNotFoundException {
        if(lectureDateService.findOne(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.valueOf(404));
        }
        lectureDateService.delete(id);
        lecturedateDetails.setId(id);
        lectureDateService.save(lecturedateDetails);
        return new ResponseEntity<>(lecturedateDetails, HttpStatus.valueOf(200));
    }

    @Override
    public ResponseEntity<List<LectureDate>> getAlllecturedates() {
        return new ResponseEntity<>(lectureDateService.findAll(), HttpStatus.valueOf(200));
    }

    @Override
    public ResponseEntity<LectureDate> getLectureDate(Long id) throws ResourceNotFoundException {
        if(!lectureDateService.findOne(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.valueOf(404));
        }
        return new ResponseEntity<>(lectureDateService.findOne(id).orElse(new LectureDate()), HttpStatus.valueOf(200));
    }

    @Override
    public ResponseEntity<Void> deleteLectureDate(Long id) {
        lectureDateService.delete(id);
        return new ResponseEntity<>(HttpStatus.valueOf(204));
    }
}
