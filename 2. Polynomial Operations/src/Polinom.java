import java.util.*;

public class Polinom {
    private List<Monom> monomList;

    public Polinom() {
        monomList = new ArrayList<Monom>();
    }

    public void addMonom(Monom monom) {
        boolean toAdd = true;
        for (Monom o: monomList) {
            if (o.getPower() == monom.getPower()) {
                o.setCoeff(monom.getCoeff() + o.getCoeff());
                toAdd = false;
            }
        }
        if (toAdd) monomList.add(monom);
    }

    public void purge() {
        monomList.clear();
    }

    public void sortMonoms() {
        monomList.sort(null);
        Collections.reverse(monomList);
    }

    public int getDegree() {
        int i = 0;
        if (monomList.size() == 1 && monomList.get(0).getCoeff() == 0) return 0;
        while (monomList.get(i).getCoeff() == 0) {
            monomList.remove(i);
        }
        return monomList.get(i).getPower();
    }

    public Monom getMonom(int index) {
        return monomList.get(index);
    }

    public List<Monom> getMonoms() {
        return monomList;
    }
}
