package 堆的数组表达;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.PriorityQueue;

public class 堆的有效记录和快速查最值特性 {
         public int max(int[][]matrix){
            int max=0;
             Arrays.sort(matrix,(e1,e2)->{return e1[0]-e2[0];});

             PriorityQueue<Integer>queue=new PriorityQueue<>();
             //不加入开始的节点，只把用作清除和计算数据的准则，同时，在遍历的过程中,每次清除完都统计有多少个重合线段，就是说，以每个开始时间作为标点来统计，是一种不错的选择
            for(int i=0;i<matrix.length;i++){
                while (queue.peek()<matrix[i][0]){
                    queue.poll();
                }
                queue.add(matrix[i][0]);
                max= Math.max(max,queue.size());
            }
            return max;
         }
}
//最大的敌人就是不能下定决心，我想下定决心，攻克算法难关，至少也先成为一次，小镇做题家吧.

//web端
// 基础原理八股文你得会
// 欸框架组件你得会
//算法你还得好
// 数据库，中间件的使用
// 什么系统及Io 什么资源管理 ..
// 这这那那的我草泥马
//   redis docker maven git linux
// 什么缓存，安全，架构，存储哦！
// 还是先技术，然后把算法作为面试需要的技能突击比较好
//                      对浏览器的特征进行控制
//  