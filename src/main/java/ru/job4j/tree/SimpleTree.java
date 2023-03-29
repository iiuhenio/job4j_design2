package ru.job4j.tree;

import java.util.*;
import java.util.function.Predicate;

/**
 *  поле "корень дерева"
 */
public class SimpleTree<E> implements Tree<E> {
    private final Node<E> root;

    /**
     * конструктор нового узла
     */
    public SimpleTree(final E root) {
        this.root = new Node<>(root);
    }

    /**
     * Метод add - Должен находить узел по значению parent и добавлять в него дочерний узел со значением child.
     * В этом методе нужно проверить, что значения child еще нет в дереве, а parent есть.
     * Если child есть, то метод должен вернуть false.
     *
     * Значение строк метода по порядку сверху вниз:
     * если parent существует а child нет
     * то полчаем значение parent, и в поле children добавляем новый узел child
     */
    @Override
    public boolean add(E parent, E child) {
        boolean rsl = false;
        Optional<Node<E>> findByParent = findBy(parent);
        if (findByParent.isPresent() && findBy(child).isEmpty()) {
            rsl = findByParent.get().children.add(new Node<>(child));
        }
        return rsl;
    }

    /**
     * Пояснение строк метода снизу вверх:
     * принимает значение, которое нужно найти
     * создаем объект rsl типа Optional
     * создаем объект типа Линкедлист/Очередь (ПППУ)
     * добавляем корневой узел в список
     * перебираем пока список не пустой
     * el = извлекаемый элемент
     * если значение элемента el равно искомому значению
     * результат = el
     * добавляем все элементы в очередь
     * результат равен el
     */
    @Override
    public Optional<Node<E>> findBy(E value) {
        return findByPredicate(node -> node.value.equals(value));
    }

    /**
     *
     Метод должен проверять количество дочерних элементов в дереве. Если их > 2 - то дерево не бинарное
     Метод должен циклически пройти по всем элементам дерева, аналогично методу findBy().
     */
    public boolean isBinary() {
        return findByPredicate(node -> node.children.size() > 2).isEmpty();
    }

    /**
     * Обратите внимание, что методы isBinary() и findBy() идентичны.
     * Ваша задача отрефакторить код, создав вспомогательный метод.
     * Этот метод уже использовать в методах isBinary() и findBy()
     */
    private Optional<Node<E>> findByPredicate(Predicate<Node<E>> condition) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (condition.test(el)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }

    public static void main(String[] args) {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        System.out.println(tree);
    }
}