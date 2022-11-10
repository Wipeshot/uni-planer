package de.digitra.uniplaner.controller;

import de.digitra.uniplaner.domain.Lecturer;
import de.digitra.uniplaner.exceptions.BadRequestException;
import de.digitra.uniplaner.exceptions.DuplicateEmailException;
import de.digitra.uniplaner.exceptions.ResourceNotFoundException;
import de.digitra.uniplaner.interfaces.ILecturerController;
import de.digitra.uniplaner.service.LecturerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    @Override
    public ResponseEntity<Lecturer> createLecturer(Lecturer lecturer) throws BadRequestException, DuplicateEmailException {
        return null;
    }

    @Override
    public ResponseEntity<Lecturer> updateLecturer(Lecturer lecturer) throws BadRequestException {
        return null;
    }

    @Override
    public ResponseEntity<Lecturer> updateLecturer(Long id, Lecturer lecturerDetails) throws ResourceNotFoundException {
        return null;
    }

    @Override
    public ResponseEntity<List<Lecturer>> getAlllecturers() {
        return null;
    }

    @Override
    public ResponseEntity<Lecturer> getLecturer(Long id) throws ResourceNotFoundException {
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteLecturer(Long id) {
        return null;
    }
}
