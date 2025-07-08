package org.example.studycards;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeitnerSystem extends StudyMethod {
    private final List<Box> boxes;

    public LeitnerSystem(String methodName) {
        super(methodName);
        this.boxes = createBoxes(5);
    }

    @Override
    public String getMethodName() {
        return this.methodName;
    }

    @Override
    void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    @Override
    public String toString() {
        return buildBoxString();
    }

    public void clearBoxes() {
        boxes.clear();
        boxes.addAll(createBoxes(5));
    }

    public List<Box> getBoxes() {
        return boxes;
    }

    public String getRandomCard(List<Box> otherBoxes) {
        if (otherBoxes == null || otherBoxes.isEmpty()) return null;

        Box merged = mergeBoxes(otherBoxes);
        Integer cardId = merged.getRandomCard();
        return (cardId == null) ? "No card found" : formatCard(cardId);
    }

    public void addCardToBox(Integer id, Integer boxId) {
        boxes.get(boxId).addCard(id);
    }

    public void removeCardFromBox(Integer id, Integer boxId) {
        boxes.get(boxId).removeCard(id);
    }

    public Card takeCardFromBox(Integer boxId) {
        return cardManager.getCard(boxes.get(boxId).getRandomCard());
    }

    public void boxIdValidation(Integer boxId) throws Exception {
        if (boxId == null || boxId < 0 || boxId >= boxes.size()) {
            throw new Exception("Invalid box ID");
        }
    }

    public void upgradeCard(Integer cardId, Integer boxId) throws Exception {
        moveCardWithValidation(cardId, boxId, Math.min(boxId + 1, boxes.size() - 1));
    }

    public void downgradeCard(Integer cardId, Integer boxId) throws Exception {
        moveCardWithValidation(cardId, boxId, Math.max(boxId - 1, 0));
    }

    // 🔽 Métodos privados para reduzir complexidade 🔽

    private List<Box> createBoxes(int count) {
        List<Box> newBoxes = new ArrayList<>();
        for (int i = 0; i < count; i++) newBoxes.add(new Box());
        return newBoxes;
    }

    private String buildBoxString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < boxes.size(); i++) {
            result.append("Box ").append(i).append(": ").append(boxes.get(i)).append("\n");
        }
        return result.toString();
    }

    private Box mergeBoxes(List<Box> boxList) {
        Box result = new Box();
        for (Box b : boxList) result.addCards(b.getCards());
        return result;
    }

    private String formatCard(Integer cardId) {
        Card card = cardManager.getCard(cardId);
        return "[" + cardId + "] The random question was: " + card.getQuestion() +
                " | The answer is: " + card.getAnswer();
    }

    private void moveCardWithValidation(Integer cardId, Integer fromBoxId, Integer toBoxId) throws Exception {
        boxIdValidation(fromBoxId);
        Box fromBox = boxes.get(fromBoxId);
        if (!fromBox.hasCard(cardId)) throw new Exception("No card Found");

        fromBox.removeCard(cardId);
        boxes.get(toBoxId).addCard(cardId);
    }
}
