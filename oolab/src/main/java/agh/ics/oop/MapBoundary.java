package agh.ics.oop;

import java.util.*;


public class MapBoundary implements IPositionChangeObserver{
    /* Contains all objects positions on the map, and amount of object on every tile
    * */
    private SortedSet<pairVectorObject> sortedXAxis;
    private SortedSet<pairVectorObject> sortedYAxis;
    private IWorldMap map;

    public MapBoundary(IWorldMap map){
        this.map = map;
        sortedXAxis = new TreeSet<>(new Comparator<pairVectorObject>() {
            @Override
            public int compare(pairVectorObject p1, pairVectorObject p2) {
                Vector2d v1 = p1.vector;
                Vector2d v2 = p2.vector;

                if (v1.x - v2.x != 0) return v1.x - v2.x;
                if (v1.y - v2.y != 0) return v1.y - v2.y;

                Object o1 = p1.object;
                Object o2 = p2.object;
                return objectToComparableInt(o1) - objectToComparableInt(o2);
            }
        });

        sortedYAxis = new TreeSet<>(new Comparator<pairVectorObject>() {
            @Override
            public int compare(pairVectorObject p1, pairVectorObject p2) {
                Vector2d v1 = p1.vector;
                Vector2d v2 = p2.vector;

                if (v1.y - v2.y != 0) return v1.y - v2.y;
                if (v1.x - v2.x != 0) return v1.x - v2.x;

                Object o1 = p1.object;
                Object o2 = p2.object;
                return objectToComparableInt(o1) - objectToComparableInt(o2);
            }
        });
    }

    private int objectToComparableInt(Object object){
        if (object instanceof Animal) return 2;
        if (object instanceof Grass) return 1;
        return 0;
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
//        Should take and move animal
        Object object = map.objectAt(newPosition);
        removeObject(oldPosition, object);
        addObject(newPosition, object);
    }

    public void addObject(Vector2d position, Object object){
        if (object == null)
            return;
        sortedXAxis.add( new pairVectorObject(position, object) );
        sortedYAxis.add( new pairVectorObject(position, object) );
    }

    public void removeObject(Vector2d position, Object object){
        if (object == null)
            return;
        sortedXAxis.remove( new pairVectorObject(position, object) );
        sortedYAxis.remove( new pairVectorObject(position, object) );

    }

    public Vector2d getLowerLeft(){
        return sortedXAxis.first().vector.lowerLeft(sortedYAxis.first().vector);
    }

    public Vector2d getUpperRight(){
        return sortedXAxis.last().vector.upperRight(sortedYAxis.last().vector);
    }
}
