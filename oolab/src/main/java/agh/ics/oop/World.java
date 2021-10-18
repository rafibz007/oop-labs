package agh.ics.oop;
import javax.lang.model.type.NullType;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.lineSeparator;
import static java.lang.System.out;

public class World {
    public static void run(Direction[] moves){

//        WYPISYWANIE KROKOW DO WYKONANIA
        for(int i=0; i<moves.length; i++){
            if (i>0){
                out.print(",");
            }
            out.print(moves[i]);
        }
        out.println();

//        WYKONANIE KROKOW
        for(Direction move : moves){
            String message = switch (move){
                case FORWARD -> "Zwierzak idzie do przodu";
                case BACKWARD -> "Zwierzak idzie do tylu";
                case LEFT -> "Zwierzak idzie w lewo";
                case RIGHT -> "Zwierzak idzie w prawo";
            };
            out.println(message);
        }
    }

    public static Direction[] parse(String[] args){
        List<Direction> enumArgs = new ArrayList<Direction>();
        for( String arg : args ){
            Direction enumValue = switch (arg){
                case "f" -> Direction.FORWARD;
                case "b" -> Direction.BACKWARD;
                case "l" -> Direction.LEFT;
                case "r" -> Direction.RIGHT;
                default -> null;
            };
            enumArgs.add(enumValue);
        }
        Direction[] enumArgsArray = new Direction[enumArgs.size()];
        enumArgsArray = enumArgs.toArray(enumArgsArray);
        return enumArgsArray;
    }

    public static void main(String[] args){
        out.println("Start");

        Direction[] enumArgs = parse(args);
        run(enumArgs);

        out.println("Stop");
    }
}
