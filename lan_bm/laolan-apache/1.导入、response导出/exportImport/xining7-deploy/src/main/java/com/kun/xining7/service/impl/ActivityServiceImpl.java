package com.kun.xining7.service.impl;

import com.kun.utils.PageBean;
import com.kun.xining7.po.ActivityMediaPo;
import com.kun.xining7.po.vo.ActivityQueryVo;
import com.kun.xining7.po.vo.BaseResultVo;
import com.kun.xining7.dao.ActivityDao;
import com.kun.xining7.dao.ActivityJpaDao;
import com.kun.xining7.dao.ActivityMediaJpaDao;
import com.kun.xining7.dao.MediaJpaDao;
import com.kun.xining7.po.ActivityPo;
import com.kun.xining7.po.MediaPo;
import com.kun.xining7.po.vo.E3ResultVo;
import com.kun.xining7.service.ActivityService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Resource
    private ActivityJpaDao activityJpaDao;

    @Resource
    private ActivityMediaJpaDao activityMediaJpaDao;

    @Resource
    private MediaJpaDao mediaJpaDao;

    @Resource
    private ActivityDao activityDao;

    @Override
    public BaseResultVo queryAllActivities(String type,String date,String  key,int pageNo, int pageSize) {
        BaseResultVo brv = new BaseResultVo();
//        PageRequest pageable = new PageRequest((pageNo - 1), pageSize);
//        Page<ActivityPo> page = activityJpaDao.findAll(pageable);
//        brv.setData(page.getContent());
//        brv.setPage(page);
        PageBean<Map<String, Object>> pageBean = activityDao.findAllActivities( type, date, key, pageNo, pageSize);
        brv.setData(pageBean.getDataModel());
        brv.setPageBean(pageBean);
        return brv;
    }

    @Override
    public BaseResultVo queryActivity(Integer activityId) {
        BaseResultVo brv = new BaseResultVo();
        List list = activityDao.findActivityByActivityIdYn(activityId);
        brv.setData(list.size() != 0 ? list.get(0) : null);
        return brv;
    }

    @Override
    public BaseResultVo queryActivityMediaYn(Integer activityId) {
        BaseResultVo brv = new BaseResultVo();
        brv.setData(activityDao.findActivityMediaByActivityIdYn(activityId));
        return brv;
    }

    @Override
    public BaseResultVo queryMedia() {
        BaseResultVo brv = new BaseResultVo();
        brv.setData(activityDao.findMedia());
        return brv;
    }

    @Transactional
    @Override
    public ActivityPo addActivity(ActivityQueryVo activityQueryVo) throws Exception {
        ActivityPo activityPo = new ActivityPo();//上转型对象：所以子类的值可以直接赋值给父类
        activityPo.setContent(activityQueryVo.getContent());
        activityPo.setCoverImg(activityQueryVo.getCoverImg());
        activityPo.setCreateTime(activityQueryVo.getCreateTime());
        activityPo.setDate(activityQueryVo.getDate());
        activityPo.setHost(activityQueryVo.getHost());
        activityPo.setPopular(activityQueryVo.getPopular());
        activityPo.setTitle(activityQueryVo.getTitle());
        activityPo.setType(activityQueryVo.getType());
        Date createDate = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd");
        activityPo.setCreateTime(simpleDateFormat.format(createDate));
        //1.执行保存操作tb_activity
        ActivityPo activity = activityJpaDao.save(activityPo);
        //2.执行保存操作tb_activity_media
        Integer activityId = activity.getId();
        //2.1 先删除原有 activity_id
        activityMediaJpaDao.deleteMediaByActivityId(activityId);
        //2.2 再新增
        List<ActivityMediaPo> activityMediaes = activityQueryVo.getMediaes();
        for (ActivityMediaPo activityMediae : activityMediaes) {
            activityMediae.setActivityId(activityId);
        }
        activityMediaJpaDao.save(activityMediaes);
        return activity;
    }

    @Transactional
    @Override
    public ActivityPo updateActivity(ActivityQueryVo activityQueryVo) throws Exception {
        ActivityPo activityPo = new ActivityPo();
        activityPo.setId(activityQueryVo.getId());
        activityPo.setContent(activityQueryVo.getContent());
        activityPo.setCoverImg(activityQueryVo.getCoverImg());
        activityPo.setCreateTime(activityQueryVo.getCreateTime());
        activityPo.setDate(activityQueryVo.getDate());
        activityPo.setHost(activityQueryVo.getHost());
        activityPo.setPopular(activityQueryVo.getPopular());
        activityPo.setTitle(activityQueryVo.getTitle());
        activityPo.setType(activityQueryVo.getType());
        //1.执行保存操作tb_activity  save或者saveAndFlash
        ActivityPo activity = activityJpaDao.save(activityPo);
        //2.执行保存操作tb_activity_media
        Integer activityId = activity.getId();
        //2.1 先删除原有 activity_id
        activityMediaJpaDao.deleteMediaByActivityId(activityId);
        //2.2 再新增
        List<ActivityMediaPo> activityMediaes = activityQueryVo.getMediaes();
        for (ActivityMediaPo activityMediae : activityMediaes) {
            activityMediae.setActivityId(activityId);
        }
        activityMediaJpaDao.save(activityMediaes);
        return activity;
    }

    @Override
    public MediaPo addMedia(String fileName, String pathNameTemp)  throws Exception {
        MediaPo mdiaPo = new MediaPo();
        mdiaPo.setName(fileName);
        mdiaPo.setUrl(pathNameTemp);
        return mediaJpaDao.save(mdiaPo);
    }

    @Override
    public E3ResultVo addMediaes(List<MediaPo> mediaes) throws Exception {
        E3ResultVo erv = new E3ResultVo();
        List<MediaPo> mes = mediaJpaDao.save(mediaes);
        String [] meds = new String[mes.size()];
        for (int i = 0; i < mes.size(); i++) {
            meds[i] = mes.get(i).getUrl();
        }
        erv.setErrno(0);
        erv.setData(meds);
        return erv;
    }

    @Override
    public void deleteActivity(String id) throws Exception {
        Integer parsedId = Integer.parseInt(id);
        //先删除 tb_activity
        activityJpaDao.delete(parsedId);
        //再删除 tb_activity_media
        activityMediaJpaDao.deleteMediaByActivityId(parsedId);
    }
}
