package com.gmail.darkfireyo;

public class Grid {

	private int[] gridArr;

	private int width;

	private int height;


	public Grid(int width, int height) {
		this.gridArr = new int[width * height];

		this.width = width;
		this.height = height;
	}

	public int getElement(int x, int y) {
		if (x >= width || y >= height || x < 0 || y < 0) {
			System.out.println("Bad Values");
			System.exit(1);
		}
		return gridArr[x + y * width];
	}

	public void setElement(int x, int y, int value) {
		gridArr[x + y * width] = value;
	}

	public boolean isValid(int x, int y, int value) {
		for (int i = 0; i < width; i++) {
			if (getElement(i, y) == value) {
				return false;
			}
		}

		for (int j = 0; j < height; j++) {
			if (getElement(x, j) == value) {
				return false;
			}
		}

		int startX = (x / 3) * 3;
		int startY = (y / 3) * 3;
		for (int i = startX; i < startX + 3; i++) {
			for (int j = startY; j < startY + 3; j++) {
				if (getElement(i, j) == value) {
					return false;
				}
			}
		}

		return true;
	}

	public int getHeight(){
		return height;
	}

	public  int getWidth(){
		return width;
	}
	
	public Grid clone() {
		Grid grid = new Grid(width,height);
		grid.copy(this);
		return grid;
	}

	public void copy(Grid grid) {
		for(int i = 0; i < width; i ++) {
			for(int j = 0 ; j < height; j++) {
				gridArr[i + j * width] = grid.getElement(i, j);
			}
		}
	}
	
	public String toString() {
		StringBuilder result = new StringBuilder();
		for (int y = 0; y < width; y++) {
	         StringBuilder ligne = new StringBuilder();
	         for (int i = 0; i < height; i++) {
	                ligne.append(this.getElement(i, y));

	                if (i < height - 1) {
	                    ligne.append(" ");
	                }
	            }

	            result.append(ligne.toString());
	            result.append(System.lineSeparator());
	        }
		return result.toString();
	}
	
}