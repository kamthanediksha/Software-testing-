// ================
// Topic: Null and Undefined
// ================

/*
  SIMPLE DEFINITIONS:

  undefined  ->  A variable exists, but it has not been assigned any value yet.
                 JavaScript itself sets this automatically.

  null       ->  A variable exists, but the developer explicitly assigns
                "no value" or "empty".
                 It is intentional absence of any value.
*/

// var x = null ; // null value
// console.log(x);

// var audi = null;
// console.log(audi);

// --------------------------------------------------------
// 1. undefined
// --------------------------------------------------------

// let userName ;
// console.log(userName); // undefined
// console.log(typeof userName); // undefined

// function greet() {
// // no return statement
// }
// console.log(greet()); // undefined

let x ;
x = 10;
console.log(x); // 10

// ================
// 2.null
// ============
let profilePicture = null;
console.log(profilePicture);
console.log(typeof profilePicture); // object

/*
  | Feature              | undefined                     | null                           |
  |----------------------|-------------------------------|--------------------------------|
  | Meaning              | Not assigned yet              | Intentionally empty            |
  | Who sets it?         | JavaScript automatically      | Developer manually             |
  | Type                 | undefined                     | object (historical bug in JS)  |
  | ==  comparison        | null == undefined  -> true    |                                |
  | === comparison       | null === undefined -> false   |                                |
*/
