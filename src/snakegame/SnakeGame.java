/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegame;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.Timer;

/**
 *
 * @author Lokesh Chandra
 */
public class SnakeGame extends javax.swing.JFrame implements KeyListener, ActionListener{

    /**
     * Creates new form SnakeGame
     */
    
    private int [] snakeXlength = new int[750];
    private int [] snakeYlength = new int[750];
    
    private boolean left = false;   // for the key pressed ..........
    private boolean right = false;
    private boolean up = false;
    private boolean down = false;
    
    private ImageIcon rigthmouth,leftmouth,upmouth,downmouth,snakeimage; // after key pressed snake move action changed
    private int lengthofsnake = 3, moves = 0;
    private int [] enemyXpos = {25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625,650,675,700,725};
    private int [] enemyYpos = {25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525};
    
    private ImageIcon enemyimage;
    
    private Random random = new Random();
    private int xpos = random.nextInt(29);
    private int ypos = random.nextInt(21);
    private int score = 0;
    private int snakeSpeed = 80;
    
    Timer timer;
    java.util.Timer ti;
    
    TimerTask tti;
    
    public SnakeGame() {
        initComponents();
        MakeOffline mo = new MakeOffline();
        System.out.println("status : "+playstatusbox.getText());
        if("Single Play".equals(Welcome.playstatus)){
            player2box.setVisible(false);
            scorebox2.setVisible(false);
            System.out.println("hide");
        }
        else
        {
            player2box.setVisible(true);
            scorebox2.setVisible(true);
            friends_score();
        }
        
        
        gameoverlabel.setVisible(false);
        highscorelabel.setVisible(false);
        highscorebox.setVisible(false);
        restartmsglabel.setVisible(false);
        
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        
        timer = new Timer(snakeSpeed, this);
        timer.start();

        setLocationRelativeTo(null);
    }

