package agh.ics.oop;


public interface IPositionChangeObserver {

    /*
    * Change position of object on the map from oldPosition to newPosition
    * */
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition);
}
