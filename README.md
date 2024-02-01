# Game of Life
A Java program that simulates Conway's Game of Life

<h2>Installation</h2>
This program requires the Java Development Kit (JDK) for compilation and execution of the program.

The JDK can be downloaded from Oracle's website: https://www.oracle.com/java/technologies/downloads/


<h2>Compilation</h2>
In terminal: <code>javac Sim453.java</code>

<h2>Execution</h2>
In terminal: <code>java Sim453 n P</code>
<p>
n : Number of iterations to run the program, n >= 0 <br/>
P : Inital grid pattern, one of three possible values: {B, C, R} <br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;B : By Flop Oscillator Pattern <br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;C : Crab Glider Pattern <br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;R : Random (50% chance of each cell being dead or alive) <br/>
          
Example:
  <code>java Sim453 100 B</code><br/>
  Simulates 100 iterations with the By Flop pattern as the inital grid layout
</p>
