class Player{
   String PlayerName;
   int PlayerId;
   Player(String PlayerName,int PlayerId){
        this.PlayerName=PlayerName;
        this.PlayerId=PlayerId;
    }

    int getPlayerId(){
        return PlayerId;
    }
    String getPlayerName(){
        return PlayerName;
    }
}