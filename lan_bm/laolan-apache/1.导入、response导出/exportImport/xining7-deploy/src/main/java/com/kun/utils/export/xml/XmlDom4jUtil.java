package com.kun.utils.export.xml;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.XMLWriter;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class XmlDom4jUtil {

    public static void exportXmlDemo1(HttpServletResponse response, XmlDom4jBean1 xdb1) {
        XMLWriter writer = null;
        try {
            //创建xml
            Document document = DocumentHelper.createDocument();

            //一级节点list
            Element root = DocumentHelper.createElement("html");
            document.add(root);

            //二级节点col,data
            Element root21 = root.addElement("list").addAttribute("name", "col");
            Element root22 = root.addElement("list").addAttribute("name", "data");

            //三级节点 item,四级节点，column
            Element element = null;
            List<Map<String, Object>> elements = xdb1.getHeads();
            Map<String, Object> map = null;
            String value = "";
            for (int i = 0; i < elements.size(); i++) {
                element = root21.addElement("item").addAttribute("number", String.valueOf(i));
                map = elements.get(i);
                for (String key : map.keySet()) {
                    // 解决value可能为空的空指针异常
                    if (null == map.get(key)) {
                        value = "";
                    } else {
                        value = map.get(key).toString();
                    }
                    element.addElement("column").addAttribute("name", key).addText(value);
                }
            }

            elements = xdb1.getCentents();
            for (int i = 0; i <  elements.size(); i++) {
                element = root22.addElement("item").addAttribute("number", String.valueOf(i));
                map = elements.get(i);
                for (String key : map.keySet()) {
                    // 解决value可能为空的空指针异常
                    if (null == map.get(key)) {
                        value = "";
                    } else {
                        value = map.get(key).toString();
                    }
                    element.addElement("column").addAttribute("name", key).addText(value);
                }
            }

            writer = XMLDom4j.getXMLWriter(response, xdb1.getFileName());
            writer.write(document);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != writer) {
                    writer.close();
                }
            } catch (IOException e) {
                System.out.println("关闭XMLWriter出错：" + e);
                e.printStackTrace();
            }
        }
    }

}
