package ex_2.test_version;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Count_Test {
    BST<Integer> emptyTree = new BST<>(null); // пустое дерево без корня
    BSTNode<Integer> rootNode = new BSTNode<>(8, 8, null); // задаем корень непустого дерева
    BST<Integer> tree = new BST<>(rootNode); // непустое дерево
    BSTNode<Integer> onlyRootNode = new BSTNode<>(10, 10, null); // корень для дерева только из одного корня
    BST<Integer> onlyRootTree = new BST<>(onlyRootNode); // задаем непустое дерево, в котором только корень и все
    // Задаем используемые ключи
    BSTNode<Integer> node_1 = new BSTNode<>(1, 1, null);
    BSTNode<Integer> node_2 = new BSTNode<>(2, 2, null);
    BSTNode<Integer> node_4 = new BSTNode<>(4, 4, null);
    BSTNode<Integer> node_6 = new BSTNode<>(6, 6, null);
    BSTNode<Integer> node_10 = new BSTNode<>(10, 10, null);
    BSTNode<Integer> node_12 = new BSTNode<>(12, 12, null);
    BSTNode<Integer> node_13 = new BSTNode<>(13, 13, null);
    BSTNode<Integer> node_14 = new BSTNode<>(14, 14, null);
    BSTNode<Integer> node_15 = new BSTNode<>(15, 15, null);

    @Test
    @DisplayName("1) Пустое дерево без корня")
    void count_empty_tree() throws Exception {
        assertTrue(emptyTree.Count() == 0);
    }

    @Test
    @DisplayName("2) Дерево с одним узлом-корнем")
    void count_empty_one_node_tree() throws Exception {
        assertTrue(onlyRootTree.Count() == 1);
    }

    @Test
    @DisplayName("3) Распределенное дерево, несколько узлов")
    void count_empty_few_nodes_tree() throws Exception {
        // связываем узлы между собой
        // левая часть дерева
        rootNode.LeftChild = node_4;
        node_4.Parent = rootNode;
        rootNode.RightChild = node_12;
        node_12.Parent = rootNode;
        node_4.LeftChild = node_2;
        node_2.Parent = node_4;
        node_4.RightChild = node_6;
        node_6.Parent = node_4;
        node_2.LeftChild = node_1;
        node_1.Parent = node_2;
        // правая часть дерева
        rootNode.RightChild = node_12;
        node_12.Parent = rootNode;
        node_12.LeftChild = node_10;
        node_10.Parent = node_12;
        node_12.RightChild = node_14;
        node_14.Parent = node_12;
        node_14.LeftChild = node_13;
        node_13.Parent = node_14;
        node_14.RightChild = node_15;
        node_15.Parent = node_14;

        assertTrue(tree.Count() == 10);
    }

    @Test
    @DisplayName("4) Все дерево - это левая ветка")
    void count_only_left_branch_tree() throws Exception {
        // Собрали дерево в виде только левой ветки
        rootNode.LeftChild = node_4;
        node_4.Parent = rootNode;
        node_4.LeftChild = node_2;
        node_2.Parent = node_4;
        node_2.LeftChild = node_1;
        node_1.Parent = node_2;

        assertTrue(tree.Count() == 4);
    }

    @Test
    @DisplayName("5) Все дерево - это правая ветка")
    void count_only_right_branch_tree() throws Exception {
        // Собрали дерево в виде только правой ветки
        rootNode.RightChild = node_12;
        node_12.Parent = rootNode;
        node_12.RightChild = node_14;
        node_14.Parent = node_12;
        node_14.RightChild = node_15;
        node_15.Parent = node_14;

        assertTrue(tree.Count() == 4);
    }
}