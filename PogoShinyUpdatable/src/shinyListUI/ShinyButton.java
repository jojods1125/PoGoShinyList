/**
 * 
 */
package shinyListUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * Features an image of the shiny Pokemon and changes background color when
 * clicked
 * 
 * @author Joseph Dasilva
 */
public class ShinyButton extends JButton {

	private static final long serialVersionUID = 1L;
	private Color hoverBackgroundColor;
	private Color pressedBackgroundColor;
	private int currentColor = 0;
	private Color[] colors = new Color[] { Color.LIGHT_GRAY, Color.YELLOW, Color.RED, Color.GREEN, Color.DARK_GRAY };

	public ShinyButton() {
		this(null);
	}

	public ShinyButton(ImageIcon text) {
		super(text);
		super.setContentAreaFilled(false);
		addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentColor++;
			}
		});
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.setColor(colors[currentColor % 5]);
		g.fillRect(0, 0, getWidth(), getHeight());
		super.paintComponent(g);
	}

	public void setCurrentColor(int color) {
		currentColor = color;
	}

	public int getCurrentColor() {
		return currentColor;
	}

	@Override
	public void setContentAreaFilled(boolean b) {
	}

	public Color getHoverBackgroundColor() {
		return hoverBackgroundColor;
	}

	public void setHoverBackgroundColor(Color hoverBackgroundColor) {
		this.hoverBackgroundColor = hoverBackgroundColor;
	}

	public Color getPressedBackgroundColor() {
		return pressedBackgroundColor;
	}

	public void setPressedBackgroundColor(Color pressedBackgroundColor) {
		this.pressedBackgroundColor = pressedBackgroundColor;
	}
}
