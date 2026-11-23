package main;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RedisStore {

    private class ReddisEntry{
        private String value;
        private long timeToExpire;
        public ReddisEntry(String value,long timeToExpire){
            this.value=value;
            this.timeToExpire=timeToExpire;
        }
       public boolean isExpired(){
            return timeToExpire!=-1&&System.currentTimeMillis()-timeToExpire>0;
       }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
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

    public void setValue(String key,String value, long timeToExpireInMill)
    {   ReddisEntry entryToBeinserted=new ReddisEntry(value,timeToExpireInMill);
        if(timeToExpireInMill!=-1){
            long timeToExpireCalculated=System.currentTimeMillis()+timeToExpireInMill;
            entryToBeinserted.setValue(value);
            entryToBeinserted.setTimeToExpire(timeToExpireCalculated);

        }
        store.put(key,entryToBeinserted);

    }

    public String getValue(String key) {

        ReddisEntry entry = store.get(key);

        if (entry == null) {
            return "$-1\r\n";
        }

        if (entry.isExpired()) {
            store.remove(key);
            return "$-1\r\n";
        }

        String value = entry.getValue();

        return "$"
                + value.length()
                + "\r\n"
                + value
                + "\r\n";
    }
}
