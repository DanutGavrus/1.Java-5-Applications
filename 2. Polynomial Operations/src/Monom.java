public class Monom implements Comparable<Monom> {
    private float coeff;
    private int power;

    public Monom(float coeff, int power) {
        this.coeff = coeff;
        this.power = power;
    }

    @Override
    public int compareTo(Monom monom) {
        Integer power = this.power;
        Integer power2 = monom.power;

        return power.compareTo(power2);
    }

    public float getCoeff() {return coeff;}

    public int getPower() {return power;}

    public void setCoeff(float coeff) {this.coeff = coeff;}

    public void setPower(int power) {this.power = power;}
}
