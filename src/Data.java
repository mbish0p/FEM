import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static java.lang.Math.sqrt;

public class Data {

        double H,L;
        int nH,nL;
        int nW = nH * nL;
        int nE = (nH-1)*(nL-1);
        double[][] PC = new double[4][4];
        double[][] wsp = new double[2][2];
        int[][]waga = new int[2][2];

        int lambda;
        int alfa;
        int ambientTemp;
        double initialTemp;
        double stepTime;
        double simulationTime;


        public Data(String path) throws FileNotFoundException {
            File file = new File(path);
            Scanner scanner = new Scanner(file);

            String string = scanner.nextLine();
            H = Double.parseDouble(string);

            string = scanner.nextLine();
            L = Double.parseDouble(string);

            string = scanner.nextLine();
            nH = Integer.parseInt(string);

            string = scanner.nextLine();
            nL = Integer.parseInt(string);

            string = scanner.nextLine();
            lambda = Integer.parseInt(string);

            string = scanner.nextLine();
           alfa = Integer.parseInt(string);

            string = scanner.nextLine();
            ambientTemp = Integer.parseInt(string);

            string = scanner.nextLine();
            initialTemp = Integer.parseInt(string);

            string = scanner.nextLine();
            stepTime = Integer.parseInt(string);

            string = scanner.nextLine();
            simulationTime = Integer.parseInt(string);

            wsp[0][0] = -1/(sqrt(3));
            wsp[0][1] = 1/(sqrt(3));

            waga[0][0] = 1;
            waga[0][1] = 1;

            // --- PC 1 ---
            PC[0][0] = wsp[0][0];
            PC[0][1] = wsp[0][0];

            // --- PC 2 ---
            PC[1][0] = wsp[0][1];
            PC[1][1] = wsp[0][0];

            // --- PC 3 ---
            PC[2][0] = wsp[0][1];
            PC[2][1] = wsp[0][1];

            // --- PC 4 ---
            PC[3][0] = wsp[0][0];
            PC[3][1] = wsp[0][1];



            System.out.println(H);
            System.out.println(L);
            System.out.println(nH);
            System.out.println(nL);

        }
}
