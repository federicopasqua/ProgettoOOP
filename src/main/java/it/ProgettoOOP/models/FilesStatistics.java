package it.ProgettoOOP.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class FilesStatistics {
	private long min = 0;
	private long max = 0;
	private long average = 0;
	private long sum = 0;
	private int n = 0;
	
	public long getMin() {
		return min;
	}
	public long getMax() {
		return max;
	}
	public long getAverage() {
		return average;
	}
	
	@JsonIgnore
	public long getSum() {
		return sum;
	}
	
	@JsonIgnore
	public int getN() {
		return n;
	}
	
	public void add_file(FileModel file) {
		long dim = file.getDimension();
		if (dim < min || (min == 0 && n == 0)) min = dim;
		if (dim>max) max = dim;
		sum += dim;
		n++;
		average = sum/ (long) n;
	}
}
