import java.io.*; 
import java.util.*; 

public class Maze{

   public static int numberOfMonsters = 0;
   
   public static String addMonsters = "";

   public static int numberOfMoves = 0;
 
   public static int row = 1; 
   
   public static int col = 1;
   
   public static String[] history = new String[100000];
   
   public static Random generator = new Random();  

   public static void main(String[] args) throws IOException {
            
      char[][] maze = new char[7][31];
            
      String whichMaze;
   
      String direction; 
                  
      Scanner keyboard = new Scanner(System.in); 
      
      System.out.println("Welcome to the maze, prepare for awesome!");
      
      System.out.println(); 
      
      whichMaze = args[0];
      
      if(args.length > 1)
         addMonsters = args[1];
      
      if(args.length > 2)
         numberOfMonsters = Integer.parseInt(args[2]);
            
      if (whichMaze.toLowerCase().equals("preset"))
         presetMaze(maze);
         
      else 
         readFile(maze, whichMaze);  
            
      row = generator.nextInt(6) + 1; 
      col = generator.nextInt(30) + 1; 
                         
      while (maze[row][col] != ' ')  { 
         row = generator.nextInt(6) + 1; 
         col = generator.nextInt(30) + 1; 
      } 
     
      maze[row][col] = 'p';
      
      int startRow = row;
      
      int startCol = col;
      
      if (addMonsters.equals("addMonsters")) {
         for (int i = 1; i <= numberOfMonsters; i++) {
            while (maze[row][col] != ' ')  { 
               row = generator.nextInt(6) + 1; 
               col = generator.nextInt(30) + 1; 
            }
         
            maze[row][col] = 'm';
         }
      }
      
      row = startRow;
      
      col = startCol;      
   
      System.out.println();
      
      for (int i = 0; i < 7; i ++) {
         
         for (int j = 0; j < 31; j++){
            
            System.out.print(maze[i][j]);
                               
         } 
         System.out.println(); 
      } 
         
      System.out.println(); 
   
      while (maze[row][col] != 'f') {
         System.out.println("Enter a direction");
         
         System.out.println();
         
         if (maze[row][col - 1] != 'x') {
            System.out.println("left is open");
            System.out.println();
         }
      
         if (maze[row][col + 1] != 'x') {
            System.out.println("right is open");
            System.out.println();
         }
         if (maze[row - 1][col] != 'x') {
            System.out.println("up is open");
            System.out.println();
         }
         if (maze[row + 1][col] != 'x') {
            System.out.println("down is open");
            System.out.println();
         }
         direction=keyboard.nextLine();
         direction.toLowerCase();
        
         maze = moveAround(direction, maze);
      
      
         System.out.println();
      
         for (int i = 0; i < 7; i ++) {
         
            for (int j = 0; j < 31; j++){
            
               System.out.print(maze[i][j]);
                               
            } 
            System.out.println(); 
         }
          
         System.out.println();
             
      }          
      System.out.println();       
   }

   public static void presetMaze(char[][] maze) throws IOException {
   
      String fileName = "Maze.txt";     
      String line;   
      
      File charactersFile = new File(fileName);  
            
      Scanner inputFile = new Scanner(charactersFile); 
      
      for (int row = 0; row < maze.length; row ++) {
      
         if (inputFile.hasNext()) {
            line = inputFile.nextLine();
            
            for (int col = 0; col < maze[row].length; col++) 
               maze[row][col] = line.charAt(col);  
         } 
      }
                 
      inputFile.close();  
         
   } 
   
   public static void readFile(char[][] maze, String fileName) throws IOException {
   
      Scanner keyboard = new Scanner(System.in);
      
      String line;    
      
      File charactersFile = new File(fileName); 
            
      while (!charactersFile.exists()) {
         System.out.println();
         System.out.println("Invalid file name. " + 
            "Enter a new file name + .txt or type 'preset'");
                     
         fileName = keyboard.nextLine();
         
         charactersFile = new File(fileName);  
      } 
         
      Scanner inputFile = new Scanner(charactersFile);
     
      for (int row = 0; row < maze.length; row ++) {
      
         if (inputFile.hasNext()) {
            line = inputFile.nextLine();
            
            for (int col = 0; col < maze[row].length; col++) 
               maze[row][col] = line.charAt(col); 
         }       
      }       
      inputFile.close();          
   } 
        
