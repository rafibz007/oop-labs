package agh.ics.oop;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class OptionParserTest {
    @Test
    void testParse(){
        Assertions.assertArrayEquals(
                new MoveDirection[] {MoveDirection.FORWARD, MoveDirection.FORWARD,
                        MoveDirection.BACKWARD, MoveDirection.BACKWARD,
                        MoveDirection.LEFT, MoveDirection.LEFT,
                        MoveDirection.RIGHT, MoveDirection.RIGHT} ,
                OptionParser.parse(new String[]{"f", "forward",
                        "b", "backward",
                        "l", "left",
                        "r", "right"}));

        Assertions.assertArrayEquals(
                new MoveDirection[] {} ,
                OptionParser.parse(new String[]{}));

        Assertions.assertArrayEquals(
                new MoveDirection[] {} ,
                OptionParser.parse(new String[]{"some", "garbage", "data"}));

        Assertions.assertArrayEquals(
                new MoveDirection[] {MoveDirection.FORWARD, MoveDirection.FORWARD,
                        MoveDirection.BACKWARD, MoveDirection.BACKWARD,
                        MoveDirection.LEFT, MoveDirection.LEFT,
                        MoveDirection.RIGHT, MoveDirection.RIGHT} ,
                OptionParser.parse(new String[]{"some","f", "forward", "garbage","b", "backward", "data","l", "left", "r", "maybe","right", "delete me!/:;}{<>.,"}));

    }

}
