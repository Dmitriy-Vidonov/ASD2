package ex_2.test_version;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class WideAllNodes_Test {
    BST<Integer> emptyTree = new BST(null); // пустое дерево без корня
    BSTNode<Integer> rootNode = new BSTNode<>(150, 150, null);
    BST<Integer> tree = new BST<>(rootNode); // стандартное дерево с корнем
    BSTNode<Integer> node_100 = new BSTNode<>(100, 100, null);
    BSTNode<Integer> node_200 = new BSTNode<>(200, 200, null);
    BSTNode<Integer> node_50 = new BSTNode<>(50, 50, null);
    BSTNode<Integer> node_240 = new BSTNode<>(240, 240, null);
    BSTNode<Integer> node_260 = new BSTNode<>(260, 260, null);

    @Test
    @DisplayName("1) Пустое дерево")
    void wideAllNodes_empty_tree() throws Exception {
        // Проверка пустоты дерева перед тестом
        assertTrue(emptyTree.Root == null);

        assertTrue(emptyTree.WideAllNodes() == null);
    }

    @Test
    @DisplayName("2) Дерево из одного корня")
    void wideAllNodes_one_node_tree() throws Exception {
        // Проверка того, что корень у дерева есть
        assertTrue(tree.Root != null);
        // Смотрим, что итоговый список состоит из одного элемента
        assertTrue(tree.WideAllNodes().size() == 1);
        // Проверяем - действительно ли корень посчитался и был занесен в список
        assertTrue(tree.WideAllNodes().get(0).NodeKey == rootNode.NodeKey); // см значение ключа
        assertTrue(tree.WideAllNodes().get(0) == rootNode); // проверяем - сам ли это корень собственной персоной
        assertTrue(tree.WideAllNodes().get(0).NodeValue == rootNode.NodeValue); // см значение самого корня
    }

    @Test
    @DisplayName("3) Дерево из корня и левого потомка")
    void wideAllNodes_only_left_child_root_tree() throws Exception {
        // Проверка того, что корень у дерева есть
        assertTrue(tree.Root != null);

        // Сформировали структуру дерева
        rootNode.LeftChild = node_100;
        node_100.Parent = rootNode;

        // Смотрим, что у корня есть только левый потомок
        assertTrue(rootNode.LeftChild != null);
        assertTrue(rootNode.RightChild == null);

        // Смотрим, что итоговый список состоит из двух элементов
        assertTrue(tree.WideAllNodes().size() == 2);
    }

    @Test
    @DisplayName("4) Дерево из корня и правого потомка")
    void wideAllNodes_only_right_child_root_tree() throws Exception {
        // Проверка того, что корень у дерева есть
        assertTrue(tree.Root != null);

        // Сформировали структуру дерева
        rootNode.RightChild = node_200;
        node_200.Parent = rootNode;

        // Смотрим, что у корня есть только левый потомок
        assertTrue(rootNode.LeftChild == null);
        assertTrue(rootNode.RightChild != null);

        // Смотрим, что итоговый список состоит из двух элементов
        assertTrue(tree.WideAllNodes().size() == 2);
    }

    @Test
    @DisplayName("5) Дерево из корня и двух потомков")
    void wideAllNodes_both_children_root_tree() throws Exception {
        // Проверка того, что корень у дерева есть
        assertTrue(tree.Root != null);

        // Сформировали структуру дерева
        rootNode.LeftChild = node_100;
        node_100.Parent = rootNode;
        rootNode.RightChild = node_200;
        node_200.Parent = rootNode;

        // Смотрим, что у корня есть только левый потомок
        assertTrue(rootNode.LeftChild != null);
        assertTrue(rootNode.RightChild != null);

        // Смотрим, что итоговый список состоит из двух элементов
        assertTrue(tree.WideAllNodes().size() == 3);

        // Надо посмотреть порядок узлов в списке
        ArrayList<BSTNode> bstList = tree.WideAllNodes();
        assertTrue(bstList.get(0).NodeKey == 150 && bstList.get(1).NodeKey == 100
                && bstList.get(2).NodeKey == 200);
    }

    @Test
    @DisplayName("6) Дерево из 6 узлов")
    void wideAllNodes_6_nodes_tree() throws Exception {
        // Проверка того, что корень у дерева есть
        assertTrue(tree.Root != null);

        // Сформировали структуру дерева
        rootNode.LeftChild = node_100;
        node_100.Parent = rootNode;
        rootNode.RightChild = node_200;
        node_200.Parent = rootNode;
        node_100.LeftChild = node_50;
        node_50.Parent = node_100;
        node_200.RightChild = node_240;
        node_240.Parent = node_200;
        node_240.RightChild = node_260;
        node_260.Parent = node_240;

        // Надо посмотреть порядок узлов в списке
        ArrayList<BSTNode> bstList = tree.WideAllNodes();

        assertTrue(bstList.get(0).NodeKey == 150
                        && bstList.get(1).NodeKey == 100
                        && bstList.get(2).NodeKey == 200
                        && bstList.get(3).NodeKey == 50
                        && bstList.get(4).NodeKey == 240
                        && bstList.get(5).NodeKey == 260);
    }
}