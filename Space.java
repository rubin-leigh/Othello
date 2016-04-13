import javax.swing.JButton;
/**
 * @author Leigh Rubin
 * Space.java
 * Blueprint for a Space object
 */
public class Space
{
    private Piece p;
    private JButton b;
    /**
     * Constructs and empty space on the board with a location
     */
    public Space(int x, int y)
    {
        p = null;
        b = new JButton();
        b.setBounds(x,y,73,73);
        b.setOpaque(false);
        b.setContentAreaFilled(false);
        b.setBorderPainted(false);
    }
    
    /**
     * Adds a Piece to the Space on the board
     * @param Piece p
     */
    public void addPiece(Piece p)
    {
        this.p = p;
    }
    
    /**
     * Returns the Piece in the space or null if there is no Piece present
     * @return Piece p
     */
    public Piece getPiece()
    {
        return p;
    }
    
    /**
     * Returns if the space is filled with a Piece
     * @reutrn boolean filled
     */
    public boolean isFilled()
    {
        return (getPiece() != null);
    }
    
    /**
     * Returns the button
     * @return JButton b
     */
    public JButton getButton()
    {
        return b;
    }
    
    /**
     * Resets the piece to null
     */
    public void resetPiece()
    {
        p = null;
    }
}