package ex_2.test_version;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddKeyValue_Test {
    // Задаем узлы и деревья, которые будем использовать в тестах
    BSTNode<Integer> rootNode = new BSTNode<>(10, 10, null); // задаем корень
    BSTNode<Integer> node_55 = new BSTNode<>(55, 55, null);
    BST<Integer> tree = new BST<>(rootNode); // задаем непустое дерево
    BST<Integer> emptyTree = new BST<>(null); // задаем пустое дерево

    BSTNode<Integer> node_8 = new BSTNode<>(8, 8, rootNode);
    BSTNode<Integer> node_7 = new BSTNode<>(7, 7, node_8);
    BSTNode<Integer> node_9 = new BSTNode<>(9, 9, node_8);
    BSTNode<Integer> node_20 = new BSTNode<>(20, 20, rootNode);
    BSTNode<Integer> node_15 = new BSTNode<>(15, 15, null);
    BSTNode<Integer> node_30 = new BSTNode<>(30, 30, null);
    BSTNode<Integer> node_25 = new BSTNode<>(25, 25, null);
    BSTNode<Integer> node_35 = new BSTNode<>(35, 35, null);

    @BeforeEach
        // предустановки перед каждым тестом - связываем узлы между собой до метода с добавлением
    void setup() throws Exception {
        // задаем детей - устанавливаем связи между узлами дерева
        rootNode.LeftChild = node_8;
        node_8.Parent = rootNode;
        rootNode.RightChild = node_20;
        node_20.Parent = rootNode;
        node_8.LeftChild = node_7;
        node_7.Parent = node_8;
        node_8.RightChild = node_9;
        node_9.Parent = node_8;
    }
    @Test
    @DisplayName("1) Дерево без корня, добавляем новый ключ")
    void addKeyValue_empty_tree() throws Exception {
        emptyTree.AddKeyValue(55, 55);
        assertTrue(emptyTree.Root.NodeKey == node_55.NodeKey); // проверили, что узел является узлом
        assertTrue(emptyTree.Root.Parent == null); // проверили родителя узла
    }

    @Test
    @DisplayName("2) Добавить новый ключ левым потомком")
    void addKeyValue_new_key_as_left_child() throws Exception {
        // Сначала проверить, что у узла 20 нет потомков - т.е. в дереве нет добавляемого ключа
       assertTrue(node_20.LeftChild == null);
       assertTrue(node_20.RightChild == null);

       tree.AddKeyValue(15, 15);
       // проверить, что добавленный узел - левый потомок у 20 и родитель у добавленного верный
       assertTrue(node_20.LeftChild.NodeKey == node_15.NodeKey);
       assertTrue(node_20.LeftChild.Parent == node_20);
    }

    @Test
    @DisplayName("3) Добавить новый ключ правым потомком")
    void addKeyValue_new_key_as_right_child() throws Exception {
        // Сначала проверить, что у узла 20 нет потомков - т.е. в дереве нет добавляемого ключа
        assertTrue(node_20.LeftChild == null);
        assertTrue(node_20.RightChild == null);

        tree.AddKeyValue(30, 30);
        // проверить, что добавленный узел - правый потомок у 20 и родитель у добавленного верный
        assertTrue(node_20.RightChild.NodeKey == node_30.NodeKey);
        assertTrue(node_20.RightChild.Parent == node_20);
    }

    @Test
    @DisplayName("4) Добавить ключ, который уже есть в дереве")
    void addKeyValue_key_already_exist() throws Exception {
        // Проверить, что такой ключ уже есть
        assertTrue(node_8.RightChild.NodeKey == 9);

        tree.AddKeyValue(9, 9);
        // Проверить, что все узлы остались теми же, с теми же связями
        assertTrue(rootNode.LeftChild == node_8);
        assertTrue(node_8.Parent == rootNode);
        assertTrue(rootNode.RightChild == node_20);
        assertTrue(node_20.Parent == rootNode);
        assertTrue(node_8.LeftChild == node_7);
        assertTrue(node_7.Parent == node_8);
        assertTrue(node_8.RightChild == node_9);
        assertTrue(node_9.Parent == node_8);
    }

    @Test
    @DisplayName("5) Добавить 3 новых ключа в дерево")
    void addKeyValue_three_new_keys() throws Exception {
        // добавили 3 ключа
        tree.AddKeyValue(30, 30);
        tree.AddKeyValue(25, 25);
        tree.AddKeyValue(35, 35);
        // проверяем связи у потомков начиная от узла 20
        assertTrue(node_20.RightChild.NodeKey == node_30.NodeKey);
        assertTrue(node_20.LeftChild == null);
        assertTrue(node_20.RightChild.Parent.NodeKey == node_20.NodeKey);

        assertTrue( node_20.RightChild.LeftChild.NodeKey == node_25.NodeKey); // мы не добавляем сам узел node_30, создается новый с key = 30
        assertTrue(node_20.RightChild.RightChild.NodeKey == node_35.NodeKey);
        assertTrue(node_20.RightChild.LeftChild.Parent.NodeKey == node_30.NodeKey);
        assertTrue(node_20.RightChild.RightChild.Parent.NodeKey == node_30.NodeKey);
    }
}