import java.util.HashMap;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Scanner;

class GameBoard {
    int n;
    char firstPlayerSymbol = 'x';
    char secondPlayerSymbol = 'o';
    char matrix[][];
    char defaultSymbol = ' ';
    Queue<Player> turnQueue;
    HashMap<Integer, Character> playerSymbolMap;
    String status;
    Player p1;
    Player p2;  
    int winnerPlayerId;
	GameBoard(int n,Player p1,Player p2){
		this.n=n;
		this.turnQueue=new LinkedList<>();
		turnQueue.add(p1);
		turnQueue.add(p2);
		this.playerSymbolMap=new HashMap<>();
		playerSymbolMap.put(p1.getPlayerId(),this.firstPlayerSymbol);
		playerSymbolMap.put(p2.getPlayerId(),this.secondPlayerSymbol);
		this.matrix=new char[n][n];
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				matrix[i][j]=defaultSymbol;
			}
		}
		this.status="NOTSTARTED";
		System.out.println("GameBoard successfully intialized");
        this.p1=p1;
        this.p2=p2;
        this.winnerPlayerId=winnerPlayerId;
	}
    String findCurrentStatus(){
        boolean isDiagonal1 = true;
        boolean isDiagonal2 = true;
        boolean isCol = false;
        boolean isRow = false;

        for (int i = 0; i < n; i++) {
            if (matrix[i][i]==defaultSymbol || matrix[i][i] != matrix[0][0]) {
                isDiagonal1 = false;
                break;
            }
        }

        for (int i = 0; i < n; i++) {
            if (matrix[i][n - 1 - i]==defaultSymbol || matrix[i][n - 1 - i] != matrix[0][n - 1]) {
                isDiagonal2 = false;
                break;
            }
        }

        for (int i = 0; i < n; i++) {
            int firstEle = matrix[i][0];
            boolean isCurrentRow = true;
            for (int j = 1; j < n; j++) {
                if (matrix[i][j]==defaultSymbol || firstEle != matrix[i][j]) {
                    isCurrentRow = false;
                    break;
                }
            }
              if (isCurrentRow) {
                    isRow = true;
                    break;
                }
        }

       

        for (int i = 0; i < n; i++) {
            int firstEle = matrix[0][i];
            boolean isCurrentCol = true;
            for (int j = 1; j < n; j++) {
                if (matrix[j][i]==defaultSymbol || firstEle != matrix[j][i]) {
                    isCurrentCol = false;
                    break;
                }
               
            }
             if (isCurrentCol) {
                    isCol = true;
                    break;
                }
        }

        System.out.print("isDiagonal1 : "+isDiagonal1+" isDiagonal2 : "+isDiagonal2+" isCol : "+isCol+" isRow : "+isRow);
        System.out.println();
        if (isDiagonal1==true || isDiagonal2==true || isCol==true || isRow==true) {
            return "FINISHED";
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == defaultSymbol) {
                    return "RUNNING";
                }
            }
        }

        return "DRAW";
    }

    void Start() {
		System.out.println("Game is Runnig ");
        status = "RUNNING";
        Player currentPlayer=null;
        while (status.equals("RUNNING")) {
            currentPlayer = turnQueue.poll();
			System.out.println(currentPlayer.getPlayerName()+"'s turn");
            int player_id = currentPlayer.getPlayerId();
            char symbol = playerSymbolMap.get(player_id);
            turnQueue.add(currentPlayer);
            BoardPosition currentBoardPosition = getUserOperation();
            matrix[currentBoardPosition.row][currentBoardPosition.col] = symbol;
            printBoard();
            status = findCurrentStatus();

        }

        if (status.equals("FINISHED")) {
            winnerPlayerId=currentPlayer.getPlayerId();
            System.out.println("Winner Player is: "+currentPlayer.getPlayerName());
        } else if (status.equals("DRAW")) {
            System.out.println("No winner, match is Draw");
        }
    }


    BoardPosition getUserOperation() {
        int row = -1;
        int col = -1;
        while (row < 0 || col < 0 || row >= n || col >= n || matrix[row][col] != defaultSymbol) {
			Scanner sc=new Scanner(System.in);
			System.out.println("Enter row and col: ");
            row = sc.nextInt();
            col = sc.nextInt();
        }

        BoardPosition obj = new BoardPosition(row, col);
        return obj;
    }

	void printBoard(){
		System.out.println("CurrentBoard");
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				if(matrix[i][j]==defaultSymbol){
					System.out.print("_");
				}
				else{
					System.out.print(matrix[i][j]);
				}
			}
            System.out.println();
		}
	}
}
































	