package ex_1.test_version;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FindNodesByValue_Test {
    SimpleTreeNode<Integer> rootNode  = new SimpleTreeNode<>(5, null);
    SimpleTree<Integer> treeTest = new SimpleTree<>(rootNode);

    SimpleTreeNode<Integer> node1 = new SimpleTreeNode<>(10, null);
    SimpleTreeNode<Integer> node2 = new SimpleTreeNode<>(15, null);
    SimpleTreeNode<Integer> node3 = new SimpleTreeNode<>(20, null);
    SimpleTreeNode<Integer> node4 = new SimpleTreeNode<>(20, null);

    @Test
    @DisplayName("1) Узлов с искомым значением в дереве нет")
    void findNodesByValue_no_such_vals() throws Exception {
        treeTest.AddChild(rootNode, node1);
        treeTest.AddChild(rootNode, node2);
        treeTest.AddChild(rootNode, node3);
        // Размер списка с узлами искомого значения равен нулю
        assertTrue(treeTest.FindNodesByValue(45).size() == 0);
    }

    @Test
    @DisplayName("2) Узел с искомым значением всего один")
    void findNodesByValue_just_one_node() throws Exception {
        treeTest.AddChild(rootNode, node1);
        treeTest.AddChild(rootNode, node2);
        treeTest.AddChild(rootNode, node3);
        // Размер списка с узлами искомого значения равен единице
        assertTrue(treeTest.FindNodesByValue(15).size() == 1);
    }

    @Test
    @DisplayName("3) Узлов с искомым значением несколько")
    void findNodesByValue_few_nodes() throws Exception {
        treeTest.AddChild(rootNode, node1);
        treeTest.AddChild(rootNode, node2);
        treeTest.AddChild(rootNode, node3);
        treeTest.AddChild(node3, node4);
        // Размер списка с узлами искомого значения равен двум
        assertTrue(treeTest.FindNodesByValue(20).size() == 2);
    }

    @Test
    @DisplayName("4) Дерево без корня")
    void findNodesByValue_no_root_tree() throws Exception {
        treeTest.DeleteNode(rootNode);
        // Если дерево без корня, то список и не создается
        assertTrue(treeTest.FindNodesByValue(59) == null);
    }
}