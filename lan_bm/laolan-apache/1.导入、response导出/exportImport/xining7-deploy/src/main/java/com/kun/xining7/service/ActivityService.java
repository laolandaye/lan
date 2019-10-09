package com.kun.xining7.service;

import com.kun.xining7.po.vo.ActivityQueryVo;
import com.kun.xining7.po.vo.BaseResultVo;
import com.kun.xining7.po.ActivityPo;
import com.kun.xining7.po.MediaPo;
import com.kun.xining7.po.vo.E3ResultVo;

import java.util.List;

public interface ActivityService {


    BaseResultVo queryAllActivities(String type,String  date,String  key, int pageNo, int pageSize);

    BaseResultVo queryActivity(Integer activityId);

    BaseResultVo queryActivityMediaYn(Integer activityId);

    BaseResultVo queryMedia();

    ActivityPo addActivity(ActivityQueryVo activityQueryVo) throws Exception ;

    ActivityPo updateActivity(ActivityQueryVo activityQueryV) throws Exception ;

    MediaPo addMedia(String fileName, String pathNameTemp)  throws Exception ;

    E3ResultVo addMediaes(List<MediaPo> mediaes)  throws Exception ;

    void deleteActivity(String id)  throws Exception;
}
