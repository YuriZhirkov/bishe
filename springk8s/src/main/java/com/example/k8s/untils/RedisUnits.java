package com.example.k8s.untils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by zzg on 2018/3/27.
 */
@Component
public class RedisUnits {
    @Autowired
    @Qualifier("redisTemplate")
    RedisTemplate template;


     //redisTemplate.opsForValue();//操作字符串
     //redisTemplate.opsForHash();//操作hash
     //redisTemplate.opsForList();//操作list
     public void setValue(String key, Object val) {
         template.opsForValue().set(key, val);
     }

    public void setValue(String key, Object val, int time, TimeUnit unit) {
        template.opsForValue().set(key, val, time, unit);
    }

    public Object getValue(String key) {
        return template.opsForValue().get(key);
    }

    public void multiSet(Map<String, Object> map) {
        template.opsForValue().multiSet(map);
    }

    public List<Object> multiGet(Collection<String> keys) {
        return template.opsForValue().multiGet(keys);
    }

    public void setHash(String key, Map<String, Object> map) {
        template.opsForHash().putAll(key, map);
    }

    public Object getHash(String key, String prop) {
        return template.opsForHash().get(key, prop);
    }

    public Map getHashAll(String key) {
        Map map = new HashMap();
        map.put("keys", template.opsForHash().keys(key));
        map.put("vals", template.opsForHash().values(key));
        return map;
    }




}
