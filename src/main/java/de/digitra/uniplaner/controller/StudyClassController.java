package de.digitra.uniplaner.controller;

import de.digitra.uniplaner.domain.StudyClass;
import de.digitra.uniplaner.exceptions.BadRequestException;
import de.digitra.uniplaner.exceptions.ResourceNotFoundException;
import de.digitra.uniplaner.interfaces.IStudyClassController;
import de.digitra.uniplaner.service.StudyClassService;
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


//Kurse
@RestController
@RequestMapping("/studyclasss")
public class StudyClassController implements IStudyClassController {

    @Autowired
    StudyClassService studyClassService;

    @Override
    public ResponseEntity<StudyClass> createStudyClass(StudyClass studyclass) throws BadRequestException {
        if(studyclass.getId() != null) {
            return new ResponseEntity<>(HttpStatus.valueOf(400));
        }
        studyClassService.save(studyclass);
        return new ResponseEntity<>(studyclass, HttpStatus.valueOf(200));
    }

    @Override
    public ResponseEntity<StudyClass> updateStudyClass(StudyClass studyclass) throws BadRequestException {
        if(studyclass.getId() == null) {
            return new ResponseEntity<>(HttpStatus.valueOf(400));
        }
        studyClassService.delete(studyclass.getId());
        studyClassService.save(studyclass);
        return new ResponseEntity<>(studyclass, HttpStatus.valueOf(200));
    }

    @Override
    public ResponseEntity<StudyClass> updateStudyClass(Long id, StudyClass studyclassDetails) throws ResourceNotFoundException {
        if(!studyClassService.findOne(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.valueOf(404));
        }
        studyClassService.delete(id);
        studyClassService.save(studyclassDetails);
        return new ResponseEntity<>(studyclassDetails, HttpStatus.valueOf(200));
    }

    @Override
    public ResponseEntity<List<StudyClass>> getAllStudyClass() {
        return new ResponseEntity<>(studyClassService.findAll(), HttpStatus.valueOf(200));
    }

    @Override
    public ResponseEntity<StudyClass> getStudyClass(Long id) throws ResourceNotFoundException {
        if(!studyClassService.findOne(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.valueOf(404));
        }
        return new ResponseEntity<>(studyClassService.findOne(id).orElse(new StudyClass()), HttpStatus.valueOf(200));
    }

    @Override
    public ResponseEntity<Void> deleteStudyClass(Long id) {
        if(!studyClassService.findOne(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.valueOf(404));
        }
        studyClassService.delete(id);
        return new ResponseEntity<>(HttpStatus.valueOf(204));
    }

}
