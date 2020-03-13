public class bigC {

    double[][] bigC = new double[4][4];

    public bigC(Jacobian j1, Jacobian j2, Jacobian j3, Jacobian j4){

        for(int i = 0; i<4; i++){

            for(int j = 0; j<4; j++){

                bigC[i][j] = j1.skladowaC[i][j] + j2.skladowaC[i][j] + j3.skladowaC[i][j] + j4.skladowaC[i][j];
            }
        }


    }

    public void printBigC(){

        for(int i = 0; i<4; i++){
            for(int j=0; j<4;j++){

                System.out.print((bigC[i][j]) + " ");
            }
            System.out.println();
        }

    }
}
