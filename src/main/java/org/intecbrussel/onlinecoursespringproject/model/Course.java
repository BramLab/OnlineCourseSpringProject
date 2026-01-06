package org.intecbrussel.onlinecoursespringproject.model;

import java.time.LocalDateTime;

public class Course {
    long  id;
    String title;
    String description;
    User instructor;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
