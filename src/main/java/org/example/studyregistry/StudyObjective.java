package org.example.studyregistry;

import java.time.LocalDateTime;
import java.util.List;

public class StudyObjective extends Registry {
    private String title;
    private String description;
    private String topic;
    private Integer practicedDays;
    private LocalDateTime startDate;
    private Double duration;
    private String objectiveInOneLine;
    private String objectiveFullDescription;
    private String motivation;

    public StudyObjective(String title, String description) {
        this.title = title;
        this.description = description;
        this.name = title; // herdado de Registry
    }

    public int handleSetObjectiveAdapter(List<Integer> intProperties, List<String> stringProperties, Double duration, boolean isActive) {
        if (intProperties == null || intProperties.size() < 6)
            throw new IllegalArgumentException("intProperties precisa ter pelo menos 6 elementos");
        if (stringProperties == null || stringProperties.size() < 7)
            throw new IllegalArgumentException("stringProperties precisa ter pelo menos 7 elementos");

        this.id = intProperties.get(0);
        this.priority = intProperties.get(1);
        this.practicedDays = intProperties.get(2);
        int day = intProperties.get(3);
        int month = intProperties.get(4);
        int year = intProperties.get(5);
        this.startDate = LocalDateTime.of(year, month, day, 0, 0);

        this.name = stringProperties.get(0);
        this.title = stringProperties.get(1);
        this.description = stringProperties.get(2);
        this.topic = stringProperties.get(3);
        this.objectiveInOneLine = stringProperties.get(4);
        this.objectiveFullDescription = stringProperties.get(5);
        this.motivation = stringProperties.get(6);

        this.duration = duration;
        this.isActive = isActive;

        return this.id;
    }

    // ------------- Comportamentos adicionados ----------------

    public void markAsPracticed() {
        if (practicedDays == null) practicedDays = 0;
        practicedDays++;
    }

    public boolean isCompleted() {
        return practicedDays != null && duration != null && practicedDays >= duration;
    }

    public String summary() {
        return String.format("[%s] %s - %s | Progresso: %d/%.0f dias",
                topic == null ? "Sem tópico" : topic,
                title,
                objectiveInOneLine == null ? "Sem objetivo curto" : objectiveInOneLine,
                practicedDays == null ? 0 : practicedDays,
                duration == null ? 0.0 : duration);
    }

    // --------------------------------------------------------

    // getters simples
    public String getName() { return name; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getTopic() { return topic; }
    public Integer getPracticedDays() { return practicedDays; }
    public LocalDateTime getStartDate() { return startDate; }
    public Double getDuration() { return duration; }
    public String getObjectiveInOneLine() { return objectiveInOneLine; }
    public String getObjectiveFullDescription() { return objectiveFullDescription; }
    public String getMotivation() { return motivation; }
}