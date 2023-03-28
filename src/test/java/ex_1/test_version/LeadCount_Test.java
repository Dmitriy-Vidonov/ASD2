package ex_1.test_version;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LeadCount_Test {
    SimpleTreeNode<Integer> rootNode  = new SimpleTreeNode<>(5, null);
    SimpleTree<Integer> treeTest = new SimpleTree<>(rootNode);

    SimpleTreeNode<Integer> node1 = new SimpleTreeNode<>(10, null);
    SimpleTreeNode<Integer> node2 = new SimpleTreeNode<>(15, null);
    SimpleTreeNode<Integer> node3 = new SimpleTreeNode<>(20, null);
    SimpleTreeNode<Integer> node4 = new SimpleTreeNode<>(25, null);


    @Test
    @DisplayName("1) Дерево без корня")
    void leafCount_empty_tree() throws Exception {
        treeTest.DeleteNode(rootNode);

        assertTrue(treeTest.LeafCount() == 0);
    }

    @Test
    @DisplayName("2) Дерево только с корнем")
    void leafCount_only_root() throws Exception {
        assertTrue(treeTest.LeafCount() == 1);
    }

    @Test
    @DisplayName("3) У корня ряд потомков")
    void leafCount_few_root_children() throws Exception {
        treeTest.AddChild(rootNode, node1);
        treeTest.AddChild(rootNode, node2);
        treeTest.AddChild(rootNode, node3);
        treeTest.AddChild(rootNode, node4);

        assertTrue(treeTest.LeafCount() == 4);
    }

    @Test
    @DisplayName("4) Дерево с потомками потомков")
    void leafCount_few_children() throws Exception {
        treeTest.AddChild(rootNode, node1);
        treeTest.AddChild(rootNode, node2);
        treeTest.AddChild(node2, node3);
        treeTest.AddChild(node2, node4);

        assertTrue(treeTest.LeafCount() == 3);
    }
}