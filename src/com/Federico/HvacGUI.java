package com.Federico;



import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;


/**
 * Created by Federico on 12/4/2016.
 */
public class HvacGUI extends JFrame{
    private JPanel rootPanel;
    private JTextField addressField;
    private JTextField problemField;
    private JRadioButton furnaceRadioButton;
    private JRadioButton ACRadioButton;
    private JComboBox comboBox1;
    private JTextField ACModel;
    private JList <ServiceCall>UnresolvedList;
    private JButton addTicketButton;
    private JTextField ResolutionField;
    private JTextField FeeField;
    private JList ResolvedList;
    private JButton solveTicketButton;
    private JRadioButton checkThisWhenSubmittingRadioButton;
    private JButton quitButton;

    DefaultListModel UnresolvedListModel;
    DefaultListModel ResolvedListModel;

    public HvacGUI() {

        this.setContentPane(rootPanel);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        UnresolvedListModel = new DefaultListModel<>();
        UnresolvedList.setModel(UnresolvedListModel);
        ResolvedListModel = new DefaultListModel<>();
        ResolvedList.setModel(ResolvedListModel);

        String forcedAir = "Forced Air";
        String boiler = "Boiler";
        String Octopus = "Octopus-Type";

        pack();
        setVisible(true);
        ResolutionField.setEditable(false);
        FeeField.setEditable(false);

        ButtonGroup bg = new ButtonGroup();
        bg.add(this.furnaceRadioButton);
        bg.add(this.ACRadioButton);

        checkThisWhenSubmittingRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkThisWhenSubmittingRadioButton.isSelected()){
                    addressField.setEditable(false);
                    problemField.setEditable(false);
                    ResolutionField.setEditable(true);
                    FeeField.setEditable(true);
                }else{
                    ResolutionField.setText("");
                    FeeField.setText("");
                    ResolutionField.setEditable(false);
                    FeeField.setEditable(false);
                }
            }
        });

        furnaceRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ACModel.setEditable(false);
                ACModel.setText("");
                comboBox1.removeAllItems();
                comboBox1.addItem(forcedAir);
                comboBox1.addItem(boiler);
                comboBox1.addItem(Octopus);
            }
        });
        ACRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ACModel.setEditable(true);
                comboBox1.removeAllItems();
            }
        });
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        addTicketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(furnaceRadioButton.isSelected()){
                    Furnace f = new Furnace(addressField.getText(),problemField.getText(),new Date(),comboBox1.getSelectedIndex()+1);
                    UnresolvedListModel.addElement(f);
                }else if (ACRadioButton.isSelected()){
                    CentralAC cac = new CentralAC(addressField.getText(),problemField.getText(),new Date(),ACModel.getText());
                    UnresolvedListModel.addElement(cac);
                }
            }
        });
        solveTicketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                ResolvedListModel.addElement(UnresolvedList.getSelectedValue());
                UnresolvedListModel.removeElement(UnresolvedList.getSelectedValue());
            }
        });
    }
}
