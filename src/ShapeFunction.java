public class ShapeFunction {

    double ksi, eta;
    double[][] shapeFunction;
    double[][] dNdKsi;
    double[][] dNdEta;



    public ShapeFunction(Data data){

        shapeFunction = new double[4][4];
        dNdEta = new double[4][4];
        dNdKsi = new double[4][4];

        // -------------------- Funkcje kształtu -------------------------------

        for(int i =0; i<4; i++){

            ksi = data.PC[i][0];
            eta = data.PC[i][1];

            //System.out.println("Ksi " + ksi);
            //System.out.println("Eta " + eta);

            for(int j=0; j<4;j++){

               if(j==0) shapeFunction[i][j] = 0.25*(1-ksi)*(1-eta);
               if(j==1) shapeFunction[i][j] = 0.25*(1+ksi)*(1-eta);
               if(j==2) shapeFunction[i][j] = 0.25*(1+ksi)*(1+eta);
               if(j==3) shapeFunction[i][j] = 0.25*(1-ksi)*(1+eta);
            }
        }


    // --------------------Pochodna po ksi ----------------------

        for(int i=0; i<4; i++){
            for(int j=0;j<4;j++){

                if(j==0) dNdKsi[i][j] = -0.25*(1-data.PC[i][1]);
                if(j==1) dNdKsi[i][j] = 0.25*(1-data.PC[i][1]);
                if(j==2) dNdKsi[i][j] = 0.25*(1+data.PC[i][1]);
                if(j==3) dNdKsi[i][j] = -0.25*(1+data.PC[i][1]);
            }
        }

    // -------------------Pochodna po eta -----------------------

        for(int i=0; i<4; i++){
            for(int j=0;j<4;j++){

                if(j==0) dNdEta[i][j] = -0.25*(1-data.PC[i][0]);
                if(j==1) dNdEta[i][j] = -0.25*(1+data.PC[i][0]);
                if(j==2) dNdEta[i][j] = 0.25*(1+data.PC[i][0]);
                if(j==3) dNdEta[i][j] = 0.25*(1-data.PC[i][0]);


            }
        }

    }

    public double[] calculateShapeFunction(double ksi1, double eta1){

        double[] tab = new double[4];
        tab[0] = 0.25*(1-ksi1)*(1-eta1);
        tab[1] = 0.25*(1+ksi1)*(1-eta1);
        tab[2] = 0.25*(1+ksi1)*(1+eta1);
        tab[3] = 0.25*(1-ksi1)*(1+eta1);

        return tab;
    }

    public void printShapeFunction(){
        System.out.println("");
        System.out.println("SHAPE FUNCTIONS:");
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4 ; j++) {
                System.out.print(shapeFunction[i][j] + "   ");
                if (j == shapeFunction[0].length -1 ) System.out.println("");
            }

        }
    }

    public void printdNdKSI(){
        System.out.println("");
        System.out.println("dNdKSI: ");
        for (int i = 0; i < dNdKsi.length; i++) {
            for (int j = 0; j < dNdKsi[0].length /*4*/ ; j++) {
                System.out.print(dNdKsi[i][j] + "   ");
                if (j == dNdKsi[0].length -1 ) System.out.println("");
            }

        }
    }

    public void printdNdEta(){
        System.out.println("");
        System.out.println("dNdEta: ");
        for (int i = 0; i < dNdEta.length; i++) {
            for (int j = 0; j < dNdEta[0].length /*4*/ ; j++) {
                System.out.print(dNdEta[i][j] + "   ");
                if (j == dNdEta[0].length -1 ) System.out.println("");
            }

        }


    }


}
