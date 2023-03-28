package ex_1.test_version;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoveNode_Test {
    SimpleTreeNode<Integer> rootNode  = new SimpleTreeNode<>(5, null);
    SimpleTree<Integer> treeTest = new SimpleTree<>(rootNode);

    SimpleTreeNode<Integer> node1 = new SimpleTreeNode<>(10, null);
    SimpleTreeNode<Integer> node2 = new SimpleTreeNode<>(15, null);
    SimpleTreeNode<Integer> node3 = new SimpleTreeNode<>(20, null);
    SimpleTreeNode<Integer> node4 = new SimpleTreeNode<>(25, null);

    @Test
    @DisplayName("1) Проверка списков потомков у старого и нового родителя после перемещения")
    void moveNode_children_lists_check() throws Exception {
        treeTest.AddChild(rootNode, node1);
        treeTest.AddChild(rootNode, node2);
        treeTest.AddChild(node2, node3);
        treeTest.AddChild(node2, node4);

        treeTest.MoveNode(node2, node1);
        // Проверка списка Children у старого родителя
        assertTrue(rootNode.Children.size() == 1); // изменение размера списка после удаления элемента
        assertTrue(rootNode.Children.contains(node1)); // в списке потомков у корня только node1
        // Проверка списка Children у нового родителя
        assertTrue(node1.Children.size() == 1); // у node1 появился список потомков и там - наш узел
        assertTrue(node1.Children.contains(node2)); // проверяем, что в списке потомков именно перемещаемый узел
    }

    @Test
    @DisplayName("2) Проверка родителя перемещаемого узла")
    void moveNode_parents_check() throws Exception {
        treeTest.AddChild(rootNode, node1);
        treeTest.AddChild(rootNode, node2);
        treeTest.AddChild(node2, node3);
        treeTest.AddChild(node2, node4);

        treeTest.MoveNode(node2, node1);
        // Проверка нового родителя
        assertTrue(node2.Parent == node1);
    }

    @Test
    @DisplayName("3) Корректная отработка метода при пустом дереве")
    void moveNode_empty_tree() throws Exception {
        treeTest.DeleteNode(rootNode);

        treeTest.MoveNode(node1, rootNode);
        assertTrue(node1.Parent == null); // к перемещаемому узлу ничего в качестве родителя не подтянулось
        assertTrue(treeTest.GetAllNodes() == null); // список узлов дерева равен null
    }

    @Test
    @DisplayName("4) Перемещение корневого узла, где он в дереве один")
    void moveNode_root_no_children_move() throws Exception {
        treeTest.MoveNode(rootNode, rootNode);

        assertTrue(treeTest.Root == rootNode); // проверка того, что корень на том же месте
        assertTrue(treeTest.Root.Parent == null); // что у корня не появилось никаких родителей
        assertTrue(treeTest.Root.Children == null); // что у корня не появилось никаких потомков
    }

    @Test
    @DisplayName("5) Перемещение корневого узла, когда он с потомками")
    void moveNode_root_with_children_move() throws Exception {
        treeTest.AddChild(rootNode, node1);
        treeTest.AddChild(rootNode, node2);
        treeTest.AddChild(node2, node3);
        treeTest.AddChild(node2, node4);

        treeTest.MoveNode(rootNode, rootNode);

        assertTrue(treeTest.Root == rootNode); // проверка того, что корень на том же месте
        assertTrue(treeTest.Root.Parent == null); // что у корня не появилось никаких родителей
        assertTrue(treeTest.Root.Children.size() == 2); // что у корня тот же список потомков
        // И потомки в списке - те же
        assertTrue(treeTest.Root.Children.contains(node1));
        assertTrue(treeTest.Root.Children.contains(node2));
    }

    @Test
    @DisplayName("6) Перемещаемый узел == null")
    void moveNode_originalNode_is_null() throws Exception {
        treeTest.AddChild(rootNode, node1);
        treeTest.AddChild(rootNode, node2);
        treeTest.AddChild(node2, node3);
        treeTest.AddChild(node2, node4);

        treeTest.MoveNode(null, rootNode);
        assertTrue(treeTest.GetAllNodes().size() == 5); // число узлов в дереве то же самое
    }

    @Test
    @DisplayName("7) Хотим переместить узел в null")
    void moveNode_newParent_is_null() throws Exception {
        treeTest.AddChild(rootNode, node1);
        treeTest.AddChild(rootNode, node2);
        treeTest.AddChild(node2, node3);
        treeTest.AddChild(node2, node4);

        treeTest.MoveNode(node2, null);
        assertTrue(treeTest.GetAllNodes().size() == 5); // число узлов в дереве то же самое
    }

    @Test
    @DisplayName("8) Обход узлов поддерева")
    void moveNode_subTree() throws Exception {
        treeTest.AddChild(rootNode, node1);
        treeTest.AddChild(rootNode, node2);
        treeTest.AddChild(node2, node3);
        treeTest.AddChild(node2, node4);

        assertTrue(treeTest.GetAllNodesSubTree(node2).size() == 3); // число узлов вместе с вершиной
        // Поддерево с вершиной содержит все узлы
        assertTrue(treeTest.GetAllNodesSubTree(node2).contains(node2));
        assertTrue(treeTest.GetAllNodesSubTree(node2).contains(node3));
        assertTrue(treeTest.GetAllNodesSubTree(node2).contains(node4));
    }

    @Test
    @DisplayName("9) Обход узлов поддерева после перемещения")
    void moveNode_subTree_after_move() throws Exception {
        treeTest.AddChild(rootNode, node1);
        treeTest.AddChild(rootNode, node2);
        treeTest.AddChild(node2, node3);
        treeTest.AddChild(node2, node4);

        assertTrue(treeTest.GetAllNodesSubTree(node2).size() == 3); // число узлов вместе с вершиной
        // Поддерево с вершиной содержит все узлы
        assertTrue(treeTest.GetAllNodesSubTree(node2).contains(node2));
        assertTrue(treeTest.GetAllNodesSubTree(node2).contains(node3));
        assertTrue(treeTest.GetAllNodesSubTree(node2).contains(node4));

        // Переместили узел
        treeTest.MoveNode(node2, node1);

        // Проверили все заново
        assertTrue(treeTest.GetAllNodesSubTree(node2).size() == 3); // число узлов вместе с вершиной
        // Поддерево с вершиной содержит все узлы
        assertTrue(treeTest.GetAllNodesSubTree(node2).contains(node2));
        assertTrue(treeTest.GetAllNodesSubTree(node2).contains(node3));
        assertTrue(treeTest.GetAllNodesSubTree(node2).contains(node4));
    }

    @Test
    @DisplayName("10) Перемещение узла в себя же")
    void moveNode_self_move() throws Exception {
        treeTest.AddChild(rootNode, node1);
        treeTest.AddChild(rootNode, node2);
        treeTest.AddChild(node2, node3);
        treeTest.AddChild(node2, node4);

        treeTest.MoveNode(node2, node2);
        assertTrue(treeTest.GetAllNodes().size() == 5); // число узлов в дереве то же самое
    }
}