# Java Shape Drwaer

__Description__

The program works as follows:
 1. Create a new canvas
 2. Draw on the canvas by issuing various commands
 3. Clear canvas if needed
 4. Quit


|Command 		|Description|
|----|----|
|c w h          | Create a new canvas of width w and height h.|
|cl           	| Clear canvas and keep the existing width w and height h.|
|L x1 y1 x2 y2  | Draw a new line from (x1,y1) to (x2,y2).
|R x1 y1 x2 y2  | Draw a rectangle whose upper left corner is (x1,y1) and lower right corner is (x2,y2).|
|Q              | Quit|

__Sample Run__

Below is a sample run of the program. User input is prefixed with `enter command: `
````text
Enter command: c 10 10

     1  2  3  4  5  6  7  8  9  10 
1  | -  -  -  -  -  -  -  -  -  -  |
2  | -  -  -  -  -  -  -  -  -  -  |
3  | -  -  -  -  -  -  -  -  -  -  |
4  | -  -  -  -  -  -  -  -  -  -  |
5  | -  -  -  -  -  -  -  -  -  -  |
6  | -  -  -  -  -  -  -  -  -  -  |
7  | -  -  -  -  -  -  -  -  -  -  |
8  | -  -  -  -  -  -  -  -  -  -  |
9  | -  -  -  -  -  -  -  -  -  -  |
10 | -  -  -  -  -  -  -  -  -  -  |

Enter command: l 2 1 4 4
     1  2  3  4  5  6  7  8  9  10 
1  | -  y  -  -  -  -  -  -  -  -  |
2  | -  -  y  -  -  -  -  -  -  -  |
3  | -  -  -  y  -  -  -  -  -  -  |
4  | -  -  -  -  y  -  -  -  -  -  |
5  | -  -  -  -  -  -  -  -  -  -  |
6  | -  -  -  -  -  -  -  -  -  -  |
7  | -  -  -  -  -  -  -  -  -  -  |
8  | -  -  -  -  -  -  -  -  -  -  |
9  | -  -  -  -  -  -  -  -  -  -  |
10 | -  -  -  -  -  -  -  -  -  -  |

Enter command: r 2 6 8 10
     1  2  3  4  5  6  7  8  9  10 
1  | -  y  -  -  -  -  -  -  -  -  |
2  | -  -  y  -  -  -  -  -  -  -  |
3  | -  -  -  y  -  -  -  -  -  -  |
4  | -  -  -  -  y  -  -  -  -  -  |
5  | -  -  -  -  -  -  -  -  -  -  |
6  | -  y  y  y  y  y  y  y  -  -  |
7  | -  y  -  -  -  -  -  y  -  -  |
8  | -  y  -  -  -  -  -  y  -  -  |
9  | -  y  -  -  -  -  -  y  -  -  |
10 | -  y  y  y  y  y  y  y  -  -  |
     

enter command: Q
````

The drawing algorithm still needs work, this exercise main focus was to organise the ideas and try to use a clean code approach  

__Build__
To build the executable Jar, run the following maven command in the project root directory:
$ mvn clean install



