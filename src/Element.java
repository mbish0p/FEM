import static java.lang.Math.sqrt;

public class Element {


    int id;
    int[] nodeID = new int[4];
    Data data;

    boolean Pow1 = false;
    boolean Pow2 = false;
    boolean Pow3 = false;
    boolean Pow4 = false;
    double dlugosc;
    int alfa;
    int ambientTemp ;

    double[][] H_BC = new double[4][4];
    double[] P = new double[4];

    public Element(int id, int[] nodeID,Data data ){

        this.id = id;
        this.nodeID = nodeID;
        this.data = data;
        this.alfa = data.alfa;
        this.ambientTemp = data.ambientTemp;

        for(int i = 0; i<4; i++){
            for(int j = 0; j <4; j++){

                if(nodeID[i] == 1 + data.nH*j) Pow1 = true;
                if(nodeID[i] >= data.nH*data.nL - data.nH +1) Pow2 = true;
                if(nodeID[i] == data.nH * j) Pow3 = true;
                if(nodeID[i] <= data.nH) Pow4 = true;
            }

        }

        //setH_BC_and_P();
    }

    public void setH_BC_and_P(){

        double ksi1;
        double eta1;
        double ksi2;
        double eta2;
        double[][] tab1Pc1 = new double[4][4];
        double[][] tab2Pc1 = new double[4][4];
        double[][] tab1Pc2 = new double[4][4];
        double[][] tab2Pc2 = new double[4][4];
        double[][] tab1Pc3 = new double[4][4];
        double[][] tab2Pc3 = new double[4][4];
        double[][] tab1Pc4 = new double[4][4];
        double[][] tab2Pc4 = new double[4][4];
        double[][] sumPow1 = new double[4][4];
        double[][] sumPow2 = new double[4][4];
        double[][] sumPow3 = new double[4][4];
        double[][] sumPow4 = new double[4][4];


        double[] P1_Pc1 = new double[4];
        double[] P2_Pc1 = new double[4];
        double[] P1_Pc2 = new double[4];
        double[] P2_Pc2 = new double[4];
        double[] P1_Pc3 = new double[4];
        double[] P2_Pc3 = new double[4];
        double[] P1_Pc4 = new double[4];
        double[] P2_Pc4 = new double[4];
        double[] sumP1 = new double[4];
        double[] sumP2 = new double[4];
        double[] sumP3 = new double[4];
        double[] sumP4 = new double[4];




        ShapeFunction shapeFunction = new ShapeFunction(data);

        if(Pow1) {

            eta1 = -1;
            eta2 = -1;
            ksi1 = -1 / sqrt(3);
            ksi2 = 1 / sqrt(3);
            dlugosc = data.L/(data.nL-1);

//            double N1_Pc1 = 0.25*(1-ksi1)*(1-eta1);
//            double N2_Pc1 = 0.25*(1-ksi1)*(1-eta1);
//            double N3_Pc1 = 0.25*(1-ksi1)*(1-eta1);
//            double N4_Pc1 = 0.25*(1-ksi1)*(1-eta1);
//
//            double N1_Pc2 = 0.25*(1-ksi2)*(1-eta2);
//            double N2_Pc2 = 0.25*(1-ksi2)*(1-eta2);
//            double N3_Pc2 = 0.25*(1-ksi2)*(1-eta2);
//            double N4_Pc2 = 0.25*(1-ksi2)*(1-eta2);


            double[] N_Pc1 = shapeFunction.calculateShapeFunction(ksi1, eta1);
            double[] N_Pc2 = shapeFunction.calculateShapeFunction(ksi2, eta2);

            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {

                    tab1Pc1[i][j] = N_Pc1[i] * N_Pc1[j] * alfa;
                    tab2Pc1[i][j] = N_Pc2[i] * N_Pc2[j] * alfa;
                    sumPow1[i][j] = dlugosc / 2 * (tab1Pc1[i][j] + tab2Pc1[i][j]);
                }
                P1_Pc1[i] = N_Pc1[i] * alfa * ambientTemp;
                P2_Pc1[i] = N_Pc2[i] * alfa * ambientTemp;
                sumP1[i] = dlugosc / 2 * (P1_Pc1[i] + P2_Pc1[i]);
            }

//            for(int i = 0; i<4; i++){
//                for(int j=0; j<4;j++){
//
//                    System.out.print((sumPow1[i][j]) + " ");
//                }
//                System.out.println();
//            }
        }
            if(Pow2){

                eta1 = -1/sqrt(3);
                eta2 = 1/sqrt(3);
                ksi1 = 1;
                ksi2 = 1;
                dlugosc = data.H/(data.nH-1);

                double[] N_Pc1 = shapeFunction.calculateShapeFunction(ksi1,eta1);
                double[] N_Pc2 = shapeFunction.calculateShapeFunction(ksi2,eta2);

                for(int i =0; i<4; i++){
                    for(int j=0; j<4;j++){

                        tab1Pc2[i][j] = N_Pc1[i]*N_Pc1[j]*alfa;
                        tab2Pc2[i][j] = N_Pc2[i]*N_Pc2[j]*alfa;
                        sumPow2[i][j] = dlugosc/2 *(tab1Pc2[i][j] + tab2Pc2[i][j]);
                    }
                    P1_Pc2[i] = N_Pc1[i] *alfa * ambientTemp;
                    P2_Pc2[i] = N_Pc2[i] *alfa*ambientTemp;
                    sumP2[i] = dlugosc/2*(P1_Pc2[i] + P2_Pc2[i]);
                }

            }

