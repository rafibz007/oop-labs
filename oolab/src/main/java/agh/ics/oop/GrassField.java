package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class GrassField extends AbstractWorldMap{
    private final int grassTilesAmount;
    private List<Grass> grassTiles;
    private Vector2d grassLowerLeft;
    private Vector2d grassUpperRight;

    public GrassField(int grassTilesAmount){
        super();
        this.grassTilesAmount = grassTilesAmount;
        this.grassTiles = new ArrayList<>();

        int distance = (int)Math.floor(Math.sqrt(10*grassTilesAmount));
        this.grassLowerLeft = new Vector2d(0,0);
        this.grassUpperRight = new Vector2d( distance, distance );

        while (grassTiles.size()<grassTilesAmount){
            Vector2d newPosition = new Vector2d((int)Math.floor(Math.random()*(distance+1)), (int)Math.floor(Math.random()*(distance+1)));
            if (grassAt(newPosition) != null){
                continue;
            }
            grassTiles.add(new Grass(newPosition));
        }
    }

    private Grass grassAt(Vector2d position){
        for (int i=grassTiles.size()-1; i>=0; i--) {
            Grass tile = grassTiles.get(i);
            if (tile.getPosition().equals(position)){
                return tile;
            }
        }
        return null;
    }

    private Animal animalAt(Vector2d position){
        for (int i = animalsList.size()-1; i>=0; i--){
            Animal tile = animalsList.get(i);
            if (tile.getPosition().equals(position)){
                return tile;
            }
        }
        return null;
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
        Vector2d lowerLeft = grassTiles.get(0).getPosition();
        for (int i = animalsList.size()-1; i>=0; i--){
            lowerLeft = lowerLeft.lowerLeft(animalsList.get(i).getPosition());
        }

        for (int i= grassTiles.size()-1; i>=0; i--){
            lowerLeft = lowerLeft.lowerLeft(grassTiles.get(i).getPosition());
        }
        return lowerLeft;
    }

    @Override
    public Vector2d getUpperRight() {
        Vector2d upperRight = grassTiles.get(0).getPosition();
        for (int i = animalsList.size()-1; i>=0; i--){
            upperRight = upperRight.upperRight(animalsList.get(i).getPosition());
        }

        for (int i= grassTiles.size()-1; i>=0; i--){
            upperRight = upperRight.upperRight(grassTiles.get(i).getPosition());
        }
        return upperRight;
    }


    public void forTestingSetGrassTiles(List<Grass> grassTiles){
        this.grassTiles = grassTiles;
    }
}
