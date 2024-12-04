/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package studentmanagementsystem;


import javax.swing.SwingWorker;

public class ThreadingHelper {
    public static <T> void executeInBackground(SwingWorker<T, Void> worker) {
        worker.execute();
    }
}
