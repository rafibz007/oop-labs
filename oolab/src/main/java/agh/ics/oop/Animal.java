package agh.ics.oop;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Animal implements IMapElement{
    private MapDirection mapDirection = MapDirection.NORTH;
    private Vector2d position = new Vector2d(2,2);
    private final IWorldMap map;
    private final List<IPositionChangeObserver> Observers = new ArrayList<>();


    public Animal(IWorldMap map){
        this.map = map;
    }

    public Animal(IWorldMap map, Vector2d initialPosition){
        this(map);
        this.position = initialPosition;
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
        for (IPositionChangeObserver observer : Observers){
            observer.positionChanged(oldPosition, newPosition);
        }
    }


    @Override
    public ImageView guiRepresentationImageView() throws FileNotFoundException {
        String direction = switch (mapDirection){
            case NORTH -> "up";
            case EAST -> "right";
            case SOUTH -> "down";
            case WEST -> "left";
        };

        Image image = new Image( new FileInputStream("src/main/resources/"+direction+".png"));
        return new ImageView(image);
    }

    @Override
    public Label guiRepresentationLabel() {
        return new Label(position.toString());
    }

}
