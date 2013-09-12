package Quiz1;


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
	private static void swap(dataArray[]arr, int el1, int el2)	{
		int jobWeight=arr[el1].jobWeight;
		int jobLength=arr[el1].jobLength;
		int jobScore=arr[el1].jobScore;
		arr[el1].jobWeight=arr[el2].jobWeight;
		arr[el1].jobLength=arr[el2].jobLength;
		arr[el1].jobScore=arr[el2].jobScore;
		arr[el2].jobWeight=jobWeight;
		arr[el2].jobLength=jobLength;
		arr[el2].jobScore=jobScore;
		
	}
	
	private static int partitioning(dataArray[]arr, int start, int len, boolean sortByScore){
		int i;
		dataArray p=arr[start];	//pivot el-t
		int partidx=start+1;		//partition index
		count1+=len-1;
		for (i=1; i<len; i++)	{//цикл по нераспределенным элементам
			count++;
                        if(sortByScore) {
                            if (arr[start+i].jobScore>p.jobScore)	{
                                    swap(arr,start+i,partidx);
                                    partidx++;
                            }
                        } else  {// sortByWeight
                            if (arr[start+i].jobWeight>p.jobWeight)	{
                                    swap(arr,start+i,partidx);
                                    partidx++;
                            }
                        }
		}
		if(partidx>start+1)	swap(arr, start, partidx-1);	//помещаем pivot на све место
		return partidx;
	}
	
	public static void qsort(dataArray[]arr, int start, int len, boolean sortByScore )	{
		int pivot;
		int pivotidx;
		if (len<1) return;
		
//		pivot=getpivotmedian(arr,  start,  len);
		pivotidx=partitioning(arr,start, len, sortByScore);
		
		qsort(arr, start, pivotidx-start-1, sortByScore);	//qsort левой части
		qsort(arr, pivotidx, start+len-pivotidx, sortByScore);	//qsort правой части
		
	}
}


