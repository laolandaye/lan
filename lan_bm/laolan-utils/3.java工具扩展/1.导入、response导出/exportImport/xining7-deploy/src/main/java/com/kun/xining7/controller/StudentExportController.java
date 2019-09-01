package com.kun.xining7.controller;

import com.kun.utils.export.csv.CsvExportUtil;
import com.kun.utils.export.csv.CsvExportBean;
import com.kun.utils.export.excel.ExcelExportBean;
import com.kun.utils.export.excel.ExcelExportUtil;
import com.kun.utils.export.json.JsonExportBean;
import com.kun.utils.export.json.JsonExportUtil;
import com.kun.utils.export.xml.XmlDom4jBean1;
import com.kun.utils.export.xml.XmlDom4jUtil;
import com.kun.utils.upload.excel.ExcelImportUtil;
import com.kun.utils.upload.excel.IoUtils;
import com.kun.xining7.dao.StudentDao;
import com.kun.xining7.po.StudentPo;
import com.kun.xining7.po.vo.BaseResultVo;
import com.kun.xining7.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@Controller
@RequestMapping("/api")
public class StudentExportController {

    private static final Logger log = LoggerFactory.getLogger(StudentExportController.class);

    @Resource
    private StudentService studentService;

    @Autowired
    private StudentDao studentDao;

