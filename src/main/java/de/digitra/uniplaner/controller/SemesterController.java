package de.digitra.uniplaner.controller;

import de.digitra.uniplaner.domain.Semester;
import de.digitra.uniplaner.exceptions.BadRequestException;
import de.digitra.uniplaner.exceptions.ResourceNotFoundException;
import de.digitra.uniplaner.interfaces.ISemesterController;
import de.digitra.uniplaner.service.SemesterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    @Override
    public ResponseEntity<Semester> createSemester(Semester semester) throws BadRequestException {
        return null;
    }

    @Override
    public ResponseEntity<Semester> updateSemester(Semester semester) throws BadRequestException {
        return null;
    }

    @Override
    public ResponseEntity<Semester> updateSemester(Long id, Semester semesterDetails) throws ResourceNotFoundException {
        return null;
    }

    @Override
    public ResponseEntity<List<Semester>> getAllsemesters() {
        return null;
    }

    @Override
    public ResponseEntity<Semester> getSemester(Long id) throws ResourceNotFoundException {
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteSemester(Long id) {
        return null;
    }

    @GetMapping("/semesters")
    public String semesters() {return "/semesters";}
}
