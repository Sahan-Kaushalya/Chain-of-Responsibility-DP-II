package lk.kaushalya.dpII.example.model;

/// මෙතන patient object එක කියන්නේ request එක
class Patient {

    private String name;
    private int age;
    private String symptom;

    public Patient(String name, int age, String symptom) {
        this.name = name;
        this.age = age;
        this.symptom = symptom;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getSymptom() {
        return symptom;
    }

}

abstract class Doctor {

    private Doctor doctor;

    public abstract void handle(Patient patient);

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Doctor getDoctor() {
        return doctor;
    }
}

class GeneralDoctor extends Doctor {

    @Override
    public void handle(Patient patient) {

        if (patient.getSymptom().equalsIgnoreCase("Fever")) {
            // handle the patient
            System.out.println("General Doctor is handling patient: " + patient.getName() + "\npatient age: " + patient.getAge() + "\npatient symptom: " + patient.getSymptom());
            System.out.println("Patient " + patient.getName() + " is treated by General Doctor.");

        } else {
            this.getDoctor().handle(patient);
        }
    }
}

class Specialist extends Doctor {

    @Override
    public void handle(Patient patient) {
        if (patient.getSymptom().equalsIgnoreCase("Chest Pain")) {
            // handle the patient
            System.out.println("Specialist is handling patient: " + patient.getName() + "\npatient age: " + patient.getAge() + "\npatient symptom: " + patient.getSymptom());
            System.out.println("Patient " + patient.getName() + " is treated by Specialist.");

        } else {
            this.getDoctor().handle(patient);
        }
    }
}

class Consultant extends Doctor {

    @Override
    public void handle(Patient patient) {
        if (patient.getSymptom().equalsIgnoreCase("Heart Disease")) {
            // handle the patient
            System.out.println("Consultant Doctor is handling patient: " + patient.getName() + "\npatient age: " + patient.getAge() + "\npatient symptom: " + patient.getSymptom());
            System.out.println("Patient " + patient.getName() + " is treated by Consultant Doctor.");

        } else {

            if (this.getDoctor() != null) {
                this.getDoctor().handle(patient);
            } else {
                System.out.println("Patient " + patient.getName() + " is not treated by any doctor.");
                System.out.println("Patient " + patient.getName() + " is referred to another hospital for further treatment.\n" +
                        "Because the patient has " + patient.getSymptom() + " which is not treated by any doctor in this hospital.");

            }
        }
    }
}

public class Test {

    public static void main(String[] args) {

        Patient patient1 = new Patient("Saman", 25, "Fever");
        Patient patient2 = new Patient("Kamal", 30, "Chest Pain");
        Patient patient3 = new Patient("Nimal", 40, "Heart Disease");
        Patient patient4 = new Patient("Sunimal", 35, "Brain Pain");

        GeneralDoctor generalDoctor = new GeneralDoctor();
        Specialist specialist = new Specialist();
        Consultant consultant = new Consultant();


        // Set up the chain of responsibility,
        // මෙතන generalDoctor එකේ doctor කියන්නේ specialist එක, specialist එකේ doctor කියන්නේ consultant එක
        // මෙහෙම තියා ගන්න පුලුවන් වෙන්නේ polymorphism එක නිසා, Doctor class එකේ doctor කියන්නේ Doctor type එකක්,
        // ඒකේ reference එකක් තියෙනවා, ඒකේ actual object එක වෙනස් වුණත්, ඒකේ method call කරන්න පුලුවන්.
        generalDoctor.setDoctor(specialist);
        specialist.setDoctor(consultant);

        generalDoctor.handle(patient1);
        System.out.println("\n-----------------------------------");
        generalDoctor.handle(patient2);
        System.out.println("\n-----------------------------------");
        generalDoctor.handle(patient3);
        System.out.println("\n-----------------------------------");
        generalDoctor.handle(patient4);
        System.out.println("\n-----------------------------------");

    }
}
