import java.util.HashMap;
import java.util.ArrayList;
import java.util.Queue;

class GameBoard {
    private Dice dice;
    private Queue<Player> nextTurn; 
    private ArrayList<Jumper> snakes;
    private ArrayList<Jumper> ladders;
    private HashMap<String,Integer> playerCurrentPosition;
    int boardSize;

    GameBoard(Dice dice,Queue<Player> nextTurn,ArrayList<Jumper> snakes,ArrayList<Jumper> ladders,HashMap<String,Integer> playerCurrentPosition,int boardSize){
        this.dice=dice;
        this.nextTurn=nextTurn;
        this.snakes=snakes;
        this.ladders=ladders;
        this.playerCurrentPosition=playerCurrentPosition;
        this.boardSize=boardSize;
    }

    public void startGame(){
        while(!nextTurn.isEmpty()){
            Player player=nextTurn.poll();
            int currentPosition=playerCurrentPosition.get(player.getPlayerName());
            int diceValue=dice.rollDice();
            int nextCell=currentPosition+diceValue;

            if(nextCell>boardSize){
                nextTurn.add(player);
            }
            else if(nextCell==boardSize){
                System.out.println(player.getPlayerName()+" won the match");
            }
            else{
                int nextPosition=nextCell;
                for(Jumper snake:snakes){
                    if(snake.getStartPoint()==nextPosition){
                        nextPosition=snake.getEndPoint();
                        System.out.println(player.getPlayerName()+" Bitten by Snake at : "+nextCell);
                        break;
                    }
                }   

                for(Jumper ladder:ladders){
                    if(ladder.getStartPoint()==nextPosition){
                        nextPosition=ladder.getEndPoint();
                        System.out.println(player.getPlayerName()+" Got ladder present at : "+nextCell);
                        break;
                    }
                }

                if(nextCell==boardSize){
                    System.out.println(player.getPlayerName()+" won the game");
                }

                else{
                    playerCurrentPosition.put(player.getPlayerName(),nextPosition);
                    System.out.println(player.getPlayerName()+" is at position : "+nextPosition);
                    nextTurn.add(player);
                }
            }

            
        }
    }

}
