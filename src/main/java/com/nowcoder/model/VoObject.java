package com.nowcoder.model;

import java.util.HashMap;
import java.util.Map;

public class VoObject {
    private Map<String,Object> map=new HashMap<>();

    public Object get(String key) {
        return map.get(key);
    }

    public void  set(String key, Object  value)  {
        this.map.put(key,value);
    }
//    private Map<String, Object> map=new HashMap<>();
//
//    public Object get(String key) {
//        return map.get(key);
//    }
//
//    public void  set(String key, Object value)  {
//       this.map.put(key,value);
//    }
}
//public class VoObject<T> {
//    private Map<String,T> map=new HashMap<>();
//
//    public T get(String key) {
//        return map.get(key);
//    }
//
//    public <T> void  set(String key, T value)  {
//        this.map.put(key,value);
//    }