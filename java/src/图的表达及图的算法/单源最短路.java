package 图的表达及图的算法;

import java.util.*;

public class 单源最短路 {//与各个点之间的最小距离
//      public HashMap<Node,Integer> minDistance(Graph graph,Node targetNode){
//          HashMap<Node,Integer>ans=new HashMap<>();
//          ans.put(targetNode,0);
//          for(Node node:graph.nodes.values()){
//              if(!ans.containsKey(node)){
//                  ans.put(node,Integer.MAX_VALUE);
//              }
//          }
//
//         PriorityQueue<Map.Entry<Node,Integer>>helper=new PriorityQueue<>(new Comparator<Map.Entry<Node, Integer>>() {
//             @Override
//             public int compare(Map.Entry<Node, Integer> o1, Map.Entry<Node, Integer> o2) {
//                 return o1.getValue()-o2.getValue();
//             }
//         });
//          helper.addAll(ans.entrySet());
//         while(!helper.isEmpty()){
//             Node node=helper.poll().getKey();
//             for(Edge edge: node.edgeArrayList){
//                 if((ans.get(node)+edge.weight)<ans.get(edge.to)){
//                     ans.put(edge.to,ans.get(node)+edge.weight);
//                 }
//             }
//         }
//          //接下来要干什么
//
//          return ans;
//      }
    public HashMap<Node,Integer> minDistance(Node fromNode){
        HashMap<Node,Integer>ans =new HashMap<>();
        ans.put(fromNode,0);
        //一个答案容器，和一个过程容器，有时候还需要一个校验容器
        PriorityQueue<Map.Entry<Node,Integer>>helper=new PriorityQueue<>(new Comparator<Map.Entry<Node, Integer>>() {
             @Override
             public int compare(Map.Entry<Node, Integer> o1, Map.Entry<Node, Integer> o2) {
                 return o1.getValue()-o2.getValue();
             }
         });
       return  null;
    }
}
