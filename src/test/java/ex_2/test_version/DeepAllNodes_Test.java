package ex_2.test_version;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DeepAllNodes_Test {

    BSTNode<Integer> rootNode = new BSTNode<>(100, 100, null);
    BST<Integer> tree = new BST<>(rootNode);
    BST<Integer> emptyTree = new BST<>(null);
    BSTNode<Integer> node_90 = new BSTNode<>(90, 90, null);
    BSTNode<Integer> node_80 = new BSTNode<>(80, 80, null);
    BSTNode<Integer> node_85 = new BSTNode<>(85, 85, null);
    BSTNode<Integer> node_95 = new BSTNode<>(95, 95, null);
    BSTNode<Integer> node_150 = new BSTNode<>(150, 150, null);
    BSTNode<Integer> node_140 = new BSTNode<>(140, 140, null);
    BSTNode<Integer> node_130 = new BSTNode<>(130, 130, null);
    BSTNode<Integer> node_145 = new BSTNode<>(145, 145, null);

    @BeforeEach
    void setUp() throws Exception {
        // Выстраиваем связи между узлами - создаем нужное нам дерево
        rootNode.LeftChild = node_90;
        node_90.Parent = rootNode;

        node_90.LeftChild = node_80;
        node_80.Parent = node_90;

        node_90.RightChild = node_95;
        node_95.Parent = node_90;

        node_80.RightChild = node_85;
        node_85.Parent = node_80;

        rootNode.RightChild = node_150;
        node_150.Parent = rootNode;

        node_150.LeftChild = node_140;
        node_140.Parent = node_150;

        node_140.LeftChild = node_130;
        node_130.Parent = node_140;

        node_140.RightChild = node_145;
        node_145.Parent = node_140;
    }

    @Test
    @DisplayName("1) Обход в глубину in-order (инфиксный обход)")
    void deepAllNodes_in_order() throws Exception {
        ArrayList<BSTNode> nodeList = tree.DeepAllNodes(0);
        // Проверили значение каждого элемента итогового списка согласно обходу in-order
        assertTrue(nodeList.get(0).NodeKey == 80
                            && nodeList.get(1).NodeKey == 85
                            && nodeList.get(2).NodeKey == 90
                            && nodeList.get(3).NodeKey == 95
                            && nodeList.get(4).NodeKey == 100
                            && nodeList.get(5).NodeKey == 130
                            && nodeList.get(6).NodeKey == 140
                            && nodeList.get(7).NodeKey == 145
                            && nodeList.get(8).NodeKey == 150);
        // Проверили итоговый размер списка
        assertTrue(nodeList.size() == 9);
    }

    @Test
    @DisplayName("2) Обход в глубину post-order (постфиксный обход)")
    void deepAllNodes_post_order() throws Exception {
        ArrayList<BSTNode> nodeList = tree.DeepAllNodes(1);
        // Проверили значение каждого элемента итогового списка согласно обходу in-order
        assertTrue(nodeList.get(0).NodeKey == 85
                            && nodeList.get(1).NodeKey == 80
                            && nodeList.get(2).NodeKey == 95
                            && nodeList.get(3).NodeKey == 90
                            && nodeList.get(4).NodeKey == 130
                            && nodeList.get(5).NodeKey == 145
                            && nodeList.get(6).NodeKey == 140
                            && nodeList.get(7).NodeKey == 150
                            && nodeList.get(8).NodeKey == 100);
        // Проверили итоговый размер списка
        assertTrue(nodeList.size() == 9);
    }

    @Test
    @DisplayName("3) Обход в глубину pre-order (префиксный обход)")
    void deepAllNodes_pre_order() throws Exception {
        ArrayList<BSTNode> nodeList = tree.DeepAllNodes(2);
        // Проверили значение каждого элемента итогового списка согласно обходу in-order
        assertTrue(nodeList.get(0).NodeKey == 100
                            && nodeList.get(1).NodeKey == 90
                            && nodeList.get(2).NodeKey == 80
                            && nodeList.get(3).NodeKey == 85
                            && nodeList.get(4).NodeKey == 95
                            && nodeList.get(5).NodeKey == 150
                            && nodeList.get(6).NodeKey == 140
                            && nodeList.get(7).NodeKey == 130
                            && nodeList.get(8).NodeKey == 145);
        // Проверили итоговый размер списка
        assertTrue(nodeList.size() == 9);
    }

    @Test
    @DisplayName("4) Обход в глубину с пустым деревом")
    void deepAllNodes_empty_tree() throws Exception {
        // 0 - in-order
        ArrayList<BSTNode> nodeList_0 = emptyTree.DeepAllNodes(0);
        assertTrue(nodeList_0 == null);
        // 1 - post-order
        ArrayList<BSTNode> nodeList_1 = emptyTree.DeepAllNodes(1);
        assertTrue(nodeList_1 == null);
        // 2 - pre-order
        ArrayList<BSTNode> nodeList_2 = emptyTree.DeepAllNodes(2);
        assertTrue(nodeList_2 == null);
    }
}