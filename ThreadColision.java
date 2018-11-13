import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class ThreadColision extends Thread {

private JLabel lab = new JLabel();
private int n;


	
		public ThreadColision(JLabel l,int nb) {
			this.lab = l;
			this.n=nb;
			
		}
		public  void run() {
			try {
				this.lab.setText("Score ="+Integer.toString(n));
				sleep(5);
			}catch (InterruptedException e)	{
				
			}
		}

}

