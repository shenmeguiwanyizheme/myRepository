package 堆的数组表达;

import java.util.Map;

public class heapCarry {
    //任意节点的左孩子是2i+1 任意节点的右孩子是2i+2,
    //那么以此类推，任意节点的父亲是（i-1）/2
    int heapSize;

    public void heqpify(int arr[], int index, int heapSize) {
        int left = index * 2 + 1;
        while (left < heapSize) {
            int largest = left + 1 < heapSize && arr[left] < arr[left + 1] ? left + 1 : left;
            largest = arr[largest] < arr[index] ? index : largest;
            if (largest == index) {
                break;
            }
            swap(arr, largest, index);
            index = largest;
            left = index * 2 + 1;
            //运算和判断解耦
        }
    }

    public void heapInsert(int[] arr, int index) {
        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }

    }
    public void swap(int arr[],int x,int y){
        int tep=arr[x];
        arr[x]=arr[y];
        arr[y]=tep;
    }
}