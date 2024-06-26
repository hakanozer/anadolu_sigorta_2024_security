package com.works.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Note extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nid;

    private String title;
    private String content;

}
