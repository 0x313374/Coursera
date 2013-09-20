package Quiz2;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;





public class Quiz2_1 {
    int[][] edges=null;
    int[][] nodes=null;
    static final int FINAL_CLUSTERS=4;
    final int NODE_PARENT_INDEX=0;
    final int NODE_RANK_INDEX=1;
    static final int EDGE_NODE1_INDEX=0;
    static final int EDGE_NODE2_INDEX=1;
    static final int EDGE_COST_INDEX=2;
    int nClusters=0;
    
    public static void main(String[] args) {
        String fileName="clustering1.txt";
        
        Quiz2_1 myProg=new Quiz2_1();
        
        myProg.readData(fileName);
        
        myProg.sortEdges();
        
        int spacing=0;
        for(int i=1; i<myProg.edges.length; ++i)   {
            spacing=myProg.edges[i][EDGE_COST_INDEX];
            if(myProg.nClusters<FINAL_CLUSTERS)    { break; }
            myProg.mergeClusters(myProg.edges[i][EDGE_NODE1_INDEX], myProg.edges[i][EDGE_NODE2_INDEX]);
//        System.out.println("i="+i+" spacing="+spacing+"\r\n"+myProg.printNodes());
//        System.out.println("i="+i+" spacing="+spacing);
        }
        
        System.out.println("max spacing for "+myProg.nClusters+" clasters="+spacing);
        
//        System.out.println(myProg.printEdges());
        
        
    }

    void mergeClusters(int node1, int node2)    {
        int workNode1=findRoot(node1);
        int workNode2=findRoot(node2);
        
        if(nodes[workNode1][NODE_PARENT_INDEX]==nodes[workNode2][NODE_PARENT_INDEX])    {
            return;
        }
//        System.out.print("root1. parent="+nodes[workNode1][NODE_PARENT_INDEX]+" rank="+nodes[workNode1][NODE_RANK_INDEX]);
//        System.out.println("   root2. parent="+nodes[workNode2][NODE_PARENT_INDEX]+" rank="+nodes[workNode2][NODE_RANK_INDEX]);
        
        if(nodes[workNode1][NODE_RANK_INDEX]>nodes[workNode2][NODE_RANK_INDEX]) {
            nodes[workNode1][NODE_RANK_INDEX]+=nodes[workNode2][NODE_RANK_INDEX];
            for(int i=0; i<nodes.length; ++i)    {
                if(nodes[i][NODE_PARENT_INDEX]==workNode2)  {
                    nodes[i][NODE_PARENT_INDEX]=workNode1;
                }
            }
//            nodes[workNode2][NODE_PARENT_INDEX]=workNode1;
        } else  {
            nodes[workNode2][NODE_RANK_INDEX]+=nodes[workNode1][NODE_RANK_INDEX];
            for(int i=0; i<nodes.length; ++i)    {
                if(nodes[i][NODE_PARENT_INDEX]==workNode1)  {
                    nodes[i][NODE_PARENT_INDEX]=workNode2;
                }
            }
//            nodes[workNode1][NODE_PARENT_INDEX]=workNode2;
        }
        nClusters--;
    }
    
    int findRoot(int node)  {
        int workNode=node;
        
        while(workNode!=nodes[workNode][NODE_PARENT_INDEX]) {
            workNode=nodes[workNode][NODE_PARENT_INDEX];
        }
        return workNode;
    }
    
    String printNodes() {
        StringBuilder ret=new StringBuilder();

        ret.append("Nodes[]->").append(nodes.length-1);
        for(int i=1; i< nodes.length; ++i)  {
            ret.append("\r\n i=").append(i);
            ret.append(" parent=").append(nodes[i][NODE_PARENT_INDEX]);
            ret.append(" rank=").append(nodes[i][NODE_RANK_INDEX]);
        }
        return ret.toString();
    }
    String printEdges() {
        StringBuilder ret=new StringBuilder();
        
        ret.append("Edges[]->").append(edges.length-1);
        for(int i=1; i<edges.length; ++i)   {
            ret.append("\r\n i=").append(i);
            ret.append(" node1=").append(edges[i][EDGE_NODE1_INDEX]);
            ret.append(" node2=").append(edges[i][EDGE_NODE2_INDEX]);
            ret.append(" cost=").append(edges[i][EDGE_COST_INDEX]);
        }
        return ret.toString();
    }
    
    void readData(String fileName)  {
        Scanner sc=null;
        int nNodes;
        int nEdges=0;
        
        try{
            sc=new Scanner(new File(fileName));
            nNodes=sc.nextInt();
            nodes=new int[nNodes+1][2];
            nClusters=nNodes;
            for(int i=1; i<nodes.length; ++i)   {
                nodes[i][NODE_PARENT_INDEX]=i;
                nodes[i][NODE_RANK_INDEX]=1;
            }
            while(sc.hasNext()) {
                sc.nextInt(); sc.nextInt(); sc.nextInt();
                nEdges++;
            }
            System.out.println("nEdges="+nEdges);            
            sc.close();
            sc=new Scanner(new File(fileName));
            nNodes=sc.nextInt();
            edges=new int[nEdges+1][3];
            for(int i=1; i<edges.length; ++i) {
                edges[i][EDGE_NODE1_INDEX]=sc.nextInt();
                edges[i][EDGE_NODE2_INDEX]=sc.nextInt();
                edges[i][EDGE_COST_INDEX]=sc.nextInt();
            }
        }catch(IOException ex)  {
            System.out.println("Error in data file");
            System.exit(1);
        }finally    {
            sc.close();
        }        
    }
    
    void sortEdges()    {
        new QSort().qsort(edges, 1, edges.length-1);
    }
}
