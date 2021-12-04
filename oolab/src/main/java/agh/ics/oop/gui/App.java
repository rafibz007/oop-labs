package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import java.util.Arrays;

import static java.lang.System.out;

public class App extends Application {
    String[] args;
    MoveDirection[] directions;
    AbstractWorldMap map;
    Vector2d[] positions;
    IEngine engine;

    @Override
    public void start(Stage primaryStage) throws Exception {

        int rowSize = 25;
        int columnSize = 25;

        GridPane gridPane = new GridPane();
        gridPane.setGridLinesVisible(true);


        Label label = new Label("y\\x");
        gridPane.add(label, 0 , 0, 1, 1);
        gridPane.getColumnConstraints().add(new ColumnConstraints(columnSize));
        gridPane.getRowConstraints().add(new RowConstraints(rowSize));
        GridPane.setHalignment(label, HPos.CENTER);


//        DRAW BORDER
        Vector2d lowerLeft = map.getLowerLeft();
        Vector2d upperRight = map.getUpperRight();

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
        for (int i=0; i<=upperRight.x- lowerLeft.x; i++){

            for (int j=0; j<= upperRight.y- lowerLeft.y; j++){

                Vector2d position = new Vector2d( i + lowerLeft.x, upperRight.y-j );
                Object object = map.objectAt(position);

                if (object != null){
                    Label labelObject = new Label(object.toString());
                    gridPane.add( labelObject, i+1, j+1 );
                    GridPane.setHalignment(labelObject, HPos.CENTER);
                }
            }
        }


        Scene scene = new Scene(gridPane, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void init() throws IllegalArgumentException {
        args = getParameters().getRaw().toArray( new String[0] );
        directions = OptionParser.parse(args);
        map = new GrassField(5);
//        map = new RectangularMap(5, 5);
        positions = new Vector2d[] { new Vector2d(0,0), new Vector2d(1,0) };
        engine = new SimulationEngine(directions, map, positions);

        engine.run();
//        out.println(map);
    }
}
