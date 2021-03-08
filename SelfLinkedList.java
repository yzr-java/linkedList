package 链表的实现;

public class SelfLinkedList<T> {
    //链表的属性：链表的头部、链表的大小
    Node<T> root = null;
    int size = 0;

    //默认的构造函数
    public SelfLinkedList(){}

    // 获取长度
    public int size() {
        return this.size;
    }

    // 获取某个索引节点内容
    public T get(int index) {
        int i = 0;
        Node node = this.root;
        // 依次往前推进
        while (i < index) {
            node = node.next;
            i++;
        }
        return (T) node.content;
    }

    // 查找某个值的索引值，默认不存在为-1
    public int find(T value) {
        Node node = this.root;
        // index 保存当前的索引
        int index = 0;
        // 依次遍历链表，找到内容等于value的node，返回index
        while (node != null && node.next != null) {
            if (node.content == value) {
                return index;
            }
            node = node.next;
            index++;
        }
        return -1;
    }

    /**
     * 在尾部、头部、中间部位插入元素
     * -1为头部插入，其他索引值默认为在索引值所对应元素后插入元素
     * 所以插入的索引值范围为：-1 ~ this.size-1的整数
     * 注意：成功删除完元素后链表的size要减一
     */
    // 末尾添加元素
    public boolean addLast(T value) {
        return this.add(this.size - 1, value);
    }

    // 头部插入元素
    public boolean addFirst(T value) {
        return this.add(-1, value);
    }

    // 插入元素
    public boolean add(int index, T value) {
        if (index == -1) {
            if (this.root != null) {
                this.root = new Node(value, this.root);
            } else {
                this.root = new Node(value, null);
            }
        } else {
            Node pre = this.root;
            while (index > 0) {
                if (pre.next == null) {
                    return false;
                }
                pre = pre.next;
                index--;
            }

            if (pre == null) {
                return false;
            }

            Node newNode = new Node(value, pre.next);
            pre.next = newNode;
        }
        this.size++;
        return true;
    }

    /**
     * 在头部、尾部、指定位置删除元素
     * 删除元素的索引值范围：0 ~ this.size-1
     * 注意：成功删除元素后链表的size要加一
     */
    // 删除最后一个元素
    public boolean removeLast() {
        return this.remove(this.size - 1);
    }

    // 删除第一个元素
    public boolean removeFirst() {
        return this.remove(0);
    }

    // index = 0, 表示删除第 1 个元素
    // index = n，表示删除第 n+1 个元素
    public boolean remove(int index) {
        //讨论index的值对返回值的影响,两种无效删除
        if(index < 0 || index > this.size - 1) {
            return false;
        }
        //保存当前链表的根节点
        Node root = this.root;
        if(root == null) {
            return false;
        }
        //index=0即删除头部元素
        if(index == 0) {
            this.root = root.next;
            root.next = null;
        } else {
            //当index>0&&index<=this.size-1时，需要遍历寻找前置节点
            //当index==1时，前置节点就是当前链表根节点，无需寻找
            //当index>1时，需要遍历链表寻找前置节点
            while(index > 1) {
                root = root.next;
                if(root == null) {
                    return false;
                }
                index--;
            }
            if(root == null || root.next == null) {
                return false;
            }
            Node removeNode = root.next;
            root.next = removeNode.next;
            removeNode.next = null;
        }

        this.size--;
        return true;
    }

    public static void main(String args[]) {
        SelfLinkedList<String> linkedList = new SelfLinkedList<>();
        linkedList.addFirst("测试头部插入");
        linkedList.addLast("测试尾部插入");
        linkedList.add(0,"测试指定位置插入");
        for(int i=0 ; i < linkedList.size; i++) {
            System.out.println(linkedList.get(i));
        }
        linkedList.removeFirst();
        linkedList.removeLast();
        for (int i=0 ; i < linkedList.size; i++) {
            System.out.println(linkedList.get(i));
        }
        linkedList.remove(0);
        linkedList.addFirst("测试成功");
        for(int i=0; i < linkedList.size; i++) {
            System.out.println(linkedList.get(i) + " 最终链表长度为：" + linkedList.size);
        }
    }
}
/**
 * 测试结果：
 *
 * 测试头部插入
 * 测试指定位置插入
 * 测试尾部插入
 * 测试指定位置插入
 * 测试成功 最终链表长度为：1
 *
 * 与预期结果相符，链表构建正确
 */
