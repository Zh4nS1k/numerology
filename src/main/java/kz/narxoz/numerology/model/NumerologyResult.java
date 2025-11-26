package kz.narxoz.numerology.model;

public class NumerologyResult {

    private Long id;
    private String originalName;
    private String transliteratedName;
    private int nameNumber;
    private String nameMeaning;
    private int consciousnessNumber;
    private String consciousnessMeaning;
    private int missionNumber;
    private String missionMeaning;

    public NumerologyResult() {
    }

    public NumerologyResult(Long id,
                            String originalName,
                            String transliteratedName,
                            int nameNumber,
                            String nameMeaning,
                            int consciousnessNumber,
                            String consciousnessMeaning,
                            int missionNumber,
                            String missionMeaning) {
        this.id = id;
        this.originalName = originalName;
        this.transliteratedName = transliteratedName;
        this.nameNumber = nameNumber;
        this.nameMeaning = nameMeaning;
        this.consciousnessNumber = consciousnessNumber;
        this.consciousnessMeaning = consciousnessMeaning;
        this.missionNumber = missionNumber;
        this.missionMeaning = missionMeaning;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public String getTransliteratedName() {
        return transliteratedName;
    }

    public void setTransliteratedName(String transliteratedName) {
        this.transliteratedName = transliteratedName;
    }

    public int getNameNumber() {
        return nameNumber;
    }

    public void setNameNumber(int nameNumber) {
        this.nameNumber = nameNumber;
    }

    public String getNameMeaning() {
        return nameMeaning;
    }

    public void setNameMeaning(String nameMeaning) {
        this.nameMeaning = nameMeaning;
    }

    public int getConsciousnessNumber() {
        return consciousnessNumber;
    }

    public void setConsciousnessNumber(int consciousnessNumber) {
        this.consciousnessNumber = consciousnessNumber;
    }

    public String getConsciousnessMeaning() {
        return consciousnessMeaning;
    }

    public void setConsciousnessMeaning(String consciousnessMeaning) {
        this.consciousnessMeaning = consciousnessMeaning;
    }

    public int getMissionNumber() {
        return missionNumber;
    }

    public void setMissionNumber(int missionNumber) {
        this.missionNumber = missionNumber;
    }

    public String getMissionMeaning() {
        return missionMeaning;
    }

    public void setMissionMeaning(String missionMeaning) {
        this.missionMeaning = missionMeaning;
    }
}
