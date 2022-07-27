package 图的表达及图的算法;

import java.util.*;

public class 拓扑排序 {
    public List<Node> sortedTopology(Graph graph){

        List<Node>zeroInQueue=new LinkedList<>();
        //接下来干啥//nodes是个map
        HashSet<Node>isSelected=new HashSet<>();
        while(zeroInQueue.size()!=graph.nodes.values().size()){
            for(Node node:graph.nodes.values()){
                if(node.in==0&&!isSelected.contains(node)){
                    zeroInQueue.add(node);
                    for(Node node1:node.nexts){
                        node1.in--;
                    }
                }
            }
        }
       //怎么减少入度
        return  zeroInQueue;

    }//这个方法的时间复杂度比我的低很多,绝对不会造成重复检查......  采用的也是一种动态思想，姜还是老的辣
    public List<Node> sortedTopology2(Graph graph){
        HashMap<Node,Integer>inMap=new HashMap<>();
        //key 是某个节点 value是剩余的入度
        Queue<Node>zeroInQueue=new LinkedList<>();
        for(Node node:graph.nodes.values()){
            inMap.put(node,node.in);
            if(node.in==0){
                zeroInQueue.add(node);
            }
        }
        List<Node>result=new ArrayList<>();
        while(!zeroInQueue.isEmpty()){
            Node cur=zeroInQueue.poll();
            result.add(cur);
            for(Node next:cur.nexts){
                inMap.put(next,inMap.get(next)-1);
                if(inMap.get(next)==0){
                    zeroInQueue.add(next);

                }
            }

        }
        return result;
    }

}
