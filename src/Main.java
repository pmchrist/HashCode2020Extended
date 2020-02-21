import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    int booksNumber;
    int librariesNumber;
    int daysToScan;

    public static void run(String in, String out) throws IOException{
        ArrayList<Book> booksOverall = new ArrayList<>();
        ArrayList<Library> librariesOverall = new ArrayList<>();
        ArrayList<Library> librariesOutput = new ArrayList<>();
        HashMap<Integer, Integer> booksRate = new HashMap<Integer, Integer>();
        FileReader inputFile = null;
        FileWriter outputFile = null;

        try {
            //Setting Input
            inputFile = new FileReader(in);
            outputFile = new FileWriter(out);

            Scanner scanner = new Scanner(inputFile);

            //Setting Variables
            int booksNumber = scanner.nextInt();
            int librariesNumber = scanner.nextInt();
            int daysToScan = scanner.nextInt();

            //Setting Books
            for(int i=0; i<booksNumber; i++){
                booksOverall.add(new Book(i, scanner.nextInt()));
            }

            //Setting Libraries
            for(int i=0; i<librariesNumber; i++){
                int libraryID = i;
                int libraryBooksAmount = scanner.nextInt();
                int librarySignUp = scanner.nextInt();
                int libraryBooksPerDay = scanner.nextInt();
                ArrayList<Book> libraryBooks = new ArrayList<>();
                for (int book=0; book<libraryBooksAmount; book++){
                    int bookId = scanner.nextInt();
                    int bookRate = 0;
                    if (booksRate.containsKey(bookId)){
                        bookRate = booksRate.get(bookId);
                    }
                    booksRate.put(bookId, ++bookRate);
                    libraryBooks.add(booksOverall.get(bookId));
                }
                librariesOverall.add(new Library(libraryBooks, libraryID, librarySignUp, libraryBooksPerDay));
            }
            scanner.close();

            //Getting Better Book Score
            for (int id=0; id<booksNumber; id++){
                if (booksRate.containsKey(id)){
                    booksOverall.get(id).score /= (double) booksRate.get(id);
                }
            }

            //Setting Score Libraries
            for (int id=0; id<librariesOverall.size(); id++){
                librariesOverall.get(id).updateScore();
            }

            Collections.sort(librariesOverall, Collections.reverseOrder());
            librariesOutput=librariesOverall;

            //Outputting
            outputFile.write(Integer.toString(librariesOutput.size()));
            outputFile.write("\n");
            for (int i=0; i<librariesOutput.size(); i++){
                outputFile.write(Integer.toString(librariesOutput.get(i).libraryID));
                outputFile.write(" ");
                outputFile.write(Integer.toString(librariesOutput.get(i).books.size()));
                outputFile.write("\n");
                for (int j=0; j<librariesOutput.get(i).books.size(); j++){
                    outputFile.write(Integer.toString(librariesOutput.get(i).books.get(j).id));
                    outputFile.write(" ");
                }
                outputFile.write("\n");
            }


            //Clearing
        }finally {
            if (inputFile != null) {
                inputFile.close();
            }
            if (outputFile != null) {
                outputFile.close();
            }
        }
    }
    public static void main(String args[]) throws IOException {
        run("a_example.txt", "a.out");
        run("b_read_on.txt", "b.out");
        run("c_incunabula.txt", "c.out");
        run("d_tough_choices.txt", "d.out");
        run("e_so_many_books.txt", "e.out");
        run("f_libraries_of_the_world.txt", "f.out");
    }
}
