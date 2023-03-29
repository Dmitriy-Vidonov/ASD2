package ex_2.test_version;

import ex_1.final_version.SimpleTreeNode;

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
        BSTNode<T> nodeToAdd = new BSTNode<>(key, val, null);

        if(this.Root == null) { // если в дереве нет корня
            this.Root = nodeToAdd;
            return true;
        }
        // Создаем объект промежуточного поиска из FindNodeByKey()
        BSTFind<T> bstFind = this.FindNodeByKey(key);

        if(bstFind.NodeHasKey) // если мы нашли в дереве такой ключ
            return false; // вернем false и ничего делать не будем
        if(bstFind.ToLeft == true && bstFind.NodeHasKey == false) { // если добавить ключ надо как левый потомок
            bstFind.Node.LeftChild = nodeToAdd; // добавляем новый узел к узлу из bstFind
            nodeToAdd.Parent = bstFind.Node;
            return true;
        }
        if(bstFind.ToLeft == false && bstFind.NodeHasKey == false) { // если ToLeft != true
            bstFind.Node.RightChild = nodeToAdd;
            nodeToAdd.Parent = bstFind.Node;
            return true;
        }
        return false;
    }

    public BSTNode<T> FinMinMax(BSTNode<T> FromNode, boolean FindMax) { // поиск узла с макс. или мин. ключом
        // если FindMax == true, то ищем максимальный, если false - ищем минимальный
        if(Root == null) return null; // если дерево без корня
        if(Root.LeftChild == null && Root.RightChild == null) return Root; // если дерево состоит из одного лишь корня

        if(FindMax == false) // если нам надо найти минимальное значение
            return FinMinHelper(FromNode);
        return FinMaxHelper(FromNode); // иначе будем искать максимальное значение
    }

    private BSTNode<T> FinMinHelper(BSTNode<T> FromNode) { // вспомогательный рекурсивный метод поиска минимального ключа
        if(FromNode.RightChild == null && FromNode.LeftChild == null) // если мы дошли до узла, это крайнее значение и дальше идти не надо
            return FromNode; // просто возвращаем узел, т.к. дальше идти некуда
        return FinMinHelper(FromNode.LeftChild); // каждый раз берем левого потомка и идем по сути до конца дерева
    }

    private BSTNode<T> FinMaxHelper(BSTNode<T> FromNode) { // вспомогательный рекурсивный метод поиска максимального ключа
        if(FromNode.RightChild == null && FromNode.LeftChild == null)
            return FromNode;
        return FinMaxHelper(FromNode.RightChild); // каждый раз берем правого потомка и идем до конца дерева
    }

    public boolean DeleteNodeByKey(int key) { // удаление узла по ключу
        // если дерево пустое и в нем нет даже корня
        if(this.Root == null) return false;
        // если дерево из одного корня и его хотим удалить
        if(this.Root.LeftChild == null && this.Root.RightChild == null && this.Root.NodeKey == key) {
            this.Root = null;
            return true;
        }
        BSTFind<T> bstFind = this.FindNodeByKey(key); // создаем объект промежуточного поиска
        // если в дереве есть нужный ключ и он расположен в листе, т.е. потомков нет
        // описываем случай, если удаляемый узел - левый потомок и является листом
        if(bstFind.NodeHasKey == true && bstFind.Node.LeftChild == null
                && bstFind.Node.RightChild == null && bstFind.ToLeft == true) {
            // у родителя узла на удаление - обнуляется список потомков - обнулять надо только нужного потомка
            bstFind.Node.Parent.LeftChild = null;
            // снесли ссылку на родителя
            bstFind.Node.Parent = null;
            return true;
        }
        // описываем случай, если удаляемый узел - правый потомок и является листом
        if(bstFind.NodeHasKey == true && bstFind.Node.LeftChild == null
                && bstFind.Node.RightChild == null && bstFind.ToLeft == false) {
            // у родителя узла на удаление - обнуляется список потомков - обнулять надо только нужного потомка
            bstFind.Node.Parent.RightChild = null;
            // снесли ссылку на родителя
            bstFind.Node.Parent = null;
            return true;
        }
        // Если такого листа нет
        if(!bstFind.NodeHasKey) return false;

        // Если у узла один потомок правый)
        if(bstFind.NodeHasKey == true && bstFind.Node.RightChild != null && bstFind.Node.LeftChild == null) {
            bstFind.Node.RightChild.Parent = bstFind.Node.Parent; // потомку узла на удаление прописали нового родителя
            bstFind.Node.Parent.RightChild = bstFind.Node.RightChild; // преемника прописали в потомки родителя узла на удаление
            bstFind.Node.Parent = null; // обнулили предка у узла на удаление
            bstFind.Node.RightChild = null; // обнулили потомка у узла на удаление
            return true;
        }
        // Если у узла один потомок левый)
        if(bstFind.NodeHasKey == true && bstFind.Node.RightChild == null && bstFind.Node.LeftChild != null) {
            bstFind.Node.LeftChild.Parent = bstFind.Node.Parent; // потомку узла на удаление прописали нового родителя
            bstFind.Node.Parent.LeftChild = bstFind.Node.LeftChild; // преемника прописали в потомки родителя узла на удаление
            bstFind.Node.Parent = null; // обнулили предка у узла на удаление
            bstFind.Node.LeftChild = null; // обнулили потомка у узла на удаление
            return true;
        }
        // Если у узла два потомка и удаляемый узел не корень
        if(bstFind.NodeHasKey == true && bstFind.Node.LeftChild != null && bstFind.Node.RightChild != null && bstFind.Node != Root) {
            // Сначала надо найти преемника - тот узел, который мы будем ставить на место удаляемого узла. Узел с минимальным ключом правой ветки.
            BSTNode<T> successor = this.FinMinMax(bstFind.Node.RightChild, false); // поиск ведем по правой ветке
            successor.Parent.LeftChild = null; // убрали преемника из списка детей его прежнего родителя
            successor.Parent = bstFind.Node.Parent; // прописали нового родителя преемнику
            bstFind.Node.Parent.RightChild = successor; // прописали преемника в качестве потомка бывшему родителю удаляемого узла
            successor.RightChild = bstFind.Node.RightChild; // прописали преемнику потомка от удаляемого узла
            bstFind.Node.RightChild.Parent = successor; // бывшему потомку удаляемого узла прописали нового родителя - преемника
            successor.LeftChild = bstFind.Node.LeftChild; // передали преемнику левого потомка удаляемого узла
            bstFind.Node.LeftChild.Parent = successor; // установили преемника в качестве родителя у бывшего левого потомка удаляемого узла
            bstFind.Node.Parent = null; // отрезали удаляемый узел от предка
            bstFind.Node.LeftChild = null; // отрезали удаляемый узел от левого потомка
            bstFind.Node.RightChild = null; // отрезали удаляемый узел от правого потомка
            return true;
        }
        // Если мы пытаемся удалить корень, у которого есть оба потомка
        if(bstFind.NodeHasKey == true && bstFind.Node.LeftChild != null && bstFind.Node.RightChild != null && bstFind.Node == Root) {
            // Аналогично предыдущему варианту ищем преемника
            BSTNode<T> successor = this.FinMinMax(Root.RightChild, false); // поиск минимального элемента по правой ветке
            successor.Parent.LeftChild = null; // убрали преемника от его прежнего родителя
            successor.Parent = null; // установили преемнику null родителя
            successor.RightChild = Root.RightChild; // прописали преемнику правого потомка
            Root.RightChild.Parent = successor; // перепрописали родителя правому потомку корня
            successor.LeftChild = Root.LeftChild; // прописали преемнику левого потомка
            Root.LeftChild.Parent = successor; // перепрописали родителя левому потомку корня
            Root = null; // обнулили корень
            this.Root = successor; // установили преемника новым корнем дерева
            return true;
        }
        return false; // если узел не найден
    }

    public int Count() { // подсчет количества узлов в дереве
        if(this.Root == null) return 0; // если в дереве нет корней
        if(this.Root.LeftChild == null && this.Root.RightChild == null) return 1; // если дерево состоит из одного корня
        return CountHelper(this.Root);

    }
    private int CountHelper(BSTNode<T> node) {
        if(node == null) return 0;
        int count = 1;

        if(node.LeftChild != null || node.RightChild != null) {
            count += CountHelper(node.LeftChild);
            count += CountHelper(node.RightChild);
        }
        return count;
    }
}
