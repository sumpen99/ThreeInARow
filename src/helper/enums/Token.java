package helper.enums;

public enum Token {
    TOKEN_EMPTY(' '),
    TOKEN_PLAYER_ONE('O'),
    TOKEN_PLAYER_TWO('X');
    private final char chrValue;
    Token(char chrValue){this.chrValue = chrValue;}
    public char getChar(){return this.chrValue;}

}
