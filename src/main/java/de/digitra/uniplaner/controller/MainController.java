package de.digitra.uniplaner.controller;

import de.digitra.uniplaner.domain.*;
import de.digitra.uniplaner.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

//Anfragen Controller
@Controller
@RequestMapping("/web")
public class MainController {

    private String lectureResource = "http://localhost:8080/lectures";
    private String lectureIdResource = "http://localhost:8080/lectures/{id}";
    private String lectureDateResource = "http://localhost:8080/lecturedates";
    private String lectureDateIdResource = "http://localhost:8080/lecturedates/{id}";
    private String lecturerResource = "http://localhost:8080/lecturers";
    private String lecturerIdResource = "http://localhost:8080/lecturers/{id}";
    private String semesterResource = "http://localhost:8080/semesters";
    private String semesterIdResource = "http://localhost:8080/semesters/{id}";
    private String studyClassResource = "http://localhost:8080/studyclasss";
    private String studyClassIdResource = "http://localhost:8080/studyclasss/{id}";
    private String studyProgramResource = "http://localhost:8080/studyprograms";
    private String studyProgramIdResource = "http://localhost:8080/studyprograms/{id}";


    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    StudyClassService studyClassService;

    @Autowired
    StudyProgramService studyProgramService;

    @Autowired
    LecturerService lecturerService;

    @Autowired
    LectureService lectureService;

    @Autowired
    SemesterService semesterService;

    @Autowired
    LectureDateService lectureDateService;

    @GetMapping("/lectures")
    public String getAlllectures(Model model) {
        try {
            ResponseEntity<Lecture[]> response =
                    this.restTemplate.getForEntity(lectureResource, Lecture[].class);
            model.addAttribute("lectures", response.getBody());
            return "lecture-list";
        } catch(Exception e){
            ResponseEntity<Lecture[]> response =
                    this.restTemplate.getForEntity(lectureResource, Lecture[].class);
            model.addAttribute("lectures", response.getBody());
            return "lecture-list2";
        }
    }

    @GetMapping("/lectures/create")
    public String createLecture(Model model) {
        model.addAttribute("lecture", new Lecture());
        model.addAttribute("studyPrograms", studyProgramService.findAll());
        return "create-lecture";
    }

    @PostMapping("/lectures")
    public String createLecture(@Valid Lecture lecture){
        ResponseEntity<Lecture> response = this.restTemplate.postForEntity(lectureResource, lecture, Lecture.class);
        return "redirect:/web/lectures";
    }

    @DeleteMapping("/lectures/{id}")
    public String deleteLecture(@PathVariable Long id) {
        String resourceById = lectureIdResource;
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<?> requestEntity = new HttpEntity<>( headers);
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", id.toString());
        ResponseEntity<Lecture> response = restTemplate.exchange(resourceById, HttpMethod.DELETE,requestEntity, Lecture.class,params);
        return "redirect:/web/lectures";

    }

    @GetMapping("/lectures/edit/{id}")
    public String updateLecture(@PathVariable Long id, Model model) {
        String resourceById = lectureIdResource;
        model.addAttribute("studyPrograms", studyProgramService.findAll());
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", id.toString());
        ResponseEntity<Lecture> response = restTemplate.getForEntity(resourceById, Lecture.class,params);
        model.addAttribute("lecture", response.getBody());
        return "update-lecture";
    }

    @PutMapping("/lectures/{id}")
    public String updateLecture(@PathVariable Long id, @Valid Lecture lecture, Errors errors) {
        if(errors.hasErrors()){
            return "update-lecture";
        }

        String resourceById = lectureResource;
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Lecture> requestEntity = new HttpEntity<>(lecture, headers);
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", id.toString());
        ResponseEntity<Lecture> response = restTemplate.exchange(resourceById,HttpMethod.PUT,requestEntity, Lecture.class,params);
        return "redirect:/web/lectures";
    }

