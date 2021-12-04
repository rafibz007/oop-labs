package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class RectangularMap extends AbstractWorldMap{
    private final int height;
    private final int width;
    private final Vector2d lowerLeft;
    private final Vector2d upperRight;


    public RectangularMap(int width, int height){
        super();
        this.height = height;
        this.width = width;
        this.lowerLeft = new Vector2d(0,0);
        this.upperRight = new Vector2d(width-1,height-1);
    }


    @Override
    public boolean canMoveTo(Vector2d position) {
        if ( !(position.follows(lowerLeft)) || !(position.precedes(upperRight)) ){
            return false;
        }
        return super.canMoveTo(position);
    }


    @Override
    public Vector2d getLowerLeft() {
        return lowerLeft;
    }

    @Override
    public Vector2d getUpperRight() {
        return upperRight;
    }
}
