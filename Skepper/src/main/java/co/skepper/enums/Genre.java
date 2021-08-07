package co.skepper.enums;

public enum Genre {

    POETRY(1),
    ROMANCE(2),
    DRAMA(3),
    PLAY(4),
    TERROR(5),
    SCIFI(6),
    RELIGIOUS(7),
    HISTORY(8),
    PHILOSOPHY(9);

    private int number;

    Genre(int number){
        this.number = number;
    }

    public int getNumber(){
        return number;
    }

}
