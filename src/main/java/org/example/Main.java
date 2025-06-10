package org.example;

import java.util.*;

public class Main {
    public static final String QUESTIONS_PATH = "questions.ser";
    public static final String BAD_QUESTIONS_PATH = "bad_questions.ser";
    private static ArrayList<QuestionResponse> questionResponses;
    private static ArrayList<BadQuestion> badQuestions;

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        questionResponses = QuestionResponse.loadQuestions();
        badQuestions = BadQuestion.loadBadQuestions();
        System.out.println("| Привет, я Говорилка2000, я умею отвечать на различные вопросы(но не на все на свете:[)\n"+
                "| Системные команды:\n" +
                "| выход - сохранить неотвеченные вопросы и закрыть программу\n" +
                "| плохие вопросы - вопросы, на которые не удалось ответить");

        if (questionResponses == null) {
            System.out.println("Файл с вопросами повреждён или пуст");
            return;
        }
        while (true) {
            System.out.print("Введите текст: ");
            String userInput = sc.nextLine().trim();

            if (userInput.equals("плохие вопросы")) {
                BadQuestion.printBadQuestions(badQuestions);
                continue;
            }

            if (userInput.equals("выход")) {
                System.out.println("Говорилка2000: До свидания");
                break;
            }

            if (userInput.isEmpty()) {
                System.out.println("Говорилка2000: Введите вопрос.");
                continue;
            }
            
            Optional<String> answer = findAnswer(userInput);

            if (answer.isPresent()) {
                System.out.println("Говорилка2000: " + answer.get());
            } else {
                System.out.println("Говорилка2000: Извините, я не знаю ответа на этот вопрос.");
                badQuestions.add(new BadQuestion( userInput, new Date()));
            }

            System.out.println();
        }

        BadQuestion.saveBadQuestions(badQuestions);
    }

    private static Optional<String> findAnswer(String userQuestion) {
        return questionResponses.stream()
                .filter(qr -> userQuestion.toLowerCase().contains(qr.question.toLowerCase()) ||
                        qr.question.toLowerCase().contains(userQuestion.toLowerCase())) // Или в вопросе юзера есть один из вопросов системы, или наоборот
                .map(qr -> qr.response)
                .findFirst();
    }
}