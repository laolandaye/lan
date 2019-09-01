package com.kun.xining7.service;

import com.kun.utils.export.csv.CsvExportBean;
import com.kun.utils.export.json.JsonExportBean;
import com.kun.utils.export.xml.XmlDom4jBean1;
import com.kun.xining7.po.vo.BaseResultVo;
import com.kun.utils.export.excel.ExcelExportBean;
import com.kun.xining7.po.StudentPo;

import java.io.IOException;
import java.util.List;

public interface StudentService {


    BaseResultVo queryAllStudents(int  pageNo, int  pageSize,int  classNo, String key);

    ExcelExportBean queryAllStudentsByClassNo(int classNo);

    CsvExportBean queryStudentsByClassNo(int classNo);

    JsonExportBean queryStudentsByClassNo2(int classNo)  throws Exception;

    XmlDom4jBean1 queryStudentsByClassNo3(int classNo)  throws Exception;

    StudentPo addStudent(StudentPo studentPo) throws Exception ;

    StudentPo updateStudent(StudentPo studentPo) throws Exception ;

    Object importStudentsExecl(List<List<String>> excelDataList, Integer classNo) throws IOException;

    void deleteStudent(String id)  throws Exception;

    Integer deleteStudentes(String ids)  throws Exception;
}
