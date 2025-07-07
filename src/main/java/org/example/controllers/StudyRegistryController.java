package org.example.controllers;

import org.example.studyregistry.StudyObjective;
import org.example.studyregistry.StudyTaskManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class StudyRegistryController {
    private final StudyTaskManager studyTaskManager = StudyTaskManager.getStudyTaskManager();
    private final Map<String, Runnable> actions = new HashMap<>();

    public StudyRegistryController() {
        assignActions();
    }

    private void assignActions() {
        actions.put("4", this::handleAddStudyObjective);
        // Outras ações podem ser adicionadas aqui
    }

    private <T> T readInput(String prompt, Function<String, T> parser) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = MainController.getInput();
                return parser.apply(input);
            } catch (Exception e) {
                System.out.println("Entrada inválida, tente novamente.");
            }
        }
    }

    private void handleAddStudyObjective() {
        System.out.println("Criando StudyObjective...");

        int id = readInput("id (Integer): ", Integer::parseInt);
        int priority = readInput("priority (Integer): ", Integer::parseInt);
        int practicedDays = readInput("practicedDays (Integer): ", Integer::parseInt);
        int day = readInput("day (int): ", Integer::parseInt);
        int month = readInput("month (int): ", Integer::parseInt);
        int year = readInput("year (int): ", Integer::parseInt);

        String name = readInput("name (String): ", s -> s);
        String title = readInput("title (String): ", s -> s);
        String description = readInput("description (String): ", s -> s);
        String topic = readInput("topic (String): ", s -> s);
        String objectiveInOneLine = readInput("objectiveInOneLine (String): ", s -> s);
        String objectiveFullDescription = readInput("objectiveFullDescription (String): ", s -> s);
        String motivation = readInput("motivation (String): ", s -> s);

        Double duration = readInput("duration (Double): ", Double::parseDouble);
        boolean isActive = readInput("isActive (boolean): ", Boolean::parseBoolean);

        StudyObjective objective = new StudyObjective(title, description);
        objective.handleSetObjectiveAdapter(
                List.of(id, priority, practicedDays, day, month, year),
                List.of(name, title, description, topic, objectiveInOneLine, objectiveFullDescription, motivation),
                duration, isActive);

        studyTaskManager.addRegistry(objective);
        System.out.println("StudyObjective adicionado com sucesso!");
    }

    public void handleRegistryInput() {
        try {
            while (true) {
                controllerOptions();
                String response = MainController.validateInput(actions);
                if (response == null) return;
                actions.get(response).run();
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public static void controllerOptions() {
        System.out.println("""
                0 - return
                4 - add study objective
                """);
    }
}
