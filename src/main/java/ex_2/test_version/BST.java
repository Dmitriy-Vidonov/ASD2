package ex_2.test_version;

class BSTNode<T> { // класс узла бинарного дерева поиска
    public int NodeKey; // ключ узла
    public T NodeValue; // значение в узле
    public BSTNode<T> Parent; // родительский узел. для корня - null
    public BSTNode<T> LeftChild; // левый потомок
    public BSTNode<T> RightChild; // правый потомок

    public BSTNode(int key, T val, BSTNode<T> parent) { // конструктор узла бинарного дерева поиска
        NodeKey = key; // задаем значение ключа
        NodeValue = val; // задаем значение в узле
        Parent = parent; // указываем родителя
        LeftChild = null; // изначально при создании узла его потомки равны null - левый и правый
        RightChild = null;
    }
}

// Промежуточный результат поиска
class BSTFind<T> {
    public BSTNode<T> Node; // Если мы нашли нужный узел, то Node отдаст на него ссылку, а переменная NodeHasKey будет true
    // в ином случае Node отдаст ссылку на потенциального родителя для нового узла с заданным ключом.
    // NodeHasKey при этом будет false
    // Если в дереве ни одного узла - будет null
    public boolean NodeHasKey; // true если узел найден, false если ключ не нашли

    // true, если родительскому узлу надо добавить новый левым
    public boolean ToLeft; // true, если новый узел надо добавить как левого потомка, false - если надо добавить как правого

    public BSTFind() { // конструктор класса. Изначально Node берет равным null
        Node = null;
    }
}

class BST<T> { // Класс бинарного дерева поиска
    BSTNode<T> Root; // корень дерева. Если дерево пустое, без корня, то Root будет равен null
    public BST(BSTNode<T> node) { // конструктор дерева, на вход подаем узел, который станет корневым
        Root = node; // здесь мы просто задаем корень дерева
    }

    public BSTFind<T> FindNodeByKey(int key) { // метод поиска по ключу. На вход - ключ. На выходе - объект класса BSTFind
        // частный случай с пустым деревом
        if (Root == null) return null;

        BSTFind<T> bstFind = new BSTFind<>();  // создаем объект промежуточного поиска
        bstFind.Node = Root; // изначально в Node у нас корень дерева

        FindNodeByKeyHelper(key, bstFind.Node, bstFind);
        return bstFind;
    }

    private void FindNodeByKeyHelper(int key, BSTNode<T> nodeHelper, BSTFind<T> bstFindHelper) { // рекурсивный метод поиска узла с ключом
        // в корне нужный ключ
        if (key == nodeHelper.NodeKey) {
            bstFindHelper.NodeHasKey = true; // мы нашли ключ
            return; // выходим из метода
        }
        // ключ больше ключа в узле - нужно идти к правому потомку, если тот существует
        if(key > nodeHelper.NodeKey && nodeHelper.RightChild != null) {
            bstFindHelper.NodeHasKey = false; // ключа в узле нет
            bstFindHelper.ToLeft = false; // т.к. идем направо, то ToLeft = false
            bstFindHelper.Node = nodeHelper.RightChild; // в узел промежуточного поиска помещаем правого потомка
            FindNodeByKeyHelper(key, bstFindHelper.Node, bstFindHelper); // рекурсивный вызов для правого потомка
        }
        // ключ меньше ключа в узле - нужно идти к левому потомку, если тот существует
        else if(key < nodeHelper.NodeKey && nodeHelper.LeftChild != null) {
            bstFindHelper.NodeHasKey = false; // ключа в узле нет
            bstFindHelper.ToLeft = true; // т.к. идем налево, то ToLeft = true
            bstFindHelper.Node = nodeHelper.LeftChild; // в узел промежуточного поиска помещаем левого потомка
            FindNodeByKeyHelper(key, bstFindHelper.Node, bstFindHelper); // рекурсивный вызов метода для LeftChild
        }
        // если ключ меньше ключа в узле и у узла нет левого потомка
        else if (key < nodeHelper.NodeKey && nodeHelper.LeftChild == null) {
            bstFindHelper.NodeHasKey = false; // ключа в узле нет
            bstFindHelper.ToLeft = true; // т.к. идем налево, то ToLeft = true
            return;
        }
        // если ключ больше ключа в узле и у узла нет правого потомка
        else if(key > nodeHelper.NodeKey && nodeHelper.RightChild == null) {
            bstFindHelper.NodeHasKey = false; // ключа в узле нет
            bstFindHelper.ToLeft = false; // т.к. идем направо, то ToLeft = false
            return;
        }
    }

    public boolean AddKeyValue(int key, T val) { // добавление нового узла с заданным ключом и значением
        
        return false; // если такой ключ уже есть
    }

    public BSTNode<T> FinMinMax(BSTNode<T> FromNode, boolean FindMax) { // поиск узла с макс. или мин. ключом
        // если FindMax == true, то ищем максимальный, если false - ищем минимальный

        return null; // видимо на случай пустого дерева
    }

    public boolean DeleteNodeByKey(int key) { // удаление узла по ключу
        return false; // если узел не найден
    }

    public int Count() { // подсчет количества узлов в дереве
        return 0; // если дерево без узлов
    }

    // Тесты
    public static void main(String[] args) {
        BSTNode<Integer> rootNode = new BSTNode<>(10, 10, null); // задаем корень
        BST<Integer> tree = new BST<>(rootNode); // задаем непустое дерево
        BST<Integer> emptyTree = new BST<>(null); // задаем пустое дерево

        BSTNode<Integer> node_9 = new BSTNode<>(9, 9, rootNode);
        BSTNode<Integer> node_8 = new BSTNode<>(8, 8, node_9);
        BSTNode<Integer> node_12 = new BSTNode<>(12, 12, node_9);
        BSTNode<Integer> node_20 = new BSTNode<>(20, 20, rootNode);

        tree.Root.LeftChild = node_9;
        node_9.LeftChild = node_8;
        node_9.RightChild = node_12;
        rootNode.RightChild = node_20;

        System.out.println(tree.FindNodeByKey(8).NodeHasKey);
    }
}
