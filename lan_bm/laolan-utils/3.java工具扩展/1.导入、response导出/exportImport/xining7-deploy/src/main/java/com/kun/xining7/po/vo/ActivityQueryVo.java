package com.kun.xining7.po.vo;

import com.kun.xining7.po.ActivityMediaPo;
import com.kun.xining7.po.ActivityPo;
import com.kun.xining7.po.MediaPo;

import java.io.Serializable;
import java.util.List;

public class ActivityQueryVo extends ActivityPo {

    private List<ActivityMediaPo> mediaes;//媒体库

    public List<ActivityMediaPo> getMediaes() {
        return mediaes;
    }

    public void setMediaes(List<ActivityMediaPo> mediaes) {
        this.mediaes = mediaes;
    }
}