    @GetMapping("/lecturedates")
    public String getAlllecturedates(Model model) {
        ResponseEntity<LectureDate[]> response =
                this.restTemplate.getForEntity(lectureDateResource, LectureDate[].class);
        model.addAttribute("lecturedates", response.getBody());
        return "lecturedate-list";
    }

    @GetMapping("/lecturedates/create")
    public String createLectureDate(Model model) {
        model.addAttribute("lecturedate", new LectureDate());
        model.addAttribute("lecturers", lecturerService.findAll());
        model.addAttribute("lectures", lectureService.findAll());
        model.addAttribute("semesters", semesterService.findAll());
        return "create-lecturedate";
    }

    @PostMapping("/lecturedates")
    public String createLectureDate(@Valid LectureDate lectureDate){
        ResponseEntity<LectureDate> response = this.restTemplate.postForEntity(lectureDateResource, lectureDate, LectureDate.class);
        return "redirect:/web/lecturedates";
    }

    @DeleteMapping("/lecturedates/{id}")
    public String deleteLectureDate(@PathVariable Long id) {
        String resourceById = lectureDateIdResource;
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<?> requestEntity = new HttpEntity<>( headers);
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", id.toString());
        ResponseEntity<LectureDate> response = restTemplate.exchange(resourceById,HttpMethod.DELETE,requestEntity, LectureDate.class,params);
        return "redirect:/web/lecturedates";

    }

    @GetMapping("/lecturedates/edit/{id}")
    public String updateLectureDate(@PathVariable Long id, Model model) {
        String resourceById = lectureDateIdResource;
        model.addAttribute("lecturers", lecturerService.findAll());
        model.addAttribute("lectures", lectureService.findAll());
        model.addAttribute("semesters", semesterService.findAll());
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", id.toString());
        ResponseEntity<LectureDate> response = restTemplate.getForEntity(resourceById, LectureDate.class,params);
        model.addAttribute("lecturedate", response.getBody());
        return "update-lecturedate";
    }

    @PutMapping("/lecturedates/{id}")
    public String updateLectureDate(@PathVariable Long id, @Valid LectureDate lecturedate, Errors errors) {
        if(errors.hasErrors()){
            return "update-lecturedate";
        }

        String resourceById = lectureDateResource;
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<LectureDate> requestEntity = new HttpEntity<>(lecturedate, headers);
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", id.toString());
        ResponseEntity<LectureDate> response = restTemplate.exchange(resourceById,HttpMethod.PUT,requestEntity, LectureDate.class,params);
        return "redirect:/web/lecturedates";
    }

    @GetMapping("/lecturers")
    public String getAlllecturers(Model model) {
        ResponseEntity<Lecturer[]> response =
                this.restTemplate.getForEntity(lecturerResource,Lecturer[].class);
        model.addAttribute("lecturers",response.getBody());
        model.addAttribute("studyPrograms", studyProgramService.findAll());
        return "lecturer-list";
    }

    @PostMapping("/lecturers")
    public String createLecturer(@Valid Lecturer lecturer){
        ResponseEntity<Lecturer> response = this.restTemplate.postForEntity(lecturerResource, lecturer, Lecturer.class);
        return "redirect:/web/lecturers";
    }

    @DeleteMapping("/lecturers/{id}")
    public String deleteLecturer(@PathVariable Long id) {
        String resourceById = lecturerIdResource;
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<?> requestEntity = new HttpEntity<>( headers);
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", id.toString());
        ResponseEntity<Lecturer> response = restTemplate.exchange(resourceById,HttpMethod.DELETE,requestEntity, Lecturer.class,params);
        return "redirect:/web/lecturers";
    }

    @GetMapping("/lecturers/create")
    public String createLecturer(Model model) {
        model.addAttribute("lecturer", new Lecturer());
        model.addAttribute("studyPrograms", studyProgramService.findAll());
        return "create-lecturer";
    }


