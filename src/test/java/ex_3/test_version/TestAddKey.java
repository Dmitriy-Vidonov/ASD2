package ex_3.test_version;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestAddKey {

    // ПУСТЫЕ ДЕРЕВЬЯ
    @Test
    @DisplayName("1) Пустые деревья. Дерево из одного узла.")
    void addKeyOneNodeTree() {
        aBST tree = new aBST(0);
        // Добавили корень
        assertEquals(0, tree.AddKey(10));
        // Добавить дубль корня
        assertEquals(0, tree.AddKey(10));
        // Добавить элемент меньше корня
        assertEquals(-1, tree.AddKey(5));
        // Добавить элемент больше корня
        assertEquals(-1, tree.AddKey(50));
    }

    @Test
    @DisplayName("2) Пустые деревья. Дерево из 7 узлов.")
    void addKeyEmptySevenNodesTree() {
        aBST tree = new aBST(2);
        // Добавили корень
        assertEquals(0, tree.AddKey(10));
        // Добавить дубль корня
        assertEquals(0, tree.AddKey(10));
        // Добавить ключ меньше корня
        assertEquals(1, tree.AddKey(5));
        // Добавить ключ больше корня
        assertEquals(2, tree.AddKey(15));
        // Добавить ключ меньше левого потомка корня
        assertEquals(3, tree.AddKey(1));
        // Добавить ключ больше правого потомка корня
        assertEquals(6, tree.AddKey(20));
    }

    // ЧАСТИЧНО ЗАПОЛНЕННЫЕ ДЕРЕВЬЯ
    @Test
    @DisplayName("3) Частично заполнено. 7 узлов. Заполнен только корень.")
    void addKeyPartlyFilledOnlyRootSevenNodesTree() {
        aBST tree = new aBST(2);
        // Добавили корень
        assertEquals(0, tree.AddKey(10));
        // Добавить дубль корня
        assertEquals(0, tree.AddKey(10));
    }

    @Test
    @DisplayName("4) Частично заполнено. 7 узлов. Заполнена вся левая ветка. У родителя левого листа есть правый потомок")
    void addKeyPartlyFilledLeftBranchLeftLeafParentHasBothChildren() {
        aBST tree = new aBST(2);
        // Добавили корень
        assertEquals(0, tree.AddKey(100));
        // Добавить левого потомка корня
        assertEquals(1, tree.AddKey(50));
        // Добавить левый лист
        assertEquals(3, tree.AddKey(25));
        // Добавить правого потомка родителя левого листа
        assertEquals(4, tree.AddKey(55));
        // Добавить ключ меньше левого листа
        assertEquals(-1, tree.AddKey(15));
        // Добавить ключ больше левого листа, но меньше его родителя
        assertEquals(-1, tree.AddKey(30));
        // Добавить дубль левого листа
        assertEquals(3, tree.AddKey(25));
        // Добавить ключ больше родителя левого листа, но меньше правого потомка родителя левого листа
        assertEquals(-1, tree.AddKey(53));
        // Добавить ключ больше правого потомка родителя левого листа
        assertEquals(-1, tree.AddKey(60));
        // Добавить дубль правого потомка родителя самого левого листа
        assertEquals(4, tree.AddKey(55));
        // Добавить ключ больше корня
        assertEquals(2, tree.AddKey(200));
    }

    @Test
    @DisplayName("5) Частично заполнено. 7 узлов. Заполнена вся левая ветка. У корня левого листа нет правого потомка.")
    void addKeyPartlyFilledLeftBranchLeftLeafParentHasOnlyLeftChild() {
        aBST tree = new aBST(2);
        // Добавили корень
        assertEquals(0, tree.AddKey(100));
        // Добавить левого потомка корня
        assertEquals(1, tree.AddKey(50));
        // Добавить левый лист
        assertEquals(3, tree.AddKey(25));
        // Добавить узел больше родителя левого листа
        assertEquals(4, tree.AddKey(55));
        // Добавить дубля узла, добавленного выше
        assertEquals(4, tree.AddKey(55));
    }

    @Test
    @DisplayName("6) Частично заполнено. 7 узлов. Заполнена вся правая ветка.")
    void addKeyPartlyFilledRightBranch() {
        aBST tree = new aBST(2);
        // Добавили корень
        assertEquals(0, tree.AddKey(100));
        // Добавить правого потомка корня
        assertEquals(2, tree.AddKey(200));
        // Добавить правый лист
        assertEquals(6, tree.AddKey(250));
        // Добавить дубль правого листа
        assertEquals(6, tree.AddKey(250));
    }

    // ДЕРЕВЬЯ ЗАПОЛНЕНЫ ПОЛНОСТЬЮ
    @Test
    @DisplayName("7) Дерево заполнено полностью. 3 узла.")
    void addKeyFullFilledTree() {
        aBST tree = new aBST(1);
        // Добавили корень
        assertEquals(0, tree.AddKey(100));
        // Добавить левого потомка корня
        assertEquals(1, tree.AddKey(50));
        // Добавить правого потомка корня
        assertEquals(2, tree.AddKey(200));
        // Добавить дубль корня
        assertEquals(0, tree.AddKey(100));
        // Добавить дубль левого узла
        assertEquals(1, tree.AddKey(50));
        // Добавить дубль правого узла
        assertEquals(2, tree.AddKey(200));
        // Добавить узел меньше левого узла
        assertEquals(-1, tree.AddKey(25));
        // Добавить узел больше правого узла
        assertEquals(-1, tree.AddKey(250));
    }
}