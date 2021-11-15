package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractWorldMap implements IWorldMap{
    protected List<Animal> animalsList;

    public AbstractWorldMap(){
        this.animalsList = new ArrayList<>();
    }

    @Override
    public boolean place(Animal animal) {
        if (canMoveTo(animal.getPosition())){
            animalsList.add(animal);
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
}
