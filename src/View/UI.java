package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class UI extends JFrame {
    public UI() {
        CreateUI();
        attachEventHandlers();
    }

    JPanel mainPanel;
    JLabel lba, lbb, lbc, lbresult;
    JTextField tfa, tfb, tfc, tfresult;
    JButton btnS, btnP;

    public void CreateUI() {
        this.setDefaultCloseOperation(3);

        this.setLayout(new GridBagLayout());

        // main panel
        mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(300,300));
        mainPanel.setLayout(new GridLayout(6, 2, 20, 20));

        // (0,0)
        lba = new JLabel("Độ dài cạnh a: ");
        lba.setPreferredSize(new Dimension(120,20));
        mainPanel.add(lba);

        // (0,1)
        tfa = new JTextField();
        tfa.setPreferredSize(new Dimension(120,20));
        mainPanel.add(tfa);

        // (1,0)
        lbb = new JLabel("Độ dài cạnh b: ");
        lbb.setPreferredSize(new Dimension(120,20));
        mainPanel.add(lbb);

        // (1,1)
        tfb = new JTextField();
        tfb.setPreferredSize(new Dimension(120,20));
        mainPanel.add(tfb);

        // (2,0)
        lbc = new JLabel("Độ dài cạnh b: ");
        lbc.setPreferredSize(new Dimension(120,20));
        mainPanel.add(lbc);

        // (2,1)
        tfc = new JTextField();
        tfc.setPreferredSize(new Dimension(120,20));
        mainPanel.add(tfc);

        // (3,0)
        mainPanel.add((new Button())).setVisible(false);    // skip

        // (3,1)
        btnS = new JButton("Tính diện tích");
        btnS.setPreferredSize(new Dimension(120,20));
        mainPanel.add(btnS);

        // (4,0)
        mainPanel.add(new Button()).setVisible(false);    // skip

        // (4,1)
        btnP = new JButton("Tính chu vi");
        btnP.setPreferredSize(new Dimension(120,20));
        mainPanel.add(btnP);

        // (5,0)
        lbresult = new JLabel("Kết quả: ");
        lbresult.setPreferredSize(new Dimension(120,20));
        mainPanel.add(lbresult);

        // (6,1)
        tfresult = new JTextField();
        tfresult.setPreferredSize(new Dimension(120,20));
        tfresult.setEditable(false);
        mainPanel.add(tfresult);

        this.add(mainPanel);

        this.setSize(400, 400);
        this.setVisible(true);
    }

    private void attachEventHandlers() {

        ActionListener bt = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object source = e.getSource();

                double a = Double.parseDouble(tfa.getText());
                double b = Double.parseDouble(tfb.getText());
                double c = Double.parseDouble(tfc.getText());

                if (a+b <= c || a+c <= b || b+c <= a) {
                    tfresult.setText("Khong phai tam giac.");
                    return;
                }

                if (((JButton)source).getText().equals("Tính diện tích")) {
                    double p = (a + b + c) / 2;
                    tfresult.setText(Double.toString(Math.sqrt(p * (p-a) * (p-b) * (p-c))));
                }

                else {
                    tfresult.setText(Double.toString(a + b + c));
                }
            }
        };

        btnP.addActionListener(bt);
        btnS.addActionListener(bt);
    }
}
