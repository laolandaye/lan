package com.lan.jackson;

import com.fasterxml.jackson.annotation.JsonFilter;

import java.io.Serializable;

@JsonFilter("afareFlightFilter")
public class AfareFlight implements Serializable {

    private static final long serialVersionUID = -5691992455196596940L;

    String carrier;
    String flightNumber;
    String depAirport;
    String depTime;
    String arrAirport;
    String arrTime;
    String stopCities;
    boolean codeshare;
    String cabin;

    /* 新增字段：舱位等级 1 经济舱,2 商务舱,3 头等舱,*/
    int cabinClass = 0;

    String aircraftCode;

    /**
     * 当前航段所属航程序号，从1开始
     * 例如多程第一段：A-B-C；第二段：D-F
     * 则航段A-B B-C的tripIndex值为1，D-F的tripIndex值为2
     */
    int group;
}
