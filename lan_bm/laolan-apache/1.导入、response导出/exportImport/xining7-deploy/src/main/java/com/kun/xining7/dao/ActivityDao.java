package com.kun.xining7.dao;

import com.kun.framework.core.jdbc.JdbcTemplate;
import com.kun.utils.PageBean;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ActivityDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcTemplate getDataSource() {
        if (jdbcTemplate == null) {
            jdbcTemplate = new JdbcTemplate();
        }
        return jdbcTemplate;
    }

    //
    public PageBean<Map<String, Object>> findAllActivities(String type,String date,String  key,int pageNo, int pageSize) {
        String sql = " FROM tb_activity  WHERE 2 > 1 " ;
        if(!"".equals(type)) {
            sql += " and type = '"+ type + "'";
        }
        if(!"".equals(date)) {
            sql += " and date = '"+ date + "'";
        }
        if(!"".equals(key)) {
            sql += " AND  ( ";
            sql += " title LIKE '%" + key + "%' or ";
            sql += " host LIKE '%" + key + "%' or ";
            sql += " content LIKE '%" + key + "%'";
            sql += " ) ";
        }
        String columnSql = "  SELECT * ";
        columnSql += sql;
        columnSql += " limit " + (pageNo - 1) * pageSize + "," + pageSize;
        List<Map<String, Object>> listMap = getDataSource().queryForList(columnSql);
        String countSql = " SELECT count(1) as total ";
        countSql += sql;
        List<Map<String, Object>> totalList = getDataSource().queryForList(countSql);
        int total = Integer.valueOf(totalList.get(0).get("total") + "");
        int totalPage = (total - 1) / pageSize + 1;
        return new PageBean<>(listMap, totalPage, pageNo, pageSize, total);
    }


    //获得当前项目，已使用的媒体库 tb_activity_media
    public List<Map<String, Object>> findActivityByActivityIdYn(Integer activityId) {
//        String sql = "SELECT id,name,url,activity_id as activityId FROM tb_activity_media WHERE activity_id = " + activityId ;
        String sql = "SELECT * FROM tb_activity WHERE id = " + activityId ;
        return getDataSource().queryForList(sql);
    }

    //获得当前项目，已使用的媒体库 tb_activity_media
    public List<Map<String, Object>> findActivityMediaByActivityIdYn(Integer activityId) {
        String sql = "SELECT id,name,url,activity_id as activityId FROM tb_activity_media WHERE activity_id = " + activityId ;
        return getDataSource().queryForList(sql);
    }

    //获得所有的媒体库 tb_media
    public List<Map<String, Object>> findMedia() {
        String sql = "SELECT id,name,url FROM tb_media ";
        return getDataSource().queryForList(sql);
    }


}
