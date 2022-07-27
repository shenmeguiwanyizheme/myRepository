package 图的表达及图的算法;

public class Edge {
    public  int weight;
      public  Node from;
      public  Node to;

    public Edge(int weight, Node from, Node to) {
        this.weight = weight;
        this.from = from;
        this.to = to;
    }
}
