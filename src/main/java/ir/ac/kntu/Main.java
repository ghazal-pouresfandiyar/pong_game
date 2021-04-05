package ir.ac.kntu;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Ghazal Pouresfandiyar
 */
public class Main extends Application {
    int pointA=0 , pointB=0 ;
    int deltaX = RandomHelper.nextInt(3) + 3;
    int deltaY = deltaX + 3;
    int tempX =deltaX;
    int tempY=deltaY;
    @Override
    public void start(Stage primaryStage) {
        //TODO: Assume that your program starts from this method
        Group root = new Group();
        Scene scene = new Scene(root, 700, 700, Color.GRAY);
        boolean random = RandomHelper.nextBoolean();
        Ball ball=new Ball(20);
        if (random) {
            ball.setColor(Color.RED);
        } else {
            ball.setColor(Color.BLUE);
        }
        Circle movingBall = new Circle(350, 350, ball.getRadius());
        movingBall.setFill(ball.getColor());
        Text text1 = new Text(100,25,"Point A:");
        Text point1 = new Text(190 ,25,pointA+"");
        text1.setFont(Font.font(25));
        point1.setFont(Font.font(25));
        point1.setFill(Color.PINK);
        Text text2 = new Text(500,25,"Point B:");
        Text point2 = new Text(590,25,pointB+"");
        text2.setFont(Font.font(25));
        point2.setFont(Font.font(25));
        point2.setFill(Color.PINK);
        Wall longWall=Wall.LONGWALL;
        Wall shortWall=Wall.SHORTWALL;
        Rectangle wall1 = new Rectangle(longWall.getWidth(), longWall.getHeight(), longWall.getColor());
        Rectangle wall2 = new Rectangle(shortWall.getWidth(), shortWall.getHeight(), shortWall.getColor());
        wall2.setX(680);
        wall2.setY(0);
        Button button = new Button("Start!");
        button.setScaleX(20);
        button.setScaleY(20);
        button.setTranslateX(350);
        button.setTranslateY(350);
        button.setFocusTraversable(false);
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton() == MouseButton.PRIMARY) {
                    root.getChildren().remove(button);
                    deltaY=tempY;
                    deltaX=tempX;
                    new AnimationTimer() {
                        @Override
                        public void handle(long l) {
                            movingBall.setCenterX(movingBall.getCenterX() + deltaX);
                            movingBall.setCenterY(movingBall.getCenterY() + deltaY);
                            boolean firstWall = movingBall.getCenterX() <= 40 && (movingBall.getCenterY() >= wall1.getY() && movingBall.getCenterY() <= (wall1.getY() + wall1.getHeight()));
                            boolean secondWall = movingBall.getCenterX() >= 640 && (movingBall.getCenterY() >= wall2.getY() && movingBall.getCenterY() <= (wall2.getY() + wall2.getHeight()));
                            boolean firstBorder = movingBall.getCenterX() <= 20;
                            boolean secondBorder = movingBall.getCenterX() >= 680;
                            if (firstWall == true) {
                                deltaX *= -1;
                            }
                            if (secondWall == true) {
                                deltaX *= -1;
                            }
                            if (firstWall == false && firstBorder == true) {
                                movingBall.setCenterX(350);
                                movingBall.setCenterY(350);
                                pointB++;
                                point2.setText(pointB + "");
                            }
                            if (secondWall == false && secondBorder == true) {
                                movingBall.setCenterX(350);
                                movingBall.setCenterY(350);
                                pointA++;
                                point1.setText(pointA + "");
                            }
                            if (movingBall.getCenterY() >= 680 || movingBall.getCenterY() <= 20) {
                                deltaY *= -1;
                            }
                        }
                    }.start();
                }
            }
        });
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.UP && wall2.getY() >= 10) {
                    if(wall2.getFill().equals(Color.YELLOW)) {
                        wall2.setY(wall2.getY() - shortWall.getSpeed());
                    }else{
                        wall2.setY(wall2.getY() - longWall.getSpeed());
                    }
                } else if (event.getCode() == KeyCode.DOWN && wall2.getY() <= 690-wall2.getHeight()) {
                    if(wall2.getFill().equals(Color.YELLOW)) {
                        wall2.setY(wall2.getY() + shortWall.getSpeed());
                    }else{
                        wall2.setY(wall2.getY() + longWall.getSpeed());
                    }
                } else if (event.getCode() == KeyCode.W && wall1.getY() >= 10) {
                    if(wall1.getFill().equals(Color.YELLOW)) {
                        wall1.setY(wall1.getY() - shortWall.getSpeed());
                    }else{
                        wall1.setY(wall1.getY() - longWall.getSpeed());
                    }
                } else if (event.getCode() == KeyCode.S && wall1.getY() <= 690-wall1.getHeight()) {
                    if(wall1.getFill().equals(Color.YELLOW)) {
                        wall1.setY(wall1.getY() + shortWall.getSpeed());
                    }else{
                        wall1.setY(wall1.getY() + longWall.getSpeed());
                    }
                }
                if(event.getCode()==KeyCode.SPACE){
                    button.setText("Resume");
                    button.setScaleX(15);
                    button.setScaleY(15);
                    root.getChildren().add(button);
                    deltaY=0;
                    deltaX=0;
                }
                if(event.getCode()==KeyCode.A){
                    if(wall1.getFill().equals(Color.YELLOW)) {
                        wall1.setWidth(longWall.getWidth());
                        wall1.setHeight(longWall.getHeight());
                        wall1.setFill(longWall.getColor());
                    }else{
                        wall1.setWidth(shortWall.getWidth());
                        wall1.setHeight(shortWall.getHeight());
                        wall1.setFill(shortWall.getColor());
                    }

                }
                if(event.getCode()==KeyCode.LEFT){
                    if(wall2.getFill().equals(Color.YELLOW)) {
                        wall2.setWidth(longWall.getWidth());
                        wall2.setHeight(longWall.getHeight());
                        wall2.setFill(longWall.getColor());
                    }else{
                        wall2.setWidth(shortWall.getWidth());
                        wall2.setHeight(shortWall.getHeight());
                        wall2.setFill(shortWall.getColor());
                    }
                }
            }
        });
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    movingBall.setCenterY(movingBall.getCenterY());
                });
            }
        };
        new Timer().schedule(task, 0, 30);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Pong game");
        primaryStage.setResizable(false);
        primaryStage.show();
        root.getChildren().addAll(text1, text2, point1, point2, wall1, wall2, movingBall, button);
    }

    @Override
    public void init() {
        System.out.println("Initializing...");
    }

    @Override
    public void stop() {
        System.out.println("Closing....");
    }
    public static void main(String[] args) {
        launch(args);
    }
}



