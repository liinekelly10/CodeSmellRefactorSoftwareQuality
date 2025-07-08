package org.example.controllers;

import org.example.studycards.*;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

import static org.example.controllers.MainController.getInput;
import static org.example.controllers.MainController.validateInput;

public class StudyCardsController {
    private FlashCard flashCard = new FlashCard("FlashCard");
    private LeitnerSystem leitnerSystem = new LeitnerSystem("LeitnerSystem");
    private CardManager manager = CardManager.getCardManager();
    private Map<String, Runnable> actions = new HashMap<>();

    // Helpers internos para encapsular acesso ao manager e leitnerSystem
    private CardManagerView cardView = new CardManagerView(manager);
    private LeitnerSystemView leitnerView = new LeitnerSystemView(leitnerSystem);

    public StudyCardsController() {
        assignActions();
    }

    public StudyCardsController(LeitnerSystem leitnerSystem) {
        this.leitnerSystem = leitnerSystem;
        leitnerView = new LeitnerSystemView(leitnerSystem);
        assignActions();
    }

    void assignActions(){
        actions.put("1", this::handleViewCards);
        actions.put("2", this::handleCreateCard);
        actions.put("3", this::handleRemoveCard);
        actions.put("4", this::handleRandomFlashCard);
        actions.put("5", this::handleInsertCardInBox);
        actions.put("6", this::handleRemoveCardFromBox);
        actions.put("7", this::handleUpgradeCardFromBox);
        actions.put("8", this::handleDowngradeCardFromBox);
        actions.put("9", this::handleViewBoxes);
        actions.put("10", this::handleGetRandomCardFromBox);
    }

    // Métodos auxiliares para ler inputs
    private int readCardId() {
        System.out.println("Type card id:");
        return Integer.parseInt(getInput());
    }

    private int readBoxNumber() {
        System.out.println("Type box(0-4):");
        return Integer.parseInt(getInput());
    }

    private String readInputWithPrompt(String prompt) {
        System.out.println(prompt);
        return getInput();
    }

    // Handlers utilizando helpers internos
    public void handleViewCards() {
        cardView.printAllCards();
    }

    public void handleRemoveCard() {
        int id = readCardId();
        cardView.removeCardById(id);
    }

    public void handleCreateCard() {
        String question = readInputWithPrompt("Type the question: \n");
        String answer = readInputWithPrompt("Type the answer: \n");
        cardView.addCard(question, answer);
    }

    public void handleRandomFlashCard() {
        System.out.println("Random flash card:");
        Integer id = flashCard.randomFlashCard();
        System.out.println(manager.formatCard(id));
    }

    public void handleInsertCardInBox() {
        int id = readCardId();
        int box = readBoxNumber();
        leitnerView.addCardToBox(id, box);
    }

    public void handleRemoveCardFromBox() {
        int id = readCardId();
        int box = readBoxNumber();
        leitnerView.removeCardFromBox(id, box);
    }

    public void handleViewBoxes() {
        leitnerView.printBoxes();
    }

    public void handleUpgradeCardFromBox() {
        try {
            int id = readCardId();
            int box = readBoxNumber();
            leitnerView.upgradeCard(id, box);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void handleDowngradeCardFromBox() {
        try {
            int id = readCardId();
            int box = readBoxNumber();
            leitnerView.downgradeCard(id, box);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public String getRandomCardFromBox() {
        return leitnerView.getRandomCard();
    }

    public void handleGetRandomCardFromBox() {
        try {
            String response = getRandomCardFromBox();
            System.out.println(response);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void handleCardsInput() {
        try {
            while (true) {
                controllerOptions();
                String response = validateInput(actions);
                if (response == null) {
                    return;
                }
                actions.get(response).run();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void controllerOptions() {
        System.out.println("""
                0 - return
                1 - view cards
                2 - create card
                3 - delete card
                4 - (FlashCard) Get random card
                5 - (Leitner) Insert card in box
                6 - (Leitner) Remove card from box
                7 - (Leitner) Upgrade card from box
                8 - (Leitner) Downgrade card from box
                9 - (Leitner) View boxes
                10- (Leitner) Get random card from box
                """);
    }

    // Helper interno para encapsular CardManager
    private static class CardManagerView {
        private CardManager manager;

        CardManagerView(CardManager manager) {
            this.manager = manager;
        }

        void printAllCards() {
            Map<Integer, Card> cards = manager.getCardsMap();
            if (cards.isEmpty()) {
                System.out.println("No cards");
                return;
            }
            StringBuilder response = new StringBuilder();
            for (Map.Entry<Integer, Card> entry : cards.entrySet()) {
                Card card = entry.getValue();
                response.append("[id: ").append(entry.getKey())
                        .append("] Question: ").append(card.getQuestion())
                        .append(", Answer: ").append(card.getAnswer()).append("\n");
            }
            System.out.print(response);
        }

        void removeCardById(int id) {
            manager.removeCard(id);
        }

        void addCard(String question, String answer) {
            manager.addCard(question, answer);
        }
    }

    // Helper interno para encapsular LeitnerSystem com tratamento de exceções
    private static class LeitnerSystemView {
        private LeitnerSystem leitner;

        LeitnerSystemView(LeitnerSystem leitner) {
            this.leitner = leitner;
        }

        void printBoxes() {
            System.out.println(leitner.toString());
        }

        void addCardToBox(int id, int box) {
            try {
                leitner.addCardToBox(id, box);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        void removeCardFromBox(int id, int box) {
            try {
                leitner.removeCardFromBox(id, box);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        void upgradeCard(int id, int box) {
            try {
                leitner.upgradeCard(id, box);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        void downgradeCard(int id, int box) {
            try {
                leitner.downgradeCard(id, box);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        String getRandomCard() {
            try {
                String response = leitner.getMethodName();
                response += leitner.getRandomCard(leitner.getBoxes());
                return response;
            } catch (Exception e) {
                return e.getMessage();
            }
        }
    }
}
