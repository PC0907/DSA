import java.util.*;
class Traversals
{
    public static  class Queue
    {
        int front=-1;
        int size;
        int rear=-1;
        int queue[];
       public Queue(int size)
       {
        this.size=size;
        this.queue=new int[size];
       }

       public boolean isfull()
       {
        return front==0 && rear==size-1;
       }

       public boolean isEmpty()
       {
        return front==-1;
       }

       public void enqueue(int item)
       {
        if(isfull())
        {
            return;
        }
        if(front==-1)
        {
            front++;
        }
        rear++;
        queue[rear]=item;
       }
       public int deque()
       {
        int item;
        if(isEmpty())
        {
            return -1;
        }
        else
        item=queue[front];
        if(front>=rear)
        {
            front=rear=-1;
        }
        else
        {
            front++;
        }
        return item;
        }
    }
    
    public static class Stack
    {
        int top=-1;
        int size;
        int stack[];    
        public Stack(int size)
        {
            this.size=size;
            this.stack = new int[size];
        }
        boolean isEmpty()
        {
            return top==-1;
        }
        boolean isFull()
        {
            return top==size-1;
        }
        void push(int item)
        {
            if(isFull())
            {
                return;
            }
            if(top==-1)
            {
                top++;
                stack[top]=item;
                return;
            }
            top++;
            stack[top]=item;
        }
        int pop()
        {
            int item;
            if(isEmpty())
            {
                return -1;
            }
            item=stack[top];
            top--;
            return item;
        }
    }
    
    
    public static void bfs(int graph[][],int source,int n)
    {
        Queue queue=new Queue(1000);
        boolean visited[]= new boolean[1000];
        queue.enqueue(source);
        for(int i=0;i<n;i++)
        {
            visited[i]=false;
        }
        visited[source]=true;
        System.out.print(source);
        while(!queue.isEmpty())
        {
            int u=queue.deque();
            visited[u]=true;
            for(int v=0;v<n+1;v++)
            {
                if(graph[u][v]!=0 && visited[v]==false)
                {
                queue.enqueue(v);
                visited[v]=true;
                System.out.print(" "+v);
                }
            }
            
        }
    }


    public static void dfs(int graph[][],int source,int n)
    {
        Stack stack=new Stack(n);
        boolean visited[]=new boolean[n];
        for(int i=0;i<n;i++)
        {
            visited[i]=false;
        }
        visited[source]=true;
        System.out.print(source);
        stack.push(source);
        while(!stack.isEmpty())
        {
            int u=stack.pop();
            if(!visited[u])
            {
                System.out.print(" "+u);
                visited[u]=true;
            }
            for(int v=0;v<n;v++)
            {
                if(graph[u][v]!=0 && visited[v]==false)
                {
                    stack.push(v);
                }
            }
        }
    }
    public static void printgraph(int graph[][],int n)
    {
        for(int i=0;i<n+1;i++)
        {
            for(int j=0;j<n+1;j++)
            {
                if(graph[i][j]!=0)
                {
                    System.out.println("Source : "+ i +", Destination : "+j);
                }
            }
        }
    }
    public static void main(String args[])
    {
        int n,ed;
        System.out.print("Enter the number of nodes: ");
        Scanner sc= new Scanner(System.in);
        n=sc.nextInt();
        System.out.print("Enter the number of edges: ");
        ed=sc.nextInt();
        int graph[][]=new int[1000][1000];
        int src,dest,x;
        for(int i=0;i<ed;i++)
        {
            System.out.print("Enter the source node for edge " + i + " : ");
            src=sc.nextInt();
            System.out.print("Enter the destination node for edge " + i + " : ");
            dest=sc.nextInt();
            graph[src][dest]=1;
        }
        printgraph(graph, n);
        bfs(graph, 0, n);
        dfs(graph, 0, n);
        
    }
}