package com.yuanyuanis.concurrente.feedback1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Exercice1 extends JFrame {

    private JButton buttonEjecutar;
    private JButton buttonCancelar;

    private JLabel progressText;
    private JLabel textError;
    private JProgressBar progressBar;
    private JSeparator separator;
    private JTextField inputText;


    private JLabel titulo;

    private  ProgressBarSwingWorker progressBarTask;

    public Exercice1() {
        initComponents();
    }


    private void ejecutar(ActionEvent evt) {

        // Validamos que es un número válido.
        if(esElInputTextValido()){

            textError.setText(null);
            buttonCancelar.setEnabled(true);
            buttonEjecutar.setEnabled(false);

            progressBarTask = new ProgressBarSwingWorker(progressBar, Integer.valueOf(inputText.getText()), progressText, buttonEjecutar, buttonCancelar);

            progressBarTask.execute();


        } else{
            textError.setForeground(Color.RED);
            textError.setText("Escribe un valor numérico entre 2 y 100");
        }
    }

    private void cancelar(ActionEvent evt) {
        buttonCancelar.setEnabled(false);
        buttonEjecutar.setEnabled(true);

        if (progressBarTask.cancel(true))
            progressBar.setValue(0);

    }

    private boolean esElInputTextValido() {
        if(inputText.getText().chars().allMatch( Character::isDigit )){

            Integer numero = Integer.valueOf(inputText.getText());
            if(numero >= 2 && numero <= 100){
                return  true;
            }
        }
        return false;
    }




    private void initComponents() {

        buttonEjecutar = new JButton();
        buttonCancelar = new JButton();
        progressBar = new JProgressBar();
        progressText = new JLabel();
        inputText = new JTextField();
        separator = new JSeparator();
        textError = new JLabel();
        titulo = new JLabel();

        buttonEjecutar.setText("Ejecutar");
        buttonCancelar.setText("Cancelar");

        titulo.setText("Exercice 1 Progress Bar.");
        titulo.setFont(new Font("Arial", Font.PLAIN, 20));

        buttonCancelar.setEnabled(false);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        buttonCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                cancelar(evt);
            }
        });

        buttonEjecutar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                ejecutar(evt);
            }
        });

        

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap(15, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(inputText, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(textError))
                                                .addGap(323, 323, 323)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(buttonEjecutar, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(buttonCancelar, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE))
                                                .addGap(81, 81, 81))
                                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(progressText)
                                                .addGap(340, 340, 340))
                                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(separator, GroupLayout.PREFERRED_SIZE, 705, GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap())
                                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(progressBar, GroupLayout.PREFERRED_SIZE, 546, GroupLayout.PREFERRED_SIZE)
                                                .addGap(88, 88, 88))))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(241, 241, 241)
                                                .addComponent(titulo)
                                                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(titulo)
                                .addGap(34, 34, 34)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(buttonEjecutar)
                                        .addComponent(inputText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(31, 31, 31)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(buttonCancelar)
                                        .addComponent(textError))
                                .addGap(43, 43, 43)
                                .addComponent(separator, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(progressText)
                                .addGap(18, 18, 18)
                                .addComponent(progressBar, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(138, Short.MAX_VALUE))
        );

        pack();
    }


    public static void main(String args[]) {

        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Exercice1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Exercice1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Exercice1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Exercice1.class.getName()).log(Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Exercice1().setVisible(true);
            }
        });
    }
}