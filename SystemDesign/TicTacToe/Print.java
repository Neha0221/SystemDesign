class Print{
    public static void main(String[] args) {
        Player p1=new Player("Neha",1);
        Player p2=new Player("Abhi",2);
        Player p3=new Player("Abhimanyu",3);
        GameBoard gb1=new GameBoard(2,p1,p2);
        gb1.Start();
        StatsHandler sh=new StatsHandler();
        sh.addMatchStats(gb1);
        GameBoard gb2=new GameBoard(3,p1,p3);
        gb2.Start();
        sh.addMatchStats(gb2);
        sh.getLeaderBoard();
    }
}