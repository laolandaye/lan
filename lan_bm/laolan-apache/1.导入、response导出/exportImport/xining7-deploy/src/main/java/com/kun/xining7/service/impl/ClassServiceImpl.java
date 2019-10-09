package com.kun.xining7.service.impl;

import com.kun.xining7.po.vo.BaseResultVo;
import com.kun.xining7.dao.ClassDao;
import com.kun.xining7.dao.ClassJpaDao;
import com.kun.xining7.po.ClassPo;
import com.kun.xining7.po.GradePo;
import com.kun.xining7.service.ClassService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ClassServiceImpl implements ClassService {

    @Resource
    private ClassDao classDao;

    @Resource
    private ClassJpaDao  classJpaDao;

    /**
     * 查询信息
     * @return 所有信息
     */
    public Page<ClassPo> queryAll(Pageable pageable) {
        return  classJpaDao.findAll(pageable);
    }

    @Transactional
    public ClassPo addClass(ClassPo class2) throws Exception {
        return classJpaDao.save(class2);
    }

    @Transactional
    public ClassPo updateClass(ClassPo class2) {
        ClassPo ClassPo =  classJpaDao.save(class2);
        return ClassPo;
    }

    @Transactional
    public void deleteClass(String id) {
         classJpaDao.delete(Integer.parseInt(id));
    }

    @Override
    public BaseResultVo queryAllClasses() {
        BaseResultVo brv = new BaseResultVo();
//        List<Object> result =  classJpaDao.findAllClasses();
//        List<Map<String, Object>> listMap = new ArrayList<>();
//        int i =0;
//        for (Object row : result) {
//            Object[] rowArray = (Object[]) row;
//            Map<String, Object> mapArr = new HashMap<>();
//            mapArr.put("id", rowArray[0]);
//            mapArr.put("order", ++i);
//            mapArr.put("classNo", rowArray[1]);
//            mapArr.put("className", rowArray[2]);
//            GradePo gradePo = new GradePo();
//            gradePo.setId(Integer.parseInt(rowArray[3].toString()));
//            gradePo.setGradeNo(Integer.parseInt(rowArray[4].toString()));
//            gradePo.setGradeName(rowArray[5].toString());
//            gradePo.setOrder(Integer.parseInt(rowArray[6].toString()));
//            mapArr.put("grade", gradePo);
//            listMap.add(mapArr);
//        }
//        brv.setData(listMap);

        //方式二
        List<Map<String, Object>> result2 = classDao.findAllClasses();
        List<Map<String, Object>> listMap = new ArrayList<>();
        int i =0;
        for (Map<String, Object> row : result2) {
            Map<String, Object> mapArr = new HashMap<>();
            mapArr.put("id", row.get("id"));
            mapArr.put("order", ++i);
            mapArr.put("classNo", row.get("classNo"));
            mapArr.put("className",  row.get("className"));
            GradePo gradePo = new GradePo();
            gradePo.setId(Integer.parseInt( row.get("gradeId").toString()));
            gradePo.setGradeNo(Integer.parseInt( row.get("gradeGradeNo").toString()));
            gradePo.setGradeName( row.get("gradeGradeName").toString());
            gradePo.setOrder(Integer.parseInt( row.get("gradeOrder").toString()));
            mapArr.put("grade", gradePo);
            listMap.add(mapArr);
        }
        brv.setData(listMap);
        return brv;
    }
}
