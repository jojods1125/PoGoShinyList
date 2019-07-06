/**
 * 
 */
package shinyListMaker;

import java.util.Comparator;

/**
 * Abstract class that compares pieces of data and sorts them
 * 
 * @author Joseph Dasilva
 * @param <E> data being compared
 */
public abstract class AbstractComparisonSorter<E extends Comparable<E>> implements Sorter<E> {

	/** The comparator type being used */
	private Comparator<E> comparator;

	/**
	 * Constructs the AbstractComparisonSorter
	 * 
	 * @param comparator the comparator type
	 */
	public AbstractComparisonSorter(Comparator<E> comparator) {
		setComparator(comparator);
	}

	private void setComparator(Comparator<E> comparator) {
		if (comparator == null) {
			comparator = new NaturalOrder();
		}
		this.comparator = comparator;
	}

	/**
	 * The default comparator type
	 * 
	 * @author Joseph Dasilva
	 */
	private class NaturalOrder implements Comparator<E> {
		public int compare(E first, E second) {
			return ((Comparable<E>) first).compareTo(second);
		}
	}

	/**
	 * Compares two pieces of data
	 * 
	 * @param data1 first data compared
	 * @param data2 second data compared
	 * @return 1 if data1 greater than data2, -1 if data2 greater than data1, 0 if
	 *         equal
	 */
	public int compare(E data1, E data2) {
		return comparator.compare(data1, data2);
	}
}