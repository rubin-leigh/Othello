import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;
/**
 * @author Leigh Rubin
 * Othello.java
 * Runs the othello game
 */
public class Game extends JFrame
{
    private Space[][] spaces = new Space[8][8];
    private boolean black = true;
    private Set<Piece> erase = new HashSet<Piece>();
    private Set<Piece> all = new HashSet<Piece>();
    private JLabel cover;
    private JLabel winnerB;
    private JLabel winnerW;
    private int w = 2;
    private int b = 2;
    private JLabel wh = new JLabel(Integer.toString(w));
    private JLabel bl = new JLabel(Integer.toString(b));
    private JButton newGame = new JButton();
    private JButton pass = new JButton();
    private boolean passed = false;
    /**
     * Puts together the JFrame, buttons, and labels
     */
    public Game()
    {
        pack();
        setVisible(true);
        setSize(905, 655);
        setLocationRelativeTo(null);
        setTitle("Othello");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        getContentPane().setLayout(null);
        setContentPane(new JLabel(new ImageIcon("Board.fw.png")));
        cover = new JLabel(new ImageIcon("Cover.png"));
        winnerB = new JLabel(new ImageIcon("Cover2.png"));
        winnerW = new JLabel(new ImageIcon("Cover2.png"));
        cover.setBounds(787,102,67,70);
        winnerB.setBounds(649,392,100,100);
        winnerW.setBounds(776,392,100,100);
        wh.setBounds(805,190,100,100);
        wh.setForeground(Color.white);
        wh.setFont(new Font("Algerian", Font.BOLD, 35));
        add(wh);
        bl.setBounds(687,190,100,100);
        bl.setForeground(Color.black);
        bl.setFont(new Font("Algerian", Font.BOLD, 35));
        newGame.setBounds(669,544,195,65);
        newGame.setOpaque(false);
        newGame.setContentAreaFilled(false);
        newGame.setBorderPainted(false);
        pass.setBounds(669,102,67,70);
        pass.setOpaque(false);
        pass.setContentAreaFilled(false);
        pass.setBorderPainted(false);
        add(bl);
        add(cover);
        add(newGame);
        add(winnerB);
        add(winnerW);
        add(pass);
        for(int i = 0; i < spaces.length; i++)
        {
            for(int j = 0; j < spaces[i].length; j++)
            {
                spaces[i][j] = new Space(13 + 75*i, 8 + 76*j);
                add(spaces[i][j].getButton());
            }
        }
        newPiece("Black.png",3,3);
        newPiece("White.png",3,4);
        newPiece("White.png",4,3);
        newPiece("Black.png",4,4);

        addActions();
    }
    
