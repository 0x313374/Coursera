package Quiz2;



public class QSort {
	public static int count=0;
	public static int count1=0;
	
//	private static int getpivotfirst(int[]arr, int start, int len)	{
//		return arr[start];
//	}
//	private static dataArray getpivotlast(dataArray[]arr, int start, int len)	{
//		swap(arr, start, start+len-1);
//		return arr[start];
//	}
//	private static dataArray getpivotmedian(dataArray[]arr, int start, int len)	{
//		int middle=arr[start+(len-1)/2].jobScore;
//		int first=arr[start].jobScore;
//		int last=arr[start+len-1].jobScore;
//		int k=arr[start].jobScore;
//		
//		if(first<last)	{
//			if (last<middle) swap(arr,start, start+len-1);	//медианный - last
//			else 
//			  if(first<middle)	swap(arr, start, start+(len-1)/2);	//медианный - middle
//		}
//		else
//			if(middle<last) swap(arr, start, start+len-1); 	//медианный - last
//			else 
//				if(middle<first) swap(arr, start, start+(len-1)/2);	//медианный - middle
//			
//		return arr[start];
//	}
	private static void swap(int[][] arr, int el1, int el2)	{
		int node1=arr[el1][Quiz2_1.EDGE_NODE1_INDEX];
		int node2=arr[el1][Quiz2_1.EDGE_NODE2_INDEX];
		int cost=arr[el1][Quiz2_1.EDGE_COST_INDEX];
		arr[el1][Quiz2_1.EDGE_NODE1_INDEX]=arr[el2][Quiz2_1.EDGE_NODE1_INDEX];
		arr[el1][Quiz2_1.EDGE_NODE2_INDEX]=arr[el2][Quiz2_1.EDGE_NODE2_INDEX];
		arr[el1][Quiz2_1.EDGE_COST_INDEX]=arr[el2][Quiz2_1.EDGE_COST_INDEX];
		arr[el2][Quiz2_1.EDGE_NODE1_INDEX]=node1;
		arr[el2][Quiz2_1.EDGE_NODE2_INDEX]=node2;
		arr[el2][Quiz2_1.EDGE_COST_INDEX]=cost;
		
	}
	
	private int partitioning(int[][] arr, int start, int len){
		int i;
		int p=arr[start][Quiz2_1.EDGE_COST_INDEX];	//pivot el-t
		int partidx=start+1;		//partition index
		count1+=len-1;
		for (i=1; i<len; i++)	{//цикл по нераспределенным элементам
			count++;
                            if (arr[start+i][Quiz2_1.EDGE_COST_INDEX]<p)	{
                                    swap(arr,start+i,partidx);
                                    partidx++;
                            }
                        
		}
		if(partidx>start+1)	swap(arr, start, partidx-1);	//помещаем pivot на све место
		return partidx;
	}
	
	public void qsort(int[][] edges, int start, int len )	{
		int pivot;
		int pivotidx;
		if (len<1) return;
		
//		pivot=getpivotmedian(arr,  start,  len);
		pivotidx=partitioning(edges, start, len);
		
		qsort(edges, start, pivotidx-start-1);	//qsort левой части
		qsort(edges, pivotidx, start+len-pivotidx);	//qsort правой части
		
	}
}


