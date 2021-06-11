import java.io.*;
import java.util.Scanner;

class ProstyShell {
    private String path;
    private Scanner scan;
    private String polecenie = "";

    void shell() {
        System.out.println("JESLI POTRZEBUJESZ POMOCY WPISZ: help");
        while (!"exit".equals(polecenie)) {
            try (var writer = new BufferedWriter(new FileWriter("history",true))) {
                System.out.print(">> ");
                polecenie = scan.nextLine();
                writer.write(polecenie);
                writer.newLine();
                writer.close();

                if (polecenie.length() >= 2) {
                    if (polecenie.substring(0, 2).equals("ls"))
                        ls(path);
                }

                if (polecenie.length() >= 3) {
                    if (polecenie.substring(0, 3).equals("pwd"))
                        System.out.println(path);

                    if (polecenie.substring(0, 3).equals("cp "))
                        cp(path, polecenie.split(" ")[1], polecenie.split(" ")[2]);

                    if (polecenie.substring(0, 3).equals("cd "))
                        if (!cd(path, polecenie.split(" ")[1]).equals("NULL")) {
                            setPath(cd(path, polecenie.split(" ")[1]));
                        }

                }

                if (polecenie.length() >= 4) {
                    if (polecenie.substring(0, 4).equals("cat ")) cat(getPath(), polecenie.split(" ")[1]);

                    if (polecenie.substring(0, 4).equals("help")) {
                        System.out.println("Dostepne polecenia: \nls - \t\t\t\twypisuje zawartosc katalogu \npwd - " +
                                "\t\t\t\twypisuje aktualne polozenie\ncp <plik1> <plik2>- kopiuje plik1 do plik2\n" +
                                "cd <katalog> - \t\tsluzy do poruszania sie miedzy katalogami\ncat <plik> - \t\t" +
                                "wypisuje zawartosc pliku\necho <tekst> - \t\twypisuje tekst\ntouch <filename> - \ttworzy plik\n" +
                                "mkdir <dirname> - \ttworzy katalog\nhistory - \t\t\tpokazuje historie\nexit - \t\t\t\tkonczy prace");
                    }
                }

                if (polecenie.length() >= 5) {
                    if (polecenie.substring(0, 5).equals("echo ")) echo(polecenie.substring(5));
                }

                if (polecenie.length() >= 6) {
                    if (polecenie.substring(0, 6).equals("touch ")) touch(getPath(), polecenie.split(" ")[1]);

                    if (polecenie.substring(0, 6).equals("mkdir ")) mkdir(getPath(), polecenie.split(" ")[1]);
                }

                if (polecenie.length() >= 7) {
                    if (polecenie.substring(0, 7).equals("history")) {
                        cat(".","history");
                    }
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.print("Konczenie pracy programu...");
    }

    private void ls(String path) {
        File f;
        File[] paths;

        try {
            f = new File(path);
            paths = f.listFiles();
            assert paths != null;
            for (File sciezka : paths) {
                System.out.println(sciezka);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void cat(String path, String fName) {
        try (var reader = new BufferedReader(new FileReader(path + "/" + fName))) {
            String nextLine;
            while ((nextLine = reader.readLine()) != null)
                System.out.println(nextLine);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void cp(String path, String fName1, String fName2) {
        try (
                var reader = new BufferedReader(new FileReader(path + "/" + fName1));
                var writer = new BufferedWriter(new FileWriter(path + "/" + fName2))
        ) {
            String nextLine;
            while ((nextLine = reader.readLine()) != null) {
                writer.write(nextLine);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Nie udalo sie skopiowac pliku");
        }
    }

    private void echo(String text) {
        System.out.println(text);
    }

    private String cd(String cpath, String npath) {
        if (npath.equals("..")) {
            File file = new File(cpath);
            if (file.exists()) {
                file = file.getParentFile();
                return file.getAbsolutePath();
            }
        } else if (!npath.equals(".")) {
            File file = new File(cpath + "/" + npath);
            if (file.exists()) {
                return file.getAbsolutePath();
            } else {
                System.err.println("NIE MA TAKIEGO KATALOGU!");
            }
        }
        return "NULL";
    }

    private void mkdir(String path, String dirName) {
        File dir = new File(path + "/" + dirName);
        boolean bool = dir.mkdir();

        if (!bool)
            System.err.println("NIE UDALO SIE UTWORZYC KATALOGU");
    }

    private void touch(String path, String fileName) {
        File file = new File(path + "/" + fileName);
        boolean fileExists = file.exists();

        if (!fileExists) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.err.println("NIE UDALO SIE UTWORZYC PLIKU");
            }
        }else {
            System.err.println("PLIK O PODANEJ NAZWIE JUZ ISTNIEJE");
        }
    }

    private void setPath(String path) {
        this.path = path;
    }

    private String getPath() {
        return path;
    }

    ProstyShell() {
        path = System.getProperty("user.dir");
        scan = new Scanner(System.in);
    }
}
