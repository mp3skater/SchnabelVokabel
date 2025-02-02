package net.mp3skater.schnabelvokabel.model;

public class AppState {
    private static AppState instance;
    private boolean reverseLanguage;
    private boolean checkCapitalization;
    private int currentKarteiNummer;
    private int currentFach;
    private String lastGuess;

    private AppState() {}

    public static synchronized AppState getInstance() {
        if (instance == null) {
            instance = new AppState();
        }
        return instance;
    }

    // Getters and setters
    public int getCurrentFach() {
        return currentFach;
    }
    public void setCurrentFach(int currentFach) {
        this.currentFach = currentFach;
    }

    public boolean isReverseLanguage() { return reverseLanguage; }
    public void setReverseLanguage(boolean reverseLanguage) { this.reverseLanguage = reverseLanguage; }

    public int getCurrentKarteiNummer() {
        return currentKarteiNummer;
    }
    public void setCurrentKarteiNummer(int currentKarteiNummer) {
        this.currentKarteiNummer = currentKarteiNummer;
    }

    public boolean isCheckCapitalization() { return checkCapitalization; }
    public void setCheckCapitalization(boolean checkCapitalization) { this.checkCapitalization = checkCapitalization; }
}