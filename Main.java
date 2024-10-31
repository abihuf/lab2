import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;
public class Main {
    public static PrintStream out = System.out;
    public static Scanner in = new Scanner(System.in);
    public static void main(String[] args) {
        int n = in.nextInt();
        int m = in.nextInt();
        int[][] a = new int[n][m];
        int c = 0;
        double sum = 0;

        //Ввод с консоли элементов массива размером NxM
        //Также вычисляем их количество и среднее арифметическое всех нечетных элементов массива
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                a[i][j] = in.nextInt();
                if (a[i][j] % 2 != 0) {
                    c += 1;
                    sum += a[i][j];
                }
            }
        }
        double ave = sum/c;

        //Сортировка каждого столбца массива по возрастанию методом пузырька
        int x;
        for (int k = 1; k < n; k++) {
            for (int i = n - 1; i > 0; i--) {
                for (int j = 0; j < m; j++) {
                    if (a[i][j] < a[i - 1][j]) {
                        x = a[i - 1][j];
                        a[i - 1][j] = a[i][j];
                        a[i][j] = x;
                    }
                }
            }
        }

        //Сортировка каждой строки массива по убыванию методом вставок
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < m; j++) {
                int current = a[i][j];
                int k = j;
                while (k > 0 && a[i][k - 1] < current) {
                    a[i][k] = a[i][k - 1];
                    k--;
                }
                a[i][k] = current;
            }
        }

        //Сортировка главной диагонали по возрастанию
        int min = 1000000000;
        if (n < m)
            min = n;
        else min = m;
        for (int k = 1; k < min; k++) {
            for (int i = 0; i < min - 1; i++) {
                if (a[i][i] > a[i + 1][i + 1]) {
                    x = a[i + 1][i + 1];
                    a[i + 1][i + 1] = a[i][i];
                    a[i][i] = x;
                }
            }
        }
        //Вывод зигзагом
        int counter = 0;
        int n0 = 0, m0 = 0;
        while (n0 < n) {
            if (n0 % 2 == 0) {
                out.print(a[n0][m0] + " ");
                m0 += 1;
                counter += 1;
            }
            if (counter == m) {
                n0 += 1;
                counter = 0;
                m0 -= 1;
                //если здесь написать out.println() выведем как новый массив
            }
            if (n0 % 2 != 0 && n0 < n) {
                out.print(a[n0][m0] + " ");
                m0 -= 1;
                counter += 1;
            }
            if (counter == m) {
                n0 += 1;
                counter = 0;
                m0 += 1;
                //если здесь написать out.println() выведем как новый массив
            }
        }

        out.println();

        //Замена всех чётных чисел в массиве на их отрицательные значения, а нечётные - на их квадраты + вывод массива
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (a[i][j] % 2 == 0)
                    a[i][j] = -a[i][j];
                else a[i][j] = (int) Math.pow(a[i][j], 2);
                out.print(a[i][j] + " ");
            }
            out.println();
        }
    }
}