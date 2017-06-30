package Objects;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

/**
 * Created by Misha on 30.06.2017.
 */
public class Walls extends Pane {

    Rectangle rect;
    public int height;
    public Walls(int height){
        this.height = height;
        rect = new Rectangle(20,height);

        getChildren().add(rect);
    }
}
