import javax.swing.*;
/**
 * @author Leigh Rubin
 * Piece.java  
 * Blueprint for a Space object
 */
public class Piece
{
    private String color;
    private int row;
    private int col;
    private JLabel p;
    /**
     * Constructs a Piece object with a color and location on the board
     * @param Color color
     * @param int row
     * @param int col
     */
    public Piece(String color, int row, int col)
    {
        this.color = color;
        this.row = row;
        this.col = col;
        p = new JLabel(new ImageIcon(color));
        p.setBounds(13 + row*76,13 + col*76,73,73);
    }
    
    /**
     * Changes the color of the Piece
     */
    public void changeColor()
    {
        if(color.equals("Black.png"))
            color = "White.png";
        else
            color = "Black.png";
        p.setIcon(new ImageIcon(color));
    }
    
    /**
     * Returns the color of the Piece
     * @reutrn Color color
     */
    public String getColor()
    {
        return color;
    }
    
    /**
     * Returns the row the Piece is located in
     * @return int row
     */
    public int getRow()
    {
        return row;
    }
    
    /**
     * Returns the column the Piece is located in
     * @return int col
     */
    public int getCol()
    {
        return col;
    }
    
    /**
     * Returns the pic of the Piece
     * @return JLabel p
     */
    public JLabel getPiece()
    {
        return p;
    }
}