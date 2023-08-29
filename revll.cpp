//REVERSING A LINKED LIST
#include<iostream>
using namespace std;
#include<iostream>
using namespace std;
struct node
{
    int val;
    node* next;
};
struct node *insertatbeg(node *head,int x)
{
    struct node *ptr=new node;
    ptr->val=x;
    ptr->next=head;
    head=ptr;
    return ptr;
}
struct node *traverse(node *head)
{
    while(head!=NULL)
    {
        cout<<head->val<<"->"<<ends;
        head=head->next;
    }
}
struct node *create(int x)
{
    struct node *head=new node;
    head->val=x;
    head->next=NULL;
    return head;
}
struct node *insertatvalue(node *head,int x,int y)
{
    struct node *ptr=new node;
    struct node *p=new node;
    p=head;
    while(p!=NULL && p->val!=y)
    {
        p=p->next;
    }
    if(p->val==y)
    {
        ptr->val=x;
        ptr->next=p->next;
        p->next=ptr;
        return head;
    }
    else{
        return 0;
    }
}
struct node * insertatend(node *head,int x)
{
    struct node *ptr=new node;
    struct node *p=new node;
    p=head;
    while(p->next!=NULL)
    {
        p=p->next;
    }
    p->next=ptr;
    ptr->next=NULL;
    ptr->val=x;
    return head;
}
int lenght(node *head)
{
    int c=0;
    while(head!=NULL)
    {
        c++;
        head=head->next;
    }
    return c;
}
// struct node *add(node *head,int x)
// {
//     struct node *ptr=new node;
//     head->next=ptr;
//     ptr->val=x;
//     ptr->next=NULL;
//     return head;
// }
//copying contents of the linked list into n array
int copy(node *head,int arr[100])
{
    struct node *p=new node;
    p=head;
    int i=lenght(p);
    while(i!=0)
    {
        arr[i]=p->val;
        p=p->next;
        i--;
    }
}
int main()
{
    int arr[100];
    struct node *head=new node;
    head=create(7);
    // head=add(head,10);
    head=insertatbeg(head,11);
    head=insertatend(head,76);
    head=insertatvalue(head,101,11);
    traverse(head);
    cout<<endl;
    int size=lenght(head);
    cout<<size<<endl;
    copy(head,arr);
    for(int i=0;i<size+1;i++)
    {
        cout<<arr[i]<<ends;
    }
}