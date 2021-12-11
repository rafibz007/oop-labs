package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class SimulationEngine implements IEngine, Runnable{
    private MoveDirection[] moves = new MoveDirection[]{};
    private IWorldMap map;
    private Vector2d[] positions;
    private List<Animal> animalsList;
    int moveDelay = 1000;

    public SimulationEngine(IWorldMap map, Vector2d[] positions){
        this.map = map;
        this.positions = positions;
        this.animalsList = new ArrayList<>();
        for(int i = 0; i < positions.length; i++){
            Animal animal = new Animal(map, positions[i]);
            map.place(animal);
            animalsList.add(animal);
        }
    }

    public SimulationEngine(MoveDirection[] moves, IWorldMap map, Vector2d[] positions){
        this(map, positions);
        this.moves = moves;
    }

    public SimulationEngine(IWorldMap map, Vector2d[] positions, int moveDelay){
        this(map, positions);
        this.moveDelay = moveDelay;
    }

    public SimulationEngine(MoveDirection[] moves, IWorldMap map, Vector2d[] positions, int moveDelay){
        this(map, positions);
        this.moves = moves;
        this.moveDelay = moveDelay;
    }


    @Override
    public synchronized void run() {
        if (animalsList.size() <= 0)
            return;

        for(int i=0; i<moves.length; i++){
            singleStep(i);
            try {
                Thread.sleep(moveDelay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void singleStep(int index){
        Animal animal = this.animalsList.get(index%animalsList.size());
        animal.moveDirection(moves[index]);
    }

    public void setMoves(MoveDirection[] moves) {
        this.moves = moves;
    }
}
