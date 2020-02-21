import java.util.ArrayList;
import java.util.Collections;

public class Library implements Comparable<Library>{

    public ArrayList<Book> books;
    public int libraryID;
    public int signUpDays;
    public int booksPerDay;
    public double score;

    public Library(ArrayList<Book> books, int libraryID, int signUpDays, int booksPerDay){
        this.books = books;
        this.libraryID = libraryID;
        this.signUpDays = signUpDays;
        this.booksPerDay = booksPerDay;
    }

    public void updateScore(){
        for (int i=0; i<books.size(); i++){
            score+=books.get(i).score;
        }
        score=score * booksPerDay;
        score=score / signUpDays;
        Collections.sort(books,Collections.reverseOrder());
    }

    public String toString() {
        String S = "My ID is: " + libraryID + ". It takes days to SignUp: " + (signUpDays) + ". I Have that much books: " + (booksPerDay);
        return (S);
    }

    @Override
    public int compareTo(Library o) {
        if (this.score==o.score){
            return 0;
        }
        if (this.score>o.score){
            return 1;
        }else{
            return -1;
        }
    }
}

