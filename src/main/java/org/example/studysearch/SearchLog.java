package org.example.studysearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchLog {
    private List<String> searchHistory;
    private Map<String, Integer> searchCount;
    private boolean isLocked;
    private Integer numUsages;
    private String logName;

    public SearchLog(String logName) {
        searchHistory = new ArrayList<>();
        searchCount = new HashMap<>();
        this.logName = logName;
        numUsages = 0;
        isLocked = false;
    }

    // Método compatível com testes antigos
    public void addSearchHistory(String term) {
        recordSearch(term);
    }

    // Método principal que encapsula a lógica de adicionar histórico e atualizar contador
    public void recordSearch(String searchTerm) {
        if (isLocked) {
            throw new IllegalStateException("SearchLog is locked. Cannot modify.");
        }
        searchHistory.add(searchTerm);
        searchCount.put(searchTerm, searchCount.getOrDefault(searchTerm, 0) + 1);
        numUsages++;
    }

    public List<String> getSearchHistory() {
        return searchHistory;
    }

    public Map<String, Integer> getSearchCount() {
        return searchCount;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked(boolean locked) {
        isLocked = locked;
    }

    public Integer getNumUsages() {
        return numUsages;
    }

    public String getLogName() {
        return logName;
    }
}
