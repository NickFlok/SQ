package hanze.nl.tijdtools;

import java.io.IOException;
import com.thoughtworks.xstream.XStream;

public class TijdFuncties {
	private Tijd startTijd;
	private Tijd simulatorTijd;
	private Tijd verschil;
	private int interval;
	private int syncInterval;
	private int syncCounter;
	
    public void initSimulatorTijden(int interval, int syncInterval){
    	simulatorTijd=new Tijd(0,0,0);
    	startTijd=CentralTime.getCentralTime();
    	verschil=berekenVerschil(startTijd,simulatorTijd);
    	this.interval=interval;
    	this.syncCounter=syncInterval;
    	this.syncInterval=syncInterval;
    }

    public String getSimulatorWeergaveTijd(){
    	Tijd simulatorWeergaveTijd= simulatorTijd.copyTijd();
    	simulatorWeergaveTijd.increment(verschil);
    	return simulatorWeergaveTijd.toString();
    }

    public void simulatorStep() throws InterruptedException{
		Thread.sleep(interval);
		simulatorTijd.increment(new Tijd(0,0,1));
		syncCounter--;
		if (syncCounter==0){
			syncCounter=syncInterval;
			synchroniseTijd();
		}
    }

    private Tijd berekenVerschil(Tijd reverentieTijd, Tijd werkTijd){
    	int urenVerschil = reverentieTijd.getUur()-werkTijd.getUur();
    	int minutenVerschil = reverentieTijd.getMinuut()-werkTijd.getMinuut();
    	int secondenVerschil = reverentieTijd.getSeconde()-werkTijd.getSeconde();
    	if (secondenVerschil<0){
    		minutenVerschil--;
    		secondenVerschil+=60;
    	}
    	if (minutenVerschil<0){
    		urenVerschil--;
    		minutenVerschil+=60;
    	}
    	return new Tijd(urenVerschil, minutenVerschil, secondenVerschil);
    }
    
    private void synchroniseTijd(){
    	Tijd huidigeTijd = CentralTime.getCentralTime();
    	System.out.println("De werkelijke tijd is nu: "+ huidigeTijd.toString());
    	Tijd verwachtteSimulatorTijd = simulatorTijd.copyTijd();
    	verwachtteSimulatorTijd.increment(verschil);
    	Tijd delay = berekenVerschil(huidigeTijd, verwachtteSimulatorTijd);
    	verschil.increment(delay);
    }
}
