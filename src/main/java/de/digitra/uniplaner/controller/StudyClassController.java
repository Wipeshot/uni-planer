package de.digitra.uniplaner.controller;

import de.digitra.uniplaner.domain.StudyClass;
import de.digitra.uniplaner.exceptions.BadRequestException;
import de.digitra.uniplaner.exceptions.ResourceNotFoundException;
import de.digitra.uniplaner.interfaces.IStudyClassController;
import de.digitra.uniplaner.service.StudyClassService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;


//Kurse
@RestController
@RequestMapping("/studyclasss")
public class StudyClassController implements IStudyClassController {
    @Override
    public ResponseEntity<StudyClass> createStudyClass(StudyClass studyclass) throws BadRequestException {
        return null;
    }

    @Override
    public ResponseEntity<StudyClass> updateStudyClass(StudyClass studyclass) throws BadRequestException {
        return null;
    }

    @Override
    public ResponseEntity<StudyClass> updateStudyClass(Long id, StudyClass studyclassDetails) throws ResourceNotFoundException {
        return null;
    }

    @Override
    public ResponseEntity<List<StudyClass>> getAllstudyclasss() {
        return null;
    }

    @Override
    public ResponseEntity<StudyClass> getStudyClass(Long id) throws ResourceNotFoundException {
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteStudyClass(Long id) {
        return null;
    }

    @GetMapping("/studyclasss")
    public String studyclass() {
        return "/studyclasss";
    }
}
