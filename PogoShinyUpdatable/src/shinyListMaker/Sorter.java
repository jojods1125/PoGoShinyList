package shinyListMaker;

/**
 * Interface that contains the sorting method for each algorithm
 * 
 * @author Joseph Dasilva
 * @param <E> the data type being sorted
 */
public interface Sorter<E> {

	/**
	 * Sorts the data using an algorithm
	 * 
	 * @param data the data being sorted
	 */
	void sort(E[] data);
}
