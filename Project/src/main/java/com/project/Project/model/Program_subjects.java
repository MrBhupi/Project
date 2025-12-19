package com.project.Project.model;


import jakarta.persistence.*;

@Entity
@Table(name = "programs_subjects")
public class Program_subjects {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long program_subjectid;

    /* FK → programs.id */
    @ManyToOne
    @JoinColumn(name = "program_id", nullable = false)
    private Programs program;

    /* FK → subjects.id */
    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    private Subjects subject;

    private Integer semester;

    /* Getters and Setters */

    public Long getProgram_subjectid() {
        return program_subjectid;
    }

    public void setProgram_subjectid(Long program_subjectid) {
        this.program_subjectid = program_subjectid;
    }


    public Programs getProgram() {
        return program;
    }



    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public void setProgram(Integer programId) {

    }

    public void setSubject(Integer subjectId) {

    }
}
