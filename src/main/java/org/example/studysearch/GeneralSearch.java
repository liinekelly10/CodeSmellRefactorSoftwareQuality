package org.example.studysearch;

import org.example.studycards.CardManager;
import org.example.studyplanner.HabitTracker;
import org.example.studyplanner.TodoTracker;
import org.example.studyregistry.StudyMaterial;
import org.example.studyregistry.StudyTaskManager;

import java.util.ArrayList;
import java.util.List;

public class GeneralSearch implements Search<String> {

    private SearchLog searchLog = new SearchLog("General Search");
    private List<SearchSource> searchSources;

    public GeneralSearch() {
        searchSources = new ArrayList<>();
        searchSources.add(new CardSearchSource(CardManager.getCardManager()));
        searchSources.add(new HabitSearchSource(HabitTracker.getHabitTracker()));
        searchSources.add(new TodoSearchSource(TodoTracker.getInstance()));
        searchSources.add(new MaterialSearchSource(StudyMaterial.getStudyMaterial()));
        searchSources.add(new TaskRegistrySearchSource(StudyTaskManager.getStudyTaskManager()));
    }

    @Override
    public List<String> search(String text) {
        List<String> results = new ArrayList<>();
        for (SearchSource source : searchSources) {
            results.addAll(source.search(text));
        }
        // registra o log sem poluir os resultados
        searchLog.logSearch(text);
        return results;
    }

    public SearchLog getSearchLog() {
        return searchLog;
    }

    private interface SearchSource {
        List<String> search(String text);
    }

    private class CardSearchSource implements SearchSource {
        private final CardManager cardManager;
        public CardSearchSource(CardManager cardManager) {
            this.cardManager = cardManager;
        }
        public List<String> search(String text) {
            return cardManager.searchInCards(text);
        }
    }

    private class HabitSearchSource implements SearchSource {
        private final HabitTracker habitTracker;
        public HabitSearchSource(HabitTracker habitTracker) {
            this.habitTracker = habitTracker;
        }
        public List<String> search(String text) {
            return habitTracker.searchInHabits(text);
        }
    }

    private class TodoSearchSource implements SearchSource {
        private final TodoTracker todoTracker;
        public TodoSearchSource(TodoTracker todoTracker) {
            this.todoTracker = todoTracker;
        }
        public List<String> search(String text) {
            return todoTracker.searchInTodos(text);
        }
    }

    private class MaterialSearchSource implements SearchSource {
        private final StudyMaterial studyMaterial;
        public MaterialSearchSource(StudyMaterial studyMaterial) {
            this.studyMaterial = studyMaterial;
        }
        public List<String> search(String text) {
            return studyMaterial.searchInMaterials(text);
        }
    }

    private class TaskRegistrySearchSource implements SearchSource {
        private final StudyTaskManager taskManager;
        public TaskRegistrySearchSource(StudyTaskManager taskManager) {
            this.taskManager = taskManager;
        }
        public List<String> search(String text) {
            return taskManager.searchInRegistries(text);
        }
    }
}
