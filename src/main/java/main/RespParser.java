package main;

import java.io.BufferedReader;
import java.io.IOException;

public class RespParser {
public String[] parseRespCommand(BufferedReader reader) throws IOException {

    String numberOfArgs= reader.readLine();
    int totoalNumberofArgsInt=Integer.parseInt(numberOfArgs.substring(1));
    //Array to hold the final arguments
    String[] argumentsToReturn=new String[totoalNumberofArgsInt];
    //for loop to loop over number of args here
    for(int i=0;i<totoalNumberofArgsInt;i++){
        //get the bulk length
        String bulkLengthString= reader.readLine();
        //future use length
        int bulkLengthInt=Integer.parseInt(bulkLengthString.substring(1));
        argumentsToReturn[i]= reader.readLine();
    }

    return argumentsToReturn;
}

}
