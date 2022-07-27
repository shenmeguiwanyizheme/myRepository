package 图的表达及图的算法;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Node implements Serializable {
     public int value;
     public int in;
     public int out;
    public ArrayList<Node> nexts;
    public ArrayList<Edge> edgeArrayList;

    @Override
    public boolean equals(Object o) {
        if(o==null||o.getClass()!=getClass()) return false;
        Node obj=(Node) o;
        return obj.value==this.value;

    }

    public Node(int in) {
        this.in = in;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, in, out, nexts, edgeArrayList);
    }
}
