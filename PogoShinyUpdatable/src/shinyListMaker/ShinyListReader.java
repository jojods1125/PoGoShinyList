/**
 * 
 */
package shinyListMaker;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Reads the list of shiny Pokedex numbers and creates an array of file names.
 * Also stores file names for Forms and "hat" shinies.
 * 
 * @author Joseph Dasilva
 */
public class ShinyListReader {

	/** List of normal shiny Pokemon filenames */
	private String[] shiniesDefault;

	/** List of Forms shiny Pokemon filenames */
	private String[] shiniesForms;

	/** List of costume shiny Pokemon filenames */
	private String[] shiniesSpecial;

	/**
	 * Constructs the ShinyListReader by reading through all relevant shiny files
	 */
	public ShinyListReader() {
		try {
			// Runs through the shinyAvailable file and records number of lines
			Scanner sizer = new Scanner(new FileInputStream("saveData/shinyAvailable"));
			int size = 0;
			while (sizer.hasNext()) {
				size++;
				sizer.next();
			}
			sizer.close();

			// Runs through the shinyAvailable file and creates png filenames
			Scanner scan = new Scanner(new FileInputStream("saveData/shinyAvailable"));
			int idx = 0;
			shiniesDefault = new String[size];
			while (scan.hasNext()) {
				shiniesDefault[idx] = "pokemon_icon_" + scan.next() + "_00_shiny.png";
				idx++;
			}
			scan.close();

			// Sort the numbers so they're in numerical order
			Sorter<String> sort = new MergeSorter<String>();
			sort.sort(shiniesDefault);

			// If more shiny forms come out, add to shinyFormsAvailable file
			// Runs through the shinyFormsAvailable file and records number of lines
			Scanner sizerFo = new Scanner(new FileInputStream("assets/shinyFormsAvailable"));
			int sizeFo = 0;
			while (sizerFo.hasNext()) {
				sizeFo++;
				sizerFo.next();
			}
			sizerFo.close();

			// Runs through the shinyFormsAvailable file and gets png filenames
			Scanner scanFo = new Scanner(new FileInputStream("assets/shinyFormsAvailable"));
			int idxFo = 0;
			shiniesForms = new String[sizeFo];
			while (scanFo.hasNext()) {
				shiniesForms[idxFo] = scanFo.next();
				idxFo++;
			}
			scanFo.close();

			// Sort the special files so they're in numerical order
			sort.sort(shiniesForms);

//			// If more Formsn Pokemon come into existence for some ungodly reason, put them
//			// here
//			shiniesForms = new String[] { "pokemon_icon_019_61_shiny.png", "pokemon_icon_020_61_shiny.png",
//					"pokemon_icon_026_61_shiny.png", "pokemon_icon_027_61_shiny.png", "pokemon_icon_028_61_shiny.png",
//					"pokemon_icon_037_61_shiny.png", "pokemon_icon_038_61_shiny.png", "pokemon_icon_050_61_shiny.png",
//					"pokemon_icon_051_61_shiny.png", "pokemon_icon_052_61_shiny.png", "pokemon_icon_053_61_shiny.png",
//					"pokemon_icon_074_61_shiny.png", "pokemon_icon_075_61_shiny.png", "pokemon_icon_076_61_shiny.png",
//					"pokemon_icon_088_61_shiny.png", "pokemon_icon_089_61_shiny.png", "pokemon_icon_103_61_shiny.png",
//					"pokemon_icon_105_61_shiny.png" };

			// If more shiny hats come out, add to shinySpecialAvailable file
			// Runs through the shinySpecialAvailable file and records number of lines
			Scanner sizerSp = new Scanner(new FileInputStream("assets/shinySpecialAvailable"));
			int sizeSp = 0;
			while (sizerSp.hasNext()) {
				sizeSp++;
				sizerSp.next();
			}
			sizerSp.close();

			// Runs through the shinySpecialAvailable file and gets png filenames
			Scanner scanSp = new Scanner(new FileInputStream("assets/shinySpecialAvailable"));
			int idxSp = 0;
			shiniesSpecial = new String[sizeSp];
			while (scanSp.hasNext()) {
				shiniesSpecial[idxSp] = scanSp.next();
				idxSp++;
			}
			scanSp.close();

			// Sort the special files so they're in numerical order
			sort.sort(shiniesSpecial);
		} catch (FileNotFoundException e) {
			System.out.println("Could not find file saveData/shinyAvailable");
		}
	}

	/**
	 * Gets the normal shiny Pokemon filenames
	 * 
	 * @return array of normal shiny Pokemon filenames
	 */
	public String[] getShiniesAvailableDefault() {
		return shiniesDefault;
	}

	/**
	 * Gets the Forms shiny Pokemon filenames
	 * 
	 * @return array of Forms shiny Pokemon filenames
	 */
	public String[] getShiniesAvailableForms() {
		return shiniesForms;
	}

	/**
	 * Gets the costume shiny Pokemon filenames
	 * 
	 * @return array of costume shiny Pokemon filenames
	 */
	public String[] getShiniesAvailableSpecial() {
		return shiniesSpecial;
	}

	/**
	 * Gets the normal and Forms shiny Pokemon filenames
	 * 
	 * @return array of normal and Forms shiny Pokemon filenames
	 */
	public String[] getShiniesAllButSpecial() {
		String[] combinedShinies = new String[shiniesDefault.length + shiniesForms.length];
		for (int i = 0; i < shiniesDefault.length; i++) {
			combinedShinies[i] = shiniesDefault[i];
		}
		for (int i = 0; i < shiniesForms.length; i++) {
			combinedShinies[i + shiniesDefault.length] = shiniesForms[i];
		}
		Sorter<String> sort = new MergeSorter<String>();
		sort.sort(combinedShinies);
		return combinedShinies;
	}

	/**
	 * Gets all shiny Pokemon filenames
	 * 
	 * @return array of all shiny Pokemon filenames
	 */
	public String[] getShiniesAll() {
		String[] combinedShinies = new String[shiniesDefault.length + shiniesForms.length + shiniesSpecial.length];
		for (int i = 0; i < shiniesDefault.length; i++) {
			combinedShinies[i] = shiniesDefault[i];
		}
		for (int i = 0; i < shiniesForms.length; i++) {
			combinedShinies[i + shiniesDefault.length] = shiniesForms[i];
		}
		for (int i = 0; i < shiniesSpecial.length; i++) {
			combinedShinies[i + shiniesDefault.length + shiniesForms.length] = shiniesSpecial[i];
		}
		Sorter<String> sort = new MergeSorter<String>();
		sort.sort(combinedShinies);
		return combinedShinies;
	}

	/**
	 * Reads the color files for whichever list type
	 * 
	 * @param type type of list
	 * @param size size of list
	 * @return array of integers signifying color values
	 */
	public int[] readColors(String type, int size) {
		int[] colors = new int[size];
		try {
			Scanner scan = new Scanner(new FileInputStream("saveData/color" + type));
			int idx = 0;
			while (scan.hasNext()) {
				colors[idx] = scan.nextInt() % 5;
				idx++;
			}
			scan.close();
		} catch (

		FileNotFoundException e) {
			System.out.println("saveData/color" + type + " could not be found");
		}
		return colors;
	}

}
