package com.works.models;

import java.util.List;

@lombok.Data
public class Post {
    private long id;
    private String title;
    private String body;
    private List<String> tags;
    private Reactions reactions;
    private long views;
    private long userID;
}
