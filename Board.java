
import java.util.ArrayList;

public class Board
{

    public Board()
    {
        int INITIAL_SEEDS = 4;
        state = new int[TOTAL_HOLES];
        for (int i=0; i<TOTAL_HOLES; i++)
        {
            state[i] = INITIAL_SEEDS;
        }
        state[MAX_SCORE_HOLE] = 0;
        state[MIN_SCORE_HOLE] = 0;
        player = MAX;
        prevPlayer=MIN;
    }

    public Board(Board cb)//create duplicate copy of board.
    {
        this.state=new int[TOTAL_HOLES];
        for(int i=0;i<TOTAL_HOLES;i++)
        {
            this.state[i]=cb.state[i];
        }
        this.player=cb.player;
        this.free_turn = cb.free_turn;
    }

    private int MAX_SCORE_HOLE = 6, MIN_SCORE_HOLE = 13, TOTAL_HOLES = 14, PLAY_HOLES = 6;
    private int MAX=1,MIN=0;
   private int[] state = new int[14];
   private int player;
   private int W1=2;
   private int W2=2;
   private int W3=3;
   private int W4=3;
   int  extraMove=0;
   int prevPlayer;
   int stoneCaptured=0;
   boolean free_turn=false;
    
    public void setPlayer(int player)
    {
        this.player=player;
    }
    public int getPlayer()
    {
        return player;
    }

    public boolean isFree_turn() {
        return free_turn;
    }

    public void setFree_turn(boolean free_turn) {
        this.free_turn = free_turn;
    }

    public int getMAX_SCORE_HOLE() {
        return MAX_SCORE_HOLE;
    }

    public void setMAX_SCORE_HOLE(int MAX_SCORE_HOLE) {
        this.MAX_SCORE_HOLE = MAX_SCORE_HOLE;
    }

    public int getMIN_SCORE_HOLE() {
        return MIN_SCORE_HOLE;
    }

    public void setMIN_SCORE_HOLE(int MIN_SCORE_HOLE) {
        this.MIN_SCORE_HOLE = MIN_SCORE_HOLE;
    }

    public int getTOTAL_HOLES() {
        return TOTAL_HOLES;
    }

    public void setTOTAL_HOLES(int TOTAL_HOLES) {
        this.TOTAL_HOLES = TOTAL_HOLES;
    }

    public int getPLAY_HOLES() {
        return PLAY_HOLES;
    }

    public void setPLAY_HOLES(int PLAY_HOLES) {
        this.PLAY_HOLES = PLAY_HOLES;
    }

    public int getMAX() {
        return MAX;
    }

    public void setMAX(int MAX) {
        this.MAX = MAX;
    }

    public int getMIN() {
        return MIN;
    }

    public void setMIN(int MIN) {
        this.MIN = MIN;
    }

    public int[] getState() {
        return state;
    }


    public void applyMove(int pos)
    {
       if(player==prevPlayer)
        {
            extraMove++;
        }
        else
        {
            extraMove=0;
            prevPlayer=player;
        }
        int scoreHole, oppositeHole;
        int seeds = state[pos];
        state[pos] = 0;
        // Redistribute around the holes, skipping the opponent's scoring hole.
        while(seeds>0)
        {
            pos=(pos+1)%TOTAL_HOLES;
            // Skip over opponent's scoring hole
            if (pos!=((player == MAX)? MIN_SCORE_HOLE : MAX_SCORE_HOLE))
            {
                state[pos]++;
                seeds--;
            }
        }
        //here now 'pos' is the last distributed seed location
        scoreHole = (player == MAX)? MAX_SCORE_HOLE : MIN_SCORE_HOLE;
        if ((state[pos]==1)&&((scoreHole-pos)>0)&&(scoreHole-pos <=PLAY_HOLES))
        {
            oppositeHole = MIN_SCORE_HOLE-pos -1;

            if (state[oppositeHole] > 0) {
                // capture own seeds
                state[scoreHole] ++;
                state[pos] --;
                // capture opposite seeds
                state[scoreHole] += state[oppositeHole];
                stoneCaptured=state[oppositeHole];
                state[oppositeHole] = 0;
                //System.out.println("Captured "+stoneCaptured+" stones");
            }
            else
            {
                stoneCaptured=0;
            }
        }
        if (pos != scoreHole){
            player=(player == MAX)?MIN:MAX;
        }
        else {
           // System.out.println("Got free turn");
            free_turn=true;
        }
        //int socreHole2=scoreHole;
        //check next player's  own side is empty or not
        boolean isFinish = true;

        scoreHole = (player == MAX) ? MAX_SCORE_HOLE : MIN_SCORE_HOLE;

        for (pos = scoreHole-PLAY_HOLES;pos < scoreHole; pos++)
        {
            if (state[pos]>0)
            {
                isFinish = false;
                break;
            }
        }

        if (isFinish)
        {
            scoreHole = (scoreHole+PLAY_HOLES +1) % TOTAL_HOLES;
            for (pos = scoreHole-PLAY_HOLES;pos < scoreHole; pos++)
            {
                state[scoreHole] += state[pos];
                state[pos] = 0;
            }
        }
        //check opponent side
        boolean isFinish1=true;
        scoreHole = (player == MAX) ? MAX_SCORE_HOLE : MIN_SCORE_HOLE;
        for (pos = (scoreHole+1)%TOTAL_HOLES;pos <(scoreHole+PLAY_HOLES+1)%TOTAL_HOLES; pos++)
        {
            if (state[pos]>0)
            {
                isFinish1 = false;
                break;
            }
        }

        if (isFinish1)
        {
            //scoreHole = (scoreHole+PLAY_HOLES +1) % TOTAL_HOLES;
            for (pos = scoreHole-PLAY_HOLES;pos <scoreHole; pos++)
            {
                state[scoreHole] += state[pos];
                state[pos] = 0;
            }
        }

    }


