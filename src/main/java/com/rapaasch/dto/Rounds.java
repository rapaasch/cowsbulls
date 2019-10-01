package com.rapaasch.dto;
import java.sql.Timestamp;
import java.util.Objects;

public class Rounds {
    int id;
    int gameId;
    String result;
    String guess;
    Timestamp timestamp;

    public Rounds(){
    }

    public Rounds(int id, int gameId, String result, String guess, Timestamp timestamp) {
        this.id = id;
        this.gameId = gameId;
        this.result = result;
        this.guess = guess;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Rounds{" +
                "id=" + id +
                ", gameId=" + gameId +
                ", result='" + result + '\'' +
                ", guess='" + guess + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getGuess() {
        return guess;
    }

    public void setGuess(String guess) {
        this.guess = guess;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rounds rounds = (Rounds) o;
        return id == rounds.id &&
                gameId == rounds.gameId &&
                Objects.equals(result, rounds.result) &&
                Objects.equals(guess, rounds.guess) &&
                Objects.equals(timestamp, rounds.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, gameId, result, guess, timestamp);
    }
}