    @GetMapping("/lecturers/edit/{id}")
    public String updateLecturer(@PathVariable Long id, Model model) {
        String resourceById = lecturerIdResource;
        model.addAttribute("studyPrograms", studyProgramService.findAll());
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", id.toString());
        ResponseEntity<Lecturer> response = restTemplate.getForEntity(resourceById, Lecturer.class,params);
        model.addAttribute("lecturer", response.getBody());
        return "update-lecturer";
    }

    @PutMapping("/lecturers/{id}")
    public String updateLecturer(@PathVariable Long id, @Valid Lecturer lecturer, Errors errors) {
        if(errors.hasErrors()){
            return "update-lecturer";
        }

        String resourceById = lecturerResource;
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Lecturer> requestEntity = new HttpEntity<>(lecturer, headers);
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", id.toString());
        ResponseEntity<Lecturer> response = restTemplate.exchange(resourceById,HttpMethod.PUT,requestEntity, Lecturer.class,params);
        return "redirect:/web/lecturers";
    }

    @GetMapping("/lecturers/dashboard/{id}")
    public String user(@PathVariable Long id, Model model)    {
        ResponseEntity<LectureDate[]> response =
                this.restTemplate.getForEntity(lectureDateResource, LectureDate[].class);
        model.addAttribute("lecturedates", response.getBody());
        return "lecture-list2";
    }

    @GetMapping("/semesters")
    public String getAllsemesters(Model model) {
        ResponseEntity<Semester[]> response =
                this.restTemplate.getForEntity(semesterResource,Semester[].class);
        model.addAttribute("semesters",response.getBody());
        return "semester-list";
    }

    @GetMapping("/semesters/create")
    public String createSemester(Model model) {
        model.addAttribute("semester", new Semester());
        model.addAttribute("studyClasss", studyClassService.findAll());
        return "create-semester";
    }

    @PostMapping("/semesters")
    public String createSemester(@Valid Semester semester){
        ResponseEntity<Semester> response = this.restTemplate.postForEntity(semesterResource, semester, Semester.class);
        return "redirect:/web/semesters";
    }

    @DeleteMapping("/semesters/{id}")
    public String deleteSemester(@PathVariable Long id) {
        String resourceById = semesterIdResource;
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<?> requestEntity = new HttpEntity<>( headers);
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", id.toString());
        ResponseEntity<Semester> response = restTemplate.exchange(resourceById,HttpMethod.DELETE,requestEntity, Semester.class,params);
        return "redirect:/web/semesters";
    }

    @GetMapping("/semesters/edit/{id}")
    public String updateSemester(@PathVariable Long id, Model model) {
        String resourceById = semesterIdResource;
        model.addAttribute("studyClasss", studyClassService.findAll());
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", id.toString());
        ResponseEntity<Semester> response = restTemplate.getForEntity(resourceById, Semester.class,params);
        model.addAttribute("semester", response.getBody());
        return "update-semester";
    }

    @PutMapping("/semesters/{id}")
    public String updateSemester(@PathVariable Long id, @Valid Semester semester, Errors errors) {
        if(errors.hasErrors()){
            return "update-semester";
        }

        String resourceById = semesterResource;
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Semester> requestEntity = new HttpEntity<>(semester, headers);
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", id.toString());
        ResponseEntity<Semester> response = restTemplate.exchange(resourceById,HttpMethod.PUT,requestEntity, Semester.class,params);
        return "redirect:/web/semesters";
    }

    @GetMapping("/studyclasss")
    public String getAllstudyclasss(Model model) {
        ResponseEntity<StudyClass[]> response =
                this.restTemplate.getForEntity(studyClassResource,StudyClass[].class);
        model.addAttribute("studyclasss",response.getBody());
        model.addAttribute("studyPrograms", studyProgramService.findAll());
        return "studyclass-list";
    }

    @PostMapping("/studyclasss")
    public String createStudyClass(@Valid StudyClass studyClass){
        ResponseEntity<StudyClass> response = this.restTemplate.postForEntity(studyClassResource, studyClass, StudyClass.class);
        return "redirect:/web/studyclasss";
    }