            if(Pow3){

                eta1 = 1;
                eta2 = 1;
                ksi1 = 1/sqrt(3);
                ksi2 = -1/sqrt(3);
                dlugosc = data.L/(data.nL-1);;

                double[] N_Pc1 = shapeFunction.calculateShapeFunction(ksi1,eta1);
                double[] N_Pc2 = shapeFunction.calculateShapeFunction(ksi2,eta2);

                for(int i =0; i<4; i++){
                    for(int j=0; j<4;j++){

                        tab1Pc3[i][j] = N_Pc1[i]*N_Pc1[j]*alfa;
                        tab2Pc3[i][j] = N_Pc2[i]*N_Pc2[j]*alfa;
                        sumPow3[i][j] = dlugosc/2 *(tab1Pc3[i][j] + tab2Pc3[i][j]);
                    }
                    P1_Pc3[i] = N_Pc1[i] *alfa * ambientTemp;
                    P2_Pc3[i] = N_Pc2[i] *alfa*ambientTemp;
                    sumP3[i] = dlugosc/2*(P1_Pc3[i] + P2_Pc3[i]);
                }

            }

            if(Pow4){

                eta1 = 1/sqrt(3);
                eta2 = -1/sqrt(3);
                ksi1 = -1;
                ksi2 = -1;
                dlugosc = data.H/(data.nH-1);

                double[] N_Pc1 = shapeFunction.calculateShapeFunction(ksi1,eta1);
                double[] N_Pc2 = shapeFunction.calculateShapeFunction(ksi2,eta2);

                for(int i =0; i<4; i++){
                    for(int j=0; j<4;j++){

                        tab1Pc4[i][j] = N_Pc1[i]*N_Pc1[j]*alfa;
                        tab2Pc4[i][j] = N_Pc2[i]*N_Pc2[j]*alfa;
                        sumPow4[i][j] = dlugosc/2 *(tab1Pc4[i][j] + tab2Pc4[i][j]);
                    }
                    P1_Pc4[i] = N_Pc1[i] *alfa * ambientTemp;
                    P2_Pc4[i] = N_Pc2[i] *alfa*ambientTemp;
                    sumP4[i] = dlugosc/2*(P1_Pc4[i] + P2_Pc4[i]);
                }


            }

            double v1 =0,v2=0,v3=0,v4=0;

            if(Pow1) v1=1;
            if(Pow2) v2=1;
            if(Pow3) v3=1;
            if(Pow4) v4=1;


            for(int i=0; i<4; i++){
                for(int j=0; j<4;j++){

                    H_BC[i][j] = v1*sumPow1[i][j] + v2*sumPow2[i][j] +v3*sumPow3[i][j] +v4*sumPow4[i][j];

                }

                P[i] = v1*sumP1[i] +v2*sumP2[i] +v3*sumP3[i] +v4*sumP4[i] ;
            }




    }





//    public void isPowIsBC(Data data){
//
//        for(int i = 0; i<4; i++){
//            for(int j = 0; j <4; j++){
//
//                if(nodeID[i] == 1 + data.nH*j) Pow1 = true;
//                if(nodeID[i] >= data.nH*data.nL - data.nH +1) Pow2 = true;
//                if(nodeID[i] == data.nH * j) Pow3 = true;
//                if(nodeID[i] <= data.nH) Pow4 = true;
//            }
//
//        }
//
//    }

    public void printPows(){

        System.out.println("Pow1 " + Pow1);
        System.out.println("Pow2 " + Pow2);
        System.out.println("Pow3 " + Pow3);
        System.out.println("Pow4 " + Pow4);

    }

    public void printH_BC(){
        for(int i = 0; i<4; i++){
            for(int j=0; j<4;j++){

                System.out.print((H_BC[i][j]) + " ");
            }
            System.out.println();
        }
    }

    public void printP(){

        for(int i=0; i<4; i++){
            System.out.println(P[i] + " ");
        }
    }

    public void printElement(){

        System.out.println("ID =" + this.id);
        System.out.println("Node 0 -" + this.nodeID[0]);
        System.out.println("Node 1 -" + this.nodeID[1]);
        System.out.println("Node 2 -" + this.nodeID[2]);
        System.out.println("Node 3 -" + this.nodeID[3]);
    }

}
