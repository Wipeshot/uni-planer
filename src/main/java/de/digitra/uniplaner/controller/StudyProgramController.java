package de.digitra.uniplaner.controller;

import de.digitra.uniplaner.domain.StudyProgram;
import de.digitra.uniplaner.exceptions.BadRequestException;
import de.digitra.uniplaner.exceptions.ResourceNotFoundException;
import de.digitra.uniplaner.interfaces.IStudyProgramController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//Studiengänge
@RestController
@RequestMapping("/studyprograms")
public class StudyProgramController implements IStudyProgramController {
    @Override
    public ResponseEntity<StudyProgram> createStudyProgram(StudyProgram studyprogram) throws BadRequestException {
        return null;
    }

    @Override
    public ResponseEntity<StudyProgram> updateStudyProgram(StudyProgram studyprogram) throws BadRequestException {
        return null;
    }

    @Override
    public ResponseEntity<StudyProgram> updateStudyProgram(Long id, StudyProgram studyProgramDetails) throws ResourceNotFoundException {
        return null;
    }

    @Override
    public ResponseEntity<List<StudyProgram>> getAllStudyPrograms() {
        return null;
    }

    @Override
    public ResponseEntity<StudyProgram> getStudyProgram(Long id) throws ResourceNotFoundException {
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteStudyProgram(Long id) {
        return null;
    }

    @GetMapping("/studyprograms")
    public String getStudyProgram() {
        return "/studyprograms";
    }
}
