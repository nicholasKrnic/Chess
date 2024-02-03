// Nicholas Krnic
// Mr. Morrison
// 18/1/2024
// Checked by Julian Furlin

// importing 
import java.util.*;
import java.io.*;

// main class
class Main {
  public static void main(String[] args) {

    // create scanner object
    Scanner input = new Scanner(System.in);

    // welcome and settings
    System.out.println("Welcome to Chess.");

    // asks user to see chess rules
    String seeRules = "";
    // while loop re-prompts the user if they give an invalid input
    while (true) {

      // gets yes or no answer to see chess rules
      System.out.print("See the rules? y/n: ");
      seeRules = input.nextLine();

      // if statemtent checks if input is valid
      if (seeRules.equalsIgnoreCase("y") || seeRules.equalsIgnoreCase("n")) {
        // breaks loop to move onto next question
        break;
      } else {

        // reminds user to input "y" or "n" as an answer
        System.out.println("");
        System.out.println("Please type 'y' or 'n'.");
      }
    }

    // prints chessRules.txt file
    // if statement checks if user wants to see chess rules
    if (seeRules.equalsIgnoreCase("y")) {
      System.out.println("");
      try {

        // open file and create file reader
        FileReader fr = new FileReader("chessRules.txt");
        Scanner s = new Scanner(fr);

        // output lines in chessRules.txt
        // while loop runs untill it reaches the last line in chessRules.txt
        while (s.hasNext()) {

          // create variable to hold info of current line
          String sentence = s.nextLine();
          // prints current line on the console
          System.out.println(sentence);

        }

        // catches exception
      } catch (Exception e) {

      }

    }

    // ask for highlight moves preference
    System.out.println("");

    // variable stores highlight potential moves preference
    String highlightMoves = "";

    // while loop to keep re-prompting untill user gives valid input
    while (true) {

      // gets users prefernce
      System.out.print("Highlight Moves? y/n: ");
      highlightMoves = input.nextLine();

      // if statement checks for valid input from user
      if (highlightMoves.equalsIgnoreCase("y") || highlightMoves.equalsIgnoreCase("n")) {

        // breaks loop
        break;
      } else { // else statement re-prompts user for valid input
        System.out.println("");
        System.out.println("Please type 'y' or 'n'.");
      }
    }

    // create chess piece objects

    // create grid
    // 2d array of 'piece' objects which is the chess board
    // "new piece(0)" are blank spaces
    piece[][] board = {
        { new piece('R', 2, true), new piece('K', 2, true), new piece('B', 2, true), new piece('Q', 2, true),
            new piece('G', 2, true), new piece('B', 2, true), new piece('K', 2, true), new piece('R', 2, true) },
        { new piece('P', 2, true), new piece('P', 2, true), new piece('P', 2, true), new piece('P', 2, true),
            new piece('P', 2, true), new piece('P', 2, true), new piece('P', 2, true), new piece('P', 2, true) },
        { new piece(0), new piece(0), new piece(0), new piece(0), new piece(0), new piece(0), new piece(0),
            new piece(0) },
        { new piece(0), new piece(0), new piece(0), new piece(0), new piece(0), new piece(0), new piece(0),
            new piece(0) },
        { new piece(0), new piece(0), new piece(0), new piece(0), new piece(0), new piece(0), new piece(0),
            new piece(0) },
        { new piece(0), new piece(0), new piece(0), new piece(0), new piece(0), new piece(0), new piece(0),
            new piece(0) },
        { new piece('P', 1, true), new piece('P', 1, true), new piece('P', 1, true), new piece('P', 1, true),
            new piece('P', 1, true), new piece('P', 1, true), new piece('P', 1, true), new piece('P', 1, true) },
        { new piece('R', 1, true), new piece('K', 1, true), new piece('B', 1, true), new piece('Q', 1, true),
            new piece('G', 1, true), new piece('B', 1, true), new piece('K', 1, true), new piece('R', 1, true) }
    };

    // declaring variables
    int turn = 1;
    boolean run = true;
    boolean nextTurn = true;
    // game loop
    // while loop runs untill game ends
    while (run) {

      // spacing for organization
      System.out.println("");

      // calls the print board function to display how the board currently looks on
      // screen
      printBoard(board);

      // spacing
      System.out.println("");

      // if statements check which turn it is and prints message on console
      // accordingly
      if (turn == 1) {
        System.out.println("Blue's Turn.");
      } else if (turn == 2) {
        System.out.println("Red's Turn.");
      }

      // gets input for the coordinate of the piece ("ex. A6, G1")
      System.out.print("Piece Coordinate: ");
      // variable holds piece coordinate
      String pieceCoord = input.nextLine();

      // if statement check for valid formatted coordinate by calling method
      if (!(checkFormat(pieceCoord, true))) {
        // prevents user from moving on to next input
        nextTurn = false;
        // else-if statement checks if inputted coordinate is valid (belongs to the
        // current player, is not an empty space, etc.)
      } else if (!(checkPiece(board, pieceCoord, turn))) {
        // prints error on console
        System.out.println("Error: Invalid piece coordinate");
        // prevents player from advancing to next prompt
        nextTurn = false;
        // else statement for receiving move coordinate input
      } else {

        // if statement checks for highlightmoves preference
        if (highlightMoves.equalsIgnoreCase("y")) {
          // spacing for organization
          System.out.println("");
          // calls method to print board with highlighted potential moves on console
          printMoves(board, pieceCoord, turn);
          System.out.println("");
        }

        // get move coordinate
        System.out.print("Move Coordinate: ");
        // variable holds info for move coordinate
        String moveCoord = input.nextLine();

        // if statement checks for properly formatted coordinate
        if (!(checkFormat(moveCoord, true))) {
          // prevents program from continuing untill valid coord is entered
          nextTurn = false;
        }
        // check for valid move coordinate
        else if (!(checkMove(board, pieceCoord, moveCoord, turn, true))) {
          // prints error onto console
          System.out.println("Invalid move coordinate");
          // prevents program from continuing untill valid input is given
          nextTurn = false;
          // else statement moves onto
        } else {

          // convert move coords to array coords
          int mr = 7 - ((int) (moveCoord.charAt(1)) - 49);
          int mc = (int) (moveCoord.charAt(0)) - 65;

          // convert piece coord to array coords
          int pr = 7 - ((int) (pieceCoord.charAt(1)) - 49);
          int pc = (int) (pieceCoord.charAt(0)) - 65;

          // checks for checkmate / end of game
          // if statement check if the move coordinate is onto king
          if (board[mr][mc].getSymbol() == 'G') {
            // finalize move
            board[mr][mc] = board[pr][pc];
            // replaces original piece coordinate with blank space
            board[pr][pc] = new piece(0);
            System.out.println("");
            // prints board onto console
            printBoard(board);
            // disables loop condition to end game
            run = false;

            // if statements check for which side lost and outputs messages accordingly
            if (turn == 1) {
              System.out.println("\nBlue Wins.");
            } else if (turn == 2) {
              System.out.println("\nRed Wins.");
            }
            break;
          }

          // swaps move coordinate with piece
          board[mr][mc] = board[pr][pc];
          // replaces original piece coordinate with blank space
          board[pr][pc] = new piece(0);

          // end zone conversion
          if (board[mr][mc].getSymbol() == 'P' && run) {
            // if statement checks if pawn move is to first or last row
            if (mr == 0 || mr == 7) {

              // asks user which piece to convert pawn to
              System.out.println("Choose Conversion:");
              System.out.println("1. Rook\n2. Knight\n3. Bishop\n 4. Queen");

              // create list of conversion options
              piece[] conversionChoices = { new piece('R', turn, true), new piece('K', turn, true),
                  new piece('B', turn, true), new piece('Q', turn, true) };

              // replace pawn with piece of choice
              board[mr][mc] = conversionChoices[(input.nextInt() - 1)];
              input.nextLine();
            }
          }

        }
      }

      // change turn
      // if statement lets turn change only if coordinate inputs are valid
      if (nextTurn) {
        // changes turn 1 to turn 2
        if (turn == 1) {
          turn = 2;
        } else if (turn == 2) { // changes turn 2 to turn 1
          turn = 1;
        }
      }
      nextTurn = true;

    }
  }

