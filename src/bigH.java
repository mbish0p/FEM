public class bigH {

//    double[][] skladowaH1 = new double[4][4];
//    double[][] skladowaH2 = new double[4][4];
//    double[][] skladowaH3 = new double[4][4];
//    double[][] skladowaH4 = new double[4][4];
//
    double[][] bigH = new double[4][4];

    public bigH(Jacobian j1, Jacobian j2, Jacobian j3, Jacobian j4){

        for(int i = 0; i<4; i++){

            for(int j = 0; j<4; j++){

                bigH[i][j] = j1.skladoweH[i][j] + j2.skladoweH[i][j] + j3.skladoweH[i][j] + j4.skladoweH[i][j];
            }
        }


    }

    public void printBigH(){

        for(int i = 0; i<4; i++){
            for(int j=0; j<4;j++){

                System.out.print((bigH[i][j]) + " ");
            }
            System.out.println();
        }

    }


}
