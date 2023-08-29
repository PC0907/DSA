#include<iostream>
using namespace std;
struct node
{
    int val;
    node *left;
    node *right;
};
class tree
{
    public:
    struct node *preorder(node *root)
    {
        if(root!=NULL)
        {
        cout<<root->val<<ends;
        preorder(root->left);
        preorder(root->right);
    }
    }
    struct node *inorder(node *root)
    {
        if(root!=NULL)
        {
        inorder(root->left);
        cout<<root->val<<ends;
        inorder(root->right);
    }
    }
    struct node *create(int x)
    {
        node *ptr=new node;
        ptr->val=x;
        ptr->left=NULL;
        ptr->right=NULL;
        return ptr;
    }
    struct node* insert(node* root,int x)
    {
        if(root==NULL)
        {
            return create(x);
        }
        if(x>root->val)
        {
            root->right=insert(root->right,x);
        }
        else if(x<root->val)
        {
            root->left=insert(root->left,x);
        }
        return root;
    }
    struct node *search(node *root,int val)
    {
        if(val==root->val)
        {
            return root;
        }
        if(val>root->val)
        {
            return search(root->right,val);
        }
        else if(val<root->val)
        {
            return search(root->right,val);
        }
        else
        {
            return NULL;
        }
    }
    struct node* minValueNode(struct node* root)
{
    while(root->left!=NULL)
    {
        root=root->left;
    }
    return root;
}
    struct node *delete_node(node *root,int val)
    {
        node *ptr=new node;
        if(root==NULL)
        {
            return root;
        }
        if(root->val>val)
        {
            root->left=delete_node(root->left,val);
        }
        else if(root->val<val)
        {
            delete_node(root->right,val);
        }
        else
        {
            if(root->left==NULL)
            {
                node *ptr=new node;
                ptr=root;
                root=root->right;
                delete ptr;
                return root;
            }
            else if(root->right==NULL)
            {
                node *ptr=new node;
                ptr=root->left;
                delete root;
                return ptr;
            }
            node *temp=new node;
            temp=minValueNode(root->right);
            root->val=temp->val;
            root->right=delete_node(root->right,val);
        }
        return root;
    }

};
int main()
{
    node *root=new node;
    tree t;
    int a;
    cout<<"Enter the value of root node: ";
    cin>>a;
    root=t.create(a);
    root=t.insert(root,12);
    root=t.insert(root,34);
    root=t.insert(root,91);
    root=t.insert(root,1);
    root=t.insert(root,33);
    node *ptr=new node;
    t.inorder(root);
    cout<<endl;
    root=t.delete_node(root,12);
    // ptr=t.search(root,23);
    // if(ptr!=NULL)
    // {
    //     if(ptr->left!=NULL && ptr->right!=NULL)
    //     {
    //         cout<<"It has 2 children! with left child "<<ptr->left->val<<"and right child "<<ptr->right->val;
    //     }
    //     else if(ptr->left==NULL && ptr->right!=NULL)
    //     {
    //         cout<<"It has only right child "<<ptr->right->val;
    //     }
    //     else if(ptr->left!=NULL && ptr->right==NULL)
    //     {
    //         cout<<"It has only left child "<<ptr->left->val;
    //     }
    //     else
    // {
    //     cout<<"Leaf node!";
    // }
    // }
    cout<<endl;
    t.inorder(root);
}
