package com.grgbanking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DictData {


    public static Map<String, Object> dict = null;

    static {
        dict = new HashMap<>();
        //父节点
        List<String> list = new ArrayList<>();
        list.add("玲场地");
        list.add("综合楼");
        list.add("加建筑物");
        dict.put("Building", list);

        //子节点
        Map<String, List<String>> subMap = new HashMap<>();
        list = new ArrayList<>();
        list.add("玲101");
        list.add("玲102");
        list.add("玲103");
        list.add("玲104");
        subMap.put("玲场地", list);

        list = new ArrayList<>();
        list.add("防灾体验中心");
        list.add("财商教师");
        list.add("沙画教室");
        list.add("生命救护中心");
        subMap.put("综合楼", list);

        list = new ArrayList<>();
        list.add("测试1");
        list.add("测试2");
        list.add("测试3");
        list.add("测试4");
        subMap.put("加建筑物", list);

        dict.put("Room", subMap);
    }

    /** 获取数据字典中的值 */
    public static Object getDict(String dict) {
        return DictData.dict.get(dict);
    }
}