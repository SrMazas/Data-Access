package exercise;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Scanner;

public class Business {

    private File f;
    private int idDepart;
    private int idPerson;

    public Business(String path) {
        this.f = new File(path);
        idPerson = getLastIndex(true);
        idDepart = getLastIndex(false);
    }

    public void addDepart() {
        saveObject(new Depart(getName("Depart"), idDepart));
        idDepart++;
    }

    public void addPerson() {
        saveObject(new Person(getName("Person"), idPerson));
        idPerson++;
    }

    public void showAll() {
        readObjects();
    }

    public void showDepart() {
        readObjects(true);
    }

    public void showPerson() {
        readObjects(false);
    }

    public void removeObject(int indexToRem, boolean isDepart) {
        File temp = new File(this.f.getParent() + "\\temp.dat");
        String fileNameWithPath = this.f.getAbsolutePath();

        try (FileInputStream fin = new FileInputStream(f);
                ObjectInputStream in = new ObjectInputStream(fin);
                FileOutputStream dos = new FileOutputStream(temp, true);
                ObjectOutputStream out = temp.length() == 0 ? new ObjectOutputStream(dos)
                        : new AppendingObjectOutputStream(dos)) {

            try {

                Depart readDepart;
                Person readPerson;
                Object readObject;

                while (true) {
                    readObject = in.readObject();

                    if (readObject instanceof Depart) {
                        readDepart = (Depart) readObject;

                        if (readDepart.getId() == indexToRem && isDepart) {

                        } else {
                            out.writeObject(readObject);

                        }

                    } else if (readObject instanceof Person) {
                        readPerson = (Person) readObject;

                        if (readPerson.getId() == indexToRem && !isDepart) {

                        } else {

                            out.writeObject(readObject);
                        }

                    }

                }
            } catch (EOFException e) {
            }

        } catch (Exception e) {
            System.err.println("Error reading and writing object ");
        }

        f.delete();
        temp.renameTo(new File(fileNameWithPath));

        this.f = new File(fileNameWithPath);
    }

    private String getName(String type) {
        Scanner sc = new Scanner(System.in);

        System.err.println("Insert the name of the " + type);
        return sc.nextLine();
    }

    private void saveObject(Object obj) {

        try (FileOutputStream dos = new FileOutputStream(f, true);
                ObjectOutputStream out = f.length() == 0 ? new ObjectOutputStream(dos)
                        : new AppendingObjectOutputStream(dos)) {

            out.writeObject(obj);

        } catch (Exception e) {
            System.out.println("Error writing object ");
        }
    }

    private void readObjects() {
        try (FileInputStream fin = new FileInputStream(f);
                ObjectInputStream in = new ObjectInputStream(fin)) {

            try {
                Depart readDepart;
                Person readPerson;
                Object readObject;

                while (true) {
                    readObject = in.readObject();

                    if (readObject instanceof Depart) {
                        readDepart = (Depart) readObject;

                        readDepart.showObject();

                    } else if (readObject instanceof Person) {
                        readPerson = (Person) readObject;

                        readPerson.showObject();

                    }

                }
            } catch (EOFException e) {
            }
            System.out.println("+--------------------------------------------");

        } catch (Exception e) {
            System.out.println("Error reading object ");
        }
    }

    private void readObjects(boolean isDepart) {
        try (FileInputStream fin = new FileInputStream(f);
                ObjectInputStream in = new ObjectInputStream(fin)) {

            try {
                Depart readDepart;
                Person readPerson;
                Object readObject;

                while (true) {
                    readObject = in.readObject();

                    if (readObject instanceof Depart) {
                        readDepart = (Depart) readObject;

                        if (isDepart) {
                            readDepart.showObject();
                        }

                    } else if (readObject instanceof Person) {
                        readPerson = (Person) readObject;

                        if (!isDepart) {
                            readPerson.showObject();
                        }
                    }

                }
            } catch (EOFException e) {
            }
            System.out.println("+--------------------------------------------");

        } catch (Exception e) {
            System.out.println("Error reading object ");
        }
    }

    int getLastIndex(boolean isDepart) {
        int lastIndex = -1;

        try (FileInputStream fin = new FileInputStream(this.f);
                ObjectInputStream in = new ObjectInputStream(fin)) {

            try {
                Depart readDepart;
                Person readPerson;
                Object readObject;

                while (true) {
                    readObject = in.readObject();

                    if (readObject instanceof Depart) {
                        readDepart = (Depart) readObject;

                        if (isDepart) {
                            lastIndex = readDepart.getId();
                        }

                    } else if (readObject instanceof Person) {
                        readPerson = (Person) readObject;

                        if (!isDepart) {
                            lastIndex = readPerson.getId();
                        }
                    }

                }
            } catch (EOFException e) {
            }

        } catch (Exception e) {
        }

        return lastIndex + 1;
    }

    class AppendingObjectOutputStream extends ObjectOutputStream {
        public AppendingObjectOutputStream(OutputStream out) throws IOException {
            super(out);
        }

        @Override
        protected void writeStreamHeader() throws IOException {
            reset();// do not write a header
        }
    }
}
