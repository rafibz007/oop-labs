package agh.ics.oop;

public class Animal {
    private MapDirection mapDirection;
    private Vector2d position;
    private IWorldMap map;


    public Animal(IWorldMap map){
        this.mapDirection = MapDirection.NORTH;
        this.position = new Vector2d(2,2);
        this.map = map;
    }

    public Animal(IWorldMap map, Vector2d initialPosition){
        this.mapDirection = MapDirection.NORTH;
        this.position = initialPosition;
        this.map = map;
    }

    public boolean isAt(Vector2d position){
        return this.position.x == position.x && this.position.y == position.y;
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
        return switch (mapDirection){
            case NORTH -> "^";
            case SOUTH -> "v";
            case EAST -> ">";
            case WEST -> "<";
        };
    }

    public void moveDirection(MoveDirection direction){
        switch (direction){
            case FORWARD -> {
                Vector2d newPosition = position.add(mapDirection.toUnitVector());
                if (map.canMoveTo(newPosition)){
                    position = newPosition;
                }
            }
            case BACKWARD -> {
                Vector2d newPosition = position.subtract(mapDirection.toUnitVector());
                if (map.canMoveTo(newPosition)){
                    position = newPosition;
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
