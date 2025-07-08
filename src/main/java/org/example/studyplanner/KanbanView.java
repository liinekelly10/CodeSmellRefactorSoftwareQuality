package org.example.studyplanner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KanbanView {
    public enum State {
        TODO, DOING, DONE;
    }

    HabitTracker habitTracker = null;
    TodoTracker todoTracker = null;
    Map<State, List<PlannerMaterial>> kanban = null;

    public KanbanView(HabitTracker habitTracker, TodoTracker todoTracker) {
        this.habitTracker = habitTracker;
        this.todoTracker = todoTracker;
        this.kanban = new HashMap<>();
        this.kanban.put(State.TODO, new ArrayList<>());
        this.kanban.put(State.DOING, new ArrayList<>());
        this.kanban.put(State.DONE, new ArrayList<>());
    }

    public List<PlannerMaterial> getKanbanByState(State state) {
        return kanban.get(state);
    }

    public void addHabitToKanban(State state, Integer id) throws Exception {
        try {
            Habit toAdd = this.habitTracker.getHabitById(id);
            if (toAdd == null) {
                throw new Exception("Habit not found with id: " + id);
            }
            kanban.get(state).add(toAdd);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void addToDoToKanban(State state, Integer id) throws Exception {
        try {
            ToDo toAdd = this.todoTracker.getToDoById(id);
            if (toAdd == null) {
                throw new Exception("ToDo not found with id: " + id);
            }
            kanban.get(state).add(toAdd);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void removeHabitFromKanban(State state, Integer id) throws Exception {
        try {
            Habit toRemove = this.habitTracker.getHabitById(id);
            if (toRemove == null) {
                throw new Exception("No habit found with id: " + id);
            }
            kanban.get(state).remove(toRemove);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void removeToDoFromKanban(State state, Integer id) throws Exception {
        try {
            ToDo toRemove = this.todoTracker.getToDoById(id);
            if (toRemove == null) {
                throw new Exception("No todo found with id: " + id);
            }
            kanban.get(state).remove(toRemove);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public String kanbanView() throws Exception {
        validateKanban();

        StringBuilder sb = new StringBuilder();
        sb.append("[ Material ToDo: ").append(System.lineSeparator());
        sb.append(formatSection(State.TODO));

        sb.append(System.lineSeparator()).append("Material in progress:").append(System.lineSeparator());
        sb.append(formatSection(State.DOING));

        sb.append(System.lineSeparator()).append("Material completed:").append(System.lineSeparator());
        sb.append(formatSection(State.DONE));

        sb.append("]");
        return sb.toString();
    }

    // MÉTODO EXTRAÍDO 1: Validação do mapa kanban
    private void validateKanban() throws Exception {
        if (kanban == null || kanban.isEmpty()) {
            throw new Exception("No material found");
        }
    }

    // MÉTODO EXTRAÍDO 2: Formatação de uma seção do kanban
    private String formatSection(State state) {
        List<PlannerMaterial> materials = kanban.getOrDefault(state, new ArrayList<>());
        if (materials.isEmpty()) {
            return "No material found";
        }

        return buildMaterialsList(materials);
    }

    // MÉTODO EXTRAÍDO 3: Constrói a string dos materiais
    private String buildMaterialsList(List<PlannerMaterial> materials) {
        StringBuilder section = new StringBuilder();
        for (PlannerMaterial material : materials) {
            section.append(", ").append(material.toString());
        }
        return section.toString();
    }
}
