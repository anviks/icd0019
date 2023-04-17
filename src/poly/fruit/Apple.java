package poly.fruit;

public class Apple implements Weighable {

    private Double weightInKiloGrams;

    public Apple(Double weight) {
        this.weightInKiloGrams = weight;
    }

    @Override
    public Integer getWeightInGrams() {
        Double weightInGrams = weightInKiloGrams * 1000;
        return weightInGrams.intValue();
    }
}

