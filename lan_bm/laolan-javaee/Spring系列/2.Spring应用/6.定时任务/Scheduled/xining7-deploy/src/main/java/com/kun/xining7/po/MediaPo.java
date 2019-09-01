package com.kun.xining7.po;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by lan_jiaxing on 2018/7/10 0010.
 */
@Entity
@Table(name = "tb_media")
public class MediaPo implements Serializable {
    private Integer id;
    private String name;
    private String url;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "url")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public MediaPo() {
    }

    public MediaPo(String name, String url) {
        this.name = name;
        this.url = url;
    }
}
