package com.kun.xining7.service;

import com.kun.xining7.po.vo.BaseResultVo;
import com.kun.xining7.po.ClassPo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by lan_jiaxing on 2018/7/8 0008.
 */
public interface ClassService {


    /**
     * 查询信息
     * @return 所有信息
     */
    public Page<ClassPo> queryAll(Pageable pageable);

    /**
     * 查询单个信息
     * @return 单个信息
     */

    public ClassPo addClass(ClassPo class2) throws Exception ;

    public ClassPo updateClass(ClassPo class2);

    public void deleteClass(String id);

    public BaseResultVo queryAllClasses();
}
