package ex_1.test_version;
import java.util.*;

public class SimpleTreeNode<T> { // класс - узел дерева. класс обобщенный и может хранить любые типы - <T> - дженерик
    public T NodeValue; // значение узла типа T (того же типа, что будет сам класс)
    public SimpleTreeNode<T> Parent; // ссылка на родительский узел
    public List<SimpleTreeNode<T>> Children; // список дочерних узлов
    public int nodeLevel; // уровень узла

    public SimpleTreeNode(T val, SimpleTreeNode<T> parent) { // конструктор принимает 2 аргумента - значение текущего узла и ссылку на родителя
        NodeValue = val; // задаем значение текущего узла
        Parent = parent; // присваиваем родителя
        Children = null; // Children ставим в null - при создании узла у него изначально нет детей
        if(parent != null) // если узел имеет предка, то уровень = уровень предка + 1
            nodeLevel = parent.nodeLevel + 1;
    }
}

class SimpleTree<T> { // очевидно, класс тоже дженерик и хранить должен тот же тип что и узел. Мы задаем здесь корень дерева
    public SimpleTreeNode<T> Root; // корень, может быть null, если мы непосредственно задаем корень

    public SimpleTree(SimpleTreeNode<T> root) // Это ведь конструктор. принимает только корень
    {
        Root = root; // по идее нужна проверка на то, нет ли у корня предков и детей
    }

    // Добавление нового узла
    public void AddChild(SimpleTreeNode<T> ParentNode, SimpleTreeNode<T> NewChild) // ParentNode - текущий узел. NewChild - новый узел
    {
        // в тесте потом проверяем наличие добавленного узла
        if(ParentNode != null) // родительский узел не должен быть null
            NewChild.Parent = ParentNode;
        else return; // выходим из метода, если в качестве родители попытались указать null, что по сути как создать новый корень
        // Если к родительскому узлу еще не добавляли детей, то изначально вместо списка у него null и список надо создать
        if(ParentNode.Children == null) { // если списка еще нет, то создаем новый
            ParentNode.Children = new ArrayList<>();
        }
        ParentNode.Children.add(NewChild);
    }

    // Удаление существуюшего узла, не корневого. Узел удаляется вместе со всем поддеревом.
    public void DeleteNode(SimpleTreeNode<T> NodeToDelete)
    {
        if(NodeToDelete == null) return; // если нечего удалять - то и делать ничего не надо и метод завершаем
        if(NodeToDelete == this.Root && this.Root.Children != null) { // если удаляем корень и список детей не пуст
            for(SimpleTreeNode<T> child : new ArrayList<>(NodeToDelete.Children)) DeleteNode(child);
            this.Root = null;
            return;
        }
        else if(NodeToDelete == this.Root && this.Root.Children == null) { // если удаляем корень без списка детей
            this.Root = null;
            return;
        }

        if(NodeToDelete.Parent != null) { // если у узла есть родитель, то удаляем узел из списка детей родителя
            NodeToDelete.Parent.Children.remove(NodeToDelete);
            NodeToDelete.Parent = null;
        }

        if(NodeToDelete.Children != null) { // если список потомков не пуст
            for(SimpleTreeNode<T> child : new ArrayList<>(NodeToDelete.Children))
                DeleteNode(child); // удаление узла для каждого потомка
        }
    }

    // Последовательно обойти всё дерево и сформировать список всех узлов в произвольном порядке
    public List<SimpleTreeNode<T>> GetAllNodes()
    {
        // ваш код выдачи всех узлов дерева в определённом порядке
        if(this.Root == null) return null; // если корень отсутствует, вернуть null
        List<SimpleTreeNode<T>> nodeList = new ArrayList<>();
        GetAllNodesHelper(this.Root, nodeList); // вызываем метод получения узлов для корня, а от него уже метод сам все проверит и сделает рекурсивно
        return nodeList;
    }

    // Вспомогательный метод для обхода всех узлов
    private void GetAllNodesHelper(SimpleTreeNode<T> node, List<SimpleTreeNode<T>> nodeList){ // вспомогательный метод, на вход идет узел и список узлов
        if(node == null) return; // если узел не существует - то завершаем работу метода
        nodeList.add(node);
        if(node.Children != null) { // если список потомков не пуст
            for(SimpleTreeNode<T> child : node.Children) // для каждого потомка рекурсивно вызываем метод
                GetAllNodesHelper(child, nodeList);
        }
    }

    // Найти список всех узлов по заданному значению
    public List<SimpleTreeNode<T>> FindNodesByValue(T val)
    {
        if(this.Root == null) return null; // если корень отсутствует, вернуть null
        List<SimpleTreeNode<T>> nodesByVal = new ArrayList<>();
        FindNodesByValueHelper(val, this.Root, nodesByVal);
        return nodesByVal;
    }

    // Вспомогательный метод для поиска узлов по значению
    private void FindNodesByValueHelper(T val, SimpleTreeNode<T> node, List<SimpleTreeNode<T>> nodesByVal) {
        if(node == null) return;
        if(node.NodeValue == val)
            nodesByVal.add(node);
        if (node.Children != null) {
            for(SimpleTreeNode<T> child : node.Children) // для каждого потомка нашего узла мы совершаем аналогичную проверку
                FindNodesByValueHelper(val, child, nodesByVal);
        }
    }

