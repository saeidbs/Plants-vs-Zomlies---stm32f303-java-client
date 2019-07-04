package sample.game.zombie;

import javafx.animation.PathTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;
import javafx.util.Duration;
import sample.game.GameBoard;
import sample.utills.Utill;



public abstract class Zombie extends ImageView {
    private int kind,health;
    private int xPosition,yPosition;
    private int size=5* Utill.screenUnit;
    
    public Zombie(int kind, int xPosition, int yPosition) {
        this.kind=kind;
        this.health=kind;
        this.xPosition=xPosition;
        this.yPosition=yPosition;
        setImage(kind);
    }
    public  void move(){
        //        ImageView imageView=new ImageView();
//        imageView.setImage(new Image("\\sample\\wormDamage.png"));
//        imageView.setFitHeight(50);
//        imageView.setFitWidth(50);
//        imageView.setX(50);
//        imageView.setY(50);
//        gridPane.add(imageView,2,2);
     Zombie mainZombie=this;

        int index=  GameBoard.getGridPane().getChildren().indexOf(this);
       PathTransition pathTransition=new PathTransition();
       pathTransition.setDuration(Duration.millis(200));

       Line line=new Line(getxPosition(),getyPosition(),getxPosition(),getyPosition()+Utill.boardGameDistanceY);
        setyPosition(getyPosition()+Utill.boardGameDistanceY);
        System.out.println(getyPosition());
       pathTransition.setNode(this);
       pathTransition.setPath(line);
       pathTransition.play();
       pathTransition.setOnFinished(new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent actionEvent) {
               Zombie change;
               switch (kind){
                   case 1: change=new LevelOneZombie(getxPosition(),getyPosition());
                            break;
                   case 5:change=new TempZombie(getxPosition(),getyPosition());


                       break;
                   default:
                      change=new LevelOneZombie(getxPosition(),getyPosition());
                      break;
               }

                 GameBoard.replaceZombie(mainZombie,change);
               System.out.println("hmmmmmmmmmmm"+change.getxPosition()+","+change.getyPosition());
                 for (int i=0;i<GameBoard.getGridPane().getChildren().size();i++){
                   Zombie findZombie= (Zombie) GameBoard.getGridPane().getChildren().get(i);
                   if (findZombie.getxPosition()==change.getxPosition()&&findZombie.getyPosition()==change.getyPosition()) {
                       System.out.println("find");
                     GameBoard.replaceZombie(change,findZombie);
                       break;
                   }

               }
               System.out.println(index);
           }
       });


    }
    private   void setImage(int kind){
        String path="dasdas";
        switch (kind){
            case 1 :path="\\sample\\wormDamage.png";

            break;
            case  5: path="\\sample\\wormHalf.png";
            break;
        }
                super.setImage(new Image(path));


    }

    public int getxPosition() {
        return xPosition;
    }

    public int getKind() {
        return kind;
    }

    public int getHealth() {
        return health;
    }

    public int getyPosition() {
        return yPosition;
    }

    public int getSize() {
        return size;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setxPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public void setyPosition(int yPosition) {
        this.yPosition = yPosition;
    }
}
