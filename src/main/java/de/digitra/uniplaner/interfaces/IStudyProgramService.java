package de.digitra.uniplaner.interfaces;

import de.digitra.uniplaner.domain.StudyProgram;

import java.util.List;
import java.util.Optional;

public interface IStudyProgramService {

    StudyProgram save(StudyProgram studyprogram);

    void delete(Long id);

    List<StudyProgram> findAll();

    Optional<StudyProgram> findOne(Long id);
}
