package com.yuanyuanis.concurrente.feedback1;

import javax.swing.*;


public class ProgressBarSwingWorker extends SwingWorker<Void, Void> {

    private JProgressBar progressBar;
    private Integer numero;
    private JLabel progressText;

    private double decremento;

    private JButton buttonEjecutar;
    private JButton buttonCancelar;

    ProgressBarSwingWorker(JProgressBar progressBar, Integer numero, JLabel progressText, JButton buttonEjecutar, JButton buttonCancelar){
        this.progressBar = progressBar;
        this.numero = numero;
        this.progressText = progressText;
        this.buttonCancelar = buttonCancelar;
        this.buttonEjecutar = buttonEjecutar;

        decremento = 100.0 / numero;

    }

    @Override
    protected Void doInBackground() throws Exception {

        double valor = 100;
        int segundos = numero;
        while (valor >= 0){

            valor = valor - decremento;
            int valorRedondeado = (int) valor;
            if(valorRedondeado <=0) {
                valorRedondeado = 0;
            }

            progressBar.setValue(valorRedondeado);
            progressText.setText("Segundos: " + String.valueOf(segundos));
            segundos --;

            dormirUnPoco();
        }
        reset();
        return null;
    }

    private void reset() {
        buttonEjecutar.setEnabled(true);
        buttonCancelar.setEnabled(false);
        progressText.setText(null);
        JOptionPane.showMessageDialog(null, "La tarea a finalizado", "Task Completed", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void dormirUnPoco() {
        try {
            Thread.currentThread().sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Error");
            throw new RuntimeException(e);
        }
    }
}