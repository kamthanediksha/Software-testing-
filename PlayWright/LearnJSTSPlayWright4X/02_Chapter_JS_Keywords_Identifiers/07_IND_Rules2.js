// Only camelcase and Snake case are commonly used in JavaScript. Pascal case is used for class names, and SCREAMING SNAKE CASE is used for constants. Hungarian Notation is not commonly used in modern JavaScript.

var name = "diksha";

var firstName = "diksha";
var This_is_a_long_name_variable = "diksha"; // Valid identifier, can contain underscores and is case-sensitive

var lastName = "kamthane"; //Camel Case

//Naming Conventions (cases)
//================================
//1. camel case (standard for JS variables & Identifiers)
let userName = "diksha"; //camel case
let totalPrice = 100; //camel case
let isLoggedIn = true; //camel case

//2. Pascal case (standard for JS classes) - both Capital
let UserName = "diksha"; //Pascal case
let ShoppingCart = "class name style"; //Pascal case

//3. Snake case (standard for Python variables & Identifiers) - both small letters with underscore separated
let user_name = "diksha"; //snake case
let total_price = 100; //snake case
let is_logged_in = true; //snake case

//4. SCREAMING SNAKE CASE (standard for constants) - all capital letters with underscore separated
const MAX_VALUE = 100; // constants values cannot be changed
const API_KEY = "abc1234567890";
const DATABASE_URL = "https://example.com/database";

//5. Hungarian Notation (not commonly used in modern JS) - prefix variable names with a type indicator
let strUserName = "diksha"; // String
let intAge = 25; //number
let boolIsLoggedIn = true; //boolean
let arrItems = ["item1", "item2", "item3"]; //array
