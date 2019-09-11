package com.introductory.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Entity
public class Speciality {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Please fill the message")
    @Length(max = 2048, message = "Speciality too long (more than 2kB)")
    private String description;
    @Length(max = 255, message = "Speciality too long (more than 255)")
    private String specialityName;

    private String filename;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    public Speciality() {
    }

    public Speciality(String description, String specialityName, User author) {
        this.author = author;
        this.description = description;
        this.specialityName = specialityName;
    }

    public String getAuthorName() {
        return author !=null ? author.getUsername() : "<no author>";
    }
}
