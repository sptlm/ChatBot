package org.example;

import java.io.*;
import java.util.ArrayList;

public class QuestionResponse implements Serializable{
    String question;
    String response;

    public QuestionResponse(String question, String response) {
        this.question = question;
        this.response = response;
    }

    public QuestionResponse(){}


    public static ArrayList<QuestionResponse> loadQuestions(){
        try(
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(Main.QUESTIONS_PATH))
        ) {

            return (ArrayList<QuestionResponse>) ois.readObject();

        } catch (FileNotFoundException e) {
            System.out.println("Файл с вопросами не найден, нужно создать его с помощью QuestionMaker");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    private void saveQuestionResponses(ArrayList<QuestionResponse> questionResponses) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(Main.QUESTIONS_PATH))) {
            oos.writeObject(questionResponses);
            System.out.println("Вопросы сохранены в файл.");
        } catch (IOException e) {
            System.err.println("Ошибка при сохранении вопросов: " + e.getMessage());
        }
    }


}