  // checks if requested piece is valid
  public static boolean checkPiece(piece[][] board, String pieceCoord, int turn) {

    // valid is set to initially true unless proven otherwise
    boolean valid = true;

    // convert coord to array value coord (ex. if pieceCoord = A2 then x = 0 and y =
    // 1)
    int x = 7 - ((int) (pieceCoord.charAt(1)) - 49);
    int y = (int) (pieceCoord.charAt(0)) - 65;

    // if statement checks if coord is on board (first value is between A and H,
    // second value is between 1 and 8)
    if (!((int) (pieceCoord.charAt(0)) <= (int) 'H' && (int) (pieceCoord.charAt(0)) >= (int) 'A'
        && (int) (pieceCoord.charAt(1)) <= (int) ('8') && (int) (pieceCoord.charAt(1)) >= (int) ('1'))) {
      // invalidates move if coordinate is off board and prints error
      valid = false;
      System.out.println("Error: Move is not on board.");
    }

    // if statement checks if coord is a piece and is of the opposite side
    if (!(board[x][y].getSide() == turn)) {
      System.out.println("Error: Coordinate does not contain your piece.");
      valid = false;
    }

    // returns the valid status
    return valid;
  }

  // checks if move is valid
  public static boolean checkMove(piece[][] board, String pieceCoord, String moveCoord, int turn, boolean showErrors) {

    // sets valid to true initially
    boolean valid = true;

    // A1 -> 70, H8 -> 07
    // convert move coord to array value coord
    int mr = 7 - ((int) (moveCoord.charAt(1)) - 49);
    int mc = (int) (moveCoord.charAt(0)) - 65;

    // convert piece coord to array value coord
    int pr = 7 - ((int) (pieceCoord.charAt(1)) - 49);
    int pc = (int) (pieceCoord.charAt(0)) - 65;

    // 'destCoords' array list stores every possible move a piece can make (holds
    // valid moves)
    ArrayList<String> destCoords = new ArrayList();

    // get ability-limited potential destinations for:
    // rook
    if (board[pr][pc].getSymbol() == 'R') {
      for (int count = 0; count < 8; count++) {
        // validates every coordinate with rook's x coordinate and with rook's y
        // coordinate
        destCoords.add(count + "" + pc);
        destCoords.add(pr + "" + count);
      }
    }

    // bishop
    if (board[pr][pc].getSymbol() == 'B') {
      for (int count = 1; count < 8; count++) {
        // up-right
        destCoords.add(pr - count + "" + (pc + count));
        // down-right
        destCoords.add(pr + count + "" + (pc + count));
        // down-left
        destCoords.add(pr + count + "" + (pc - count));
        // up-left
        destCoords.add(pr - count + "" + (pc - count));
      }
    }

    // knight
    // for loop runs through every possible combination of numbers from -2 to 2
    // because this is the range of a horse
    if (board[pr][pc].getSymbol() == 'K') {
      for (int count = -2; count <= 2; count++) {
        for (int count2 = -2; count2 <= 2; count2++) {
          // this if statement remove any invalid moves within the knight's range (ex.
          // moving one forward or no change in x or y position)
          if (count != 0 && count2 != 0 && Math.abs(count) != Math.abs(count2)) {
            destCoords.add((pr + count) + "" + (pc + count2));
          }
        }
      }
    }

    // queen
    // just combining the code for rook and bishop
    if (board[pr][pc].getSymbol() == 'Q') {
      // diaganol destCoords
      for (int count = 1; count < 8; count++) {
        // up-right
        destCoords.add(pr - count + "" + (pc + count));
        // down-right
        destCoords.add(pr + count + "" + (pc + count));
        // down-left
        destCoords.add(pr + count + "" + (pc - count));
        // up-left
        destCoords.add(pr - count + "" + (pc - count));
      }
      // horizontal destCoords
      for (int count = 0; count < 8; count++) {
        destCoords.add(count + "" + pc);
        destCoords.add(pr + "" + count);
      }
    }

    // king
    if (board[pr][pc].getSymbol() == 'G') {
      // for loop runs through every possible pair of numbers between -1 and 1
      for (int count = -1; count <= 1; count++) {
        for (int count2 = -1; count2 <= 1; count2++) {
          // validates every space within one unit of the kings current position
          destCoords.add((pr + count) + "" + (pc + count2));
        }
      }
    }

    // pawn
    if (board[pr][pc].getSymbol() == 'P') {
      // forward move
      // side 1
      // if statement checks for opposing piece directly above pawn
      if (turn == 1 && board[pr - 1][pc].getSide() == 0 && pr > 0) {
        destCoords.add(pr - 1 + "" + pc);
        // first move
        // if statement checks if pawn is at its starting position
        if (pr == 6 && board[pr - 2][pc].getSide() == 0) {
          destCoords.add((pr - 2) + "" + pc);
        }
      }
      // side 2
      // same explanation as side 1 but inverted
      if (turn == 2 && board[pr + 1][pc].getSide() == 0 && pr < 7) {
        destCoords.add(pr + 1 + "" + pc);
        // first move
        if (pr == 1 && board[pr + 2][pc].getSide() == 0) {
          destCoords.add((pr + 2) + "" + pc);
        }
      }

      // diaganol attacks for side one / bottom side
      if (turn == 1 && pr > 0) {
        // right attack
        // if statement checks if right attack is on the board
        if (pc < 7) {
          // checks if opposing piece on one up and to the right
          if (board[pr - 1][pc + 1].getSide() == 2) {
            destCoords.add((pr - 1) + "" + (pc + 1));
          }
        }
        // left attack
        // if statement checks if left attack is on the board
        if (pc > 0) {
          if (board[pr - 1][pc - 1].getSide() == 2 && pc > 0) {
            // validates move
            destCoords.add((pr - 1) + "" + (pc - 1));
          }
        }
      }

      // diaganol attacks for side two / top side
      if (turn == 2) {
        // right attack
        if (pc < 7) {
          // checks if the space one down and to the right has opponent piece
          if (board[pr + 1][pc + 1].getSide() == 1 && pc < 7) {
            // validates the space
            destCoords.add((pr + 1) + "" + (pc + 1));
          }
        }
        // left attack
        // makes sure left attack is on board
        if (pc > 0) {
          // checks if one down and left has opposing piece
          if (board[pr + 1][pc - 1].getSide() == 1) {
            destCoords.add((pr + 1) + "" + (pc - 1));
          }
        }
      }
    }

    // check ability-limited destinations
    // checks if the requested move has been validated by being added to destCoords
    if (!(destCoords.contains(mr + "" + mc))) {
      // invalidates move request if its not in destCoords
      valid = false;
      // prints error
      if (showErrors) {
        System.out.println("Error: Piece cannot move there.");
      }
    }

    // check destination has ally (and enemy for pawn)
    if (board[mr][mc].getSide() == turn) {
      valid = false;
      if (showErrors) {
        System.out.println("Error: Move contains ally.");
      }
    }

    // check if path is blocked for:
    // rook
    if (!(checkPath(board, pr, pc, mr, mc, showErrors))) {
      valid = false;
    }

    // check if coord is on board
    if (mr < 0 || mr > 7 || mc < 0 || mc > 7) {
      valid = false;
    }

    return valid;
  }

