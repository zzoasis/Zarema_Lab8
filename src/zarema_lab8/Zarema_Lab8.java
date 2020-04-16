
package zarema_lab8;

import java.util.concurrent.CyclicBarrier;

public class Zarema_Lab8 {

    
    public static void main(String[] args) throws InterruptedException {
        final int numSolutions = 3; // Количество потоков-задач
        float[] arraySolutions = new float[numSolutions];  // Массив вычисленных значений заданий
        
        // Объект-вычислитель значений
        SolvingProblem solvingProblem = new SolvingProblem(arraySolutions); 
        
        // Финальный поток для подитоживания всех результатов вычислений
        FinalThread finalThread = new FinalThread(arraySolutions); 
        
         // Потоковый барьер, который вызовет finalThread, когда выполнятся все задачи (их количесвто numSolutions)
        CyclicBarrier cyclicBarrier = new CyclicBarrier(numSolutions, finalThread);

        System.out.println("Laboratory work");
        System.out.println("Task: C = A ? B ");
        System.out.println("Активирован потоковый барьер для " + numSolutions + " потоков ...");

        // Запускаем нужное количество потоков-решиней задачи
        for (int numSolution = 1; numSolution <= numSolutions; numSolution++) { 
            new TaskThread(cyclicBarrier, solvingProblem, numSolution); // Запуск потоков-задач
            System.out.println("Запущен поток " + numSolution + " ...");
        }
        
        System.out.println("Основной поток программы переводится в сон с ожиданием выполнения всех задач...");
        
        while (!finalThread.completion) { // Пока не завершен финальный поток - ожидание
            Thread.sleep(10); // Ожидание этого потока в 10 мс
        }
        
        System.out.println("Основной поток и программа успешно завершены!!!");
    } 
    
    
}
