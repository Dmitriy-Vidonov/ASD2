package ex_1.test_version;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;

class Test_addChild { // проверяем метод addChild() - по сути наличие добавленного узла в списке Children

        SimpleTreeNode<Integer> rootNode  = new SimpleTreeNode<>(5, null);
        SimpleTree<Integer> treeTest = new SimpleTree<>(rootNode);

        SimpleTreeNode<Integer> node1 = new SimpleTreeNode<>(10, null);
        SimpleTreeNode<Integer> node2 = new SimpleTreeNode<>(15, null);
        SimpleTreeNode<Integer> node3 = new SimpleTreeNode<>(20, null);

    @org.junit.jupiter.api.Test
    @DisplayName("1) Добавить узел к корню")
    void addChild_to_the_root() throws Exception {
        treeTest.AddChild(rootNode, node1);
        Assertions.assertTrue(rootNode.Children.contains(node1));

        treeTest.AddChild(rootNode, node2);
        Assertions.assertTrue(rootNode.Children.contains(node2));
    }

    @org.junit.jupiter.api.Test
    @DisplayName("2) Добавить узел к null-предку") // при этом список детей корня не должен существовать
    void addChild_to_null() throws Exception {
        treeTest.AddChild(null, node1);
        Assertions.assertTrue(treeTest.Root.Children == null);
    }

    @org.junit.jupiter.api.Test
    @DisplayName("3) Добавить узел не к корню") // при этом список детей корня не должен существовать
    void addChild_to_non_root() throws Exception {
        treeTest.AddChild(rootNode, node1);
        treeTest.AddChild(node1, node2);

        Assertions.assertTrue(node1.Children.contains(node2));
    }

    @org.junit.jupiter.api.Test
    @DisplayName("4) У нового узла в родителях нужный узел")
    void addChild_is_Parent_node_correct() throws Exception {
        treeTest.AddChild(rootNode, node1);
        treeTest.AddChild(node1, node2);

        Assertions.assertTrue(node1.Parent == rootNode);
        Assertions.assertTrue(node2.Parent == node1);
    }

    @org.junit.jupiter.api.Test
    @DisplayName("5) У родителя в Children новый узел в конце списка")
    void addChild_is_Parent_Children_list_with_new_node_at_the_end() throws Exception {
        treeTest.AddChild(rootNode, node1);
        treeTest.AddChild(rootNode, node2);
        treeTest.AddChild(rootNode, node3);

        Assertions.assertTrue(rootNode.Children.get(2) == node3);
    }

    @org.junit.jupiter.api.Test
    @DisplayName("6) У родителя в Children все добавленные узлы")
    void addChild_is_Parent_Children_list_contains_all_nodes() throws Exception {
        treeTest.AddChild(rootNode, node1);
        treeTest.AddChild(rootNode, node2);
        treeTest.AddChild(rootNode, node3);

        Assertions.assertTrue(rootNode.Children.get(0) == node1);
        Assertions.assertTrue(rootNode.Children.get(1) == node2);
        Assertions.assertTrue(rootNode.Children.get(2) == node3);
    }
}