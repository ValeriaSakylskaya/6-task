package Services;

import Models.Note;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NoteService {
    private static List<Note> notes = new ArrayList<Note>();
    private static final String path = "notes.txt";
    private static DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
    private static Scanner input = new Scanner(System.in);
    private static Validation validation = new Validation();

    public NoteService() {

    }

    public static void loadNotes() {
        String line;
        String[] subLine;
        try {
            File file = new java.io.File(path);
            FileReader fileReader = new FileReader(file);
            BufferedReader reader = new BufferedReader(fileReader);
            while ((line = reader.readLine()) != null) {
                subLine = line.split(";");
                notes.add(new Note(Integer.parseInt(subLine[0]), subLine[1], format.parse(subLine[2]), subLine[3], subLine[4]));
            }
            reader.close();
            fileReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static void saveNotes() {
        String saveModel = mapToServerModel();
        try (FileWriter writer = new FileWriter(path, false)) {
            writer.write(saveModel);
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    private static String mapToServerModel() {
        StringBuilder builder = new StringBuilder();
        String string;
        for (Note note : notes) {
            string = note.getId() + ";" + note.getTheme() + ";" + format.format(note.getCreatedDate()) + ";" + note.getEmail() + ";" + note.getMessage() + "\n";
            builder.append(string);
        }
        return builder.toString();
    }

    public static void viewNotes() {
        for (Note note : notes) {
            System.out.println(note);
        }
    }

    public static void addNote() {
        int Id;
        String Theme;
        Date dateNow = new Date();
        String Email;
        String Message;
        System.out.println("enter the id ");
        Id = Integer.parseInt(input.nextLine());
        System.out.println("enter the theme");
        Theme = input.nextLine();
        System.out.println("enter the email");
        Email = input.nextLine();
        if (validation.emailValidator(Email)) {
            System.out.println("enter the message");
            Message = input.nextLine();
            notes.add(new Note(Id, Theme, dateNow, Email, Message));
            saveNotes();
        } else {
            System.out.println("email very bad)");
        }

    }

    public static void findNotesByText(String rex) {
        List<Note> findNotes = new ArrayList<Note>();
        for (Note note : notes) {
            if (validation.searchForMatchesInTheText(note.getMessage(), rex) == true)
                findNotes.add(note);
        }
        sortByTheme(findNotes);
        System.out.println("result: ");
        for (Note note : findNotes) {
            System.out.println(note.toString());
        }
    }

    public static void findNotesByDate(String date) {
        List<Note> findNotes = new ArrayList<Note>();
        if (validation.dataValidator(date)) {
            for (Note note : notes) {
                if (validation.searchForMatchesInTheText(format.format(note.getCreatedDate()), date) == true)
                    findNotes.add(note);
            }
            sortByTheme(findNotes);
            System.out.println("result: ");
            for (Note note : findNotes) {
                System.out.println(note.toString());
            }
        } else {
            System.out.println("wrong date");
        }
    }

    public static void findNotesByDateAndTheme(String date, String theme) {
        List<Note> findNotes = new ArrayList<Note>();
        if (validation.dataValidator(date)) {
            for (Note note : notes) {
                if (validation.searchForMatchesInTheText(format.format(note.getCreatedDate()), date) == true && validation.searchForMatchesInTheText(note.getTheme(), theme) == true)
                    findNotes.add(note);
            }
            sortByTheme(findNotes);
            System.out.println("result: ");
            for (Note note : findNotes) {
                System.out.println(note.toString());
            }
        } else {
            System.out.println("wrong date");
        }
    }

    public static void findNotesByTheme(String theme) {
        List<Note> findNotes = new ArrayList<Note>();
        for (Note note : notes) {
            if (validation.searchForMatchesInTheText(note.getTheme(), theme) == true)
                findNotes.add(note);
        }
        sortByTheme(findNotes);
        System.out.println("result: ");
        for (Note note : findNotes) {
            System.out.println(note.toString());
        }
    }

    public static void findNotesByEmail(String email) {
        List<Note> findNotes = new ArrayList<Note>();
        if (validation.emailValidator(email)) {
            for (Note note : notes) {
                if (validation.searchForMatchesInTheText(note.getEmail(), email) == true)
                    findNotes.add(note);
            }
            sortByTheme(findNotes);
            System.out.println("result: ");
            for (Note note : findNotes) {
                System.out.println(note.toString());
            }
        } else {
            System.out.println("wrong email");
        }
    }

    private static void sortByTheme(List<Note> notes) {
        Collections.sort(notes, (firstNote, secondNote) -> {
            String firstNoteTheme = firstNote.getTheme();
            String secondNoteTheme = secondNote.getTheme();
            return firstNoteTheme.compareToIgnoreCase(secondNoteTheme);
        });
    }

    private static class Validation {
        private Pattern pattern;
        private Matcher matcher;

        private static final String EMAIL_PATTERN =
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                        "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        private static final String DATE_PATTERN = "(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\\d\\d";

        public boolean emailValidator(final String hex) {

            pattern = Pattern.compile(EMAIL_PATTERN);
            matcher = pattern.matcher(hex);
            return matcher.matches();
        }

        public boolean dataValidator(String date) {
            pattern = Pattern.compile(DATE_PATTERN);
            matcher = pattern.matcher(date);
            return matcher.matches();
        }

        public boolean searchForMatchesInTheText(String text, String regex) {
            pattern = Pattern.compile(regex);
            matcher = pattern.matcher(text);
            return matcher.lookingAt();
        }
    }

}
