package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class RectangularMap implements IWorldMap{
    public final int height;
    public final int width;
    public final Vector2d lowerLeft;
    public final Vector2d upperRight;

//    All animals on the map are stored in a list
//    In future can be changed to List<Object>
    public List<Animal> animalsList;



    public RectangularMap(int width, int height){
        this.height = height;
        this.width = width;
        this.lowerLeft = new Vector2d(0,0);
        this.upperRight = new Vector2d(width-1,height-1);
        this.animalsList = new ArrayList<>();
    }



    @Override
    public boolean isOccupied(Vector2d position) {
        if (objectAt(position) == null)
            return false;
        return true;
    }

    @Override
    public boolean place(Animal animal) {
        if (canMoveTo(animal.getPosition())) {
            animalsList.add(animal);
            return true;
        }
        return false;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        if ( !(position.follows(lowerLeft)) || !(position.precedes(upperRight)) ){
            return false;
        }
        return !isOccupied(position);
    }

    @Override
    public Object objectAt(Vector2d position) {
        for(int i = animalsList.size()-1; i>=0; i--){
            if (animalsList.get(i).getPosition().equals(position)){
                return animalsList.get(i);
            }
        }
        return null;
    }


    public String toString(){
        MapVisualizer visualizer = new MapVisualizer(this);
        return visualizer.draw(lowerLeft, upperRight);
    }
}
