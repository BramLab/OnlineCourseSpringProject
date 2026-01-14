package org.intecbrussel.onlinecoursespringproject.dto;

import java.util.Date;

public record CourseRequest(
        long id,
        String title,
        String description,
        long instructorId,
        Date startDate,
        Date endDate
) {}
