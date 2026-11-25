package main.commands;

import main.RedisStore;
import java.util.List;

public class LRangeCommand implements IRedisCommands{
    private final RedisStore store;

    public LRangeCommand(RedisStore store){
        this.store=store;
    }
    @Override
    public String execute(String[] commandsAndArguments) {
       StringBuilder builder=new StringBuilder();
       try {
           int start = Integer.parseInt(commandsAndArguments[2]);
           int end = Integer.parseInt(commandsAndArguments[3]);

           List<String> response = store.lrange(commandsAndArguments[1],start,end);
           builder.append("*"
                   +response.size()
                   +"\r\n"
                   );
           for(String stringToApp:response){
               builder.append("$"+stringToApp.length()+"\r\n");
               builder.append(stringToApp+"\r\n");
           }
           return builder.toString();
       }
       catch(IllegalArgumentException e){
           e.printStackTrace();
       }
        return "*0\r\n";
    }
}
