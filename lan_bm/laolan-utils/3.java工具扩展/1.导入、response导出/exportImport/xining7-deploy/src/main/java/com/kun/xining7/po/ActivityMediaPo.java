package com.kun.xining7.po;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by lan_jiaxing on 2018/7/10 0010.
 */
@Entity
@Table(name = "tb_activity_media")
public class ActivityMediaPo implements Serializable {
    private Integer id;
    private String name;
    private String url;
    private Integer activityId;

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


    @Basic
    @Column(name = "activity_id")
    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

}
