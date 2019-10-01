package com.rapaasch.dto;

import java.util.Objects;

public class Games {
    private int id;
    private boolean solved;
    private String answer;

    public Games(){
    }

    public Games(int id, boolean solved, String answer) {
        this.id = id;
        this.solved = solved;
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "Games{" +
                "id=" + id +
                ", solved=" + solved +
                ", answer='" + answer + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isSolved() {
        return solved;
    }

    public void setSolved(boolean solved) {
        this.solved = solved;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Games games = (Games) o;
        return id == games.id &&
                solved == games.solved &&
                Objects.equals(answer, games.answer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, solved, answer);
    }
}
