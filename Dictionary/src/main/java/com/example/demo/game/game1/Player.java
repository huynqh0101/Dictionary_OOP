package com.example.demo.game.game1;

public class Player {
    private String chooseAnswer;
    private boolean checkCorrect;
    private boolean checkSubmit;

    public Player(String chooseAnswer, boolean checkCorrect, boolean checkSubmit) {
        this.chooseAnswer = chooseAnswer;
        this.checkCorrect = checkCorrect;
        this.checkSubmit = checkSubmit;
    }

    public String getChooseAnswer() {
        return chooseAnswer;
    }

    public void setChooseAnswer(String chooseAnswer) {
        this.chooseAnswer = chooseAnswer;
    }

    public boolean isCheckCorrect() {
        return checkCorrect;
    }

    public void setCheckCorrect(boolean checkCorrect) {
        this.checkCorrect = checkCorrect;
    }

    public boolean isCheckSubmit() {
        return checkSubmit;
    }

    public void setCheckSubmit(boolean checkSubmit) {
        this.checkSubmit = checkSubmit;
    }
}
