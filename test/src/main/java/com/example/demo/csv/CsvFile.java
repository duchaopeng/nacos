package com.example.demo.csv;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

@Data
public class CsvFile {

    @CsvBindByName(column = "name")
    private String name;

    @CsvBindByName(column = "title")
    private String title;

    @CsvBindByName(column = "number")
    private String number;

    @CsvBindByName(column = "type")
    private String type;

    @CsvBindByName(column = "personnel")
    private String personnel;

    @CsvBindByName(column = "time")
    private String time;
}