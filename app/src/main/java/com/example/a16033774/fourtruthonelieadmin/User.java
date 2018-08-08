package com.example.a16033774.fourtruthonelieadmin;

public class User {

    private int pairingid;
    private int gamekey1;
    private int user1;
    private int user2;
    private int round;

    public User(int pairingid, int gamekey1, int user1, int user2, int round) {
        this.pairingid = pairingid;
        this.gamekey1 = gamekey1;
        this.user1 = user1;
        this.user2 = user2;
        this.round = round;
    }

    public int getPairingid() {
        return pairingid;
    }

    public void setPairingid(int pairingid) {
        this.pairingid = pairingid;
    }

    public int getGamekey1() {
        return gamekey1;
    }

    public void setGamekey1(int gamekey1) {
        this.gamekey1 = gamekey1;
    }

    public int getUser1() {
        return user1;
    }

    public void setUser1(int user1) {
        this.user1 = user1;
    }

    public int getUser2() {
        return user2;
    }

    public void setUser2(int user2) {
        this.user2 = user2;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }
}
