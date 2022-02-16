package com.codegym.controller;

import com.codegym.model.Contact;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ContactManagement implements ReadFile, WriteFile{
    public List<Contact> contacts = new ArrayList<>();

    public int size(){
        return contacts.size();
    }
    public void showAllContact (){
        for (Contact contact:contacts) {
            System.out.println(contact);
        }
    }
    public void adNewContact (Contact contact){
        contacts.add(contact);
    }

    public int findContactByphoneNum(String phoneNum) {
        int index = -1;
        for (int i = 0; i < size(); i++) {
            if (contacts.get(i).getPhoneNum().equals(phoneNum)) {
                index = i;
            }
        }
        return index;
    }
    public void updateContactByPhoneNum(String phoneNum, Contact contact){
        int index = findContactByphoneNum(phoneNum);
        contacts.set(index, contact);
    }

    public void removeContract (String phoneNum){
        int index = findContactByphoneNum(phoneNum);
        contacts.remove(index);
    }
    public Contact getContactByName(String name) {
        int index = findContactByphoneNum(name);
        return contacts.get(index);
    }

    @Override
    public void readFile(String path) throws IOException, ClassNotFoundException {
        InputStream is = new FileInputStream(path);
        ObjectInputStream ois = new ObjectInputStream(is);
        this.contacts = (List<Contact>) ois.readObject();
    }

    @Override
    public void writeFile(String path) throws IOException {
        OutputStream os = new FileOutputStream(path);
        ObjectOutputStream oos = new ObjectOutputStream(os);
        oos.writeObject(this.contacts);
    }
}
