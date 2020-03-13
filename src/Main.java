import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        // --------------------- Pobieranie danych z pliku i ustawianie ich -------------------------------------
        Data data = new Data("C:\\Users\\user\\Desktop\\demo\\MES\\src\\plik.txt");
//        System.out.println(data.H);
//        System.out.println(data.L);
//        System.out.println(data.nH);
//        System.out.println(data.nL);


        // -------------------- Node ---------------------------------

        double odlegloscx = data.L/(data.nL-1);
        double odlegloscy = data.H/(data.nH-1);
        //System.out.println(odlegloscx);
        //System.out.println(odlegloscy);

//        Node[] nodes = new Node[data.nL*data.nH+1];
//
//        int licznik = 0;
//
//        for(int i =0; i<data.nL; i++){
//            for(int j=0; j<data.nH;j++){
//
//                nodes[licznik] = new Node(i*odlegloscx,j*odlegloscy,100,licznik +1,data.nL,data.nH);
//                licznik ++;
//            }
//        }
//        nodes[data.nL*data.nH] = new Node(nodes[data.nH*data.nL - 1].x,nodes[data.nH*data.nL - 1].y,100,data.nH*data.nL,data.nL,data.nH);

        double x = 0;
        double y = 0;
        int id = 1;

        Node[] nodes = new Node[data.nL * data.nH];
        for (int i = 0; i < data.nL * data.nH; i++) {
            nodes[i] = new Node(x, y,data.initialTemp, id, data.L, data.H);
            if (y < data.H) {
                y = y + (data.H / (data.nH - 1));
            } else {
                y = 0;
                x = x + (data.L / (data.nL - 1));
            }

            id++;
        }

        for(int i =0; i<data.nH* data.nL; i++){
            nodes[i].printNode();
        }

        // -------------------- TABLICA ELEMNTÓW ----------------------------------

        int l_elementów = (data.nH-1)*(data.nL-1);
        System.out.println(l_elementów);

        Element[] elements = new Element[l_elementów];

        id = 0;
        for(int i=0; i< l_elementów; i++){

            int[] nodeID = new int[4];
            if(id % data.nH ==0 ) id++;
            nodeID[0] = id;
            nodeID[1] = id + data.nH;
            nodeID[2] = id + data.nH +1;
            nodeID[3] = id+1;
            elements[i] = new Element(i,nodeID,data);
            id++;
        }

        for(int i=0; i< l_elementów; i++){

            elements[i].printElement();
        }

        // ------------------ siatka ---------------------------------

        Siatka siatka = new Siatka(data.nL,data.nH,elements,nodes);

        // ------------------ macierze funkcji kształtu i pochodne po nich --------------------------

        ShapeFunction shapeFunction = new ShapeFunction(data);

        shapeFunction.printShapeFunction();
        shapeFunction.printdNdKSI();
        shapeFunction.printdNdEta();


        //-------------------------- Jacobian ------------------------------------

        Jacobian jacobian = new Jacobian(shapeFunction,elements,nodes,data,0,0);
        Jacobian jacobian1 = new Jacobian(shapeFunction,elements,nodes,data,0,1);
        Jacobian jacobian2 = new Jacobian(shapeFunction,elements,nodes,data,0,2);
        Jacobian jacobian3 = new Jacobian(shapeFunction,elements,nodes,data,0,3);
        jacobian.printJacobian();
        jacobian1.printJacobian();
        jacobian2.printJacobian();
        jacobian3.printJacobian();

        System.out.println(jacobian.detJ);

        System.out.println(" -------------dNdy------------");
        System.out.println("1 PC");
        jacobian.printdNdy();
        System.out.println("2 PC");
        jacobian1.printdNdy();
        System.out.println("3 PC");
        jacobian2.printdNdy();
        System.out.println("4 PC");
        jacobian3.printdNdy();

        System.out.println(" -------------dNdx------------");
        System.out.println("1 PC");
        jacobian.printdNdx();
        System.out.println("2 PC");
        jacobian1.printdNdx();
        System.out.println("3 PC");
        jacobian2.printdNdx();
        System.out.println("4 PC");
        jacobian3.printdNdx();

        System.out.println(" -------------dNdxdNdxT------------");
        System.out.println("1 PC");
        jacobian.printdNdxdNdxT();
        System.out.println("2 PC");
        jacobian1.printdNdxdNdxT();
        System.out.println("3 PC");
        jacobian2.printdNdxdNdxT();
        System.out.println("4 PC");
        jacobian3.printdNdxdNdxT();


        System.out.println(" -------------dNdydNdyT------------");
        System.out.println("1 PC");
        jacobian.printdNdydNdyT();
        System.out.println("2 PC");
        jacobian1.printdNdydNdyT();
        System.out.println("3 PC");
        jacobian2.printdNdydNdyT();
        System.out.println("4 PC");
        jacobian3.printdNdydNdyT();

        System.out.println(" -------------dNdxdNdxTdetJ------------");
        System.out.println("1 PC");
        jacobian.printdNdxdNdxTdetJ();
        System.out.println("2 PC");
        jacobian1.printdNdxdNdxTdetJ();
        System.out.println("3 PC");
        jacobian2.printdNdxdNdxTdetJ();
        System.out.println("4 PC");
        jacobian3.printdNdxdNdxTdetJ();

        System.out.println(" -------------dNdydNdyTdetJ------------");
        System.out.println("1 PC");
        jacobian.printdNdydNdyTdetJ();
        System.out.println("2 PC");
        jacobian1.printdNdydNdyTdetJ();
        System.out.println("3 PC");
        jacobian2.printdNdydNdyTdetJ();
        System.out.println("4 PC");
        jacobian3.printdNdydNdyTdetJ();

        System.out.println(" -------------skladoweH------------");
        System.out.println("1 PC");
        jacobian.printSkladoweH();
        System.out.println("2 PC");
        jacobian1.printSkladoweH();
        System.out.println("3 PC");
        jacobian2.printSkladoweH();
        System.out.println("4 PC");
        jacobian3.printSkladoweH();


        System.out.println("--------------- Macierz H --------------");
        bigH H = new bigH(jacobian,jacobian1,jacobian2,jacobian3);
        H.printBigH();

        System.out.println("--------------- skladowa C --------------");
        System.out.println("1 PC");
        jacobian.printskladowaC();
        System.out.println("2 PC");
        jacobian1.printskladowaC();
        System.out.println("3 PC");
        jacobian2.printskladowaC();
        System.out.println("4 PC");
        jacobian3.printskladowaC();

        System.out.println("--------------- Macierz C --------------");
        bigC C = new bigC(jacobian,jacobian1,jacobian2,jacobian3);
        C.printBigC();

        System.out.println("--------------- Czy brzeg ---------------");
        for(int i=0; i<4;i++){
            System.out.println(elements[0].nodeID[i]);
        }
        //elements[12].isPowIsBC(data);
        elements[0].printPows();
        System.out.println("------ Macierz HBC -----------");
        for(int i= 0; i< elements.length; i++){
            //System.out.println(i +1);
            elements[i].setH_BC_and_P();
            elements[i].printH_BC();
        }
        System.out.println("------- Wektor P -------------");
        elements[0].printP();


        System.out.println("------ Macierz C global -----------");
        Agregacja agregacja = new Agregacja(elements,data,nodes,shapeFunction);
        agregacja.printGlobalC();

        System.out.println("------ Macierz H global -----------");
        agregacja.printGlobalH();
        agregacja.printGlobalHBC();
        agregacja.printGlobalP();
        agregacja.printA();
        agregacja.printB();





    }

}
