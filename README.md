# Restaurant Management

## Description
A Java/Spring application for managing a restaurant:
menu items, staff members, tables, orders, customers and bills.


## Technologies
- Java 25
- Spring


## Project structure
- `model` – entities (Staff, Server, Chef, MenuItem, Bill, Customer, Order, Table, OrderLine, OrderAssignment)
- `repository` – in-memory storage 
- `service` – business logic and validation
- `controller` – web controllers that handle HTTP requests and select the view
- `src/main/resources/templates` – Thymeleaf templates (one folder per entity)


## SOLID principles
- **Single Responsibility:** each layer has one job. Repositories only store data,
  services only validate and apply business rules, and controllers only handle
  interaction with the user. A change in one concern touches only one class.

- **Open/Closed:** `InMemoryRepository<T>` implements the CRUD logic once and is closed
  for modification. Supporting a new entity only requires extending it,
  without changing any existing code.


## Architecture
The project uses a layered architecture. Each layer only depends on the one below it:
controller -> service -> repository -> model

- **model** has no dependencies on other layers.
  Chef and Server extend Staff, so a single repository handles all staff members.
  
- **repository** only stores data. A generic `IRepository<T>` interface and an
  abstract `InMemoryRepository<T>` base class implement the CRUD logic once
  (`save`, `findById`, `findAll`, `delete`), so it is not repeated for each
  entity. Some repositories add their own methods (
  `TableRepository.findByNumber`).
  
- **service** holds all validation and business rules, so they apply no matter
  how the data is accessed. Services depend on the concrete repository classes
  (`ChefService` -> `ChefRepository`).
  
 -**controller** talks only to services, never directly to repositories. Because
all repositories extend `InMemoryRepository<T>`, the storage mechanism is
encapsulated in one base class: it could be replaced with files or a database by
changing that class, without modifying the services.

 -**src/main/resources/templates** Thymeleaf templates (one folder per entity)


## Web interface (Thymeleaf)
Each entity has its own folder under `src/main/resources/templates/` with two
templates:
* `index.html` – a table listing all entities, with a Delete button per row
* `form.html` – a form for creating a new entity

  
### How a request is handled
1. **Listing:** the browser sends `GET /<entity>`. The controller puts the list
   into the `Model` (`model.addAttribute("orders", ...)`) and returns the
   template name (`"order/index"`). Thymeleaf renders the HTML, iterating the
   list with `th:each`.
2. **Creating:** `GET /<entity>/new` puts an empty object into the `Model` and
   shows `form.html`. The form is bound to it with `th:object` and `th:field`.
   On submit, the browser sends `POST /<entity>`; Spring creates the object
   from the form values (using the empty constructor and the setters) and passes
   it to the controller, which calls the service.
3. **Deleting:** the Delete button submits a form with `POST /<entity>/{id}/delete`.
   The `{id}` is read in the controller with `@PathVariable`.
4. After every POST the controller returns `redirect:/<entity>`
   (Post/Redirect/Get). The browser then requests the list again, so the page
   always shows the current data and a refresh does not resubmit the form.


### Available pages
The application runs on `http://localhost:8080`.

| Entity    | List                  | New                       |
|-----------|-----------------------|---------------------------|
| Customer  | `/customer`           | `/customer/new`           |
| Table     | `/table`              | `/table/new`              |
| Menu item | `/menuItem`           | `/menuItem/new`           |
| Chef      | `/chef`               | `/chef/new`               |
| Server    | `/server`             | `/server/new`             |
| Order     | `/order`              | `/order/new`              |
| Bill      | `/bill`               | `/bill/new`               |

Create entities in this order: customer and table first, then the order (using
the customer ID and table ID shown in the lists), then the bill (using the order
ID).


## How to run
1. Clone the repo
2. Open it in IntelliJ
3. Run the `RestaurantManagementAppApplication`
4. Open `http://localhost:8080/customer` (or any other page from the table above)


## Authors
- Sara Vlaicu
- Ana-Maria Malanca
