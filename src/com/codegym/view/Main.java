package com.codegym.view;

import com.codegym.controller.ContactManagement;
import com.codegym.controller.PhoneAndMailCheck;
import com.codegym.model.Contact;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        ContactManagement contactManagement = new ContactManagement();


        int choice = -1;
        do {
            menu();
            System.out.println("---Mời nhập lựa chọn---");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {

                case 1: {
                    showAllContact(contactManagement);

                    break;
                }
                case 2: {
                    showAddContact(contactManagement);
                    break;
                }
                case 3: {
                    showUpdateContact(contactManagement);
                    break;
                }
                case 4: {
                    showDeleteContact(contactManagement);
                    break;
                }
                case 5: {
                    showSearchContactByPhoneNumber(contactManagement);
                    break;
                }
                case 6: {
                    showReadFile(contactManagement);
                    break;
                }
                case 7: {
                    showWritefile(contactManagement);
                    break;
                }
                case 8: {
                    break;
                }
                default: {
                    System.out.println("Nhập lựa chọn từ 1 -> 8");
                }
            }

        } while (choice != 8);
    }

    private static void showWritefile(ContactManagement contactManagement) {
        System.out.println("---Ghi file---");
        try {
            contactManagement.writeFile("contacts.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void showReadFile(ContactManagement contactManagement) {
        System.out.println("---Đọc file---");
        File file = new File("contacts.csv");
        if (file.exists()) {
            try {
                contactManagement.readFile("contacts.csv");
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private static void showSearchContactByPhoneNumber(ContactManagement contactManagement) {
        System.out.println("---Tìm kiếm danh bạ theo SĐT---");
        System.out.println("Nhập SĐT cần tìm kiếm");
        String phoneNum = scanner.nextLine();
        int index = contactManagement.findContactByphoneNum(phoneNum);
        if (index != -1) {
            System.out.println("Thông tin nhân viên cần tìm: " + contactManagement.getContactByName(phoneNum));
        } else {
            System.out.println("Danh bạ không có SĐT " + phoneNum);
        }
    }

    private static void showDeleteContact(ContactManagement contactManagement) {
        System.out.println("---Xóa---");
        System.out.println("Nhập SĐT cần xóa");
        String phoneNum = scanner.nextLine();
        String cofirm ;
        int index = contactManagement.findContactByphoneNum(phoneNum);
        if (index != -1) {
            System.out.println("Chọ Y để xóa ");
            cofirm = scanner.nextLine();
            if (cofirm.equals("Y")){
                contactManagement.removeContract(phoneNum);
                System.out.println("Xóa thành công");
            }
            else {
                menu();
            }

        } else {
            System.out.println("Danh bạ không có SĐT" + phoneNum);
        }
    }

    private static void showUpdateContact(ContactManagement contactManagement) {
        System.out.println("---Cập nhật theo SĐT---");
        System.out.println("Nhập SĐT cần chỉnh sửa");
        String phoneNum = scanner.nextLine();
        int index = contactManagement.findContactByphoneNum(phoneNum);
        if (index != -1) {
            Contact contact = getContact();
            contactManagement.updateContactByPhoneNum(phoneNum, contact);
            System.out.println("Cập nhật thành công!!!");
        } else {
            System.out.println("Không có " + phoneNum + " trong danh bạ");
        }
    }

    private static void showAddContact(ContactManagement contactManagement) {
        System.out.println("---Thêm mới---");
        Contact newContact = getContact();
        contactManagement.adNewContact(newContact);
        System.out.println("Thêm mới thành công!!!");
    }

    private static void showAllContact(ContactManagement contactManagement) {
        if (contactManagement.size() == 0) {
            System.out.println("Danh sách trống");
        } else {
            contactManagement.showAllContact();
        }
    }

    private static Contact getContact() {
        System.out.println("Nhập tên");
        String name = scanner.nextLine();
        String phoneNum = creatPhoneNumber();
        System.out.println("Nhập địa chỉ");
        String adress = scanner.nextLine();
        String mail = creatEmail();
        Contact newContact = new Contact(name, phoneNum, adress, mail);
        return newContact;
    }

    private static void menu() {
        System.out.println("---CHƯƠNG TRÌNH QUẢN LÝ DANH BẠ---");
        System.out.println("Chọn chức năng theo số (để tiếp tục)");
        System.out.println("1. Xem danh sách mới");
        System.out.println("2. Thêm mới");
        System.out.println("3. Cập nhật");
        System.out.println("4. Xóa ");
        System.out.println("5. Tìm kiếm danh bạ theo SĐT");
        System.out.println("6. Đọc từ file");
        System.out.println("7. Ghi vào file");
        System.out.println("8. Thoát");
    }


    private static String creatPhoneNumber() {
        String phoneNum;
        do {
            System.out.println("Nhập số điện thoại");
            phoneNum = scanner.nextLine();
            if (!PhoneAndMailCheck.phoneNumValidate(phoneNum)) {
                System.err.println("Không đúng Định dạng số điện thoại");
            }
        } while (!PhoneAndMailCheck.phoneNumValidate(phoneNum));
        return phoneNum;
    }

    private static String creatEmail() {
        String email;
        do {
            System.out.println("Nhập địa chỉ Email");
            email = scanner.nextLine();
            if (!PhoneAndMailCheck.emailValidate(email)) {
                System.err.println("Email không đúng định dạng không đúng Định dạng");
            }
        } while (!PhoneAndMailCheck.emailValidate(email));
        return email;
    }
}
