package ru.r0bot.entity;


import org.springframework.context.annotation.ComponentScan;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "user_info")

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;


    @Column
    private String log;

    @Column
    private Date datetime;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return datetime;
    }

    public void setDate(Date date) {
        this.datetime = date;
    }


    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }




}
