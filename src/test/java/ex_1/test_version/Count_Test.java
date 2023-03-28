package ex_1.test_version;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Count_Test {
    SimpleTreeNode<Integer> rootNode  = new SimpleTreeNode<>(5, null);
    SimpleTree<Integer> treeTest = new SimpleTree<>(rootNode);

    SimpleTreeNode<Integer> node1 = new SimpleTreeNode<>(10, null);
    SimpleTreeNode<Integer> node2 = new SimpleTreeNode<>(15, null);
    SimpleTreeNode<Integer> node3 = new SimpleTreeNode<>(20, null);
    SimpleTreeNode<Integer> node4 = new SimpleTreeNode<>(25, null);

    @Test
    @DisplayName("1) Дерево пустое")
    void count_empty_tree() throws Exception {
        treeTest.DeleteNode(rootNode);

        assertTrue(treeTest.Count() == 0);
    }

    @Test
    @DisplayName("2) У дерева только корень")
    void count_only_root() throws Exception {
        assertTrue(treeTest.Count() == 1);
    }

    @Test
    @DisplayName("3) У дерева несколько узлов")
    void count_few_nodes() throws Exception {
        treeTest.AddChild(rootNode, node1);
        treeTest.AddChild(rootNode, node2);
        treeTest.AddChild(node2, node3);
        treeTest.AddChild(node2, node4);

        assertTrue(treeTest.Count() == 5);
    }

    @Test
    @DisplayName("4) У дерева большая степень вложенности узлов")
    void count_multilevel_nodes() throws Exception {
        treeTest.MultiLevelNodes(1000); // к корню еще n уровней вложенности, итого n + 1 узлов

        assertTrue(treeTest.Count() == 1001);
    }
}