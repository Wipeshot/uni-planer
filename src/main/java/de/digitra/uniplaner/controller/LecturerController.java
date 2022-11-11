package de.digitra.uniplaner.controller;

import de.digitra.uniplaner.domain.Lecturer;
import de.digitra.uniplaner.exceptions.BadRequestException;
import de.digitra.uniplaner.exceptions.DuplicateEmailException;
import de.digitra.uniplaner.exceptions.ResourceNotFoundException;
import de.digitra.uniplaner.interfaces.ILecturerController;
import de.digitra.uniplaner.service.LecturerService;
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

//Dozenten
@RestController
@RequestMapping("/lecturers")
public class LecturerController implements ILecturerController {

    @Autowired
    LecturerService lecturerService;

    @Override
    public ResponseEntity<Lecturer> createLecturer(Lecturer lecturer) throws BadRequestException, DuplicateEmailException {
        if(lecturer.getId() != null) {
            return new ResponseEntity<>(HttpStatus.valueOf(400));
        }
        boolean duplicatedEMail = false;
        for (Lecturer lec : lecturerService.findAll()) {
            if(lecturer.getId() == lec.getId()) {
                duplicatedEMail = false;
            }
        }
        if(duplicatedEMail) {
            return new ResponseEntity<>(HttpStatus.valueOf(400));
        }
        lecturerService.save(lecturer);
        return new ResponseEntity<>(lecturer, HttpStatus.valueOf(200));
    }

    @Override
    public ResponseEntity<Lecturer> updateLecturer(Lecturer lecturer) throws BadRequestException {
        if(lecturer.getId() == null) {
            return new ResponseEntity<>(HttpStatus.valueOf(400));
        }
        lecturerService.delete(lecturer.getId());
        lecturerService.save(lecturer);
        return new ResponseEntity<>(lecturer, HttpStatus.valueOf(200));
    }

    @Override
    public ResponseEntity<Lecturer> updateLecturer(Long id, Lecturer lecturerDetails) throws ResourceNotFoundException {
        if(!lecturerService.findOne(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.valueOf(404));
        }
        lecturerService.delete(id);
        lecturerDetails.setId(id);
        lecturerService.save(lecturerDetails);
        return new ResponseEntity<>(lecturerDetails, HttpStatus.valueOf(200));
    }

    @Override
    public ResponseEntity<List<Lecturer>> getAllLecturers() {
        return new ResponseEntity<>(lecturerService.findAll(), HttpStatus.valueOf(200));
    }

    @Override
    public ResponseEntity<Lecturer> getLecturer(Long id) throws ResourceNotFoundException {
        if(!lecturerService.findOne(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.valueOf(404));
        }
        return new ResponseEntity<>(lecturerService.findOne(id).orElse(new Lecturer()), HttpStatus.valueOf(200));
    }

    @Override
    public ResponseEntity<Void> deleteLecturer(Long id) {
        lecturerService.delete(id);
        return new ResponseEntity<>(HttpStatus.valueOf(204));
    }
}
