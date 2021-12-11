package agh.ics.oop;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Grass implements IMapElement{
    private final Vector2d position;

    public Grass(Vector2d position){
        this.position = position;
    }

    public Vector2d getPosition(){return position;}

    public String toString(){return "*";}

    @Override
    public ImageView guiRepresentationImageView() throws FileNotFoundException {
        Image image = new Image( new FileInputStream("src/main/resources/grass.png"));
        return new ImageView(image);
    }

    @Override
    public Label guiRepresentationLabel() {
        return new Label("Trawa");
    }
}