    @DeleteMapping("/studyclasss/{id}")
    public String deleteStudyClass(@PathVariable Long id) {
        String resourceById = studyClassIdResource;
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<?> requestEntity = new HttpEntity<>( headers);
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", id.toString());
        ResponseEntity<StudyClass> response = restTemplate.exchange(resourceById,HttpMethod.DELETE,requestEntity, StudyClass.class,params);
        return "redirect:/web/studyclasss";
    }

    @GetMapping("/studyclasss/create")
    public String createStudyClass(Model model) {
        model.addAttribute("studyclass", new StudyClass());
        model.addAttribute("studyPrograms", studyProgramService.findAll());
        return "create-studyclass";
    }


    @GetMapping("/studyclasss/edit/{id}")
    public String updateStudyClass(@PathVariable Long id, Model model) {
        String resourceById = studyClassIdResource;
        model.addAttribute("studyPrograms", studyProgramService.findAll());
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", id.toString());
        ResponseEntity<StudyClass> response = restTemplate.getForEntity(resourceById, StudyClass.class,params);
        model.addAttribute("studyclass", response.getBody());
        return "update-studyclass";
    }

    @PutMapping("/studyclasss/{id}")
    public String updateStudyClass(@PathVariable Long id, @Valid StudyClass studyClass, Errors errors) {
        if(errors.hasErrors()){
            return "update-studyclass";
        }

        String resourceById = studyClassResource;
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<StudyClass> requestEntity = new HttpEntity<>(studyClass, headers);
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", id.toString());
        ResponseEntity<StudyClass> response = restTemplate.exchange(resourceById,HttpMethod.PUT,requestEntity, StudyClass.class,params);
        return "redirect:/web/studyclasss";
    }

    @GetMapping("/studyprograms")
    public String getAllstudyprograms(Model model) {
        ResponseEntity<StudyProgram[]> response =
                this.restTemplate.getForEntity(studyProgramResource, StudyProgram[].class);
        model.addAttribute("studyprograms", response.getBody());
        return "studyprogram-list";
    }

    @GetMapping("/studyprograms/create")
    public String createStudyProgram(Model model) {
        model.addAttribute("studyprogram", new StudyProgram());
        return "create-studyprogram";
    }

    @PostMapping("/studyprograms")
    public String createStudyProgram(@Valid StudyProgram studyProgram){
        ResponseEntity<StudyProgram> response = this.restTemplate.postForEntity(studyProgramResource, studyProgram, StudyProgram.class);
        return "redirect:/web/studyprograms";
    }

    @GetMapping("/studyprograms/edit/{id}")
    public String updateStudyProgram(@PathVariable Long id, Model model) {
        String resourceById = studyProgramIdResource;
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", id.toString());
        ResponseEntity<StudyProgram> response = restTemplate.getForEntity(resourceById, StudyProgram.class,params);
        model.addAttribute("studyprogram", response.getBody());
        return "update-studyprogram";
    }

    @PutMapping("/studyprograms/{id}")
    public String updateStudyProgram(@PathVariable Long id, @Valid StudyProgram studyProgram, Errors errors) {
        if(errors.hasErrors()){
            return "update-studyprogram";
        }

        String resource = studyProgramResource;
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<StudyProgram> requestEntity = new HttpEntity<>(studyProgram, headers);
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", id.toString());
        ResponseEntity<StudyProgram> response = restTemplate.exchange(resource,HttpMethod.PUT,requestEntity, StudyProgram.class,params);
        return "redirect:/web/studyprograms";
    }

    @DeleteMapping("/studyprograms/{id}")
    public String deleteStudyProgram(@PathVariable Long id) {
        String resourceById = studyProgramIdResource;
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<?> requestEntity = new HttpEntity<>( headers);
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", id.toString());
        ResponseEntity<StudyProgram> response = restTemplate.exchange(resourceById,HttpMethod.DELETE,requestEntity, StudyProgram.class,params);
        return "redirect:/web/studyprograms";

    }
}