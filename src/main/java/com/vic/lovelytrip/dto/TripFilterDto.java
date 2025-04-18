package com.vic.lovelytrip.dto;

public class TripFilterDto {


    // Search by title, destination, or description
    private String keywords;

    private String destination;

    private int minDuration;

    private int maxDuration;

    // Filter by trip days
    private String sort;

//    | `keyword`     | string  | Search by title, destination, or description     | `aurora`           |
//            | `destination` | string  | Filter by destination                            | `Lapland`          |
//            | `supplierId`  | long    | Filter by supplier                               | `5`                |
//            | `status`      | int     | Filter by trip status (1 = active, etc.)         | `1`                |
//            | `minDuration` | int     | Filter by trip days ≥                            | `3`                |
//            | `maxDuration` | int     | Filter by trip days ≤                            | `7`                |
//            | `sort`        | string  | Sort field, e.g. `createdTime`, `title`          | `title`            |
//            | `order`       | string  | `asc` or `desc`                                  | `asc`              |
//            | `page`        | int     | Page number (0-based)                            | `0`                |
//            | `size`        | int

}
