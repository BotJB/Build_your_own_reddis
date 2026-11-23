package main;

import main.commands.EchoCommand;
import main.commands.IRedisCommands;
import main.commands.PingCommand;
import main.commands.SetCommand;

import java.util.HashMap;
import java.util.Map;

public class CommandResgistry {
    public final Map<String, IRedisCommands> commands;


    public CommandResgistry(RedisStore store){
        commands=new HashMap<>();
        commands.put("PING", new PingCommand());
        commands.put("ECHO",new EchoCommand());
        commands.put("SET",new SetCommand(store));

    }
}
