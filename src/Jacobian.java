public class Jacobian {

    double dXdKsi;
    double dXdEta;
    double dYdKsi;
    double dYdEta;
    int Pc;
    int numerElementu;
    int ro = 7800;
    int c = 700;

    double[][] Jacobian = new double[2][2];
    double detJ;
    double[][] odwrotnyJacobian = new double[2][2];
    double[] dNdx = new double[4];
    double[] dNdy = new double[4];

    double[][]dNdxdNdxT = new double[4][4];
    double[][]dNdydNdyT = new double[4][4];

    double[][]dNdxdNdxTdetJ = new double[4][4];
    double[][]dNdydNdyTdetJ = new double[4][4];

    double[][]skladoweH = new double[4][4];

    double[][] skladowaC = new double[4][4];


    public Jacobian(ShapeFunction shapeFunction, Element[] elements, Node[] nodes,Data data, int numerElementu, int Pc){

            this.Pc = Pc;
            this.numerElementu = numerElementu;

            dXdKsi = nodes[elements[numerElementu].nodeID[0]-1].x * shapeFunction.dNdKsi[Pc][0] +
                    nodes[elements[numerElementu].nodeID[1]-1].x * shapeFunction.dNdKsi[Pc][1]+
                    nodes[elements[numerElementu].nodeID[2]-1].x * shapeFunction.dNdKsi[Pc][2]+
                    nodes[elements[numerElementu].nodeID[3]-1].x * shapeFunction.dNdKsi[Pc][3];

            dXdEta = nodes[elements[numerElementu].nodeID[0]-1].x * shapeFunction.dNdEta[Pc][0]+
                    nodes[elements[numerElementu].nodeID[1]-1].x * shapeFunction.dNdEta[Pc][1]+
                    nodes[elements[numerElementu].nodeID[2]-1].x * shapeFunction.dNdEta[Pc][2]+
                    nodes[elements[numerElementu].nodeID[3]-1].x * shapeFunction.dNdEta[Pc][3];

            dYdKsi = nodes[elements[numerElementu].nodeID[0]-1].y * shapeFunction.dNdKsi[Pc][0]+
                    nodes[elements[numerElementu].nodeID[1]-1].y * shapeFunction.dNdKsi[Pc][1]+
                    nodes[elements[numerElementu].nodeID[2]-1].y * shapeFunction.dNdKsi[Pc][2]+
                    nodes[elements[numerElementu].nodeID[3]-1].y * shapeFunction.dNdKsi[Pc][3];

            dYdEta = nodes[elements[numerElementu].nodeID[0]-1].y * shapeFunction.dNdEta[Pc][0]+
                    nodes[elements[numerElementu].nodeID[1]-1].y * shapeFunction.dNdEta[Pc][1]+
                    nodes[elements[numerElementu].nodeID[2]-1].y * shapeFunction.dNdEta[Pc][2]+
                    nodes[elements[numerElementu].nodeID[3]-1].y * shapeFunction.dNdEta[Pc][3];


            Jacobian[0][0] = dXdKsi;
            Jacobian[0][1] = dXdEta;
            Jacobian[1][0] = dYdKsi;
            Jacobian[1][1] = dYdEta;

            detJ = Jacobian[0][0]*Jacobian[1][1] - Jacobian[0][1]*Jacobian[1][0];


//            double[][] macierzDopelenien = new double[2][2];
//            macierzDopelenien[0][0] = Jacobian[1][1];
//            macierzDopelenien[0][0] = - Jacobian[1][0];
//            macierzDopelenien[0][0] = - Jacobian[0][1];
//            macierzDopelenien[0][0] = Jacobian[0][0];

            odwrotnyJacobian[0][0] = 1/detJ * Jacobian[1][1];
            odwrotnyJacobian[0][1] = -1/detJ * Jacobian[0][1];
            odwrotnyJacobian[1][0] = -1/detJ * Jacobian[1][0];
            odwrotnyJacobian[1][1] = 1/detJ * Jacobian[0][0];



            // ---------- dNdx -----------------

            for(int i = 0;i<4;i++){
                dNdx[i] = (1/detJ) *(dYdEta * shapeFunction.dNdKsi[Pc][i] + (-1)*dYdKsi*shapeFunction.dNdEta[Pc][i]);
            }


            // ---------- dNdy -----------------
            for(int i =0; i<4;i++){
                dNdy[i] =1/detJ*(-dXdEta*shapeFunction.dNdKsi[Pc][i] + dXdKsi*shapeFunction.dNdEta[Pc][i]);
            }


            // ---------- dNdxdNdxT ----------------

            for(int i =0; i<4; i++){
                for(int j=0; j<4; j++){

                    dNdxdNdxT[i][j] = dNdx[i] * dNdx[j] ;
                }
            }

            // ---------- dNdydNdyT ----------------

            for(int i =0; i<4; i++){
                for(int j=0; j<4; j++){

                    dNdydNdyT[i][j] = dNdy[i] * dNdy[j] ;
                }
            }

            // ---------- dNdxdNdxTdetJ ----------------

            for(int i =0; i<4; i++){
                for(int j=0; j<4; j++){

                    dNdxdNdxTdetJ[i][j] = detJ * dNdxdNdxT[i][j];
                }
            }

            // ---------- dNdydNdyTdetJ ----------------

            for(int i =0; i<4; i++){
                for(int j=0; j<4; j++){

                    dNdydNdyTdetJ[i][j] = detJ * dNdydNdyT[i][j];
                }
            }

            // ---------- składowe macierzy h dla PC --------------

            for(int i = 0; i<4; i++){
                for(int j=0; j<4;j++){

                    skladoweH[i][j] = data.lambda * (dNdxdNdxTdetJ[i][j] + dNdydNdyTdetJ[i][j]);
                }
            }

            // ---------- NN dla pc --------------

            for(int i = 0; i<4; i++){
                for(int j = 0; j<4; j++){

                    skladowaC[i][j] = shapeFunction.shapeFunction[Pc][i] * shapeFunction.shapeFunction[Pc][j] * ro *c *detJ;
                }
            }



    }
    public void printSkladoweH(){

        for(int i = 0; i<4; i++){
            for(int j=0; j<4;j++){

                System.out.print((skladoweH[i][j]) + " ");
            }
            System.out.println();
        }

    }

    public void printskladowaC(){

        for(int i = 0; i<4; i++){
            for(int j=0; j<4;j++){

                System.out.print((skladowaC[i][j]) + " ");
            }
            System.out.println();
        }

    }



    public void printdNdxdNdxT(){

        for(int i = 0; i<4; i++){
            for(int j=0; j<4;j++){

                System.out.print((dNdxdNdxT[i][j]) + " ");
            }
            System.out.println();
        }

    }
    public void printdNdxdNdxTdetJ(){

        for(int i = 0; i<4; i++){
            for(int j=0; j<4;j++){

                System.out.print((dNdxdNdxTdetJ[i][j]) + " ");
            }
            System.out.println();
        }

    }
    public void printdNdydNdyTdetJ(){

        for(int i = 0; i<4; i++){
            for(int j=0; j<4;j++){

                System.out.print((dNdydNdyTdetJ[i][j]) + " ");
            }
            System.out.println();
        }

    }

    public void printdNdydNdyT(){

        for(int i = 0; i<4; i++){
            for(int j=0; j<4;j++){

                System.out.print((dNdydNdyT[i][j]) + " ");
            }
            System.out.println();
        }

    }

    public void printJacobian(){

        for(int i = 0; i<2; i++){
            for(int j=0; j<2;j++){

                System.out.println((Jacobian[i][j]));
            }
        }

    }
    public void printdNdx(){

        for(int i=0; i<4; i++){
            System.out.println(dNdx[i]);
        }
    }
    public void printdNdy(){

        for(int i=0; i<4; i++){
            System.out.println(dNdy[i]);
        }
    }


}
