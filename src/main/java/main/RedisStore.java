package main;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RedisStore {
    private final Map<String,String> store;

    public RedisStore(){
        store=new ConcurrentHashMap<>();
    }

    public void setValue(String key,String value){
        store.put(key,value);
    }

    public String getValue(String key){
        return store.get(key);
    }
}
