package com.example.leaflet_back_demo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payroll implements Serializable {
    private String name;
    private Integer id;
    private String date;
    private Integer attendance;
    private Integer rest;
    private Integer overtime;
    private Integer leavetime;
}
