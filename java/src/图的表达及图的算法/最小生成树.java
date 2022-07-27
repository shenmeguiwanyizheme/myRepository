package 图的表达及图的算法;

import java.util.*;

public class 最小生成树 {
    public List<Edge> prim(Node fromNode){//这是无向图的最小生成树
        PriorityQueue<Edge>queue=new PriorityQueue<>(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return o1.weight-o2.weight;
            }
        });
        HashSet<Node>isSelected=new HashSet<>();
        List<Edge>ans=new ArrayList<>();
        queue.addAll(fromNode.edgeArrayList);
        isSelected.add(fromNode);
        while(!queue.isEmpty()){
            Edge edge=queue.poll();

            if(!isSelected.contains(edge.to)){
                ans.add(edge);
                isSelected.add(edge.to);
                queue.addAll(edge.to.edgeArrayList);
            }
        }
     return ans;
    }
}
