package main.redis;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class ListValue implements IRedisValue{
    private final Deque<String> values;
    public ListValue(){
        values=new ArrayDeque<>();
    }
    public synchronized int rpushListValues(List<String> valuesToBeAdded){
        for(String s:valuesToBeAdded){
            values.addLast(s);
        }
        return values.size();
    }
    public int getSize(){
        return values.size();
    }
}
