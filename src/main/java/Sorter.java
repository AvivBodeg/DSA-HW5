import java.util.Random;

public class Sorter {
    //region QuickSort
    // Envelope method for the recursive quickSort method
    public static <T extends Comparable<T>> void quickSort(T[] array) {
        if (array == null || array.length < 2) return;
        quickSort(array, 0, array.length - 1);
    }

    // Recursively sort the array using the quickSort algorithm
    private static <T extends Comparable<T>> void quickSort(T[] array, int l, int r) {
        if (l < r) {
            int p = partition(array, l, r);
            if (l != p) quickSort(array, l, p - 1);
            if (r != p) quickSort(array, p + 1, r);
        }
    }

    private static <T extends Comparable<T>> int partition(T[] array, int l, int r) {
        // Generates random pivot index
        Random random = new Random();
        int pivotIndex = random.nextInt(r - l + 1) + l;
        // Moves Chosen element to the r of the partition and sets it as the pivot
        swap(array, pivotIndex, r);
        T pivot = array[r];

        int i = l - 1;
        for (int j = l; j < r; j++) {
            if (array[j].compareTo(pivot) <= 0) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, r);
        return i + 1;
    }

    // Swaps two elements in a Comparable<T> array
    private static <T extends Comparable<T>> void swap(T[] array, int i, int j) {
        T temp = array[j];
        array[j] = array[i];
        array[i] = temp;
    }
    //endregion

    //region MergeSort
    public static <T extends Comparable<T>> void mergeSortNoRecursion(T[] array) {
        if (array == null || array.length < 2) return;

        int n = array.length;
        int size, leftStart;

        // Go over sub arrays in size 1, 2, 4 and so on
        for (size = 1; size <= n - 1; size = 2 * size) {
            for (leftStart = 0; leftStart < n - 1; leftStart += 2 * size) {
                // Finds end of left array (mid) and start of right array (mid + 1)
                int mid = Math.min(leftStart + size - 1, n - 1);
                // Finds end of right array
                int rightEnd = Math.min(leftStart + 2 * size - 1, n - 1);
                // merges sub arrays
                merge(array, leftStart, mid, rightEnd);
            }
        }
    }

    // Merges two sorted sub arrays into a single sorted array
    static <T extends Comparable<T>> void merge(T[] array, int l, int m, int r) {
        int lLength = m - l + 1;
        int rLength = r - m;
        // Create temporary arrays
        T[] lArray = (T[]) new Comparable[lLength];
        T[] rArray = (T[]) new Comparable[rLength];
        // Initialize the temporary arrays with values from original array
        for (int i = 0; i < lLength; i++) {
            lArray[i] = array[l + i];
        }
        for (int j = 0; j < rLength; j++)
            rArray[j] = array[m + 1 + j];

        int lIndex = 0, rIndex = 0; // indices of current element in lArray and rArray respectively
        int mergeIndex = l; // index of current cell in te original array to receive an element

        /* Merge temporary arrays into the original array until all the
        elements in one of the temporary arrays have been moved to the original */
        while (lIndex < lLength && rIndex < rLength) {
            if (lArray[lIndex].compareTo(rArray[rIndex]) <= 0) {
                array[mergeIndex] = lArray[lIndex];
                lIndex++;
            } else {
                array[mergeIndex] = rArray[rIndex];
                rIndex++;
            }
            mergeIndex++;
        }

        // Copy remaining elements of lArray[] if any are left
        while (lIndex < lLength) {
            array[mergeIndex] = lArray[lIndex];
            lIndex++;
            mergeIndex++;
        }
        // Copy remaining elements of rArray[] if any are left
        while (rIndex < rLength) {
            array[mergeIndex] = rArray[rIndex];
            rIndex++;
            mergeIndex++;
        }
    }
    //endregion

    //region RadixSort
    public static void radixSort(Long[] array, int bitsPerDigit) {

        Long maxDigit = 0L;
        // Finds biggest number
        for (int i = 0; i < array.length; i++) {
            if (array[i] > maxDigit) {
                maxDigit = array[i];
            }
        }

        // Calculates maximum length of an element
        int digits = maxDigit.toString().length();
        // Sorts
        for (int i = 0; i <= digits; i++) {
            countingSort(array, bitsPerDigit, i);
        }

    }

    // Stable counting sort
    private static void countingSort(Long[] arr, int bitsPerDigit, int digitIndex) {
        Long[] sortedArray = new Long[arr.length];
        int countSize = (1 << bitsPerDigit);
        int[] countArray = new int[countSize];

        // Set up countArray
        for (int i = 0; i < arr.length; i++) {
            countArray[extractDigit(arr[i], bitsPerDigit, digitIndex)]++;
        }

        // Cumulative count
        for (int i = 1; i < countSize; i++) {
            countArray[i] += countArray[i - 1];
        }

        // Create a temporary array that's sorted according to the current digit
        for (int i = arr.length - 1; i >= 0; i--) {
            int digit = extractDigit(arr[i], bitsPerDigit, digitIndex);
            sortedArray[countArray[digit] - 1] = arr[i];
            countArray[digit]--;
        }
        // Copy sorted array into the original array
        for (int i = 0; i < arr.length; i++) {
            arr[i] = sortedArray[i];
        }

    }

    private static int extractDigit(Long key, int bitsPerDigit, int digitIndex) {
        int bitMask = (1 << bitsPerDigit) - 1;
        int shiftedKey = (int) (key >> (digitIndex*bitsPerDigit));
        return (int) (shiftedKey & bitMask);
    }
    //endregion
}