    /**
     * Adds the actions to the JButtons
     */
    public void addActions()
    {
        newGame.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    for(int i = 0; i < spaces.length; i++)
                    {
                        for(int j = 0; j < spaces[i].length; j++)
                        {
                            spaces[i][j].resetPiece();
                            spaces[i][j].getButton().setEnabled(true);
                        }
                    }
                    for(Piece p: all)
                        p.getPiece().setVisible(false);
                    all.clear();
                    newPiece("Black.png",3,3);
                    newPiece("White.png",3,4);
                    newPiece("White.png",4,3);
                    newPiece("Black.png",4,4);
                    w = 2;
                    b = 2;
                    wh.setText(Integer.toString(w));
                    bl.setText(Integer.toString(b));
                    cover.setBounds(787,102,67,70);
                    pass.setBounds(669,102,67,70);
                    winnerB.setVisible(true);
                    winnerW.setVisible(true);
                    pass.setEnabled(true);
                    black = true;
                }
            });

        pass.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    if(black){
                        if(passed)isWinner();
                        black = !black;
                        cover.setBounds(669,102,67,70);
                        pass.setBounds(787,102,67,70);
                        passed = true;
                    }
                    else{
                        if(passed)isWinner();
                        black = !black;
                        cover.setBounds(787,102,67,70);
                        pass.setBounds(669,102,67,70);
                        passed = true;
                    }
                }
            });

        for(int i = 0; i < spaces.length; i++)
        {
            for(int j = 0; j < spaces[i].length; j++)
            {
                final int x = i;
                final int y = j;
                spaces[i][j].getButton().addActionListener(new ActionListener()
                    {
                        public void actionPerformed(ActionEvent e)
                        {
                            if(black)
                            {
                                int count = 0;
                                for(int k = -1; k <= 1; k++)
                                {
                                    for(int l = -1; l <= 1; l++)
                                    {
                                        if(l != 0 || k != 0)
                                        {
                                            int tempX = x+l;
                                            int tempY = y+k;
                                            while(true)
                                            {
                                                try
                                                {
                                                    if(spaces[tempX][tempY].getPiece().getColor().equals("White.png"))
                                                    {
                                                        erase.add(spaces[tempX][tempY].getPiece());
                                                        tempX += l;
                                                        tempY += k;
                                                    }
                                                    else if(spaces[tempX][tempY].getPiece().getColor().equals("Black.png"))
                                                    {
                                                        for(Piece p: erase)
                                                        {
                                                            p.changeColor();
                                                            b++;
                                                            w--;
                                                            if(count == 0)
                                                            {
                                                                black = !black;
                                                                b++;
                                                                newPiece("Black.png",x,y);
                                                                cover.setBounds(669,102,67,70);
                                                                pass.setBounds(787,102,67,70);
                                                                count++;
                                                                passed = false;
                                                            }
                                                            checkWinner();
                                                        }
                                                        wh.setText(Integer.toString(w));
                                                        bl.setText(Integer.toString(b));
                                                        repaint();
                                                        erase.clear();
                                                        break;
                                                    }
                                                }
                                                catch(Exception ex)
                                                {
                                                    erase.clear();
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            else if(!black)
                            {
                                int count = 0;
                                for(int k = -1; k <= 1; k++)
                                {
                                    for(int l = -1; l <= 1; l++)
                                    {
                                        if(l != 0 || k != 0)
                                        {
                                            int tempX = x+l;
                                            int tempY = y+k;
                                            while(true)
                                            {
                                                try
                                                {
                                                    if(spaces[tempX][tempY].getPiece().getColor().equals("Black.png"))
                                                    {
                                                        erase.add(spaces[tempX][tempY].getPiece());
                                                        tempX += l;
                                                        tempY += k;
                                                    }
                                                    else if(spaces[tempX][tempY].getPiece().getColor().equals("White.png"))
                                                    {
                                                        for(Piece p: erase)
                                                        {
                                                            p.changeColor();
                                                            b--;
                                                            w++;
                                                            if(count == 0)
                                                            {
                                                                black = !black;
                                                                w++;
                                                                newPiece("White.png",x,y);
                                                                cover.setBounds(787,102,67,70);
                                                                pass.setBounds(669,102,67,70);
                                                                count++;
                                                                passed = false;
                                                            }
                                                            checkWinner();
                                                        }
                                                        wh.setText(Integer.toString(w));
                                                        bl.setText(Integer.toString(b));
                                                        repaint();
                                                        erase.clear();
                                                        break;
                                                    }
                                                }
                                                catch(Exception ex)
                                                {
                                                    erase.clear();
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    });
            }
        }
    }
    
    /**
     * Check to see if the game is over and if someone has won
     */
    public void checkWinner()
    {
        if(all.size() == 64 || b == 0 || w == 0){
            if(b>w)winnerB.setVisible(false);
            else if(w>b)winnerW.setVisible(false);
            pass.setEnabled(false);
        }
    }
    
    /**
     * Forces the game to end (after passing twice in a row) and determines a winner
     */
    public void isWinner()
    {
        if(b>w)winnerB.setVisible(false);
        else if(w>b)winnerW.setVisible(false);
        pass.setEnabled(false);
        for(int i = 0; i < spaces.length; i++)
        {
            for(int j = 0; j < spaces[i].length; j++)
            {
                spaces[i][j].getButton().setEnabled(false);
            }
        }
    }
    
    /**
     * Adds a new Piece to the board
     * @param String color
     * @param int x location
     * @param int y location
     */
    public void newPiece(String c, int x, int y)
    {
        spaces[x][y].addPiece(new Piece(c,x,y));
        add(spaces[x][y].getPiece().getPiece());
        all.add(spaces[x][y].getPiece());
        spaces[x][y].getButton().setEnabled(false);
    }
    
    /**
     * Main method
     */
    public static void main(String[] args)
    {
        new Game();
    }
}
