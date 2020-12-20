package com.example.hackernewstest.comments.models;

import lombok.Data;
import java.util.ArrayList;

@Data
public class HNComment {
    String id;
    String by;
    ArrayList<String> kids;
    String parent;
    String text;
}
