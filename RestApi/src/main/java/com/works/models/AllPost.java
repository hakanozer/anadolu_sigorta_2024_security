package com.works.models;

import java.util.List;

@lombok.Data
public class AllPost {
    private List<Post> posts;
    private long total;
    private long skip;
    private long limit;
}
