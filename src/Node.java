public class Node {

    double x;
    double y;
    double temperatura;

    int id;
    boolean Bc;

    public Node(double x, double y, double temp, int id, double width, double height){

        this.temperatura = temp;
        this.id=id;
        this.x=x;
        this.y=y;
        this.Bc = settingValueOfBc(width,height);
    }

    public boolean settingValueOfBc(double width, double height){

        //boolean isBorder;

        if(this.x==0 || this.x == width || this.y == 0 || this.y == height)
            return true;
        else
        return false;
    }

    public void printNode(){

        System.out.println("X =" + this.x);
        System.out.println("Y =" + this.y);
        System.out.println("ID =" + this.id);
    }
}
