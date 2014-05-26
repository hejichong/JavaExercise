import javax.swing.Timer;
import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


public class MultipleBallApp extends JApplet{
	public MultipleBallApp(){
		add(new BallControl());
	}
	
	
	
	public class BallControl extends JPanel{
		private BallPanel ballPanel = new BallPanel();
		private JScrollBar jsbDelay = new JScrollBar();
		private JButton jbtResume = new JButton();
		private JButton jbtSuspend = new JButton();
		private JButton jbtAdd = new JButton();
		private JButton jbtRemove = new JButton();
		
		public BallControl(){
			JPanel panel = new JPanel();
			panel.add(jbtSuspend);
			panel.add(jbtResume);
			panel.add(jbtAdd);
			panel.add(jbtRemove);
			
			ballPanel.setBorder(new javax.swing.border.LineBorder(Color.red));
			jsbDelay.setOrientation(JScrollBar.HORIZONTAL);
			ballPanel.setDelay(jsbDelay.getMaximum());
			setLayout(new BorderLayout());
			add(jsbDelay,BorderLayout.NORTH);
			add(ballPanel,BorderLayout.CENTER);
			add(panel,BorderLayout.SOUTH);
			
			jbtSuspend.addActionListener(new Listener());
			jbtResume.addActionListener(new Listener());
			jbtAdd.addActionListener(new Listener());
			jbtRemove.addActionListener(new Listener());
			jbtDelay.addAdjustmentListener(new AdjustmentListener(){
				public void adjustmentValueChanged(AdjustmentEvent e){
					ballPanel.setDelay(jsbDelay.getMaximum()-e.getValue());
				}
			});
		}
		
		class Listener implements ActionListener{
			public void actionPerformed(ActionEvent e){
				if(e.getSource()==jbtSuspend)
					ballPanel.suspend();
				else if (e.getSource()==jbtResume)
					ballPanel.resume();
				else if (e.getSource()==jbtAdd)
					ballPanel.add();
				else if (e.getSource()==jbtRemove)
					ballPanel.substract();
			}
		}
		
		
		
	}
	
	
	public class BallPanel extends JPanel {
		private int delay=10;
		private ArrayList<Ball> list = new ArrayList<Ball>();
		
		protected Timer timer = new Timer(delay,new ActionListener(){
			public void actionPerformed(ActionEvent e){
				repaint();
			}
		});
		
		public BallPanel(){
			timer.start();
		}
		
		public void suspend(){
			timer.stop();
		}
		
		public void resume() {
			timer.start();
		}
		
		public void setDelay(int delay){
			this.delay = delay;
			timer.setDelay(delay);
		}
		
		public void add(){
			list.add(new Ball());
		}
		
		public void substract(){
			if(list.size() > 0)
				list.remove(list.size()-1);
		}
		
		protected void paintComponent(Graphics g){
			super.paintComponent(g);
			
			for(int i= 0;i<list.size();i++){
				Ball ball = (Ball)list.get(i);
				g.setColor(ball.color);
				
				if(ball.x<0||ball.x>getWidth())
					ball.dx=-ball.dx;
				if(ball.y<0||ball.y>getHeight())
					ball.dy=-ball.dy;
				
				ball.x +=ball.dx;
				ball.y +=ball.dy;
				g.fillOval(ball.x-ball.radius,ball.y-ball.radius,ball.radius*2,ball.radius*2);
			}
		}
		
	}
	
	
	
	
	
	class Ball{
		int x;
		int y;
		int dx=5;
		int dy=5;
		int radius=10;
		Color color = new Color((int)(Math.random()*256),(int)(Math.random()*256),(int)(Math.random()*256);
		
		
	}
	

}
