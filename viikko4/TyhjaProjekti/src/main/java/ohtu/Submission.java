package ohtu;

import java.util.ArrayList;
import java.util.Arrays;

public class Submission {
    private String student_number;

    private int week;
    private int hours;
    
    private boolean a1;
    private boolean a2;
    private boolean a3;
    private boolean a4;
    private boolean a5;
    private boolean a6;
    private boolean a7;
    private boolean a8;
    private boolean a9;
    private boolean a10;
    private boolean a11;
    private boolean a12;
    private boolean a13;
    private boolean a14;
    private boolean a15;
    private boolean a16;
    private boolean a17;
    private boolean a18;
    private boolean a19;
    private boolean a20;
    private boolean a21;
    
    
    public String getStudent_number() {
        return student_number;
    }

    public void setStudent_number(String student_number) {
        this.student_number = student_number;
    }
    
    public boolean[] getAssignments() {
        boolean[] assignments = new boolean[21];
        assignments[0] = a1;
        assignments[1] = a2;
        assignments[2] = a3;
        assignments[3] = a4;
        assignments[4] = a5;
        assignments[5] = a6;
        assignments[6] = a7;
        assignments[7] = a8;
        assignments[8] = a9;
        assignments[9] = a10;
        assignments[10] = a11;
        assignments[11] = a12;
        assignments[12] = a13;
        assignments[13] = a14;
        assignments[14] = a15;
        assignments[15] = a16;
        assignments[16] = a17;
        assignments[17] = a18;
        assignments[18] = a19;
        assignments[19] = a20;
        assignments[20] = a21;
        return assignments;
    }
    
    public ArrayList<Integer> getDoneAssignments() {
        ArrayList<Integer> done = new ArrayList<Integer>();
        int index = 0;
        for (boolean a : getAssignments()) {
            if (a) {
                done.add(index + 1);
            }
            index++;
        }
        return done;
    }
    
    public int getAssignmentsDone() {
        return getDoneAssignments().size();
    }
    
    public int getHours() {
        return hours;
    }

    @Override
    public String toString() {
        String s = "viikko " + week + ": " + 
                "tehtyjä tehtäviä yhteensä: " + getAssignmentsDone() + ", " +
                "aikaa kului " + hours + " tuntia, "
                + "tehdyt tehtävät: " + getDoneAssignments();
        
        
        return s;
    }
    
}