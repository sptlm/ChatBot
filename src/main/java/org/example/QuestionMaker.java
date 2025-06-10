package org.example;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class QuestionMaker {
    public static void main(String[] args) {

        ArrayList<QuestionResponse> questionResponses =new ArrayList<QuestionResponse>();
        questionResponses.add(new QuestionResponse("погода", "Погода теплая, одевайтесь полегче"));
        questionResponses.add(new QuestionResponse("как дела", "У меня все хорошо! А у вас?"));
        questionResponses.add(new QuestionResponse("умеешь", "Я умею отвечать на вопросы."));
        questionResponses.add(new QuestionResponse("сколько время", System.currentTimeMillis() + " миллисекунд начиная с 1 января 1970 года XD"));
        questionResponses.add(new QuestionResponse("как тебя зовут", "Меня зовут Говорилка2000"));
        questionResponses.add(new QuestionResponse("что такое итис", "Это Институт информационных технологий и интеллектуальных систем КФУ"));
        questionResponses.add(new QuestionResponse("что такое Java", "Java - это объектно-ориентированный ЯП."));
        questionResponses.add(new QuestionResponse("где ты живешь", "Я нахожусь на этом компьютере где-то в недрах жесткого диска."));
        questionResponses.add(new QuestionResponse("что ты ешь", "электричество и строками кода!"));


        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(Main.QUESTIONS_PATH))) {
            oos.writeObject(questionResponses);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Файл с вопросами создан.");
    }

}

