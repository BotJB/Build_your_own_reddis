package main.redis;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class ListValue implements IRedisValue{
    private final Deque<String> values;
    public ListValue(){
        values=new ArrayDeque<>();
    }

    public Deque<String> getValues() {
        return values;
    }

    public synchronized int rpushListValues(List<String> valuesToBeAdded){
        for(String s:valuesToBeAdded){
            values.addLast(s);
        }
        return values.size();
    }

    public synchronized List<String> getRange(int begin,int end){
    int actualStart=sanitizeIndex(begin,getSize());
    int actualEnd=sanitizeIndex(end,getSize());
    actualStart=Math.max(0,actualStart);
    actualEnd=Math.min(actualEnd,getSize()-1);
    return values
            .stream()
            .skip(actualStart)
            .limit(actualEnd-actualStart+1)
            .toList();


    }

    public synchronized int getSize(){
        return values.size();
    }
    public int sanitizeIndex(int indexToCheck,int size){
        return indexToCheck>=0?indexToCheck:size+indexToCheck;
    }
}
