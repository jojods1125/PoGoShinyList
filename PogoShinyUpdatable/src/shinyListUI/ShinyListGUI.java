/**
 * 
 */
package shinyListUI;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
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
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import shinyListMaker.ShinyListReader;

/**
 * Displays all of the shinies in the shinyAvailable file and allows users to
 * change background colors on individual shinies. Users can also choose to show
 * Forms and/or "hat" shinies.
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
	 * 
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
		if (type.equals("Forms")) {
			shinyList = read.getShiniesAvailableForms();
		}
		if (type.equals("Special")) {
			shinyList = read.getShiniesAvailableSpecial();
		}
		if (type.equals("DefaultForms")) {
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
		enterShiny.setMargin(new Insets(3,4,3,5));
		c.gridx = 6;
		c.gridy = 0;
		panel.add(enterShiny, c);

		JButton addShiny = new JButton();
		addShiny.setMargin(new Insets(2,20,2,20));
		addShiny.setText("Add Shiny");
		addShiny.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String dexNum = enterShiny.getText();
				try {
					File file = new File("saveData/shinyAvailable");
					FileWriter out = new FileWriter(file, true);
					out.write(dexNum + "\n");
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
					Scanner scanDefault = new Scanner(new FileInputStream("saveData/colorDefault"));
					for (int i = 0; i < idx; i++) {
						colorsUpdate[i] = scanDefault.nextInt() % 5;
					}
					colorsUpdate[idx] = 0;
					for (int i = idx + 1; i < shinyListUpdate.length; i++) {
						colorsUpdate[i] = scanDefault.nextInt() % 5;
					}
					scanDefault.close();
					PrintStream psDefault = new PrintStream("saveData/colorDefault");
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
					Scanner scanDefaultForms = new Scanner(new FileInputStream("saveData/colorDefaultForms"));
					for (int i = 0; i < idx; i++) {
						colorsUpdate[i] = scanDefaultForms.nextInt() % 5;
					}
					colorsUpdate[idx] = 0;
					for (int i = idx + 1; i < shinyListUpdate.length; i++) {
						colorsUpdate[i] = scanDefaultForms.nextInt() % 5;
					}
					scanDefaultForms.close();
					PrintStream psDefaultForms = new PrintStream("saveData/colorDefaultForms");
					for (int i = 0; i < colorsUpdate.length; i++) {
						psDefaultForms.println(colorsUpdate[i]);
					}
					psDefaultForms.close();

					shinyListUpdate = readUpdate.getShiniesAll();
					colorsUpdate = new int[shinyListUpdate.length];
					idx = 0;
					while (!shinyListUpdate[idx].equals("pokemon_icon_" + dexNum + "_00_shiny.png")) {
						idx++;
					}
					Scanner scanAll = new Scanner(new FileInputStream("saveData/colorAll"));
					for (int i = 0; i < idx; i++) {
						colorsUpdate[i] = scanAll.nextInt() % 5;
					}
					colorsUpdate[idx] = 0;
					for (int i = idx + 1; i < shinyListUpdate.length; i++) {
						colorsUpdate[i] = scanAll.nextInt() % 5;
					}
					scanAll.close();
					PrintStream psAll = new PrintStream("saveData/colorAll");
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
		c.gridx = 7;
		c.gridy = 0;
		panel.add(addShiny, c);

		JButton removeShiny = new JButton();
		removeShiny.setMargin(new Insets(2,12,2,12));
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
					Scanner scanDefault = new Scanner(new FileInputStream("saveData/colorDefault"));
					for (int i = 0; i < idx; i++) {
						colorsUpdate[i] = scanDefault.nextInt() % 5;
					}
					scanDefault.nextInt();
					for (int i = idx; i < shinyListUpdate.length - 1; i++) {
						colorsUpdate[i] = scanDefault.nextInt() % 5;
					}
					scanDefault.close();
					PrintStream psDefault = new PrintStream("saveData/colorDefault");
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
					Scanner scanDefaultForms = new Scanner(new FileInputStream("saveData/colorDefaultForms"));
					for (int i = 0; i < idx; i++) {
						colorsUpdate[i] = scanDefaultForms.nextInt() % 5;
					}
					scanDefaultForms.nextInt();
					for (int i = idx; i < shinyListUpdate.length - 1; i++) {
						colorsUpdate[i] = scanDefaultForms.nextInt() % 5;
					}
					scanDefaultForms.close();
					PrintStream psDefaultForms = new PrintStream("saveData/colorDefaultForms");
					for (int i = 0; i < colorsUpdate.length; i++) {
						psDefaultForms.println(colorsUpdate[i]);
					}
					psDefaultForms.close();

					shinyListUpdate = readUpdate.getShiniesAll();
					colorsUpdate = new int[shinyListUpdate.length - 1];
					idx = 0;
					while (!shinyListUpdate[idx].equals("pokemon_icon_" + dexNum + "_00_shiny.png")) {
						idx++;
					}
					Scanner scanAll = new Scanner(new FileInputStream("saveData/colorAll"));
					for (int i = 0; i < idx; i++) {
						colorsUpdate[i] = scanAll.nextInt() % 5;
					}
					scanAll.nextInt();
					for (int i = idx; i < shinyListUpdate.length - 1; i++) {
						colorsUpdate[i] = scanAll.nextInt() % 5;
					}
					scanAll.close();
					PrintStream psAll = new PrintStream("saveData/colorAll");
					for (int i = 0; i < colorsUpdate.length; i++) {
						psAll.println(colorsUpdate[i]);
					}
					psAll.close();

					Scanner scan = new Scanner(new FileInputStream("saveData/shinyAvailable"));
					String[] nums = new String[read.getShiniesAvailableDefault().length];
					int j = 0;
					while (scan.hasNext()) {
						String number = scan.next();
						if (!number.equals(dexNum)) {
							nums[j] = number;
							j++;
						}
					}
					PrintStream out = new PrintStream("saveData/shinyAvailable");
					for (int i = 0; i < nums.length - 1; i++) {
						out.println(nums[i]);
					}
					scan.close();
					out.close();

					new ShinyListGUI(type);
					setVisible(false);
				} catch (IOException e1) {
					System.out.println("Could not remove shiny");
				}
			}
		});
		c.gridx = 8;
		c.gridy = 0;
		panel.add(removeShiny, c);

		ShinyListGUI gui = this;
		
		JButton editAltForm = new JButton();
		editAltForm.setMargin(new Insets(2,8,2,8));
		editAltForm.setText("Edit Alt Forms");
		editAltForm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createAltFormFrame(type, gui, read);
			}
		});
		c.gridx = 10;
		c.gridy = 0;
		panel.add(editAltForm, c);
		
		JButton editCostume = new JButton();
		editCostume.setMargin(new Insets(2,8,2,8));
		editCostume.setText("Edit Costumes");
		editCostume.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createCostumeFrame(type, gui, read);
			}
		});
		c.gridx = 11;
		c.gridy = 0;
		panel.add(editCostume, c);

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
	 * 
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
		createSelectionFrame();
	}

	/**
	 * Captures the GUI in a png file called MyShinyChecklist
	 * 
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
	 * 
	 * @param panel     panel of list
	 * @param c         grid bag constraints of list
	 * @param shinyList array of filenames
	 */
	private void createShinyButtons(JPanel panel, GridBagConstraints c, String[] shinyList) {
		for (int i = 0; i < shinyList.length; i++) {
			image = new ImageIcon("assets/ShinyDefault/" + shinyList[i]);
			if (image.getIconHeight() < 10) {
				image = new ImageIcon("assets/ShinyForms/" + shinyList[i]);
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
	 * 
	 * @param panel panel of list
	 * @param type  type of list
	 */
	private void saveColorsButton(JPanel panel, String type) {
		JButton saveColors = new JButton();
		saveColors.setBounds(150, 150, 300, 150);
		saveColors.setText("Save Colors");
		saveColors.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					PrintStream out = new PrintStream("saveData/color" + type);
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
	 * 
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
	 * 
	 * @param panel panel of list
	 * @param type  type of list
	 */
	private void clearColorsButton(JPanel panel, String type) {
		JButton clearColors = new JButton();
		clearColors.setBounds(150, 150, 300, 150);
		clearColors.setText("Clear Colors");
		clearColors.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					PrintStream out = new PrintStream("saveData/color" + type);
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
		JButton FormsOnly = new JButton();
		FormsOnly.setBounds(150, 150, 300, 150);
		FormsOnly.setText("Alt Form Shinies");
		JButton specialOnly = new JButton();
		specialOnly.setBounds(150, 150, 300, 150);
		specialOnly.setText("Costume Shinies");
		JButton defaultAndForms = new JButton();
		defaultAndForms.setBounds(150, 150, 300, 150);
		defaultAndForms.setText("Normal & Alt Form Shinies");
		JButton allShinies = new JButton();
		allShinies.setBounds(150, 150, 300, 150);
		allShinies.setText("Normal, Alt Form, & Costume Shinies");

		selection.add(defaultOnly);
		selection.add(FormsOnly);
		selection.add(specialOnly);
		selection.add(defaultAndForms);
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

		FormsOnly.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new ShinyListGUI("Forms");
			}
		});

		specialOnly.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new ShinyListGUI("Special");
			}
		});

		defaultAndForms.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new ShinyListGUI("DefaultForms");
			}
		});

		allShinies.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new ShinyListGUI("All");
			}
		});
	}

	/**
	 * Creates the edit alt form shiny window
	 * @param type type of list
	 * @param gui old instance of list
	 * @param read reader instance
	 */
	private static void createAltFormFrame(String type, ShinyListGUI gui, ShinyListReader read) {
		JFrame frame = new JFrame();
		JPanel pane = new JPanel();
		BoxLayout boxlayout = new BoxLayout(pane, BoxLayout.Y_AXIS);
		pane.setLayout(boxlayout);

		JLabel title = new JLabel("Edit Alt Form Shiny List");
		pane.add(title);

		pane.add(Box.createRigidArea(new Dimension(5, 20)));

		JLabel helper = new JLabel("Enter the first number in the asset filename here");
		pane.add(helper);

		JTextField enterDex = new JTextField();
		enterDex.setText("Enter New Dex #");
		enterDex.setMaximumSize(new Dimension(350, enterDex.getPreferredSize().height));
		pane.add(enterDex);

		pane.add(Box.createRigidArea(new Dimension(5, 10)));

		JLabel helper2 = new JLabel("Enter the second number in the asset filename here");
		pane.add(helper2);

		JTextField enterForm = new JTextField();
		enterForm.setText("Enter New Form #");
		enterForm.setMaximumSize(new Dimension(350, enterDex.getPreferredSize().height));
		pane.add(enterForm);
		
		pane.add(Box.createRigidArea(new Dimension(5, 10)));

		JButton addShiny = new JButton();
		addShiny.setBounds(150, 150, 300, 150);
		addShiny.setText("Add Alt Form Shiny");
		pane.add(addShiny);
		
		pane.add(Box.createRigidArea(new Dimension(5, 5)));

		JButton removeShiny = new JButton();
		removeShiny.setBounds(150, 150, 300, 150);
		removeShiny.setText("Remove Alt Form Shiny");
		pane.add(removeShiny);

		frame.add(pane);
		frame.setTitle("Edit Alt Form List");
		frame.setSize(350, 250);
		frame.setLocation(250, 250);
		frame.setVisible(true);

		addShiny.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String dexNum = enterDex.getText();
				String formNum = enterForm.getText();
				try {
					File file = new File("saveData/shinyFormsAvailable");
					FileWriter out = new FileWriter(file, true);
					out.write("pokemon_icon_" + dexNum + "_" + formNum + "_shiny.png" + "\n");
					out.close();

					ShinyListReader readUpdate = new ShinyListReader();
					String[] shinyListUpdate;
					int[] colorsUpdate;
					int idx;

					shinyListUpdate = readUpdate.getShiniesAvailableForms();
					colorsUpdate = new int[shinyListUpdate.length];
					idx = 0;
					while (!shinyListUpdate[idx].equals("pokemon_icon_" + dexNum + "_" + formNum + "_shiny.png")) {
						idx++;
					}
					Scanner scanDefault = new Scanner(new FileInputStream("saveData/colorForms"));
					for (int i = 0; i < idx; i++) {
						colorsUpdate[i] = scanDefault.nextInt() % 5;
					}
					colorsUpdate[idx] = 0;
					for (int i = idx + 1; i < shinyListUpdate.length; i++) {
						colorsUpdate[i] = scanDefault.nextInt() % 5;
					}
					scanDefault.close();
					PrintStream psDefault = new PrintStream("saveData/colorForms");
					for (int i = 0; i < colorsUpdate.length; i++) {
						psDefault.println(colorsUpdate[i]);
					}
					psDefault.close();

					shinyListUpdate = readUpdate.getShiniesAllButSpecial();
					colorsUpdate = new int[shinyListUpdate.length];
					idx = 0;
					while (!shinyListUpdate[idx].equals("pokemon_icon_" + dexNum + "_" + formNum + "_shiny.png")) {
						idx++;
					}
					Scanner scanDefaultForms = new Scanner(new FileInputStream("saveData/colorDefaultForms"));
					for (int i = 0; i < idx; i++) {
						colorsUpdate[i] = scanDefaultForms.nextInt() % 5;
					}
					colorsUpdate[idx] = 0;
					for (int i = idx + 1; i < shinyListUpdate.length; i++) {
						colorsUpdate[i] = scanDefaultForms.nextInt() % 5;
					}
					scanDefaultForms.close();
					PrintStream psDefaultForms = new PrintStream("saveData/colorDefaultForms");
					for (int i = 0; i < colorsUpdate.length; i++) {
						psDefaultForms.println(colorsUpdate[i]);
					}
					psDefaultForms.close();

					shinyListUpdate = readUpdate.getShiniesAll();
					colorsUpdate = new int[shinyListUpdate.length];
					idx = 0;
					while (!shinyListUpdate[idx].equals("pokemon_icon_" + dexNum + "_" + formNum + "_shiny.png")) {
						idx++;
					}
					Scanner scanAll = new Scanner(new FileInputStream("saveData/colorAll"));
					for (int i = 0; i < idx; i++) {
						colorsUpdate[i] = scanAll.nextInt() % 5;
					}
					colorsUpdate[idx] = 0;
					for (int i = idx + 1; i < shinyListUpdate.length; i++) {
						colorsUpdate[i] = scanAll.nextInt() % 5;
					}
					scanAll.close();
					PrintStream psAll = new PrintStream("saveData/colorAll");
					for (int i = 0; i < colorsUpdate.length; i++) {
						psAll.println(colorsUpdate[i]);
					}
					psAll.close();
					
					frame.setVisible(false);
					gui.setVisible(false);
					new ShinyListGUI(type);
				} catch (IOException e1) {
					System.out.println("Could not add shiny: " + e1.getMessage());
				}
				frame.setVisible(false);
			}
		});

		removeShiny.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String dexNum = enterDex.getText();
				String formNum = enterForm.getText();
				try {
					ShinyListReader readUpdate = new ShinyListReader();
					String[] shinyListUpdate;
					int[] colorsUpdate;
					int idx;

					shinyListUpdate = readUpdate.getShiniesAvailableForms();
					colorsUpdate = new int[shinyListUpdate.length - 1];
					idx = 0;
					while (!shinyListUpdate[idx].equals("pokemon_icon_" + dexNum + "_" + formNum + "_shiny.png")) {
						idx++;
					}
					Scanner scanDefault = new Scanner(new FileInputStream("saveData/colorForms"));
					for (int i = 0; i < idx; i++) {
						colorsUpdate[i] = scanDefault.nextInt() % 5;
					}
					scanDefault.nextInt();
					for (int i = idx; i < shinyListUpdate.length - 1; i++) {
						colorsUpdate[i] = scanDefault.nextInt() % 5;
					}
					scanDefault.close();
					PrintStream psDefault = new PrintStream("saveData/colorForms");
					for (int i = 0; i < colorsUpdate.length; i++) {
						psDefault.println(colorsUpdate[i]);
					}
					psDefault.close();

					shinyListUpdate = readUpdate.getShiniesAllButSpecial();
					colorsUpdate = new int[shinyListUpdate.length - 1];
					idx = 0;
					while (!shinyListUpdate[idx].equals("pokemon_icon_" + dexNum + "_" + formNum + "_shiny.png")) {
						idx++;
					}
					Scanner scanDefaultForms = new Scanner(new FileInputStream("saveData/colorDefaultForms"));
					for (int i = 0; i < idx; i++) {
						colorsUpdate[i] = scanDefaultForms.nextInt() % 5;
					}
					scanDefaultForms.nextInt();
					for (int i = idx; i < shinyListUpdate.length - 1; i++) {
						colorsUpdate[i] = scanDefaultForms.nextInt() % 5;
					}
					scanDefaultForms.close();
					PrintStream psDefaultForms = new PrintStream("saveData/colorDefaultForms");
					for (int i = 0; i < colorsUpdate.length; i++) {
						psDefaultForms.println(colorsUpdate[i]);
					}
					psDefaultForms.close();

					shinyListUpdate = readUpdate.getShiniesAll();
					colorsUpdate = new int[shinyListUpdate.length - 1];
					idx = 0;
					while (!shinyListUpdate[idx].equals("pokemon_icon_" + dexNum + "_" + formNum + "_shiny.png")) {
						idx++;
					}
					Scanner scanAll = new Scanner(new FileInputStream("saveData/colorAll"));
					for (int i = 0; i < idx; i++) {
						colorsUpdate[i] = scanAll.nextInt() % 5;
					}
					scanAll.nextInt();
					for (int i = idx; i < shinyListUpdate.length - 1; i++) {
						colorsUpdate[i] = scanAll.nextInt() % 5;
					}
					scanAll.close();
					PrintStream psAll = new PrintStream("saveData/colorAll");
					for (int i = 0; i < colorsUpdate.length; i++) {
						psAll.println(colorsUpdate[i]);
					}
					psAll.close();

					Scanner scan = new Scanner(new FileInputStream("saveData/shinyFormsAvailable"));
					String[] nums = new String[read.getShiniesAvailableForms().length];
					int j = 0;
					while (scan.hasNext()) {
						String number = scan.next();
						if (!number.equals("pokemon_icon_" + dexNum + "_" + formNum + "_shiny.png")) {
							nums[j] = number;
							j++;
						}
					}
					PrintStream out = new PrintStream("saveData/shinyFormsAvailable");
					for (int i = 0; i < nums.length - 1; i++) {
						out.println(nums[i]);
					}
					scan.close();
					out.close();

					frame.setVisible(false);
					gui.setVisible(false);
					new ShinyListGUI(type);
				} catch (IOException e1) {
					System.out.println("Could not remove shiny");
				}
			}
		});
	}
	
	/**
	 * Creates the edit costume shiny window
	 * @param type type of list
	 * @param gui old instance of list
	 * @param read reader instance
	 */
	private static void createCostumeFrame(String type, ShinyListGUI gui, ShinyListReader read) {
		JFrame frame = new JFrame();
		JPanel pane = new JPanel();
		BoxLayout boxlayout = new BoxLayout(pane, BoxLayout.Y_AXIS);
		pane.setLayout(boxlayout);

		JLabel title = new JLabel("Edit Costume Shiny List");
		pane.add(title);

		pane.add(Box.createRigidArea(new Dimension(5, 20)));

		JLabel helper = new JLabel("Enter the first number in the asset filename here");
		pane.add(helper);

		JTextField enterDex = new JTextField();
		enterDex.setText("Enter New Dex #");
		enterDex.setMaximumSize(new Dimension(350, enterDex.getPreferredSize().height));
		pane.add(enterDex);

		pane.add(Box.createRigidArea(new Dimension(5, 10)));

		JLabel helper2 = new JLabel("Enter the second number in the asset filename here");
		pane.add(helper2);

		JTextField enterForm = new JTextField();
		enterForm.setText("Enter New Form #");
		enterForm.setMaximumSize(new Dimension(350, enterDex.getPreferredSize().height));
		pane.add(enterForm);
		
		pane.add(Box.createRigidArea(new Dimension(5, 10)));
		
		JLabel helper3 = new JLabel("Enter the third number in the asset filename here");
		pane.add(helper3);

		JTextField enterHat = new JTextField();
		enterHat.setText("Enter New Form #");
		enterHat.setMaximumSize(new Dimension(350, enterDex.getPreferredSize().height));
		pane.add(enterHat);
		
		pane.add(Box.createRigidArea(new Dimension(5, 10)));

		JButton addShiny = new JButton();
		addShiny.setBounds(150, 150, 300, 150);
		addShiny.setText("Add Costume Shiny");
		pane.add(addShiny);
		
		pane.add(Box.createRigidArea(new Dimension(5, 5)));

		JButton removeShiny = new JButton();
		removeShiny.setBounds(150, 150, 300, 150);
		removeShiny.setText("Remove Costume Shiny");
		pane.add(removeShiny);

		frame.add(pane);
		frame.setTitle("Edit Costume List");
		frame.setSize(350, 290);
		frame.setLocation(250, 250);
		frame.setVisible(true);

		addShiny.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String dexNum = enterDex.getText();
				String formNum = enterForm.getText();
				String hatNum = enterHat.getText();
				try {
					File file = new File("saveData/shinySpecialAvailable");
					FileWriter out = new FileWriter(file, true);
					out.write("pokemon_icon_" + dexNum + "_" + formNum + "_" + hatNum +"_shiny.png" + "\n");
					out.close();

					ShinyListReader readUpdate = new ShinyListReader();
					String[] shinyListUpdate;
					int[] colorsUpdate;
					int idx;

					shinyListUpdate = readUpdate.getShiniesAvailableSpecial();
					colorsUpdate = new int[shinyListUpdate.length];
					idx = 0;
					while (!shinyListUpdate[idx].equals("pokemon_icon_" + dexNum + "_" + formNum + "_" + hatNum +"_shiny.png")) {
						idx++;
					}
					Scanner scanDefault = new Scanner(new FileInputStream("saveData/colorSpecial"));
					for (int i = 0; i < idx; i++) {
						colorsUpdate[i] = scanDefault.nextInt() % 5;
					}
					colorsUpdate[idx] = 0;
					for (int i = idx + 1; i < shinyListUpdate.length; i++) {
						colorsUpdate[i] = scanDefault.nextInt() % 5;
					}
					scanDefault.close();
					PrintStream psDefault = new PrintStream("saveData/colorSpecial");
					for (int i = 0; i < colorsUpdate.length; i++) {
						psDefault.println(colorsUpdate[i]);
					}
					psDefault.close();

					shinyListUpdate = readUpdate.getShiniesAll();
					colorsUpdate = new int[shinyListUpdate.length];
					idx = 0;
					while (!shinyListUpdate[idx].equals("pokemon_icon_" + dexNum + "_" + formNum + "_shiny.png")) {
						idx++;
					}
					Scanner scanAll = new Scanner(new FileInputStream("saveData/colorAll"));
					for (int i = 0; i < idx; i++) {
						colorsUpdate[i] = scanAll.nextInt() % 5;
					}
					colorsUpdate[idx] = 0;
					for (int i = idx + 1; i < shinyListUpdate.length; i++) {
						colorsUpdate[i] = scanAll.nextInt() % 5;
					}
					scanAll.close();
					PrintStream psAll = new PrintStream("saveData/colorAll");
					for (int i = 0; i < colorsUpdate.length; i++) {
						psAll.println(colorsUpdate[i]);
					}
					psAll.close();
					
					frame.setVisible(false);
					gui.setVisible(false);
					new ShinyListGUI(type);
				} catch (IOException e1) {
					System.out.println("Could not add shiny: " + e1.getMessage());
				}
				frame.setVisible(false);
			}
		});

		removeShiny.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String dexNum = enterDex.getText();
				String formNum = enterForm.getText();
				String hatNum = enterHat.getText();
				try {
					ShinyListReader readUpdate = new ShinyListReader();
					String[] shinyListUpdate;
					int[] colorsUpdate;
					int idx;

					shinyListUpdate = readUpdate.getShiniesAvailableSpecial();
					colorsUpdate = new int[shinyListUpdate.length - 1];
					idx = 0;
					while (!shinyListUpdate[idx].equals("pokemon_icon_" + dexNum + "_" + formNum + "_" + hatNum +"_shiny.png")) {
						idx++;
					}
					Scanner scanDefault = new Scanner(new FileInputStream("saveData/colorSpecial"));
					for (int i = 0; i < idx; i++) {
						colorsUpdate[i] = scanDefault.nextInt() % 5;
					}
					scanDefault.nextInt();
					for (int i = idx; i < shinyListUpdate.length - 1; i++) {
						colorsUpdate[i] = scanDefault.nextInt() % 5;
					}
					scanDefault.close();
					PrintStream psDefault = new PrintStream("saveData/colorSpecial");
					for (int i = 0; i < colorsUpdate.length; i++) {
						psDefault.println(colorsUpdate[i]);
					}
					psDefault.close();

					shinyListUpdate = readUpdate.getShiniesAll();
					colorsUpdate = new int[shinyListUpdate.length - 1];
					idx = 0;
					while (!shinyListUpdate[idx].equals("pokemon_icon_" + dexNum + "_" + formNum + "_" + hatNum +"_shiny.png")) {
						idx++;
					}
					Scanner scanAll = new Scanner(new FileInputStream("saveData/colorAll"));
					for (int i = 0; i < idx; i++) {
						colorsUpdate[i] = scanAll.nextInt() % 5;
					}
					scanAll.nextInt();
					for (int i = idx; i < shinyListUpdate.length - 1; i++) {
						colorsUpdate[i] = scanAll.nextInt() % 5;
					}
					scanAll.close();
					PrintStream psAll = new PrintStream("saveData/colorAll");
					for (int i = 0; i < colorsUpdate.length; i++) {
						psAll.println(colorsUpdate[i]);
					}
					psAll.close();

					Scanner scan = new Scanner(new FileInputStream("saveData/shinySpecialAvailable"));
					String[] nums = new String[read.getShiniesAvailableSpecial().length];
					int j = 0;
					while (scan.hasNext()) {
						String number = scan.next();
						if (!number.equals("pokemon_icon_" + dexNum + "_" + formNum + "_" + hatNum +"_shiny.png")) {
							nums[j] = number;
							j++;
						}
					}
					PrintStream out = new PrintStream("saveData/shinySpecialAvailable");
					for (int i = 0; i < nums.length - 1; i++) {
						out.println(nums[i]);
					}
					scan.close();
					out.close();

					frame.setVisible(false);
					gui.setVisible(false);
					new ShinyListGUI(type);
				} catch (IOException e1) {
					System.out.println("Could not remove shiny");
				}
			}
		});
	}
}
