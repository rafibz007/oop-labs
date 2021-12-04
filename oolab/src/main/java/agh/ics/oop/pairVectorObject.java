package agh.ics.oop;

import java.util.Objects;

public class pairVectorObject {
    final public Vector2d vector;
    final public Object object;

    public pairVectorObject(Vector2d vector, Object object){
        this.vector = vector;
        this.object = object;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof pairVectorObject)) return false;
        pairVectorObject that = (pairVectorObject) o;
        return vector.equals(that.vector) && object.equals(that.object);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vector, object);
    }
}
