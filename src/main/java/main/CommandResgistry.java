package main;

import main.commands.*;

import java.util.HashMap;
import java.util.Map;

public class CommandResgistry {
    public final Map<String, IRedisCommands> commands;


    public CommandResgistry(RedisStore store){
        commands=new HashMap<>();
        commands.put("PING", new PingCommand());
        commands.put("ECHO",new EchoCommand());
        commands.put("SET",new SetCommand(store));
        commands.put("GET",new GetCommand(store));
        commands.put("RPUSH",new RPushCommand(store));
        commands.put("LRANGE",new LRangeCommand(store));
        commands.put("LPUSH",new LPushCommand(store));

    }
}
