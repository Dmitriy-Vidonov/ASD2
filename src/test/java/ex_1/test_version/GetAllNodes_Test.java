package ex_1.test_version;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GetAllNodes_Test {
    SimpleTreeNode<Integer> rootNode  = new SimpleTreeNode<>(5, null);
    SimpleTree<Integer> treeTest = new SimpleTree<>(rootNode);

    SimpleTreeNode<Integer> node1 = new SimpleTreeNode<>(10, null);
    SimpleTreeNode<Integer> node2 = new SimpleTreeNode<>(15, null);
    SimpleTreeNode<Integer> node3 = new SimpleTreeNode<>(20, null);

    @Test
    @DisplayName("1) Дерево пустое, с удаленным root")
    void getAllNodes_tree_without_root() throws Exception {
        treeTest.DeleteNode(rootNode);

        assertTrue(treeTest.GetAllNodes() == null);
    }

    @Test
    @DisplayName("2) Дерево только с root")
    void getAllNodes_only_root() throws Exception {
        assertTrue(treeTest.GetAllNodes().size() == 1);
    }

    @Test
    @DisplayName("3) Дерево с несколькими потомками у root")
    void getAllNodes_with_root_children() throws Exception {
        treeTest.AddChild(rootNode, node1);
        treeTest.AddChild(rootNode, node2);
        treeTest.AddChild(rootNode, node3);
        // Общее число элементов в списке всех узлов = 4 (все узлы + корень)
        assertTrue(treeTest.GetAllNodes().size() == 4);
        // Проверка наличия узлов в итоговом списке
        assertTrue(treeTest.GetAllNodes().contains(rootNode));
        assertTrue(treeTest.GetAllNodes().contains(node1));
        assertTrue(treeTest.GetAllNodes().contains(node2));
        assertTrue(treeTest.GetAllNodes().contains(node3));
    }

    @Test
    @DisplayName("4) Дерево с потомками у потомков")
    void getAllNodes_various_nodes_with_children() throws Exception {
        treeTest.AddChild(rootNode, node1);
        treeTest.AddChild(node1, node2);
        treeTest.AddChild(node1, node3);
        // Общее число элементов в списке всех узлов = 4 (все узлы + корень)
        assertTrue(treeTest.GetAllNodes().size() == 4);
        // Проверка наличия узлов в итоговом списке
        assertTrue(treeTest.GetAllNodes().contains(rootNode));
        assertTrue(treeTest.GetAllNodes().contains(node1));
        assertTrue(treeTest.GetAllNodes().contains(node2));
        assertTrue(treeTest.GetAllNodes().contains(node3));
    }
}