package Quiz1;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

class dataArray {
    int jobWeight;
    int jobLength;
    int jobScore;
    
    dataArray(int jobWeight, int jobLength, int jobScore)   {
        this.jobWeight=jobWeight;
        this.jobLength=jobLength;
        this.jobScore=jobScore;
    }
    
    
    public String toString()   {
        String ret="";
        
        ret+="\r\n";
        ret+="[Weight=";
        ret+=jobWeight;
        ret+=" Length=";
        ret+=jobLength;
        ret+=" Score=";
        ret+=jobScore;
        ret+="]";
        return ret;
    }
}
        
public class Quiz1_1 {
    public static void main(String[] args)  {
        String fileName ="jobs.txt";
        
        dataArray[] ar=readData(fileName);
        sortData(ar, 0, ar.length, true);
        // inner sort by jobWeight
        int startScore=ar[0].jobScore;
        int start=0;
        int len=1;
        for(int i=1; i<ar.length; ++i)  {
            if(ar[i].jobScore!=startScore)  {
                startScore=ar[i].jobScore;
                if(len>1) {  
                    sortData(ar, start, len, false); 
                    len=1;
                }
                start=i;
            } else  {
                len++;
            }
        }
        if(len>1) {  sortData(ar, start, len, false); }
        
        long complTime=0;
        int curTime=0;
        for(int i=0; i<ar.length; ++i)  {
            curTime+=ar[i].jobLength;
            complTime+=curTime;
//            System.out.println("curTime="+curTime+" completionTime="+complTime);
        }
//        System.out.println(Arrays.asList(ar));
        System.out.println("completion time="+complTime);
        
    }
    
    static dataArray[] readData(String fileName)    {
        int nJobs;
        dataArray[] ar=null;
        Scanner sc=null;
        
        try {
            sc=new Scanner(new File(fileName));
            nJobs=sc.nextInt();
            ar=new dataArray[nJobs];
            for(int i=0; i<ar.length; ++i)  {
                int jW=sc.nextInt();
                int jL=sc.nextInt();
                ar[i]=new dataArray(jW,jL, jW-jL);
            }
            
        }catch(IOException ex) {
            System.out.println("Error in data file name");
            System.exit(1);
        }finally    {
          sc.close();  
        }
        
        return ar;
    }
    
    static  void sortData(dataArray[] ar, int start, int len, boolean sortByScore) {
        QSort.qsort(ar, start, len, sortByScore);
        
        
    }
}
