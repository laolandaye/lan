package com.lan.jackson.propertyfilter;

import com.fasterxml.jackson.annotation.JsonFilter;

import java.io.Serializable;

@JsonFilter("propertyFilterMixIn")
public class PropertyFilterMixIn implements Serializable {

}
