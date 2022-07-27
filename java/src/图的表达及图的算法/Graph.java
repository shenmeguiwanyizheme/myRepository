package 图的表达及图的算法;

import java.util.*;

public class Graph {
    public HashMap<Integer, Node> nodes;
    public ArrayList<Edge> edges;

    public Graph() {
        nodes = new HashMap<>();
          edges=new ArrayList<>();
    }

    public Graph createGraph(int[][] matrix) {
             Graph graph=new Graph();
            for(int i=0;i<matrix.length;i++){
                if(!graph.nodes.containsKey(matrix[i][1]))
                {
                    Node fromNode=new Node(matrix[i][1]);

                    nodes.put(matrix[i][i],fromNode);
                }
                if(!graph.nodes.containsKey(matrix[i][2]))
                {
                    Node toNode =new Node(matrix[i][2]);

                    nodes.put(matrix[i][2],toNode);

                }
                Node fromNode=nodes.get(matrix[i][i]);
                Node toNode =nodes.get(matrix[i][2]);
                fromNode.out++;
                toNode.in++;
                fromNode.nexts.add(toNode);
                Edge edge=new Edge(matrix[i][0],fromNode,toNode);
                fromNode.edgeArrayList.add(edge);
                graph.edges.add(edge);


            }
            return graph;
    }
    public List<Node> BFS(Node node){
        Queue<Node>queue=new LinkedList<>();
      List<Node>ans=new LinkedList<>();
        //非递归版本
        HashSet<Node>isSelected=new HashSet<>();
        queue.offer(node);

        while(!queue.isEmpty()){
            Node node1=queue.poll();
            for(Node node2: node.nexts){
                if(!isSelected.contains(node2)){
                    System.out.println(node2);
                    queue.add(node2);
                    ans.add(node2);
                    isSelected.add(node2);
                }
            }
        }
        return ans;
    }
    //递归版本
//    public List<Node> BFS2(List<Node>ans,Node fromNode,HashSet<Node>isSelected){
//
//        process(ans,fromNode,isSelected,);
//        return ans;
//    }
//    public void process(List<Node>ans,Node node,HashSet<Node>isSelected,Integer count){
//        if(!isSelected.contains(node)){
//            ans.add(node);
//            isSelected.add(node);
//        }
//        for(Node node1:node.nexts){
//            process(ans,node1,isSelected,1);
//        }
//
//    }
   public List<Node> DFS(Node fromNode){
        List<Node> ans=new LinkedList<>();
        HashSet<Node>isSelected=new HashSet<>();
        dfsProcess(ans,fromNode,isSelected);
        return ans;
   }
  public void dfsProcess(List<Node>ans,Node node,HashSet<Node>isSelected){
        if(!isSelected.contains(node)){
            ans.add(node);
            isSelected.add(node);
        }
        for(Node node1:node.nexts){
            dfsProcess(ans,node1,isSelected);
        }
  }
  public List<Node> DFS2(Node fromNode){
        List<Node>ans=new LinkedList<>();
        Stack<Node>stack=new Stack<>();
        HashSet<Node>isSelected=new HashSet<>();
        stack.push(fromNode);
        while(!stack.isEmpty()){
            Node node =stack.pop();
            for(Node node1:node.nexts){
                if (!isSelected.contains(node1)) {
                    stack.push(node);
                    stack.push(node1);
                    ans.add(node1);
                    break;
                }
            }
        }
            return ans;
  }


}