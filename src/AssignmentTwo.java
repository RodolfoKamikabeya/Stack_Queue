import java.io.*;
import java.util.Scanner;
import java.io.FileWriter;

/* Assignment 2 - Stack & Queue
* Name: Rodolfo Kamikabeya
* */
public class AssignmentTwo {

    public static void main(String[] args) throws IOException {

        Stack arrayStack = new Stack(25);
        Queue arrQueue = new Queue(15);
        int totalWord = 0;
        float AvgWord = 0;

        //Open the output file and erase it
        File Output = new File("text_out.rpt");
        if (Output.exists()) {
            RandomAccessFile raf = new RandomAccessFile(Output, "rw"); //Open the file for reading and writing
            raf.setLength(0); //set the length of the character sequence equal to 0
        }

        // open the file name
        File file = new File("text1.dat");
        // Scanner all the file
        Scanner scannerFile = new Scanner(file);
        //While until have words in the file
        while (scannerFile.hasNext()) {
            // checking the next word
            String word = scannerFile.next();

            //if the word contains number, skip to the next string
            if (word.matches(".*\\d.*"))
                word = scannerFile.next();

            String newWord = removePunctuations(word); // remove punctuations

            arrayStack.push(newWord); // push word into the stack

            //check if stack is full and pop the stack value into the queue
            if (arrayStack.isFull()) {
                while (!arrayStack.isEmpty()) {
                    arrQueue.enqueue(arrayStack.pop());

                    if (arrQueue.isQFull()) { //if queue is full -> dequeue

                        while (!arrQueue.isQEmpty()) {
                            arrQueue.dequeue();
                        }
                    }
                }
            }
            //count total of words and length
            totalWord += 1;
            AvgWord += word.length();
        }
        System.out.println("Total Word count: " + totalWord);
        System.out.printf("%s %.2f", "Average word length: ", AvgWord / totalWord);

        //Flush stack into the queue
        while (!arrayStack.isEmpty()) {
            arrQueue.enqueue(arrayStack.pop());
            if (arrQueue.isQFull()) { //if queue is full -> dequeue
                while (!arrQueue.isQEmpty()) {
                    arrQueue.dequeue();
                }
            }
        }
        //Flush the queue
        while (!arrQueue.isQEmpty()) {
            arrQueue.dequeue();}
    }

    private static String removePunctuations(String word) {
        String temp = "";
        // Loop looking for character "c" in the word
        for (Character c : word.toCharArray()) {
            //if character is a letter return the letter, if not return temp =""
            if (Character.isLetterOrDigit(c))
                temp += c;
        }
        return temp;
    }

    static class Stack {

        private static int top, capacity;
        private static String arrStack[];

        Stack(int size) {
            capacity = size;
            arrStack = new String[capacity]; // Maximum size of Stack
            top = -1;
        }

        boolean isEmpty() {
            return (top < 0);
        }

        boolean isFull() {
            return (capacity - 1 == top);
        }

        boolean push(String x) {
            //check if the stack Overflow (Stop pushing words onto the stack when it fills up_
            if (top >= (capacity - 1)) {
//                System.out.println("Stack Overflow");
                return false;
            } else {
                arrStack[++top] = x; // String pushed into stack

                return true;
            }
        }

        String pop() {
            if (top < 0) {
                // System.out.println("Stack Underflow");
                return "";
            } else {
                String x = arrStack[top--];
                // System.out.println(x +" Add");
                return x;
            }
        }

        String peek() {
            if (top < 0) {
                // System.out.println("Stack Underflow");
                return "";
            } else {
                String x = arrStack[top];
                return x;
            }
        }

    }

    // Class for queue
    static class Queue {
        private String arr[];          // array to store queue elements
        private int front;          // front points to front element in the queue
        private int rear;           // rear points to last element in the queue
        private int capacity;       // maximum capacity of the queue
        private int count;          // current size of the queue
        int linecount=0;
        // Constructor to initialize queue
        Queue(int size) {
            arr = new String[size];
            capacity = size;
            front = 0;
            rear = -1;
            count = 0;
        }

        // Utility function to remove front element from the queue
        public void dequeue() {
            // check for queue underflow
            if (isQEmpty()) {
                System.out.println("UnderFlow\nProgram Terminated");
                System.exit(1);
            }

           // System.out.println("Removing " + arr[front]);
            //Print the output (limit 10 words per line)
                    if(linecount==10){WriteToFile("",linecount); linecount=0;}
                    WriteToFile(arr[front]+" ", linecount);
                    linecount+=1;
            front = (front + 1) % capacity;
            count--;
        }

        // Utility function to add an item to the queue
        public void enqueue(String item) {
            // check for queue overflow
            if (isQFull()) {
                System.out.println("OverFlow\nProgram Terminated");
                System.exit(1);
            }

           // System.out.println("Inserting " + item);

            rear = (rear + 1) % capacity;
            arr[rear] = item;
            count++;
        }

        // Utility function to return front element in the queue
        public String peek() {
            if (isQEmpty()) {
                System.out.println("UnderFlow\nProgram Terminated");
                System.exit(1);
            }
            return arr[front];
        }

        // Utility function to return the size of the queue
        public int size() {
            return count;
        }

        // Utility function to check if the queue is empty or not
        public Boolean isQEmpty() {
            return (size() == 0);
        }

        // Utility function to check if the queue is full or not
        public Boolean isQFull() {
            return (size() == capacity);
        }

        //Create the output file
        static void WriteToFile(String data, int count) {

            try {
                // Get the file
                File file = new File("text_out.rpt");
                FileWriter fw = new FileWriter(file, true);
                PrintWriter printer = new PrintWriter(fw);
                if (count == 10) {
                    printer.write('\n');
                }
                printer.append(data);
                printer.close();


            } catch (Exception e) {
                System.err.println(e);
            }
        }
    }
}