  // check blocked path
  public static boolean checkPath(piece[][] board, int pr, int pc, int mr, int mc, boolean showErrors) {
    boolean valid = true;

    // check paths for:
    // rook + queen
    if (board[pr][pc].getSymbol() == 'R' || (board[pr][pc].getSymbol() == 'Q' && (mr == pr || mc == pc))) {
      // if moving down
      // if statement compares the y values of the piece and its move to see which
      // direction it is moving in
      if (mr > pr) {
        // for loop runs through every move in the given direction in order
        for (int count = 1; count < 8; count++) {
          // stops the loop if the move is reached successfully to prevent programing from
          // checking irrelevant spaces
          if ((pr + count) == mr) {
            break;
            // else if checks if there is any piece occupying any of the spaces between the
            // piece and its move, invalidates move if so
          } else if (board[pr + count][pc].getSide() != 0) {
            valid = false;
            break;
          }
        }
      }
      // explanation applies to the following paths
      // if moving up
      if (mr < pr) {
        for (int count = 1; count < 8; count++) {
          if ((pr - count) == mr) {
            break;
          } else if (board[pr - count][pc].getSide() != 0) {
            valid = false;
            break;
          }
        }
      }
      // if moving right
      if (mc > pc) {
        for (int count = 1; count < 8; count++) {
          if ((pc + count) == mc) {
            break;
          } else if (board[pr][pc + count].getSide() != 0) {
            valid = false;
            break;
          }
        }
      }
      // if moving left
      if (mc < pc) {
        for (int count = 1; count < 8; count++) {
          if ((pc - count) == mc) {
            break;
          } else if (board[pr][pc - count].getSide() != 0) {
            valid = false;
            break;
          }
        }
      }
    }

    // bishop + queen
    // same idea as checking rook pathways, except if statements compare both change
    // in x and change in y coordinates to find the move direction
    if (board[pr][pc].getSymbol() == 'B' || (board[pr][pc].getSymbol() == 'Q' && (mr != pr && mc != pc))) {
      // moving up-right
      if (mr < pr && mc > pc) {
        for (int count = 1; count < 8; count++) {
          // check for destination
          if (pr - count == mr && pc + count == mc) {
            break;
          } else if ((pr - count >= 0 && pr - count < 8 && pc + count >= 0 && pc + count < 8)
              && board[pr - count][pc + count].getSide() != 0) {
            valid = false;
            break;
          }
        }
      }

      // moving down-right
      if (mr > pr && mc > pc) {
        for (int count = 1; count < 8; count++) {
          if (pr + count == mr && pc + count == mc) {
            break;
          } else if ((pr + count >= 0 && pr + count < 8 && pc + count >= 0 && pc + count < 8)
              && board[pr + count][pc + count].getSide() != 0) {
            valid = false;
            break;
          }
        }
      }

      // moving down-left
      if (mr > pr && mc < pc) {
        for (int count = 1; count < 8; count++) {
          if (pr + count == mr && pc - count == mc) {
            break;
          } else if ((pr + count >= 0 && pr + count < 8 && pc - count >= 0 && pc - count < 8)
              && board[pr + count][pc - count].getSide() != 0) {
            valid = false;
            break;
          }
        }
      }

      // moving up-left
      if (mr < pr && mc < pc) {
        for (int count = 1; count < 8; count++) {
          if (pr - count == mr && pc - count == mc) {
            break;
          } else if ((pr - count >= 0 && pr - count < 8 && pc - count >= 0 && pc - count < 8)
              && board[pr - count][pc - count].getSide() != 0) {
            valid = false;
            break;
          }
        }
      }
    }

    // if statement checks if valid is false
    if (valid == false) {
      // prints error onto console
      if (showErrors) {
        System.out.println("Error: Move is blocked by piece.");
      }
    }

    // returns the valid status of the move (whether or not its path is blocked)
    return valid;
  }

