package ru.job4j.collection;

import java.util.Iterator;

public class SimpleLinkedList<E> implements LinkedList<E> {

    /**
     * поля
     * size - размер списка
     * modCount - счетчик изменений
     * Node<E> head - ссылка на первый узел
     */
    private int size = 0;
    private int modCount = 0;
    private Node<E> head;

    /**
     * добавляет в конец
     */
    @Override
    public void add(E value) {
        Node<E> current = head;
        Node<E> node = new Node<>(value, null);
        if (current == null) {
            head = node;
        } else {
            while (current.next != null) {
                current = current.next;
            }
            current.next = node;
        }
        size++;
        modCount++;
    }

    /**
     * перебирает элементы до указанного индекса и возвращает значение из найденной ноды
     */
    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<E> list = head;
        for (int i = 0; i < index; i++) {
            list = list.next;
        }
        return list.item;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int counter = 0;
            @Override
            public boolean hasNext() {
                return counter < size;
            }

            @Override
            public E next() {
                return get(counter++);
            }
        };
    }

    private static class Node<E> {
        /**
         * Класс нового узла с полями
         * E item - значение
         * Node<E> next - ссылка на след. узел
         */
        private E item;
        private Node<E> next;

        /**
         * Конструктор нового узла
         * @param element - значение
         * @param next - ссылка на след.узел
         */
        Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        SimpleLinkedList<Integer> ls = new SimpleLinkedList<>();
        System.out.println(ls);
        System.out.println(ls.head);
        System.out.println(ls.size);
        ls.add(111);
        ls.add(222);
        System.out.println(ls);
        System.out.println(ls.head);
        System.out.println(ls.size);
        ls.add(3333);
        System.out.println(ls);
        System.out.println(ls.head);
        System.out.println(ls.size);
        System.out.println(ls.get(0));
        System.out.println(ls.get(1));
        System.out.println(ls.get(2));
        System.out.println(ls.get(4));
    }
}