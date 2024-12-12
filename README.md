# Patient Management System for Veterinary Clinic

## Project Description
This project is a patient management system designed for Margarita's veterinary clinic, specializing in the care of cats and dogs. The application allows for the management of patients, appointments, and treatments, maintaining a detailed and organized record. It is built using Java and the Spring Boot framework.

---

## Features
- **Patient Management**:
  - Add, update, list, and delete patient records.
  - Each patient profile includes:
    - Name
    - Age
    - Breed
    - Gender
    - Identification number
    - Owner's full name
    - Owner's phone number
  - Access patient data by identification number.

- **Appointment Management**:
  - Schedule, update, list, and delete appointments.
  - Each appointment includes:
    - Date and time
    - Patient
    - Type of appointment (standard/urgent)
    - Reason for the appointment
    - Appointment status (pending/completed).

- **Treatment History**:
  - View the history of treatments for each patient.

- **Optional Features**:
  - Add images to patient profiles using Firebase Storage.
  - User registration and management.

---

## Technical Requirements
- Built with **Java** and **Spring Boot**.
- Data stored in either an in-memory database (**H2**).
- Implements Object-Oriented Programming (OOP) principles:
  - Encapsulation
  - Layered architecture (controllers, services, repositories).
- Includes **DTOs** for data transfer.
- Unit tests for models, services, and controllers.

---

## Getting Started

### Prerequisites
- Java 21 or higher
- Maven

### Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/olegukr/veterinary_clinic.git
   cd veterinary_clinic```

### Activity diagram
 
```mermaid
graph TD
    Start[Start]
    Menu[User selects an option from the main menu]
    
    %% Filter by Date Workflow
    FilterByDate[User selects 'Filter by Date']
    EnterDate[User enters a date dd/mm/yyyy]
    InvalidDate[System checks if the date is valid: Invalid?]
    ShowError[Display error message: Invalid Date!]
    Retry[User retries entering a valid date]
    FetchMoments[Fetch moments for the selected date]
    ShowList[Display list of moments]
    ViewDone[User finishes viewing moments]
    ShowMessage[Display message: Press any key to return to main menu]

    %% Create Meeting Workflow
    CreateMeeting[User selects 'Create a appointment']
    EnterMeetingDetails[User enters appointment details: Date, Time, Reason, Patient]
    SaveMeeting[System saves the appointment]
    MeetingSuccess[System confirms appointment creation]

    %% Create Pet Workflow
    CreatePet[User selects 'Create a Pet']
    EnterPetDetails[User enters pet details: Name, Age, Breed, Gender, Owner Info]
    SavePet[System saves the pet]
    PetSuccess[System confirms pet creation]

    %% Connections
    Start --> Menu
    Menu --> FilterByDate
    Menu --> CreateMeeting
    Menu --> CreatePet

    %% Filter by Date Flow
    FilterByDate --> EnterDate
    EnterDate --> InvalidDate
    InvalidDate -->|Yes| ShowError
    ShowError --> Retry
    Retry --> EnterDate
    InvalidDate -->|No| FetchMoments
    FetchMoments --> ShowList
    ShowList --> ViewDone
    ViewDone --> ShowMessage
    ShowMessage --> Menu

    %% Create Meeting Flow
    CreateMeeting --> EnterMeetingDetails
    EnterMeetingDetails --> SaveMeeting
    SaveMeeting --> MeetingSuccess
    MeetingSuccess --> Menu

    %% Create Pet Flow
    CreatePet --> EnterPetDetails
    EnterPetDetails --> SavePet
    SavePet --> PetSuccess
    PetSuccess --> Menu
```

### Class diagram

```mermaid
classDiagram
    class Patient {
        +String name
        +int age
        +String breed
        +String gender
        +String id
        +String ownerName
        +String ownerPhone
        +List<Treatment> treatments
        +addTreatment(Treatment treatment)
        +getTreatments(): List<Treatment>
    }
    
    class Appointment {
        +String id
        +Date date
        +Time time
        +String type
        +String reason
        +String status
        +Patient patient
        +updateStatus(String newStatus)
    }
    
    class Treatment {
        +String description
        +Date date
        +String result
    }
    
    class User {
        +String username
        +String password
        +String role
        +authenticate(String password): boolean
    }
    
    class System {
        +List<Patient> patients
        +List<Appointment> appointments
        +addPatient(Patient patient)
        +findPatient(String id): Patient
        +addAppointment(Appointment appointment)
        +findAppointmentsByDate(Date date): List<Appointment>
    }

    Patient "1" --> "*" Appointment : "has"
    Patient "1" --> "*" Treatment : "has"
    System "1" --> "*" Patient : "manages"
    System "1" --> "*" Appointment : "manages"
    User "1" --> "1" System : "accesses"
```

