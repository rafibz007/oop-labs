package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class Animal {
    private MapDirection mapDirection;
    private Vector2d position;
    private final IWorldMap map;
    private final List<IPositionChangeObserver> Observers = new ArrayList<>();


    public Animal(IWorldMap map){
        this.mapDirection = MapDirection.NORTH;
        this.position = new Vector2d(2,2);
        this.map = map;
        if (map instanceof IPositionChangeObserver)
            addObserver((IPositionChangeObserver) map);
    }

    public Animal(IWorldMap map, Vector2d initialPosition){
        this.mapDirection = MapDirection.NORTH;
        this.position = initialPosition;
        this.map = map;
        if (map instanceof IPositionChangeObserver)
            addObserver((IPositionChangeObserver) map);
    }

    public boolean isAt(Vector2d position){
        return this.position.x == position.x && this.position.y == position.y;
    }

    public MapDirection getMapDirection() {
        return mapDirection;
    }

    public Vector2d getPosition() {
        return position;
    }

    public void forTestingSetPositionAndDirection(int x, int y, MapDirection direction){
        this.position = new Vector2d(x,y);
        this.mapDirection = direction;
    }

    public String toString(){
        return switch (mapDirection){
            case NORTH -> "^";
            case SOUTH -> "v";
            case EAST -> ">";
            case WEST -> "<";
        };
    }

    public void moveDirection(MoveDirection direction){
        Vector2d oldPosition = position;
        switch (direction){
            case FORWARD -> {
                Vector2d newPosition = position.add(mapDirection.toUnitVector());
                if (map.canMoveTo(newPosition)){
                    position = newPosition;
                }
            }
            case BACKWARD -> {
                Vector2d newPosition = position.subtract(mapDirection.toUnitVector());
                if (map.canMoveTo(newPosition)){
                    position = newPosition;
                }
            }
            case LEFT -> this.mapDirection = this.mapDirection.previous();
            case RIGHT -> this.mapDirection = this.mapDirection.next();
        }
        positionChanged(oldPosition, position);
    }


    public void addObserver(IPositionChangeObserver observer){
        Observers.add(observer);
    }

    public void removeObserver(IPositionChangeObserver observer){
        Observers.remove(observer);
    }

    private void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        if (newPosition == oldPosition)
            return;
        for (IPositionChangeObserver observer : Observers){
            observer.positionChanged(oldPosition, newPosition);
        }
    }

}
