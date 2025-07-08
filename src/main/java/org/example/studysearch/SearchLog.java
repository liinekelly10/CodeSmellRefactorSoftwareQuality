package org.example.studysearch;

import java.util.ArrayList;
import java.util.List;

public class SearchLog {

    private final String logName;
    private final List<String> searchHistory = new ArrayList<>();
    private int numUsages = 0;

    public SearchLog(String logName) {
        this.logName = logName;
    }

    public String getLogName() {
        return logName;
    }

    public List<String> getSearchHistory() {
        return new ArrayList<>(searchHistory);
    }

    public int getNumUsages() {
        return numUsages;
    }

    public void addSearchHistory(String text) {
        searchHistory.add(text);
    }

    public void incrementNumUsages() {
        this.numUsages++;
    }

    // Método para registrar a busca
    public void logSearch(String text) {
        addSearchHistory(text);
        incrementNumUsages();
    }
}
