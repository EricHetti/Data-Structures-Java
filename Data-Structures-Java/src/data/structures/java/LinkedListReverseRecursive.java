/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.structures.java;

/**
 *
 * @author Braavos
 */
public class LinkedListReverseRecursive implements ILinkedList {

    class Node {

        Module data;
        Node next;
    }

    private Node head;

    @Override
    public void add(Module module) {
        if (head == null || head.data.getModuleID().compareToIgnoreCase(module.getModuleID()) < 0) {
            //if list is null or id is smaller than pervious add to first position
            addFirst(module);
        } else if (head != null) {
            addMiddleModule(module, head, head.next);
        }
    }

    private void addMiddleModule(Module module, Node current, Node after) {
        //use recursion to iterate through list until element is greater than pervious and smaller than next
        if (after != null && after.data.getModuleID().compareToIgnoreCase(module.getModuleID()) > 0) {
            addMiddleModule(module, current.next, after.next);
        } else {
            //add element to list
            Node newNode = new Node();
            newNode.data = module;
            newNode.next = current.next;
            current.next = newNode;
        }
    }

    @Override
    public void addFirst(Module module) {
        //add module to the start of the list
        Node newNode = new Node();
        newNode.data = module;
        newNode.next = head;
        head = newNode;
    }

    @Override
    public void addLast(Module module) {
        //add module to the end of the list
        Node newNode = new Node();
        newNode.data = module;
        Node lastNode = getPenultimateNode(head, head.next, null).next;
        lastNode.next = newNode;
    }

    private Node getPenultimateNode(Node current, Node after, Node penultimate) {
        //iterate through and return penunltimate node
        if (head != null) {
            if (after.next == null) {
                return penultimate = current;
            } else {
                penultimate = getPenultimateNode(current.next, after.next, penultimate);
            }
        }
        return penultimate;
    }

    @Override
    public void clear() {
        //clear the list
        head = null;
    }

    @Override
    public boolean contains(String moduleID) {
        //check if id exists in list
        return getModule(moduleID) != null;
    }

    @Override
    public Module getModule(String moduleID) {
        return searchModule(moduleID, head, null);
    }

    private Module searchModule(String moduleID, Node current, Module findModule) {
        //iterate through list to find and return selected module
        if (head != null) {
            if (current.data.getModuleID().equals(moduleID)) {
                return findModule = current.data;
            } else {
                findModule = searchModule(moduleID, current.next, findModule);
            }
        }
        return findModule;
    }

    @Override
    public Module getFirstModule() {
        //return first element
        return head.data;
    }

    @Override
    public Module getLastModule() {
        //return last element
        return getPenultimateNode(head, head.next, null).next.data;
    }

    @Override
    public Module remove(String moduleID) {
        //remove selected module
        return deleteModule(moduleID, head, head.next, null);
    }

    private Module deleteModule(String moduleID, Node current, Node after, Module delModule) {
        //delete and return element
        if (head.next == null) {
            //if one element in the list
            delModule = current.data;
            clear();
        } else if (current.data.getModuleID().equals(moduleID) && current.next != null) {
            //if current element is selected element then delete element
            delModule = current.data;
            if (current.next != null) {
                current.data = after.data;
                current.next = after.next;
            }
        } else if (after.data.getModuleID().equals(moduleID) && head.next != null) {
            //if next element is selected element then delete element
            delModule = after.data;
            if (after.next != null) {
                current.next = after.next;
            } else if (after.next == null) {
                removeLastModule();
            }
        } else if (!after.data.getModuleID().equals(moduleID) && head.next != null) {
            //iterate through list to find selected element
            delModule = deleteModule(moduleID, current.next, after.next, delModule);
        }
        return delModule;
    }

    @Override
    public Module removeFirstModule() {
        //delete first element
        Module delNode = null;
        if (head != null) {
            delNode = head.data;
            head = head.next;
        }
        return delNode;
    }

    @Override
    public Module removeLastModule() {
        //delete last element
        Module delNode = null;
        if (head != null) {
            Node current = getPenultimateNode(head, head.next, null);
            delNode = current.next.data;
            current.next = null;
        }
        return delNode;
    }

    @Override
    public boolean isEmpty() {
        //check if list is empty
        return head == null;
    }

    @Override
    public int size() {
        //return list size
        return count(head);
    }

    private int count(Node current) {
        //iterate through the list and find list size
        if (current == null) {
            return 0;
        } else {
            return 1 + count(current.next);
        }
    }

    public void printll() {
        //print list
        Node current = head;
        while (current != null) {
            System.out.println(current.data.getModuleID());
            current = current.next;
        }
    }

}
