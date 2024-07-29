import java.util.*;
// Stats of single match
public class MatchStats{
    private Map<Integer,Player> players;
    private String status;
    private int playerId;
    public MatchStats(List<Player> listPlayers,String status,int playerId){
        this.players=new HashMap<>();
        this.status=status;
        this.playerId=playerId;

        for(int i=0;i<listPlayers.size();i++){
           players.put(listPlayers.get(i).getPlayerId(),listPlayers.get(i));
        }
    }

    Map<Integer,Player> getPlayers(){
        return players;
    }

    // return the player object correspond to playerId
    Player getPlayer(int playerId){
        return getPlayers().get(playerId);
    }

    String getStatus(){
        return status;
    }

    int getWinnerPlayerId(){
        return playerId;
    }
}