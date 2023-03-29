package ex_2.test_version;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FinMinMax_Test {
    // Задаем узлы и деревья, которые будем использовать в тестах
    BSTNode<Integer> rootNode = new BSTNode<>(18, 18, null); // задаем корень
    BST<Integer> tree = new BST<>(rootNode); // задаем непустое дерево
    BSTNode<Integer> onlyRootNode = new BSTNode<>(18, 18, null); // корень для дерева только из одного корня
    BST<Integer> onlyRootTree = new BST<>(onlyRootNode); // задаем непустое дерево, в котором только корень и все
    BST<Integer> emptyTree = new BST<>(null); // задаем пустое дерево
    BSTNode<Integer> node_9 = new BSTNode<>(9, 9, rootNode);
    BSTNode<Integer> node_8 = new BSTNode<>(8, 8, node_9);
    BSTNode<Integer> node_12 = new BSTNode<>(12, 12, node_9);
    BSTNode<Integer> node_20 = new BSTNode<>(20, 20, rootNode);

    BSTNode<Integer> node_6 = new BSTNode<>(6, 6, null);
    BSTNode<Integer> node_11 = new BSTNode<>(11, 11, null);
    BSTNode<Integer> node_15 = new BSTNode<>(15, 15, null);
    BSTNode<Integer> node_19 = new BSTNode<>(19, 19, null);
    BSTNode<Integer> node_35 = new BSTNode<>(35, 35, null);
    @BeforeEach
    // Сборка дерева перед тестированием
    void setUp() throws Exception {
        // задаем детей - устанавливаем связи между узлами дерева
        rootNode.LeftChild = node_9;
        node_9.LeftChild = node_8;
        node_9.RightChild = node_12;
        rootNode.RightChild = node_20;
        node_8.LeftChild = node_6;
        node_12.RightChild = node_15;
        node_20.RightChild = node_35;
        node_20.LeftChild = node_19;
        node_12.LeftChild = node_11;
        // задаем родителей
        node_9.Parent = rootNode;
        node_20.Parent = rootNode;
        node_8.Parent = node_9;
        node_12.Parent = node_9;
        node_6.Parent = node_8;
        node_15.Parent = node_12;
        node_35.Parent = node_20;
        node_19.Parent = node_20;
        node_11.Parent = node_12;
    }
    @Test
    @DisplayName("1) Поиск минимума по поддереву")
    void finMinMax_min_in_subtree() throws Exception {
        assertTrue(tree.FinMinMax(node_9, false) == node_6);
    }
    @Test
    @DisplayName("2) Поиск максимума по поддереву")
    void finMinMax_max_in_subtree() throws Exception {
        assertTrue(tree.FinMinMax(node_9, true) == node_15);
    }
    @Test
    @DisplayName("3) Поиск минимума от корня")
    void finMinMax_min_from_root() throws Exception {
        assertTrue(tree.FinMinMax(tree.Root, false) == node_6);
    }
    @Test
    @DisplayName("4) Поиск максимума от корня")
    void finMinMax_max_from_root() throws Exception {
        assertTrue(tree.FinMinMax(tree.Root, true) == node_35);
    }
    @Test
    @DisplayName("5) Поиск минимума в пустом дереве")
    void finMinMax_min_empty_tree() throws Exception {
        assertTrue(emptyTree.FinMinMax(null, false) == null);
    }
    @Test
    @DisplayName("6) Поиск максимума в пустом дереве")
    void finMinMax_max_empty_tree() throws Exception {
        assertTrue(emptyTree.FinMinMax(null, true) == null);
    }
    @Test
    @DisplayName("7) Поиск максимума по дереву только из корня")
    void finMinMax_max_only_root() throws Exception {
        assertTrue(onlyRootTree.FinMinMax(onlyRootNode, true) == onlyRootNode);
    }
    @Test
    @DisplayName("8) Поиск минимума по дереву только из корня")
    void finMinMax_min_only_root() throws Exception {
        assertTrue(onlyRootTree.FinMinMax(onlyRootNode, false) == onlyRootNode);
    }
}