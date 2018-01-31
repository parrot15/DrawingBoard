import static java.lang.System.*;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.event.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.geom.Path2D;
import javax.swing.*;

public class DrawingBoard extends JFrame implements ActionListener, MouseListener, MouseMotionListener {
	private static final long serialVersionUID = 1L;

	private JRadioButton black = new JRadioButton("Black");
	private JRadioButton red = new JRadioButton("Red");
	private JRadioButton blue = new JRadioButton("Blue");
	private JRadioButton green = new JRadioButton("Green");
	private JRadioButton yellow = new JRadioButton("Yellow");
	private JRadioButton orange = new JRadioButton("Orange");
	private JRadioButton pink = new JRadioButton("Pink");
	private JRadioButton purple = new JRadioButton("Purple");

	private JRadioButton drawCircle = new JRadioButton("Circle");
	private JRadioButton drawSquare = new JRadioButton("Square");
	private JRadioButton drawTriangle = new JRadioButton("Triangle");

	private JRadioButton erase = new JRadioButton("Erase");
	private JButton clear = new JButton("Clear");
	
	private Point start;
	private Point stop;
	private Shape shape;

	public DrawingBoard() {
		setTitle("Drawing Board");
		getContentPane().setBackground(Color.white);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		JPanel basePanel = new JPanel();
		basePanel.setLayout(new BoxLayout(basePanel, BoxLayout.X_AXIS));
		basePanel.setPreferredSize(new Dimension(510, 375));
		basePanel.setBackground(Color.white);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
		buttonPanel.setPreferredSize(new Dimension(90, 375));
		buttonPanel.setBackground(new Color(235, 235, 235));
		
		ButtonGroup options = new ButtonGroup();
		options.add(black);
		options.add(red);
		options.add(blue);
		options.add(green);
		options.add(yellow);
		options.add(orange);
		options.add(pink);
		options.add(purple);
		options.add(drawCircle);
		options.add(drawSquare);
		options.add(drawTriangle);
		options.add(erase);
		
		JLabel colors = new JLabel("Colors");
		colors.setFont(new Font("SansSerif", Font.BOLD, 20));
		buttonPanel.add(colors);
		
		black.setFont(new Font("SansSerif", Font.PLAIN, 15));
		black.addActionListener(this);
		buttonPanel.add(black);
		black.setSelected(true);
		
		red.setFont(new Font("SansSerif", Font.PLAIN, 15));
		red.setForeground(Color.red);
		red.addActionListener(this);
		buttonPanel.add(red);
		
		blue.setFont(new Font("SansSerif", Font.PLAIN, 15));
		blue.setForeground(Color.blue);
		blue.addActionListener(this);
		buttonPanel.add(blue);
		
		green.setFont(new Font("SansSerif", Font.PLAIN, 15));
		green.setForeground(Color.green);
		green.addActionListener(this);
		buttonPanel.add(green);
		
		yellow.setFont(new Font("SansSerif", Font.PLAIN, 15));
		yellow.setForeground(Color.yellow);
		yellow.addActionListener(this);
		buttonPanel.add(yellow);
		
		orange.setFont(new Font("SansSerif", Font.PLAIN, 15));
		orange.setForeground(Color.orange);
		orange.addActionListener(this);
		buttonPanel.add(orange);
		
		pink.setFont(new Font("SansSerif", Font.PLAIN, 15));
		pink.setForeground(Color.pink);
		pink.addActionListener(this);
		buttonPanel.add(pink);
		
		purple.setFont(new Font("SansSerif", Font.PLAIN, 15));
		purple.setForeground(Color.magenta);
		purple.addActionListener(this);
		buttonPanel.add(purple);

		JLabel tools = new JLabel("Tools");
		tools.setFont(new Font("SansSerif", Font.BOLD, 20));
		buttonPanel.add(tools);
		
		drawCircle.setFont(new Font("SansSerif", Font.PLAIN, 15));
		drawCircle.addActionListener(this);
		buttonPanel.add(drawCircle);
		
		drawSquare.setFont(new Font("SansSerif", Font.PLAIN, 15));
		drawSquare.addActionListener(this);
		buttonPanel.add(drawSquare);
		
		drawTriangle.setFont(new Font("SansSerif", Font.PLAIN, 15));
		drawTriangle.addActionListener(this);
		buttonPanel.add(drawTriangle);
		
		erase.setFont(new Font("SansSerif", Font.PLAIN, 15));
		erase.addActionListener(this);
		buttonPanel.add(erase);
		
		clear.setFont(new Font("SansSerif", Font.PLAIN, 15));
		clear.addActionListener(this);
		buttonPanel.add(clear);

		basePanel.add(buttonPanel);

		JPanel drawingPanel = new JPanel(new BorderLayout());
		drawingPanel.addMouseListener(this);
		drawingPanel.addMouseMotionListener(this);
		drawingPanel.setPreferredSize(new Dimension(420, 375));
		drawingPanel.setBackground(Color.white);
		drawingPanel.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(Toolkit.getDefaultToolkit().
				getImage("img/hollowsquare.png").getScaledInstance(8, 8, Image.SCALE_SMOOTH),
				new Point(drawingPanel.getX(), drawingPanel.getY()), "hollowsquare"));
		basePanel.add(drawingPanel);
		
