package hanze.nl.tijdtools;

public class Counter {

    public static int getCounter(Tijd simulatorTijd){
        return calculateCounter(simulatorTijd);
    }

    public static int getTijdCounter(Tijd simulatorTijd, Tijd verschil){
        return calculateCounter(simulatorTijd)+calculateCounter(verschil);
    }

    private static int calculateCounter(Tijd tijd){
        return tijd.getUur()*3600+tijd.getMinuut()*60+tijd.getSeconde();
    }
}
