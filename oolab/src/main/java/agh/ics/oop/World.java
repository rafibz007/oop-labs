package agh.ics.oop;
import agh.ics.oop.gui.App;
import javafx.application.Application;

import javax.lang.model.type.NullType;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
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
       try{
           Application.launch(App.class, args);
           /*ARGS FOR TESTING
            * f b f b f b f b f b f b f b f b r l f f f f f f f f f f
            * */

       } catch( IllegalArgumentException exception ) {
           out.println(exception);
           exception.printStackTrace();
//           System.exit(0); - nie wiem czy musze konczyc program, skoro caly kod w main ma byc objety try, bo po wyjsciu z try/catch nie zostaje juz nic do wykonania i sam sie konczy
       }

    }
}