    //获取所有学生(classNo=&pageNo=&pageSize=)
    @GetMapping(value = "/students")
    @ResponseBody
    public BaseResultVo queryAllStudents(
            @RequestParam(name = "pageNo", defaultValue = "1", required = false) int pageNo,
            @RequestParam(name = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(name = "classNo", defaultValue = "0", required = false) int classNo,
            @RequestParam(name = "key", defaultValue = "", required = false) String key
    ) {
        return studentService.queryAllStudents(pageNo, pageSize, classNo, key);
    }

    //导出excel(以班级为单位)
    @GetMapping(value = "/students/export/excel")
    @ResponseBody
    public void exportStudentsExcel(
            @RequestParam(name = "classNo") int classNo,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        //响应到客户端
        try {
            ExcelExportBean eeb = studentService.queryAllStudentsByClassNo(classNo);
            ExcelExportUtil.expoetExcel(response, eeb);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //导出excel(以班级为单位)
    @GetMapping(value = "/students/export/csv")
    @ResponseBody
    public void exportStudentsCsv(
            @RequestParam(name = "classNo") int classNo,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        try {
            CsvExportBean ceb = studentService.queryStudentsByClassNo(classNo);
            CsvExportUtil.exportFile(response, ceb.getFileName(), ceb.getHeads(), ceb.getContents());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //导出excel(以班级为单位)
    @GetMapping(value = "/students/export/json")
    @ResponseBody
    public void exportStudentsJson(
            @RequestParam(name = "classNo") int classNo,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        try {
            JsonExportBean jsBean = studentService.queryStudentsByClassNo2(classNo);
            JsonExportUtil.exportFile(jsBean, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 导出模版，模版为xml格式
     **/
     @GetMapping(value = "/students/export/xml")
     @ResponseBody
     public void exportStudentsXml(
             @RequestParam(name = "classNo") int classNo,
             HttpServletRequest request,
             HttpServletResponse response
     ) {
         try {
             XmlDom4jBean1 xdb1 = studentService.queryStudentsByClassNo3(classNo);
             XmlDom4jUtil.exportXmlDemo1(response, xdb1);
         } catch (Exception e) {
             e.printStackTrace();
         }
    }


    //增加学生信息
    @PostMapping(value = "/student")
    @ResponseBody
    public BaseResultVo addStudent(@RequestBody StudentPo studentPo) {
        BaseResultVo brv = new BaseResultVo();
        try {
            brv.setData(studentService.addStudent(studentPo));
            return brv;
        } catch (Exception e) {
            brv.setCodeMessage("1", "失败，该学号已存在");
            e.printStackTrace();
            return brv;
        }
    }

    //修改学生信息
    @PutMapping(value = "/student")
    @ResponseBody
    public BaseResultVo updateStudent(@RequestBody StudentPo studentPo) {
        BaseResultVo brv = new BaseResultVo();
        try {
            brv.setData(studentService.updateStudent(studentPo));
            return brv;
        } catch (Exception e) {
            brv.setCodeMessage("1", "失败，该学号已存在");
            e.printStackTrace();
            return brv;
        }
    }

    @PostMapping(value = "/students/import")
    @ResponseBody
    public BaseResultVo importStudentsExecl(MultipartHttpServletRequest multipartRequest, Integer classNo) {
        BaseResultVo brv = new BaseResultVo();

//        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        //获得非上传组件
//        String classNo = multipartRequest.getParameter("classNo");
        //获得上传组件
        List<MultipartFile> fileList = multipartRequest.getFiles("file");
        MultipartFile myfile = null;
        myfile = fileList.get(0);
        String FileName = myfile.getOriginalFilename();
        String fileFolder = multipartRequest.getSession().getServletContext().getRealPath("/upload");
        String pathNameTemp = fileFolder + FileName; //临时完整目录文件

        Object object;
        try {
            //先把传过来的文件放在临时文件夹下，然后从文件夹中取出
            InputStream inputStream = myfile.getInputStream();
            IoUtils.copyer(inputStream, pathNameTemp);
            List<List<String>> excelDataList = ExcelImportUtil.importExecl(pathNameTemp);//将excel所有数据都以String形式存入
            if (excelDataList != null && excelDataList.size() > 1) {
                brv.setData(studentService.importStudentsExecl(excelDataList, classNo));
            }

            //删除临时文件
            File fileTemp = new File(pathNameTemp);
            if (fileTemp.exists() && fileTemp.isFile()) {
                fileTemp.delete();
            }
            return brv;
        } catch (IOException ie) {
            ie.printStackTrace();
            brv.setCodeMessage("1", "上传失败，请检查格式");
            return brv;
        } catch (Exception e) {
            e.printStackTrace();
            brv.setCodeMessage("1", "上传失败，请检查格式");
            return brv;
        }
    }

    @PostMapping(value = "/students/import2")
    @ResponseBody
    public BaseResultVo importStudents(MultipartHttpServletRequest multipartRequest) {
        BaseResultVo brv = new BaseResultVo();

//        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        //获得非上传组件
//        String classNo = multipartRequest.getParameter("classNo");
        //获得上传组件
        List<MultipartFile> fileList = multipartRequest.getFiles("file");
        MultipartFile myfile = null;
        myfile = fileList.get(0);
        String FileName = myfile.getOriginalFilename();
        String fileFolder = multipartRequest.getSession().getServletContext().getRealPath("/upload");
        String pathNameTemp = fileFolder + FileName; //临时完整目录文件

        Object object;
        try {
            //先把传过来的文件放在临时文件夹下，然后从文件夹中取出
            InputStream inputStream = myfile.getInputStream();
            IoUtils.copyer(inputStream, pathNameTemp);
            List<List<String>> excelDataList = ExcelImportUtil.importExecl(pathNameTemp);//将excel所有数据都以String形式存入
            if (excelDataList != null && excelDataList.size() > 1) {
                brv.setData(studentDao.insertBatch(excelDataList));
            }

            //删除临时文件
            File fileTemp = new File(pathNameTemp);
            if (fileTemp.exists() && fileTemp.isFile()) {
                fileTemp.delete();
            }
            return brv;
        } catch (IOException ie) {
            ie.printStackTrace();
            brv.setCodeMessage("1", "上传失败，请检查格式");
            return brv;
        } catch (Exception e) {
            e.printStackTrace();
            brv.setCodeMessage("1", "上传失败，请检查格式");
            return brv;
        }
    }


    @DeleteMapping(value = "/student/{id}")
    @ResponseBody
    public void deleteStudent(@PathVariable("id") String id) {
        try {
            studentService.deleteStudent(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @DeleteMapping(value = "/studentes")
    @ResponseBody
    public BaseResultVo deleteStudentes(@RequestParam(name = "ids", defaultValue = "", required = false) String ids) {
        BaseResultVo brv = new BaseResultVo();
        try {
            brv.setData(studentService.deleteStudentes(ids));
            return brv;
        } catch (Exception e) {
            brv.setCodeMessage("1", "失败");
            e.printStackTrace();
            return brv;
        }
    }
}