    public void setState(int[] state) {
        this.state = state;
    }

    public int getW1() {
        return W1;
    }

    public void setW1(int w1) {
        W1 = w1;
    }

    public int getW2() {
        return W2;
    }

    public void setW2(int w2) {
        W2 = w2;
    }

    public int getW3() {
        return W3;
    }

    public void setW3(int w3) {
        W3 = w3;
    }

    public int getW4() {
        return W4;
    }

    public void setW4(int w4) {
        W4 = w4;
    }

    public int getExtraMove() {
        return extraMove;
    }

    public void setExtraMove(int extraMove) {
        this.extraMove = extraMove;
    }

    public int getPrevPlayer() {
        return prevPlayer;
    }

    public void setPrevPlayer(int prevPlayer) {
        this.prevPlayer = prevPlayer;
    }

    public int getStoneCaptured() {
        return stoneCaptured;
    }

    public void setStoneCaptured(int stoneCaptured) {
        this.stoneCaptured = stoneCaptured;
    }

    public int getHeuristic(int maxval,int minval)
    {
        int hVal=(player==MAX)?maxval:minval;
        if(hVal==1)
        {
            if(player==MIN) return state[MIN_SCORE_HOLE]-state[MAX_SCORE_HOLE];
            else return state[MAX_SCORE_HOLE]-state[MIN_SCORE_HOLE];
        }
        else if(hVal==2)
        {
            if(player==MIN) return W1*(state[MIN_SCORE_HOLE]-state[MAX_SCORE_HOLE])+W2*(getOwnStones()-getOpponentStones());
            else return W1*(state[MAX_SCORE_HOLE]-state[MIN_SCORE_HOLE])+W2*(getOwnStones()-getOpponentStones());
        }
        else if(hVal==3)
        {
            //System.out.println("extraMove: "+extraMove);
            if(player==MIN) return W1*(state[MIN_SCORE_HOLE]-state[MAX_SCORE_HOLE])+W2*(getOwnStones()-getOpponentStones())+W3*extraMove;
            else return W1*(state[MAX_SCORE_HOLE]-state[MIN_SCORE_HOLE])+W2*(getOwnStones()-getOpponentStones())+W3*extraMove;
        }
        else if(hVal==4){
            if(player==MIN) return W1*(state[MIN_SCORE_HOLE]-state[MAX_SCORE_HOLE])+W2*(getOwnStones()-getOpponentStones())+W4*stoneCaptured;
            else return W1*(state[MAX_SCORE_HOLE]-state[MIN_SCORE_HOLE])+W2*(getOwnStones()-getOpponentStones())+W4*stoneCaptured;
        }
        else if(hVal==5)
        {
            //System.out.println("stoneCaptured: "+stoneCaptured);
            if(player==MIN) return W1*(state[MIN_SCORE_HOLE]-state[MAX_SCORE_HOLE])+W2*(getOwnStones()-getOpponentStones())+W3*extraMove+W4*stoneCaptured;
            else return W1*(state[MAX_SCORE_HOLE]-state[MIN_SCORE_HOLE])+W3*extraMove+W4*stoneCaptured;
        }
        else if(hVal==6){
            if(player==MIN) return W1*(state[MIN_SCORE_HOLE]-state[MAX_SCORE_HOLE])+W2*(getOwnStones()-getOpponentStones())+W3*extraMove+W4*stoneCaptured;
            else return W1*(state[MAX_SCORE_HOLE]-state[MIN_SCORE_HOLE])+W2*(getOwnStones()-getOpponentStones())+W3*extraMove+W4*stoneCaptured;
        }
        return 0;
    }


    public boolean gameOver()
    {
	boolean isGameOver = true;
	for (int position = MAX_SCORE_HOLE - PLAY_HOLES; isGameOver && (position < MAX_SCORE_HOLE); position++)
	    isGameOver = isGameOver && (state[position] == 0);
	for (int position = MIN_SCORE_HOLE - PLAY_HOLES; isGameOver && (position < MIN_SCORE_HOLE); position++)
	    isGameOver = isGameOver && (state[position] == 0);
	return isGameOver;
    }
 
    public ArrayList<Integer> getAvailableMoves() 
    {
	ArrayList<Integer> validMoves = new ArrayList<>();
	final int index = (player == MAX) ? 0 : MAX_SCORE_HOLE +1;
	for (int i = index; i <(index+PLAY_HOLES); i++)
        {
            if (state[i]> 0) validMoves.add(i);
        }	    
	return validMoves;
    }
    

    public int getOwnStones()
    {
        int numOfSeeds=0;
        int index=(player==MAX)?0:PLAY_HOLES+1;
        for(int i=index;i<(MAX_SCORE_HOLE+index);i++)
        {
            numOfSeeds+=state[i];
        }
        return numOfSeeds;
    }
    
    public int getOpponentStones()
    {
        int numOfSeeds=0;
        int index=(player==MAX)?(PLAY_HOLES+1):0;
        for(int i=index;i<(MAX_SCORE_HOLE+index);i++)
        {
            numOfSeeds+=state[i];
        }
        return numOfSeeds;
    }


    public int getDiffernce() 
    {
        return state[MAX_SCORE_HOLE]-state[MIN_SCORE_HOLE];
    }   

}

