package net.mp3skater.schnabelvokabel.model;

public class AppState {
    private static AppState instance;
    private boolean reverseLanguage;
    private boolean checkCapitalization;

    private AppState() {}

    public static synchronized AppState getInstance() {
        if (instance == null) {
            instance = new AppState();
        }
        return instance;
    }

    // Getters and setters
    public boolean isReverseLanguage() { return reverseLanguage; }
    public void setReverseLanguage(boolean reverseLanguage) { this.reverseLanguage = reverseLanguage; }

    public boolean isCheckCapitalization() { return checkCapitalization; }
    public void setCheckCapitalization(boolean checkCapitalization) { this.checkCapitalization = checkCapitalization; }
}