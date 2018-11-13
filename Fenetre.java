import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;


public class Fenetre extends JPanel {
	public static boolean flag = true;

	private static final int LONGUEUR = 600;
	private static final int LARGEUR = LONGUEUR;
	private static final int TPSREFRESH = 20;


	private static JPanel container = new JPanel();

	private static JButton BTStart = new JButton("STOP");
	private static JButton BTAjouter = new JButton("+");
	private static JButton BTRetirer = new JButton("-");


	private static int nb_collision = 0;
	private static int nbstartballes= 2;
	static List<Rond> cercleList = new ArrayList<>();
	static JLabel lab = new JLabel("Score =");
	static JLabel temps = new JLabel("Temps =");

	int time=0;
	Timer t = new Timer(1000,new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(flag==false) ;
			else if(flag==true) time++;
			temps.setText("Temps =" +String.valueOf(time)+ "s");


		}
	});


	public Fenetre() {

			for (int i = 0; i < nbstartballes; i++) {
				cercleList.add(new Rond(new Color((int)(Math.random() * 0x1000000)), LONGUEUR, LARGEUR));
			}
		setBackground(Color.WHITE);
		
		new Timer(TPSREFRESH, new TimerListener()).start();
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);


		for (Rond cercle : cercleList) {
			cercle.draw(g);
		}
	}


	private class TimerListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {            

			for (Rond cercle : cercleList) {
				cercle.go();
			}
			t.start();
			Collision();
			repaint(); 
		}
	}

	public void Collision(){
		int x = 0;
		int y = 0;
		int dist =0;

		int savedI = 0;
		int savedJ = 0;
		boolean collision = false;

		for(int i=0;i<cercleList.size();i++) {
			for(int j=0;j<cercleList.size();j++) {
				if(i!=j) {
					x= cercleList.get(i).getPosX() - cercleList.get(j).getPosX();
					y= cercleList.get(i).getPosY() - cercleList.get(j).getPosY();
					dist = x*x+y*y;

					if(dist < Rond.getRayon()*15) {
						collision = true;
						savedI = i;
						savedJ = j;
					}

				}

			}
		}	

		if(collision == true) {

			cercleList.remove(savedI);
			cercleList.remove(savedJ);
			collision = false;
			nb_collision ++;
			ThreadColision p = new ThreadColision(lab,nb_collision);
			p.start();
			System.out.println(nb_collision);



		}

	}
	public static int getnbCollision() {return nb_collision;}
	public static boolean getFlag() {return flag;}
	public List<Rond> Liste() {return cercleList;}

	private static void gui() {
		Fenetre fen = new Fenetre();

		JFrame frame = new JFrame("Jeu de balles");
		frame.setSize(LONGUEUR, LARGEUR);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		container.setBackground(Color.white);
		container.setLayout(new BorderLayout());
		container.add(fen, BorderLayout.CENTER);
		JPanel south1 = new JPanel();
		JPanel north = new JPanel();

		south1.add(BTStart);
		south1.add(BTAjouter);
		south1.add(BTRetirer);
		north.add(lab);
		north.add(temps);

		container.add(south1, BorderLayout.SOUTH);
		container.add(north,BorderLayout.NORTH);

		frame.setContentPane(container);

		BTRetirer.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event) {
				if(cercleList.size()>0)cercleList.remove(0);
			}
		});


		BTAjouter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event) {
				if(cercleList.size()<20) cercleList.add(new Rond(new Color((int)(Math.random() * 0x1000000)), LONGUEUR, LARGEUR));
			}
		});
		BTStart.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event) {
				if(flag==true) {
					flag=false;
					BTStart.setText("START")	;

				}
				else if(flag == false) {
					flag=true;
					BTStart.setText("STOP");;
				}
			}
		});

		frame.setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> gui());
	}
}


