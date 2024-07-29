import java.util.*;

class PlaySnakeAndLadder {
    public static void main(String args[]){
        Dice dice=new Dice(1);

        Player p1=new Player("Neha",1);
        Player p2=new Player("Abhi",2);
        p2.setPlayerName("Abhijeet");

        Queue<Player> allPlayers=new LinkedList<>();
        allPlayers.add(p1);
        allPlayers.add(p2);

        Jumper snake1=new Jumper(99,12);
        Jumper snake2=new Jumper(63,2);

        ArrayList<Jumper> snakes=new ArrayList<>();
        snakes.add(snake1);
        snakes.add(snake2);

        Jumper ladder1=new Jumper(7,88);
        Jumper ladder2=new Jumper(3,77);

        ArrayList<Jumper> ladders=new ArrayList<>();
        ladders.add(ladder1);
        ladders.add(ladder2);

        HashMap<String,Integer> currentPlayerPosition=new HashMap<>();
        currentPlayerPosition.put("Neha",0);
        currentPlayerPosition.put("Abhi",0);

        GameBoard gb=new GameBoard(dice,allPlayers,snakes,ladders,currentPlayerPosition,100);
        gb.startGame();

    }
}
