package main.redis;

public class StringValue implements IRedisValue{
    private final String value;

    public StringValue(String value){
        this.value=value;
    }
    public String getValue(){
        return value;
    }
    public int getSize(){
        return value.length();
    }
}
