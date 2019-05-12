import java.util.ArrayList;
import java.util.Scanner;
/**
 * Created by kunal on 04-10-2017.
 */
class Pair
{
    int v1;
    int v2;
    public Pair(int v1,int v2)
    {
        this.v1=v1;
        this.v2=v2;
    }
}
class Edge
{
    int v1;
    int v2;
    int weight;
    boolean picked;
    public Edge(int v1,int v2,int weight)
    {
        this.v1=v1;
        this.v2=v2;
        this.weight=weight;
        this.picked=false;
    }
}
class Graph
{
    int vertices;
    int edges;
    ArrayList<Edge> edge;
    public Graph(int vertices,int edges)
    {
        this.vertices=vertices;
        this.edges=edges;
        edge=new ArrayList<>();
    }
    public void addEdge(int v1,int v2,int wt)
    {
        Edge e=new Edge(v1,v2,wt);
        edge.add(e);
    }
    public void printEdges()
    {
        for(int i=0;i<edge.size();i++)
        {
            System.out.println(edge.get(i).v1+" "+edge.get(i).v2+" "+edge.get(i).weight);
            System.out.print("\n");
        }
    }
}
public class Main {
    static ArrayList<Pair> pair=new ArrayList<>();
    public static boolean findpair(ArrayList<Pair> pairs,int v1,int v2,int j)
    {
        for(int i=0;i<pairs.size();i++)
        {
            if((v1==pairs.get(i).v1&&v2==pairs.get(i).v2)&&(i!=j))
            {
                pairs.remove(i);
                return true;
            }
        }
        return false;
    }
    public static void addpair(ArrayList<Pair> pairs,int v1,int v2)
    {
        pairs.add(new Pair(v1,v2));
        for(int i=0;i<pairs.size()-1;i++)
        {
            if(pairs.get(i).v2==v1)
            {
                pairs.add(new Pair(pairs.get(i).v1,v2));
            }
        }
    }
    public static void kruskal(Graph g,int vertices,int edges)
    {
        for(int i=0;i<g.edge.size();i++)
        {
            if(findpair(pair,g.edge.get(i).v1,g.edge.get(i).v2,i))
            {
                g.edge.remove(i);
            }
        }
        Scanner scanner=new Scanner(System.in);
        String ch=scanner.next();
        g.edge.remove(0);
        g.printEdges();
    }
    public static void main(String []args)
    {
        Scanner scanner=new Scanner(System.in);
        int vertex,edge;
        System.out.println("Enter no of vertices:");
        vertex=scanner.nextInt();
        System.out.println("Enter no of edges:");
        edge=scanner.nextInt();
        Graph g=new Graph(vertex,edge);
        int vertex1[]=new int[edge];
        int vertex2[]=new int[edge];
        int weight[]=new int[edge];
        for(int i=0;i<edge;i++)
        {
            System.out.println("Enter vertex 1:");
            vertex1[i]=scanner.nextInt();
            System.out.println("Enter vertex 2:");
            vertex2[i]=scanner.nextInt();
            if((vertex1[i]>vertex||vertex2[i]>vertex)||(vertex1[i]<1||vertex2[i]<1)||vertex2[i]<=vertex1[i])
            {
                System.out.println("Not possible.");
                System.exit(-1);
            }
            else
            {
                System.out.println("Enter weight:");
                weight[i] = scanner.nextInt();
            }
        }
        for(int i=0;i<edge;i++)
        {
            for(int j=0;j<edge-1;j++)
            {
                if(weight[j]<weight[j+1])
                {
                    int tmp=weight[j];
                    weight[j]=weight[j+1];
                    weight[j+1]=tmp;
                    tmp=vertex1[j];
                    vertex1[j]=vertex1[j+1];
                    vertex1[j+1]=tmp;
                    tmp=vertex2[j];
                    vertex2[j]=vertex2[j+1];
                    vertex2[j+1]=tmp;
                }
            }
        }
        for(int i=0;i<edge;i++)
        {
            g.edge.add(new Edge(vertex1[i],vertex2[i],weight[i]));
            addpair(pair,vertex1[i],vertex2[i]);
        }
        kruskal(g,vertex,edge);
    }
}

