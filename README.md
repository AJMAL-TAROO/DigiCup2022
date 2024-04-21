# DigiCup2022
This app was develop during DigiCup2022 a mobile application competition where the goal is to help NGOs towards acheiving their goals. Here the NGO in play is ZET which is an NGO located in Curepipe, Mauritus and helps the people in need.


## The app is in 4 fragments:
### The first 2 fragments are not a big of a deal. They were implemented for showcasing only.
1. HomePage: Showing what the NGO ZET is all about.
2. Event Page: This one is just linked to facebook to see how people will attend the events.
### The last two are what made the app ranked top 3 in the competitiom:
3. Donation Page: This section query the Firebase Realtime Database to get all the causes and each of them have their own donation threshold and other data which was set using an admin app which pushes those data onto firebase. Upon clicking on a cause it redirects the user to a page to see the location of the people in need and a video to show the actual scene and it has a donation button which uses Google Pay Gateway to allow users to make donations using their credit cards.
4. The shop section has some items where users can buy some items by and a percentage goes to ZET as charity, therefore it is a win-win situation where the users gets something in return and also giving charity as well. The payment is done using Paypal Checkout Integration for this one.

