public class PrintBoard {
    StringBuilder sb;
    Board board;
    public PrintBoard(Board board) {
        this.sb = new StringBuilder();
        this.board=board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public String print(){
        sb.delete(0, sb.length());
        sb.append("    B6 B5 B4 B3 B2 B1\n-------------------------\n|  ");
        for (int i = board.getMIN_SCORE_HOLE() - 1; i > board.getMAX_SCORE_HOLE(); i--)
            if (board.getState()[i] > 9)
                sb.append("|").append(board.getState()[i]);
            else
                sb.append("| ").append(board.getState()[i]);
        sb.append("|  |");
        if (board.getPlayer() == board.getMIN())
            sb.append(" <--");
        if (board.getState()[board.getMIN_SCORE_HOLE()] > 9)
            sb.append("\n|").append(board.getState()[board.getMIN_SCORE_HOLE()]);
        else
            sb.append("\n| ").append(board.getState()[board.getMIN_SCORE_HOLE()]);
        sb.append("|-----------------|");
        if (board.getState()[board.getMAX_SCORE_HOLE()] > 9)
            sb.append(board.getState()[board.getMAX_SCORE_HOLE()]);
        else
            sb.append(" ").append(board.getState()[board.getMAX_SCORE_HOLE()]);
        sb.append("|\n|  ");
        for (int i = 0; i < board.getMAX_SCORE_HOLE(); i++)
            if (board.getState()[i] > 9)
                sb.append("|").append(board.getState()[i]);
            else
                sb.append("| ").append(board.getState()[i]);
        sb.append("|  |");
        if (board.getPlayer() == board.getMAX())
            sb.append(" <--");
        sb.append("\n-------------------------\n    A1 A2 A3 A4 A5 A6\n");
        return sb.toString();
    }
}
