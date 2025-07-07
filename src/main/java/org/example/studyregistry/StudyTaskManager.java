package org.example.studyregistry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StudyTaskManager {
    private static StudyTaskManager instance;
    private StudyMaterial studyMaterial = StudyMaterial.getStudyMaterial();
    List<Registry> registryList;
    List<String> weekResponsibilities = List.of();

    private StudyTaskManager() {
        this.registryList = new ArrayList<Registry>();
    }

    public static StudyTaskManager getStudyTaskManager() {
        if (instance == null) {
            instance = new StudyTaskManager();
        }
        return instance;
    }

    public List<String> getWeekResponsibilities() {
        return weekResponsibilities;
    }

    /**
     * Configura as responsabilidades da semana a partir de uma lista de 11 strings,
     * representando respectivamente: planName, objectiveTitle, objectiveDescription,
     * materialTopic, materialFormat, goal, reminderTitle, reminderDescription,
     * mainTaskTitle, mainHabit, mainCardStudy.
     *
     * @param stringProperties lista com exatamente 11 elementos
     */
    public void setUpWeek(List<String> stringProperties) {
        if (stringProperties.size() != 11) {
            throw new IllegalArgumentException("Expected 11 elements for week setup, got " + stringProperties.size());
        }
        this.weekResponsibilities = new ArrayList<>(stringProperties);
    }

    /**
     * Mantido para compatibilidade com quem chama handleSetUpWeek,
     * mas agora apenas redireciona para setUpWeek(List<String>).
     */
    public void handleSetUpWeek(List<String> stringProperties) {
        setUpWeek(stringProperties);
    }

    public void addRegistry(Registry registry) {
        registryList.add(registry);
    }

    public void removeRegistry(Registry registry) {
        registryList.remove(registry);
    }

    public List<Registry> getRegistryList() {
        return registryList;
    }

    public List<String> searchInRegistries(String text) {
        List<String> response = new ArrayList<>();
        for (Registry registry : registryList) {
            String mix = (registry.getName() != null ? registry.getName() : "");
            if (mix.toLowerCase().contains(text.toLowerCase())) {
                response.add(registry.getName());
            }
        }
        return response;
    }
}
