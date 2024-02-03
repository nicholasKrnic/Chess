public class piece {

  // declare variables
  char symbol;
  int side;
  boolean status;

  public piece(char symbol, int side, boolean status) {
    this.symbol = symbol;
    this.side = side;
    this.status = status;
  }

  // this is for blank spaces on the board (piece(0))
  public piece(int side) {
    this.side = side;
  }

  // getters
  public char getSymbol() {
    return this.symbol;
  }

  public int getSide() {
    return this.side;
  }

  public boolean getStatus() {
    return this.status;
  }

}