		getContentPane().add(basePanel);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new DrawingBoard();
			}
		});
	}

	public void drawStroke() {
		Graphics g = getGraphics();
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		if (start != null && stop != null) {
			Shape strokedShape = (new BasicStroke(3)).createStrokedShape(shape);
			if (black.isSelected()) {
				g2d.setColor(Color.black);
				g2d.fill(strokedShape);
			} else if (red.isSelected()) {
				g2d.setColor(Color.red);
				g2d.fill(strokedShape);
			} else if (blue.isSelected()) {
				g2d.setColor(Color.blue);
				g2d.fill(strokedShape);
			} else if (green.isSelected()) {
				g2d.setColor(Color.green);
				g2d.fill(strokedShape);
			} else if (yellow.isSelected()) {
				g2d.setColor(Color.yellow);
				g2d.fill(strokedShape);
			} else if (orange.isSelected()) {
				g2d.setColor(Color.orange);
				g2d.fill(strokedShape);
			} else if (pink.isSelected()) {
				g2d.setColor(Color.pink);
				g2d.fill(strokedShape);
			} else if (purple.isSelected()) {
				g2d.setColor(Color.magenta);
				g2d.fill(strokedShape);
			} else if (erase.isSelected()) {
				Shape eraseStrokedShape = (new BasicStroke(20)).createStrokedShape(shape);
				g2d.setColor(Color.white);
				g2d.fill(eraseStrokedShape);
			} else if (drawCircle.isSelected()) {
				g2d.setColor(Color.black);
			} else if (drawSquare.isSelected()) {
				g2d.setColor(Color.black);
			} else if (drawTriangle.isSelected()) {
				g2d.setColor(Color.black);
			}
		}
	}

	public void drawShape(int x, int y) {
		Graphics2D g2d = (Graphics2D) getGraphics();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(Color.black);
		if (drawCircle.isSelected()) {
			g2d.drawOval(x, y, 40, 40);
		} else if (drawSquare.isSelected()) {
			g2d.drawRect(x, y, 36, 36);
		} else if (drawTriangle.isSelected()) {
			g2d.drawPolygon(new int[] {x + 20, x, x + 40}, new int[] {y - 5, y + 35, y + 35}, 3);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(clear)) {
			out.println("clear");
			repaint();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getX() >= -2 && e.getY() >= -6 && e.getX() <= 414 && e.getY() <= 365) {
			stop = new Point(e.getX() + 94, e.getY() + 30);
			Path2D path = (Path2D) shape;
			path.moveTo(start.x, start.y);
			path.lineTo(stop.x, stop.y);
			shape = path;
			start = stop;
			drawStroke();
			drawShape(e.getX() + 74, e.getY() + 10);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getX() >= -2 && e.getY() >= -6 && e.getX() <= 414 && e.getY() <= 365) {
			start = new Point(e.getX() + 94, e.getY() + 30);
			Path2D path = new Path2D.Double();
			shape = path;
			drawStroke();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getX() >= -2 && e.getY() >= -6 && e.getX() <= 414 && e.getY() <= 365) {
			Path2D path = (Path2D) shape;
			try {
				path.closePath();
			} catch (Exception ex) {
			}
			shape = path;
			drawStroke();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (e.getX() >= -2 && e.getY() >= -6 && e.getX() <= 414 && e.getY() <= 365) {
			stop = new Point(e.getX() + 94, e.getY() + 30);
			Path2D path = (Path2D) shape;
			path.moveTo(start.x, start.y);
			path.lineTo(stop.x, stop.y);
			shape = path;
			start = stop;
			drawStroke();
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}
}