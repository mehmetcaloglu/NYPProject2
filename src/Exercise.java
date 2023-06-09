public class Exercise {
    private int number1;
    private int number2;
    private int userAnswer;
    private int correctAnswer;

    public Exercise(int number1, int number2) {
        this.number1 = number1;
        this.number2 = number2;
        this.correctAnswer = number1 * number2;
    }

    public int getNumber1() {
        return number1;
    }

    public int getNumber2() {
        return number2;
    }

    public int getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(int userAnswer) {
        this.userAnswer = userAnswer;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public String getQuestion() {
        return number1 + " * " + number2;
    }

    @Override
    public String toString() {
        return number1 + " x " + number2;
    }
}
