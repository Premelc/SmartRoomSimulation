package dataset;

import Operations.Reader;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DHMZObradenoDataset implements Dataset {

    public static final DateTimeFormatter DateFormatter = DateTimeFormatter.ofPattern("d.MM.yyyy.");
    public static final DateTimeFormatter TimeFormatter = DateTimeFormatter.ofPattern("hh:MM:SS");

    private LocalDate datum;
    private LocalTime vrijeme;
    private Timestamp ts;
    private float zracenje;
    private float temperatura;
    private float smjerVjetra;
    private float brzinaVjetra;
    private float relativnaVlaznost;

    public Timestamp getTs() {
        return ts;
    }

    public void setTs(Timestamp ts) {
        this.ts = ts;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public LocalTime getVrijeme() {
        return vrijeme;
    }

    public void setVrijeme(LocalTime vrijeme) {
        this.vrijeme = vrijeme;
    }

    public float getZracenje() {
        return zracenje;
    }

    public void setZracenje(float zracenje) {
        this.zracenje = zracenje;
    }

    public float getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(float temperatura) {
        this.temperatura = temperatura;
    }

    public float getSmjerVjetra() {
        return smjerVjetra;
    }

    public void setSmjerVjetra(float smjerVjetra) {
        this.smjerVjetra = smjerVjetra;
    }

    public float getBrzinaVjetra() {
        return brzinaVjetra;
    }

    public void setBrzinaVjetra(float brzinaVjetra) {
        this.brzinaVjetra = brzinaVjetra;
    }

    public float getRelativnaVlaznost() {
        return relativnaVlaznost;
    }

    public void setRelativnaVlaznost(float relativnaVlaznost) {
        this.relativnaVlaznost = relativnaVlaznost;
    }

    public DHMZObradenoDataset(String[]csv){
            this.setDatum(LocalDate.parse(Reader.parseDateTime(csv[0])[0]));
            this.setVrijeme(LocalTime.parse(Reader.parseDateTime(csv[0])[1]));

            this.setTs(Timestamp.valueOf(LocalDate.parse(Reader.parseDateTime(csv[0])[0]) + " " + Reader.parseDateTime(csv[0])[1] ));
            this.getTs().setTime(this.getTs().getTime() + 7200000 );
        try {
            this.setZracenje(Float.parseFloat(csv[1]));
        }catch(NumberFormatException nfe){
            //ako je vrijednost "nan" ili
            this.setZracenje(-1);
        }
        try {
            this.setTemperatura(Float.parseFloat(csv[2]));
        }catch(NumberFormatException nfe){
            //ako je vrijednost "nan" ili
            this.setTemperatura(-1);
        }
        try {
            this.setSmjerVjetra(Float.parseFloat(csv[3]));
        }catch(NumberFormatException nfe){
            //ako je vrijednost "nan" ili
            this.setSmjerVjetra(-1);
        }
        try{
            this.setBrzinaVjetra(Float.parseFloat(csv[4]));
        }catch(NumberFormatException nfe){
            //ako je vrijednost "nan" ili
            this.setBrzinaVjetra(-1);
        }
        try {
            this.setRelativnaVlaznost(Float.parseFloat(csv[5]));
        }catch(NumberFormatException nfe){
            //ako je vrijednost "nan" ili
            this.setRelativnaVlaznost(-1);
        }
    }

    @Override
    public String toString() {
        return "DHMZobradeno{" +
                "datum=" + datum +
                ", vrijeme=" + vrijeme +
                ", zracenje=" + zracenje +
                ", temperatura=" + temperatura +
                ", smjerVjetra=" + smjerVjetra +
                ", brzinaVjetra=" + brzinaVjetra +
                ", relativnaVlaznost=" + relativnaVlaznost +
                '}';
    }
}
