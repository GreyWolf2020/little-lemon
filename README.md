<img src="UI Design/Little Lemon.png"/>

Little Lemon application is a food ordering and reservation app, built using the MVVM architecture with
[Jetpack Compose](https://developer.android.com/jetpack/compose).

To try out this application, use the stable version of [Android Studio](https://developer.android.com/studio). You can copy the link of the git repository
[here](https://github.com/GreyWolf2020/little-lemon.git) to clone the application.

## Screenshots
<img src="UI Design/Little Lemon Screens.png"/>

## Features
This application contains the following screens: onboarding, home, menu description, order, reservation and profile.
The navigation between home, reservation and profile uses a navigation drawer.

### Onboarding Screen
The screen for registering the user.
It is only visible when the application is started if there is no logged-in user.
Otherwise, the application navigates home.
The onboarding captures the user's name, surname and email address.

### Home Screen
Home consists of the hero and menu section. The important information in the Hero section is the brief description of the restaurant, Little Lemon, a family-owned Mediterranean restaurant focussed on traditional recipes served with a modern twist. 
The menu section has a list of tantalising meals offered at the restaurant.

### Menu Description Screen
Selecting a meal at home navigates to its description. It shows the details of the selected meal.
There is an appetising image, the description, toppings of the meal, along with the prices. Adding a meal reveals a basket FAB on the bottom right off the screen.

### Order Screen
After adding a meal, clicking the basket FAB navigates the user to the order screen.
Order screen shows a list of meals being ordered, their quantity and their prices, as well as extra costs for the delivery run and service.
The secure Checkout button is clearly labeled, and upon selecting it, the transaction for the meal is securely paid.

### Reservation Screen
The Reservation screen has three sections:
* Reserve info form for name, date and time for the reservation.
* Payment form for the address, card number, CVV and expiration date and month.
* Review Information section with all the captured information.
A prominent "Submit" button navigates to a confirmation screen on success, or displays errors on invalid input and "Cancel" button that cancels the reservation process.

### Profile Screen
The screen shows the logged-in user information and a logout button.

## Data
The menu items information is fetched from a [remote server]("https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json") using [Ktor](https://ktor.io/) library and the remote information is modelled in [Network](app/src/main/java/com/example/littlelemon/data/remote/menu/Network.kt).
The menu items information is cached locally in an SQLite database using Room library.
The locally cached information is modelled in [MenuDatabase](app/src/main/java/com/example/littlelemon/data/local/menu/MenuDatabase.kt).  Imagery🎨 is loaded using [Coil](https://coil-kt.github.io/coil/) library.