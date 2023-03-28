package ex_1.test_version;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeleteNode_Test { // проверка удаления узла из дерева
    SimpleTreeNode<Integer> rootNode  = new SimpleTreeNode<>(5, null);
    SimpleTree<Integer> treeTest = new SimpleTree<>(rootNode);

    SimpleTreeNode<Integer> node1 = new SimpleTreeNode<>(10, null);
    SimpleTreeNode<Integer> node2 = new SimpleTreeNode<>(15, null);
    SimpleTreeNode<Integer> node3 = new SimpleTreeNode<>(20, null);

    @Test
    @DisplayName("1) Если удалить узел, всё его поддерево уходит из дерева")
    void deleteNode_subtree_is_not_exist() throws Exception{
        treeTest.AddChild(rootNode, node1);
        treeTest.AddChild(node1, node2);
        treeTest.AddChild(node1, node3);

        treeTest.DeleteNode(node1);
        assertFalse(treeTest.contains(node1));
        assertFalse(treeTest.contains(node2));
        assertFalse(treeTest.contains(node3));
    }

    @Test
    @DisplayName("2) Если удалить корень, дерево пустое")
    void deleteNode_tree_is_empty_after_root_delete() throws Exception{
        treeTest.AddChild(rootNode, node1);
        treeTest.AddChild(node1, node2);
        treeTest.AddChild(node1, node3);

        treeTest.DeleteNode(rootNode);
        assertFalse(treeTest.contains(node1), "node1 should not be in the tree");
        assertFalse(treeTest.contains(node2), "node2 should not be in the tree");
        assertFalse(treeTest.contains(node3), "node3 should not be in the tree");
        assertFalse(treeTest.contains(rootNode), "rootNode should not be in the tree");
    }

    @Test
    @DisplayName("3) После удаления узла его нет в списке потомков")
    void deleteNode_children_list_without_deleted_node() throws Exception{
        treeTest.AddChild(rootNode, node1);

        treeTest.DeleteNode(node1);
        assertFalse(rootNode.Children.contains(node1));
    }

    @Test
    @DisplayName("4) Удалить узел, которого нет в дереве")
    void deleteNode_if_node_is_not_exist() throws Exception{
        treeTest.AddChild(rootNode, node1);
        treeTest.AddChild(rootNode, node2);

        treeTest.DeleteNode(node3);
        // проверить, что другие узлы не были удалены при этом
        assertTrue(treeTest.Root.Children.contains(node1));
        assertTrue(treeTest.Root.Children.contains(node2));
    }

    @Test
    @DisplayName("5) Удалить null-узел")
    void deleteNode_null_node() throws Exception{
        treeTest.AddChild(rootNode, node1);
        treeTest.AddChild(rootNode, node2);

        treeTest.DeleteNode(null);
        // проверить, что другие узлы не были удалены при этом
        assertTrue(treeTest.Root.Children.contains(node1));
        assertTrue(treeTest.Root.Children.contains(node2));
    }

    @Test
    @DisplayName("6) Удалить только корень")
    void deleteNode_only_root() throws Exception{
        treeTest.DeleteNode(rootNode);

        assertTrue(treeTest.Root == null);
    }
}