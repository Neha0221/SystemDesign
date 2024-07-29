// package SystemDesign.TicTacToe;

import java.util.*;
class StatsHandler{
    // stats of all match 
    private List<MatchStats> statsHistory;
    public StatsHandler(){
        this.statsHistory = new ArrayList<>();
    }

    void addMatchStats(GameBoard gb){
        List<Player> players=new ArrayList<>();
        players.add(gb.p1);
        players.add(gb.p2);
        MatchStats obj=new MatchStats(players,gb.status,gb.winnerPlayerId);
        this.statsHistory.add(obj);

    }
    void getPlayerStats(int PlayerId){

    }
    void getLeaderBoard(){
       /*player name
        * no.of win
        * no.of loss the match
        * no.of draws
        */

        int n=statsHistory.size();
        HashMap<Integer,Integer> winnersMap=new HashMap<>();
        HashMap<Integer,Integer> drawMap=new HashMap<>();
        HashMap<Integer,Integer> playingPlayerMap=new HashMap<>();
        for(int i=0;i<n;i++){
            MatchStats info=statsHistory.get(i);
            // I have to print playerName and its stats with include loss win draw
            String status=info.getStatus();
            if(status=="FINISHED"){
                int winnerId=info.getWinnerPlayerId();
                Player p=info.getPlayer(winnerId);

                winnersMap.put(winnerId,winnersMap.getOrDefault(winnerId,0)+1);
                
            }
           
            if(status=="DRAW"){
                Map<Integer,Player> map=info.getPlayers();
                for(Map.Entry<Integer,Player> entry:map.entrySet()){
                    int drawMatchPlayerId=entry.getKey();
                    drawMap.put(drawMatchPlayerId,drawMap.getOrDefault(drawMatchPlayerId,0)+1);
                }
               
            }

            Map<Integer,Player> map=info.getPlayers();
                for(Map.Entry<Integer,Player> entry:map.entrySet()){
                    int playMatchPlayerId=entry.getKey();
                    playingPlayerMap.put(playMatchPlayerId,playingPlayerMap.getOrDefault(playMatchPlayerId,0)+1);
                }
               
           
        }


        System.out.println("WinnerPlayer : "+winnersMap);
        System.out.println("DrawMatchPlayer : "+drawMap);
        System.out.println("playingPlayerMap : "+playingPlayerMap);

        ArrayList<PlayersStats> list=new ArrayList<>();
        for(Map.Entry<Integer,Integer> entry:playingPlayerMap.entrySet()){
            int playerId=entry.getKey();
            int winCnt=winnersMap.getOrDefault(playerId,0);
            int drawCnt=drawMap.getOrDefault(playerId,0);

            int totalCnt=playingPlayerMap.get(playerId);
            int lossCnt=totalCnt-(winCnt+drawCnt);

            PlayersStats obj=new PlayersStats(playerId,winCnt,drawCnt,lossCnt);
            list.add(obj);
        
        }
        
         Collections.sort(list,new Comparator<>(){
                public int compare(PlayersStats a,PlayersStats b){
                        
                        if(a.noOfWin!=b.noOfWin){
                            return b.noOfWin-a.noOfWin;
                        }

                        else if(a.noOfWin==b.noOfWin){
                            return a.noOfLoss-b.noOfLoss;
                        }

                        else if(b.noOfLoss==a.noOfLoss){
                            return b.noOfDraw-a.noOfDraw;
                        }

                       
                        return a.playerId-b.playerId;
                                

                }
            });

            for(int i=0;i<list.size();i++){
                PlayersStats plStats=list.get(i);
                System.out.print("playerId : "+plStats.playerId+" Rank:"+(i+1)+ " winCnt:"+plStats.noOfWin+" lossCnt:"+plStats.noOfLoss+" drawCnt:"+plStats.noOfDraw);
                System.out.println();
            }

    }   
}