public class Operations {

    public static Polinom add2Polinoms(Polinom polinom1, Polinom polinom2) {
        Polinom result = new Polinom();
        for (Monom monom: polinom1.getMonoms()) {
            result.addMonom(monom);
        }
        for (Monom monom: polinom2.getMonoms()) {
            result.addMonom(monom);
        }
        result.sortMonoms();
        return result;
    }

    public static Polinom substract2Polinoms(Polinom polinom1, Polinom polinom2) {
        Polinom result = new Polinom();
        Polinom copy = polinom2;
        for (Monom monom: polinom1.getMonoms()) {
            result.addMonom(monom);
        }
        for (Monom monom: copy.getMonoms()) {
            monom.setCoeff(0 - monom.getCoeff());
            result.addMonom(monom);
        }
        result.sortMonoms();
        return result;
    }

    public static Polinom multiply2Polinoms(Polinom polinom1, Polinom polinom2) {
        Polinom result = new Polinom();
        for (Monom monom1: polinom1.getMonoms()) {
            for (Monom monom2: polinom2.getMonoms()) {
                Monom resultMonom = new Monom(0, 0);
                resultMonom.setCoeff(monom1.getCoeff() * monom2.getCoeff());
                resultMonom.setPower(monom1.getPower() + monom2.getPower());
                result.addMonom(resultMonom);
            }
        }
        return result;
    }

    public static Polinom[] divide2Polinoms(Polinom polinom1, Polinom polinom2) {
        if (polinom2.getMonoms().size() == 1 && polinom2.getMonom(0).getCoeff() == 0) return null;
        else {
            Polinom[] results = new Polinom[2];
            Polinom cat = new Polinom();
            Polinom rest = new Polinom();
            Polinom aux = polinom1;
            do {
                Polinom aux2 = new Polinom();
                Monom help = new Monom(0,0);
                help.setCoeff(aux.getMonom(0).getCoeff() / polinom2.getMonom(0).getCoeff());
                help.setPower(aux.getMonom(0).getPower() - polinom2.getMonom(0).getPower());
                cat.addMonom(help);
                for(Monom m: polinom2.getMonoms()) {
                    Monom help2 = new Monom(0, 0);
                    help2.setCoeff(m.getCoeff() * help.getCoeff());
                    help2.setPower(m.getPower() + help.getPower());
                    aux2.addMonom(help2);
                }
                rest = Operations.substract2Polinoms(aux,aux2);
                aux = rest;
                System.out.println(rest.getDegree());
            } while (rest.getDegree() > 1 && rest.getDegree() >= polinom2.getDegree());
            results[0] = cat;
            results[1] = rest;
            return results;
        }
    }

    public static Polinom integratePolinom1(Polinom polinom) {
        Polinom result = new Polinom();
        for (Monom monom: polinom.getMonoms()) {
            Monom monomCopy = new Monom(0,0);
            monomCopy.setPower(monom.getPower() + 1);
            monomCopy.setCoeff(monom.getCoeff() / (monom.getPower() + 1));
            result.addMonom(monomCopy);
        }
        return result;
    };

    public static Polinom derivatePolinom1(Polinom polinom) {
        Polinom result = new Polinom();
        for (Monom monom: polinom.getMonoms()) {
            Monom monomCopy = new Monom(0,0);
            monomCopy.setPower(monom.getPower() - 1);
            monomCopy.setCoeff(monom.getCoeff() * monom.getPower());
            result.addMonom(monomCopy);
        }
        return result;
    }
}