    public void friends_score()
    {
        try {
            System.out.println("Friends : "+MultiPlay.s);
            MySql mysql = new MySql();
            String sql = "select tempscore from info where userid = '"+MultiPlay.s+"'";
            PreparedStatement pstmt = mysql.conn.prepareStatement(sql);
            
            ti = new java.util.Timer();
            ti.schedule(tti = new TimerTask()
            {
                public void run()
                {
                    String fval="";
                    try {
                        ResultSet rs = pstmt.executeQuery();
                        while(rs.next()){
                            fval = rs.getString(1);
                        }
                        scorebox2.setText(fval);
                    } catch (SQLException ex) {
                        Logger.getLogger(SnakeGame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            },0, 2000);
        } catch (SQLException ex) {
            Logger.getLogger(SnakeGame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void ourCustomPaintingMethod(Graphics g)
    {   
        if(moves == 0){
            
            snakeXlength[0] = 100;
            snakeXlength[1] = 75;
            snakeXlength[2] = 50;
            
            snakeYlength[0] = 25;
            snakeYlength[1] = 25;
            snakeYlength[2] = 25;
            
        }
        
        URL rurl = getClass().getResource("/snakegame/images/rightmouth.png");
        URL lurl = getClass().getResource("/snakegame/images/leftmouth.png");
        URL uurl = getClass().getResource("/snakegame/images/upmouth.png");
        URL durl = getClass().getResource("/snakegame/images/downmouth.png");
        URL snk = getClass().getResource("/snakegame/images/snakeimage.png");
        URL ene = getClass().getResource("/snakegame/images/enemy.png");
        
        rigthmouth = new ImageIcon(rurl);
        rigthmouth.paintIcon(this, g, snakeXlength[0], snakeYlength[0]);
        
        for(int i=0; i<lengthofsnake; i++){
            
            if(i ==0 && right){
                rigthmouth = new ImageIcon(rurl);
                rigthmouth.paintIcon(this, g, snakeXlength[0], snakeYlength[0]);
            } 
            if(i == 0 && left){
                leftmouth = new ImageIcon(lurl);
                leftmouth.paintIcon(this, g, snakeXlength[0], snakeYlength[0]);
            }
            if(i ==0 && down){
                downmouth = new ImageIcon(durl);
                downmouth.paintIcon(this, g, snakeXlength[0], snakeYlength[0]);
            }
            if(i == 0 && up){
                upmouth = new ImageIcon(uurl);
                upmouth.paintIcon(this, g, snakeXlength[0], snakeYlength[0]);
            }
            
            // for snake body ..
            
            if( i != 0){
                snakeimage = new ImageIcon(snk);
                snakeimage.paintIcon(this, g, snakeXlength[i], snakeYlength[i]);
            }
            
        }
        
        enemyimage = new ImageIcon(ene);
        if((enemyXpos[xpos] == snakeXlength[0] && enemyYpos[ypos] == snakeYlength[0]))
        {
            score += 10;
            if("Single Play".equals(Welcome.playstatus)){
            scorebox.setText(Integer.toString(score));
            }
            else
                update_score(score);
            
            lengthofsnake++;
            xpos = random.nextInt(29);
            ypos = random.nextInt(21);
        }
        
        enemyimage.paintIcon(this, g, enemyXpos[xpos], enemyYpos[ypos]);
        
        for(int check = 1; check < lengthofsnake; check++)
        {
            if(snakeXlength[check] == snakeXlength[0] && snakeYlength[check] == snakeYlength[0])
            {
                right = false;
                left = false;
                up = false;
                down = false;
                
                highscorebox.setText(Integer.toString(score));
                gameoverlabel.setVisible(true);
                highscorelabel.setVisible(true);
                highscorebox.setVisible(true);
                restartmsglabel.setVisible(true);
            }
        }
    }
    
    public void update_score(int score)
    {
        try {
            MySql msql = new MySql();
            String sql = "update info set tempscore = '"+score+"' where userid = '"+Login.uid+"'";
            PreparedStatement pstmt = msql.conn.prepareStatement(sql);
            int i = pstmt.executeUpdate();
            
            if(i>0){
                System.out.println("Successfully online ....!!!!");
                scorebox.setText(Integer.toString(score));
            }
            else
                System.out.println("Error to update score ....!!!!");
            
            
        } catch (SQLException ex) {
            Logger.getLogger(SnakeGame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        playstatusbox = new javax.swing.JTextField();
        player1box = new javax.swing.JTextField();
        scorebox2 = new javax.swing.JTextField();
        scorebox = new javax.swing.JTextField();
        player2box = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel()
        {
            public void paint(Graphics g)
            {
                super.paint(g);
                ourCustomPaintingMethod(g);
            }
        };
        gameoverlabel = new javax.swing.JLabel();
        highscorelabel = new javax.swing.JLabel();
        highscorebox = new javax.swing.JTextField();
        restartmsglabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 153, 51), new java.awt.Color(255, 153, 51), new java.awt.Color(255, 153, 51), new java.awt.Color(255, 153, 51)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        playstatusbox.setEditable(false);
        playstatusbox.setBackground(new java.awt.Color(0, 0, 0));
        playstatusbox.setFont(new java.awt.Font("HP Simplified Light", 1, 30)); // NOI18N
        playstatusbox.setForeground(new java.awt.Color(255, 153, 0));
        playstatusbox.setBorder(null);
        jPanel2.add(playstatusbox, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 10, 210, 40));

        player1box.setEditable(false);
        player1box.setBackground(new java.awt.Color(0, 0, 0));
        player1box.setFont(new java.awt.Font("HP Simplified Light", 0, 18)); // NOI18N
        player1box.setForeground(new java.awt.Color(51, 255, 0));
        player1box.setText("userid ");
        player1box.setBorder(null);
        jPanel2.add(player1box, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 10, 90, 40));

        scorebox2.setEditable(false);
        scorebox2.setBackground(new java.awt.Color(0, 0, 0));
        scorebox2.setFont(new java.awt.Font("HP Simplified Light", 0, 30)); // NOI18N
        scorebox2.setForeground(new java.awt.Color(255, 255, 51));
        scorebox2.setText("0");
        scorebox2.setBorder(null);
        jPanel2.add(scorebox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, 100, 40));

        scorebox.setEditable(false);
        scorebox.setBackground(new java.awt.Color(0, 0, 0));
        scorebox.setFont(new java.awt.Font("HP Simplified Light", 0, 30)); // NOI18N
        scorebox.setForeground(new java.awt.Color(255, 255, 51));
        scorebox.setText("0");
        scorebox.setBorder(null);
        jPanel2.add(scorebox, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 10, 100, 40));

        player2box.setEditable(false);
        player2box.setBackground(new java.awt.Color(0, 0, 0));
        player2box.setFont(new java.awt.Font("HP Simplified Light", 0, 18)); // NOI18N
        player2box.setForeground(new java.awt.Color(51, 255, 0));
        player2box.setBorder(null);
        jPanel2.add(player2box, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 90, 40));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 750, 60));

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));
        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(51, 102, 255), new java.awt.Color(51, 102, 255), new java.awt.Color(51, 102, 255), new java.awt.Color(51, 102, 255)));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        gameoverlabel.setFont(new java.awt.Font("Comic Sans MS", 1, 60)); // NOI18N
        gameoverlabel.setForeground(new java.awt.Color(255, 255, 255));
        gameoverlabel.setText("Game Over");
        jPanel3.add(gameoverlabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 180, 330, 60));

        highscorelabel.setFont(new java.awt.Font("HP Simplified Light", 1, 24)); // NOI18N
        highscorelabel.setForeground(new java.awt.Color(204, 204, 204));
        highscorelabel.setText("High Score : ");
        jPanel3.add(highscorelabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 250, -1, -1));

        highscorebox.setBackground(new java.awt.Color(0, 0, 0));
        highscorebox.setFont(new java.awt.Font("HP Simplified Light", 0, 32)); // NOI18N
        highscorebox.setForeground(new java.awt.Color(51, 255, 51));
        highscorebox.setBorder(null);
        jPanel3.add(highscorebox, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 250, 120, 30));

        restartmsglabel.setFont(new java.awt.Font("HP Simplified Light", 1, 24)); // NOI18N
        restartmsglabel.setForeground(new java.awt.Color(255, 255, 51));
        restartmsglabel.setText("press   SPACE  button to restart Game");
        jPanel3.add(restartmsglabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 300, -1, -1));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 750, 550));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 770, 640));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SnakeGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SnakeGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SnakeGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SnakeGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SnakeGame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel gameoverlabel;
    private javax.swing.JTextField highscorebox;
    private javax.swing.JLabel highscorelabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    public static javax.swing.JTextField player1box;
    public static javax.swing.JTextField player2box;
    public static javax.swing.JTextField playstatusbox;
    private javax.swing.JLabel restartmsglabel;
    public javax.swing.JTextField scorebox;
    public static javax.swing.JTextField scorebox2;
    // End of variables declaration//GEN-END:variables

    @Override
    public void keyTyped(KeyEvent e) {
        
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
        if(e.getKeyCode() == KeyEvent.VK_SPACE)
        {
            moves = 0;
            score = 0;
            lengthofsnake = 3;
            
            gameoverlabel.setVisible(false);
            highscorelabel.setVisible(false);
            highscorebox.setVisible(false);
            restartmsglabel.setVisible(false);
            
            repaint();
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            moves++;
            right = true;
            
            // preventing to colide .....
            
            if(!left){
                right = true;
            }
            else
            {
                right = false;
                left = true;
            }
            
 
            up = false;
            down = false;
            
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            moves++;
            left = true;
            
            // preventing to colide .....
            
            if(!right){
                left = true;
            }
            else
            {
                left = false;
                right = true;
            }
            
 
            up = false;
            down = false;
            
        }
        if(e.getKeyCode() == KeyEvent.VK_UP){
            moves++;
            up = true;
            
            // preventing to colide .....
            
            if(!down){
                up = true;
            }
            else
            {
                up = false;
                down = true;
            }
            
 
            left = false;
            right = false;
            
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN){
            moves++;
            down = true;
            
            // preventing to colide .....
            
            if(!up){
                down = true;
            }
            else
            {
                down = false;
                up = true;
            }
            
 
            left = false;
            right = false;
            
        }
        
        
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        if(right){
            for(int r = lengthofsnake-1; r>=0; r-- )
            {
                snakeYlength[r+1] = snakeYlength[r];   // move the value of y indexes ....
            }
            for(int r = lengthofsnake; r>=0 ; r--){
                if(r == 0)
                {
                    snakeXlength[r] = snakeXlength[r] + 25; //  snake head move with 25 value ahead ....
                    
                }
                else
                {
                    snakeXlength[r] = snakeXlength[r-1]; // shifting snake body value further ....
                }
                if(snakeXlength[r] > 725)
                {
                    snakeXlength[r] = 0;
                }
            }
            repaint();
        }
        if(left){
                for(int r = lengthofsnake-1; r>=0; r-- )
                {
                    snakeYlength[r+1] = snakeYlength[r];   // move the value of y indexes ....
                }
                for(int r = lengthofsnake; r>=0 ; r--){
                    if(r == 0)
                    {
                        snakeXlength[r] = snakeXlength[r] - 25; //  snake head move with 25 value ahead ....
                    }
                    else
                    {
                        snakeXlength[r] = snakeXlength[r-1]; //  shifting snake body value further ....
                    }
                    if(snakeXlength[r] < 0)
                    {
                        snakeXlength[r] = 725;
                    }
                }
                repaint();
        }
        if(up)
        {
            for(int r = lengthofsnake-1; r>=0; r-- )
                {
                    snakeXlength[r+1] = snakeXlength[r];   //   move the value of y indexes ....
                }
                for(int r = lengthofsnake; r>=0 ; r--){
                    if(r == 0)
                    {
                        snakeYlength[r] = snakeYlength[r] - 25; //  snake head move with 25 value ahead ....

                    }
                    else
                    {
                        snakeYlength[r] = snakeYlength[r-1]; //  shifting snake body value further ....
                    }
                    if(snakeYlength[r] < 0)
                    {
                        snakeYlength[r] = 525;
                    }
                }
                repaint();
            
        }
        if(down)
        {
                for(int r = lengthofsnake-1; r>=0; r-- )
                {
                    snakeXlength[r+1] = snakeXlength[r];   // move the value of y indexes ....
                }
                for(int r = lengthofsnake; r>=0 ; r--){
                    if(r == 0)
                    {
                        snakeYlength[r] = snakeYlength[r] + 25; //  snake head move with 25 value ahead ....

                    }
                    else
                    {
                        snakeYlength[r] = snakeYlength[r-1]; // shifting snake body value further ....
                    }
                    if(snakeYlength[r] > 525)
                    {
                        snakeYlength[r] = 0;
                    }
                }
                repaint();
        }
        
    }
}
