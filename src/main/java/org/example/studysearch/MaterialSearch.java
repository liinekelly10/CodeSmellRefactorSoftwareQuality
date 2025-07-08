package org.example.studysearch;

import org.example.studyregistry.StudyMaterial;
import java.util.ArrayList;
import java.util.List;

public class MaterialSearch implements Search<String> {

    private SearchLog searchLog = new SearchLog("Material Search");

    public MaterialSearch() {}

    @Override
    public List<String> search(String text) {
        return handleMaterialSearch(text);
    }

    public SearchLog getSearchLog() {
        return searchLog;
    }

    private List<String> handleMaterialSearch(String text){
        List<String> results = new ArrayList<>();
        results.addAll(StudyMaterial.getStudyMaterial().searchInMaterials(text));

        // ✅ agora completamente encapsulado
        results.add(searchLog.logAndGetMessage(text));

        return results;
    }
}
