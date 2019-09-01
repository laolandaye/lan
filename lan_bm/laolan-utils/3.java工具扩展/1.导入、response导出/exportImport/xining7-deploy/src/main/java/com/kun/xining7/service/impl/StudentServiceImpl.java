package com.kun.xining7.service.impl;

import com.kun.utils.export.csv.CsvExportBean;
import com.kun.utils.export.json.JsonExportBean;
import com.kun.utils.export.xml.XmlDom4jBean1;
import com.kun.xining7.po.vo.BaseResultVo;
import com.kun.utils.export.excel.ExcelExportBean;
import com.kun.utils.PageBean;
import com.kun.xining7.dao.StudentDao;
import com.kun.xining7.dao.StudentJpaDao;
import com.kun.xining7.po.StudentPo;
import com.kun.xining7.service.StudentService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;

@Service
public class StudentServiceImpl implements StudentService {

    @Resource
    private StudentDao studentDao;

    @Resource
    private StudentJpaDao studentJpaDao;

    @Override
    public BaseResultVo queryAllStudents(int  pageNo, int  pageSize, int  classNo, String key) {
        BaseResultVo brv = new BaseResultVo();
        PageBean<Map<String, Object>> pageBean = studentDao.findAllStudents(pageNo, pageSize, classNo, key);
        brv.setData(pageBean.getDataModel());
        brv.setPageBean(pageBean);
        return brv;
    }

    @Override
    public ExcelExportBean queryAllStudentsByClassNo(int classNo) {
        ExcelExportBean eeb = new ExcelExportBean();
        List<Map<String, Object>> listMap = studentDao.findStudentsByGroupClass(classNo);
        eeb.setList(listMap);
        String [][] content = new String[listMap.size()][listMap.get(0).size()];
        for (int i = 0; i < listMap.size(); i++) {
            content[i][0] = listMap.get(i).get("studentId").toString();
            content[i][1] = listMap.get(i).get("name").toString();
            content[i][2] = listMap.get(i).get("sex").toString();
            content[i][3] = listMap.get(i).get("nation") != null ? listMap.get(i).get("nation").toString() : "无";
            content[i][4] = listMap.get(i).get("origin") != null ? listMap.get(i).get("origin").toString() : "无";
            content[i][5] = listMap.get(i).get("address") != null ? listMap.get(i).get("address").toString() : "无";
            content[i][6] = listMap.get(i).get("contactsName") != null ? listMap.get(i).get("contactsName").toString() : "无";
            content[i][7] = listMap.get(i).get("contactsPhone") != null ? listMap.get(i).get("contactsPhone").toString() : "无";
            content[i][8] = listMap.get(i).get("className").toString();
            content[i][9] = listMap.get(i).get("gradeName").toString();
        }
        eeb.setContent(content);
        String[] title = {"学号","姓名","性别","民族","籍贯","居住地址","联系人","联系电话","班级","年级"};
        eeb.setTitle(title);
        eeb.setFileName(listMap.get(0).get("gradeName").toString() + listMap.get(0).get("className").toString() + "学生信息表.xls");
        eeb.setSheetName(listMap.get(0).get("gradeName").toString() + listMap.get(0).get("className").toString() + "学生信息表");
        return eeb;
    }

    @Override
    public CsvExportBean queryStudentsByClassNo(int classNo) {
        CsvExportBean  ceb = new CsvExportBean();

        List<LinkedHashMap<String, String>> contents = new ArrayList<>();
        LinkedHashMap<String, String> linkedHashMap = null;
        List<Map<String, Object>> listMap = studentDao.findStudentsByGroupClass(classNo);
        for (Map<String, Object> map : listMap) {
            linkedHashMap = new LinkedHashMap<>();
            for (String key : map.keySet()) {
                linkedHashMap.put(key, (String) map.get(key));
            }
            contents.add(linkedHashMap);
        }
        ceb.setContents(contents);

        String[] title = {"学号","姓名","性别","民族","籍贯","居住地址","联系人","联系电话","班级","年级"};
        String[] title2 = {"studentid","name","sex","nation","origin","address","contactsname","contactsphone","classname","gradename"};
        LinkedHashMap<String, String> heads = new LinkedHashMap<>();
        for (int i = 0; i < title.length; i++) {
            heads.put(title2[i], title[i]);
        }
        ceb.setHeads(heads);

        ceb.setFileName("fjkdsfk.csv");
        return ceb;
    }

    @Override
    public JsonExportBean queryStudentsByClassNo2(int classNo) throws Exception {
        JsonExportBean jeBean = new JsonExportBean();
        JSONArray jsonArray = new JSONArray();
        String[] title = {"学号","姓名","性别","民族","籍贯","居住地址","联系人","联系电话","班级","年级"};
        String[] title2 = {"studentid","name","sex","nation","origin","address","contactsname","contactsphone","classname","gradename"};
        List<Map<String, Object>> listMap2 = new ArrayList<>();
        Map<String, Object> map = null;
        for (int i = 0; i < title.length; i++) {
            map = new HashMap<>();
            map.put("name", title[i]);
            map.put("cn_name", title2[i]);
            listMap2.add(map);
        }
        jsonArray.add(listMap2);

        List<Map<String, Object>> listMap = studentDao.findStudentsByGroupClass(classNo);
        jsonArray.add(listMap);

        jeBean.setJsonString(jsonArray.toString());

        jeBean.setFileName("1111.json");
        return jeBean;
    }

    @Override
    public XmlDom4jBean1 queryStudentsByClassNo3(int classNo)  throws Exception {
        XmlDom4jBean1 xdb1 = new XmlDom4jBean1();
        List<Map<String, Object>> listMap = studentDao.findStudentsByGroupClass(classNo);
        xdb1.setCentents(listMap);

        String[] title = {"学号","姓名","性别","民族","籍贯","居住地址","联系人","联系电话","班级","年级"};
        String[] title2 = {"studentid","name","sex","nation","origin","address","contactsname","contactsphone","classname","gradename"};
        List<Map<String, Object>> listMap2 = new ArrayList<>();
        Map<String, Object> map = null;
        for (int i = 0; i < title.length; i++) {
            map = new HashMap<>();
            map.put("name", title[i]);
            map.put("cn_name", title2[i]);
            listMap2.add(map);
        }
        xdb1.setHeads(listMap2);

        xdb1.setFileName("1111.xml");
        return xdb1;
    }

    @Transactional
    @Override
    public StudentPo addStudent(StudentPo studentPo) throws Exception {
        return studentJpaDao.save(studentPo);
    }

    @Transactional
    @Override
    public StudentPo updateStudent(StudentPo studentPo) throws Exception {
        return studentJpaDao.saveAndFlush(studentPo);
    }

    @Transactional
    @Override
    public  Object importStudentsExecl(List<List<String>> excelDataList, Integer classNo) throws IOException {
        return studentDao.insertBatchStudents(excelDataList, classNo);
    }

    @Transactional
    @Override
    public void deleteStudent(String id)  throws Exception {
        studentJpaDao.delete(Integer.parseInt(id));
    }

    @Transactional
    @Override
    public Integer deleteStudentes(String ids) throws Exception {

        String[] id =  ids.split(",");
        return studentJpaDao.deleteStudentes(Arrays.asList(id));
    }


}
