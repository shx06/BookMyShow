package com.example.bookmyshow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
public class BaseModel {
    @Id
    private  int id;
    private Date createDate;
    private Date updateDate;
}
