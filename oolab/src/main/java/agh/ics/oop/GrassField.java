package agh.ics.oop;

import java.util.*;

public class GrassField extends AbstractWorldMap{
    private final int grassTilesAmount;
    private Map <Vector2d, Grass> grassTiles;
    private MapBoundary mapBoundary;

    public GrassField(int grassTilesAmount){
        super();
        this.grassTilesAmount = grassTilesAmount;
        this.grassTiles = new LinkedHashMap<>();
        this.mapBoundary = new MapBoundary(this);

        int distance = (int)Math.floor(Math.sqrt(10*grassTilesAmount));

        while (grassTiles.size()<grassTilesAmount){
            Vector2d newPosition = new Vector2d((int)Math.floor(Math.random()*(distance+1)), (int)Math.floor(Math.random()*(distance+1)));
            if (grassAt(newPosition) != null){
                continue;
            }
            Grass grass = new Grass(newPosition);
            grassTiles.put(newPosition, grass);
            mapBoundary.addObject(newPosition, grass);
        }
    }

    private Grass grassAt(Vector2d position){
        return grassTiles.get(position);
    }

    private Animal animalAt(Vector2d position){
        return (Animal)super.objectAt(position);
    }

    @Override
    public Object objectAt(Vector2d position) {
        Animal animal = animalAt(position);
        if (animal != null)
            return animal;
        Grass grass = grassAt(position);
        if (grass != null)
            return grass;

        return null;
    }

    @Override
    public boolean place(Animal animal) {
        mapBoundary.addObject(animal.getPosition(), animal);
        animal.addObserver(mapBoundary);
        return super.place(animal);
    }

    @Override
    public Vector2d getLowerLeft() {
        return mapBoundary.getLowerLeft();
    }

    @Override
    public Vector2d getUpperRight() {
        return mapBoundary.getUpperRight();
    }

    public void forTestingSetGrassTiles(Map<Vector2d, Grass> grassTiles){
        this.grassTiles = grassTiles;
    }
}
