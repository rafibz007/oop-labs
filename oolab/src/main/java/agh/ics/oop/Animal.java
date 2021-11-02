package agh.ics.oop;

public class Animal {
    private MapDirection mapDirection;
    private Vector2d position;

    private final static Vector2d lowerLeftFieldCorner = new Vector2d(0,0);
    private final static Vector2d upperRightFieldCorner = new Vector2d(4,4);

    public Animal(){
        this.mapDirection = MapDirection.NORTH;
        this.position = new Vector2d(2,2);
    }

    public MapDirection getMapDirection() {
        return mapDirection;
    }

    public Vector2d getPosition() {
        return position;
    }

    public void forTestingSetPositionAndDirection(int x, int y, MapDirection direction){
        this.position = new Vector2d(x,y);
        this.mapDirection = direction;
    }

    public String toString(){
        return this.position.toString() + ":" + this.mapDirection.toString();
    }

    public void moveDirection(MoveDirection direction){
        switch (direction){
            case FORWARD -> {
                Vector2d newPosition = this.position.add(this.mapDirection.toUnitVector());
                if (newPosition.precedes(upperRightFieldCorner) && newPosition.follows(lowerLeftFieldCorner)) {
                    this.position = newPosition;
                }
            }
            case BACKWARD -> {
                Vector2d newPosition = this.position.subtract(this.mapDirection.toUnitVector());
                if (newPosition.precedes(upperRightFieldCorner) && newPosition.follows(lowerLeftFieldCorner)) {
                    this.position = newPosition;
                }
            }
            case LEFT -> this.mapDirection = this.mapDirection.previous();
            case RIGHT -> this.mapDirection = this.mapDirection.next();
        }
    }

//    public boolean equals(Object other){
//        if (this == other)
//            return true;
//        if (!(other instanceof Animal))
//            return false;
//        Animal that = (Animal) other;
//        return this.position.equals(that.position) && this.mapDirection.equals(that.mapDirection);
//    }

}
