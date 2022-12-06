import java.util.Scanner;

public class Mancala_Game {
    static final int MAX = 1, MIN = 0;
    int player;
    static Board initialBoard;

    public static void main(String[] args) {
        int h1,h2,depth;
        initialBoard = new Board();
        PrintBoard pb = new PrintBoard(initialBoard);

        Scanner scan=new Scanner(System.in);
        System.out.println("Enter Heuristic No. for Player1(MAX)<1~6> :");
        h1=scan.nextInt();
        System.out.println("Enter Heuristic No. for Player2(MIN)<1~6> :");
        h2=scan.nextInt();
        System.out.println("Enter Depth Limit:");
        depth=scan.nextInt();

        System.out.println("Initial board:");
        System.out.println("**********************************");
        System.out.println(pb.print());
        System.out.println("**********************************");
        System.out.println("Select Mode ----> \n 1.Human vs Computer\n2.Computer vs Computer");
        int mode = scan.nextInt();
        //System.out.println(initialBoard);

        int count=0,turn=0;

        while(true)
        {
            initialBoard.setFree_turn(false);
            if(mode == 2 || (mode==1 && turn == 0)) {applyComputerMove(depth,h1,h2,pb);}
            else if(mode == 1 && turn ==1 ){  applyHumanMove(depth,h1,h2,pb);}
            if(!initialBoard.isFree_turn()){
                turn = (turn+1)%2;
            }
            System.out.println(pb.print());
            if(initialBoard.gameOver()) break;

            if(mode==1){
                System.out.println("Human's Stone: "+initialBoard.getOwnStones());
                System.out.println("Computer's Stone: "+initialBoard.getOpponentStones());
            }
            else if(mode==2){
                System.out.println("1st Computer's stone: "+initialBoard.getOwnStones());
                System.out.println("2nd Computer's stone: "+initialBoard.getOpponentStones());
            }
            count++;
        }

       if(mode==1){
           if(initialBoard.getDiffernce()>0) System.out.println("Computer won");
           else System.out.println("Human won");
       }
       else if(mode==2){
           if(initialBoard.getDiffernce()>0) System.out.println("1st Computer won");
           else System.out.println("2nd Computer won");
       }
        System.out.println("Count: "+count);

    }

    public static void applyComputerMove(int depth,int h1,int h2,PrintBoard pb){
        MiniMax minimax1=new MiniMax(initialBoard,depth,h1,h2);
        Board board=minimax1.AlphaBetaSearch();
        if(board!=null)
        {
            initialBoard=board;
            pb.setBoard(initialBoard);
        }
        else System.out.println("Null board return.");
    }
    public static void applyHumanMove(int depth,int h1,int h2,PrintBoard pb){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of holes: ");
        int n = scanner.nextInt();
        initialBoard.applyMove(n+6);
        pb.setBoard(initialBoard);

    }
}
