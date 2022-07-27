package 堆的数组表达;

public class myHeap {
    int heapSize;
    int heap[];
    int limit;
    public myHeap(int limit){
        this.limit=limit;
        heap=new int[limit];
        heapSize=0;
    }

//    public int [] heapSort(int arr[]){
//        heapSize=arr.length;
//         int [] ans =new int [arr.length];
//        for(int i=0;i<arr.length;i++){
//           ans[i]=arr[i];
//           heapInsert(ans,i);
//        }
//        for(int i=0;i<ans.length;i++){
//             //
//            arr[arr.length-1-i]=ans[0];
//            swap(ans,0,--heapSize);
//            heqpify(arr,0,arr[0]);
//        }
//        return arr;

    public void heapSort(int arr[]){
//        for(int i=0;i<arr.length;i++){
//            //因为数据已经在这里了..0.0
//            heapInsert(arr,i);
//        }
        for(int i=arr.length-1;i>=0;i--){
            heaqpify(arr,i,arr.length);
        }
        int heapSize=arr.length;
        while(heapSize>0){
            swap(arr,0,--heapSize);
            heaqpify(arr,0,heapSize);
        }

            }
    public void heaqpify(int arr[], int index, int heapSize) {//下沉的的话多一个堆的大小的参数，因为要知道那个地方有没有数
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

    public void heapInsert(int[] arr, int index) {//可是为什么heapInsert没有要插入的值，那应该改名为heapFly...
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
    public void push(int x){
        heap[heapSize]=x;
        heapInsert(heap,heapSize++);
    }
    public  int pop(){
        int i=heap[0];
        swap(heap,0,--heapSize);
        heaqpify(heap,0,heapSize);
        return i;
    }
}
