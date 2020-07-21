package sortcomparison;

import java.util.*;

public class BasicSorter implements Sorter {

	@Override
	public void insertionSort(ArrayList<String> data, int firstIndex, int numberToSort) {

		for (int i = 1; i < numberToSort; i++) {
			for (int j = 0; j < i; j++) {
				if (data.get((firstIndex) + i).compareTo(data.get(firstIndex + j)) < 0) {
					String x = data.get(firstIndex + i);
					String y = data.get(firstIndex + j);
					data.set(firstIndex + j, x);
					data.set(firstIndex + i, y);

				}
			}
		}
	}

	@Override
	public void quickSort(ArrayList<String> data, int firstIndex, int numberToSort) {
		if (firstIndex < (firstIndex + numberToSort - 1)) {
			if (numberToSort >= 16) {
				int pivot = partition(data, firstIndex, numberToSort);
				int quickSprtv1EndParam = (pivot - firstIndex);
				quickSort(data, firstIndex, quickSprtv1EndParam);
				int quickSortv2EndParam = (numberToSort - (pivot - firstIndex) - 1);
				int quickSortv2BeginParam = pivot + 1;
				quickSort(data, quickSortv2BeginParam, quickSortv2EndParam);
			} else {
				insertionSort(data, firstIndex, numberToSort);
			}
		}
	}

	/**
	 * Partitions part (or all) of the list. The range of indices included in the
	 * partitioning is [firstIndex, firstIndex + numberToPartition -1].
	 * 
	 * @param data
	 * @param firstIndex
	 * @param numberToPartition
	 * @return The index, relative to data[0], where the pivot value is located at
	 *         the end of this partitioning.
	 */
	@Override
	public int partition(ArrayList<String> data, int firstIndex, int numberToPartition) {
		int indexMidddle = firstIndex + numberToPartition / 2;
		Collections.swap(data, firstIndex, indexMidddle);
		String pivot = data.get(firstIndex);
		int tooBigNdx = firstIndex + 1;
		int tooSmallNdx = firstIndex + numberToPartition - 1;

		while (tooBigNdx < tooSmallNdx) {

			while (tooBigNdx < tooSmallNdx && data.get(tooBigNdx).compareTo(pivot) <= 0) {
				tooBigNdx++;
			}

			while (tooSmallNdx > firstIndex && data.get(tooSmallNdx).compareTo(pivot) > 0) {
				tooSmallNdx--;
			}
			if (tooBigNdx < tooSmallNdx) {
				Collections.swap(data, tooSmallNdx, tooBigNdx);

			}
		}
		if (pivot.compareTo(data.get(tooSmallNdx)) < 0) {

			return firstIndex;
		} else {
			Collections.swap(data, firstIndex, tooSmallNdx);
			return tooSmallNdx;
		}

		// String pivot = data.get(ndx);

		// TODO Auto-generated method stub

	}

	@Override

	public void mergeSort(ArrayList<String> data, int firstIndex, int numberToSort) {
		if (numberToSort < 16) {
			insertionSort(data, firstIndex, numberToSort);

		} else {
			int centerPiece = (numberToSort + firstIndex + firstIndex) / 2;
			int leftSize = centerPiece - firstIndex;
			int rightSize = numberToSort - leftSize;
			mergeSort(data, firstIndex, leftSize);
			mergeSort(data, centerPiece, rightSize);
			merge(data, firstIndex, leftSize, rightSize);
		}
	}

	@Override
	public void merge(ArrayList<String> data, int firstIndex, int leftSegmentSize, int rightSegmentSize) {

		ArrayList<String> temp = new ArrayList<String>();

		int leftIndex = firstIndex;
		int rightIndex = (firstIndex + leftSegmentSize);

		int ndx;

		int sizeLeft = leftSegmentSize;
		int sizeRight = rightSegmentSize;

		for (int x = 0; (sizeLeft > 0) || (sizeRight > 0); x++) {
			if ((sizeLeft > 0) && (sizeRight > 0)) {

				String leftSect = data.get(leftIndex);
				String rightSect = data.get(rightIndex);

				if (leftSect.compareTo(rightSect) > 0) {
					temp.add(data.get(rightIndex++));
					sizeRight = sizeRight - 1;

				} else {
					temp.add(data.get(leftIndex++));
					sizeLeft = sizeLeft - 1;

				}
			} else {
				if (sizeLeft >= 0) {
					sizeRight = sizeRight - 1;
					temp.add(data.get(rightIndex++));

				} else {
					sizeLeft = sizeLeft - 1;
					temp.add(data.get(leftIndex++));

				}
			}
		}

		ndx = firstIndex;
		int iterator = 0;
		while (iterator < temp.size()) {

			data.set(ndx++, temp.get(iterator));
			iterator++;

		}
	}

	@Override
	public void heapSort(ArrayList<String> data) {
		// TODO Auto-generated method stub

	}

	@Override
	public void heapify(ArrayList<String> data) {
		// TODO Auto-generated method stub

	}

}
