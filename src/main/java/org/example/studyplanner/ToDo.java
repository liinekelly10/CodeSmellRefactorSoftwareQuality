package org.example.studyplanner;

import java.text.MessageFormat;

public class ToDo implements PlannerMaterial {
    private Integer id;  // id mutável apenas para compatibilidade com testes
    private String title;
    private String description;
    private int priority;

    public ToDo(Integer id, String title, String description, int priority) {
        if (id == null || id <= 0) throw new IllegalArgumentException("Id must be positive and not null");
        this.id = id;
        updateDetails(title, description, priority);
    }

    @Override
    public String toString() {
        return MessageFormat.format("[(Priority:{3}) ToDo {0}: {1}, {2}]", id, title, description, priority);
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        if (id <= 0) throw new IllegalArgumentException("Id must be positive");
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void updateTitle(String title) {
        if (title == null || title.isEmpty()) throw new IllegalArgumentException("Title cannot be empty");
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void updateDescription(String description) {
        if (description == null) throw new IllegalArgumentException("Description cannot be null");
        this.description = description;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        if (priority < 0) throw new IllegalArgumentException("Priority cannot be negative");
        this.priority = priority;
    }

    // ---- Adicionando comportamentos relevantes ----

    /** Promove a prioridade para a mais alta. */
    public void escalate() {
        this.priority = Integer.MAX_VALUE;
    }

    /** Marca a tarefa como concluída (usando descrição). */
    public void markAsDone() {
        this.description = this.description + " [DONE]";
    }

    /** Atualiza todos os dados em uma operação atômica. */
    public void updateDetails(String title, String description, int priority) {
        updateTitle(title);
        updateDescription(description);
        setPriority(priority);
    }
}
