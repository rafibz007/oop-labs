package agh.ics.oop;

import java.util.*;

public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver{
    protected Map<Vector2d, Animal> animalsList;
    protected final List<IPositionChangeObserver> observersForAnimals;


    public AbstractWorldMap(){
        this.animalsList = new LinkedHashMap<>();
        observersForAnimals = new ArrayList<>();
        observersForAnimals.add(this);
    }

    public void addObserverForAnimals(IPositionChangeObserver observer){
        observersForAnimals.add(observer);
    }

    public void removeObserverForAnimals(IPositionChangeObserver observer){
        observersForAnimals.remove(observer);
    }

    @Override
    public boolean place(Animal animal) {
        if (canMoveTo(animal.getPosition())){
            for (IPositionChangeObserver observer : observersForAnimals)
                animal.addObserver(observer);

            animalsList.put(animal.getPosition(), animal);
            return true;
        }
//        return false;
        throw new IllegalArgumentException( animal.getPosition() + " is not a valid tile to place an animal" );
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        if (objectAt(position) == null)
            return false;
        return true;
    }

    abstract public Vector2d getLowerLeft();
    abstract public Vector2d getUpperRight();

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

    public boolean canMoveTo(Vector2d position) {
        return !(objectAt(position) instanceof Animal);
    }

    public IMapElement objectAt(Vector2d position) {
        return animalsList.get(position);
    }

    public Set<Vector2d> objectsSet(){
        return animalsList.keySet();
    }

}
