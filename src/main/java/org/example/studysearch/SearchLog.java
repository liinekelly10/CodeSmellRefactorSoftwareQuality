package org.example.studysearch;

import java.util.ArrayList;
import java.util.List;

public class SearchLog {

    private String logName;
    private int numUsages;
    private List<String> searchHistory = new ArrayList<>();

    public SearchLog(String logName) {
        this.logName = logName;
        this.numUsages = 0;
    }

    public void addSearchHistory(String searchTerm) {
        searchHistory.add(searchTerm);
    }

    public List<String> getSearchHistory() {
        return searchHistory;
    }

    public int getNumUsages() {
        return numUsages;
    }

    public void setNumUsages(int numUsages) {
        this.numUsages = numUsages;
    }

    public String getLogName() {
        return logName;
    }

    // ✅ Novo método que encapsula toda a lógica de log + mensagem
    public String logAndGetMessage(String text) {
        this.addSearchHistory(text);
        this.setNumUsages(this.getNumUsages() + 1);
        return "\nLogged in: " + this.logName;
    }
}
