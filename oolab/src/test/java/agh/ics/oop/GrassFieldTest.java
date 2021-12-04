package agh.ics.oop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GrassFieldTest {
    Animal animal1, animal2;
    Grass grass1, grass2, grass3, grass4;
    GrassField map;
    Map<Vector2d, Grass> grassTiles;
    Vector2d position1, position2, position3, position4;

    @Test
    void objectAt(){
        Assertions.assertEquals(grass1, map.objectAt(new Vector2d(0, 0)));
        Assertions.assertEquals(animal1, map.objectAt(new Vector2d(1, 1)));
        Assertions.assertEquals(animal2, map.objectAt(new Vector2d(2,2)));
        Assertions.assertEquals(grass4, map.objectAt(new Vector2d(3,3)));
        Assertions.assertNull(map.objectAt(new Vector2d(4,4)));
    }

    @Test
    void isOccupied(){
        Assertions.assertTrue(map.isOccupied(new Vector2d(0, 0)));
        Assertions.assertTrue(map.isOccupied(new Vector2d(1, 1)));
        Assertions.assertTrue(map.isOccupied(new Vector2d(2,2)));
        Assertions.assertTrue(map.isOccupied(new Vector2d(3,3)));
        Assertions.assertFalse(map.isOccupied(new Vector2d(4,4)));
        Assertions.assertFalse(map.isOccupied(new Vector2d(-1,-1)));
        Assertions.assertFalse(map.isOccupied(new Vector2d(2,3)));
    }

    @Test
    void place(){
        Assertions.assertTrue(map.place(new Animal( map ,new Vector2d(10, 10))));
        Assertions.assertTrue(map.place(new Animal( map ,new Vector2d(0, 10))));
        Assertions.assertTrue(map.place(new Animal( map ,new Vector2d(-10, 10))));
        Assertions.assertTrue(map.place(new Animal( map ,new Vector2d(-10, 0))));
        Assertions.assertTrue(map.place(new Animal( map ,new Vector2d(-10, -10))));
        Assertions.assertTrue(map.place(new Animal( map ,new Vector2d(0, -10))));
        Assertions.assertTrue(map.place(new Animal( map ,new Vector2d(10, -10))));
        Assertions.assertTrue(map.place(new Animal( map ,new Vector2d(10, 0))));
        Assertions.assertThrows(IllegalArgumentException.class, ()-> map.place(new Animal( map ,new Vector2d(2,2))));
        Assertions.assertThrows(IllegalArgumentException.class, ()-> map.place(new Animal( map ,new Vector2d(1,1))));
        Assertions.assertTrue(map.place(new Animal( map ,new Vector2d(3,3))));
        Assertions.assertTrue(map.place(new Animal( map ,new Vector2d(0,0))));
    }

    @Test
    void canMoveTo(){
        Assertions.assertTrue(map.canMoveTo(new Vector2d(10, 10)));
        Assertions.assertTrue(map.canMoveTo(new Vector2d(0, 10)));
        Assertions.assertTrue(map.canMoveTo(new Vector2d(-10, 10)));
        Assertions.assertTrue(map.canMoveTo(new Vector2d(-10, 0)));
        Assertions.assertTrue(map.canMoveTo(new Vector2d(-10, -10)));
        Assertions.assertTrue(map.canMoveTo(new Vector2d(0, -10)));
        Assertions.assertTrue(map.canMoveTo(new Vector2d(10, -10)));
        Assertions.assertTrue(map.canMoveTo(new Vector2d(10, 0)));
        Assertions.assertFalse(map.canMoveTo(new Vector2d(2,2)));
        Assertions.assertFalse(map.canMoveTo(new Vector2d(1,1)));
        Assertions.assertTrue(map.canMoveTo(new Vector2d(3,3)));
        Assertions.assertTrue(map.canMoveTo(new Vector2d(0,0)));
    }


    @BeforeEach
    void setUp(){
        map = new GrassField(4);
        animal1 = new Animal(map, new Vector2d(1, 1));
        animal2 = new Animal(map, new Vector2d(2, 2));
        grassTiles = new LinkedHashMap<>();

        position1 = new Vector2d(0,0);
        position2 = new Vector2d(1,1);
        position3 = new Vector2d(2,2);
        position4 = new Vector2d(3,3);
        grass1 = new Grass(position1);
        grass2 = new Grass(position2);
        grass3 = new Grass(position3);
        grass4 = new Grass(position4);
        grassTiles.put(position1, grass1);
        grassTiles.put(position2, grass2);
        grassTiles.put(position3, grass3);
        grassTiles.put(position4, grass4);
        map.forTestingSetGrassTiles(grassTiles);
        map.place(animal1);
        map.place(animal2);
    }
}
