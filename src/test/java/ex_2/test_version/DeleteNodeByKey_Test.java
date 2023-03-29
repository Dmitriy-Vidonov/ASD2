package ex_2.test_version;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeleteNodeByKey_Test {
    BST<Integer> emptyTree = new BST<>(null); // пустое дерево без корня
    BSTNode<Integer> rootNode = new BSTNode<>(8, 8, null); // задаем корень непустого дерева
    BST<Integer> tree = new BST<>(rootNode); // непустое дерево
    BSTNode<Integer> onlyRootNode = new BSTNode<>(10, 10, null); // корень для дерева только из одного корня
    BST<Integer> onlyRootTree = new BST<>(onlyRootNode); // задаем непустое дерево, в котором только корень и все
    // Задаем используемые ключи
    BSTNode<Integer> node_1 = new BSTNode<>(1, 1, null);
    BSTNode<Integer> node_2 = new BSTNode<>(2, 2, null);
    BSTNode<Integer> node_4 = new BSTNode<>(4, 4, null);
    BSTNode<Integer> node_6 = new BSTNode<>(6, 6, null);
    BSTNode<Integer> node_10 = new BSTNode<>(10, 10, null);
    BSTNode<Integer> node_12 = new BSTNode<>(12, 12, null);
    BSTNode<Integer> node_13 = new BSTNode<>(13, 13, null);
    BSTNode<Integer> node_14 = new BSTNode<>(14, 14, null);
    BSTNode<Integer> node_15 = new BSTNode<>(15, 15, null);
    BSTNode<Integer> node_18 = new BSTNode<>(18, 18, null);

    // Установка начальных значений - по сути сборка нового дерева
    @BeforeEach
    void setUp() throws Exception {
        // связываем узлы между собой
        // левая часть дерева
        rootNode.LeftChild = node_4;
        node_4.Parent = rootNode;
        rootNode.RightChild = node_12;
        node_12.Parent = rootNode;
        node_4.LeftChild = node_2;
        node_2.Parent = node_4;
        node_4.RightChild = node_6;
        node_6.Parent = node_4;
        node_2.LeftChild = node_1;
        node_1.Parent = node_2;
        // правая часть дерева
        rootNode.RightChild = node_12;
        node_12.Parent = rootNode;
        node_12.LeftChild = node_10;
        node_10.Parent = node_12;
        node_12.RightChild = node_14;
        node_14.Parent = node_12;
        node_14.LeftChild = node_13;
        node_13.Parent = node_14;
        node_14.RightChild = node_15;
        node_15.Parent = node_14;
        node_15.RightChild = node_18;
        node_18.Parent = node_15;
    }
    @Test
    @DisplayName("1) Удаление листа, если он есть")
    void deleteNodeByKey_existing_leaf() throws Exception {
        // 1) Проверяем наличие узла перед удалением
        assertTrue(node_2.LeftChild == node_1);
        assertTrue(node_1.Parent == node_2);
        // Произвели удаление
        boolean res = tree.DeleteNodeByKey(1);
        // 2) Отсутствие узла после удаления
        assertFalse(node_2.LeftChild == node_1);
        assertFalse(node_1.Parent == node_2);
        // 3) Результат работы метода
        assertEquals(res, true);
/*
        // упрощенная проверка по еще паре листов
        boolean res2 = tree.DeleteNodeByKey(13);
        assertTrue(node_14.LeftChild == null);
        assertEquals(res2, true);

        boolean res3 = tree.DeleteNodeByKey(15);
        assertTrue(node_14.RightChild == null);
        assertEquals(res3, true);*/
    }

    @Test
    @DisplayName("2) Удаление отсутствующего листа")
    void deleteNodeByKey_non_existing_key() throws Exception {
        // Произвели удаление
        boolean res = tree.DeleteNodeByKey(20);
        // Результат работы метода
        assertEquals(res, false);
    }
    @Test
    @DisplayName("3) Удаление узла с одним левым потомком")
    void deleteNodeByKey_node_with_one_left_child() throws Exception {
        // 1) Проверяем наличие узла перед удалением
        assertTrue(node_4.LeftChild == node_2);
        assertTrue(node_2.Parent == node_4);
        assertTrue(node_2.LeftChild == node_1);
        assertTrue(node_1.Parent == node_2);
        // Произвели удаление
        boolean res = tree.DeleteNodeByKey(2);
        // 2) Отсутствие узла после удаления
        assertFalse(node_4.LeftChild == node_2);
        assertFalse(node_2.Parent == node_4);
        assertFalse(node_2.LeftChild == node_1);
        assertFalse(node_1.Parent == node_2);
        assertTrue(node_4.LeftChild == node_1);
        assertTrue(node_1.Parent == node_4);
        // 3) Результат работы метода
        assertEquals(res, true);
    }
    @Test
    @DisplayName("3.1) Удаление узла с одним правым потомком")
    void deleteNodeByKey_node_with_one_right_child() throws Exception {
        // 1) Проверяем наличие узла перед удалением
        assertTrue(node_15.RightChild == node_18);
        assertTrue(node_18.Parent == node_15);
        assertTrue(node_15.LeftChild == null);
        // Произвели удаление
        boolean res = tree.DeleteNodeByKey(15);
        // 2) Отсутствие узла после удаления
        assertTrue(node_14.RightChild == node_18);
        assertTrue(node_18.Parent == node_14);
        assertTrue(node_18.LeftChild == null);
        assertTrue(node_18.RightChild == null);
        // 3) Результат работы метода
        assertEquals(res, true);
    }
    @Test
    @DisplayName("4) Удаление узла с двумя потомками")
    void deleteNodeByKey_node_with_both_children() throws Exception {
        // 1) Проверяем наличие узла перед удалением
        assertTrue(rootNode.RightChild == node_12);
        assertTrue(node_12.Parent == rootNode);
        assertTrue(node_12.LeftChild == node_10);
        assertTrue(node_10.Parent == node_12);
        assertTrue(node_12.RightChild == node_14);
        assertTrue(node_14.Parent == node_12);
        // Произвели удаление
        boolean res = tree.DeleteNodeByKey(12);
        // 2) Отсутствие узла после удаления
        assertFalse(rootNode.RightChild == node_12);
        assertFalse(node_12.Parent == rootNode);
        assertFalse(node_12.LeftChild == node_10);
        assertFalse(node_10.Parent == node_12);
        assertFalse(node_12.RightChild == node_14);
        assertFalse(node_14.Parent == node_12);

        assertTrue(rootNode.RightChild == node_13);
        assertTrue(node_13.Parent == rootNode);
        assertTrue(node_13.LeftChild == node_10);
        assertTrue(node_10.Parent == node_13);
        assertTrue(node_13.RightChild == node_14);
        assertTrue(node_14.Parent == node_13);

        assertTrue(node_14.LeftChild == null);
        assertTrue(node_14.RightChild == node_15);
        assertTrue(node_15.Parent == node_14);

        // 3) Результат работы метода
        assertEquals(res, true);
    }

    @Test
    @DisplayName("5) Удаление листа из пустого дерева")
    void deleteNodeByKey_empty_tree() throws Exception {
        // Произвели удаление
        boolean res = emptyTree.DeleteNodeByKey(12);
        // Результат работы метода
        assertEquals(res, false);
    }

    @Test
    @DisplayName("6) Попытка удалить корень дерева (без потомков")
    void deleteNodeByKey_root_no_children() throws Exception {
        // 1) Проверяем наличие узла перед удалением
        assertTrue(onlyRootTree.Root == onlyRootNode);
        // Произвели удаление
        boolean res = onlyRootTree.DeleteNodeByKey(10); // удаляем по сути единственный узел - корень с ключом 10
        // 2) Отсутствие узла после удаления
        assertTrue(onlyRootTree.Root == null);
        // Результат работы метода
        assertEquals(res, true);
    }

    @Test
    @DisplayName("7) Удаление корня, у которого есть потомки")
    void deleteNodeByKey_root_all_children() throws Exception {
        // 1) Проверяем наличие узла перед удалением
        assertTrue(tree.Root == rootNode);
        assertTrue(rootNode.LeftChild == node_4);
        assertTrue(rootNode.RightChild == node_12);
        assertTrue(node_4.Parent == rootNode);
        assertTrue(node_12.Parent == rootNode);
        // Произвели удаление
        boolean res = tree.DeleteNodeByKey(8); // удалили корень дерева
        // 2) Отсутствие корня после удаления - вместо него расположен node_10
        assertTrue(tree.Root == node_10); // старого корня нет, теперь корень это node_10
        assertTrue(node_10.LeftChild == node_4);
        assertTrue(node_10.RightChild == node_12);
        assertTrue(node_4.Parent == node_10);
        assertTrue(node_12.Parent == node_10);

        assertFalse(tree.Root == rootNode);
        assertFalse(node_4.Parent == rootNode);
        assertFalse(node_12.Parent == rootNode);

        // Результат работы метода
        assertEquals(res, true);
    }
}