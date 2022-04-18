
import javax.sound.midi.*;
import javax.swing.*;
import java.awt.*;

public class Prostokaty extends JPanel implements ControllerEventListener {

    boolean komunikat = false;
    public void controlChange(ShortMessage zdarzenie) {
        komunikat = true;
        repaint();
    }
    public void paintComponent(Graphics g) {
        if (komunikat) {
            Graphics2D g2 = (Graphics2D) g;
            int c = (int) (Math.random() * 250);
            int z = (int) (Math.random() * 250);
            int n = (int) (Math.random() * 250);
            g.setColor(new Color(c,z,n));
            int wys = (int) ((Math.random() * 320) + 70);
            int szer = (int) ((Math.random() * 320) + 70);
            int x = (int) ((Math.random() * 100) + 30);
            int y = (int) ((Math.random() * 100) + 30);
            g.fillRect(x,y,wys,szer);
            komunikat = false;
        } 
    } 
}
