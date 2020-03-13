public class Siatka {

    double nh,nl;
    double nw = nh * nl;
    double ne = (nh-1)*(nl-1);
    Element[] siatkaElement;
    Node[] siatkaNode;

    public Siatka(double nl, double nh, Element[] el, Node[] n){

        this.nh = nh;
        this.nl = nl;
        this.siatkaElement = el;
        this.siatkaNode = n;
    }

}
