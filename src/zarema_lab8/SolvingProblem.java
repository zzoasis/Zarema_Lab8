
package zarema_lab8;

import java.util.Scanner;
import java.util.concurrent.locks.ReentrantLock;

public class SolvingProblem {
     ReentrantLock reentrantLock = new ReentrantLock(); // "Входящий замок" потоков
    float[] arraySolutions; // Массив вычисленных значений заданий

    SolvingProblem(float[] arraySolutions) { // Инициализация решателя заданий
        this.arraySolutions = arraySolutions; // Получаем доступ к внешнему массиву вычисленных значений заданий
    }

     boolean doExpr(int numSolution) { // Метод решения заданий
        reentrantLock.lock(); // Блокировка других потоков

        try {
            System.out.println("Поток " + numSolution + " успешно захватил управление решателем заданий ...");

            // ------------------------------------------------------- АЛГОРИТМ РЕШЕНИЯ ВАРИАНТА ЗАДАНИЯ ---
            Scanner sc = new Scanner(System.in); // Подключение к консоли
            System.out.print("Введите A: "); // Вывод значения
            float a = sc.nextFloat(); // Ввод с консоли вещественного значения
            System.out.print("Введите B: "); // Вывод вопроса
            float b = sc.nextFloat();// Ввод с консоли вещественного значения
            System.out.print("Введите знак ? : "); // Вывод вопроса
            boolean c = sc.hasNext();
              while (sc.hasNext()) {
                String task = sc.next();
                switch (task) {
                    case "+":
                        System.out.println(a + b);
                        break;
                    case "-":
                        System.out.println(a - b);
                        break;
                    case "/":
                        System.out.println(a / b);
                        break;
                    case "%":
                        System.out.println(a % b);
                        break;
                    case "==":
                        System.out.println(a == b);
                        break;
                    case ">":
                        System.out.println(a > b);
                        ;
                        break;
                    case "<":
                        System.out.println(a < b);
                        break;
                    default:
                  
                          System.out.println("Введите математический знак!");
                }
                break;
            }
            System.out.println("Знак будет заменен на * и передан в финальный поток");  
            float d = a*b;
            arraySolutions[numSolution - 1] = d;
            
            System.out.println("Решение задания " + numSolution + " было успешно получено и передано в основной поток!");
        } catch (Exception e) {
            arraySolutions[numSolution - 1] = 0; // Сохранение решения в результирующем массиве при ошибке расчета
        } finally {
            System.out.println("Поток " + numSolution + " отключился от управления решателем заданий.");
            System.out.println();
    
            reentrantLock.unlock(); // Снятие блокировки "замка" на разрешения работы других потоков
        }
         return false;

    }
}

