
import javax.sound.midi.*;
import javax.swing.*;

class MiniPlayer {
    static JFrame ramka = new JFrame("Rectangles appearing");
    static Prostokaty panel;

   MiniPlayer(){
       begin();
   }

    public void konfigurujGUI() {
        ramka.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new Rectangles();
        ramka.setContentPane(panel);
        ramka.setBounds(30, 30, 600, 600);
        ramka.setVisible(true);
    } 

    public void begin() {
        konfigurujGUI();
        try {
            Sequencer sekwenser = MidiSystem.getSequencer();
            sekwenser.open();
            sekwenser.addControllerEventListener(panel, new int[]{127});
            Sequence sekw = new Sequence(Sequence.PPQ, 4);
            Track sciezka = sekw.createTrack();
            int r = 0;
            for (int i = 5; i < 60; i += 4) {
                r = (int) ((Math.random() * 50) + 1);
                sciezka.add(tworzZdarzenie(144, 1, r, 100, i));
                sciezka.add(tworzZdarzenie(176, 1, 127, 0, i));
                sciezka.add(tworzZdarzenie(128, 1, r, 100, i + 2));
            } 
            sekwenser.setSequence(sekw);
            sekwenser.setTempoInBPM(220);
            sekwenser.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    } 
    public MidiEvent tworzZdarzenie(int plc, int kanal, int jeden, int dwa, int takt) {
        MidiEvent zdarzeni = null;
        try {
            ShortMessage a = new ShortMessage();
            a.setMessage(plc, kanal, jeden, dwa);
            zdarzeni = new MidiEvent(a, takt);

        } catch(Exception e) { }
        return zdarzeni;
    }
}