   public static char[][] moveAround(String direction, char[][] maze) throws IOException {
   
      char endGame = 'f';
      
      switch (direction) {
         case ("left") :
            if (maze[row][col - 1] != 'x') {
               if (maze[row][col - 1] == endGame) {
                  System.out.println();
                  System.out.println("YOU WIN!");
                  System.out.println();
                  System.out.println("It took you " + numberOfMoves + " turns.");
                  System.exit(0);
               }
               
               else if (maze[row][col - 1] == 'm') {
                  hitMonster(maze);
               }
               
               else {   
                  maze[row][col] = ' ';
                  col--;
                  maze[row][col] = 'p';
                  history[numberOfMoves] = "left";
                  numberOfMoves++;
               }
            }
            else { 
               System.out.println();
               System.out.println("Invalid Movement");
            }
         
            break;
         
         case ("right") :
            if (maze[row][col + 1] != 'x') {
               if (maze[row][col + 1] == endGame) {
                  System.out.println();
                  System.out.println("YOU WIN!");
                  System.out.println();
                  System.out.println("It took you " + numberOfMoves + " turns.");
                  System.exit(0);
               }
               
               else if (maze[row][col + 1] == 'm') {
                  hitMonster(maze);
               }
               
               else {
                  maze[row][col] = ' ';
                  col++;
                  maze[row][col] = 'p';
                  history[numberOfMoves] = "right";
                  numberOfMoves++;
               }
            }
            else {
               System.out.println();
               System.out.println("Invalid Movement");
            }
         
            break;
       
         case ("up") :
            if (maze[row - 1][col] != 'x') {
               if (maze[row - 1][col] == endGame) {
                  System.out.println();
                  System.out.println("YOU WIN!");
                  System.out.println();
                  System.out.println("It took you " + numberOfMoves + " turns.");
                  System.exit(0);
               }
               
               else if (maze[row - 1][col] == 'm') {
                  hitMonster(maze);
               }
               
               else {
                  maze[row][col] = ' ';
                  row--;
                  maze[row][col] = 'p';
                  history[numberOfMoves] = "up";
                  numberOfMoves++;   
               }
            }
            else {
               System.out.println();
               System.out.println("Invalid Movement");
            }
         
            break;
        
         case ("down") :
            if (maze[row + 1][col] != 'x') {
               if (maze[row + 1][col] == endGame) {
                  System.out.println();
                  System.out.println("YOU WIN!");
                  System.out.println();
                  System.out.println("It took you " + numberOfMoves + " turns.");
                  System.exit(0);
               }
               
               else if (maze[row + 1][col] == 'm') {
                  hitMonster(maze);
               }
               
               else {
                  maze[row][col] = ' ';
                  row++;
                  maze[row][col] = 'p';
                  history[numberOfMoves] = "down";
                  numberOfMoves++;   
               }
            }
            else {
               System.out.println(); 
               System.out.println("Invalid Movement");
            }
         
            break;
            
         case ("history") :
            if (numberOfMoves == 0) { 
               System.out.println();
               System.out.println("No History Yet");
            }
            else {
               System.out.println();
               for (int i = 0; i < numberOfMoves; i++) {
                  System.out.print(history[i] + ", ");
               }
               System.out.println();
            }
            
            break;
                     
         default :
            System.out.println(); 
            System.out.println("Invalid data");
      
      }
      
               
      return(maze);
   }
   
   public static void hitMonster(char[][] maze) throws IOException {
   
      System.out.println();
   
      System.out.println("You hit a monster. You run . . . five random spaces.)");
      
      maze[row][col] = ' ';
      
      int randomMove = generator.nextInt(3) + 1;
      
      String direction = "";
      
      for (int i = 1; i <= 5; i++) {
         switch (randomMove) {
         
            case (1):
               direction = "left";
               maze = moveAround(direction, maze);
               break;
            
            case (2):
               direction = "right";
               maze = moveAround(direction, maze);
               break;
           
            case (3):
               direction = "up";
               maze = moveAround(direction, maze);
               break;
           
            case (4):
               direction = "down";
               maze = moveAround(direction, maze);
               break;
         }
      }              
   }

}