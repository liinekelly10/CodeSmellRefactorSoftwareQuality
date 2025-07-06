package org.example.studyregistry;

import java.time.LocalDateTime;

public class Task extends Registry {
    private String title;
    private String description;
    private String author;
    private LocalDateTime dueDate;
    private boolean completed;

    public Task(String title, String description, String author, LocalDateTime dueDate) {
        setTitleInternal(title);
        setDescriptionInternal(description);
        setAuthorInternal(author);
        setDueDateInternal(dueDate);
        this.completed = false;
    }

    // Métodos do domínio - única interface pública significativa
    public void complete() {
        if (completed) {
            throw new IllegalStateException("A tarefa já foi concluída.");
        }
        this.completed = true;
    }

    public void reopen() {
        if (!completed) {
            throw new IllegalStateException("A tarefa ainda não está concluída para ser reaberta.");
        }
        this.completed = false;
    }

    public boolean isOverdue() {
        return !completed && LocalDateTime.now().isAfter(this.dueDate);
    }

    public String getSummary() {
        return String.format(
                "Tarefa: %s\nAutor: %s\nVencimento: %s\nDescrição: %s\nStatus: %s",
                title, author, dueDate,
                description.isBlank() ? "Sem descrição" : description,
                completed ? "Concluída" : (isOverdue() ? "Atrasada" : "Pendente")
        );
    }

    // Getters obrigatórios pelos testes (não são parte do domínio, mas necessários)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        setTitleInternal(title);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        setDescriptionInternal(description);
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        setAuthorInternal(author);
    }

    public LocalDateTime getDate() {
        return dueDate;
    }

    public void setDate(LocalDateTime date) {
        setDueDateInternal(date);
    }

    public boolean isCompleted() {
        return completed;
    }

    // Encapsulamento interno com validação
    private void setTitleInternal(String title) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("O título não pode ser nulo ou vazio");
        }
        this.title = title.trim();
        this.name = this.title; // Registry compatibility
    }

    private void setDescriptionInternal(String description) {
        if (description == null) {
            throw new IllegalArgumentException("A descrição não pode ser nula");
        }
        this.description = description.length() > 500
                ? description.substring(0, 500) + "..."
                : description.trim();
    }

    private void setAuthorInternal(String author) {
        if (author == null || author.isBlank()) {
            throw new IllegalArgumentException("Autor não pode ser nulo ou vazio");
        }
        this.author = author.trim();
    }

    private void setDueDateInternal(LocalDateTime date) {
        if (date == null) {
            throw new IllegalArgumentException("Data não pode ser nula");
        }
        this.dueDate = date;
    }
}
