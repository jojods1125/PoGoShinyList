/**
 * 
 */
package shinyListUI;

import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import shinyListMaker.ShinyListReader;

/**
 * Displays all of the shinies in the shinyAvailable file and allows users to
 * change background colors on individual shinies. Users can also choose to show
 * alola and/or "hat" shinies.
 * 
 * @author Joseph Dasilva
 */
public class ShinyListGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private ImageIcon image;
	private ShinyButton button;
	private ShinyButton[] buttons;
	private int[] colors;

	/**
	 * Constructs the ShinyListGUI of type
	 * @param type type of ShinyListGUI
	 */
	public ShinyListGUI(String type) {
		setTitle("Pokemon Go Shiny Checklist");
		Container cp = getContentPane();
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5, 5, 5, 5);

		ShinyListReader read = new ShinyListReader();
		String[] shinyList = new String[10];

		if (type.equals("Default")) {
			shinyList = read.getShiniesAvailableDefault();
		}
		if (type.equals("Alolan")) {
			shinyList = read.getShiniesAvailableAlola();
		}
		if (type.equals("Special")) {
			shinyList = read.getShiniesAvailableSpecial();
		}
		if (type.equals("DefaultAlolan")) {
			shinyList = read.getShiniesAllButSpecial();
		}
		if (type.equals("All")) {
			shinyList = read.getShiniesAll();
		}

		screenshotButton(panel);
		saveColorsButton(panel, type);
		clearColorsButton(panel, type);

		JTextField enterShiny = new JTextField();
		enterShiny.setText("Enter New Dex #");
		enterShiny.setBounds(150, 150, 300, 150);
		c.gridx = 9;
		c.gridy = 0;
		panel.add(enterShiny, c);

		JButton addShiny = new JButton();
		addShiny.setBounds(150, 150, 300, 150);
		addShiny.setText("Add Shiny");
		addShiny.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String dexNum = enterShiny.getText();
				try {
					File file = new File("assets/shinyAvailable");
					FileWriter out = new FileWriter(file, true);
					out.write("\n" + dexNum);
					out.close();

					ShinyListReader readUpdate = new ShinyListReader();
					String[] shinyListUpdate;
					int[] colorsUpdate;
					int idx;

					shinyListUpdate = readUpdate.getShiniesAvailableDefault();
					colorsUpdate = new int[shinyListUpdate.length];
					idx = 0;
					while (!shinyListUpdate[idx].equals("pokemon_icon_" + dexNum + "_00_shiny.png")) {
						idx++;
					}
					Scanner scanDefault = new Scanner(new FileInputStream("assets/colorDefault"));
					for (int i = 0; i < idx; i++) {
						colorsUpdate[i] = scanDefault.nextInt() % 5;
					}
					colorsUpdate[idx] = 0;
					for (int i = idx + 1; i < shinyListUpdate.length; i++) {
						colorsUpdate[i] = scanDefault.nextInt() % 5;
					}
					scanDefault.close();
					PrintStream psDefault = new PrintStream("assets/colorDefault");
					for (int i = 0; i < colorsUpdate.length; i++) {
						psDefault.println(colorsUpdate[i]);
					}
					psDefault.close();

					shinyListUpdate = readUpdate.getShiniesAllButSpecial();
					colorsUpdate = new int[shinyListUpdate.length];
					idx = 0;
					while (!shinyListUpdate[idx].equals("pokemon_icon_" + dexNum + "_00_shiny.png")) {
						idx++;
					}
					Scanner scanDefaultAlolan = new Scanner(new FileInputStream("assets/colorDefaultAlolan"));
					for (int i = 0; i < idx; i++) {
						colorsUpdate[i] = scanDefaultAlolan.nextInt() % 5;
					}
					colorsUpdate[idx] = 0;
					for (int i = idx + 1; i < shinyListUpdate.length; i++) {
						colorsUpdate[i] = scanDefaultAlolan.nextInt() % 5;
					}
					scanDefaultAlolan.close();
					PrintStream psDefaultAlolan = new PrintStream("assets/colorDefaultAlolan");
					for (int i = 0; i < colorsUpdate.length; i++) {
						psDefaultAlolan.println(colorsUpdate[i]);
					}
					psDefaultAlolan.close();

					shinyListUpdate = readUpdate.getShiniesAll();
					colorsUpdate = new int[shinyListUpdate.length];
					idx = 0;
					while (!shinyListUpdate[idx].equals("pokemon_icon_" + dexNum + "_00_shiny.png")) {
						idx++;
					}
					Scanner scanAll = new Scanner(new FileInputStream("assets/colorAll"));
					for (int i = 0; i < idx; i++) {
						colorsUpdate[i] = scanAll.nextInt() % 5;
					}
					colorsUpdate[idx] = 0;
					for (int i = idx + 1; i < shinyListUpdate.length; i++) {
						colorsUpdate[i] = scanAll.nextInt() % 5;
					}
					scanAll.close();
					PrintStream psAll = new PrintStream("assets/colorAll");
					for (int i = 0; i < colorsUpdate.length; i++) {
						psAll.println(colorsUpdate[i]);
					}
					psAll.close();

					new ShinyListGUI(type);
					setVisible(false);
				} catch (IOException e1) {
					System.out.println("Could not add shiny");
				}
			}
		});
		c.gridx = 10;
		c.gridy = 0;
		panel.add(addShiny, c);

		JButton removeShiny = new JButton();
		removeShiny.setBounds(150, 150, 300, 150);
		removeShiny.setText("Delete Shiny");
		removeShiny.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String dexNum = enterShiny.getText();
				try {
					ShinyListReader readUpdate = new ShinyListReader();
					String[] shinyListUpdate;
					int[] colorsUpdate;
					int idx;

					shinyListUpdate = readUpdate.getShiniesAvailableDefault();
					colorsUpdate = new int[shinyListUpdate.length - 1];
					idx = 0;
					while (!shinyListUpdate[idx].equals("pokemon_icon_" + dexNum + "_00_shiny.png")) {
						idx++;
					}
					Scanner scanDefault = new Scanner(new FileInputStream("assets/colorDefault"));
					for (int i = 0; i < idx; i++) {
						colorsUpdate[i] = scanDefault.nextInt() % 5;
					}
					scanDefault.nextInt();
					for (int i = idx; i < shinyListUpdate.length - 1; i++) {
						colorsUpdate[i] = scanDefault.nextInt() % 5;
					}
					scanDefault.close();
					PrintStream psDefault = new PrintStream("assets/colorDefault");
					for (int i = 0; i < colorsUpdate.length; i++) {
						psDefault.println(colorsUpdate[i]);
					}
					psDefault.close();

					shinyListUpdate = readUpdate.getShiniesAllButSpecial();
					colorsUpdate = new int[shinyListUpdate.length - 1];
					idx = 0;
					while (!shinyListUpdate[idx].equals("pokemon_icon_" + dexNum + "_00_shiny.png")) {
						idx++;
					}
					Scanner scanDefaultAlolan = new Scanner(new FileInputStream("assets/colorDefaultAlolan"));
					for (int i = 0; i < idx; i++) {
						colorsUpdate[i] = scanDefaultAlolan.nextInt() % 5;
					}
					scanDefaultAlolan.nextInt();
					for (int i = idx; i < shinyListUpdate.length - 1; i++) {
						colorsUpdate[i] = scanDefaultAlolan.nextInt() % 5;
					}
					scanDefaultAlolan.close();
					PrintStream psDefaultAlolan = new PrintStream("assets/colorDefaultAlolan");
					for (int i = 0; i < colorsUpdate.length; i++) {
						psDefaultAlolan.println(colorsUpdate[i]);
					}
					psDefaultAlolan.close();

					shinyListUpdate = readUpdate.getShiniesAll();
					colorsUpdate = new int[shinyListUpdate.length - 1];
					idx = 0;
					while (!shinyListUpdate[idx].equals("pokemon_icon_" + dexNum + "_00_shiny.png")) {
						idx++;
					}
					Scanner scanAll = new Scanner(new FileInputStream("assets/colorAll"));
					for (int i = 0; i < idx; i++) {
						colorsUpdate[i] = scanAll.nextInt() % 5;
					}
					scanAll.nextInt();
					for (int i = idx; i < shinyListUpdate.length - 1; i++) {
						colorsUpdate[i] = scanAll.nextInt() % 5;
					}
					scanAll.close();
					PrintStream psAll = new PrintStream("assets/colorAll");
					for (int i = 0; i < colorsUpdate.length; i++) {
						psAll.println(colorsUpdate[i]);
					}
					psAll.close();

					Scanner scan = new Scanner(new FileInputStream("assets/shinyAvailable"));
					String[] nums = new String[read.getShiniesAvailableDefault().length];
					int j = 0;
					while (scan.hasNext()) {
						String number = scan.next();
						if (!number.equals(dexNum)) {
							nums[j] = number;
							j++;
						}
					}
					PrintStream out = new PrintStream("assets/shinyAvailable");
					for (int i = 0; i < nums.length - 1; i++) {
						out.println(nums[i]);
					}
					scan.close();
					out.close();

					new ShinyListGUI(type);
					setVisible(false);
				} catch (IOException e1) {
					System.out.println("Could not add shiny");
				}
			}
		});
		c.gridx = 11;
		c.gridy = 0;
		panel.add(removeShiny, c);

		colors = read.readColors(type, shinyList.length);
		buttons = new ShinyButton[shinyList.length];
		createShinyButtons(panel, c, shinyList);

		JScrollPane pane = new JScrollPane(panel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		cp.add(pane);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1400, 800);
		setVisible(true);
	}

	/**
	 * Starts the program by showing the selection frame
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
		createSelectionFrame();
	}

	/**
	 * Captures the GUI in a png file called MyShinyChecklist
	 * @param component GUI being captured
	 */
	private static void captureComponent(Component component) {
		Rectangle rect = component.getBounds();

		try {
			String format = "png";
			String fileName = "MyShinyChecklist" + "." + format;
			BufferedImage captureImage = new BufferedImage(rect.width, rect.height, BufferedImage.TYPE_INT_ARGB);
			component.paint(captureImage.getGraphics());

			ImageIO.write(captureImage, format, new File(fileName));

			System.out.printf("The screenshot of %s was saved!", component.getName());
		} catch (IOException ex) {
			System.err.println(ex);
		}
	}

	/**
	 * Creates all of the shiny buttons that appear in the list
	 * @param panel panel of list
	 * @param c grid bag constraints of list
	 * @param shinyList array of filenames
	 */
	private void createShinyButtons(JPanel panel, GridBagConstraints c, String[] shinyList) {
		for (int i = 0; i < shinyList.length; i++) {
			image = new ImageIcon("assets/ShinyDefault/" + shinyList[i]);
			if (image.getIconHeight() < 10) {
				image = new ImageIcon("assets/ShinyAlola/" + shinyList[i]);
			}
			if (image.getIconHeight() < 10) {
				image = new ImageIcon("assets/ShinySpecial/" + shinyList[i]);
			}
			Image newimg = image.getImage().getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH);
			image = new ImageIcon(newimg); // transform it back
			button = new ShinyButton(image);
			button.setCurrentColor(colors[i]);
			buttons[i] = button;
			c.gridx = i % 12;
			c.gridy = i / 12 + 1;
			panel.add(button, c);
		}
	}

	/**
	 * Creates the Save Colors button
	 * @param panel panel of list
	 * @param type type of list
	 */
	private void saveColorsButton(JPanel panel, String type) {
		JButton saveColors = new JButton();
		saveColors.setBounds(150, 150, 300, 150);
		saveColors.setText("Save Colors");
		saveColors.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					PrintStream out = new PrintStream("assets/color" + type);
					for (int i = 0; i < buttons.length; i++) {
						out.println(buttons[i].getCurrentColor());
					}
					out.close();
				} catch (FileNotFoundException except) {
					System.out.println("Could not find saved colors");
				}
			}
		});
		panel.add(saveColors);
	}

	/**
	 * Creates the Screenshot button
	 * @param panel panel of list
	 */
	private void screenshotButton(JPanel panel) {
		JButton screenshot = new JButton();
		screenshot.setBounds(150, 150, 300, 150);
		screenshot.setText("Screenshot");
		screenshot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				captureComponent(panel);
			}
		});
		panel.add(screenshot);
	}

	/**
	 * Creates the Clear Colors button
	 * @param panel panel of list
	 * @param type type of list
	 */
	private void clearColorsButton(JPanel panel, String type) {
		JButton clearColors = new JButton();
		clearColors.setBounds(150, 150, 300, 150);
		clearColors.setText("Clear Colors");
		clearColors.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					PrintStream out = new PrintStream("assets/color" + type);
					for (int i = 0; i < buttons.length; i++) {
						buttons[i].setCurrentColor(0);
						buttons[i].setVisible(false);
						buttons[i].setVisible(true);
						out.println(buttons[i].getCurrentColor());
					}
					out.close();
				} catch (FileNotFoundException except) {
					System.out.println("Could not find saved colors");
				}
			}
		});
		panel.add(clearColors);
	}

	/**
	 * Creates the list selection GUI that appears at the beginning
	 */
	private static void createSelectionFrame() {
		JFrame frame = new JFrame();
		Container cp = frame.getContentPane();
		JPanel selection = new JPanel(new FlowLayout());
		JButton defaultOnly = new JButton();
		defaultOnly.setBounds(150, 150, 300, 150);
		defaultOnly.setText("Normal Shinies");
		JButton alolanOnly = new JButton();
		alolanOnly.setBounds(150, 150, 300, 150);
		alolanOnly.setText("Alolan Shinies");
		JButton specialOnly = new JButton();
		specialOnly.setBounds(150, 150, 300, 150);
		specialOnly.setText("Costume Shinies");
		JButton defaultAndAlolan = new JButton();
		defaultAndAlolan.setBounds(150, 150, 300, 150);
		defaultAndAlolan.setText("Normal & Alolan Shinies");
		JButton allShinies = new JButton();
		allShinies.setBounds(150, 150, 300, 150);
		allShinies.setText("Normal, Alolan, & Costume Shinies");

		selection.add(defaultOnly);
		selection.add(alolanOnly);
		selection.add(specialOnly);
		selection.add(defaultAndAlolan);
		selection.add(allShinies);
		cp.add(selection);
		frame.setTitle("Pokemon Go Shiny Checklist - Select List");
		frame.setSize(550, 125);
		frame.setLocation(250, 250);
		frame.setVisible(true);

		defaultOnly.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new ShinyListGUI("Default");
			}
		});

		alolanOnly.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new ShinyListGUI("Alolan");
			}
		});

		specialOnly.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new ShinyListGUI("Special");
			}
		});

		defaultAndAlolan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new ShinyListGUI("DefaultAlolan");
			}
		});

		allShinies.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new ShinyListGUI("All");
			}
		});
	}
}