package Objects;

import javafx.geometry.Point2D;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import javax.swing.*;

/**
 * Created by Misha on 30.06.2017.
 */
public class Bird extends Pane{

    public Point2D velocity = new Point2D(0,0);
    Rectangle rect;

    public Bird(){
        rect = new Rectangle(20,20, Color.RED);
        velocity = new Point2D(0,0);
        setTranslateX(300);
        setTranslateY(100);

        getChildren().add(rect);
    }

    public void moveY(int value){

        boolean movedown = value>0? true:false;

        for(int i = 0; i < Math.abs(value); i++){
            for(Walls w : FlappyBird.walls){
                if(this.getBoundsInParent().intersects(w.getBoundsInParent())){
                    if(movedown){
                        setTranslateY(getTranslateY()-1);
                        return;
                    }
                    else{
                        setTranslateY(getTranslateY()+1);
                        return;
                    }
                }
            }
            if(getTranslateY()<0){
                setTranslateY(0);
            }
            if(getTranslateY()>580){
                setTranslateY(580);
            }
            this.setTranslateY(getTranslateY()+(movedown?1:-1));
        }
    }
    public void moveX(int value) {

        for (int i = 0; i < value; i++) {
            setTranslateX(getTranslateX()+1);
            for (Walls w : FlappyBird.walls) {
                if (this.getBoundsInParent().intersects(w.getBoundsInParent())) {
                    if (getTranslateX() + 20 == w.getTranslateX()) {
                        setTranslateX(getTranslateX() - 1);
                        return;
                    }
                }

                if (getTranslateX() == w.getTranslateX()) {
                    FlappyBird.score++;
                    return;
                }
            }
        }
    }

    public void jump(){

        velocity = new Point2D(3,-10);
    }
}


