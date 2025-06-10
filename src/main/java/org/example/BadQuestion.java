package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;

public class BadQuestion implements Serializable{
    String question;
    Date dateQuestion;

    public BadQuestion(String question, Date dateQuestion) {
        this.question = question;
        this.dateQuestion = dateQuestion;
    }

    public BadQuestion(){}

    public static ArrayList<BadQuestion> loadBadQuestions() {
        ArrayList<BadQuestion> badQuestions;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(Main.BAD_QUESTIONS_PATH))) {
            badQuestions = (ArrayList<BadQuestion>) ois.readObject();
            System.out.println("Вопросы загружены");
        } catch (FileNotFoundException e) {
            System.out.println("Файл с плохими вопросами не найден, будет создан новый");
            badQuestions = new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            badQuestions = new ArrayList<>();
        }
        return badQuestions;
    }


    public static void saveBadQuestions(ArrayList<BadQuestion> badQuestions) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(Main.BAD_QUESTIONS_PATH))) {
            oos.writeObject(badQuestions);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void printBadQuestions(ArrayList<BadQuestion> badQuestions) {
        System.out.println("Всего неотвеченных вопросов: " + badQuestions.size());

        if (!badQuestions.isEmpty()) {
            badQuestions.stream()
                    .forEach(System.out::println);
        }
    }

    @Override
    public String toString() {
        return "BadQuestion{" +
                "question='" + question + '\'' +
                ", dateQuestion=" + dateQuestion +
                '}';
    }
}
