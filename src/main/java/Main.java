
import entities.*;
import entities.DTO.*;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class Main {

    private static final String URL = "http://localhost:8081";
    private static final RestTemplate restTemplate = new RestTemplate();
    private static final HttpHeaders headers = new HttpHeaders();
    private static final HttpEntity<Object> headersEntity = new HttpEntity<>(headers);

    public static void main(String[] args) {
        headers.setContentType(MediaType.APPLICATION_JSON);

        Owner owner = owner();

        ArrayList<Cleaner> cleaners = cleaners();
        ArrayList<Trainer> trainers = trainers();
        ArrayList<Manager> managers = managers();
        addStaff(cleaners, trainers, managers);


        ArrayList<Client> clients = clients();
        addClients(clients);


        ArrayList<Locker> lockers = lockers();
        ArrayList<Shower> showers = showers();
        ArrayList<Apparatus> apparatuses = Apparatuses();
        addGoods(apparatuses, showers, lockers);


        ArrayList<Date> date = dates();


        ArrayList<TrainerMeet> trainerMeets = trainerMeets(getTrainers(), getClients(), date);
        ArrayList<CleanerTime> cleanerTimes = cleanerTimes(getCleaners(), date);
        ArrayList<GroupMeet> groupMeets = groupMeets(getClients(), getTrainers(), date);


        addTime(trainerMeets, cleanerTimes, groupMeets);


        printApparatus();
        System.out.println("Perform deletion");
        deleteApparatus(getApparatusId());
        printApparatus();
    }

    public static void deleteApparatus(UUID uuid) {
        HttpEntity<UUID> deleteItem = new HttpEntity<>(uuid);
        ResponseEntity<Void> response = restTemplate
                .exchange(URL + "/goods/apparatus/" + uuid, HttpMethod.DELETE,
                        deleteItem, Void.class);
    }

    public static UUID getApparatusId() {
        ResponseEntity<Apparatus[]> response = restTemplate
                .exchange(URL + "/goods/apparatuses", HttpMethod.GET, headersEntity, Apparatus[].class);
        List<Apparatus> apparatuses = Arrays.asList(Objects.requireNonNull(response.getBody()));
        return apparatuses.get(0).getId();
    }

    public static void printApparatus() {
        ResponseEntity<Apparatus[]> response = restTemplate
                .exchange(URL + "/goods/apparatuses", HttpMethod.GET, headersEntity, Apparatus[].class);
        List<Apparatus> apparatuses = Arrays.asList(Objects.requireNonNull(response.getBody()));
        System.out.println(apparatuses);
    }

    public static List<Trainer> getTrainers() {
        ResponseEntity<Trainer[]> response = restTemplate
                .exchange(URL + "/staff/trainers", HttpMethod.GET, headersEntity, Trainer[].class);
        return Arrays.asList(Objects.requireNonNull(response.getBody()));
    }

    public static List<Client> getClients() {
        ResponseEntity<Client[]> response = restTemplate
                .exchange(URL + "/client", HttpMethod.GET, headersEntity, Client[].class);
        return Arrays.asList(Objects.requireNonNull(response.getBody()));
    }

    public static List<Cleaner> getCleaners() {
        ResponseEntity<Cleaner[]> response = restTemplate
                .exchange(URL + "/staff/cleaners", HttpMethod.GET, headersEntity, Cleaner[].class);
        return Arrays.asList(Objects.requireNonNull(response.getBody()));
    }

    public static void addClients(ArrayList<Client> clients) {
        for (Client client : clients) {
            ClientDTO clientDTO = new ClientDTO();
            clientDTO.setPresent(false);
            clientDTO.setVIP(false);
            clientDTO.setName(client.getName());
            HttpEntity<ClientDTO> addClient = new HttpEntity<>(clientDTO);
            ResponseEntity<Void> response = restTemplate
                    .exchange(URL + "/client", HttpMethod.POST,
                            addClient, Void.class);
        }
    }

    public static void addTime(ArrayList<TrainerMeet> trainerMeets, ArrayList<CleanerTime> cleanerTimes, ArrayList<GroupMeet> groupMeets) {

        for (TrainerMeet trainerMeet : trainerMeets) {
            TrainerMeetDTO trainerMeetDTO = new TrainerMeetDTO();
            trainerMeetDTO.setClient(trainerMeet.getClient());
            trainerMeetDTO.setDate(trainerMeet.getDate());
            trainerMeetDTO.setTrainer(trainerMeet.getTrainer());
            HttpEntity<TrainerMeetDTO> addTrainerMeet = new HttpEntity<>(trainerMeetDTO);
            ResponseEntity<Void> response = restTemplate
                    .exchange(URL + "/shedule/trainermeet", HttpMethod.POST,
                            addTrainerMeet, Void.class);
        }

        for (CleanerTime cleanerTime : cleanerTimes) {
            CleanerTimeDTO cleanerTimeDTO = new CleanerTimeDTO();
            cleanerTimeDTO.setCleaner(cleanerTime.getCleaner());
            cleanerTimeDTO.setDate(cleanerTime.getDate());
            HttpEntity<CleanerTimeDTO> addCleanerTime = new HttpEntity<>(cleanerTimeDTO);
            ResponseEntity<Void> response = restTemplate
                    .exchange(URL + "/shedule/cleanertime", HttpMethod.POST,
                            addCleanerTime, Void.class);
        }
        for (GroupMeet groupMeet : groupMeets) {
            GroupMeetDTO groupMeetDTO = new GroupMeetDTO();
            groupMeetDTO.setClients(groupMeet.getClients());
            groupMeetDTO.setDate(groupMeet.getDate());
            groupMeetDTO.setTrainer(groupMeet.getTrainer());
            groupMeetDTO.setMaxClients(15);
            HttpEntity<GroupMeetDTO> addGroupMeet = new HttpEntity<>(groupMeetDTO);
            ResponseEntity<Void> response = restTemplate
                    .exchange(URL + "/shedule/groupmeet", HttpMethod.POST,
                            addGroupMeet, Void.class);
        }
    }


    public static void addGoods(ArrayList<Apparatus> apparatuses, ArrayList<Shower> showers, ArrayList<Locker> lockers) {
        for (Apparatus apparatus : apparatuses) {
            ApparatusDTO apparatusDTO = new ApparatusDTO();
            apparatusDTO.setAge(apparatus.getAge());
            apparatusDTO.setName(apparatus.getName());
            HttpEntity<ApparatusDTO> addApparatus = new HttpEntity<>(apparatusDTO);
            ResponseEntity<Void> response = restTemplate
                    .exchange(URL + "/goods/apparatus", HttpMethod.POST,
                            addApparatus, Void.class);
        }
        for (Shower shower : showers) {
            ShowerDTO showerDTO = new ShowerDTO();
            showerDTO.setNumber(shower.getNumber());
            HttpEntity<ShowerDTO> addShower = new HttpEntity<>(showerDTO);
            ResponseEntity<Void> response = restTemplate
                    .exchange(URL + "/goods/shower", HttpMethod.POST,
                            addShower, Void.class);
        }
        for (Locker locker : lockers) {
            LockerDTO lockerDTO = new LockerDTO();
            lockerDTO.setNumber(locker.getNumber());
            HttpEntity<LockerDTO> addLocker = new HttpEntity<>(lockerDTO);
            ResponseEntity<Void> response = restTemplate
                    .exchange(URL + "/goods/locker", HttpMethod.POST,
                            addLocker, Void.class);
        }
    }

    public static void addStaff(ArrayList<Cleaner> cleaners, ArrayList<Trainer> trainers, ArrayList<Manager> managers) {
        for (Cleaner cleaner : cleaners) {
            CleanerDTO cleanerDTO = new CleanerDTO();
            cleanerDTO.setName(cleaner.getName());
            cleanerDTO.setSalary(cleaner.getSalary());
            HttpEntity<CleanerDTO> addCleaner = new HttpEntity<>(cleanerDTO);
            ResponseEntity<Void> response = restTemplate
                    .exchange(URL + "/staff/cleaner", HttpMethod.POST,
                            addCleaner, Void.class);
        }
        for (Trainer trainer : trainers) {
            TrainerDTO trainerDTO = new TrainerDTO();
            trainerDTO.setName(trainer.getName());
            trainerDTO.setStatus(trainer.getStatus());
            HttpEntity<TrainerDTO> addTrainer = new HttpEntity<>(trainerDTO);
            ResponseEntity<Void> response = restTemplate
                    .exchange(URL + "/staff/trainer", HttpMethod.POST,
                            addTrainer, Void.class);
        }
        for (Manager manager : managers) {
            ManagerDTO managerDTO = new ManagerDTO();
            managerDTO.setName(manager.getName());
            managerDTO.setSalary(manager.getSalary());
            HttpEntity<ManagerDTO> addManager = new HttpEntity<>(managerDTO);
            ResponseEntity<Void> response = restTemplate
                    .exchange(URL + "/staff/manager", HttpMethod.POST,
                            addManager, Void.class);
        }
    }

    public static ArrayList<Apparatus> Apparatuses() {
        ArrayList<Apparatus> apparatuses = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            apparatuses.add(new Apparatus("Barbell" + Integer.toString(i), i));
        }
        for (int i = 0; i < 5; i++) {
            apparatuses.add(new Apparatus("Arbitrack" + Integer.toString(i), i));
        }
        for (int i = 0; i < 5; i++) {
            apparatuses.add(new Apparatus("SpeedRunner" + Integer.toString(i), i));
        }
        for (int i = 0; i < 5; i++) {
            apparatuses.add(new Apparatus("Dumbbells" + Integer.toString(i), i));
        }
        return apparatuses;
    }

    public static ArrayList<GroupMeet> groupMeets(List<Client> clients, List<Trainer> trainers, List<Date> date) {
        ArrayList<GroupMeet> groupMeets = new ArrayList<>();
        for (int i = 0; i < 30; i += 2) {
            int j = (int) (Math.random() * 10);
            ArrayList<Client> clients1 = new ArrayList<>();
            while (clients1.size() != 15) {
                int q = (int) (Math.random() * 100);
                if (!clients1.contains(clients.get(q))) {
                    clients1.add(clients.get(q));
                }
            }
            GroupMeet groupMeet = new GroupMeet(trainers.get(j), date.get(i), 15);
            for (Client client : clients1) {
                groupMeet.addClient(client);
            }
            groupMeets.add(groupMeet);

        }
        return groupMeets;
    }

    public static Owner owner() {
        return new Owner("Owner", 0);
    }

    public static ArrayList<Cleaner> cleaners() {
        ArrayList<Cleaner> cleaners = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            cleaners.add(new Cleaner("Cleaner" + Integer.toString(i), 1000));
        }
        return cleaners;
    }

    public static ArrayList<Trainer> trainers() {
        ArrayList<Trainer> trainers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            trainers.add(new Trainer("Trainer" + Integer.toString(i), "Master"));
        }
        return trainers;
    }

    public static ArrayList<Manager> managers() {
        ArrayList<Manager> managers = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            managers.add(new Manager("Manager" + Integer.toString(i), 5000));
        }
        return managers;
    }

    public static ArrayList<Client> clients() {
        ArrayList<Client> clients = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            clients.add(new Client("Client" + Integer.toString(i), false, false));
        }
        return clients;
    }

    public static ArrayList<Locker> lockers() {
        ArrayList<Locker> lockers = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            lockers.add(new Locker(i));
        }
        return lockers;
    }

    public static ArrayList<Shower> showers() {
        ArrayList<Shower> showers = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            showers.add(new Shower(i, true));
        }
        return showers;
    }

    public static ArrayList<Date> dates() {
        ArrayList<Date> date = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            date.add(new Date(2020, Calendar.SEPTEMBER, i + 1));
        }
        return date;
    }

    public static ArrayList<TrainerMeet> trainerMeets(List<Trainer> trainers, List<Client> clients, List<Date> date) {
        ArrayList<TrainerMeet> trainerMeets = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            int j = (int) (Math.random() * 10);
            int k = (int) (Math.random() * 100);
            trainerMeets.add(new TrainerMeet(trainers.get(j), clients.get(k), date.get(i)));
        }
        return trainerMeets;
    }

    public static ArrayList<CleanerTime> cleanerTimes(List<Cleaner> cleaners, List<Date> date) {
        ArrayList<CleanerTime> cleanerTimes = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            int j = (int) (Math.random() * 5);
            cleanerTimes.add(new CleanerTime(cleaners.get(j), date.get(i)));
        }
        return cleanerTimes;
    }


}