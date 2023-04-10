package ex_3.test_version;

import java.util.*;

class aBST {
    public Integer Tree[]; // массив ключей
    private static Random random = new Random();

    public aBST(int depth) {
        int tree_size = getSize(depth); // вычисляем размер массива в зависимости от заданной глубины
        Tree = new Integer[tree_size];
        for(int i = 0; i < tree_size; i++) Tree[i] = null; // заполнение дерева null-ами
    }

    // Посчитать размер массива
    public int getSize(int depth) {
        if (depth < 0) {
            return 0;
        }
        return (int)(Math.pow(2.0, (depth + 1)) - 1);
    }

    // Вывод содержимого массива на экран
    public void ShowArray() {
        for(Integer num : this.Tree) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    // Вывод содержимого рандомного массива на экран
    public static void ShowRandomArray(Integer[] array) {
        for(Integer element : array) System.out.print(element + " ");
        System.out.println();
    }

    public Integer FindKeyIndex(int key) {
        return FindKeyHelper(0, key);
    }

    private Integer FindKeyHelper(int index, int key) {
        if (index >= Tree.length) return null;

        if (Tree[index] != null && Tree[index] == key) return index;

        if (Tree[index] == null) {
            if (index == 0) return 0; // Если дерево пустое. не ясно - возможно тут нужно вернуть null, если дерево пустое. раньше было null

            int parentIndex = (index - 1) / 2;
            if(index == 2 * parentIndex + 2 && key > Tree[parentIndex] && key < Tree[0]
                    && (Tree[parentIndex] < Tree[0] || Objects.equals(Tree[parentIndex], Tree[0]))) {
                return index * -1;
            }
            else if (index == 2 * parentIndex + 1 && key < Tree[parentIndex] && key < Tree[0]
                    && (Tree[parentIndex] < Tree[0] || Objects.equals(Tree[parentIndex], Tree[0]))) {
                return index * -1;
            }
            else if(index == 2 * parentIndex + 2 && key > Tree[parentIndex] && key > Tree[0]
                    && (Tree[parentIndex] > Tree[0] || Objects.equals(Tree[parentIndex], Tree[0]))) {
                return index * -1;
            }
            else if(index == 2 * parentIndex + 1 && key < Tree[parentIndex] && key > Tree[0]
                    && (Tree[parentIndex] > Tree[0] || Objects.equals(Tree[parentIndex], Tree[0]))) {
                return index * -1;
            }
            else {
                return null;
            }
        }
        Integer left = FindKeyHelper(index * 2 + 1, key);
        if (left != null) return left;

        Integer right = FindKeyHelper(index * 2 + 2, key);
        if (right != null) return right;

        return null;
    }

    public int AddKey(int key) {
        // Работа с корневым узлом
        if (Tree[0] == null) {
            Tree[0] = key;
            return 0;
        }

        int index = 0; // начинаем с корневого узла
        while (index < Tree.length) { // пока не вышли за пределы массива
            if (Tree[index] == null) { // если текущий элемент пуст
                Tree[index] = key; // добавляем ключ в текущий элемент
                return index; // возвращаем индекс добавленного элемента
            } else if (key < Tree[index]) { // если ключ меньше текущего элемента
                index = 2 * index + 1; // переходим к левому потомку
            } else if (key > Tree[index]) { // если ключ больше текущего элемента
                index = 2 * index + 2; // переходим к правому потомку
            } else if (key == Tree[index]) { // если ключ равен текущему элементу
                return index; // возвращаем индекс существующего элемента
            }
            else return index;
        }
        return -1; // если не удалось добавить элемент, возвращаем -1
    }

    // вспомогательный метод поиска элемента в массиве дерева
    public int[] containsKey(int key) {
        int[] resultArray = {0, 0}; // массив с результатами работы
        for (int i=0; i<Tree.length; i++) {
            if (Tree[i] == null) continue;
            if (Tree[i] == key) {
                resultArray[0] += 1;
                resultArray[1] = i;
                return resultArray; // если нашли - вернули индекс
            }
        }
        return resultArray; // если элемент не найден
    }

    // вспомогательный метод - создание массива с рандомными значениями
    public static Integer[] randArray (int size, int min, int max) {
        Integer[] array = new Integer[size];
        for (int i=0; i<array.length; i++) {
            array[i] = random.nextInt(max - min + 1) + min;
        }
        return array;
    }

    // Вспомогательный метод - проверка метода AddKey() с рандомным списком значений
    public boolean addKeyTest(Integer[] array) {
        boolean result = false;
        int indexOfAddedElement;
        for(Integer element : array) {
            indexOfAddedElement = this.AddKey(element); // добавили элемент списка в наше дерево
            int[] containsKey = containsKey(element);
            // если ключ в дерево добавить не смогли и в дереве такого элемента нет
            if(indexOfAddedElement == -1 && containsKey[0] == 0) {
                result = true;
            }
            // если ключ в дереве нашелся, а метод AddKey() ключ вроде как добавить не смог
            // Если в дерево удалось добавить элемент, но в массиве такого значения нет
            // Если метод AddKey(key) и containsKey() отдают разные индексы
            else if(indexOfAddedElement == -1 && containsKey[0] > 0 && containsKey[1] >= 0
                    || (indexOfAddedElement != -1 && containsKey[0] == 0 && containsKey[1] == 0)
                    || (indexOfAddedElement != -1 && containsKey[1] != indexOfAddedElement)) {
                result = false;
                break;
            }
            // Если в дерево удалось добавить элемент и такой ключ в дереве есть
            else if(indexOfAddedElement != -1 && containsKey[0] != 0) {
                result = true;
            }
            // Проверяем равенство AddKey(key) и значение Treeindex - работу метода и реального индекса в дереве
            else if(AddKey(element) == containsKey[1]) {
                result = true;
            }
        }
        return result;
    }
}
/*
*1-я ошибка была - типа при глубине 3 должно быть 15 элементов в массиве
* Исправлена
*
* 2-я ошибка была - AddKey() не возвращает корректный индекс добавленного элемента
* Скорее всего проверялся случай добавления дубля - данный момент скорректирован. Сделал -1 при попытке добавления дубля
* Плюс исправлен метод FindKeyIndex, который при обходе дерева некорректно проверял возможность вставки ключа в узел
*
* 3-я ошибка - AddKey() не возвращает корректный индекс существующего элемента
* мы что, при добавлении дубля должны не -1, а индекс этого элемента отображать??
* видимо да, скорее всего в словах "индекс существующего элемента" подразумевается что он показывает индекс дубля как бы
* стало быть надо будет под это условие все тесты переделать
* Сделал так, чтобы при добавления дубля возвращался индекс элемента.
*
* 4-я ошибка - AddKey() не возвращает корректный индекс добавленного элемента
* т.е. здесь проблема с возвратом индекса существующего элемента ушла. Но почему проблема с индексом добавленного
* элемента - неясно.
* UPD: После многочисленных тестов с разными условиями - понял, что не тестировал момент с обновлением значения.
* Больше мыслей нет других - в каких условиях можно получать некорректный индекс добавленного элемента.
* Типа если можно обновить значение - мы обновляем и отдаем индекс. А до этого было так - если элемент не пустой,
* то мы сразу вернули -1, типа не удалось элемент добавить и все. C другой стороны - если мы будем обновлять ключи,
* то у нас не будет ситуации, в которой мы получим -1. Т.к. нам придется обходить все дерево и мы всегда сможем обновить
* крайние узлы на новые значения, какими бы они ни были.
*
* 5-я ошибка - AddKey() не возвращает корректный индекс добавленного элемента
* По сравнению с предыдущим разом - обновил метод расчета размера массива. Думал, что возможно по этой причине
* AddKey() работал некорректно, но нет, проблема в чем-то другом.
* Решил весь процесс тестирования и разные варианты тестов расписать в майнд-карте. В ней же отобразить
* информацию по вспомогательным методам для тестирования и правила корректного функционирования тестируемых методов.
*/
