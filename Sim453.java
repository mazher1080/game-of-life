/******************************************************************************
 *  A Game of Life simulation
 *
 *  Compilation:  javac Sim453.java
 *  Execution:    java Sim453 n P
 * 
 *  n = Number of iterations to run the program, n >= 0
 *  P = Inital grid pattern, one of three possible values: {B, C, R}
 *          B : By Flop Oscillator Pattern
 *          C : Crab Glider Pattern
 *          R : Random (50% chance of each cell being dead or alive)
 * 
 *  Example:
 *  java Sim453 100 B 
 *  Simulates 100 iterations with the By Flop pattern as the inital grid layout
 * 
 *  Note: This program makes use of the Picture.java file provided
 *  in RESOURCES > Cellular Automata > Code and Demos > PictureDemo
 * 
 *
 ******************************************************************************/

import java.awt.Color;

public class Sim453 {
    private static boolean[][] cells;
    private static boolean[][] old;
    private Picture pic;
    private final int pixelWidth = 5; // Used to magnify the cell size for easy viewing
    private static final int gridLen = 140; // 140x140 grid size

    public Sim453(int width, int height) {
        cells = new boolean[width][height];
        old = new boolean[width][height];
        pic = new Picture(width*pixelWidth, height*pixelWidth);
    }
    
    private void drawGrid(int i, int j) {
        for (int offsetX = 0; offsetX < pixelWidth; offsetX++) {
            for (int offsetY = 0; offsetY < pixelWidth; offsetY++) {
                if (cells[i][j] == true) {
                    pic.set((i*pixelWidth)+offsetX, (j*pixelWidth)+offsetY, Color.BLACK);
                } else {
                    pic.set((i*pixelWidth)+offsetX, (j*pixelWidth)+offsetY, Color.WHITE);
                }
            }     
        }
    }

    private void updateGrid() {
        for (int i = 0; i < gridLen; i++) {
            for (int j = 0; j < gridLen; j++) {
                int liveNeighbours = getAliveNeighbours(i, j);
                if (liveNeighbours < 2 && cells[i][j] == true) {
                    old[i][j] = false;
                } else if ((liveNeighbours == 2 || liveNeighbours == 3) && cells[i][j] == true) {
                    old[i][j] = true;
                } else if (liveNeighbours == 3 && cells[i][j] == false) {
                    old[i][j] = true;
                } else if (liveNeighbours > 3 && cells[i][j] == true) {
                    old[i][j] = false;
                }
            }
        }

        for (int i = 0; i < gridLen; i++) {
            for (int j = 0; j < gridLen; j++) {
                cells[i][j] = old[i][j];
            }
        }

    }

    private int getAliveNeighbours(int i, int j) {
        int numLiveCells = 0;

        // Iterate over all neighbourhood cells
        for (int m = -1; m < 2; m++) {
            for (int n = -1; n < 2; n++) {
                // DO NOT COUNT THE CELL ITSELF AS A NEIGHBOUR
                if (!(m == 0 && n == 0)) {
                    int xPos = (m + i + gridLen) % gridLen;
                    int yPos = (n + j + gridLen) % gridLen;
                    if (cells[xPos][yPos] == true) {
                        numLiveCells++;
                    }
                }
            }
        }
        return numLiveCells;
    }

    public static void main(String[] args) {
        
        // Obtain input arguments from command line
        int n = Integer.parseInt(args[0]);
        String pattern = args[1];

        Sim453 sim = new Sim453(gridLen, gridLen);

        // Create initial grid layout based on pattern (crab, by flop, random)
        if (pattern.contentEquals("B")) {
            cells[4][1] = true;
            cells[2][2] = true;
            cells[4][2] = true;
            cells[6][3] = true;
            cells[1][4] = true;
            cells[2][4] = true;
            cells[3][4] = true;
            cells[4][4] = true;
            cells[5][4] = true;
            cells[6][5] = true;
            cells[2][6] = true;
            cells[4][6] = true;
            cells[4][7] = true;
        }

        else if (pattern.contentEquals("C")) {
            cells[9][1] = true;
            cells[10][1] = true;
            cells[8][2] = true;
            cells[9][2] = true;
            cells[10][3] = true;
            cells[12][4] = true;
            cells[13][4] = true;
            cells[11][5] = true;
            cells[10][7] = true;
            cells[13][7] = true;
            cells[2][8] = true;
            cells[3][8] = true;
            cells[9][8] = true;
            cells[10][8] = true;
            cells[1][9] = true;
            cells[2][9] = true;
            cells[8][9] = true;
            cells[3][10] = true;
            cells[8][10] = true;
            cells[10][10] = true;
            cells[5][11] = true;
            cells[6][11] = true;
            cells[9][11] = true;
            cells[5][12] = true;
            cells[6][12] = true;
        }

        else if (pattern.contentEquals("R")) {
            for (int i = 0; i < gridLen; i++) {
                for (int j = 0; j < gridLen; j++) {
                    cells[i][j] = Math.random() < 0.5;
                }     
            }
        }

        // Run simulation for n iterations
        for (int m = 0; m < n; m++) {

            // Draw inital grid layout
            for (int i = 0; i < gridLen; i++) {
                for (int j = 0; j < gridLen; j++) {
                    sim.drawGrid(i, j);
                }
            }
            sim.pic.show();

            // Save current grid state
            for (int k = 0; k < gridLen; k++) {
                for (int l = 0; l < gridLen; l++) {
                    old[k][l] = cells[k][l];
                }
            }

            sim.updateGrid();

            // Delay execution to show grid animations
            try {
				Thread.sleep(120);
			} catch (InterruptedException e) {
				// Print error
				e.printStackTrace();
			}
        }

    }
}
