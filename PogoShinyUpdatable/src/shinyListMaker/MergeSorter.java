package shinyListMaker;

import java.util.Comparator;

/**
 * Sorts a data set with Merge Sort algorithm
 * 
 * @author Joseph Dasilva
 * @param <E> type of data sorted
 */
public class MergeSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {

	/**
	 * Constructs MergeSorter
	 * 
	 * @param comparator type of comparator used
	 */
	public MergeSorter(Comparator<E> comparator) {
		super(comparator);
	}

	/**
	 * Constructs MergeSorter with default comparator
	 */
	public MergeSorter() {
		super(null);
	}

	/**
	 * Sorts the data with Merge Sort algorithm
	 * 
	 * @param data the set of data sorted
	 */
	public void sort(E[] data) {
		mergeSort(data);
	}

	/**
	 * Uses recursive calls to break an array in half for sorting
	 * 
	 * @param data the array being sorted
	 * @return the sorted array
	 */
	public E[] mergeSort(E[] data) {
		if (data.length < 2) {
			return data;
		}

		int mid = data.length / 2;
		E[] left = copyArray(data, 0, mid - 1);
		E[] right = copyArray(data, mid, data.length - 1);

		left = mergeSort(left);
		right = mergeSort(right);

		merge(left, right, data);

		return data;
	}

	/**
	 * Copies a section of an array to a new array
	 * 
	 * @param data  the array having part of copied
	 * @param start the index the copy is starting at
	 * @param end   the index the copy is ending at
	 * @return the copied array section
	 */
	public E[] copyArray(E[] data, int start, int end) {
		@SuppressWarnings("unchecked")
		E[] data2 = (E[]) (new Comparable[end - start + 1]);
		for (int i = 0; i <= end - start; i++) {
			data2[i] = data[i + start];
		}
		return data2;
	}

	/**
	 * Merges two arrays into one
	 * 
	 * @param left  left array being merged
	 * @param right right array being merged
	 * @param data  finished merged array
	 */
	public void merge(E[] left, E[] right, E[] data) {
		int leftIndex = 0;
		int rightIndex = 0;
		while (leftIndex + rightIndex < data.length) {
			if (rightIndex == right.length
					|| (leftIndex < left.length && compare(left[leftIndex], right[rightIndex]) < 0)) {
				data[leftIndex + rightIndex] = left[leftIndex];
				leftIndex = leftIndex + 1;
			} else {
				data[leftIndex + rightIndex] = right[rightIndex];
				rightIndex = rightIndex + 1;
			}
		}
	}
}