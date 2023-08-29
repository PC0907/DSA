//TREE IMPLEMENTATION
#include<iostream>
using namespace std;
struct node
{
    int data;
    node *left;
    node *right;
};
class tree
{
    public:
    struct node *create()
    {
        struct node *newnode=new node;
        int x;
        cout<<"Enter the value of x: ";
        cin>>x;
        if(x==-1)
        {
            newnode->data=0;
            return 0;
        }
        newnode->data=x;
        cout<<"Left node insertion: ";
        newnode->left=create();
        cout<<"Right node insertion: ";
        newnode->right=create();
        return newnode;
    }
    struct node* search(node *root,int x)
    {
        if(root->data==x)
        {
            return root;
        }
        else if(root->left!=NULL && root->right!=NULL)
        {
            search(root->left,x);
            search(root->right,x);
            return root;
        }
        else if(root->right!=NULL && root->left==NULL)
        {
            search(root->right,x);
            return root;
        }
        else if(root->left!=NULL && root->right==NULL)
        {
            search(root->left,x);
            return root;
        }
        else
        {
            return NULL;
        }
    }
    struct node* disp_preorder(node *root)
    {
        if(root==NULL)
        {
            cout<<0<<ends;
        }
        else
        {
            cout<<root->data<<ends;
            disp_preorder(root->left);
            disp_preorder(root->right);
        }
    }
    struct node *iterative_preorder(node *root)
    {
        cout<<"Iterative Traversal";
    }
    int count_node(node *root)
    {
        int x,y;
        if(root!=NULL)
        {
            x=count_node(root->left);
            y=count_node(root->right);
            return x+y+1;
        }
        else
        {
            return 0;
        }
    }
    int count_leaf_nodes(node *root)
    {
        int x,y;
        if(root!=NULL)
        {
            x=count_node(root->left);
            y=count_node(root->right);
            if(root->left==NULL && root->right==NULL)
            {
                return x+y+1;
            }
            else
            {
                return x+y;
            }
        }
        else
        {
            return 0;
        }
    }
};
int main()
{
    struct node * root=new node;
    tree t;
    root=t.create();
    cout<<endl;
    t.disp_preorder(root);
    cout<<endl;
    int x;
    cout<<"Enter the value to be searched?";
    cin>>x;
    cout<<endl;
    cout<<"Counting the number of nodes: "<<endl;
    cout<<t.count_node(root);
    cout<<endl;
    cout<<t.count_leaf_nodes(root);
    // struct node* snode=new node;
    // snode=t.search(root,x);
    // cout<<endl;
    // cout<<snode->data;
    // cout<<endl;
    // if(snode->data==x)
    // {
    //     cout<<"The data is found at root node";
    // }
    // else if(snode->left->data==x)
    // {
    //     cout<<"The data is found as left child with root node "<<snode->data<<"and right node "<<snode->right->data;
    // }
    // else if(snode->right->data==x)
    // {
    //     cout<<"The data is found as right child with root node "<<snode->data<<"and left node "<<snode->left->data;
    // }
    // else
    // {
    //     cout<<"Element not found at tree";
    // }
}