package com.lan._1utils.maven._1mapobj;

import java.io.Serializable;

/**
 * vo
 * @author ouyangjun
 */
public class PhoneVO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String plate;
    private Long number;
    private Double memory;
    private Double size;

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public Double getMemory() {
        return memory;
    }

    public void setMemory(Double memory) {
        this.memory = memory;
    }

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "PhoneVO{" +
                "plate='" + plate + '\'' +
                ", number=" + number +
                ", memory=" + memory +
                ", size=" + size +
                '}';
    }

    public PhoneVO() {
    }

    public PhoneVO(String plate, Long number, Double memory, Double size) {
        this.plate = plate;
        this.number = number;
        this.memory = memory;
        this.size = size;
    }
}

