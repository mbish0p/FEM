import static java.util.Arrays.sort;

public class Agregacja {

    double[][] globalH;
    double[][] globalC;
    double[] globalP;
    double[][] globalHBC;

    double[][] A;
    double[] B;
    double[] vectorTemp;

    public Agregacja(Element[] elements, Data data, Node[] nodes, ShapeFunction shapeFunction){

        globalH = new double[data.nL*data.nH][data.nH*data.nL];
        globalC = new double[data.nL*data.nH][data.nH*data.nL];
        globalHBC = new double[data.nL*data.nH][data.nH*data.nL];
        globalP = new double[data.nL*data.nH];

        for(int i = 0; i<(data.nH -1)*(data.nL-1); i++){

            Jacobian jacobian = new Jacobian(shapeFunction,elements,nodes,data,i,0);
            Jacobian jacobian1 = new Jacobian(shapeFunction,elements,nodes,data,i,1);
            Jacobian jacobian2 = new Jacobian(shapeFunction,elements,nodes,data,i,2);
            Jacobian jacobian3 = new Jacobian(shapeFunction,elements,nodes,data,i,3);

            bigH H = new bigH(jacobian,jacobian1,jacobian2,jacobian3);
            bigC C = new bigC(jacobian,jacobian1,jacobian2,jacobian3);

//            C.printBigC();
//            System.out.println();

            elements[i].printH_BC();;

            for(int j =0; j<4; j++){
                for(int k=0; k<4; k++){

                    globalH[elements[i].nodeID[j] -1][elements[i].nodeID[k] -1] += H.bigH[j][k];
                    globalC[elements[i].nodeID[j] -1][elements[i].nodeID[k] -1] += C.bigC[j][k];
                    globalHBC[elements[i].nodeID[j] -1][elements[i].nodeID[k] -1] += elements[i].H_BC[j][k];
                }
                globalP[elements[i].nodeID[j] -1] += elements[i].P[j];


            }
        }


        // ------------------------- calculation ---------------------------


        A = new double[data.nL*data.nH][data.nH*data.nL];
        B = new double[data.nH*data.nL];
        MetodaJacobiego metodaJacobiego;

        vectorTemp = new double[data.nH*data.nL];
        for(int i = 0; i<data.nL*data.nH; i++){

            vectorTemp[i] = data.initialTemp;
        }

        for(int i=0; i<data.nH*data.nL; i++){
            for(int j=0; j<data.nL*data.nH; j++){

                A[i][j] = globalH[i][j] + globalHBC[i][j] + globalC[i][j]/data.stepTime;

            }
        }

        double[] bTemp = new double[data.nL * data.nH];
        double[] tempTemp = new double[data.nL * data.nH];
        double time = data.stepTime;

        System.out.println();
        System.out.println();
        System.out.println("Time[s] \t MinTemp[s] \t MaxTemp[s]");

       while(time<= data.simulationTime) {


           for (int i = 0; i < data.nL * data.nH; i++) {

               bTemp[i] = 0;
           }

           for (int i = 0; i < data.nL * data.nH; i++) {
               for (int j = 0; j < data.nL * data.nH; j++) {

                   bTemp[i] += (globalC[i][j] / data.stepTime) * vectorTemp[j];
               }
           }

           for (int i = 0; i < data.nL * data.nH; i++) {

               B[i] = bTemp[i] + globalP[i];
           }

           metodaJacobiego = new MetodaJacobiego(data,A,B);
           for(int i=0; i<metodaJacobiego.getX1().length; i++){

               vectorTemp[i] = metodaJacobiego.getX1()[i];
               tempTemp[i] = metodaJacobiego.getX1()[i];
           }

           sort(tempTemp);
           System.out.println(time + "    " + tempTemp[0] + "     " + tempTemp[tempTemp.length-1]);
           time+= data.stepTime;
       }

    }

    public void printB(){

        System.out.println();
        System.out.println("Global B");
        for(int i=0; i<B.length; i++){

            System.out.print(B[i] + " ");
        }
    }
    public void printGlobalP(){

        System.out.println();
        System.out.println("Global P");
        for(int i=0; i<globalP.length; i++){

            System.out.print(globalP[i] + " ");
        }
    }

    public void printGlobalC() {
        System.out.println();
        System.out.println("Global C");
        for (int i = 0; i < globalC[0].length; i++) {
            for (int j = 0; j < globalC.length; j++) {
                {
                    System.out.print(globalC[i][j] + " ");
                    if (j == globalC[0].length - 1) System.out.println();

                }
            }
        }
    }

    public void printGlobalH() {
        System.out.println();
        System.out.println("Global H");
        for (int i = 0; i < globalH[0].length; i++) {
            for (int j = 0; j < globalH.length; j++) {
                {
                    System.out.print(globalH[i][j] + " ");
                    if (j == globalH[0].length - 1) System.out.println();
                }
            }
        }
    }

    public void printGlobalHBC() {
        System.out.println();
        System.out.println("Global HBC");
        for (int i = 0; i < globalHBC[0].length; i++) {
            for (int j = 0; j < globalHBC.length; j++) {
                {
                    System.out.print(globalHBC[i][j] + " ");
                    if (j == globalHBC[0].length - 1) System.out.println();
                }
            }
        }
    }

    public void printA() {
        System.out.println();
        System.out.println("Global H + C/dT");
        for (int i = 0; i < A[0].length; i++) {
            for (int j = 0; j < A.length; j++) {
                {
                    System.out.print(A[i][j] + " ");
                    if (j == A.length - 1) System.out.println();

                }
            }
        }
    }
}
