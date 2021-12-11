package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import javafx.event.ActionEvent;

public class App extends Application implements IPositionChangeObserver {

    AbstractWorldMap map;
    Vector2d[] positions;
    SimulationEngine engine;

    Vector2d lowerLeft;
    Vector2d upperRight;

    Map<Vector2d,GuiElementBox> drawnElementsPositions;

    Stage window;
    Thread thread;
    GridPane gridPane;
    Scene scene;


    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        drawGrid();
        window.setScene(scene);
        window.setTitle("Simulation");
//        window.setFullScreen(true);
        window.show();

    }

    @Override
    public void init() {
//        SETTING UP ENGINE
        int moveDelay = 500;
        map = new GrassField(3);
        //map = new RectangularMap(5, 5);
        map.addObserverForAnimals(this);
        positions = new Vector2d[] { new Vector2d(0,0), new Vector2d(1,0) };
        engine = new SimulationEngine(map, positions, moveDelay);

//        SETTING UP GUI
        drawnElementsPositions = new HashMap<>();

        BorderPane borderPane = new BorderPane();
        scene = new Scene(borderPane, 400, 400);

        gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);

        VBox vBox = new VBox();
        TextField textField = new TextField("f r b l");
        vBox.getChildren().addAll(textField, gridPane);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);
        BorderPane.setMargin(vBox, new Insets(10));

        Button button = new Button("RUN");
        button.setOnAction( (ActionEvent event) -> {
            String[] args = textField.getText().split(" ");
            engine.setMoves(OptionParser.parse(args));
            Thread thread = new Thread(engine);
            thread.start();
        } );
        vBox.getChildren().add(1,button);


        borderPane.setTop(vBox);

    }


    public void drawGrid() throws FileNotFoundException {
        int rowSize = 30;
        int columnSize = 30;

        gridPane.setGridLinesVisible(false);
        gridPane.getChildren().clear();
        gridPane.getColumnConstraints().clear();
        gridPane.getRowConstraints().clear();

        Label label = new Label("y\\x");
        gridPane.add(label, 0 , 0, 1, 1);
        gridPane.getColumnConstraints().add(new ColumnConstraints(columnSize));
        gridPane.getRowConstraints().add(new RowConstraints(rowSize));
        GridPane.setHalignment(label, HPos.CENTER);
        gridPane.setGridLinesVisible(true);


//        DRAW BORDER
        upperRight = map.getUpperRight();
        lowerLeft = map.getLowerLeft();

        for (int i=0; i<= upperRight.x- lowerLeft.x; i++){
            gridPane.getColumnConstraints().add(new ColumnConstraints(columnSize));
            Label index = new Label(String.valueOf(i+ lowerLeft.x));
            gridPane.add(index, i+1 ,0);
            GridPane.setHalignment(index, HPos.CENTER);
        }

        for (int i=0; i<= upperRight.y- lowerLeft.y; i++){
            gridPane.getRowConstraints().add(new RowConstraints(rowSize));
            Label index = new Label(String.valueOf(upperRight.y-i));
            gridPane.add(index, 0 ,i+1);
            GridPane.setHalignment(index, HPos.CENTER);
        }

//        DRAW OBJECTS
        drawnElementsPositions.clear();
        for (Vector2d position : map.objectsSet()){
            addDrawnObject(position);
        }
    }

    public void updatePosition(Vector2d oldPosition, Vector2d newPosition) throws FileNotFoundException {

        removeDrawnObject(oldPosition);
        removeDrawnObject(newPosition);

        if (map.isOccupied(oldPosition) && !oldPosition.equals(newPosition))
            addDrawnObject(oldPosition);

        addDrawnObject(newPosition);

    }

    private void removeDrawnObject(Vector2d position){
        if (drawnElementsPositions.containsKey(position)) {
            gridPane.getChildren().remove(drawnElementsPositions.get(position).getVbox());
            drawnElementsPositions.remove(position);
        }
    }

    private void addDrawnObject(Vector2d position) throws FileNotFoundException {
        if (!drawnElementsPositions.containsKey(position)){
            GuiElementBox elementBox = new GuiElementBox(map.objectAt(position));
            gridPane.add(elementBox.getVbox(), mapToGuiX(position), mapToGuiY(position));
            GridPane.setHalignment(elementBox.getVbox(), HPos.CENTER);

            drawnElementsPositions.put(position, elementBox);
        }
    }

    private int mapToGuiX(Vector2d position){
        return -lowerLeft.x+position.x+1;
    }
    private int mapToGuiY(Vector2d position){
        return upperRight.y-position.y+1;
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        Platform.runLater(()->{
            if (!lowerLeft.equals(map.getLowerLeft()) || !upperRight.equals(map.getUpperRight())){
                try {
                    drawGrid();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    updatePosition(oldPosition, newPosition);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
