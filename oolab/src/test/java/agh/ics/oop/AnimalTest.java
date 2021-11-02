package agh.ics.oop;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class AnimalTest {
    @Test
    void testToString(){

        Animal animal = new Animal();

        animal.forTestingSetPositionAndDirection(1, 1, MapDirection.NORTH);
        Assertions.assertEquals("(1, 1):Północ", animal.toString());

        animal.forTestingSetPositionAndDirection(2, 1, MapDirection.EAST);
        Assertions.assertEquals("(2, 1):Wschód", animal.toString());

        animal.forTestingSetPositionAndDirection(1, 3, MapDirection.SOUTH);
        Assertions.assertEquals("(1, 3):Południe", animal.toString());

        animal.forTestingSetPositionAndDirection(0, 0, MapDirection.WEST);
        Assertions.assertEquals("(0, 0):Zachód", animal.toString());
    }

    @Test
    void testMoveDirection(){
        Animal animal = new Animal();

        animal.forTestingSetPositionAndDirection(2,2,MapDirection.NORTH);
        for (MoveDirection direction : OptionParser.parse(new String[]{"f", "f", "f"})){
            animal.moveDirection(direction);
        }
        Assertions.assertEquals(new Vector2d(2,4), animal.getPosition());
        Assertions.assertEquals(MapDirection.NORTH, animal.getMapDirection());

        for (MoveDirection direction : OptionParser.parse(new String[]{"r","r","f", "f", "f", "f", "f", "f"})){
            animal.moveDirection(direction);
        }
        Assertions.assertEquals(new Vector2d(2,0), animal.getPosition());
        Assertions.assertEquals(MapDirection.SOUTH, animal.getMapDirection());

        for (MoveDirection direction : OptionParser.parse(new String[]{"l", "l","f", "f", "r","f", "f", "f"})){
            animal.moveDirection(direction);
        }
        Assertions.assertEquals(new Vector2d(4,2), animal.getPosition());
        Assertions.assertEquals(MapDirection.EAST, animal.getMapDirection());

        for (MoveDirection direction : OptionParser.parse(new String[]{"l", "r", "r", "r","f", "f", "f", "f", "f", "f"})){
            animal.moveDirection(direction);
        }
        Assertions.assertEquals(new Vector2d(0,2), animal.getPosition());
        Assertions.assertEquals(MapDirection.WEST, animal.getMapDirection());
    }
}
