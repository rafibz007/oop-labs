package agh.ics.oop;

import java.util.*;

public class GrassField extends AbstractWorldMap{
    private final int grassTilesAmount;
    private Map <Vector2d, Grass> grassTiles;
    private Vector2d grassLowerLeft;
    private Vector2d grassUpperRight;

    public GrassField(int grassTilesAmount){
        super();
        this.grassTilesAmount = grassTilesAmount;
        this.grassTiles = new LinkedHashMap<>();

        int distance = (int)Math.floor(Math.sqrt(10*grassTilesAmount));
        this.grassLowerLeft = new Vector2d(0,0);
        this.grassUpperRight = new Vector2d( distance, distance );

        while (grassTiles.size()<grassTilesAmount){
            Vector2d newPosition = new Vector2d((int)Math.floor(Math.random()*(distance+1)), (int)Math.floor(Math.random()*(distance+1)));
            if (grassAt(newPosition) != null){
                continue;
            }
            grassTiles.put(newPosition, new Grass(newPosition));
        }
    }

    private Grass grassAt(Vector2d position){
        return grassTiles.get(position);
    }

    private Animal animalAt(Vector2d position){
        return animalsList.get(position);
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
    public boolean canMoveTo(Vector2d position) {
        return !(objectAt(position) instanceof Animal);
    }


    @Override
    protected Vector2d getLowerLeft() {
        Vector2d[] grassTilesKeys = grassTiles.keySet().toArray(new Vector2d[0]);
        Vector2d[] animalsListKeys = animalsList.keySet().toArray(new Vector2d[0]);

        Vector2d lowerLeft = grassTilesKeys.length > 0 ? grassTilesKeys[0] : null;
        lowerLeft = lowerLeft == null && animalsListKeys.length > 0 ? animalsListKeys[0] : lowerLeft;
        lowerLeft = lowerLeft == null ? new Vector2d(0,0) : lowerLeft;

        for (int i = animalsListKeys.length-1; i>=0; i--){
            assert animalsListKeys[i] != null;
            lowerLeft = lowerLeft.lowerLeft(animalsListKeys[i]);
        }

        for (int i= grassTiles.size()-1; i>=0; i--){
            assert grassTilesKeys[i] != null;
            lowerLeft = lowerLeft.lowerLeft(grassTilesKeys[i]);
        }
        return lowerLeft;
    }

    @Override
    public Vector2d getUpperRight() {
        Vector2d[] grassTilesKeys = grassTiles.keySet().toArray(new Vector2d[0]);
        Vector2d[] animalsListKeys = animalsList.keySet().toArray(new Vector2d[0]);

        Vector2d upperRight = grassTilesKeys.length > 0 ? grassTilesKeys[0] : null;
        upperRight = upperRight == null && animalsListKeys.length > 0 ? animalsListKeys[0] : upperRight;
        upperRight = upperRight == null ? new Vector2d(0,0) : upperRight;

        for (int i = animalsList.size()-1; i>=0; i--){
            assert animalsListKeys[i] != null;
            upperRight = upperRight.upperRight(animalsListKeys[i]);
        }

        for (int i= grassTiles.size()-1; i>=0; i--){
            assert grassTilesKeys[i] != null;
            upperRight = upperRight.upperRight(grassTilesKeys[i]);
        }
        return upperRight;
    }

    public void forTestingSetGrassTiles(Map<Vector2d, Grass> grassTiles){
        this.grassTiles = grassTiles;
    }
}