  // check for valid formatted coordinate
  public static boolean checkFormat(String coord, boolean showErrors) {
    boolean valid = true;
    // checks if coordinate is exactly 2 characters long
    if (coord.length() != 2) {
      valid = false;
    } else {
      // cehcks if first character is an uppercase letter A-H
      if (!((int) (coord.charAt(0)) > 64 && (int) (coord.charAt(0)) < 73)) {
        valid = false;
      }
      // checks if second character is number 1-8
      if (!((int) (coord.charAt(1)) > 48 && (int) (coord.charAt(1)) < 57)) {
        valid = false;
      }
    }
    if (valid == false) {
      // prints error onto console
      if (showErrors) {
        System.out.println("Error: Invalid Coordinate Format.");
      }
    }
    // returns valid status
    return valid;
  }

  // print board
  public static void printBoard(piece[][] board) {

    // create coordinate array
    String[][] coordinates = new String[8][8];

    // import values into coordinate array
    for (int count = 0; count < 8; count++) {
      for (int count2 = 0; count2 < 8; count2++) {
        coordinates[count][count2] = (char) (count2 + 65) + "" + (8 - count);
      }
    }

    // forloop runs through each row and column in board
    for (int count = 0; count < board.length; count++) {
      for (int count2 = 0; count2 < board[count].length; count2++) {

        // print vertical coordinate
        if (count2 == 0) {
          System.out.print(8 - count + " | ");
        }

        // print piece + open space
        // if statements look at each coordinate and print a black or white tile
        // depending whether the x and y values are even or odd
        if (board[count][count2].getSide() == 0) {
          // print checkered boards
          if (count % 2 == 0 && count2 % 2 == 0) {
            System.out.print("  ");
          } else if (count % 2 != 0 && count2 % 2 != 0) {
            System.out.print("  ");
          } else {
            System.out.print("⬜");
          }
        } else {
          // print vertical coordinate
          // prints piece as red if its side is 2
          if (board[count][count2].getSide() == 2) {
            System.out.print("\u001b[31m" + runSymbol(board[count][count2].getSymbol()) + "\u001B[0m" + " ");
            // prints piece as blue if its side is 1
          } else {
            System.out.print("\u001B[34m" + runSymbol(board[count][count2].getSymbol()) + "\u001B[0m" + " ");
          }
        }
      }
      System.out.println("");
    }

    // print horizontal coordinates
    System.out.print("    ");
    for (int count = 0; count < 8; count++) {
      System.out.print("--");
    }
    System.out.println("");
    System.out.print("    ");
    for (int count = 0; count < 8; count++) {
      System.out.print((char) (count + 65) + " ");
    }

    System.out.println("");

  }

