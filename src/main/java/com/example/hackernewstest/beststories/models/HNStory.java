package com.example.hackernewstest.beststories.models;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;

@Data
@Entity
@Table(name = "tbl__story")
public class HNStory {
    @Id
    private String id;

    @Column
    private String title;

    @Column
    private String url;

    @Column
    private long score;

    @Column
    private long time;

    @Column
    private String by;

    private ArrayList<String> kids;
}
