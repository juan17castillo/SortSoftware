package model;

/**

 * This class is the one that carries out all kinds of orderings to the generated values depending on the amount of data
 * among other variables.

 * @author: Nicolas Martinez - Cristian Molina - Juan Manuel Castillo.

 * @version: 23/08/2018 A

 * @see <a href = "http://www.instagram.com/Nicolasmr21" /> Let us know what you can help us with � Nicol�s M�rtinez </a>
 
 * @see <a href = "http://www.instagram.com/Juan17castillo" /> Let us know what you can help us with � Juan Manuel Castillo </a>

 * @see <a href = "http://www.instagram.com/CristianMolina_10" /> Let us know what you can help us with � Cristian Molina </a>

 */

import java.util.Arrays;

public class Sorter {

	public Sorter(int[] array, int roof, int floor, double percentOrder) {
		this.array = array;
		this.roof = roof;
		this.floor = floor;
		this.percentOrder = percentOrder;
	}
	
	/**
	 * This constructor will be used to manage operations that are not using attributes.
	 * like calculate floor and calculate roof.
	 * 
	 */
	
	
	public Sorter() {
	}

	private int[] array;
	private int roof;
	private int floor;
	private double percentOrder;
	
	public int[] getArray()
	{
		return array;
	}
	
	

	/**
	 * This method is responsible for selecting the most relevant method according to the amount
	 * of data, the percentage of disorder and their range.
	 */
	
	public void sort()
	{
		boolean bigNums = false;
		boolean almostOrdered = false;
		boolean bigInterval = false;
		if(floor>100)
		{
			bigNums = true;
		}
		if(percentOrder<0.1)
		{
			almostOrdered = true;
		}
		if((roof-floor)>100)
		{
			bigInterval = true;
		}
		if(bigNums&&!bigInterval)
		{
			countingsort(array);
		}
		else if(bigInterval&&!bigNums)
		{
			radixsort(array, array.length);
		}
		else if(bigInterval&&bigNums&&!almostOrdered)
		{
			quicksort(array, 0, array.length-1);
		}
		else
		{
			radixsort(array, array.length);
		}
		
	}
	
	/**
	 * This method is responsible for finding the largest number of the array
	 * @param arr[] of type array.
	 * @param n of type int.
	 * @return mx of type int.
	 */
	
	private int getMax(int arr[], int n)
	{
		int mx = arr[0];
		for (int i = 1; i < n; i++)
			if (arr[i] > mx)
				mx = arr[i];
		return mx;
	}
	
	
	/**
	 * A function to do counting sort of arr[] according to the digit represented by exp.
	 * @param arr[] of type array.
	 * @param n of type int.
	 * @param exp of type int.
	 */
	
	private void countSort(int arr[], int n, int exp)
	{
		int output[] = new int[n];
		int i;
		int count[] = new int[10];
		Arrays.fill(count,0);
		
		for (i = 0; i < n; i++)
			count[ (arr[i]/exp)%10 ]++;
 
		for (i = 1; i < 10; i++)
			count[i] += count[i - 1];
		
		for (i = n - 1; i >= 0; i--)
		{
			output[count[ (arr[i]/exp)%10 ] - 1] = arr[i];
            count[ (arr[i]/exp)%10 ]--;
		}
		
		for (i = 0; i < n; i++)
			arr[i] = output[i];
	}
	
	/**
	 * This method is responsible for ordering whole numbers by processing their digits individually.
	 * @param arr[] of type array.
	 * @param n of type int.
	 */
	
	public void radixsort(int arr[], int n)
	{
		int m = getMax(arr, n);
		for (int exp = 1; m/exp > 0; exp *= 10)
			countSort(arr, n, exp);
	}
	
	/**
	 * This algorithm is responsible for separating several times into smaller lists partially ordered
	 * until you have lists of a single element that is already in the correct position.
	 * 
	 * @param array type array that enters as a parameter for the realization of order.
	 * @param left the int type that indicates that the larger values to the pivot go to the right
	 * @param right the int type that indicates that the shorter values to the pivot go to the left
	 */
	
	public void quicksort(int array[], int left, int right) 
	{
		int pivot=array[left];
		int i=left;
		int j=right;
		int aux;
		
		while(i<j)
		{            
			while(array[i]<=pivot && i<j)
			{
				i++;
			}
			while(array[j]>pivot)
			{
				j--;         
			}
			if (i<j)
			{                      
				aux= array[i];                  
				array[i]=array[j];
				array[j]=aux;
			}
		}
		array[left]=array[j];
		array[j]=pivot;
		if(left<j-1)
		{
			quicksort(array,left,j-1);
		}
		if(j+1 <right)
		{
			quicksort(array,j+1,right);
		}
	}

	/**
	 * This method is responsible for ordering the number of ints that have different values. 
	 * Then, do some arithmetic to calculate the position of each object in the output sequence.
	 * 
	 * @param array type array that enters as a parameter for the realization of order.
	 */
	
	public void countingsort(int[] array) {
		int[] aux = new int[array.length];

		int min = array[0];
		int max = array[0];
		for (int i = 1; i < array.length; i++) {
			if (array[i] < min) {
				min = array[i];
			} else if (array[i] > max) {
				max = array[i];
			}
		}
	 
		int[] counts = new int[max - min + 1];

		for (int i = 0;  i < array.length; i++) {
			counts[array[i] - min]++;
		}
	 
		counts[0]--;
		for (int i = 1; i < counts.length; i++) {
			counts[i] = counts[i] + counts[i-1];
		}
	    
		for (int i = array.length - 1; i >= 0; i--) {
			aux[counts[array[i] - min]--] = array[i];
		}
		
		this.array = aux;
	}
	
	
	/**
	 * 	This method is responsible for calculating the smallest value of the generated array.
	 * 
	 * @param a of type array container of the generated values.
	 * @return nfloor of type int.
	 */
	
	public int calculateFloor(int[] a) {
		int nfloor = a[0];
		
		for (int i = 1; i < a.length; i++) {
			if(nfloor>a[i])
				nfloor = a[i];
		}
		
		return nfloor;
	}
	
	
	
	public int calculateRoof(int[] a) {
		int nroof = a[0];
		
		for (int i = 1; i < a.length; i++) {
			if(nroof<a[i])
				nroof = a[i];
		}
		
		return nroof;
	}
	
}
