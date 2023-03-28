package ex_2.test_version;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FindNodeByKey_Test {
    // Задаем узлы и деревья, которые будем использовать в тестах
    BSTNode<Integer> rootNode = new BSTNode<>(10, 10, null); // задаем корень
    BST<Integer> tree = new BST<>(rootNode); // задаем непустое дерево
    BST<Integer> emptyTree = new BST<>(null); // задаем пустое дерево

    BSTNode<Integer> node_9 = new BSTNode<>(9, 9, rootNode);
    BSTNode<Integer> node_8 = new BSTNode<>(8, 8, node_9);
    BSTNode<Integer> node_12 = new BSTNode<>(12, 12, node_9);
    BSTNode<Integer> node_20 = new BSTNode<>(20, 20, rootNode);

    @BeforeEach // предустановки перед каждым тестом - связываем узлы между собой до метода с добавлением
    void setup() throws Exception {
        // задаем детей
        rootNode.LeftChild = node_9;
        node_9.LeftChild = node_8;
        node_9.RightChild = node_12;
        rootNode.RightChild = node_20;
    }

    @Test
    @DisplayName("1) Пустое дерево")
    void findNodeByKey_empty_tree() throws Exception {
        assertTrue(emptyTree.FindNodeByKey(9) == null);
    }

    @Test
    @DisplayName("2) Ключа в дереве нет - добавить к левому потомку")
    void findNodeByKey_no_key_left_child() throws Exception {
         assertTrue(tree.FindNodeByKey(7).ToLeft == true); // добавить ключ в качестве левого потомка
         assertTrue(tree.FindNodeByKey(7).NodeHasKey == false); // ключа нет в узле
         assertTrue(tree.FindNodeByKey(7).Node == node_8); // указание, что добавить ключ надо к node_8
    }

    @Test
    @DisplayName("3) Ключа в дереве нет - добавить к правому потомку")
    void findNodeByKey_no_key_right_child() throws Exception {
        assertTrue(tree.FindNodeByKey(40).ToLeft == false); // добавить ключ в качестве правого потомка
        assertTrue(tree.FindNodeByKey(40).NodeHasKey == false); // ключа нет в узле
        assertTrue(tree.FindNodeByKey(40).Node == node_20); // указание, что добавить ключ надо к node_12
    }

    @Test
    @DisplayName("4) Ищем существующий ключ") // этот же тест идет как поиск узла с ключом меньше ключа у корня
    void findNodeByKey_key_exist() throws Exception {
        assertTrue(tree.FindNodeByKey(8).NodeHasKey == true); // ключ есть в узле
        assertTrue(tree.FindNodeByKey(8).Node == node_8); // указание на правильный узел
    }

    @Test
    @DisplayName("5) Ищем ключ больше корневого ключа") // этот же тест идет как поиск узла с ключом меньше ключа у корня
    void findNodeByKey_greater_than_root() throws Exception {
        assertTrue(tree.FindNodeByKey(40).NodeHasKey == false); // такого ключа изначально нет
        assertTrue(tree.FindNodeByKey(40).ToLeft == false); // добавить в качестве правого потомка
        assertTrue(tree.FindNodeByKey(40).Node == node_20); // родительский узел = node_20
    }
}