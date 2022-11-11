package de.digitra.uniplaner.controller;

import de.digitra.uniplaner.domain.Semester;
import de.digitra.uniplaner.exceptions.BadRequestException;
import de.digitra.uniplaner.exceptions.ResourceNotFoundException;
import de.digitra.uniplaner.interfaces.ISemesterController;
import de.digitra.uniplaner.service.SemesterService;
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

//Semester
@RestController
@RequestMapping("/semesters")
public class SemesterController implements ISemesterController {

    @Autowired
    SemesterService semesterService;
    @Override
    public ResponseEntity<Semester> createSemester(Semester semester) throws BadRequestException {
        if(semester.getId() != null) {
            return new ResponseEntity<>(HttpStatus.valueOf(400));
        }
        semesterService.save(semester);
        return new ResponseEntity<>(semester, HttpStatus.valueOf(200));
    }

    @Override
    public ResponseEntity<Semester> updateSemester(Semester semester) throws BadRequestException {
        if(semester.getId() == null) {
            return new ResponseEntity<>(HttpStatus.valueOf(400));
        }
        semesterService.delete(semester.getId());
        semesterService.save(semester);
        return new ResponseEntity<>(semester, HttpStatus.valueOf(200));
    }

    @Override
    public ResponseEntity<Semester> updateSemester(Long id, Semester semesterDetails) throws ResourceNotFoundException {
        if(!semesterService.findOne(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.valueOf(400));
        }
        semesterService.delete(id);
        semesterService.save(semesterDetails);
        return new ResponseEntity<>(semesterDetails, HttpStatus.valueOf(200));
    }

    @Override
    public ResponseEntity<List<Semester>> getAllsemesters() {
        return new ResponseEntity<>(semesterService.findAll(), HttpStatus.valueOf(200));
    }

    @Override
    public ResponseEntity<Semester> getSemester(Long id) throws ResourceNotFoundException {
        if(!semesterService.findOne(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.valueOf(400));
        }
        return new ResponseEntity<>(semesterService.findOne(id).orElse(new Semester()), HttpStatus.valueOf(200));
    }

    @Override
    public ResponseEntity<Void> deleteSemester(Long id) {
        if(!semesterService.findOne(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.valueOf(400));
        }
        semesterService.delete(id);
        return new ResponseEntity<>(HttpStatus.valueOf(200));
    }

}
