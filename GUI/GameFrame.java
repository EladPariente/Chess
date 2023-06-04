package GUI;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import Logic.Board;
import Logic.Figure.Piece;


public class GameFrame extends JFrame {
	BoardPanel boardPanel = new BoardPanel();
	public  GameFrame() {	
		

		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setTitle("Chess Game");
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setVisible(true);

		boardPanel = new BoardPanel();
		boardPanel.setBounds(10, 40, Piece.CellSize*Board.SIZE, Piece.CellSize*Board.SIZE);
		boardPanel.setLocation(0, 0);
		this.setSize(boardPanel.getWidth()+200, boardPanel.getHeight()+45);		
		
		
		
//		ControlPanel controlPanel = new ControlPanel(boardPanel);		
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWeights = new double[]{boardPanel.getWidth()+500, 200};
		gridBagLayout.rowWeights = new double[]{Board.SIZE * Piece.CellSize+20, Board.SIZE * Piece.CellSize};
		
		setLayout(gridBagLayout);  		
		GridBagConstraints gc = new GridBagConstraints();
		gc.fill=GridBagConstraints.BOTH;
		gc.anchor=GridBagConstraints.CENTER;
		
		gc.gridx=0;
        gc.gridy=0; 
		this.add(boardPanel, gc);
        
		gc.gridx=1;
//        this.add(controlPanel, gc);
		JPanel panel1  = new JPanel();
		panel1.setLayout(new GridBagLayout());
		
		// add restart button ===================
		JButton restartButton = new JButton("RESTART");
		GridBagConstraints passGBC = new GridBagConstraints();
		passGBC.weightx = 0.5;
		passGBC.fill = GridBagConstraints.HORIZONTAL;
		passGBC.gridx = 0;
		passGBC.gridy = 0;
		
		restartButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	Restart();
	        }
		});
		
		panel1.add(restartButton,passGBC);
		//===========================================

		
		// add Queen button ===================
		JButton queenButton = new JButton("QUEEN");
		passGBC.fill = GridBagConstraints.HORIZONTAL;
		passGBC.weightx = 0.5;
		passGBC.gridx = 0;
		passGBC.gridy = 1;
//		queenButton.addActionListener(new ActionListener() {
//	        public void actionPerformed(ActionEvent e) {
//	        	queen();
//	        }
//		});
//		panel1.add (queenButton,passGBC);		
		//===========================================
		
		// add Rook button ===================
		JButton rookButton = new JButton("ROOK");
		passGBC.fill = GridBagConstraints.HORIZONTAL;
		passGBC.weightx = 0.5;
		passGBC.gridx = 0;
		passGBC.gridy = 2;
//		rookButton.addActionListener(new ActionListener() {
//	        public void actionPerformed(ActionEvent e) {
//	        	rook();
//	        }
//		});
//		panel1.add (rookButton,passGBC);		
		//===========================================
		
		
//		 add Bishop button ===================
		JButton bishopButton = new JButton("BISHOP");
		passGBC.fill = GridBagConstraints.HORIZONTAL;
		passGBC.weightx = 0.5;
		passGBC.gridx = 0;
		passGBC.gridy = 3;
//		bishopButton.addActionListener(new ActionListener() {
//	        public void actionPerformed(ActionEvent e) {
//	        	bishop();
//	        }
//		});
//		panel1.add (bishopButton,passGBC);		
		//===========================================

//		 add knight button ===================
		JButton knightButton = new JButton("KNIGHT");
		passGBC.fill = GridBagConstraints.HORIZONTAL;
		passGBC.weightx = 0.5;
		passGBC.gridx = 0;
		passGBC.gridy = 4;
//		knightButton.addActionListener(new ActionListener() {
//	        public void actionPerformed(ActionEvent e) {
//	        	knight();
//	        }
//		});
//		panel1.add (knightButton,passGBC);		
		//===========================================
              
		setVisible(true);
		this.add(panel1,gc);
        this.repaint();
        

	}
	private void Restart()
	{
		boardPanel.board = new Board(boardPanel);
		boardPanel.img = Toolkit.getDefaultToolkit().getImage("Images/board.jpg");
		boardPanel.repaint();
	}
	
//	private void queen(){
//		System.out.println("I am a queen");
//	}
//	private void rook(){
//		System.out.println("I am a rook");
//	}
//	private void bishop(){
//		System.out.println("I am a bishop");
//	}
//	private void knight(){
//		System.out.println("I am a knight");
//	}
}

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                