package agh.ics.oop;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OptionParser {

    static public MoveDirection[] parse(String[] args){
        MoveDirection[] parsedArgs = new MoveDirection[args.length];
        int i = 0;
        for(String arg : args){
            parsedArgs[i] = switch (arg){
                case "f", "forward" -> MoveDirection.FORWARD;
                case "r", "right" -> MoveDirection.RIGHT;
                case "b", "backward" -> MoveDirection.BACKWARD;
                case "l", "left" -> MoveDirection.LEFT;
                default -> null;
            };
            if (parsedArgs[i] == null)
                continue;
            i += 1;
        }
        return Arrays.copyOf(parsedArgs, i);
    }
}
