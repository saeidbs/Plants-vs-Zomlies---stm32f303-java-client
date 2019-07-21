package sample.game.zombie;

import com.sun.webkit.network.Util;
import javafx.animation.AnimationTimer;
import javafx.animation.PathTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;
import sample.game.GameBoard;
import sample.utills.Utill;


public abstract class Zombie extends ImageView {
    private int kind, health;
    private static double speed = Utill.animationStep;
    int row, column;

    public Zombie(int kind, int row, int column) {
        this.row = row;
        this.column = column;
        this.kind = kind;
        this.health = kind;

        setLayoutX(getxPosition());
        setLayoutY(getyPosition());
        setFitWidth(Utill.zombieFitWidth);
        setFitHeight(Utill.zombieFitHeight);
        setImage(kind);
    }

    public void move(int row, int column) {
        //        ImageView imageView=new ImageView();

        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                Zombie.this.setY(Zombie.this.getY() + speed);
                if (Zombie.this.getY() > getyPosition() + rowToY(Math.abs(row - Zombie.this.row))) {
                    Zombie.this.row = row;
                    Zombie.this.column = column;
                    Zombie.this.setY(getyPosition());
                    this.stop();
                }

            }
        };


        animationTimer.start();

    }

    private void setImage(int kind) {
        String path = "";
        switch (kind) {
            case 1:
                path = "\\sample\\game\\zombie\\LevelOneZombie.png";
                break;
            case 2:
                path = "\\sample\\game\\zombie\\LevelTwoZombie.png";
                break;
            case 3:
                path = "\\sample\\game\\zombie\\LevelThreeZombie.png";
                break;
            case 4:
                path = "\\sample\\game\\zombie\\LevelFourZombie.png";
                break;
            case 5:
                path = "\\sample\\wormHalf.png";
                break;
        }
        super.setImage(new Image(path));


    }

    public int getxPosition() {
        return columnToX(column);
    }

    public int getKind() {
        return kind;
    }

    public int getHealth() {
        return health;
    }

    public int getyPosition() {
        return rowToY(row);
    }

    public static int rowToY(int row) {
        return row * Utill.zombieFitHeight;
    }

    public static int columnToX(int column) {
        return column * Utill.zombieFitWidth;
    }

    public static void setZombieSpeed(int level) {
        speed = Utill.animationStep * Math.pow(1.5, level - 1);
    }


}