    // Переместить некорневой узел в другое место вместе с его поддеревом
    public void MoveNode(SimpleTreeNode<T> OriginalNode, SimpleTreeNode<T> NewParent)
    {
        // если хотим либо переместить null либо переместиться в null, либо в самого себя
        if(OriginalNode == null || NewParent == null || OriginalNode == NewParent)
            return;
        if(OriginalNode.Parent == null || OriginalNode == this.Root) // если перемещаемый узел корневой или не имеет родителя
            return;
        else {
            OriginalNode.Parent.Children.remove(OriginalNode); // удаляем текущий узел из списка детей родителя
            OriginalNode.Parent = NewParent; // прописываем новую связь, одновременно разрывая старую
        }
        if(OriginalNode.Parent.Children == null) // если у нового родителя нет списка потомков, то мы его создаем
            OriginalNode.Parent.Children = new ArrayList<>();
        OriginalNode.Parent.Children.add(OriginalNode); // записываем текущий узел в список детей нового родителя
    }

    // Подсчет общего количества всех узлов в дереве
    public int Count()
    {
        if(this.Root == null) return 0; // если нет корня - то вернем 0
        return countHelper(this.Root); // в ином случае мы рекурсивно обходим все дерево и считаем узлы
    }

    private int countHelper(SimpleTreeNode<T> node) { // вспомогательный метод рекурсивного обхода
        if(node == null) return 0;
        int count = 1; // если начальный узел не пустой, то в счетчике уже должна быть единица

        if(node.Children != null) { // если список детей не пустой, то мы его проверяем
            for(SimpleTreeNode<T> child : node.Children) // для каждого потомка из списка потомков текущего узла
                count += countHelper(child); // обновляем счетчик
        }
        return count; // вернем итоговое значение счетчика
    }

    // Подсчет количества листьев в дереве
    public int LeafCount()
    {
        if(this.Root == null) return 0; // если нет корня - то вернем 0
        return leafCountHelper(this.Root);
    }

    private int leafCountHelper(SimpleTreeNode<T> node) {
        if(node == null) return 0;
        int count = 0;
        if(node.Children == null || node.Children.isEmpty())
            count++;
        if (node.Children != null) {
            for(SimpleTreeNode<T> child : node.Children)
                count += leafCountHelper(child);
        }
        return count;
    }

    // Проверка наличия узла в дереве
    public boolean contains(SimpleTreeNode<T> node) {
        if(node == null) return false; // если узел не существует, то вернем false
        if(this.Root == node) return true; // если корень дерева является искомым узлом - то true
        if(this.Root == null) return false;
        if(Root.Children != null) {
            for(SimpleTreeNode<T> child : this.Root.Children) // для каждого потомка корня
            {
                if(contains(child)) return true; // рекурсивно вызываем метод для каждого потомка и потомков потомков
            }
        }
        return false; // если ничего не нашли после рекурсивного обхода дерева
    }

    // Список узлов поддерева
    public List<SimpleTreeNode<T>> GetAllNodesSubTree(SimpleTreeNode<T> nodeTopSubTree)
    {
        if(nodeTopSubTree == null) return null; // если узел отсутствует, вернуть null
        List<SimpleTreeNode<T>> nodeList = new ArrayList<>(); // создаем список узлов
        GetAllNodesSubTreeHelper(nodeTopSubTree, nodeList); // вызываем метод получения узлов для корня, а от него уже метод сам все проверит и сделает рекурсивно
        return nodeList;
    }

    // Вспомогательный метод для обхода узлов поддерева
    private void GetAllNodesSubTreeHelper(SimpleTreeNode<T> node, List<SimpleTreeNode<T>> nodeList){ // вспомогательный метод, на вход идет узел и список узлов
        if(node == null) return; // если узел не существует - то завершаем работу метода
        nodeList.add(node);
        if(node.Children != null) { // если список потомков не пуст
            for(SimpleTreeNode<T> child : node.Children) // для каждого потомка рекурсивно вызываем метод
                GetAllNodesHelper(child, nodeList);
        }
    }

    // Метод для создания многоуровневой вложенности в дереве (тестировочный)
    public void MultiLevelNodes(int levels) { // на вход даем число - на сколько уровней будем создавать иерархию
        for(int i=0; i<levels; i++) {
            MultiLevelNodesHelper(this.Root, i);
        }
    }
    private void MultiLevelNodesHelper(SimpleTreeNode<T> node, int level) {
        if (level == 0) {
            SimpleTreeNode<T> nodeNew = new SimpleTreeNode<>(null, node);
            if (node.Children == null) {
                node.Children = new ArrayList<>();
            }
            this.AddChild(node, nodeNew);
        } else {
                for (SimpleTreeNode<T> child : node.Children) {
                    MultiLevelNodesHelper(child, level - 1);
                }
            }
        }
}

// на первой отправке была ошибка - "Ошибка или не пройден тест: Исключение в LeafCount()"
// она возникала тогда, когда мы пытались в методе LeafCountHelper() проверить список Children, который был пуст
// и исключение было:
/*
 Exception in thread "main" java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because "node.Children"
 is null at
 ex_1.test_version_with_comments.SimpleTree.leafCountHelper(SimpleTreeNode.java:128) // не было проверки if (node.Children != null), а просто шел перебор значений в списке Children
 at ex_1.test_version_with_comments.SimpleTree.leafCountHelper(SimpleTreeNode.java:129)
 at ex_1.test_version_with_comments.SimpleTree.LeafCount(SimpleTreeNode.java:120) // вызов метода LeafCountHelper()
 at ex_1.test_version_with_comments.Test.main(SimpleTreeNode.java:159)
* */

// После второй отправки с проверкой типа if(root.Children != null) ошибка - "Исключение при вызове FindNodesByValue()"
// После этой ошибки полностью переработал все тесты для каждого метода