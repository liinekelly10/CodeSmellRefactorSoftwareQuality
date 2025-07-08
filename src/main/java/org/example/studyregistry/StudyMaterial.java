package org.example.studyregistry;

import org.example.studymaterial.AudioReference;
import org.example.studymaterial.Reference;
import org.example.studymaterial.TextReference;
import org.example.studymaterial.VideoReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudyMaterial {
    List<Reference> references;
    private static StudyMaterial studyMaterial;
    private Map<String, Integer> referenceCount;

    private StudyMaterial() {
        references = new ArrayList<Reference>();
    }

    public static StudyMaterial getStudyMaterial() {
        if (studyMaterial == null) {
            studyMaterial = new StudyMaterial();
        }
        return studyMaterial;
    }

    public void addReference(Reference ref) {
        references.add(ref);
    }

    List<Reference> getReferences() {
        return references;
    }

    public List<Reference> getTypeReference(Reference type) {
        List<Reference> response = new ArrayList<>();
        for (Reference reference : references) {
            if (reference.getClass() == type.getClass()) {
                response.add(reference);
            }
        }
        return response;
    }

    public void setReferenceCount(Map<String, Integer> referenceCount) {
        this.referenceCount = referenceCount;
    }

    public List<String> searchInMaterials(String text) {
        List<String> response = new ArrayList<>();
        for (Reference reference : references) {
            String mix = (reference.getTitle() != null ? reference.getTitle() : "")
                    + (reference.getDescription() != null ? reference.getDescription() : "");
            if (mix.toLowerCase().contains(text.toLowerCase())) {
                response.add(reference.getTitle());
            }
        }
        return response;
    }

    public Map<String, Integer> getReferenceCountMap() {
        Map<String, Integer> response = initializeCountMap();
        for (Reference reference : references) {
            updateCountBasedOnType(reference, response);
        }
        setReferenceCount(response);
        return response;
    }

    private Map<String, Integer> initializeCountMap() {
        Map<String, Integer> countMap = new HashMap<>();
        countMap.put("Audio References", 0);
        countMap.put("Video References", 0);
        countMap.put("Text References", 0);
        return countMap;
    }

    private void updateCountBasedOnType(Reference reference, Map<String, Integer> countMap) {
        if (reference.getClass() == AudioReference.class) {
            incrementCount(countMap, "Audio References");
        } else if (reference.getClass() == VideoReference.class) {
            if (((VideoReference) reference).handleStreamAvailability()) {
                incrementCount(countMap, "Video References");
            }
        } else if (reference.getClass() == TextReference.class) {
            if (((TextReference) reference).handleTextAccess()) {
                incrementCount(countMap, "Text References");
            }
        }
    }

    private void incrementCount(Map<String, Integer> countMap, String key) {
        countMap.put(key, countMap.get(key) + 1);
    }
}
