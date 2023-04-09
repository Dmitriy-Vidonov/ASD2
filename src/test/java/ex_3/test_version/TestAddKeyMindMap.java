package ex_3.test_version;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestAddKeyMindMap {

    // ПУСТЫЕ ДЕРЕВЬЯ
    @Test
    @DisplayName("1) Пустые деревья. Дерево из одного элемента")
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
}