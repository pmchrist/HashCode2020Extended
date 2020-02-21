public class Book implements Comparable<Book>{

    public int id;
    public double score;

    public Book(int id, int score){
        this.id = id;
        this.score = (double) score;
    }

    public String toString() {
        String S = "My ID is: " + (id) + ". My Score is: " + (score);
        return (S);
    }

    @Override
    public int compareTo(Book o) {
        if (this.id==o.id){
            return 0;
        }
        if (this.score>o.score){
            return 1;
        }else{
            return -1;
        }
    }
}
