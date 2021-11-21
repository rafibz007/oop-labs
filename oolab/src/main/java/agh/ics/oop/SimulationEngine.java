package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class SimulationEngine implements IEngine{
    private MoveDirection[] moves;
    private IWorldMap map;
    private Vector2d[] positions;
    private List<Animal> animalsList;

    public SimulationEngine(MoveDirection[] moves, IWorldMap map, Vector2d[] positions){
        this.moves = moves;
        this.map = map;
        this.positions = positions;
        this.animalsList = new ArrayList<>();
        for(int i = 0; i < positions.length; i++){
            Animal animal = new Animal(map, positions[i]);
            map.place(animal);
            animalsList.add(animal);
        }
    }

    @Override
    public void run() {
        if (animalsList.size() <= 0)
            return;

        for(int i=0; i<moves.length; i++){
            Animal animal = this.animalsList.get(i%animalsList.size());
            animal.moveDirection(moves[i]);
        }
    }
}
