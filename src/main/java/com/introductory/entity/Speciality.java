package com.introductory.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Setter
@Getter
@Entity
public class Speciality {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String specialityName;
}
