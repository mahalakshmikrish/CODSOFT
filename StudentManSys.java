import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class Student implements Serializable {
    private String name;
    private String rollno;
    private String grade;
    private String fname;
    private String mname;
    private String age;


    public Student(String name, String rollno, String grade, String fname,String mname, String age) {
        this.name = name;
        this.rollno = rollno;
        this.grade = grade;
        this.fname = fname;
        this.mname = mname;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getRollno() {
        return rollno;
    }

    public String getGrade() {
        return grade;
    }
    public String getFname() {
        return fname;
    }
    public String getMname() {
        return mname;
    }
    public String getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\nRollno: " + rollno + "\nGrade: " + grade + "\nFname: "+ fname +"\nMname: "+ mname +"\nAge: "+ age;
    }
}

class StudentManagement {
    private ArrayList<Student> students;

    public StudentManagement() {
        students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void removeStudent(String name) {
        students.removeIf(student -> student.getName().equalsIgnoreCase(name));
    }

    public Student searchStudent(String name) {
        return students.stream()
                .filter(student -> student.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    public ArrayList<Student> getAllStudents() {
        return students;
    }
}

public class StudentManSys{
    private StudentManagement studentmanagement = new StudentManagement();
    private JFrame frame;
    private JTextArea displayArea;
    private JTextField nameField;
    private JTextField rollnoField;
    private JTextField gradeField;
    private JTextField fnameField;
    private JTextField mnameField;
    private JTextField ageField;

    public StudentManSys() {
        frame = new JFrame("Student Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);

        displayArea = new JTextArea(10, 30);
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);

        nameField = new JTextField(20);
        rollnoField = new JTextField(20);
        gradeField = new JTextField(20);
        fnameField = new JTextField(20);
        mnameField = new JTextField(20);
        ageField = new JTextField(20);

        JButton addButton = new JButton("Add Student");
        JButton removeButton = new JButton("Remove Student");
        JButton searchButton = new JButton("Search Student");
        JButton displayButton = new JButton("Display Student");

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String rollno = rollnoField.getText();
                String grade = gradeField.getText();
                String fname = fnameField.getText();
                String mname = mnameField.getText();
                String age = ageField.getText();

                if (!name.isEmpty() && !rollno.isEmpty() && !grade.isEmpty() && !fname.isEmpty() && !mname.isEmpty() && !age.isEmpty()) {
                    Student newStudent = new Student(name, rollno, grade, fname, mname, age);
                    studentmanagement.addStudent(newStudent);
                    nameField.setText("");
                    rollnoField.setText("");
                    gradeField.setText("");
                    fnameField.setText("");
                    mnameField.setText("");
                    ageField.setText("");
                    displayArea.append("Student detail added successfully.\n");
                } else {
                    displayArea.append("Please fill in all the required fields.\n");
                }
            }
        });

        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                studentmanagement.removeStudent(name);
                nameField.setText("");
                rollnoField.setText("");
                gradeField.setText("");
                fnameField.setText("");
                mnameField.setText("");
                ageField.setText("");
                displayArea.append("Student removed.\n");
            }
        });

        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                Student foundStudent = studentmanagement.searchStudent(name);
                if (foundStudent != null) {
                    displayArea.setText(foundStudent.toString() + "\n");
                } else {
                    displayArea.setText("Student not found.\n");
                }
            }
        });

        displayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayArea.setText(""); // Clear the display area
                for (Student student : studentmanagement.getAllStudents()) {
                    displayArea.append(student.toString() + "\n-------------------\n");
                }
            }
        });
        

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2));
        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Roll no:"));
        inputPanel.add(rollnoField);
        inputPanel.add(new JLabel("Grade:"));
        inputPanel.add(gradeField);
        inputPanel.add(new JLabel("Father Name:"));
        inputPanel.add(fnameField);
        inputPanel.add(new JLabel("Mother Name:"));
        inputPanel.add(mnameField);
        inputPanel.add(new JLabel("Age:"));
        inputPanel.add(ageField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(displayButton);

        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StudentManSys());
    }
}
