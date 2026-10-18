package lk.kaushalya.dpII.example.model;

/// මෙතන patient object එක කියන්නේ request එක
class Patient {
    String name;
    int age;
    String symptom;
}

class GeneralDoctor {
    Specialist specialist;

    public void handle(Patient patient) {

        if (patient.symptom.equalsIgnoreCase("Fever")) {
            // handle the patient
            System.out.println("General Doctor is handling patient: " + patient.name + "\npatient age: " + patient.age + "\npatient symptom: " + patient.symptom);
            System.out.println("Patient " + patient.name + " is treated by General Doctor.");

        } else {
            specialist.handle(patient);
        }
    }
}

class Specialist {
    Consultant consultant;

    public void handle(Patient patient) {
        if (patient.symptom.equalsIgnoreCase("Chest Pain")) {
            // handle the patient
            System.out.println("Specialist is handling patient: " + patient.name + "\npatient age: " + patient.age + "\npatient symptom: " + patient.symptom);
            System.out.println("Patient " + patient.name + " is treated by Specialist.");

        } else {
            consultant.handle(patient);
        }
    }
}

class Consultant {

    public void handle(Patient patient) {
        if (patient.symptom.equalsIgnoreCase("Heart Disease")) {
            // handle the patient
            System.out.println("Consultant Doctor is handling patient: " + patient.name + "\npatient age: " + patient.age + "\npatient symptom: " + patient.symptom);
            System.out.println("Patient " + patient.name + " is treated by Consultant Doctor.");

        } else {
            System.out.println("Patient " + patient.name + " is not treated by any doctor.");
            System.out.println("Patient " + patient.name + " is referred to another hospital for further treatment.\n" +
                    "Because the patient has " + patient.symptom + " which is not treated by any doctor in this hospital.");
        }
    }
}

public class Test {

    public static void main(String[] args) {

        Patient patient1 = new Patient();
        patient1.name = "Saman";
        patient1.age = 25;
        patient1.symptom = "Fever";

        Patient patient2 = new Patient();
        patient2.name = "Kamal";
        patient2.age = 30;
        patient2.symptom = "Chest Pain";

        Patient patient3 = new Patient();
        patient3.name = "Nimal";
        patient3.age = 40;
        patient3.symptom = "Heart Disease";

        Patient patient4 = new Patient();
        patient4.name = "Sunil";
        patient4.age = 35;
        patient4.symptom = "Brain Pain";

        GeneralDoctor generalDoctor = new GeneralDoctor();
        Specialist specialist = new Specialist();
        Consultant consultant = new Consultant();

        generalDoctor.specialist = specialist;
        specialist.consultant = consultant;

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
