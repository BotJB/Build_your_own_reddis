package main;

import main.commands.EchoCommand;
import main.commands.IRedisCommands;
import main.commands.PingCommand;

import java.util.HashMap;
import java.util.Map;

public class CommandResgistry {
    public Map<String, IRedisCommands> commands;

    public CommandResgistry(){
        commands=new HashMap<>();
        commands.put("PING", new PingCommand());
        commands.put("ECHO",new EchoCommand());
    }
}
