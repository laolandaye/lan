package _7io._1file.示列._1复制文件;

import java.util.HashMap;
import java.util.Map;

/**
 * 重命名规则类
 * @author jack
 */
public class ReplacementChain{
    private Map<String,String> map;


    public ReplacementChain() {
        this.map = new HashMap<String, String>();
    }

    public Map<String, String> getMap() {
        return map;
    }

    // 添加新的替换规则(字符串替换)
    public ReplacementChain  addRegulation(String oldStr , String newStr){
        this.map.put(oldStr, newStr);
        return this;
    }

}