  // print moves
  // explanation for this method is very similar to the printBoard method
  public static void printMoves(piece[][] board, String pieceCoord, int turn) {

    // create coordinate array
    String[][] coordinates = new String[8][8];

    // import values into coordinate array
    for (int count = 0; count < 8; count++) {
      for (int count2 = 0; count2 < 8; count2++) {
        coordinates[count][count2] = (char) (count2 + 65) + "" + (8 - count);
      }
    }

    // forloop runs through each row and column in board
    for (int count = 0; count < board.length; count++) {
      for (int count2 = 0; count2 < board[count].length; count2++) {

        // this converts the coordinates produced by the for loop (ex. 34) into the
        // format that the checkMove method needs (ex. D5) and stores it in
        // formattedCoord
        String formattedCoord = ((char) (count2 + 65)) + "" + (8 - count);

        // print vertical coordinate
        if (count2 == 0) {
          System.out.print(8 - count + " | ");
        }

        // print piece + open space
        if (board[count][count2].getSide() == 0) {
          // calls the checkMove method to see if the current coordinate is valid for the
          // given piece
          if (checkMove(board, pieceCoord, formattedCoord, turn, false)) {
            // prints a yellow square instead of a white square (represents a valid move on
            // the board)
            System.out.print("🟨");
          } else {
            // print checkered boards
            if (count % 2 == 0 && count2 % 2 == 0) {
              System.out.print("  ");
            } else if (count % 2 != 0 && count2 % 2 != 0) {
              System.out.print("  ");
            } else {
              System.out.print("⬜");
            }
          }
        } else {
          // print vertical coordinate
          // if statement calls the checkMove method to see if the current coordinate is
          // valid
          if (checkMove(board, pieceCoord, formattedCoord, turn, false)) {
            // outputs opposing pieces that are on vaid moves as yellow
            System.out.print("\u001B[33m" + runSymbol(board[count][count2].getSymbol()) + "\u001B[0m" + " ");
          } else {
            if (board[count][count2].getSide() == 2) {
              System.out.print("\u001b[31m" + runSymbol(board[count][count2].getSymbol()) + "\u001B[0m" + " ");
            } else {
              System.out.print("\u001B[34m" + runSymbol(board[count][count2].getSymbol()) + "\u001B[0m" + " ");
            }
          }
        }
      }
      System.out.println("");
    }

    // print horizontal coordinates
    System.out.print("    ");
    for (int count = 0; count < 8; count++) {
      System.out.print("--");
    }
    System.out.println("");
    System.out.print("    ");
    for (int count = 0; count < 8; count++) {
      System.out.print((char) (count + 65) + " ");
    }

    System.out.println("");

  }

  // get corresponding chess piece character
  public static char runSymbol(char symbol) {
    // returns the given character symbol as its corresponding chess piece character
    if (symbol == 'R') {
      return '♖';
    }
    if (symbol == 'K') {
      return '♘';
    }
    if (symbol == 'B') {
      return '♗';
    }
    if (symbol == 'Q') {
      return '♕';
    }
    if (symbol == 'G') {
      return '♔';
    }
    if (symbol == 'P') {
      return '♙';
    } else {
      return ' ';
    }
  }

}