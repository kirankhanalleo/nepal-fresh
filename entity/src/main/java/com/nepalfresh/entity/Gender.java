package com.nepalfresh.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "genders")
public class Gender extends AbstractEntity  {

    @Column(name = "gender", nullable = false)
    private String gender;
}
