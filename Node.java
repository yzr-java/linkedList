package 链表的实现;

public class Node<T> {
    /**
     * 链表采用泛型设计，可以在使用时自定义存储数据类型
     */

    //节点的两个属性：自身的值、指向下一个节点的指针
    T content;
    Node<T> next;

    //默认的构造函数
    public Node(){}

    //可以传入两个参数的构造函数
    public Node(T content, Node<T> next) {
        this.content = content;
        this.next = next;
    }
}
