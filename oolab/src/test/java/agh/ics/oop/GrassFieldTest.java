package agh.ics.oop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class GrassFieldTest {
    Animal animal1;
    Animal animal2;
    Grass grass1;
    Grass grass2;
    Grass grass3;
    Grass grass4;
    GrassField map;
    List<Grass> grassTiles;

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
        Assertions.assertFalse(map.place(new Animal( map ,new Vector2d(2,2))));
        Assertions.assertFalse(map.place(new Animal( map ,new Vector2d(1,1))));
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
        grassTiles = new ArrayList<>();
        grass1 = new Grass(new Vector2d(0,0));
        grass2 = new Grass(new Vector2d(1,1));
        grass3 = new Grass(new Vector2d(2,2));
        grass4 = new Grass(new Vector2d(3,3));
        grassTiles.add(grass1);
        grassTiles.add(grass2);
        grassTiles.add(grass3);
        grassTiles.add(grass4);
        map.forTestingSetGrassTiles(grassTiles);
        map.place(animal1);
        map.place(animal2);
    }
}
