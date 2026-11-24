package main;

import main.redis.IRedisValue;
import main.redis.ListValue;
import main.redis.StringValue;


import java.security.Key;
import java.util.Map;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class RedisStore {

    private class ReddisEntry{
        private IRedisValue value;
        private long timeToExpire;
        public ReddisEntry(IRedisValue value,long timeToExpire){
            this.value=value;
            this.timeToExpire=timeToExpire;
        }
       public boolean isExpired(){
            return timeToExpire!=-1&&System.currentTimeMillis()-timeToExpire>0;
       }

        public IRedisValue getValue() {
            return value;
        }

        public void setValue(IRedisValue value) {
            this.value = value;
        }

        public long getTimeToExpire() {
            return timeToExpire;
        }

        public void setTimeToExpire(long timeToExpire) {
            this.timeToExpire = timeToExpire;
        }
    }
    //normal class begins from here
    private final Map<String,ReddisEntry> store;

    public RedisStore(){
        store=new ConcurrentHashMap<>();
    }

    public void setValueString(String key,String value, long timeToExpireInMill)
    {   ReddisEntry entryToBeinserted=new ReddisEntry(new StringValue(value),timeToExpireInMill);
        if(timeToExpireInMill!=-1){
            long timeToExpireCalculated=System.currentTimeMillis()+timeToExpireInMill;
            entryToBeinserted.setValue(new StringValue(value));
            entryToBeinserted.setTimeToExpire(timeToExpireCalculated);

        }
        store.put(key,entryToBeinserted);

    }

    public IRedisValue getValue(String key) {

        ReddisEntry entry = store.get(key);

        if (entry == null) {
            return null;
        }

        if (entry.isExpired()) {
            store.remove(key);
            return null;
        }

        IRedisValue value = entry.getValue();

        return value;
    }
    //method to add the
    public int rpush(String key, List<String> values){
        final int[] newSize=new int[1];
    //check if the value is already present
        store.compute(key,(k,v)->{
           if(v==null){
               ListValue listValue=new ListValue();
               newSize[0]=listValue.rpushListValues(values);
               return new ReddisEntry(listValue,-1);
           }
           if(v.getValue() instanceof ListValue list){
               newSize[0]=list.rpushListValues(values);
               return v;
           }
          throw new IllegalStateException("WRONG type");
        });
        return newSize[0];
    }


}
