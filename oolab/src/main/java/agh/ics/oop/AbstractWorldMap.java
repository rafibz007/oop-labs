package agh.ics.oop;

import java.util.*;

public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver{
    protected Map<Vector2d, Animal> animalsList;

    public AbstractWorldMap(){
        this.animalsList = new LinkedHashMap<>();
    }

    @Override
    public boolean place(Animal animal) {
        if (canMoveTo(animal.getPosition())){
            animalsList.put(animal.getPosition() ,animal);
            return true;
        }
        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        if (objectAt(position) == null)
            return false;
        return true;
    }

    abstract protected Vector2d getLowerLeft();
    abstract protected Vector2d getUpperRight();

    public String toString(){
        Vector2d lowerLeft = getLowerLeft();
        Vector2d upperRight = getUpperRight();
        MapVisualizer visualizer = new MapVisualizer(this);
        return visualizer.draw(lowerLeft, upperRight);
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        Animal animal =  animalsList.get(oldPosition);
        animalsList.remove(oldPosition);
        animalsList.put(newPosition, animal);
    }
}
