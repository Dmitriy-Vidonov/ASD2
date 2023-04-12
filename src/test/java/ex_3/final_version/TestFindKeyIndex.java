package ex_3.final_version;

import ex_3.test3.aBST;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class TestFindKeyIndex {

    // ПУСТЫЕ ДЕРЕВЬЯ
    @Test
    @DisplayName("1) Пустые деревья. Дерево из одного узла.")
    void findKeyIndexEmptyTree() {
        ex_3.test3.aBST tree = new ex_3.test3.aBST(0);
        // Пробуем найти индекс несуществующего значения
        assertEquals(0, tree.FindKeyIndex(10));
    }

    @Test
    @DisplayName("2) Пустые деревья. Дерево из 7 узлов.")
    void findKeyIndexSevenNodesEmptyTree() {
        ex_3.test3.aBST tree = new ex_3.test3.aBST(2);
        // Пробуем найти индекс несуществующего значения
        assertEquals(0, tree.FindKeyIndex(10));
    }

    // ДЕРЕВЬЯ, ЗАПОЛНЕННЫЕ ЧАСТИЧНО
    @Test
    @DisplayName("3) Частично заполнено. Дерево из 7 узлов. Заполнен лишь корень.")
    void findKeyIndexSevenNodesOnlyRoot() {
        ex_3.test3.aBST tree = new ex_3.test3.aBST(2);
        // Добавили корень
        assertEquals(0, tree.AddKey(100));
        // Найти индекс корня
        assertEquals(0, tree.FindKeyIndex(100));
        // Найти индекс значения меньше корня
        assertEquals(-1, tree.FindKeyIndex(50));
        // Найти индекс значения больше корня
        assertEquals(-2, tree.FindKeyIndex(200));
    }

    @Test
    @DisplayName("4) Частично заполнено. Дерево из 7 узлов. Корень + левый потомок без детей.")
    void findKeyIndexSevenNodesRootPlusLeftChild() {
        ex_3.test3.aBST tree = new ex_3.test3.aBST(2);
        // Добавили корень
        assertEquals(0, tree.AddKey(100));
        // Добавили левого потомка
        assertEquals(1, tree.AddKey(50));
        // Найти индекс под левого потомка
        assertEquals(-3, tree.FindKeyIndex(25));
        // Найти индекс под правого потомка
        assertEquals(-4, tree.FindKeyIndex(55));
    }

    @Test
    @DisplayName("5) Частично заполнено. Дерево из 7 узлов. Корень + левый потомок с левым потомком.")
    void findKeyIndexSevenNodesRootPlusLeftChildLeftChild() {
        ex_3.test3.aBST tree = new ex_3.test3.aBST(2);
        // Добавили корень
        assertEquals(0, tree.AddKey(100));
        // Добавили левого потомка
        assertEquals(1, tree.AddKey(50));
        // Добавили левый лист
        assertEquals(3, tree.AddKey(25));
        // Найти индекс левого листа
        assertEquals(3, tree.FindKeyIndex(25));
        // Найти индекс ключа меньше, чем левый лист
        assertNull(tree.FindKeyIndex(15));
        // Найти индекс правого потомка от левого потомка корня
        assertEquals(-4, tree.FindKeyIndex(55));
    }

    @Test
    @DisplayName("6) Частично заполнено. Дерево из 7 узлов. Корень + левый потомок с правым потомком.")
    void findKeyIndexSevenNodesRootPlusLeftChildRightChild() {
        ex_3.test3.aBST tree = new ex_3.test3.aBST(2);
        // Добавили корень
        assertEquals(0, tree.AddKey(100));
        // Добавили левого потомка
        assertEquals(1, tree.AddKey(50));
        // Добавили правый лист к левому потомку
        assertEquals(4, tree.AddKey(55));
        // Найти индекс этого правого листа
        assertEquals(4, tree.FindKeyIndex(55));
        // Найти индекс левого потомка от родителя листа
        assertEquals(-3, tree.FindKeyIndex(25));
        // Найти индекс ключа больше правого листа, но меньше корня
        assertNull(tree.FindKeyIndex(60));
    }

    @Test
    @DisplayName("7) Частично заполнено. Дерево из 7 узлов. Корень + правый потомок без детей.")
    void findKeyIndexSevenNodesRootPlusRightChildWithoutChildren() {
        ex_3.test3.aBST tree = new ex_3.test3.aBST(2);
        // Добавили корень
        assertEquals(0, tree.AddKey(100));
        // Добавили правого потомка
        assertEquals(2, tree.AddKey(200));
        // Найти индекс правого потомка
        assertEquals(2, tree.FindKeyIndex(200));
        // Найти индекс ключа меньше правого потомка
        assertEquals(-5, tree.FindKeyIndex(150));
        // Найти индекс для правого листа
        assertEquals(-6, tree.FindKeyIndex(250));
    }

    @Test
    @DisplayName("8) Частично заполнено. Дерево из 7 узлов. Корень + правый потомок с левым листом.")
    void findKeyIndexSevenNodesRootPlusRightChildWithLeftLeaf() {
        ex_3.test3.aBST tree = new ex_3.test3.aBST(2);
        // Добавили корень
        assertEquals(0, tree.AddKey(100));
        // Добавили правого потомка
        assertEquals(2, tree.AddKey(200));
        // Добавили левый лист
        assertEquals(5, tree.AddKey(150));
        // Найти индекс для левого листа
        assertEquals(5, tree.FindKeyIndex(150));
        // Найти индекс для ключа больше правого потомка корня
        assertEquals(-6, tree.FindKeyIndex(250));
        // Найти индекс для ключа меньше левого листа и больше корня
        assertNull(tree.FindKeyIndex(155));
    }

    @Test
    @DisplayName("9) Частично заполнено. Дерево из 7 узлов. Только крайние правые значения")
    void findKeyIndexSevenNodesOnlyRightBranch() {
        ex_3.test3.aBST tree = new ex_3.test3.aBST(2);
        // Добавили корень
        assertEquals(0, tree.AddKey(100));
        // Добавили правого потомка
        assertEquals(2, tree.AddKey(200));
        // Добавили правый лист
        assertEquals(6, tree.AddKey(250));
        // Найти индекс для правого листа
        assertEquals(6, tree.FindKeyIndex(250));
        // Найти индекс меньше родителя правого листа
        assertEquals(-5, tree.FindKeyIndex(150));
        // Найти индекс для ключа больше правого узла
        assertNull(tree.FindKeyIndex(350));
    }

    // ДЕРЕВЬЯ, ЗАПОЛНЕННЫЕ ПОЛНОСТЬЮ
    @Test
    @DisplayName("10) Заполненные деревья. Дерево из 7 узлов.")
    void findKeyIndexSevenNodesCompletedTree() {
        ex_3.test3.aBST tree = new aBST(2);
        // Добавили корень
        assertEquals(0, tree.AddKey(100));
        // Добавили левого потомка
        assertEquals(1, tree.AddKey(50));
        // Добавили правого потомка
        assertEquals(2, tree.AddKey(200));
        // Добавили оставшиеся листья
        assertEquals(3, tree.AddKey(25));
        assertEquals(4, tree.AddKey(55));
        assertEquals(5, tree.AddKey(150));
        assertEquals(6, tree.AddKey(250));
        // Найти индекс корня
        assertEquals(0, tree.FindKeyIndex(100));
        // Найти все остальные индексы оставшихся элементов
        assertEquals(1, tree.FindKeyIndex(50));
        assertEquals(2, tree.FindKeyIndex(200));
        assertEquals(3, tree.FindKeyIndex(25));
        assertEquals(4, tree.FindKeyIndex(55));
        assertEquals(5, tree.FindKeyIndex(150));
        assertEquals(6, tree.FindKeyIndex(250));
        // Найти индекс для ключа меньше левого крайнего листа
        assertNull(tree.FindKeyIndex(15));
        // Найти индекс для ключа правого крайнего листа
        assertNull(tree.FindKeyIndex(350));
        // Найти индекс для ключа больше левого потомка корня
        assertNull(tree.FindKeyIndex(60));
        // Найти индекс для ключа меньше правого потомка корня
        assertNull(tree.FindKeyIndex(170));
    }

}