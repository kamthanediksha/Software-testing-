let a = 10;
console.log(a);

// Hot code

for (let a =0 ; a < 10000; a++) {
    console.log(a);
    badCodeFn();
}

function badCodeFn() {
    console.log("This is a bad function");
}
