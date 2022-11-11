package de.digitra.uniplaner.controller;

import de.digitra.uniplaner.domain.StudyProgram;
import de.digitra.uniplaner.exceptions.BadRequestException;
import de.digitra.uniplaner.exceptions.ResourceNotFoundException;
import de.digitra.uniplaner.interfaces.IStudyProgramController;
import de.digitra.uniplaner.service.StudyProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//Studiengänge
@RestController
@RequestMapping("/studyprograms")
public class StudyProgramController implements IStudyProgramController {

    @Autowired
    StudyProgramService studyProgramService;

    @Override
    public ResponseEntity<StudyProgram> createStudyProgram(StudyProgram studyprogram) throws BadRequestException {
        if(studyprogram.getId() != null) {
            return new ResponseEntity<>(HttpStatus.valueOf(400));
        }
        studyProgramService.save(studyprogram);
        return new ResponseEntity<>(studyprogram, HttpStatus.valueOf(200));
    }

    @Override
    public ResponseEntity<StudyProgram> updateStudyProgram(StudyProgram studyprogram) throws BadRequestException {
        if(studyprogram.getId() == null) {
            return new ResponseEntity<>(HttpStatus.valueOf(400));
        }
        studyProgramService.delete(studyprogram.getId());
        studyProgramService.save(studyprogram);
        return new ResponseEntity<>(studyprogram, HttpStatus.valueOf(200));
    }

    @Override
    public ResponseEntity<StudyProgram> updateStudyProgram(Long id, StudyProgram studyProgramDetails) throws ResourceNotFoundException {
        if(!studyProgramService.findOne(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.valueOf(400));
        }
        Optional<StudyProgram> programToUpdate = studyProgramService.findOne(id);
        StudyProgram toUpdate = programToUpdate.orElse(new StudyProgram());
        studyProgramService.delete(toUpdate.getId());
        studyProgramService.save(toUpdate);
        return new ResponseEntity<>(toUpdate, HttpStatus.valueOf(200));
    }

    @Override
    public ResponseEntity<List<StudyProgram>> getAllStudyPrograms() {
        return new ResponseEntity<>(studyProgramService.findAll(), HttpStatus.valueOf(200));
    }

    @Override
    public ResponseEntity<StudyProgram> getStudyProgram(Long id) throws ResourceNotFoundException {
        if(!studyProgramService.findOne(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.valueOf(404));
        }
        StudyProgram program = studyProgramService.findOne(id).orElse(new StudyProgram());
        return new ResponseEntity<>(program, HttpStatus.valueOf(200));
    }

    @Override
    public ResponseEntity<Void> deleteStudyProgram(Long id) {
        if(!studyProgramService.findOne(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.valueOf(400));
        }
        studyProgramService.delete(id);
        return new ResponseEntity<>(HttpStatus.valueOf(204));
    }

}
