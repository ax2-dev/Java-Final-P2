import java.io.Serializable;

abstract class game implements Serializable {
    protected String id;
    protected String title;
    protected int releaseYear;

    public game(String id, String title, int releaseYear) {
        this.id = id;
        this.title = title;
        this.releaseYear = releaseYear;
    }

    public abstract double calcVal();

    protected double studentIDOffset(double value) {
        int ID = 9427426;

        if (ID % 2 == 0) {
            value -= ((ID / 10) % 10) / 2.0;
        } else {
            value += (ID / 1000) % 10;
        }

        return value;
    }
}

class retroGame extends game {
    protected String publisher;
    protected String condition;
    protected String platform;

    public retroGame(String id, String title, int releaseYear, String publisher, String condition, String platform) {
        super(id, title, releaseYear);
        this.publisher = publisher;
        this.condition = condition;
        this.platform = platform;
    }

    @Override
    public double calcVal() {
        double value = 65;
        int age = 2024 - releaseYear;
        value += age * 1.5;
        if ("EXCELLENT".equalsIgnoreCase(condition)){
            value *= 1.5;
        } else if ("GOOD".equalsIgnoreCase(condition)){
            value *= 1.2;
        }
        else{
            value *= 0.9;
        }

        return studentIDOffset(value);
    }
}

class CollectorEdition extends retroGame {
    private String specialFeatures;
    private String packagingType;
    private int limitedNumber;

    public CollectorEdition(String id, String title, int releaseYear, String publisher, String condition,String platform, String specialFeatures, String packagingType, int limitedNumber) {
        super(id, title, releaseYear, publisher, condition, platform);
        this.specialFeatures = specialFeatures;
        this.packagingType = packagingType;
        this.limitedNumber = limitedNumber;
    }

    @Override
    public double calcVal() {
        double value = super.calcVal();
        value += limitedNumber * 50;
        if ("SPECIAL".equalsIgnoreCase(packagingType)) {
            value *= 2;
        }
        return studentIDOffset(value);
    }
}