//Clustering algorithm with a LARGE # of nodes
package Quiz2;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;


class Node  {
    Node next;
    int cluster;

    Node(Node next, int cluster) {
        this.next = next;
        this.cluster = cluster;
    }
    
    int getCluster()    {
        Node workNode=this;
        while(workNode.next!=null)  {  workNode=workNode.next;  }
        return workNode.cluster;
    }
    
}

class NodeArray {
    int bitCode;
    Node node;

    public NodeArray(int bitCode, Node node) {
        this.bitCode = bitCode;
        this.node = node;
    }
    
}

public class Quiz2_2 {
    NodeArray[] nodes;
    int nClusters=0;
    int maxNCluster=0;
    
    public static void main(String[] args) {
        String fileName="clustering_big.txt";
        
        Quiz2_2 myProg=new Quiz2_2();
        myProg.readData(fileName);
        myProg.calcNClusters();
        
        System.out.println("nClusters="+myProg.nClusters);
//        System.out.println("NodeArray:"+myProg.printNodeArray());
    }
    
    void calcNClusters()    {
        for(int i=1; i<nodes.length; ++i)   {
            for(int j=i+1; j<nodes.length; ++j)  {
                if(calcDistance(nodes[i].bitCode, nodes[j].bitCode)<=2) {
                    if(mergeClusters(nodes[i], nodes[j]))   { nClusters--;  }
                    
                }
                
            }
            System.out.println("node1="+i);
        }
        
    }
    
    boolean mergeClusters(NodeArray node1, NodeArray node2)   {
        
        if(node1.node.getCluster()==node2.node.getCluster())    return false;
        Node workNode1=node1.node;
        while(workNode1.next!=null) {
            workNode1=workNode1.next;
        }
        Node workNode2=node2.node;
        while(workNode2.next!=null) {
            workNode2=workNode2.next;
        }
        workNode2.next=workNode1;
        
        return true;
    }
    
    
    int calcDistance(int bitcode1, int bitcode2)    {
        int distance=0;
        int bitXOR=bitcode1 ^ bitcode2;
        
        while(bitXOR!=0)    {
            if(bitXOR%2==1) distance++;
            bitXOR>>=1;
        }
        return distance;
    }
    
    String printNodeArray() {
        StringBuilder ret=new StringBuilder();
        
        for(int i=1; i<nodes.length; ++i)   {
            ret.append("\r\ni=").append(i).append(" bitcode=");
            ret.append(Integer.toBinaryString(nodes[i].bitCode));
            if(nodes[i].node!=null) ret.append(" cluster=").append(nodes[i].node.getCluster());
            else ret.append(" cluster=0");
            
        }
        return ret.toString();
    }
    void readData(String fileName)  {
        Scanner sc=null;
        int nNodes;
        int nBits;
        int bitCode;
        
        try {
            sc=new Scanner(new File(fileName));
            nNodes=sc.nextInt();
            nBits=sc.nextInt();
            nodes=new NodeArray[nNodes+1];
            nClusters=nNodes;
            for(int i=1; i<=nNodes; ++i) {
                bitCode=0;
                for(int j=0; j<nBits; ++j)  {
                    bitCode=(bitCode<<1)+sc.nextInt();
                }
                nodes[i]=new NodeArray(bitCode, new Node(null, i));
            }
            
            
        }catch(IOException ex)  {
            System.out.println("Error in data file");
            System.exit(1);
        }finally    {
            sc.close();
        }
        
    }
    

}
