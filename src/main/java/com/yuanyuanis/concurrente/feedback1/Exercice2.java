package com.yuanyuanis.concurrente.feedback1;

import static java.lang.System.out;

/**
 * Realiza una aplicación de texto que lance dos hilos de forma que el segundo se ejecute mientras dure la ejecución del primero
 */
public class Exercice2 {


    public static void main(String ...args) throws InterruptedException {

        Runnable tarea2 = getRunnable();

        Thread hilo2 = new Thread(tarea2, "TAREA 2");
        Thread hilo1 = new Thread(new Tarea1(hilo2), "TAREA 1");

        hilo1.start();
    }

    private static Runnable getRunnable() {
        Runnable tarea2 = () -> {
            try {

                out.println(Thread.currentThread().getName() + " ------------- ha empezado su TRABAJO");
                simularHacerUnTrabajo(200);
                out.println("\n"+Thread.currentThread().getName() + " ------------- ha finalizado su TRABAJO");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };
        return tarea2;
    }


    static class Tarea1 implements Runnable{

        private final Thread hiloDependiente;

        Tarea1(Thread hiloDependiente) {
            this.hiloDependiente = hiloDependiente;
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i < 10; i++) {
                    simularHacerUnTrabajo(68);              // Tiempo de espera para hacer la aplicación más legible
                    out.printf("%s haciendo mi trabajo ...%d%n", Thread.currentThread().getName(), i);

                    if(i == 5){
                        hiloDependiente.start();
                        hiloDependiente.join();           // Este hilo depende de que el otro complete su tarea para continuar aquí.
                    }
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            out.println(Thread.currentThread().getName() + " Finalize mi trabajo.");
        }
    }

    private static void simularHacerUnTrabajo(int milisegundos) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            Thread.sleep(milisegundos);                                  // Tiempo de espera para hacer la aplicación más legible
            out.print(".");
        }
    }
}
