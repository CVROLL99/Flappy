package Objects;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Misha on 30.06.2017.
 */
    public class FlappyBird extends Application {

        public static Pane appRoot = new Pane();//панель самого приложения
        public static Pane gameRoot = new Pane();//поле где расположены стенки
        public static ArrayList<Walls> walls = new ArrayList<>();
        public Bird bird = new Bird();
        public static int score = 0;
        public Label scorelabel = new Label();

    public Parent createContent() {

        gameRoot.setPrefSize(600,600);

        for(int i = 0; i < 100; i++){
            int enter = (int) (Math.random()*100+50);
            int height = new Random().nextInt(600-enter);
            Walls wall = new Walls(height);

            wall.setTranslateX(i*350+600);
            wall.setTranslateY(0);
            walls.add(wall);

            Walls wall2 = new Walls(600-enter-height);
            wall2.setTranslateX(i*350+600);
            wall2.setTranslateY(height+enter);
            walls.add(wall2);

            gameRoot.getChildren().addAll(wall,wall2);

        }
        gameRoot.getChildren().add(bird);
        appRoot.getChildren().addAll(gameRoot,scorelabel);
        return appRoot;
    }

    public void update(){

        if(bird.velocity.getY()<5){
            bird.velocity = bird.velocity.add(0,1);
        }

        bird.moveX((int)bird.velocity.getX());
        bird.moveY((int)bird.velocity.getY());

        scorelabel.setText("score: " + score);

        bird.translateXProperty().addListener((obs,old,newValue)->{
            int offset = newValue.intValue();
            if(offset>200)gameRoot.setLayoutX(-(offset-200));
        });
    }


        @Override
        public void start(Stage primaryStage) throws Exception{

           Scene scene = new Scene(createContent());
           scene.setOnMouseClicked(event->bird.jump());
           primaryStage.setScene(scene);
           primaryStage.show();

            AnimationTimer timer = new AnimationTimer() {
                @Override
                public void handle(long now) {
                    update();
                }
            };
            timer.start();
        }


        public static void main(String[] args) {

            launch(args);
        }
}


