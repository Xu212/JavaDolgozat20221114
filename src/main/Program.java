package main;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.TitledBorder;

public class Program {

    JPanel jpPin;
    JTextField jtfKod;
    ArrayList<JButton> gombSorrendben;
    String szoveg = "";

    public static void main(String[] args) {
        new Program();
    }

    public Program() {
        ini();
    }

    public void ini() {
        GUI();
    }

    public void GUI() {
        //2-es
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(410, 350);

        frame.setJMenuBar(menuBarKeszit());

        JTabbedPane jtp = new JTabbedPane();
        JPanel jpBejelent = new JPanel();
        JPanel jpJatek = new JPanel();

        //Bejelntkezés
        jtp.add("Bejelentkezés", jpBejelent);
        jpPin = new JPanel();
        jpPin.setBorder(new TitledBorder("Pin kód"));
        gombSorrendben = new ArrayList<JButton>();
        for (int i = 1; i < 10; i++) {
            jpPin.add(new JButton(Integer.toString(i)));
            gombSorrendben.add(new JButton(Integer.toString(i)));
        }
        jpPin.add(new JButton("0"));
        gombSorrendben.add(new JButton("0"));
        jpBejelent.add(jpPin);
        jpPin.setLayout(new GridLayout(4, 3));
        frame.add(jtp);

        JPanel jpBejBeallit = new JPanel();
        jpBejBeallit.setBorder(new TitledBorder("Beállítás"));
        jpBejelent.add(jpBejBeallit);
        JPanel jpKever = new JPanel();
        JCheckBox jcbKever = new JCheckBox();
        jpKever.add(jcbKever);
        jpKever.add(new JLabel("kever"));
        jpBejBeallit.add(jpKever);
        jpBejBeallit.add(new JLabel("kód:"));
        jtfKod = new JTextField();
        jpBejBeallit.add(jtfKod);
        jpBejBeallit.setLayout(new GridLayout(3, 1));
        jpBejelent.setLayout(new GridLayout(1, 2));

        //Játék
        jtp.add("Játék", jpJatek);
        JPanel jpAmoba = new JPanel();
        jpAmoba.setBorder(new TitledBorder("Amőba"));
        JPanel jpJatekBeallit = new JPanel();
        jpJatekBeallit.setBorder(new TitledBorder("Beállítás"));
        for (int i = 0; i < 9; i++) {
            jpAmoba.add(new JButton());
        }
        jpJatek.add(jpAmoba);
        jpJatek.add(jpJatekBeallit);
        jpAmoba.setLayout(new GridLayout(3, 3));
        JPanel jpX = new JPanel();
        JPanel jpO = new JPanel();
        JRadioButton jrbXkezd = new JRadioButton();
        JRadioButton jrbOkezd = new JRadioButton();
        jpX.add(jrbXkezd);
        jpX.add(new JLabel("'X' kezd"));
        jpO.add(jrbOkezd);
        jpO.add(new JLabel("'O' kezd"));
        String[] meretek = {"3*3", "4*4", "5*5"};
        JList jlMeret = new JList(meretek);
        JScrollPane jspMeret = new JScrollPane(jlMeret);
        jpJatekBeallit.add(jspMeret);
        jpJatekBeallit.add(jpX);
        jpJatekBeallit.add(jpO);
        //jlMeret.setModel(new DefaultListModel());
        jspMeret.setPreferredSize(new Dimension(30, 70));
        jlMeret.setPreferredSize(new Dimension(80, 30));
        jspMeret.setViewportView(jlMeret);
        jspMeret.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jspMeret.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jpJatekBeallit.setLayout(new GridLayout(3, 1));
        jpJatek.setLayout(new GridLayout(1, 2));
        jcbKever.addItemListener(new Kever());
        
        //szamAction
        gombIras();

        frame.setVisible(true);
    }

    public JMenuBar menuBarKeszit() {
        JMenuBar jmb = new JMenuBar();
        JMenu jmPrgm = new JMenu("Program");
        JMenu jmElrend = new JMenu("Játék  elrendezése");
        jmb.add(jmPrgm);
        jmb.add(jmElrend);
        JMenuItem jmiUjra = new JMenuItem("Újra");
        JMenuItem jmiKilep = new JMenuItem("Kilépés");
        jmPrgm.add(jmiUjra);
        jmPrgm.addSeparator();
        jmPrgm.add(jmiKilep);
        JMenuItem jmiVizsz = new JMenuItem("vízszintes");
        JMenuItem jmiFuggl = new JMenuItem("függőleges");
        jmElrend.add(jmiVizsz);
        jmElrend.add(jmiFuggl);
        return jmb;
    }
    
    class gombPinKattintas implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton gomb = (JButton)e.getSource();
            gomb.setBackground(Color.red);
            szoveg += gomb.getText();
            jtfKod.setText(szoveg);
         }
    
    }
    int keveres = 0;
    ArrayList<JButton> sorrendGomb = new ArrayList<JButton>();
    class Kever implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {
            JCheckBox jcbKever = (JCheckBox) e.getSource();
            Component[] raw = jpPin.getComponents();
            ArrayList<JButton> gombok = new ArrayList<JButton>();
            for (int i = 0; i < raw.length; i++) {
                if (raw[i] instanceof JButton) {
                    gombok.add((JButton) raw[i]);
                }
            if(keveres<10){
            sorrendGomb.add((JButton) raw[i]);
            keveres++;
            }
            }
            Collections.shuffle(gombok);
            if (jcbKever.isSelected()) {
                megjelenit(gombok);
            }
            else{
                System.out.println("asd");
                megjelenit(sorrendGomb);
            }
        }
        JButton szinGomb = new JButton();
        public void megjelenit(ArrayList<JButton> gombok){
            jpPin.removeAll();
            jpPin.revalidate();
            for (JButton gomb : gombok) {
                gomb.setBackground(szinGomb.getBackground());
                jpPin.add(gomb);
            }
        }

    }

    
    public void gombIras(){
        Component[] nyersGombok = jpPin.getComponents();
        for (int i = 0; i < nyersGombok.length; i++) {
            ((JButton)nyersGombok[i]).addActionListener(new gombPinKattintas());
        }
    }
}
