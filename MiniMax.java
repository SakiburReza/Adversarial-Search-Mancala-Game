
import java.util.ArrayList;

public class MiniMax 
{
    private Board board;
    private int depth;
    private static Board targetBoard;
    private int initial_depth;
    private int maxHeuristic;
    private int minHeuristic;
    public MiniMax(Board board,int depth,int max_h,int min_h)
    {
        this.board=board;
        this.depth=depth;
        this.initial_depth=depth;
        this.maxHeuristic=max_h;
        this.minHeuristic=min_h;
    }
    public Board AlphaBetaSearch()
    {
        int alpha=-999999;
        int beta=999999;
        targetBoard=null;
        int v=getMaxValue(board,alpha, beta,depth);

        return targetBoard;
       
    }
    ArrayList<Board> getSuccessors(Board cb)
    {
        ArrayList<Board> successors = new ArrayList<>();
        for (int position : cb.getAvailableMoves())
        {
            Board newBoard=new Board(cb);
            newBoard.applyMove(position);
            successors.add(newBoard);
        }
        return successors;
    }


    public int getMinValue(Board cb,int alpha,int beta,int depth)
    {
        if(cb.gameOver()|| depth==0){/*System.out.println("Terminal found");*/ return cb.getHeuristic(maxHeuristic,minHeuristic);}
        int v=9999999;
        ArrayList<Board> successors=getSuccessors(cb);
        for(Board child:successors)
        {
            v=Math.min(v,getMaxValue(child, alpha, beta,depth-1));
            if(v<=alpha) return v;
            beta=Math.min(beta,v);
        }
        return v;
    }
    
    public int getMaxValue(Board cb,int alpha,int beta,int depth)
    {
        if(cb.gameOver()|| depth==0){/*System.out.println("Terminal found");*/ return cb.getHeuristic(maxHeuristic,minHeuristic);}
        int v=-999999;
        ArrayList<Board> successors=getSuccessors(cb);

        for(Board child:successors)
        {
            int temp=getMinValue(child, alpha, beta,depth-1);
            if(v<temp)
            {
                v=temp;
                if(depth==initial_depth) targetBoard=child;
            }
            if(v>=beta)
            {
                return v;
            }
            alpha=Math.max(alpha,v);
        }
        return v;
    }
    




}
